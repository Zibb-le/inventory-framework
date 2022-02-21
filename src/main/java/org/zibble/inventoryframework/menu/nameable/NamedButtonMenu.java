package org.zibble.inventoryframework.menu.nameable;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.menu.ButtonMenu;

public abstract class NamedButtonMenu<C> extends ButtonMenu<C> implements NameableMenu {

    private final @Nullable Component title;

    public NamedButtonMenu(@Range(from = 0, to = Integer.MAX_VALUE) final int itemRows,
                           @Range(from = 0, to = Integer.MAX_VALUE) final int itemColumns,
                           @Range(from = 0, to = Integer.MAX_VALUE) final int buttonRows,
                           @Range(from = 0, to = Integer.MAX_VALUE) final int buttonColumns,
                           @Nullable final Component title) {

        super(itemRows, itemColumns, buttonRows, buttonColumns);
        this.title = title;

    }

    @Override
    @Nullable
    public Component title() {
        return title;
    }

}
