package org.zibble.inventoryframework.menu;

import net.kyori.adventure.text.Component;

public abstract class NamedButtonMenu<C> extends ButtonMenu<C> {

    private final Component title;

    public NamedButtonMenu(int itemRows, int itemColumns, int buttonRows, int buttonColumns, Component title) {
        super(itemRows, itemColumns, buttonRows, buttonColumns);
        this.title = title;
    }

    public Component getTitle() {
        return title;
    }

}
