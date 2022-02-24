package org.zibble.inventoryframework.menu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.menu.openinventory.FixedButtonOpenInventory;
import org.zibble.inventoryframework.menu.property.DataPropertyHolder;
import org.zibble.inventoryframework.menu.property.PropertyPair;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;

import java.util.function.Consumer;

public abstract class FixedButtonMenu extends Menu implements DataPropertyHolder {

    private @Nullable Consumer<Integer> clickHandler;
    private @Range(from = -1, to = Integer.MAX_VALUE) int defaultSelectedButton;

    public FixedButtonMenu(@Range(from = 0, to = Integer.MAX_VALUE) final int rows,
                           @Range(from = 0, to = Integer.MAX_VALUE) final int columns) {
        super(rows, columns);
        this.defaultSelectedButton = -1;
    }

    public void setButtonClickHandler(@Nullable final Consumer<Integer> clickHandler) {
        this.clickHandler = clickHandler;
    }

    @Nullable
    public Consumer<Integer> getButtonClickHandler() {
        return this.clickHandler;
    }

    public @Range(from = -1, to = Integer.MAX_VALUE) int getDefaultSelectedButton() {
        return defaultSelectedButton;
    }

    public void setDefaultSelectedButton(@Range(from = -1, to = Integer.MAX_VALUE) int defaultSelectedButton) {
        this.defaultSelectedButton = defaultSelectedButton;
    }

    @Override
    public void open(@NotNull ProtocolPlayer<?> user) {
        FixedButtonOpenInventory openInventory = new FixedButtonOpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        this.update(openInventory);
    }

    @Override
    public @NotNull PropertyPair[] properties() {
        return new PropertyPair[]{
                PropertyPair.of(0, this.defaultSelectedButton)
        };
    }
}
