package org.zibble.inventoryframework.menu.inventory;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.nameable.NamedMenu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.zibble.inventoryframework.menu.openinventory.OpenInventory;
import org.zibble.inventoryframework.menu.property.DataPropertyHolder;
import org.zibble.inventoryframework.menu.property.PropertyPair;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;

public class BrewingStandMenu extends NamedMenu implements DataPropertyHolder {

    private @Range(from = 0, to = 20) int brewTime;
    private @Range(from = 0, to = 400) int fuel;

    public BrewingStandMenu(@Nullable final Component title) {
        super(1, 5, title);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.BREWING_STAND;
    }

    @Override
    public void open(@NotNull final ProtocolPlayer<?> user) {
        OpenInventory openInventory = new OpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        openInventory.sendItems(this.items());
    }

    public @Range(from = 0, to = 20) int brewTime() {
        return brewTime;
    }

    public void brewTime(@Range(from = 0, to = 20) final int brewTime) {
        this.brewTime = brewTime;
    }

    public @Range(from = 0, to = 400) int fuel() {
        return fuel;
    }

    public void fuel(@Range(from = 0, to = 400) final int fuel) {
        this.fuel = fuel;
    }

    @Override
    public void update(@NotNull ProtocolPlayer<?> user) {
        AbstractOpenInventory openInventory = Menu.OPEN_INVENTORIES.get(user);
        if (openInventory != null) {
            openInventory.sendItems(this.items());
            openInventory.updateWindowData(this.properties());
        }
    }

    @Override
    @NotNull
    public PropertyPair[] properties() {
        return new PropertyPair[] {
                PropertyPair.of(0, this.brewTime),
                PropertyPair.of(1, this.fuel)
        };
    }
}
