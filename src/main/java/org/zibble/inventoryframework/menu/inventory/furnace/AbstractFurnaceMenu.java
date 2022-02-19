package org.zibble.inventoryframework.menu.inventory.furnace;

import com.github.retrooper.packetevents.protocol.player.User;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.NamedMenu;
import org.zibble.inventoryframework.menu.openinventory.OpenInventory;
import net.kyori.adventure.text.Component;

public abstract class AbstractFurnaceMenu extends NamedMenu {

    public AbstractFurnaceMenu(Component title) {
        super(1,3, title);
    }

    @Override
    public void open(User user) {
        OpenInventory openInventory = new OpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        openInventory.sendItems(this.getItems());
    }
}
