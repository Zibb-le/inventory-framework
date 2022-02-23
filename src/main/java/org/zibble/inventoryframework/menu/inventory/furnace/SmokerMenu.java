package org.zibble.inventoryframework.menu.inventory.furnace;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.InventoryType;

public class SmokerMenu extends AbstractFurnaceMenu {

    public SmokerMenu(Component title) {
        super(title);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.SMOKER;
    }

    @Override
    public boolean isSupported() {
        return InventoryFramework.framework().serverVersion().isNewerThanOrEquals(ServerVersion.V_1_14);
    }

}
