package org.zibble.inventoryframework.protocol.item;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import org.zibble.inventoryframework.protocol.Material;
import org.zibble.inventoryframework.protocol.item.meta.ItemMeta;
import org.zibble.inventoryframework.protocol.item.meta.MetaUtil;
import net.kyori.adventure.text.Component;

import java.util.List;

public class ItemStack {

    private Material material;
    private int amount;
    private int data;

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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public com.github.retrooper.packetevents.protocol.item.ItemStack asProtocol() {
        NBTCompound nbt = new NBTCompound();
        if (this.itemMeta != null) {
            this.itemMeta.applyTo(nbt);
        }
        return com.github.retrooper.packetevents.protocol.item.ItemStack.builder()
                .type(this.material.getItemType())
                .amount(this.amount)
                .nbt(nbt)
                .legacyData(this.data)
                .build();
    }
}
