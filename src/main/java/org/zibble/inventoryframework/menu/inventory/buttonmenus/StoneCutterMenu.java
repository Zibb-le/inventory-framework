package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.FixedButtonMenu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;

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
        return InventoryFramework.framework().serverVersion().isNewerThanOrEquals(ServerVersion.V_1_14);
    }

    @Override
    protected void update(@NotNull AbstractOpenInventory openInventory) {
        openInventory.sendItems(this.items());
        openInventory.updateWindowData(this.properties());
    }

}
