package org.zibble.inventoryframework.protocol.item;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.protocol.Material;
import org.zibble.inventoryframework.protocol.Materials;
import org.zibble.inventoryframework.protocol.item.meta.ItemMeta;
import org.zibble.inventoryframework.protocol.item.meta.MetaUtil;

import java.util.List;

public class ItemStack {

    public static ItemStack empty() {
        return new ItemStack(Materials.AIR);
    }

    private @NotNull Material material;
    private @Range(from = 0, to = 64) int amount;
    private int data = -1;

    private @NotNull ItemMeta itemMeta;

    public ItemStack(@NotNull Material type) {
        this(type, 1);
    }

    public ItemStack(@NotNull Material type, @Range(from = 1, to = 64) int amount) {
        this.material = type;
        this.amount = amount;
        this.itemMeta = MetaUtil.getMeta(type);
    }

    @NotNull
    public Material type() {
        return material;
    }

    public void type(@NotNull Material material) {
        this.material = material;
    }

    public @Range(from = 1, to = 64) int amount() {
        return amount;
    }

    public void amount(@Range(from = 1, to = 64) int amount) {
        this.amount = amount;
    }

    @Nullable
    public Component displayName() {
        return this.itemMeta.displayName();
    }

    public void displayName(@Nullable Component displayName) {
        this.itemMeta.displayName(displayName);
    }

    @NotNull
    public List<Component> lore() {
        return this.itemMeta.lore();
    }

    public void lore(@NotNull List<Component> lore) {
        this.itemMeta.lore(lore);
    }

    @NotNull
    public ItemMeta meta() {
        return itemMeta;
    }

    public void meta(@NotNull ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
    }

    public int data() {
        return data;
    }

    public void data(int data) {
        this.data = data;
    }

    @NotNull
    public com.github.retrooper.packetevents.protocol.item.ItemStack asProtocol() {
        NBTCompound nbt = new NBTCompound();
        this.meta().applyTo(nbt);
        return com.github.retrooper.packetevents.protocol.item.ItemStack.builder()
                .type(this.material.asProtocol())
                .amount(this.amount)
                .nbt(nbt)
                .legacyData(this.data == -1 ? this.material.legacyData() : this.data)
                .build();
    }
}
