package com.pepedevs.inventoryframework.menu.inventory.buttonmenus;

import com.pepedevs.inventoryframework.menu.ButtonMenu;
import com.pepedevs.inventoryframework.InventoryType;

public class LoomMenu extends ButtonMenu {

    public LoomMenu(int buttonRows) {
        super(1, 4, buttonRows, 4);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.LOOM;
    }

}
