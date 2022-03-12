package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.github.retrooper.packetevents.protocol.nbt.NBTInt;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class MetaLeatherArmor extends MetaItem {

    private static final String COLOR = "color";

    private Color color;

    protected MetaLeatherArmor() {
        super();
    }

    public Color getColor() {
        return new Color(this.color.getColorSpace(), this.color.getComponents(new float[0]), this.color.getAlpha());
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @ApiStatus.Internal
    @Override
    public void applyTo(@NotNull NBTCompound compound) {
        super.applyTo(compound);
        if (this.color == null) return;
        MetaUtil.applyDisplayTag(COLOR, new NBTInt(this.color.getRGB()), compound);
    }
}
