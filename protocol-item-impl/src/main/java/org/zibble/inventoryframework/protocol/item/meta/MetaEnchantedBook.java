package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import org.zibble.inventoryframework.protocol.item.objects.enums.EnumEnchant;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class MetaEnchantedBook extends MetaItem {

    private static final String STORED_ENCHANTMENTS = "StoredEnchantments";

    private Map<EnumEnchant, Integer> bookEnchants;

    protected MetaEnchantedBook() {
        this.bookEnchants = new EnumMap<>(EnumEnchant.class);
    }

    public Map<EnumEnchant, Integer> getBookEnchants() {
        return Collections.unmodifiableMap(this.bookEnchants);
    }

    public void addBookEnchant(EnumEnchant enchantment, int level) {
        this.bookEnchants.put(enchantment, level);
    }

    public void removeBookEnchant(EnumEnchant enchantment) {
        this.bookEnchants.remove(enchantment);
    }

    public void setBookEnchants(Map<EnumEnchant, Integer> bookEnchants) {
        this.bookEnchants = new EnumMap<>(bookEnchants);
    }

    @ApiStatus.Internal
    @Override
    public void applyTo(@NotNull NBTCompound compound) {
        super.applyTo(compound);
        MetaUtil.applyEnchants(bookEnchants, compound);
    }
}
