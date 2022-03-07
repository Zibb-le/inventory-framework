package org.zibble.inventoryframework;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.netty.buffer.ByteBufHelper;
import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.item.type.ItemType;
import com.github.retrooper.packetevents.protocol.item.type.ItemTypes;
import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.github.retrooper.packetevents.protocol.nbt.codec.NBTCodec;
import com.github.retrooper.packetevents.protocol.player.GameMode;
import com.github.retrooper.packetevents.util.AdventureSerializer;
import com.github.retrooper.packetevents.util.StringUtil;
import com.github.retrooper.packetevents.util.Vector3i;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@ApiStatus.Internal
public final class ByteBufferUtil {

    private static final int MODERN_MESSAGE_LENGTH = 262144;
    private static final int LEGACY_MESSAGE_LENGTH = 32767;

    public static byte readByte(Object buffer) {
        return ByteBufHelper.readByte(buffer);
    }

    public static void writeByte(Object buffer, int value) {
        ByteBufHelper.writeByte(buffer, value);
    }

    public static short readUnsignedByte(Object buffer) {
        return ByteBufHelper.readUnsignedByte(buffer);
    }

    public static boolean readBoolean(Object buffer) {
        return readByte(buffer) != 0;
    }

    public static void writeBoolean(Object buffer, boolean value) {
        writeByte(buffer, value ? 1 : 0);
    }

    public static int readInt(Object buffer) {
        return ByteBufHelper.readInt(buffer);
    }

    public static void writeInt(Object buffer, int value) {
        ByteBufHelper.writeInt(buffer, value);
    }

    public static int readVarInt(Object buffer) {
        int value = 0;
        int length = 0;
        byte currentByte;
        do {
            currentByte = readByte(buffer);
            value |= (currentByte & 0x7F) << (length * 7);
            length++;
            if (length > 5) {
                throw new RuntimeException("VarInt is too large. Must be smaller than 5 bytes.");
            }
        } while ((currentByte & 0x80) == 0x80);
        return value;
    }

    public static void writeVarInt(Object buffer, int value) {
        while (true) {
            if ((value & ~0x7F) == 0) {
                writeByte(buffer, value);
                return;
            }
            writeByte(buffer, (value & 0x7F) | 0x80);
            value >>>= 7;
        }
    }

    @NotNull
    public static ItemStack readItemStack(Object buffer) {
        boolean v1_13_2 = InventoryFramework.framework().getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_13_2);
        if (v1_13_2) {
            if (!readBoolean(buffer)) {
                return ItemStack.EMPTY;
            }
        }
        int typeID = v1_13_2 ? readVarInt(buffer) : readShort(buffer);
        if (typeID < 0) {
            return ItemStack.EMPTY;
        }
        ItemType type = ItemTypes.getById(typeID);
        int amount = readByte(buffer);
        int legacyData = v1_13_2 ? -1 : readShort(buffer);
        NBTCompound nbt = readNBT(buffer);
        return ItemStack.builder()
                .type(type)
                .amount(amount)
                .nbt(nbt)
                .legacyData(legacyData)
                .build();
    }

    public static void writeItemStack(Object buffer, ItemStack itemStack) {
        if (itemStack == null) {
            itemStack = ItemStack.EMPTY;
        }
        boolean v1_13_2 = InventoryFramework.framework().getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_13_2);
        if (v1_13_2) {
            if (ItemStack.EMPTY.equals(itemStack)) {
                writeBoolean(buffer, false);
            } else {
                writeBoolean(buffer, true);
                int typeID;
                if (itemStack.getType() == null || ItemStack.EMPTY.equals(itemStack)) {
                    typeID = -1;
                } else {
                    typeID = itemStack.getType().getId();
                }
                writeVarInt(buffer, typeID);
                if (typeID >= 0) {
                    writeByte(buffer, itemStack.getAmount());
                    writeNBT(buffer, itemStack.getNBT());
                }
            }
        } else {
            int typeID;
            if (itemStack.isEmpty()) {
                typeID = -1;
            } else {
                typeID = itemStack.getType().getId();
            }
            writeShort(buffer, typeID);
            if (typeID >= 0) {
                writeByte(buffer, itemStack.getAmount());
                writeShort(buffer, itemStack.getLegacyData());
                writeNBT(buffer, itemStack.getNBT());
            }
        }
    }

    public static NBTCompound readNBT(Object buffer) {
        return NBTCodec.readNBT(buffer, InventoryFramework.framework().getServerVersion());
    }

    public static void writeNBT(Object buffer, NBTCompound nbt) {
        NBTCodec.writeNBT(buffer, InventoryFramework.framework().getServerVersion(), nbt);
    }

    public static String readString(Object buffer) {
        return readString(buffer, 32767);
    }

    public static String readString(Object buffer, int maxLen) {
        int j = readVarInt(buffer);
        if (j > maxLen * 4) {
            throw new RuntimeException("The received encoded string buffer length is longer than maximum allowed (" + j + " > " + maxLen * 4 + ")");
        } else if (j < 0) {
            throw new RuntimeException("The received encoded string buffer length is less than zero! Weird string!");
        } else {
            String s = ByteBufHelper.toString(buffer, ByteBufHelper.readerIndex(buffer), j, StandardCharsets.UTF_8);
            ByteBufHelper.readerIndex(buffer, ByteBufHelper.readerIndex(buffer) + j);
            if (s.length() > maxLen) {
                throw new RuntimeException("The received string length is longer than maximum allowed (" + j + " > " + maxLen + ")");
            } else {
                return s;
            }
        }
    }

    public static void writeString(Object buffer, String s) {
        writeString(buffer, s, 32767);
    }

    public static void writeString(Object buffer, String s, int maxLen) {
        writeString(buffer, s, maxLen, true);
    }

    public static void writeString(Object buffer, String s, int maxLen, boolean substr) {
        if (substr) {
            s = StringUtil.maximizeLength(s, maxLen);
        }
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        if (!substr && bytes.length > maxLen) {
            throw new IllegalStateException("String too big (was " + bytes.length + " bytes encoded, max " + maxLen + ")");
        } else {
            writeVarInt(buffer, bytes.length);
            ByteBufHelper.writeBytes(buffer, bytes);
        }
    }

    public static Component readComponent(Object buffer) {
        return AdventureSerializer.parseComponent(readString(buffer, getMaxMessageLength()));
    }

    public static void writeComponent(Object buffer, Component component) {
        writeString(buffer, AdventureSerializer.toJson(component));
    }

    public static int readUnsignedShort(Object buffer) {
        return ByteBufHelper.readUnsignedShort(buffer);
    }

    public static short readShort(Object buffer) {
        return ByteBufHelper.readShort(buffer);
    }

    public static void writeShort(Object buffer, int value) {
        ByteBufHelper.writeShort(buffer, value);
    }

    public static int readVarShort(Object buffer) {
        int low = readUnsignedShort(buffer);
        int high = 0;
        if ((low & 0x8000) != 0) {
            low = low & 0x7FFF;
            high = readUnsignedByte(buffer);
        }
        return ((high & 0xFF) << 15) | low;
    }

    public static void writeVarShort(Object buffer, int value) {
        int low = value & 0x7FFF;
        int high = (value & 0x7F8000) >> 15;
        if (high != 0) {
            low = low | 0x8000;
        }
        writeShort(buffer, low);
        if (high != 0) {
            writeByte(buffer, high);
        }
    }

    public static long readLong(Object buffer) {
        return ByteBufHelper.readLong(buffer);
    }

    public static void writeLong(Object buffer, long value) {
        ByteBufHelper.writeLong(buffer, value);
    }

    public static long readVarLong(Object buffer) {
        long value = 0;
        int size = 0;
        int b;
        while (((b = readByte(buffer)) & 0x80) == 0x80) {
            value |= (long) (b & 0x7F) << (size++ * 7);
        }
        return value | ((long) (b & 0x7F) << (size * 7));
    }

    public static void writeVarLong(Object buffer, long l) {
        while ((l & ~0x7F) != 0) {
            writeByte(buffer, (int) (l & 0x7F) | 0x80);
            l >>>= 7;
        }

        writeByte(buffer, (int) l);
    }

    public static float readFloat(Object buffer) {
        return ByteBufHelper.readFloat(buffer);
    }

    public static void writeFloat(Object buffer, float value) {
        ByteBufHelper.writeFloat(buffer, value);
    }

    public static double readDouble(Object buffer) {
        return ByteBufHelper.readDouble(buffer);
    }

    public static void writeDouble(Object buffer, double value) {
        ByteBufHelper.writeDouble(buffer, value);
    }

    public static byte[] readRemainingBytes(Object buffer) {
        return readBytes(buffer, ByteBufHelper.readableBytes(buffer));
    }

    public static byte[] readBytes(Object buffer, int size) {
        byte[] bytes = new byte[size];
        ByteBufHelper.readBytes(buffer, bytes);
        return bytes;
    }

    public static void writeBytes(Object buffer, byte[] array) {
        ByteBufHelper.writeBytes(buffer, array);
    }

    public static byte[] readByteArray(Object buffer, int maxLength) {
        int len = readVarInt(buffer);
        if (len > maxLength) {
            throw new RuntimeException("The received byte array length is longer than maximum allowed (" + len + " > " + maxLength + ")");
        }
        return readBytes(buffer, len);
    }

    public static byte[] readByteArray(Object buffer) {
        int len = readVarInt(buffer);
        return readBytes(buffer, len);
    }

    public static void writeByteArray(Object buffer, byte[] array) {
        writeVarInt(buffer, array.length);
        writeBytes(buffer, array);
    }

    public static int[] readVarIntArray(Object buffer) {
        int readableBytes = ByteBufHelper.readableBytes(buffer);
        int size = readVarInt(buffer);
        if (size > readableBytes) {
            throw new IllegalStateException("VarIntArray with size " + size + " is bigger than allowed " + readableBytes);
        }

        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = readVarInt(buffer);
        }
        return array;
    }

    public static void writeVarIntArray(Object buffer, int[] array) {
        writeVarInt(buffer, array.length);
        for (int i : array) {
            writeVarInt(buffer, i);
        }
    }

    public static long[] readLongArray(Object buffer, int size) {
        long[] array = new long[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = readLong(buffer);
        }
        return array;
    }

    public static byte[] readByteArrayOfSize(Object buffer, int size) {
        byte[] array = new byte[size];
        ByteBufHelper.readBytes(buffer, array);
        return array;
    }

    public static void writeByteArrayOfSize(Object buffer, byte[] array) {
        ByteBufHelper.writeBytes(buffer, array);
    }

    public static int[] readVarIntArrayOfSize(Object buffer, int size) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = readVarInt(buffer);
        }
        return array;
    }

    public static void writeVarIntArrayOfSize(Object buffer, int[] array) {
        for (int i : array) {
            writeVarInt(buffer, i);
        }
    }

    public static long[] readLongArray(Object buffer) {
        int readableBytes = ByteBufHelper.readableBytes(buffer) / 8;
        int size = readVarInt(buffer);
        if (size > readableBytes) {
            throw new IllegalStateException("LongArray with size " + size + " is bigger than allowed " + readableBytes);
        }
        long[] array = new long[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = readLong(buffer);
        }
        return array;
    }

    public static void writeLongArray(Object buffer, long[] array) {
        writeVarInt(buffer, array.length);
        for (long l : array) {
            writeLong(buffer, l);
        }
    }

    public static UUID readUUID(Object buffer) {
        long mostSigBits = readLong(buffer);
        long leastSigBits = readLong(buffer);
        return new UUID(mostSigBits, leastSigBits);
    }

    public static void writeUUID(Object buffer, UUID uuid) {
        writeLong(buffer, uuid.getMostSignificantBits());
        writeLong(buffer, uuid.getLeastSignificantBits());
    }

    public static Vector3i readBlockPosition(Object buffer) {
        long val = readLong(buffer);
        return new Vector3i(val, InventoryFramework.framework().getServerVersion());
    }

    public static void writeBlockPosition(Object buffer, Vector3i pos) {
        long val = pos.getSerializedPosition(InventoryFramework.framework().getServerVersion());
        writeLong(buffer, val);
    }

    public static GameMode readGameMode(Object buffer) {
        return GameMode.getById(readByte(buffer));
    }

    public static void writeGameMode(Object buffer, @Nullable GameMode mode) {
        int id = mode == null ? -1 : mode.getId();
        writeByte(buffer, id);
    }

    public static byte[] toByteArray(Object buffer) {
        return ByteBufHelper.copyBytes(buffer);
    }

    public static int getMaxMessageLength() {
        return InventoryFramework.framework().getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_13) ? MODERN_MESSAGE_LENGTH : LEGACY_MESSAGE_LENGTH;
    }

}
