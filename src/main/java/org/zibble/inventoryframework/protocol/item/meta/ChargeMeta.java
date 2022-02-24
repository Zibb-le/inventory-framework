package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.protocol.item.objects.FireworkEffect;

public class ChargeMeta extends ItemMeta {

    private static final String EXPLOSION = "Explosion";

    private FireworkEffect fireworkEffect;

    protected ChargeMeta() {
        super();
    }

    public FireworkEffect getEffect() {
        return fireworkEffect;
    }

    public void setEffect(FireworkEffect fireworkEffect) {
        this.fireworkEffect = fireworkEffect;
    }

    @Override
    public void applyTo(@NotNull NBTCompound compound) {
        super.applyTo(compound);
        if (this.fireworkEffect != null) {
            NBTCompound nbtCompound = new NBTCompound();
            MetaUtil.applyFireworkEffect(this.fireworkEffect, nbtCompound);
            compound.setTag(EXPLOSION, nbtCompound);
        }
    }
}
