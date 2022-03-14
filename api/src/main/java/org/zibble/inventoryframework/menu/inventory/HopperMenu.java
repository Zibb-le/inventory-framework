package org.zibble.inventoryframework.menu.inventory;

import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.nameable.NamedMenu;

public class HopperMenu extends NamedMenu {

    public HopperMenu(@Nullable final Component title) {
        super(1, 5, title);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.HOPPER;
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
