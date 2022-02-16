package com.pepedevs.inventoryframework.inventory;

import com.pepedevs.inventoryframework.InventoryType;
import com.pepedevs.inventoryframework.Menu;

public class GrindStoneMenu extends Menu {

    public GrindStoneMenu() {
        super(1, 3);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.GRINDSTONE;
    }
}
