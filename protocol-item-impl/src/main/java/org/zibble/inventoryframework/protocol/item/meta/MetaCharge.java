package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.protocol.item.objects.EffectFirework;

public class MetaCharge extends MetaItem {

    private static final String EXPLOSION = "Explosion";

    private EffectFirework fireworkEffect;

    protected MetaCharge() {
        super();
    }

    public EffectFirework getEffect() {
        return fireworkEffect;
    }

    public void setEffect(EffectFirework fireworkEffect) {
        this.fireworkEffect = fireworkEffect;
    }

    @ApiStatus.Internal
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
