package org.zibble.inventoryframework.protocol.item.objects.enums;

public enum DyeColor {

    WHITE,
    ORANGE,
    MAGENTA,
    LIGHT_BLUE,
    YELLOW,
    LIME,
    PINK,
    GRAY,
    SILVER,
    CYAN,
    PURPLE,
    BLUE,
    BROWN,
    GREEN,
    RED,
    BLACK,
    ;

    private final byte ID;

    DyeColor() {
        this.ID = (byte) (15 - this.ordinal());
    }

    public byte id() {
        return ID;
    }
}
