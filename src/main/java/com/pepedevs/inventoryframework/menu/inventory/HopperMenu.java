package com.pepedevs.inventoryframework.menu.inventory;

import com.github.retrooper.packetevents.protocol.player.User;
import com.pepedevs.inventoryframework.InventoryType;
import com.pepedevs.inventoryframework.menu.Menu;
import com.pepedevs.inventoryframework.menu.NamedMenu;
import com.pepedevs.inventoryframework.menu.openinventory.OpenInventory;
import net.kyori.adventure.text.Component;

public class HopperMenu extends NamedMenu {

    public HopperMenu(Component title) {
        super(1, 5, title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.HOPPER;
    }

    @Override
    public void open(User user) {
        OpenInventory openInventory = new OpenInventory(user, this);
        Menu.OPEN_INVENTORIES.add(openInventory);
        openInventory.show();
        openInventory.sendItems(this.items);
    }
}
