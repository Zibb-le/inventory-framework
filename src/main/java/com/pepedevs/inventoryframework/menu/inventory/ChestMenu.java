package com.pepedevs.inventoryframework.menu.inventory;

import com.github.retrooper.packetevents.protocol.player.User;
import com.pepedevs.inventoryframework.InventoryType;
import com.pepedevs.inventoryframework.menu.Menu;
import com.pepedevs.inventoryframework.menu.NamedMenu;
import com.pepedevs.inventoryframework.menu.openinventory.OpenInventory;
import net.kyori.adventure.text.Component;

public class ChestMenu extends NamedMenu {

    public ChestMenu(int rows, Component title) {
        super(rows, 9, title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.CHEST;
    }

    @Override
    public void open(User user) {
        OpenInventory openInventory = new OpenInventory(user, this);
        Menu.OPEN_INVENTORIES.add(openInventory);
        openInventory.show();
        openInventory.sendItems(this.items);
    }
}
