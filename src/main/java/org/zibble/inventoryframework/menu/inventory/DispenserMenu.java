package org.zibble.inventoryframework.menu.inventory;

import com.github.retrooper.packetevents.protocol.player.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.nameable.NamedMenu;
import org.zibble.inventoryframework.menu.openinventory.OpenInventory;
import net.kyori.adventure.text.Component;

public class DispenserMenu extends NamedMenu {

    public DispenserMenu(@Nullable final Component title) {
        super(3, 3, title);
    }

    @Override
    @NotNull
    public InventoryType getInventoryType() {
        return InventoryType.DISPENSER;
    }

    @Override
    public void open(@NotNull final User user) {
        OpenInventory openInventory = new OpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        openInventory.sendItems(this.getItems());
    }
}
