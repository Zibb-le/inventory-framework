package org.zibble.inventoryframework.spigot;

import com.github.retrooper.packetevents.protocol.item.ItemStack;
import io.github.retrooper.packetevents.util.SpigotDataHelper;
import org.zibble.inventoryframework.protocol.Item;

public class SpigotItem implements Item {

    private final org.bukkit.inventory.ItemStack item;

    public SpigotItem(org.bukkit.inventory.ItemStack item) {
        this.item = item;
    }

    public org.bukkit.inventory.ItemStack getItem() {
        return this.item;
    }

    @Override
    public ItemStack asProtocol() {
        return SpigotDataHelper.fromBukkitItemStack(this.item);
    }

}
