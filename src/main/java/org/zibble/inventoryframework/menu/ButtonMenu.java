package org.zibble.inventoryframework.menu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.openinventory.ButtonOpenInventory;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;

import java.util.Arrays;
import java.util.List;

public abstract class ButtonMenu<C> extends Menu {

    protected @NotNull final MenuItem<C>[] buttons;

    protected @Range(from = 0, to = Integer.MAX_VALUE) final int buttonCount;

    public ButtonMenu(@Range(from = 0, to = Integer.MAX_VALUE) final int itemRows,
                      @Range(from = 0, to = Integer.MAX_VALUE) final int itemColumns,
                      @Range(from = 0, to = Integer.MAX_VALUE) final int buttonCount) {

        super(itemRows, itemColumns);
        this.buttonCount = buttonCount;
        this.buttons = new MenuItem[buttonCount];

    }

    public @Range(from = 0, to = Integer.MAX_VALUE) int getButtonCount() {
        return buttonCount;
    }

    @NotNull
    public MenuItem<C>[] getButtons() {
        return this.buttons;
    }

    public void setButton(int index, MenuItem<C> button) {
        this.buttons[index] = button;
    }

    @NotNull
    public List<MenuItem<C>> getButtonsAsList() {
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
