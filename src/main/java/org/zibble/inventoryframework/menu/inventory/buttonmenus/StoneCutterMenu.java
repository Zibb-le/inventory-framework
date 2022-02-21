package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.FixedButtonMenu;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.openinventory.FixedButtonOpenInventory;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;

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
    public void open(@NotNull final ProtocolPlayer<?> user) {
        FixedButtonOpenInventory openInventory = new FixedButtonOpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        openInventory.sendItems(this.items());
    }

}
