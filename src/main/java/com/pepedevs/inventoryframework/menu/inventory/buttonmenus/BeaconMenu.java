package com.pepedevs.inventoryframework.menu.inventory.buttonmenus;

import com.pepedevs.inventoryframework.menu.ButtonMenu;
import com.pepedevs.inventoryframework.InventoryType;

public class BeaconMenu extends ButtonMenu {

    public BeaconMenu() {
        super(1, 1, 1, 6);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.BEACON;
    }
}
