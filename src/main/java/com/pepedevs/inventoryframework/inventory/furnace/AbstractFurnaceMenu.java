package com.pepedevs.inventoryframework.inventory.furnace;

import com.pepedevs.inventoryframework.NamedMenu;
import net.kyori.adventure.text.Component;

public abstract class AbstractFurnaceMenu extends NamedMenu {

    public AbstractFurnaceMenu(Component title) {
        super(1,3, title);
    }


}
