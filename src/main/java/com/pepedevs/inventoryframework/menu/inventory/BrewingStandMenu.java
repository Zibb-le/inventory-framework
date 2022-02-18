package com.pepedevs.inventoryframework.menu.inventory;

import com.github.retrooper.packetevents.protocol.player.User;
import com.pepedevs.inventoryframework.InventoryType;
import com.pepedevs.inventoryframework.menu.Menu;
import com.pepedevs.inventoryframework.menu.NamedMenu;
import com.pepedevs.inventoryframework.menu.openinventory.OpenInventory;
import net.kyori.adventure.text.Component;

public class BrewingStandMenu extends NamedMenu {

    private int brewTime;
    private int fuel;

    public BrewingStandMenu(Component title) {
        super(1, 5, title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.BREWING_STAND;
    }

    @Override
    public void open(User user) {
        OpenInventory openInventory = new OpenInventory(user, this);
        Menu.OPEN_INVENTORIES.add(openInventory);
        openInventory.show();
        openInventory.sendItems(this.items);
    }

    public int getBrewTime() {
        return brewTime;
    }

    public void setBrewTime(int brewTime) {
        this.brewTime = brewTime;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }
}
