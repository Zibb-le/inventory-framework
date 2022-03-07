package org.zibble.inventoryframework.menu.inventory.furnace;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.menu.nameable.NamedMenu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.zibble.inventoryframework.menu.property.DataPropertyHolder;
import org.zibble.inventoryframework.menu.property.PropertyPair;

/**
 * Abstraction for all 3 types for furnace GUIs.
 */
public abstract class AbstractFurnaceMenu extends NamedMenu implements DataPropertyHolder {

    private @Range(from = 0, to = Integer.MAX_VALUE) int burnTime;
    private @Range(from = 0, to = Integer.MAX_VALUE) int ticksForCurrentFuel;
    private @Range(from = 0, to = 200) int progressBar;
    private static final int COOK_TIME_TOTAL = 200;

    protected AbstractFurnaceMenu(@Nullable final Component title) {
        super(1,3, title);
    }

    @Override
    protected void update(@NotNull AbstractOpenInventory openInventory) {
        openInventory.sendItems(this.getItems());
        openInventory.updateWindowData(this.properties());
    }

    @Override
    @NotNull
    public PropertyPair[] properties() {
        return new PropertyPair[] {
                PropertyPair.of(0, this.burnTime),
                PropertyPair.of(1, this.ticksForCurrentFuel),
                PropertyPair.of(2, this.progressBar),
                PropertyPair.of(3, COOK_TIME_TOTAL)
        };
    }

    /**
     * @return The burn time left of the current fuel (in ticks). Generally has values between 0 and max cook time of current fuel (in ticks).
     *         Used for determining the left fire icon
     *         Refer to <a href="https://wiki.vg/Protocol#Window_Property">Window Property</a>
     */
    public @Range(from = 0, to = Integer.MAX_VALUE) int getBurnTime() {
        return burnTime;
    }

    /**
     * @param burnTime The new left burn time of the current fuel (in ticks). Should have values between 0 and max cook time of current fuel (in ticks).
     *                 Used for determining the left fire icon
     *                 Refer to <a href="https://wiki.vg/Protocol#Window_Property">Window Property</a>
     */
    public void setBurnTime(@Range(from = 0, to = Integer.MAX_VALUE) final int burnTime) {
        this.burnTime = burnTime;
    }

    /**
     * @return The maximum cook time of the current fuel (in ticks). Should be higher than the current left burn time of the current fuel (in ticks).
     *         Used for determining the right fire icon
     *         Refer to <a href="https://wiki.vg/Protocol#Window_Property">Window Property</a>
     */
    public @Range(from = 0, to = Integer.MAX_VALUE) int getTicksForCurrenFuel() {
        return ticksForCurrentFuel;
    }

    /**
     * @param ticksForCurrentFuel The new maximum cook time of the current fuel (in ticks). Should be higher than the current left burn time of the current fuel (in ticks).
     *                            Used for determining the right fire icon
     *                            Refer to <a href="https://wiki.vg/Protocol#Window_Property">Window Property</a>
     */
    public void setTicksForCurrentFuel(@Range(from = 0, to = Integer.MAX_VALUE) final int ticksForCurrentFuel) {
        this.ticksForCurrentFuel = ticksForCurrentFuel;
    }

    /**
     * @return The current progress of the progress bar. Generally has values between 0 and 200.
     *         Refer to <a href="https://wiki.vg/Protocol#Window_Property">Window Property</a>
     */
    public @Range(from = 0, to = 200) int getProgressBar() {
        return progressBar;
    }

    /**
     * @param progressBar The new current progress of the progress bar. Should have values between 0 and 200.
     *                    Refer to <a href="https://wiki.vg/Protocol#Window_Property">Window Property</a>
     */
    public void setProgressBar(@Range(from = 0, to = 200) final int progressBar) {
        this.progressBar = progressBar;
    }
}
