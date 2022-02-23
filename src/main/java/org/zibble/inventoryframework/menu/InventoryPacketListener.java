package org.zibble.inventoryframework.menu;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindowButton;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientCloseWindow;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSetSlot;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.ClickType;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;

public class InventoryPacketListener extends PacketListenerAbstract {

    public InventoryPacketListener() {
        super(PacketListenerPriority.HIGH);
    }

    @Override
    public void onPacketReceive(@NotNull final PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.CLICK_WINDOW) {
            WrapperPlayClientClickWindow packet = new WrapperPlayClientClickWindow(event);
            for (AbstractOpenInventory inv : Menu.OPEN_INVENTORIES.values()) {
                if (inv.windowId() == packet.getWindowId() && event.getUser().equals(inv.player().asProtocol())) {
                    inv.listener().onClick(packet.getSlot(), ClickType.fromProtocol(packet.getWindowClickType()));
                    WrapperPlayServerSetSlot setSlot = new WrapperPlayServerSetSlot(packet.getWindowId(), 0, packet.getSlot(), packet.getClickedItemStack());
                    event.getUser().sendPacket(setSlot);
                    WrapperPlayServerSetSlot setSlot2 = new WrapperPlayServerSetSlot(-1, 0, -1, null);
                    event.getUser().sendPacket(setSlot2);
                    inv.menu().update(inv.player());
                    event.setCancelled(true);
                    inv.player().updatePlayerInventory();
                }
            }
        } else if (event.getPacketType() == PacketType.Play.Client.CLOSE_WINDOW) {
            WrapperPlayClientCloseWindow packet = new WrapperPlayClientCloseWindow(event);
            for (AbstractOpenInventory inv : Menu.OPEN_INVENTORIES.values()) {
                if (inv.windowId() == packet.getWindowId() && event.getUser().equals(inv.player().asProtocol())) {
                    inv.listener().onClose();
                    event.setCancelled(true);
                }
            }
        } else if (event.getPacketType() == PacketType.Play.Client.CLICK_WINDOW_BUTTON) {
            WrapperPlayClientClickWindowButton packet = new WrapperPlayClientClickWindowButton(event);
            for (AbstractOpenInventory inv : Menu.OPEN_INVENTORIES.values()) {
                if (inv.windowId() == packet.getWindowId() && event.getUser().equals(inv.player().asProtocol())) {
                    inv.listener().onButtonClick(packet.getButtonId());
                    event.setCancelled(true);
                }
            }
        }
    }

}
