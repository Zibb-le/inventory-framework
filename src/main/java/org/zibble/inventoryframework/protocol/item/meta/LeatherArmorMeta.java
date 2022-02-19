package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.github.retrooper.packetevents.protocol.nbt.NBTInt;

import java.awt.*;

public class LeatherArmorMeta extends ItemMeta {

    private static final String COLOR = "color";

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

    @Override
    public void applyTo(NBTCompound compound) {
        super.applyTo(compound);
        if (this.color == null) return;
        MetaUtil.applyDisplayTag(COLOR, new NBTInt(this.color.getRGB()), compound);
    }
}
