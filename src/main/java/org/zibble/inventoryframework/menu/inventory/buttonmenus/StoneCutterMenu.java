package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.NamedButtonMenu;
import net.kyori.adventure.text.Component;

public class StoneCutterMenu extends NamedButtonMenu {

    public StoneCutterMenu(int buttonRows, Component title) {
        super(1, 1, buttonRows, 4, title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.STONE_CUTTER;
    }

}
