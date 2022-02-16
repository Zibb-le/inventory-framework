package com.pepedevs.inventoryframework.inventory;

import com.pepedevs.inventoryframework.InventoryType;
import com.pepedevs.inventoryframework.Menu;

public class CartographyTableMenu extends Menu {

    public CartographyTableMenu() {
        super(1, 3);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.CARTOGRAPHY_TABLE;
    }
}
