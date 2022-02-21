package org.zibble.inventoryframework.menu.inventory.furnace;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryType;

public class SmokerMenu extends AbstractFurnaceMenu {

    public SmokerMenu(Component title) {
        super(title);
    }

    @Override
    public @NotNull InventoryType type() {
        return InventoryType.SMOKER;
    }

}
