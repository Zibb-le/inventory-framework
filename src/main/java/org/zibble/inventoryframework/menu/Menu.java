package org.zibble.inventoryframework.menu;

import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.player.User;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public abstract class Menu implements Iterable<MenuItem<ItemStack>> {

    public static final List<AbstractOpenInventory> OPEN_INVENTORIES = Collections.synchronizedList(new ArrayList<>());

    protected final MenuItem<ItemStack>[][] items;
    protected final int rows;
    protected final int columns;
    protected char[][] mask;
    protected final Map<Character, MenuItem<ItemStack>> itemMap;

    protected Consumer<User> onOpen;
    protected Consumer<User> onClose;


    public Menu(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.items = new MenuItem[rows][columns];
        this.mask = new char[rows][columns];
        this.itemMap = new ConcurrentHashMap<>();
    }

    public MenuItem<ItemStack> getItemAt(int index) {
        return items[index / columns][index % columns];
    }

    public void setItemAt(int index, MenuItem<ItemStack> item) {
        items[index / columns][index % columns] = item;
    }

    public MenuItem<ItemStack>[][] getItems() {
        return items;
    }

    public char[][] getMask() {
        return mask;
    }

    public void setMask(char[][] mask) {
        this.mask = mask;
        for (int i = 0; i < this.mask.length; i++) {
            for (int j = 0; j < this.mask[i].length; j++) {
                this.items[i][j] = this.itemMap.get(this.mask[i][j]);
            }
        }
    }

    public Map<Character, MenuItem<ItemStack>> getItemMap() {
        return itemMap;
    }

    public void changeMaskItem(char maskKey, MenuItem<ItemStack> item) {
        this.itemMap.put(maskKey, item);
        for (int i = 0; i < this.mask.length; i++) {
            for (int j = 0; j < this.mask[i].length; j++) {
                this.items[i][j] = this.itemMap.get(this.mask[i][j]);
            }
        }
    }

    public MenuItem<ItemStack> getMaskItem(char maskKey) {
        return this.itemMap.get(maskKey);
    }

    public List<MenuItem<ItemStack>> getAsList() {
        List<MenuItem<ItemStack>> arrayList = new ArrayList<>(rows * columns);
        for (MenuItem<ItemStack>[] item : this.items) {
            arrayList.addAll(Arrays.asList(item));
        }
        return arrayList;
    }

    @NotNull
    @Override
    public Iterator<MenuItem<ItemStack>> iterator() {
        return getAsList().iterator();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Consumer<User> getOnOpen() {
        return onOpen;
    }

    public Consumer<User> getOnClose() {
        return onClose;
    }

    public void setOnOpen(Consumer<User> onOpen) {
        this.onOpen = onOpen;
    }

    public void setOnClose(Consumer<User> onClose) {
        this.onClose = onClose;
    }

    public abstract InventoryType getInventoryType();

    public abstract void open(User user);

}
