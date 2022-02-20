package org.zibble.inventoryframework.menu;

import com.github.retrooper.packetevents.protocol.player.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.zibble.inventoryframework.menu.property.DataPropertyHolder;
import org.zibble.inventoryframework.protocol.item.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public abstract class Menu implements Iterable<MenuItem<ItemStack>> {

    protected static final Map<User, AbstractOpenInventory> OPEN_INVENTORIES = new ConcurrentHashMap<>();

    protected final int rows;
    protected final int columns;
    protected final char[][] mask;
    protected final @NotNull Map<Character, MenuItem<ItemStack>> itemMap;

    protected @Nullable Consumer<User> onOpen;
    protected @Nullable Consumer<User> onClose;

    public Menu(@Range(from = 1, to = Integer.MAX_VALUE) final int rows,
                @Range(from = 1, to = Integer.MAX_VALUE) final int columns) {

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

    public void setMask(@NotNull final String... masks) {
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

    @NotNull
    public Map<Character, MenuItem<ItemStack>> getItemMap() {
        return this.itemMap;
    }

    public void setItem(final char c, @Nullable final MenuItem<ItemStack> item) {
        if (item == null) {
            this.itemMap.remove(c);
        } else {
            this.itemMap.put(c, item);
        }
    }

    @Nullable
    public MenuItem<ItemStack> getMaskItem(final char maskKey) {
        return this.itemMap.get(maskKey);
    }

    @Nullable
    public MenuItem<ItemStack> getItem(@Range(from = 0, to = Integer.MAX_VALUE) final int slot) {
        return this.getItem(slot % this.columns, slot / this.rows);
    }

    @Nullable
    public MenuItem<ItemStack> getItem(@Range(from = 0, to = Integer.MAX_VALUE) final int x,
                                       @Range(from = 0, to = Integer.MAX_VALUE) final int y) {
        return this.itemMap.get(this.mask[y][x]);
    }

    @NotNull
    public MenuItem<ItemStack>[][] getItems() {
        MenuItem<ItemStack>[][] items = new MenuItem[this.rows][this.columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                char c = this.mask[i][j];
                items[i][j] = this.itemMap.get(c);
            }
        }
        return items;
    }

    @NotNull
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

    @Range(from = 0, to = Integer.MAX_VALUE)
    public int getRows() {
        return rows;
    }

    @Range(from = 0, to = Integer.MAX_VALUE)
    public int getColumns() {
        return columns;
    }

    @Nullable
    public Consumer<User> getOnOpen() {
        return onOpen;
    }

    @Nullable
    public Consumer<User> getOnClose() {
        return onClose;
    }

    public void setOnOpen(@Nullable final Consumer<User> onOpen) {
        this.onOpen = onOpen;
    }

    public void setOnClose(@Nullable final Consumer<User> onClose) {
        this.onClose = onClose;
    }

    @NotNull
    public abstract InventoryType getInventoryType();

    public abstract void open(@NotNull final User user);

    public void update(@NotNull final User user) {
        AbstractOpenInventory openInventory = Menu.OPEN_INVENTORIES.get(user);
        if (openInventory == null) return;

        openInventory.sendItems(this.getItems());
        if (this instanceof DataPropertyHolder) {
            DataPropertyHolder dataPropertyHolder = (DataPropertyHolder) this;
            openInventory.updateWindowData(dataPropertyHolder.getProperties());
        }

    }

    public void updateSlot(@Range(from = 0, to = Integer.MAX_VALUE) final int slot,
                           @NotNull final User user) {
        AbstractOpenInventory openInventory = Menu.OPEN_INVENTORIES.get(user);
        if (openInventory != null) {
            MenuItem<ItemStack> item = this.getItem(slot);
            openInventory.setSlot(slot, item == null ? null : item.getContent());
        }
    }

    public void close(@NotNull final User user) {
        AbstractOpenInventory inv = OPEN_INVENTORIES.remove(user);
        if (inv != null) {
            inv.close();
        }
    }

}
