package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.*;
import com.github.retrooper.packetevents.util.AdventureSerializer;
import net.kyori.adventure.text.Component;
import org.zibble.inventoryframework.protocol.item.objects.enums.Enchantment;

import java.util.*;

public class ItemMeta {

    private static final String NAME = "Name";
    private static final String LORE = "Lore";
    private static final String HIDE_FLAGS = "HideFlags";
    private static final String UNBREAKABLE = "Unbreakable";
    private static final String REPAIR_COST = "RepairCost";

    private Component displayName;
    private List<Component> lore;
    private Map<Enchantment, Integer> enchantments;
    private int repairCost;
    private EnumSet<Flag> flags;
    private boolean unBreakable;

    protected ItemMeta() {
        this.lore = new ArrayList<>();
        this.enchantments = new EnumMap<>(Enchantment.class);
        this.flags = EnumSet.noneOf(Flag.class);
        this.unBreakable = false;
    }

    public Component displayName() {
        return displayName;
    }

    public void displayName(Component displayName) {
        this.displayName = displayName;
    }

    public List<Component> lore() {
        return Collections.unmodifiableList(this.lore);
    }

    public void lore(List<Component> lore) {
        this.lore = lore;
    }

    public Map<Enchantment, Integer> enchantments() {
        return enchantments;
    }

    public void addEnchant(Enchantment enchantment, int level) {
        this.enchantments.put(enchantment, level);
    }

    public void removeEnchantment(Enchantment enchantment) {
        this.enchantments.remove(enchantment);
    }

    public void enchantments(Map<Enchantment, Integer> enchantments) {
        this.enchantments = new EnumMap<>(enchantments);
    }

    public int repairCost() {
        return repairCost;
    }

    public void repairCost(int repairCost) {
        this.repairCost = repairCost;
    }

    public Set<Flag> flags() {
        return Collections.unmodifiableSet(flags);
    }

    public void addFlag(Flag flag) {
        this.flags.add(flag);
    }

    public void removeFlag(Flag flag) {
        this.flags.remove(flag);
    }

    public void flags(EnumSet<Flag> flags) {
        this.flags = flags;
    }

    public boolean unbreakable() {
        return unBreakable;
    }

    public void unbreakable(boolean unBreakable) {
        this.unBreakable = unBreakable;
    }

    public void applyTo(NBTCompound compound) {
        if (this.displayName != null || this.lore != null) {
            if (this.displayName != null)
                MetaUtil.applyDisplayTag(NAME, new NBTString(AdventureSerializer.asVanilla(this.displayName)), compound);
            if (this.lore != null) {
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
