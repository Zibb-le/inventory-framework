package org.zibble.inventoryframework.menu.inventory;

import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.openinventory.OpenInventory;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;

public class GrindStoneMenu extends Menu {

    public GrindStoneMenu() {
        super(1, 3);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.GRINDSTONE;
    }

    @Override
    public void open(@NotNull final ProtocolPlayer<?> user) {
        OpenInventory openInventory = new OpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        openInventory.sendItems(this.items());
    }
}
