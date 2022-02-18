package com.pepedevs.inventoryframework.menu.inventory.furnace;

import com.pepedevs.inventoryframework.InventoryType;
import net.kyori.adventure.text.Component;

public class BlastFurnaceMenu extends AbstractFurnaceMenu {

    public BlastFurnaceMenu(Component title) {
        super(title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.BLAST_FURNACE;
    }
}
