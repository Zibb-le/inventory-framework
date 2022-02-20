package org.zibble.inventoryframework.menu.inventory.furnace;

import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryType;
import net.kyori.adventure.text.Component;

public class SmokerMenu extends AbstractFurnaceMenu {

    public SmokerMenu(Component title) {
        super(title);
    }

    @Override
    public @NotNull InventoryType getInventoryType() {
        return InventoryType.SMOKER;
    }

}
