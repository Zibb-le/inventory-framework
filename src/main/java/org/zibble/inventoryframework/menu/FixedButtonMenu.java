package org.zibble.inventoryframework.menu;

import java.util.function.Consumer;

public abstract class FixedButtonMenu extends Menu {

    private Consumer<Integer> buttonClicked;

    public FixedButtonMenu(int rows, int columns) {
        super(rows, columns);
    }

    public void setButtonClickHandler(Consumer<Integer> buttonClicked) {
        this.buttonClicked = buttonClicked;
    }

    public Consumer<Integer> getButtonClickHandler() {
        return this.buttonClicked;
    }

}
