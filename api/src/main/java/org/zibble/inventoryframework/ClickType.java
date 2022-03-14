package org.zibble.inventoryframework;

import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;
import org.jetbrains.annotations.NotNull;

public enum ClickType {

    PICKUP(WrapperPlayClientClickWindow.WindowClickType.PICKUP),
    QUICK_MOVE(WrapperPlayClientClickWindow.WindowClickType.QUICK_MOVE),
    SWAP(WrapperPlayClientClickWindow.WindowClickType.SWAP),
    CLONE(WrapperPlayClientClickWindow.WindowClickType.CLONE),
    THROW(WrapperPlayClientClickWindow.WindowClickType.THROW),
    QUICK_CRAFT(WrapperPlayClientClickWindow.WindowClickType.QUICK_CRAFT),
    PICKUP_ALL(WrapperPlayClientClickWindow.WindowClickType.PICKUP_ALL);

    private @NotNull final WrapperPlayClientClickWindow.WindowClickType packetEventsType;

    ClickType(@NotNull WrapperPlayClientClickWindow.WindowClickType windowClickType) {
        this.packetEventsType = windowClickType;
    }

    @NotNull
    public static ClickType fromProtocol(@NotNull WrapperPlayClientClickWindow.WindowClickType windowClickType) {
        for(ClickType clickType : values()) {
            if(clickType.packetEventsType == windowClickType) return clickType;
        }
        throw new IllegalStateException("Unknown click type: " + windowClickType);
    }

    @NotNull
    public WrapperPlayClientClickWindow.WindowClickType asProtocol() {
        return packetEventsType;
    }

}
