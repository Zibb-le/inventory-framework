package org.zibble.inventoryframework.menu.inventory;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.nameable.NamedMenu;
import org.zibble.inventoryframework.menu.property.DataPropertyHolder;
import org.zibble.inventoryframework.menu.property.PropertyPair;
import org.zibble.inventoryframework.protocol.Item;

public class BrewingStandMenu extends NamedMenu implements DataPropertyHolder {

    private static final boolean POWDER_SUPPORT = InventoryFramework.framework().getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_9);

    private @Range(from = 0, to = 20) int fuel;
    private @Range(from = 0, to = 400) int brewTime;
    private MenuItem<? extends Item> powderSlot;

    public BrewingStandMenu(@Nullable final Component title) {
        super(1, 4, title);
    }

    public MenuItem<? extends Item> getPowderSlot() {
        return powderSlot;
    }

    public void setPowderSlot(MenuItem<? extends Item> powderSlot) {
        this.powderSlot = powderSlot;
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.BREWING_STAND;
    }

    @Override
    public boolean isSupported() {
        return true;
    }

    /**
     * @return The current fuel left in the brewing stand
     *         Refer to <a href="https://wiki.vg/Protocol#Window_Property">Window Property</a>
     */
    public @Range(from = 0, to = 20) int getFuel() {
        return fuel;
    }

    /**
     * @param fuel The fuel level to set
     *             Refer to <a href="https://wiki.vg/Protocol#Window_Property">Window Property</a>
     */
    public void setFuel(@Range(from = 0, to = 20) final int fuel) {
        this.fuel = fuel;
    }

    /**
     * @return The current brew time of the brewing stand
     *         Refer to <a href="https://wiki.vg/Protocol#Window_Property">Window Property</a>
     */
    public @Range(from = 0, to = 400) int getBrewTime() {
        return brewTime;
    }

    /**
     * @param brewTime The brew time to set
     *                 Refer to <a href="https://wiki.vg/Protocol#Window_Property">Window Property</a>
     */
    public void setBrewTime(@Range(from = 0, to = 400) final int brewTime) {
        this.brewTime = brewTime;
    }

    @Override
    protected void update(@NotNull AbstractOpenInventory openInventory) {
        if (this.powderSlot != null
                && this.powderSlot.getContent() != null
                && POWDER_SUPPORT) {
            openInventory.setSlot(4, this.powderSlot.getContent());
        }
        openInventory.sendItems(this.getItems());
        openInventory.updateWindowData(this.properties());
    }

    @Override
    @NotNull
    public PropertyPair[] properties() {
        return new PropertyPair[] {
                PropertyPair.of(0, this.fuel),
                PropertyPair.of(1, this.brewTime)
        };
    }
}
