package com.pepedevs.inventoryframework.menu.inventory;

import com.github.retrooper.packetevents.protocol.player.User;
import com.pepedevs.inventoryframework.InventoryType;
import com.pepedevs.inventoryframework.menu.Menu;

public class CartographyTableMenu extends Menu {

    public CartographyTableMenu() {
        super(1, 3);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.CARTOGRAPHY_TABLE;
    }

    @Override
    public void open(User user) {

    }


}
