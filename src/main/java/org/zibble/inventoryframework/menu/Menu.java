package org.zibble.inventoryframework.menu;

import com.github.retrooper.packetevents.protocol.player.User;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.zibble.inventoryframework.protocol.item.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public abstract class Menu implements Iterable<MenuItem<ItemStack>> {

    public static final List<AbstractOpenInventory> OPEN_INVENTORIES = Collections.synchronizedList(new ArrayList<>());

    protected final int rows;
    protected final int columns;
    protected char[][] mask;
    protected final Map<Character, MenuItem<ItemStack>> itemMap;

    protected Consumer<User> onOpen;
    protected Consumer<User> onClose;


    public Menu(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.mask = new char[rows][columns];
        char[] fill = new char[columns];
        Arrays.fill(fill, 'X');
        Arrays.fill(this.mask, fill);
        this.itemMap = new ConcurrentHashMap<>();
    }

    public char[][] getMask() {
        return mask;
    }

    public void setMask(String... masks) {
        if (masks.length > rows) {
            throw new IllegalArgumentException("Mask length must be equal to rows. " + masks.length);
        }
        for (int i = 0; i < masks.length; i++) {
            String mask = masks[i];
            if (mask.length() != columns) {
                throw new IllegalArgumentException("`" + mask + "` Mask length must be equal to columns");
            }
            for (int j = 0; j < mask.length(); j++) {
                char c = mask.charAt(j);
                this.mask[i][j] = c;
            }
        }
    }

    public Map<Character, MenuItem<ItemStack>> getItemMap() {
        return this.itemMap;
    }

    public MenuItem<ItemStack> getMaskItem(char maskKey) {
        return this.itemMap.get(maskKey);
    }

    public MenuItem<ItemStack>[][] getItems() {
        MenuItem<ItemStack>[][] items = new MenuItem[rows][columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                char c = this.mask[i][j];
                items[i][j] = this.itemMap.get(c);
            }
        }
        return items;
    }

    public List<MenuItem<ItemStack>> getAsList() {
        List<MenuItem<ItemStack>> items = new ArrayList<>(rows * columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                char c = this.mask[i][j];
                items.add(this.itemMap.get(c));
            }
        }
        return items;
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
