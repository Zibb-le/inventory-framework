package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.*;
import org.zibble.inventoryframework.protocol.Material;
import org.zibble.inventoryframework.protocol.Materials;
import org.zibble.inventoryframework.protocol.item.objects.FireworkEffect;
import org.zibble.inventoryframework.protocol.item.objects.enums.Enchantment;

import java.util.Map;

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

    public static ItemMeta getMeta(final Material type) {
        if (Materials.WRITABLE_BOOK.equals(type) || Materials.WRITTEN_BOOK.equals(type)) {
            return new BookMeta();
        } else if (Materials.PLAYER_HEAD.equals(type)) {
            return new SkullMeta();
        } else if (Materials.LEATHER_HELMET.equals(type)
                || Materials.LEATHER_CHESTPLATE.equals(type)
                || Materials.LEATHER_LEGGINGS.equals(type)
                || Materials.LEATHER_BOOTS.equals(type)) {
            return new LeatherArmorMeta();
        } else if (Materials.POTION.equals(type)) {
            return new PotionMeta();
        } else if (Materials.MAP.equals(type)) {
            return new MapMeta();
        } else if (Materials.FIREWORK_ROCKET.equals(type)) {
            return new FireworkMeta();
        } else if (Materials.FIRE_CHARGE.equals(type)) {
            return new ChargeMeta();
        } else if (Materials.ENCHANTED_BOOK.equals(type)) {
            return new EnchantedBookMeta();
        } else if (Materials.BLACK_BANNER.equals(type)
                || Materials.BLUE_BANNER.equals(type)
                || Materials.BROWN_BANNER.equals(type)
                || Materials.CYAN_BANNER.equals(type)
                || Materials.GRAY_BANNER.equals(type)
                || Materials.GREEN_BANNER.equals(type)
                || Materials.LIGHT_BLUE_BANNER.equals(type)
                || Materials.LIGHT_GRAY_BANNER.equals(type)
                || Materials.LIME_BANNER.equals(type)
                || Materials.MAGENTA_BANNER.equals(type)
                || Materials.ORANGE_BANNER.equals(type)
                || Materials.PINK_BANNER.equals(type)
                || Materials.PURPLE_BANNER.equals(type)
                || Materials.RED_BANNER.equals(type)
                || Materials.WHITE_BANNER.equals(type)
                || Materials.YELLOW_BANNER.equals(type)
                || Materials.CREEPER_BANNER_PATTERN.equals(type)
                || Materials.SKULL_BANNER_PATTERN.equals(type)
                || Materials.MOJANG_BANNER_PATTERN.equals(type)
                || Materials.FLOWER_BANNER_PATTERN.equals(type)
                || Materials.GLOBE_BANNER_PATTERN.equals(type)
                || Materials.PIGLIN_BANNER_PATTERN.equals(type)) {
            return new BannerMeta();
        }
        return new ItemMeta();
    }

    protected static void applyFireworkEffect(FireworkEffect effect, NBTCompound target) {
        if (effect.hasFlicker()) {
            target.setTag(EXPLOSION_FLICKER, new NBTByte((byte) 1));
        }
        if (effect.hasTrail()) {
            target.setTag(EXPLOSION_TRAIL, new NBTByte((byte) 1));
        }
        int[] colors = new int[effect.colors().size()];
        for (int i = 0; i < effect.colors().size(); i++) {
            colors[i] = effect.colors().get(i).getRGB();
        }
        target.setTag(EXPLOSION_COLORS, new NBTIntArray(colors));
        int[] fadeColors = new int[effect.fadeColors().size()];
        for (int i = 0; i < effect.fadeColors().size(); i++) {
            fadeColors[i] = effect.fadeColors().get(i).getRGB();
        }
        target.setTag(EXPLOSION_FADE, new NBTIntArray(fadeColors));
        target.setTag(EXPLOSION_TYPE, new NBTByte((byte) effect.type().ordinal()));
    }

    protected static void applyEnchants(Map<Enchantment, Integer> enchantments, NBTCompound target) {
        if (enchantments.size() > 0) {
            NBTList<NBTCompound> enchants = new NBTList<>(NBTType.COMPOUND);
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                NBTCompound enchant = new NBTCompound();
                enchant.setTag(ENCHANT_ID, new NBTShort((short) entry.getKey().id()));
                enchant.setTag(ENCHANT_LVL, new NBTShort(entry.getValue().shortValue()));
                enchants.addTag((short) entry.getKey().asProtocol().getId(), enchant);
            }
            target.setTag(ENCHANTMENTS, enchants);
        }
    }

    protected static void applyDisplayTag(String key, NBT value, NBTCompound target) {
        NBTCompound display = target.getCompoundTagOrNull(DISPLAY);
        if (display == null) {
            target.setTag(DISPLAY, display = new NBTCompound());
        }
        display.setTag(key, value);
    }

}
