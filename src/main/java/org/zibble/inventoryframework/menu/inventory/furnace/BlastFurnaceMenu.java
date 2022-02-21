package org.zibble.inventoryframework.menu.inventory.furnace;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryType;

public class BlastFurnaceMenu extends AbstractFurnaceMenu {

    public BlastFurnaceMenu(Component title) {
        super(title);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.BLAST_FURNACE;
    }
}
