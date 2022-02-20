package org.zibble.inventoryframework.menu;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.function.Consumer;

public abstract class FixedButtonMenu extends Menu {

    private @Nullable Consumer<Integer> buttonClicked;

    public FixedButtonMenu(@Range(from = 0, to = Integer.MAX_VALUE) final int rows,
                           @Range(from = 0, to = Integer.MAX_VALUE) final int columns) {
        super(rows, columns);
    }

    public void setButtonClickHandler(@Nullable final Consumer<Integer> buttonClicked) {
        this.buttonClicked = buttonClicked;
    }

    @Nullable
    public Consumer<Integer> getButtonClickHandler() {
        return this.buttonClicked;
    }

}
