package org.zibble.inventoryframework.menu.inventory;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;

public class SmithingTableMenu extends Menu {

    public SmithingTableMenu() {
        super(1, 3);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.SMITHING;
    }

    @Override
    public boolean isSupported() {
        return InventoryFramework.framework().getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_14);
    }

    @Override
    protected void update(@NotNull AbstractOpenInventory openInventory) {
        openInventory.sendItems(this.getItems());
    }

}
