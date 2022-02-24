package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.FixedButtonMenu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;

public class LoomMenu extends FixedButtonMenu {

    public LoomMenu() {
        super(1, 4);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.LOOM;
    }

    @Override
    public boolean isSupported() {
        return InventoryFramework.framework().serverVersion().isNewerThanOrEquals(ServerVersion.V_1_14);
    }

    @Override
    protected void update(@NotNull AbstractOpenInventory openInventory) {
        openInventory.sendItems(this.getItems());
        openInventory.updateWindowData(this.properties());
    }

}
