package org.zibble.inventoryframework.menu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.menu.openinventory.FixedButtonOpenInventory;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;

import java.util.function.Consumer;

public abstract class FixedButtonMenu extends Menu {

    private @Nullable Consumer<Integer> buttonClicked;

    public FixedButtonMenu(@Range(from = 0, to = Integer.MAX_VALUE) final int rows,
                           @Range(from = 0, to = Integer.MAX_VALUE) final int columns) {
        super(rows, columns);
    }

    public void buttonClickHandler(@Nullable final Consumer<Integer> buttonClicked) {
        this.buttonClicked = buttonClicked;
    }

    @Nullable
    public Consumer<Integer> buttonClickHandler() {
        return this.buttonClicked;
    }

    @Override
    public void open(@NotNull ProtocolPlayer<?> user) {
        FixedButtonOpenInventory openInventory = new FixedButtonOpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        this.update(openInventory);
    }

}
