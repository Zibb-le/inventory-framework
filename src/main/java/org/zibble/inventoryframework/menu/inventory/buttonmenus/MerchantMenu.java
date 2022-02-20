package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import com.github.retrooper.packetevents.protocol.player.User;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.nameable.NamedButtonMenu;
import net.kyori.adventure.text.Component;
import org.zibble.inventoryframework.menu.openinventory.ButtonOpenInventory;
import org.zibble.inventoryframework.protocol.item.objects.buttons.TradeOption;

public class MerchantMenu extends NamedButtonMenu<TradeOption> {

    public MerchantMenu(int buttonRows, Component title) {
        super(1, 3, buttonRows, 1, title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.MERCHANT_GUI;
    }

    @Override
    public void open(User user) {
        ButtonOpenInventory openInventory = new ButtonOpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        openInventory.sendItems(this.getItems());
    }
}
