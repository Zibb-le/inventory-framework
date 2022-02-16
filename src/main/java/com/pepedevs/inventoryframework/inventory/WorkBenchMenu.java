package com.pepedevs.inventoryframework.inventory;

import com.pepedevs.inventoryframework.InventoryType;
import com.pepedevs.inventoryframework.Menu;

public class WorkBenchMenu extends Menu {

    public WorkBenchMenu() {
        super(3, 3);
    }

    @Override
    public InventoryType getInventoryType() {
        return null;
    }
}
