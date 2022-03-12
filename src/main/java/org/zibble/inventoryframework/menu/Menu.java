package org.zibble.inventoryframework.menu;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.zibble.inventoryframework.menu.openinventory.OpenInventory;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;
import org.zibble.inventoryframework.protocol.item.StackItem;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Represents an GUI that can be opened for a player.
 * This class is meant to be extended and not for direct use.
 * Each menu represents what items and inventory type need to be displayed to the player.
 * <p>
 *
 * Each menu can be opened by any amount of players and contents can be updated for each player.
 * Menu contains {@link MenuItem}s that can be added to it.
 * Other implementations of menu like {@link ButtonMenu} also support buttons that may or may not be configurable.
 * <p>
 *
 * Adding items in a menu requires a mask to be created.
 * The mask determines which slots can be filled by any item linked to the character.
 * By default, the mask is entirely filled with 'X'.
 */
public abstract class Menu implements Iterable<MenuItem<StackItem>> {

    protected static final Map<ProtocolPlayer<?>, AbstractOpenInventory> OPEN_INVENTORIES = new ConcurrentHashMap<>();

    protected @Range(from = 0, to = Integer.MAX_VALUE) final int rows;
    protected @Range(from = 0, to = Integer.MAX_VALUE) final int columns;
    protected char[][] mask;
    protected final @NotNull Map<Character, MenuItem<StackItem>> itemMap;

    protected @Nullable Consumer<ProtocolPlayer<?>> onOpen;
    protected @Nullable Consumer<ProtocolPlayer<?>> onClose;

    /**
     * Creates a menu with the specified number of rows and columns.
     * <strong>NOT NECESSARILY A CHEST MENU</strong>
     * @param rows number of rows
     * @param columns number of columns
     */
    public Menu(@Range(from = 1, to = Integer.MAX_VALUE) final int rows,
                @Range(from = 1, to = Integer.MAX_VALUE) final int columns) {

        if (!this.isSupported()) throw new UnsupportedOperationException("This inventory type is not supported in this version");

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

    /**
     * @return Returns the mask of the menu.
     */
    public char[][] getMask() {
        return mask;
    }

    /**
     * @param masks The new mask of the menu.
     */
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
                this.mask[i][j] = mask.charAt(j);
            }
        }
    }

    /**
     * @return Returns a non-modifiable map listing the {@link MenuItem}s linked to the characters that can be used in mask.
     */
    @NotNull
    public Map<Character, MenuItem<StackItem>> getItemMap() {
        return Collections.unmodifiableMap(this.itemMap);
    }

    /**
     * @param c The character whose linked {@link MenuItem} has to be set/changed
     * @param item The new {@link MenuItem} to be linked to the character
     */
    public void setItem(final char c, @Nullable final MenuItem<StackItem> item) {
        if (item == null) {
            this.itemMap.remove(c);
        } else {
            this.itemMap.put(c, item);
        }
    }

    /**
     * @param maskKey The character whose linked {@link MenuItem} has to be returned
     * @return The menu item linked to the character or null if no item is linked to the character
     */
    @Nullable
    public MenuItem<StackItem> getMaskItem(final char maskKey) {
        return this.itemMap.get(maskKey);
    }

    /**
     * @param slot The slot whose {@link MenuItem} has to be returned
     * @return The menu item linked to the slot or null if no item is linked to the slot
     */
    @Nullable
    public MenuItem<StackItem> getItemAt(@Range(from = 0, to = Integer.MAX_VALUE) final int slot) {
        return this.getItemAt(slot % this.columns, slot / this.rows);
    }

    /**
     * @param x The x coordinate of the slot whose {@link MenuItem} has to be returned
     * @param y The y coordinate of the slot whose {@link MenuItem} has to be returned
     * @return The menu item linked to the slot or null if no item is linked to the slot
     */
    @Nullable
    public MenuItem<StackItem> getItemAt(@Range(from = 0, to = Integer.MAX_VALUE) final int x,
                                         @Range(from = 0, to = Integer.MAX_VALUE) final int y) {
        return this.itemMap.get(this.mask[y][x]);
    }


    /**
     * @return The items in the menu as a 2D array imitating a rectangle of the GUI
     */
    @NotNull
    public MenuItem<StackItem>[][] getItems() {
        MenuItem<StackItem>[][] items = new MenuItem[this.rows][this.columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                char c = this.mask[i][j];
                items[i][j] = this.itemMap.get(c);
            }
        }
        return items;
    }

    /**
     * @return List of items in the menu in order of their slots
     */
    @NotNull
    public List<MenuItem<StackItem>> asItemList() {
        List<MenuItem<StackItem>> items = new ArrayList<>(rows * columns);
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
    public Iterator<MenuItem<StackItem>> iterator() {
        return this.asItemList().iterator();
    }

    @Range(from = 0, to = Integer.MAX_VALUE)
    public int getRows() {
        return rows;
    }

    @Range(from = 0, to = Integer.MAX_VALUE)
    public int getColumns() {
        return columns;
    }

    /**
     * @return The {@link Consumer} handling tasks to be run when the inventory is opened
     */
    @ApiStatus.Internal
    @Nullable
    public Consumer<@NotNull ProtocolPlayer<?>> onOpen() {
        return onOpen;
    }

    /**
     * @param onOpen A {@link Consumer} that can be used to run tasks when the inventory is opened
     */
    public void onOpen(@Nullable final Consumer<@NotNull ProtocolPlayer<?>> onOpen) {
        this.onOpen = onOpen;
    }

    /**
     * @param onClose A {@link Consumer} that can be used to run tasks when the inventory is closed
     */
    public void onClose(@Nullable final Consumer<@NotNull ProtocolPlayer<?>> onClose) {
        this.onClose = onClose;
    }

    @ApiStatus.Internal
    @Nullable
    public Consumer<@NotNull ProtocolPlayer<?>> onClose() {
        return onClose;
    }

    /**
     * @return The {@link InventoryType} of the menu
     */
    @NotNull
    public abstract InventoryType type();

    /**
     * @return true if the menu is supported in the current server version, false in all other cases
     */
    public abstract boolean isSupported();

    /**
     * Opens the Menu for a {@link ProtocolPlayer}
     * @param user The {@link ProtocolPlayer} to open the menu for
     */
    public void open(@NotNull final ProtocolPlayer<?> user) {
        OpenInventory openInventory = new OpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        this.update(openInventory);
    }

    protected abstract void update(@NotNull final AbstractOpenInventory openInventory);

    /**
     * Updates the Menu for a {@link ProtocolPlayer}
     * @param user The {@link ProtocolPlayer} to update the menu for
     */
    public void update(@NotNull final ProtocolPlayer<?> user) {
        AbstractOpenInventory openInventory = Menu.OPEN_INVENTORIES.get(user);
        if (openInventory == null) return;

        this.update(openInventory);
    }

    /**
     * Updates a slot in the menu for a {@link ProtocolPlayer}
     * @param slot the slot to be updated
     * @param user The {@link ProtocolPlayer} to update the slot for
     */
    public void updateSlot(@Range(from = 0, to = Integer.MAX_VALUE) final int slot,
                           @NotNull final ProtocolPlayer<?> user) {
        AbstractOpenInventory openInventory = Menu.OPEN_INVENTORIES.get(user);
        if (openInventory != null) {
            MenuItem<StackItem> item = this.getItemAt(slot);
            openInventory.setSlot(slot, item == null ? null : item.getContent());
        }
    }

    /**
     * @return A {@link Set} of all {@link ProtocolPlayer}s currently viewing the menu
     */
    @ApiStatus.Experimental
    public Set<ProtocolPlayer<?>> getViewers() {
        Set<ProtocolPlayer<?>> viewers = new HashSet<>();
        Menu.OPEN_INVENTORIES.forEach((user, openInventory) -> {
           if (openInventory.menu().equals(this)) viewers.add(user);
        });
        return viewers;
    }

    /**
     * Closes the Menu for a {@link ProtocolPlayer}
     * @param user The {@link ProtocolPlayer} to close the menu for
     */
    public void close(@NotNull final ProtocolPlayer<?> user) {
        AbstractOpenInventory inv = OPEN_INVENTORIES.remove(user);
        if (inv != null) {
            inv.close();
        }
    }

}
