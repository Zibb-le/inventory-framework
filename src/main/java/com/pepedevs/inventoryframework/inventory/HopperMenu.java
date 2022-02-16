package com.pepedevs.inventoryframework.inventory;

import com.pepedevs.inventoryframework.InventoryType;
import com.pepedevs.inventoryframework.NamedMenu;
import net.kyori.adventure.text.Component;

public class HopperMenu extends NamedMenu {

    public HopperMenu(Component title) {
        super(1, 5, title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.HOPPER;
    }

}
