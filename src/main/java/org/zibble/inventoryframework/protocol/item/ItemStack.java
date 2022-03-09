package org.zibble.inventoryframework.protocol.item;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.*;
import org.zibble.inventoryframework.protocol.Material;
import org.zibble.inventoryframework.protocol.Materials;
import org.zibble.inventoryframework.protocol.item.meta.ItemMeta;
import org.zibble.inventoryframework.protocol.item.meta.MetaUtil;

import java.util.List;

/**
 * Vanilla ItemStack representation for use in Inventory Framework.
 */
public class ItemStack {

    /**
     * @return An ItemStack of {@link Materials#AIR} type.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull ItemStack empty() {
        return new ItemStack(Materials.AIR);
    }

    private @NotNull final Material material;
    private @Range(from = 0, to = 64) int amount;
    /**
     * The legacy data to be used for older versions of Minecraft.
     */
    private int legacyData = -1;

    private @NotNull ItemMeta itemMeta;

    /**
     * Creates an ItemStack from a {@link Material} with amount set to 1.
     * @param type The {@link Material} to create the ItemStack from.
     */
    public ItemStack(@NotNull Material type) {
        this(type, 1);
    }

    /**
     * Creates an ItemStack from a {@link Material} with the provided amount.
     * @param type The {@link Material} to create the ItemStack from.
     * @param amount The amount of the items in the stack.
     */
    public ItemStack(@NotNull Material type, @Range(from = 1, to = 64) int amount) {
        this.material = type;
        this.amount = amount;
        this.itemMeta = MetaUtil.getMeta(type);
    }

    /**
     * @return The {@link Material} of this ItemStack.
     */
    @NotNull
    public Material getType() {
        return material;
    }

    /**
     * @return The amount of items in this ItemStack.
     */
    public @Range(from = 1, to = 64) int getAmount() {
        return amount;
    }

    /**
     * @param amount The amount of items to set the stack to.
     */
    public void setAmount(@Range(from = 1, to = 64) int amount) {
        this.amount = amount;
    }

    /**
     * @return The display name of this ItemStack.
     */
    @Nullable
    public Component getDisplayName() {
        return this.itemMeta.getDisplayName();
    }

    /**
     * @param displayName The display name to set for this ItemStack.
     */
    public void setDisplayName(@Nullable Component displayName) {
        this.itemMeta.setDisplayName(displayName);
    }

    /**
     * @return The lore of this ItemStack.
     */
    @NotNull
    public List<@Nullable Component> getLore() {
        return this.itemMeta.getLore();
    }

    /**
     * @param lore The lore to set for this ItemStack.
     */
    public void setLore(@NotNull List<@Nullable Component> lore) {
        this.itemMeta.setLore(lore);
    }

    /**
     * @return The {@link ItemMeta} of this ItemStack.
     */
    @NotNull
    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    /**
     * @param itemMeta The {@link ItemMeta} to set for this ItemStack.
     */
    @ApiStatus.Experimental
    public void setItemMeta(@NotNull ItemMeta itemMeta) {
        if (!this.itemMeta.getClass().isAssignableFrom(itemMeta.getClass()))
            throw new IllegalArgumentException("The provided ItemMeta is not compatible with this ItemStack.");
        this.itemMeta = itemMeta;
    }

    /**
     * @return The legacy data of this ItemStack.
     */
    public int getLegacyData() {
        return legacyData;
    }

    @ApiStatus.Experimental
    public void setLegacyData(int data) {
        this.legacyData = data;
    }

    @ApiStatus.Internal
    @NotNull
    public com.github.retrooper.packetevents.protocol.item.ItemStack asProtocol() {
        NBTCompound nbt = new NBTCompound();
        this.getItemMeta().applyTo(nbt);
        return com.github.retrooper.packetevents.protocol.item.ItemStack.builder()
                .type(this.material.asProtocol())
                .amount(this.amount)
                .nbt(nbt)
                .legacyData(this.legacyData == -1 ? this.material.legacyData() : this.legacyData)
                .build();
    }

    public ItemStack.Builder toBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private final Material material;
        private int amount;
        private ItemMeta meta;
        private int legacyData;

        public Builder(@NotNull Material material) {
            this(new ItemStack(material));
        }

        public Builder(ItemStack itemStack) {
            this.material = itemStack.material;
            this.amount = itemStack.amount;
            this.meta = itemStack.itemMeta;
            this.legacyData = itemStack.legacyData;
        }

        public Builder amount(@Range(from = 1, to = 64) int amount) {
            this.amount = amount;
            return this;
        }

        @ApiStatus.Experimental
        public Builder meta(@NotNull ItemMeta meta) {
            if (!this.meta.getClass().isAssignableFrom(meta.getClass()))
                throw new IllegalArgumentException("The provided ItemMeta is not compatible with this ItemStack.");
            this.meta = meta;
            return this;
        }

        public Builder meta(@NotNull ItemMeta.Builder metaBuilder) {
            ItemMeta meta = metaBuilder.build();
            if (!this.meta.getClass().isAssignableFrom(meta.getClass()))
                throw new IllegalArgumentException("The provided ItemMeta is not compatible with this ItemStack.");
            this.meta = meta;
            return this;
        }

        public Builder withDisplayName(@Nullable Component displayName) {
            this.meta.setDisplayName(displayName);
            return this;
        }

        public Builder withLore(@NotNull List<@NotNull Component> lore) {
            this.meta.setLore(lore);
            return this;
        }

        public Builder legacyData(int legacyData) {
            this.legacyData = legacyData;
            return this;
        }

        public ItemStack build() {
            ItemStack itemStack = new ItemStack(this.material, this.amount);
            itemStack.itemMeta = this.meta;
            itemStack.legacyData = this.legacyData;
            return itemStack;
        }

    }
}
