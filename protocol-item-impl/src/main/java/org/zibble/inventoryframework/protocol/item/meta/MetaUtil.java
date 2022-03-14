package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.*;
import org.zibble.inventoryframework.protocol.ItemMaterial;
import org.zibble.inventoryframework.protocol.ItemMaterials;
import org.zibble.inventoryframework.protocol.item.objects.EffectFirework;
import org.zibble.inventoryframework.protocol.item.objects.enums.EnumEnchant;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@ApiStatus.Internal
public class MetaUtil {

    private static final String DISPLAY = "display";

    private static final String ENCHANTMENTS = "ench";
    private static final String ENCHANT_ID = "id";
    private static final String ENCHANT_LVL = "lvl";

    private static final String EXPLOSION_FLICKER = "Flicker";
    private static final String EXPLOSION_TRAIL = "Trail";
    private static final String EXPLOSION_COLORS = "Colors";
    private static final String EXPLOSION_FADE = "FadeColors";
    private static final String EXPLOSION_TYPE = "Type";

    @ApiStatus.Internal
    public static MetaItem getMeta(final ItemMaterial type) {
        if (ItemMaterials.WRITABLE_BOOK.equals(type) || ItemMaterials.WRITTEN_BOOK.equals(type)) {
            return new MetaBook();
        } else if (ItemMaterials.PLAYER_HEAD.equals(type)) {
            return new MetaSkull();
        } else if (ItemMaterials.LEATHER_HELMET.equals(type)
                || ItemMaterials.LEATHER_CHESTPLATE.equals(type)
                || ItemMaterials.LEATHER_LEGGINGS.equals(type)
                || ItemMaterials.LEATHER_BOOTS.equals(type)) {
            return new MetaLeatherArmor();
        } else if (ItemMaterials.POTION.equals(type)) {
            return new MetaPotion();
        } else if (ItemMaterials.MAP.equals(type)) {
            return new MetaMap();
        } else if (ItemMaterials.FIREWORK_ROCKET.equals(type)) {
            return new MetaFirework();
        } else if (ItemMaterials.FIRE_CHARGE.equals(type)) {
            return new MetaCharge();
        } else if (ItemMaterials.ENCHANTED_BOOK.equals(type)) {
            return new MetaEnchantedBook();
        } else if (ItemMaterials.BLACK_BANNER.equals(type)
                || ItemMaterials.BLUE_BANNER.equals(type)
                || ItemMaterials.BROWN_BANNER.equals(type)
                || ItemMaterials.CYAN_BANNER.equals(type)
                || ItemMaterials.GRAY_BANNER.equals(type)
                || ItemMaterials.GREEN_BANNER.equals(type)
                || ItemMaterials.LIGHT_BLUE_BANNER.equals(type)
                || ItemMaterials.LIGHT_GRAY_BANNER.equals(type)
                || ItemMaterials.LIME_BANNER.equals(type)
                || ItemMaterials.MAGENTA_BANNER.equals(type)
                || ItemMaterials.ORANGE_BANNER.equals(type)
                || ItemMaterials.PINK_BANNER.equals(type)
                || ItemMaterials.PURPLE_BANNER.equals(type)
                || ItemMaterials.RED_BANNER.equals(type)
                || ItemMaterials.WHITE_BANNER.equals(type)
                || ItemMaterials.YELLOW_BANNER.equals(type)
                || ItemMaterials.CREEPER_BANNER_PATTERN.equals(type)
                || ItemMaterials.SKULL_BANNER_PATTERN.equals(type)
                || ItemMaterials.MOJANG_BANNER_PATTERN.equals(type)
                || ItemMaterials.FLOWER_BANNER_PATTERN.equals(type)
                || ItemMaterials.GLOBE_BANNER_PATTERN.equals(type)
                || ItemMaterials.PIGLIN_BANNER_PATTERN.equals(type)) {
            return new MetaBanner(type);
        }
        return new MetaItem();
    }

    protected static void applyFireworkEffect(EffectFirework effect, NBTCompound target) {
        if (effect.hasFlicker()) {
            target.setTag(EXPLOSION_FLICKER, new NBTByte((byte) 1));
        }
        if (effect.hasTrail()) {
            target.setTag(EXPLOSION_TRAIL, new NBTByte((byte) 1));
        }
        int[] colors = new int[effect.getColors().size()];
        for (int i = 0; i < effect.getColors().size(); i++) {
            colors[i] = effect.getColors().get(i).getRGB();
        }
        target.setTag(EXPLOSION_COLORS, new NBTIntArray(colors));
        int[] fadeColors = new int[effect.getFadeColors().size()];
        for (int i = 0; i < effect.getFadeColors().size(); i++) {
            fadeColors[i] = effect.getFadeColors().get(i).getRGB();
        }
        target.setTag(EXPLOSION_FADE, new NBTIntArray(fadeColors));
        target.setTag(EXPLOSION_TYPE, new NBTByte((byte) effect.getType().ordinal()));
    }

    protected static void applyEnchants(Map<EnumEnchant, Integer> enchantments, NBTCompound target) {
        if (enchantments.size() > 0) {
            NBTList<NBTCompound> enchants = new NBTList<>(NBTType.COMPOUND);
            for (Map.Entry<EnumEnchant, Integer> entry : enchantments.entrySet()) {
                NBTCompound enchant = new NBTCompound();
                enchant.setTag(ENCHANT_ID, new NBTShort((short) entry.getKey().getId()));
                enchant.setTag(ENCHANT_LVL, new NBTShort(entry.getValue().shortValue()));
                enchants.addTag((short) entry.getKey().asProtocol().getId(), enchant);
            }
            target.setTag(ENCHANTMENTS, enchants);
        }
    }

    protected static void applyDisplayTag(String key, NBT value, @NotNull NBTCompound target) {
        NBTCompound display = target.getCompoundTagOrNull(DISPLAY);
        if (display == null) {
            target.setTag(DISPLAY, display = new NBTCompound());
        }
        display.setTag(key, value);
    }

}
