package org.zibble.inventoryframework.menu.nameable;

import net.kyori.adventure.text.Component;
import org.zibble.inventoryframework.menu.ButtonMenu;

public abstract class NamedButtonMenu<C> extends ButtonMenu<C> implements NameableMenu {

    private final Component title;

    public NamedButtonMenu(int itemRows, int itemColumns, int buttonRows, int buttonColumns, Component title) {
        super(itemRows, itemColumns, buttonRows, buttonColumns);
        this.title = title;
    }

    @Override
    public Component getTitle() {
        return title;
    }

}
