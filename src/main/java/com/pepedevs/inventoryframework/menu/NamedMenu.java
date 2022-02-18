package com.pepedevs.inventoryframework.menu;

import com.pepedevs.inventoryframework.menu.Menu;
import net.kyori.adventure.text.Component;

public abstract class NamedMenu extends Menu {

    private final Component title;

    public NamedMenu(int rows, int columns, Component title) {
        super(rows, columns);
        this.title = title;
    }

    public Component getTitle() {
        return title;
    }

}
