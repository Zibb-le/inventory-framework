package org.zibble.inventoryframework.menu.inventory;

import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.zibble.inventoryframework.protocol.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class WorkBenchMenu extends Menu {

    private MenuItem<ItemStack> resultItem;

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

    public MenuItem<ItemStack> resultItem() {
        return resultItem;
    }

    public void resultItem(MenuItem<ItemStack> resultItem) {
        this.resultItem = resultItem;
    }

    @Override
    protected void update(AbstractOpenInventory openInventory) {
        List<com.github.retrooper.packetevents.protocol.item.ItemStack> itemStacks = new ArrayList<>(this.items().length * this.items()[0].length + 1);
        itemStacks.add(com.github.retrooper.packetevents.protocol.item.ItemStack.EMPTY);
        for (MenuItem<ItemStack>[] a : this.items()) {
            for (MenuItem<ItemStack> item : a) {
                if (item == null || item.content() == null) itemStacks.add(null);
                else itemStacks.add(item.content().asProtocol());
            }
        }
        openInventory.sendItems(itemStacks);
        if (this.resultItem != null && this.resultItem.content() != null) {
            openInventory.setSlot(0, this.resultItem.content());
        }
    }

}
