package com.pepedevs.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.*;
import com.pepedevs.inventoryframework.protocol.Material;
import com.pepedevs.inventoryframework.protocol.Materials;
import com.pepedevs.inventoryframework.protocol.item.objects.FireworkEffect;
import com.pepedevs.inventoryframework.protocol.item.objects.enums.Enchantment;

import java.util.Map;

public class MetaUtil {

    private static final String DISPLAY = "display";

    private static final String ENCHANTMENTS = "ench";
    private static final String ENCHANT_ID = "id";
    private static final String ENCHANT_LVL = "lvl";

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

    protected static NBTCompound asMeta(FireworkEffect fireworkEffect) {
        //TODO
        return null;
    }

    protected static void applyEnchants(Map<Enchantment, Integer> enchantments, NBTCompound target) {
        if (enchantments.size() > 0) {
            NBTList<NBTCompound> enchants = new NBTList<>(NBTType.COMPOUND);
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                NBTCompound enchant = new NBTCompound();
                enchant.setTag(ENCHANT_ID, new NBTShort((short) entry.getKey().getID()));
                enchant.setTag(ENCHANT_LVL, new NBTShort(entry.getValue().shortValue()));
                enchants.addTag((short) entry.getKey().getPacketType().getId(), enchant);
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
