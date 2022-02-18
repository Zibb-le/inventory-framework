package com.pepedevs.inventoryframework.protocol.item.meta;

import java.awt.*;

public class LeatherArmorMeta extends ItemMeta {

    private Color color;

    protected LeatherArmorMeta() {
        super();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
