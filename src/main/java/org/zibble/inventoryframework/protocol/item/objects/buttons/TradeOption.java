package org.zibble.inventoryframework.protocol.item.objects.buttons;

import com.github.retrooper.packetevents.protocol.recipe.data.MerchantRecipeData;
import org.zibble.inventoryframework.protocol.item.ItemStack;

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

    public ItemStack primaryBuyItem() {
        return buyItem1;
    }

    public ItemStack secondaryBuyItem() {
        return buyItem2;
    }

    public ItemStack sellItems() {
        return sellItem;
    }

    public int uses() {
        return uses;
    }

    public int maxUses() {
        return maxUses;
    }

    public int xp() {
        return xp;
    }

    public float priceMultiplier() {
        return priceMultiplier;
    }

    public int specialPrice() {
        return specialPrice;
    }

    public int demand() {
        return demand;
    }

    public MerchantRecipeData asProtocol() {
        return MerchantRecipeData.of(buyItem1.asProtocol(), buyItem2.asProtocol(), sellItem.asProtocol(), uses, maxUses, xp, priceMultiplier, demand, specialPrice);
    }

}
