package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.protocol.item.objects.enums.Enchantment;

import java.util.EnumMap;
import java.util.Map;

public class EnchantedBookMeta extends ItemMeta {

    private static final String STORED_ENCHANTMENTS = "StoredEnchantments";

    private Map<Enchantment, Integer> bookEnchants;

    protected EnchantedBookMeta() {
        this.bookEnchants = new EnumMap<>(Enchantment.class);
    }

    public Map<Enchantment, Integer> bookEnchants() {
        return bookEnchants;
    }

    public void addBookEnchant(Enchantment enchantment, int level) {
        this.bookEnchants.put(enchantment, level);
    }

    public void removeBookEnchant(Enchantment enchantment) {
        this.bookEnchants.remove(enchantment);
    }

    public void bookEnchants(Map<Enchantment, Integer> bookEnchants) {
        this.bookEnchants = new EnumMap<>(bookEnchants);
    }

    @Override
    public void applyTo(@NotNull NBTCompound compound) {
        super.applyTo(compound);
        MetaUtil.applyEnchants(bookEnchants, compound);
    }
}
