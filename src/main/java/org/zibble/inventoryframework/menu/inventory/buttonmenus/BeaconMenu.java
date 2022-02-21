package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.ButtonMenu;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;

public class BeaconMenu extends ButtonMenu {

    public BeaconMenu() {
        super(1, 1, 1, 6);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.BEACON;
    }

    @Override
    public void open(@NotNull ProtocolPlayer<?> user) {

    }

}
