package org.zibble.inventoryframework.menu.inventory;

import com.github.retrooper.packetevents.protocol.player.User;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.NamedMenu;
import org.zibble.inventoryframework.menu.openinventory.OpenInventory;
import net.kyori.adventure.text.Component;

public class DispenserMenu extends NamedMenu {

    public DispenserMenu(Component title) {
        super(3, 3, title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.DISPENSER;
    }

    @Override
    public void open(User user) {
        OpenInventory openInventory = new OpenInventory(user, this);
        Menu.OPEN_INVENTORIES.add(openInventory);
        openInventory.show();
        openInventory.sendItems(this.getItems());
    }
}
