package com.pepedevs.inventoryframework.inventory;

import com.pepedevs.inventoryframework.InventoryType;
import com.pepedevs.inventoryframework.NamedMenu;
import net.kyori.adventure.text.Component;

public class BrewingStandMenu extends NamedMenu {

    public BrewingStandMenu(Component title) {
        super(1, 5, title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.BREWING_STAND;
    }
}
