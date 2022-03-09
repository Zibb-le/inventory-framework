package org.zibble.inventoryframework.menu.inventory;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.nameable.NamedMenu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;

public class DispenserMenu extends NamedMenu {

    public DispenserMenu(@Nullable final Component title) {
        super(3, 3, title);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.DISPENSER;
    }

    @Override
    public boolean isSupported() {
        return true;
    }

    @Override
    protected void update(@NotNull AbstractOpenInventory openInventory) {
        openInventory.sendItems(this.getItems());
    }

}
