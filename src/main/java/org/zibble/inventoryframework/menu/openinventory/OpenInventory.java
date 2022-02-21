package org.zibble.inventoryframework.menu.openinventory;

import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerOpenWindow;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.ClickType;
import org.zibble.inventoryframework.InventoryListener;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.nameable.NamedMenu;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;
import org.zibble.inventoryframework.protocol.item.ItemStack;

import java.util.function.Consumer;

public class OpenInventory extends AbstractOpenInventory {

    private final InventoryListener listener;

    public OpenInventory(ProtocolPlayer<?> user, Menu menu) {
        super(user, menu);
        this.listener = new InventoryListener() {
            @Override
            public void onOpen() {
                Consumer<ProtocolPlayer<?>> open = menu.onOpen();
                if (open != null) open.accept(user);
            }

            @Override
            public void onClose() {
                Consumer<ProtocolPlayer<?>> close = menu.onClose();
                if (close != null) close.accept(user);
            }

            @Override
            public void onClick(int slot, @NotNull ClickType clickType) {
                if (slot < 0 || slot >= menu.getColumns() * menu.getRows()) return;
                MenuItem<ItemStack> item = menu.asList().get(slot);
                if (item == null || item.clickAction() == null) return;
                item.clickAction().onClick(user, clickType);
            }
        };
    }

    @Override
    public @NotNull InventoryListener listener() {
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
