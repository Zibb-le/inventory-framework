package org.zibble.inventoryframework.protocol.item.objects.buttons;

import com.github.retrooper.packetevents.protocol.recipe.data.MerchantRecipeData;
import org.jetbrains.annotations.ApiStatus;
import org.zibble.inventoryframework.protocol.item.ItemStack;

/**
 * Class meant to be used a type parameter for {@link org.zibble.inventoryframework.MenuItem} class to allow displaying enchants in {@link org.zibble.inventoryframework.menu.inventory.buttonmenus.MerchantMenu}
 * Refer to <a href="https://wiki.vg/Protocol#Trade_List">Trade List</a>
 */
public class TradeOption {

    private final ItemStack buyItem1;
    private final ItemStack buyItem2;
    private final ItemStack sellItem;
    private final int uses;
    private final int maxUses;
    private final int xp;
    private final float priceMultiplier;
    private final int specialPrice;
    private final int demand;

    private TradeOption(ItemStack buyItem1, ItemStack buyItem2, ItemStack sellItem, int uses, int maxUses, int xp, float priceMultiplier, int demand, int specialPrice) {
        this.buyItem1 = buyItem1;
        this.buyItem2 = buyItem2;
        this.sellItem = sellItem;
        this.uses = uses;
        this.maxUses = maxUses;
        this.xp = xp;
        this.priceMultiplier = priceMultiplier;
        this.demand = demand;
        this.specialPrice = specialPrice;
    }

    /**
     * @return The primary buy item
     */
    public ItemStack getPrimaryBuyItem() {
        return buyItem1;
    }

    /**
     * @return The secondary buy item or null if empty
     */
    public ItemStack getSecondaryBuyItem() {
        return buyItem2;
    }

    /**
     * @return The sell item
     */
    public ItemStack getSellItem() {
        return sellItem;
    }

    /**
     * @return The uses of the current trade option
     */
    public int getUses() {
        return uses;
    }

    /**
     * @return The max amount of uses for the current trade option
     */
    public int getMaxUses() {
        return maxUses;
    }

    /**
     * @return The amount of xp gained by the villager from each trade
     */
    public int getXP() {
        return xp;
    }

    /**
     * @return The price multiplier for the current trade option
     */
    public float getPriceMultiplier() {
        return priceMultiplier;
    }

    /**
     * @return The special price for the current trade option
     */
    public int getSpecialPrice() {
        return specialPrice;
    }

    /**
     * @return The demand for the current trade option
     */
    public int getDemand() {
        return demand;
    }

    @ApiStatus.Internal
    public MerchantRecipeData asProtocol() {
        return MerchantRecipeData.of(buyItem1.asProtocol(), buyItem2.asProtocol(), sellItem.asProtocol(), uses, maxUses, xp, priceMultiplier, demand, specialPrice);
    }

}
