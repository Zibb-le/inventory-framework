package org.zibble.inventoryframework.menu.nameable;

import org.zibble.inventoryframework.menu.Menu;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public abstract class NamedMenu extends Menu implements NameableMenu {

    protected @Nullable Component title;

    public NamedMenu(@Range(from = 0, to = Integer.MAX_VALUE) final int rows,
                     @Range(from = 0, to = Integer.MAX_VALUE) final int columns,
                     @Nullable final Component title) {

        super(rows, columns);
        this.title = title;

    }

    @Override
    @Nullable
    public Component title() {
        return title;
    }

    @Override
    public void title(@Nullable Component title) {
        this.title = title;
    }

}
