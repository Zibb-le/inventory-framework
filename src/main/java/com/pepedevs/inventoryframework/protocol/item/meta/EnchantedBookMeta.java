package com.pepedevs.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.pepedevs.inventoryframework.protocol.item.objects.enums.Enchantment;

import java.util.EnumMap;
import java.util.Map;

public class EnchantedBookMeta extends ItemMeta {

    private static final String STORED_ENCHANTMENTS = "StoredEnchantments";

    private Map<Enchantment, Integer> bookEnchants;

    protected EnchantedBookMeta() {
        super();
        this.bookEnchants = new EnumMap<>(Enchantment.class);
    }

    public Map<Enchantment, Integer> getBookEnchants() {
        return bookEnchants;
    }

    public void addBookEnchant(Enchantment enchantment, int level) {
        this.bookEnchants.put(enchantment, level);
    }

    public void removeBookEnchant(Enchantment enchantment) {
        this.bookEnchants.remove(enchantment);
    }

    public void setBookEnchants(Map<Enchantment, Integer> bookEnchants) {
        this.bookEnchants = new EnumMap<>(bookEnchants);
    }

    @Override
    public void applyTo(NBTCompound compound) {
        super.applyTo(compound);
        MetaUtil.applyEnchants(bookEnchants, compound);
    }
}
