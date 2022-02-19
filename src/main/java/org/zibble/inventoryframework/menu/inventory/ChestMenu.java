package org.zibble.inventoryframework.menu.inventory;

import com.github.retrooper.packetevents.protocol.player.User;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.NamedMenu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.zibble.inventoryframework.menu.openinventory.OpenInventory;
import net.kyori.adventure.text.Component;
import org.zibble.inventoryframework.protocol.item.ItemStack;

public class ChestMenu extends NamedMenu {

    public ChestMenu(int rows, Component title) {
        super(rows, 9, title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.CHEST;
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
        AbstractOpenInventory openInventory = Menu.OPEN_INVENTORIES.get(user);
        if (openInventory != null) {
            openInventory.sendItems(this.getItems());
        }
    }

    @Override
    public void updateSlot(int slot, User user) {
        AbstractOpenInventory openInventory = Menu.OPEN_INVENTORIES.get(user);
        if (openInventory != null) {
            MenuItem<ItemStack> item = this.getItem(slot);
            openInventory.setSlot(slot, item == null ? null : item.getContent());
        }
    }

}
