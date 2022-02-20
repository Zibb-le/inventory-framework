package org.zibble.inventoryframework.menu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ButtonMenu<C> extends Menu {

    protected @NotNull final MenuItem<C>[][] buttons;

    protected final int buttonRows;
    protected final int buttonColumns;

    public ButtonMenu(@Range(from = 0, to = Integer.MAX_VALUE) final int itemRows,
                      @Range(from = 0, to = Integer.MAX_VALUE) final int itemColumns,
                      @Range(from = 0, to = Integer.MAX_VALUE) final int buttonRows,
                      @Range(from = 0, to = Integer.MAX_VALUE) final int buttonColumns) {

        super(itemRows, itemColumns);
        this.buttonRows = buttonRows;
        this.buttonColumns = buttonColumns;
        this.buttons = new MenuItem[buttonRows][buttonColumns];

    }

    public int getButtonRows() {
        return buttonRows;
    }

    public int getButtonColumns() {
        return buttonColumns;
    }

    @NotNull
    public MenuItem<C>[][] getButtons() {
        return buttons;
    }

    @NotNull
    public List<MenuItem<C>> getButtonsAsList() {
        List<MenuItem<C>> buttons = new ArrayList<>(buttonRows * buttonColumns);
        for (MenuItem<C>[] button : this.buttons) {
            buttons.addAll(Arrays.asList(button));
        }
        return buttons;
    }
}
