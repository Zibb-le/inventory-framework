package com.pepedevs.inventoryframework.protocol;

import net.kyori.adventure.text.Component;

import java.util.List;

public class ItemStack {

    private Material material;
    private int amount;

    private Component displayName;
    private List<Component> lore;

    public ItemStack(Material type) {
        this(type, 1);
    }

    public ItemStack(Material type, int amount) {
        this.material = type;
        this.amount = amount;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Component getDisplayName() {
        return displayName;
    }

    public void setDisplayName(Component displayName) {
        this.displayName = displayName;
    }

    public List<Component> getLore() {
        return lore;
    }

    public void setLore(List<Component> lore) {
        this.lore = lore;
    }

}
