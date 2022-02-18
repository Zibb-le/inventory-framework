package com.pepedevs.inventoryframework.protocol.item.meta;

import java.awt.*;

public class LeatherArmorMeta extends ItemMeta {

    private Color color;

    protected LeatherArmorMeta() {
        super();
    }

    public Color getColor() {
        return new Color(this.color.getColorSpace(), this.color.getComponents(new float[0]), this.color.getAlpha());
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
