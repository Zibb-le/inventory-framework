package org.zibble.inventoryframework.menu.openinventory;

import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerOpenWindow;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.NamedMenu;
import net.kyori.adventure.text.Component;
import org.zibble.inventoryframework.ClickType;
import org.zibble.inventoryframework.InventoryListener;
import org.zibble.inventoryframework.protocol.item.ItemStack;

public class OpenInventory extends AbstractOpenInventory {

    private final InventoryListener listener;

    public OpenInventory(User user, Menu menu) {
        super(user, menu);
        this.listener = new InventoryListener() {
            @Override
            public void onOpen() {
                if (menu.getOnOpen() != null) {
                    menu.getOnOpen().accept(user);
                }
            }

            @Override
            public void onClose() {
                if (menu.getOnClose() != null) {
                    menu.getOnClose().accept(user);
                }
            }

            @Override
            public void onClick(int slot, ClickType clickType) {
                if (slot < 0) return;
                MenuItem<ItemStack> item = menu.getAsList().get(slot);
                if (item == null || item.getClickAction() == null) return;
                item.getClickAction().onClick(user, clickType);
            }
        };
    }

    @Override
    public InventoryListener getInventoryListener() {
        return this.listener;
    }

    @Override
    public void show() {
        Component title = this.menu instanceof NamedMenu ? ((NamedMenu) this.menu).getTitle() : Component.empty();
        this.windowId = this.nextContainerId();
        WrapperPlayServerOpenWindow wrapper = new WrapperPlayServerOpenWindow(
                this.windowId,
                this.getInventoryType().getLegacyId(),
                title,
                this.getInventoryType() == InventoryType.CHEST ? menu.getColumns() * menu.getRows() : 0,
                0);
        this.user.sendPacket(wrapper);
        this.getInventoryListener().onOpen();
    }
}
