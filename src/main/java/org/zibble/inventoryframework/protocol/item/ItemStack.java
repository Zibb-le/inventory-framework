package org.zibble.inventoryframework.protocol.item;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import net.kyori.adventure.text.Component;
import org.zibble.inventoryframework.protocol.Material;
import org.zibble.inventoryframework.protocol.item.meta.ItemMeta;
import org.zibble.inventoryframework.protocol.item.meta.MetaUtil;

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

    public Material type() {
        return material;
    }

    public void type(Material material) {
        this.material = material;
    }

    public int amount() {
        return amount;
    }

    public void amount(int amount) {
        this.amount = amount;
    }

    public Component displayName() {
        return this.itemMeta.displayName();
    }

    public void displayName(Component displayName) {
        this.itemMeta.displayName(displayName);
    }

    public List<Component> lore() {
        return this.itemMeta.lore();
    }

    public void lore(List<Component> lore) {
        this.itemMeta.lore(lore);
    }

    public ItemMeta meta() {
        return itemMeta;
    }

    public void meta(ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
    }

    public int data() {
        return data;
    }

    public void data(int data) {
        this.data = data;
    }

    public com.github.retrooper.packetevents.protocol.item.ItemStack asProtocol() {
        NBTCompound nbt = new NBTCompound();
        if (this.itemMeta != null) {
            this.itemMeta.applyTo(nbt);
        }
        return com.github.retrooper.packetevents.protocol.item.ItemStack.builder()
                .type(this.material.asProtocol())
                .amount(this.amount)
                .nbt(nbt)
                .legacyData(this.data)
                .build();
    }
}
