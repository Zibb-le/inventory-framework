package com.pepedevs.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.NBTByte;
import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.github.retrooper.packetevents.protocol.nbt.NBTList;
import com.github.retrooper.packetevents.protocol.nbt.NBTType;
import com.pepedevs.inventoryframework.protocol.item.objects.FireworkEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FireworkMeta extends ItemMeta {

    public static final String FIREWORKS = "Fireworks";
    public static final String EXPLOSION_FLICKER = "Flicker";
    public static final String EXPLOSION_TRAIL = "Trail";

    private int power;
    private List<FireworkEffect> effects;

    protected FireworkMeta() {
        this.effects = new ArrayList<>();
    }

    public List<FireworkEffect> getEffects() {
        return Collections.unmodifiableList(effects);
    }

    public void setEffects(List<FireworkEffect> effects) {
        this.effects = effects;
    }

    @Override
    public void applyTo(NBTCompound compound) {
        super.applyTo(compound);

        if (power == 0 || this.effects.isEmpty()) {
            return;
        }

        NBTCompound firework = compound.getCompoundTagOrNull(FIREWORKS);
        if (firework == null)
            compound.setTag(FIREWORKS, firework = new NBTCompound());

        if (!this.effects.isEmpty()) {
            NBTList<NBTCompound> effects = new NBTList<>(NBTType.COMPOUND);
            for (FireworkEffect effect : this.effects) {
                NBTCompound compoundTag = new NBTCompound();
                if (effect.hasFlicker()) {
                    compoundTag.setTag(EXPLOSION_FLICKER, new NBTByte((byte) 1));
                }
                if (effect.hasTrail()) {
                    compoundTag.setTag(EXPLOSION_TRAIL, new NBTByte((byte) 1));
                }
            }
        }
    }

}
