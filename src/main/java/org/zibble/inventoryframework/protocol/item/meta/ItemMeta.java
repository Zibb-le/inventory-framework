package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.*;
import com.github.retrooper.packetevents.util.AdventureSerializer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.protocol.item.objects.enums.Enchantment;

import java.util.*;

public class ItemMeta {

    private static final String NAME = "Name";
    private static final String LORE = "Lore";
    private static final String HIDE_FLAGS = "HideFlags";
    private static final String UNBREAKABLE = "Unbreakable";
    private static final String REPAIR_COST = "RepairCost";

    private @Nullable Component displayName;
    private @NotNull List<Component> lore;
    private Map<Enchantment, Integer> enchantments;
    private @Range(from = 0, to = Integer.MAX_VALUE) int repairCost;
    private @NotNull EnumSet<Flag> flags;
    private boolean unBreakable;

    protected ItemMeta() {
        this.lore = new ArrayList<>();
        this.enchantments = new EnumMap<>(Enchantment.class);
        this.flags = EnumSet.noneOf(Flag.class);
        this.unBreakable = false;
    }

    @Nullable
    public Component displayName() {
        return displayName;
    }

    public void displayName(@Nullable Component displayName) {
        this.displayName = displayName;
    }

    @NotNull
    public List<Component> lore() {
        return Collections.unmodifiableList(this.lore);
    }

    public void lore(@NotNull List<Component> lore) {
        this.lore = lore;
    }

    @NotNull
    public Map<Enchantment, Integer> enchantments() {
        return enchantments;
    }

    public void addEnchant(@NotNull Enchantment enchantment, @Range(from = 0, to = Integer.MAX_VALUE) int level) {
        this.enchantments.put(enchantment, level);
    }

    public void removeEnchantment(@NotNull Enchantment enchantment) {
        this.enchantments.remove(enchantment);
    }

    public void enchantments(@NotNull Map<Enchantment, Integer> enchantments) {
        this.enchantments = new EnumMap<>(enchantments);
    }

    @Range(from = 0, to = Integer.MAX_VALUE)
    public int repairCost() {
        return repairCost;
    }

    public void repairCost(@Range(from = 0, to = Integer.MAX_VALUE) int repairCost) {
        this.repairCost = repairCost;
    }

    @NotNull
    public Set<Flag> flags() {
        return Collections.unmodifiableSet(flags);
    }

    public void addFlag(@NotNull Flag flag) {
        this.flags.add(flag);
    }

    public void removeFlag(@NotNull Flag flag) {
        this.flags.remove(flag);
    }

    public void flags(@NotNull EnumSet<Flag> flags) {
        this.flags = flags;
    }

    public boolean unbreakable() {
        return unBreakable;
    }

    public void unbreakable(boolean unBreakable) {
        this.unBreakable = unBreakable;
    }

    public void applyTo(@NotNull NBTCompound compound) {
        if (this.displayName != null || !this.lore.isEmpty()) {
            if (this.displayName != null)
                MetaUtil.applyDisplayTag(NAME, new NBTString(AdventureSerializer.asVanilla(this.displayName)), compound);
            if (!this.lore.isEmpty()) {
                NBTList<NBTString> strings = new NBTList<>(NBTType.STRING);
                for (Component component : this.lore) {
                    strings.addTag(new NBTString(AdventureSerializer.asVanilla(component)));
                }
                MetaUtil.applyDisplayTag(LORE, strings, compound);
            }
        }
        byte flags = 0x00;
        for (Flag flag : this.flags) {
            flags |= 1 << flag.ordinal();
        }
        if (flags != 0x00) compound.setTag(HIDE_FLAGS, new NBTInt(flags));
        if (this.unBreakable)
            compound.setTag(UNBREAKABLE, new NBTByte((byte) 1));
        if (this.repairCost > 0)
            compound.setTag(REPAIR_COST, new NBTInt(this.repairCost));
    }

    public enum Flag {
        HIDE_ENCHANTS,
        HIDE_ATTRIBUTES,
        HIDE_UNBREAKABLE,
        HIDE_DESTROYS,
        HIDE_PLACED_ON,
        HIDE_POTION_EFFECTS;
    }

}
