package com.pepedevs.inventoryframework.protocol.item;

import com.pepedevs.inventoryframework.protocol.Material;
import com.pepedevs.inventoryframework.protocol.item.meta.ItemMeta;
import com.pepedevs.inventoryframework.protocol.item.meta.MetaUtil;
import net.kyori.adventure.text.Component;

import java.util.List;

public class ItemStack {

    private Material material;
    private int amount;

    private ItemMeta itemMeta;

    public ItemStack(Material type) {
        this(type, 1);
    }

    public ItemStack(Material type, int amount) {
        this.material = type;
        this.amount = amount;
        this.itemMeta = MetaUtil.getMeta(type);
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
        return this.itemMeta.getDisplayName();
    }

    public void setDisplayName(Component displayName) {
        this.itemMeta.setDisplayName(displayName);
    }

    public List<Component> getLore() {
        return this.itemMeta.getLore();
    }

    public void setLore(List<Component> lore) {
        this.itemMeta.setLore(lore);
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    public void setItemMeta(ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
    }
}
