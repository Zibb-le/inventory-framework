package com.pepedevs.inventoryframework.inventory.furnace;

import com.github.retrooper.packetevents.protocol.player.User;
import com.pepedevs.inventoryframework.Menu;
import com.pepedevs.inventoryframework.NamedMenu;
import com.pepedevs.inventoryframework.openinventory.OpenInventory;
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
