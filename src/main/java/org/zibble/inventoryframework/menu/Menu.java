package org.zibble.inventoryframework.menu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.zibble.inventoryframework.menu.property.DataPropertyHolder;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;
import org.zibble.inventoryframework.protocol.item.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public abstract class Menu implements Iterable<MenuItem<ItemStack>> {

    protected static final Map<ProtocolPlayer<?>, AbstractOpenInventory> OPEN_INVENTORIES = new ConcurrentHashMap<>();

    protected @Range(from = 0, to = Integer.MAX_VALUE) final int rows;
    protected @Range(from = 0, to = Integer.MAX_VALUE) final int columns;
    protected char[][] mask;
    protected final @NotNull Map<Character, MenuItem<ItemStack>> itemMap;

    protected @Nullable Consumer<ProtocolPlayer<?>> onOpen;
    protected @Nullable Consumer<ProtocolPlayer<?>> onClose;

    public Menu(@Range(from = 1, to = Integer.MAX_VALUE) final int rows,
                @Range(from = 1, to = Integer.MAX_VALUE) final int columns) {

        this.rows = rows;
        this.columns = columns;

        this.mask = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.mask[i][j] = 'X';
            }
        }

        this.itemMap = new ConcurrentHashMap<>();

    }

    public char[][] mask() {
        return mask;
    }

    public void mask(@NotNull final String... masks) {
        if (masks.length > rows) {
            throw new IllegalArgumentException("Mask length must be equal to rows. " + masks.length);
        }
        for (int i = 0; i < masks.length; i++) {
            String mask = masks[i];
            if (mask.length() != columns) {
                throw new IllegalArgumentException("`" + mask + "` Mask length must be equal to columns");
            }
            for (int j = 0; j < mask.length(); j++) {
                this.mask[i][j] = mask.charAt(j);
            }
        }
    }

    @NotNull
    public Map<Character, MenuItem<ItemStack>> itemMap() {
        return this.itemMap;
    }

    public void item(final char c, @Nullable final MenuItem<ItemStack> item) {
        if (item == null) {
            this.itemMap.remove(c);
        } else {
            this.itemMap.put(c, item);
        }
    }

    @Nullable
    public MenuItem<ItemStack> maskItem(final char maskKey) {
        return this.itemMap.get(maskKey);
    }

    @Nullable
    public MenuItem<ItemStack> item(@Range(from = 0, to = Integer.MAX_VALUE) final int slot) {
        return this.item(slot % this.columns, slot / this.rows);
    }

    @Nullable
    public MenuItem<ItemStack> item(@Range(from = 0, to = Integer.MAX_VALUE) final int x,
                                    @Range(from = 0, to = Integer.MAX_VALUE) final int y) {
        return this.itemMap.get(this.mask[y][x]);
    }

    @NotNull
    public MenuItem<ItemStack>[][] items() {
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
    public List<MenuItem<ItemStack>> asList() {
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
        return this.asList().iterator();
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
    public Consumer<ProtocolPlayer<?>> onOpen() {
        return onOpen;
    }

    public void onOpen(@Nullable final Consumer<ProtocolPlayer<?>> onOpen) {
        this.onOpen = onOpen;
    }

    public void onClose(@Nullable final Consumer<ProtocolPlayer<?>> onClose) {
        this.onClose = onClose;
    }

    @Nullable
    public Consumer<ProtocolPlayer<?>> onClose() {
        return onClose;
    }

    @NotNull
    public abstract InventoryType type();

    public abstract void open(@NotNull final ProtocolPlayer<?> user);

    public void update(@NotNull final ProtocolPlayer<?> user) {
        AbstractOpenInventory openInventory = Menu.OPEN_INVENTORIES.get(user);
        if (openInventory == null) return;

        openInventory.sendItems(this.items());
        if (this instanceof DataPropertyHolder) {
            DataPropertyHolder dataPropertyHolder = (DataPropertyHolder) this;
            openInventory.updateWindowData(dataPropertyHolder.properties());
        }

    }

    public void updateSlot(@Range(from = 0, to = Integer.MAX_VALUE) final int slot,
                           @NotNull final ProtocolPlayer<?> user) {
        AbstractOpenInventory openInventory = Menu.OPEN_INVENTORIES.get(user);
        if (openInventory != null) {
            MenuItem<ItemStack> item = this.item(slot);
            openInventory.setSlot(slot, item == null ? null : item.content());
        }
    }

    public void close(@NotNull final ProtocolPlayer<?> user) {
        AbstractOpenInventory inv = OPEN_INVENTORIES.remove(user);
        if (inv != null) {
            inv.close();
        }
    }

}
