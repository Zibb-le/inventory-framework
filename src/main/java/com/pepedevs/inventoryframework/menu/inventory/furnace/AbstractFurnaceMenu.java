package com.pepedevs.inventoryframework.menu.inventory.furnace;

import com.github.retrooper.packetevents.protocol.player.User;
import com.pepedevs.inventoryframework.menu.Menu;
import com.pepedevs.inventoryframework.menu.NamedMenu;
import com.pepedevs.inventoryframework.menu.openinventory.OpenInventory;
import net.kyori.adventure.text.Component;

public abstract class AbstractFurnaceMenu extends NamedMenu {

    public AbstractFurnaceMenu(Component title) {
        super(1,3, title);
    }

    @Override
    public void open(User user) {
        OpenInventory openInventory = new OpenInventory(user, this);
        Menu.OPEN_INVENTORIES.add(openInventory);
        openInventory.show();
        openInventory.sendItems(this.items);
    }
}
