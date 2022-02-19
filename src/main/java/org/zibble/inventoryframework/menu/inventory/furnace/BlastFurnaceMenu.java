package org.zibble.inventoryframework.menu.inventory.furnace;

import org.zibble.inventoryframework.InventoryType;
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
