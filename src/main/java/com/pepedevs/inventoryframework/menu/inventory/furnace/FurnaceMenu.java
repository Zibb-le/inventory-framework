package com.pepedevs.inventoryframework.menu.inventory.furnace;

import com.pepedevs.inventoryframework.InventoryType;
import net.kyori.adventure.text.Component;

public class FurnaceMenu extends AbstractFurnaceMenu {

    public FurnaceMenu(Component title) {
        super(title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.FURNACE;
    }

}
