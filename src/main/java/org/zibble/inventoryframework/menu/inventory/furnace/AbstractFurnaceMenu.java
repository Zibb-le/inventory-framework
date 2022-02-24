package org.zibble.inventoryframework.menu.inventory.furnace;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.menu.nameable.NamedMenu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.zibble.inventoryframework.menu.property.DataPropertyHolder;
import org.zibble.inventoryframework.menu.property.PropertyPair;


public abstract class AbstractFurnaceMenu extends NamedMenu implements DataPropertyHolder {

    private @Range(from = 0, to = Integer.MAX_VALUE) int burnTime;
    private @Range(from = 0, to = Integer.MAX_VALUE) int ticksForCurrentFuel;
    private @Range(from = 0, to = 200) int cookTime;
    private static final int COOK_TIME_TOTAL = 200;

    protected AbstractFurnaceMenu(@Nullable final Component title) {
        super(1,3, title);
    }

    @Override
    protected void update(AbstractOpenInventory openInventory) {
        openInventory.sendItems(this.items());
        openInventory.updateWindowData(this.properties());
    }

    @Override
    @NotNull
    public PropertyPair[] properties() {
        return new PropertyPair[] {
                PropertyPair.of(0, this.burnTime),
                PropertyPair.of(1, this.ticksForCurrentFuel),
                PropertyPair.of(2, this.cookTime),
                PropertyPair.of(3, COOK_TIME_TOTAL)
        };
    }

    public @Range(from = 0, to = Integer.MAX_VALUE) int getBurnTime() {
        return burnTime;
    }

    public void setBurnTime(@Range(from = 0, to = Integer.MAX_VALUE) final int burnTime) {
        this.burnTime = burnTime;
    }

    public @Range(from = 0, to = Integer.MAX_VALUE) int getTicksForCurrenFuel() {
        return ticksForCurrentFuel;
    }

    public void setTicksForCurrentFuel(@Range(from = 0, to = Integer.MAX_VALUE) final int ticksForCurrentFuel) {
        this.ticksForCurrentFuel = ticksForCurrentFuel;
    }

    public @Range(from = 0, to = 200) int getCookTime() {
        return cookTime;
    }

    public void setCookTime(@Range(from = 0, to = 200) final int cookTime) {
        this.cookTime = cookTime;
    }
}
