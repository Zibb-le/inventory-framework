package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import com.github.retrooper.packetevents.protocol.player.User;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.FixedButtonMenu;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.nameable.NamedButtonMenu;
import net.kyori.adventure.text.Component;
import org.zibble.inventoryframework.menu.openinventory.FixedButtonOpenInventory;

public class StoneCutterMenu extends FixedButtonMenu {

    public StoneCutterMenu() {
        super(1, 1);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.STONE_CUTTER;
    }

    @Override
    public void open(User user) {
        FixedButtonOpenInventory openInventory = new FixedButtonOpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        openInventory.sendItems(this.getItems());
    }

}
