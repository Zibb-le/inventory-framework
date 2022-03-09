package org.zibble.inventoryframework.menu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.openinventory.ButtonOpenInventory;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;

import java.util.Arrays;
import java.util.List;

/**
 * An extension of {@link MenuItem} that can also contain server-determinable buttons.
 * @param <C> The type of Buttons that this menu can contain.
 */
public abstract class ButtonMenu<C> extends Menu {

    protected @NotNull final MenuItem<C>[] buttons;

    protected @Range(from = 0, to = Integer.MAX_VALUE) final int buttonCount;

    /**
     * Creates a button menu with the specified number of item rows, item columns and buttons.
     * @param itemRows The number of item rows.
     * @param itemColumns The number of item columns.
     * @param buttonCount The number of buttons.
     */
    public ButtonMenu(@Range(from = 0, to = Integer.MAX_VALUE) final int itemRows,
                      @Range(from = 0, to = Integer.MAX_VALUE) final int itemColumns,
                      @Range(from = 0, to = Integer.MAX_VALUE) final int buttonCount) {

        super(itemRows, itemColumns);
        this.buttonCount = buttonCount;
        this.buttons = new MenuItem[buttonCount];

    }

    /**
     * @return The number of buttons in this menu.
     */
    public @Range(from = 0, to = Integer.MAX_VALUE) int getButtonCount() {
        return buttonCount;
    }

    /**
     * @return The buttons in this menu
     */
    @NotNull
    public MenuItem<C>[] getButtons() {
        return this.buttons;
    }

    /**
     * Changes a button in this menu
     * @param index The index of the button to change.
     * @param button The new button.
     */
    public void setButton(int index, MenuItem<C> button) {
        this.buttons[index] = button;
    }

    /**
     * @return A list of all the buttons in this menu according to their slot.
     */
    @NotNull
    public List<@Nullable MenuItem<C>> getButtonsAsList() {
        return Arrays.asList(this.buttons);
    }

    @Override
    public void open(@NotNull ProtocolPlayer<?> user) {
        ButtonOpenInventory openInventory = new ButtonOpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        this.update(openInventory);
    }

}
