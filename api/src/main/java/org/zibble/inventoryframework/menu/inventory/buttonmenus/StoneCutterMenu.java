package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.FixedButtonMenu;

public class StoneCutterMenu extends FixedButtonMenu {

    public StoneCutterMenu() {
        super(1, 1);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.STONE_CUTTER;
    }

    @Override
    public boolean isSupported() {
        return InventoryFramework.framework().getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_14);
    }

    @Override
    protected void update(@NotNull AbstractOpenInventory openInventory) {
        openInventory.sendItems(this.getItems());
        openInventory.updateWindowData(this.properties());
    }

}
