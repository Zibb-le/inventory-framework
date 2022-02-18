package com.pepedevs.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.*;
import com.github.retrooper.packetevents.util.AdventureSerializer;
import com.pepedevs.inventoryframework.protocol.item.objects.enums.Enchantment;
import net.kyori.adventure.text.Component;

import java.util.*;

public class ItemMeta {

    protected static final String DISPLAY = "display";
    protected static final String NAME = "Name";
    protected static final String LORE = "Lore";
    protected static final String HIDE_FLAGS = "HideFlags";
    protected static final String ENCHANTMENTS = "ench";
    protected static final String ENCHANT_ID = "id";
    protected static final String ENCHANT_LVL = "lvl";
    protected static final String UNBREAKABLE = "Unbreakable";
    protected static final String REPAIR_COST = "RepairCost";

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

    public Map<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }

    public void addEnchant(Enchantment enchantment, int level) {
        this.enchantments.put(enchantment, level);
    }

    public void removeEnchantment(Enchantment enchantment) {
        this.enchantments.remove(enchantment);
    }
    public void setEnchantments(Map<Enchantment, Integer> enchantments) {
        this.enchantments = new EnumMap<>(enchantments);
    }

    public int getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(int repairCost) {
        this.repairCost = repairCost;
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

    public boolean isUnBreakable() {
        return unBreakable;
    }

    public void setUnBreakable(boolean unBreakable) {
        this.unBreakable = unBreakable;
    }

    public void applyTo(NBTCompound compound) {
        if (this.displayName != null || this.lore != null) {
            NBTCompound display = compound.getCompoundTagOrNull(DISPLAY);
            if (display == null) {
                compound.setTag(DISPLAY, display = new NBTCompound());
            }
            if (this.displayName != null)
                display.setTag(NAME, new NBTString(AdventureSerializer.asVanilla(this.displayName)));
            if (this.lore != null) {
                List<NBTString> strings = new ArrayList<>(this.lore.size());
                for (Component component : this.lore) {
                    strings.add(new NBTString(AdventureSerializer.asVanilla(component)));
                }
                display.setTag(LORE, new NBTList<>(NBTType.STRING, strings));
            }
        }
        byte flags = 0x00;
        for (Flag flag : this.flags) {
            flags |= 1 << flag.ordinal();
        }
        if (flags != 0x00) compound.setTag(HIDE_FLAGS, new NBTInt(flags));
        if (enchantments.size() > 0) {
            NBTList<NBTCompound> enchants = new NBTList<>(NBTType.COMPOUND);
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                NBTCompound enchant = new NBTCompound();
                enchant.setTag(ENCHANT_ID, new NBTShort((short) entry.getKey().getID()));
                enchant.setTag(ENCHANT_LVL, new NBTShort(entry.getValue().shortValue()));
                enchants.addTag((short) entry.getKey().getPacketType().getId(), enchant);
            }
            compound.setTag(ENCHANTMENTS, enchants);
        }
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
