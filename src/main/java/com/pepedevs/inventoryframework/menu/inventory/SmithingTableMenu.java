package com.pepedevs.inventoryframework.menu.inventory;

import com.github.retrooper.packetevents.protocol.player.User;
import com.pepedevs.inventoryframework.InventoryType;
import com.pepedevs.inventoryframework.menu.Menu;
import com.pepedevs.inventoryframework.menu.openinventory.OpenInventory;

public class SmithingTableMenu extends Menu {

    public SmithingTableMenu() {
        super(1, 3);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.SMITHING;
    }

    @Override
    public void open(User user) {
        OpenInventory openInventory = new OpenInventory(user, this);
        Menu.OPEN_INVENTORIES.add(openInventory);
        openInventory.show();
        openInventory.sendItems(this.items);
    }

}
