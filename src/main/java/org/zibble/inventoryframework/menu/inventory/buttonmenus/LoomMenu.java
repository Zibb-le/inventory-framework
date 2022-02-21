package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.FixedButtonMenu;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.openinventory.FixedButtonOpenInventory;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;

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
    public void open(@NotNull final ProtocolPlayer<?> user) {
        FixedButtonOpenInventory openInventory = new FixedButtonOpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        openInventory.sendItems(this.items());
    }

}
