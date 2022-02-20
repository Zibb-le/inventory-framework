package org.zibble.inventoryframework.menu.inventory;

import com.github.retrooper.packetevents.protocol.player.User;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.openinventory.OpenInventory;

public class WorkBenchMenu extends Menu {

    public WorkBenchMenu() {
        super(3, 3);
    }

    @Override
    @NotNull
    public InventoryType getInventoryType() {
        return InventoryType.WORKBENCH;
    }

    @Override
    public void open(@NotNull final User user) {
        OpenInventory openInventory = new OpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        openInventory.sendItems(this.getItems());
    }
}
