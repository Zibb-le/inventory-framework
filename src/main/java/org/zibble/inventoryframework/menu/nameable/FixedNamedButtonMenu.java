package org.zibble.inventoryframework.menu.nameable;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.menu.FixedButtonMenu;

public abstract class FixedNamedButtonMenu extends FixedButtonMenu implements NameableMenu {

    private final @Nullable Component title;

    public FixedNamedButtonMenu(@Range(from = 0, to = Integer.MAX_VALUE) final int rows,
                                @Range(from = 0, to = Integer.MAX_VALUE) final int columns,
                                @Nullable final Component title) {

        super(rows, columns);
        this.title = title;

    }

    @Override
    @Nullable
    public Component getTitle() {
        return this.title;
    }
}
