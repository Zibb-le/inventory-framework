package org.zibble.inventoryframework.menu.inventory;

import com.github.retrooper.packetevents.protocol.player.User;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.NamedMenu;
import org.zibble.inventoryframework.menu.openinventory.OpenInventory;
import net.kyori.adventure.text.Component;

public class HopperMenu extends NamedMenu {

    public HopperMenu(Component title) {
        super(1, 5, title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.HOPPER;
    }

    @Override
    public void open(User user) {
        OpenInventory openInventory = new OpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        openInventory.sendItems(this.getItems());
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void updateSlot(int slot, User user) {

    }

}
