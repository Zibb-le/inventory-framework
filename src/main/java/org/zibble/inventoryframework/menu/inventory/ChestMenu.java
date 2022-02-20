package org.zibble.inventoryframework.menu.inventory;

import com.github.retrooper.packetevents.protocol.player.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.nameable.NamedMenu;
import org.zibble.inventoryframework.menu.openinventory.OpenInventory;
import net.kyori.adventure.text.Component;

public class ChestMenu extends NamedMenu {

    public ChestMenu(@Range(from = 1, to = 6) final int rows, @Nullable final Component title) {
        super(rows, 9, title);
    }

    @Override
    @NotNull
    public InventoryType getInventoryType() {
        return InventoryType.CHEST;
    }

    @Override
    public void open(@NotNull final User user) {
        OpenInventory openInventory = new OpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        openInventory.sendItems(this.getItems());
    }

}
