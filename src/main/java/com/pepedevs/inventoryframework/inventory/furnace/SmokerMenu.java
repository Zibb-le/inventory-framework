package com.pepedevs.inventoryframework.inventory.furnace;

import com.pepedevs.inventoryframework.InventoryType;
import net.kyori.adventure.text.Component;

public class SmokerMenu extends AbstractFurnaceMenu {

    public SmokerMenu(Component title) {
        super(title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.SMOKER;
    }

}
