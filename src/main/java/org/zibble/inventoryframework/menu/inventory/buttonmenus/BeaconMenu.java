package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import org.zibble.inventoryframework.menu.ButtonMenu;
import org.zibble.inventoryframework.InventoryType;

public class BeaconMenu extends ButtonMenu {

    public BeaconMenu() {
        super(1, 1, 1, 6);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.BEACON;
    }

}
