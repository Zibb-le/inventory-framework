package com.pepedevs.inventoryframework.protocol.item.meta;

import com.pepedevs.inventoryframework.protocol.Material;
import com.pepedevs.inventoryframework.protocol.Materials;

public class MetaUtil {

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

}
