package org.zibble.inventoryframework.menu.openinventory;

import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerOpenWindow;
import net.kyori.adventure.text.Component;
import org.zibble.inventoryframework.ClickType;
import org.zibble.inventoryframework.InventoryListener;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.nameable.NamedMenu;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;
import org.zibble.inventoryframework.protocol.item.ItemStack;

public class OpenInventory extends AbstractOpenInventory {

    private final InventoryListener listener;

    public OpenInventory(ProtocolPlayer<?> user, Menu menu) {
        super(user, menu);
        this.listener = new InventoryListener() {
            @Override
            public void onOpen() {
                if (menu.onOpen() != null) {
                    menu.onOpen().accept(user);
                }
            }

            @Override
            public void onClose() {
                if (menu.onClose() != null) {
                    menu.onClose().accept(user);
                }
            }

            @Override
            public void onClick(int slot, ClickType clickType) {
                if (slot < 0) return;
                MenuItem<ItemStack> item = menu.asList().get(slot);
                if (item == null || item.clickAction() == null) return;
                item.clickAction().onClick(user, clickType);
            }
        };
    }

    @Override
    public InventoryListener listener() {
        return this.listener;
    }

    @Override
    public void show() {
        Component title = this.menu instanceof NamedMenu ? ((NamedMenu) this.menu).title() : Component.empty();
        this.windowId = this.nextContainerId();
        WrapperPlayServerOpenWindow wrapper = new WrapperPlayServerOpenWindow(
                this.windowId,
                this.type().legacyId(),
                title,
                this.type() == InventoryType.CHEST ? menu.getColumns() * menu.getRows() : 0,
                0);
        this.user.sendPacket(wrapper);
        this.listener().onOpen();
    }
}
