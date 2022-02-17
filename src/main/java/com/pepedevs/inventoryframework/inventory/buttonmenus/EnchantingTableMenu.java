package com.pepedevs.inventoryframework.inventory.buttonmenus;

import com.pepedevs.inventoryframework.InventoryType;
import com.pepedevs.inventoryframework.NamedButtonMenu;
import net.kyori.adventure.text.Component;

public class EnchantingTableMenu extends NamedButtonMenu {

    public EnchantingTableMenu(Component title) {
        super(1, 2, 3, 1, title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.ENCHANTING_TABLE;
    }

}