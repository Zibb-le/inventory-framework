package org.zibble.inventoryframework;

import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;

public enum ClickType {

    PICKUP(WrapperPlayClientClickWindow.WindowClickType.PICKUP),
    QUICK_MOVE(WrapperPlayClientClickWindow.WindowClickType.QUICK_MOVE),
    SWAP(WrapperPlayClientClickWindow.WindowClickType.SWAP),
    CLONE(WrapperPlayClientClickWindow.WindowClickType.CLONE),
    THROW(WrapperPlayClientClickWindow.WindowClickType.THROW),
    QUICK_CRAFT(WrapperPlayClientClickWindow.WindowClickType.QUICK_CRAFT),
    PICKUP_ALL(WrapperPlayClientClickWindow.WindowClickType.PICKUP_ALL);

    private final WrapperPlayClientClickWindow.WindowClickType packetEventsType;

    ClickType(WrapperPlayClientClickWindow.WindowClickType windowClickType) {
        this.packetEventsType = windowClickType;
    }

    public static ClickType fromPacketType(WrapperPlayClientClickWindow.WindowClickType windowClickType) {
        for(ClickType clickType : values()) {
            if(clickType.packetEventsType == windowClickType) return clickType;
        }
        return null;
    }

    public WrapperPlayClientClickWindow.WindowClickType getPacketType() {
        return packetEventsType;
    }

}
