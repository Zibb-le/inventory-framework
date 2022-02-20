package org.zibble.inventoryframework.menu.inventory.furnace;

import com.github.retrooper.packetevents.protocol.player.User;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.nameable.NamedMenu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.zibble.inventoryframework.menu.openinventory.OpenInventory;
import net.kyori.adventure.text.Component;
import org.zibble.inventoryframework.menu.property.DataPropertyHolder;
import org.zibble.inventoryframework.menu.property.PropertyPair;

public abstract class AbstractFurnaceMenu extends NamedMenu implements DataPropertyHolder {

    private int burnTime;
    private int ticksForCurrentFuel;
    private int cookTime;
    private int cookTimeTotal;

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

    @Override
    public void update(User user) {
        AbstractOpenInventory openInventory = Menu.OPEN_INVENTORIES.get(user);
        if (openInventory != null) {
            openInventory.sendItems(this.getItems());
            openInventory.updateWindowData(this.getProperties());
        }
    }

    @Override
    public PropertyPair[] getProperties() {
        return new PropertyPair[] {
                PropertyPair.of(0, this.burnTime),
                PropertyPair.of(1, this.ticksForCurrentFuel),
                PropertyPair.of(2, this.cookTime),
                PropertyPair.of(3, this.cookTimeTotal)
        };
    }

    public int getBurnTime() {
        return burnTime;
    }

    public void setBurnTime(int burnTime) {
        this.burnTime = burnTime;
    }

    public int getTicksForCurrentFuel() {
        return ticksForCurrentFuel;
    }

    public void setTicksForCurrentFuel(int ticksForCurrentFuel) {
        this.ticksForCurrentFuel = ticksForCurrentFuel;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public int getCookTimeTotal() {
        return cookTimeTotal;
    }

    public void setCookTimeTotal(int cookTimeTotal) {
        this.cookTimeTotal = cookTimeTotal;
    }
}
