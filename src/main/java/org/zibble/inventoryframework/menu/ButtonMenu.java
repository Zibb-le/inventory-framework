package org.zibble.inventoryframework.menu;

import com.github.retrooper.packetevents.protocol.player.User;
import org.zibble.inventoryframework.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ButtonMenu<C> extends Menu {

    protected final MenuItem<C>[][] buttons;

    protected int buttonRows;
    protected int buttonColumns;

    public ButtonMenu(int itemRows, int itemColumns, int buttonRows, int buttonColumns) {
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

    public MenuItem<C>[][] getButtons() {
        return buttons;
    }

    public List<MenuItem<C>> getButtonsAsList() {
        List<MenuItem<C>> buttons = new ArrayList<>(buttonRows * buttonColumns);
        for (MenuItem<C>[] button : this.buttons) {
            buttons.addAll(Arrays.asList(button));
        }
        return buttons;
    }
}
