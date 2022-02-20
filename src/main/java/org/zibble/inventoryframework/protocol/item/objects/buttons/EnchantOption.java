package org.zibble.inventoryframework.protocol.item.objects.buttons;

import org.zibble.inventoryframework.protocol.item.objects.enums.Enchantment;

public class EnchantOption {

    private final int xpLevelCost;
    private final int enchantID;
    private final int enchantLevel;

    private EnchantOption(int xpLevelCost, int enchantment, int level) {
        this.xpLevelCost = xpLevelCost;
        this.enchantID = enchantment;
        this.enchantLevel = level;
    }

    public static EnchantOption shown(int xpLevelCost, Enchantment enchantment, int enchantLevel) {
        return new EnchantOption(xpLevelCost, enchantment.getID(), enchantLevel);
    }

    public static EnchantOption hidden(int xpLevelCost, int enchantLevel) {
        return new EnchantOption(xpLevelCost, -1, enchantLevel);
    }

    public int getXpLevelCost() {
        return this.xpLevelCost;
    }

    public int getEnchantID() {
        return this.enchantID;
    }

    public int getEnchantLevel() {
        return this.enchantLevel;
    }

}