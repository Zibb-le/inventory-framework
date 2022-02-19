package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.NamedButtonMenu;
import net.kyori.adventure.text.Component;

public class MerchantMenu extends NamedButtonMenu {

    public MerchantMenu(int buttonRows, Component title) {
        super(1, 3, buttonRows, 1, title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.MERCHANT_GUI;
    }
}
