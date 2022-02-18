package com.pepedevs.inventoryframework.protocol.item.meta;

import com.pepedevs.inventoryframework.protocol.item.objects.Enchantment;
import net.kyori.adventure.text.Component;

import java.util.*;

public class ItemMeta {

    private Component displayName;
    private List<Component> lore;
    private Set<Enchantment> enchantments;
    private int repairCost;
    private EnumSet<Flag> flags;

    public ItemMeta() {
        this.lore = new ArrayList<>();
        this.enchantments = new HashSet<>();
        this.flags = EnumSet.noneOf(Flag.class);
    }

    public Component getDisplayName() {
        return displayName;
    }

    public void setDisplayName(Component displayName) {
        this.displayName = displayName;
    }

    public List<Component> getLore() {
        return Collections.unmodifiableList(this.lore);
    }

    public void setLore(List<Component> lore) {
        this.lore = lore;
    }

    public Set<Enchantment> getEnchantments() {
        return enchantments;
    }

    public void addEnchant(Enchantment enchantment) {
        this.enchantments.add(enchantment);
    }

    public void removeEnchantment(Enchantment enchantment) {
        this.enchantments.remove(enchantment);
    }
    public void setEnchantments(Collection<Enchantment> enchantments) {
        this.enchantments = new HashSet<>(enchantments);
    }

    public int getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(int repairCost) {
        this.repairCost = repairCost;
    }

    public void setEnchantments(Set<Enchantment> enchantments) {
        this.enchantments = enchantments;
    }

    public Set<Flag> getFlags() {
        return Collections.unmodifiableSet(flags);
    }

    public void addFlag(Flag flag) {
        this.flags.add(flag);
    }

    public void removeFlag(Flag flag) {
        this.flags.remove(flag);
    }

    public void setFlags(EnumSet<Flag> flags) {
        this.flags = flags;
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
