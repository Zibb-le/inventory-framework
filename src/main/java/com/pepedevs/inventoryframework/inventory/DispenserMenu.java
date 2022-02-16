package com.pepedevs.inventoryframework.inventory;

import com.pepedevs.inventoryframework.InventoryType;
import com.pepedevs.inventoryframework.NamedMenu;
import net.kyori.adventure.text.Component;

public class DispenserMenu extends NamedMenu {

    public DispenserMenu(Component title) {
        super(3, 3, title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.DISPENSER;
    }
}
