package org.zibble.inventoryframework.menu;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.menu.openinventory.FixedButtonOpenInventory;
import org.zibble.inventoryframework.menu.property.DataPropertyHolder;
import org.zibble.inventoryframework.menu.property.PropertyPair;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;

import java.util.function.Consumer;

/**
 * An extension of {@link Menu} that can also contain fixed, non-changeable & client-side-determined buttons.
 */
public abstract class FixedButtonMenu extends Menu implements DataPropertyHolder {

    private @Nullable Consumer<Integer> clickHandler;
    private @Range(from = -1, to = Integer.MAX_VALUE) int defaultSelectedButton;

    public FixedButtonMenu(@Range(from = 0, to = Integer.MAX_VALUE) final int rows,
                           @Range(from = 0, to = Integer.MAX_VALUE) final int columns) {
        super(rows, columns);
        this.defaultSelectedButton = -1;
    }

    /**
     * Sets the handler for when a button is clicked.
     * @param clickHandler The handler handling tasks to run which button/slot was clicked by the user.
     */
    public void setButtonClickHandler(@Nullable final Consumer<Integer> clickHandler) {
        this.clickHandler = clickHandler;
    }

    @ApiStatus.Internal
    @Nullable
    public Consumer<Integer> getButtonClickHandler() {
        return this.clickHandler;
    }

    /**
     * @return The default selected button for this menu.
     */
    @ApiStatus.Internal
    public @Range(from = -1, to = Integer.MAX_VALUE) int getDefaultSelectedButton() {
        return defaultSelectedButton;
    }

    /**
     * @param defaultSelectedButton The button to be selected as default for this menu.
     */
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

    @ApiStatus.Internal
    @Override
    public @NotNull PropertyPair[] properties() {
        return new PropertyPair[]{
                PropertyPair.of(0, this.defaultSelectedButton)
        };
    }
}
