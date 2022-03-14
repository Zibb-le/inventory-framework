package org.zibble.inventoryframework.menu.inventory.furnace;

import org.zibble.inventoryframework.InventoryType;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class FurnaceMenu extends AbstractFurnaceMenu {

    public FurnaceMenu(Component title) {
        super(title);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.FURNACE;
    }

    @Override
    public boolean isSupported() {
        return true;
    }

}
