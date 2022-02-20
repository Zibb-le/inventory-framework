package org.zibble.inventoryframework.menu.nameable;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.menu.Menu;

public abstract class NamedMenu extends Menu implements NameableMenu {

    private final @Nullable Component title;

    public NamedMenu(@Range(from = 0, to = Integer.MAX_VALUE) final int rows,
                     @Range(from = 0, to = Integer.MAX_VALUE) final int columns,
                     @Nullable final Component title) {

        super(rows, columns);
        this.title = title;

    }

    @Override
    @Nullable
    public Component getTitle() {
        return title;
    }

}
