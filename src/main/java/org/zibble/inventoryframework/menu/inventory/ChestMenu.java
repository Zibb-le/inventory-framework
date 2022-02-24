package org.zibble.inventoryframework.menu.inventory;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.nameable.NamedMenu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;

public class ChestMenu extends NamedMenu {

    public ChestMenu(@Range(from = 1, to = 6) final int rows, @Nullable final Component title) {
        super(rows, 9, title);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.CHEST;
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
