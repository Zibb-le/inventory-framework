package org.zibble.inventoryframework.spigot;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.protocol.item.enchantment.type.EnchantmentType;
import com.github.retrooper.packetevents.protocol.item.enchantment.type.EnchantmentTypes;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.protocol.Enchant;

public class SpigotEnchant implements Enchant {

    private final Enchantment enchantment;

    public SpigotEnchant(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    public Enchantment getEnchantment() {
        return this.enchantment;
    }

    @Override
    public int getId() {
        return this.asProtocol().getId();
    }

    @Override
    public @NotNull EnchantmentType asProtocol() {
        if (InventoryFramework.framework().getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_13)) {
            return EnchantmentTypes.getByName(this.enchantment.getKey().toString());
        } else {
            return EnchantmentTypes.getByName(this.enchantment.getName());
        }
    }

}
