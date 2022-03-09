package org.zibble.inventoryframework.menu.inventory.furnace;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.InventoryType;

public class BlastFurnaceMenu extends AbstractFurnaceMenu {

    public BlastFurnaceMenu(Component title) {
        super(title);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.BLAST_FURNACE;
    }

    @Override
    public boolean isSupported() {
        return InventoryFramework.framework().getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_14);
    }

}
