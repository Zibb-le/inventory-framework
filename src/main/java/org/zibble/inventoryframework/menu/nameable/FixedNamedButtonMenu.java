package org.zibble.inventoryframework.menu.nameable;

import net.kyori.adventure.text.Component;
import org.zibble.inventoryframework.menu.FixedButtonMenu;

public abstract class FixedNamedButtonMenu extends FixedButtonMenu implements NameableMenu {

    private final Component title;

    public FixedNamedButtonMenu(int rows, int columns, Component title) {
        super(rows, columns);
        this.title = title;
    }

    @Override
    public Component getTitle() {
        return this.title;
    }
}
