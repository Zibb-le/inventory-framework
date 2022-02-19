package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import org.zibble.inventoryframework.menu.ButtonMenu;
import org.zibble.inventoryframework.InventoryType;

public class LoomMenu extends ButtonMenu {

    public LoomMenu(int buttonRows) {
        super(1, 4, buttonRows, 4);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.LOOM;
    }

}
