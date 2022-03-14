package org.zibble.inventoryframework.protocol.item.objects.enums;

public enum EnumDye {

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

    EnumDye() {
        this.ID = (byte) (15 - this.ordinal());
    }

    public byte getID() {
        return ID;
    }
}
