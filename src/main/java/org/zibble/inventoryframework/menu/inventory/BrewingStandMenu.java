package org.zibble.inventoryframework.menu.inventory;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.nameable.NamedMenu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.zibble.inventoryframework.menu.property.DataPropertyHolder;
import org.zibble.inventoryframework.menu.property.PropertyPair;
import org.zibble.inventoryframework.protocol.item.ItemStack;

public class BrewingStandMenu extends NamedMenu implements DataPropertyHolder {

    private static final boolean POWDER_SUPPORT = InventoryFramework.framework().serverVersion().isNewerThanOrEquals(ServerVersion.V_1_9);

    private @Range(from = 0, to = 20) int brewTime;
    private @Range(from = 0, to = 400) int fuel;
    private MenuItem<ItemStack> powderSlot;

    public BrewingStandMenu(@Nullable final Component title) {
        super(1, 4, title);
    }

    public MenuItem<ItemStack> getPowderSlot() {
        return powderSlot;
    }

    public void setPowderSlot(MenuItem<ItemStack> powderSlot) {
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

    public @Range(from = 0, to = 20) int getBrewTime() {
        return brewTime;
    }

    public void setBrewTime(@Range(from = 0, to = 20) final int brewTime) {
        this.brewTime = brewTime;
    }

    public @Range(from = 0, to = 400) int getFuel() {
        return fuel;
    }

    public void setFuel(@Range(from = 0, to = 400) final int fuel) {
        this.fuel = fuel;
    }

    @Override
    protected void update(@NotNull AbstractOpenInventory openInventory) {
        if (this.powderSlot != null
                && this.powderSlot.getContent() != null
                && POWDER_SUPPORT) {
            openInventory.setSlot(4, this.powderSlot.getContent());
        }
        openInventory.sendItems(this.items());
        openInventory.updateWindowData(this.properties());
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
