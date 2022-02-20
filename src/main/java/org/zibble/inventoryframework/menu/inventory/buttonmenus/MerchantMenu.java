package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import com.github.retrooper.packetevents.protocol.player.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.nameable.NamedButtonMenu;
import net.kyori.adventure.text.Component;
import org.zibble.inventoryframework.menu.openinventory.ButtonOpenInventory;
import org.zibble.inventoryframework.protocol.item.objects.buttons.TradeOption;

public class MerchantMenu extends NamedButtonMenu<TradeOption> {

    public MerchantMenu(@Range(from = 0, to = Integer.MAX_VALUE) final int buttonRows, @Nullable final Component title) {
        super(1, 3, buttonRows, 1, title);
    }

    @Override
    @NotNull
    public InventoryType getInventoryType() {
        return InventoryType.MERCHANT_GUI;
    }

    @Override
    public void open(@NotNull final User user) {
        ButtonOpenInventory openInventory = new ButtonOpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        openInventory.sendItems(this.getItems());
    }
}
