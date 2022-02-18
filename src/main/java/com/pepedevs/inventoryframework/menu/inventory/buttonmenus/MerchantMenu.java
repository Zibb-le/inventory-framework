package com.pepedevs.inventoryframework.menu.inventory.buttonmenus;

import com.pepedevs.inventoryframework.InventoryType;
import com.pepedevs.inventoryframework.menu.NamedButtonMenu;
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
