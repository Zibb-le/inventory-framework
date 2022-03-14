package org.zibble.inventoryframework.menu.inventory;

import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.protocol.Item;

import java.util.ArrayList;
import java.util.List;

public class WorkBenchMenu extends Menu {

    private @Nullable MenuItem<? extends Item> resultItem;

    public WorkBenchMenu() {
        super(3, 3);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.WORKBENCH;
    }

    @Override
    public boolean isSupported() {
        return true;
    }

    @Nullable
    public MenuItem<? extends Item> getResultItem() {
        return resultItem;
    }

    public void setResultItem(@Nullable MenuItem<? extends Item> resultItem) {
        this.resultItem = resultItem;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    protected void update(@NotNull AbstractOpenInventory openInventory) {
        List<com.github.retrooper.packetevents.protocol.item.ItemStack> itemStacks = new ArrayList<>(this.getItems().length * this.getItems()[0].length + 1);
        itemStacks.add(com.github.retrooper.packetevents.protocol.item.ItemStack.EMPTY);
        for (MenuItem<? extends Item>[] a : this.getItems()) {
            for (MenuItem<? extends Item> item : a) {
                if (item == null || item.getContent() == null) itemStacks.add(null);
                else itemStacks.add(item.getContent().asProtocol());
            }
        }
        openInventory.sendItems(itemStacks);
        if (this.resultItem != null && this.resultItem.getContent() != null) {
            openInventory.setSlot(0, this.resultItem.getContent());
        }
    }

}
