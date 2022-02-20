package org.zibble.inventoryframework.menu.nameable;

import net.kyori.adventure.text.Component;
import org.zibble.inventoryframework.menu.Menu;

public abstract class NamedMenu extends Menu implements NameableMenu {

    private final Component title;

    public NamedMenu(int rows, int columns, Component title) {
        super(rows, columns);
        this.title = title;
    }

    @Override
    public Component getTitle() {
        return title;
    }

}
