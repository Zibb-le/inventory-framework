package org.zibble.inventoryframework.menu.inventory.furnace;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryType;

public class FurnaceMenu extends AbstractFurnaceMenu {

    public FurnaceMenu(Component title) {
        super(title);
    }

    @Override
    public @NotNull InventoryType type() {
        return InventoryType.FURNACE;
    }

}
