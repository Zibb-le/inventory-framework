package org.zibble.inventoryframework.protocol.item.objects.buttons;

import org.zibble.inventoryframework.protocol.item.objects.enums.Enchantment;

/**
 * Class meant to be used a type parameter for {@link org.zibble.inventoryframework.MenuItem} class to allow displaying enchants in {@link org.zibble.inventoryframework.menu.inventory.buttonmenus.EnchantingTableMenu}
 */
public class EnchantOption {

    private final int xpLevelCost;
    private final int enchantID;
    private final int enchantLevel;

    private EnchantOption(int xpLevelCost, int enchantment, int level) {
        this.xpLevelCost = xpLevelCost;
        this.enchantID = enchantment;
        this.enchantLevel = level;
    }

    /**
     * Creates an enchant option whose name is visible to the player
     * @param xpLevelCost The amount of XP levels required to use this option
     * @param enchantment The enchantment to be shown
     * @param enchantLevel The level of the enchantment to be shown
     * @return An instance of {@link EnchantOption} with the given parameters
     */
    public static EnchantOption shown(int xpLevelCost, Enchantment enchantment, int enchantLevel) {
        return new EnchantOption(xpLevelCost, enchantment.getID(), enchantLevel);
    }

    /**
     * Creates an enchant option whose name is hidden from the player
     * @param xpLevelCost The amount of XP levels required to use this option
     * @param enchantLevel The level of the enchantment to be shown
     * @return An instance of {@link EnchantOption} with the given parameters
     */
    public static EnchantOption hidden(int xpLevelCost, int enchantLevel) {
        return new EnchantOption(xpLevelCost, -1, enchantLevel);
    }

    /**
     * @return The amount of XP levels required to use this enchant option
     */
    public int getXPLevelCost() {
        return this.xpLevelCost;
    }

    /**
     * @return The enchantment ID of this enchant option, or -1 if the enchant option is hidden
     */
    public int getEnchantmentID() {
        return this.enchantID;
    }

    /**
     * @return The level of the enchantment to be shown
     */
    public int getEnchantLevel() {
        return this.enchantLevel;
    }

}