package com.pepedevs.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.*;
import com.pepedevs.inventoryframework.protocol.item.objects.FireworkEffect;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FireworkMeta extends ItemMeta {

    private static final String FIREWORKS = "Fireworks";
    private static final String EXPLOSION_FLICKER = "Flicker";
    private static final String EXPLOSION_TRAIL = "Trail";
    private static final String EXPLOSION_COLORS = "Colors";
    private static final String EXPLOSION_FADE = "FadeColors";
    private static final String EXPLOSION_TYPE = "Type";
    private static final String EXPLOSIONS = "Explosions";
    private static final String FLIGHT = "Flight";

    private int power;
    private List<FireworkEffect> effects;

    protected FireworkMeta() {
        this.power = 0;
        this.effects = new ArrayList<>();
    }

    public List<FireworkEffect> getEffects() {
        return Collections.unmodifiableList(effects);
    }

    public void setEffects(List<FireworkEffect> effects) {
        this.effects = effects;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
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
                int[] colors = new int[effect.getColors().size()];
                for (int i = 0; i < effect.getColors().size(); i++) {
                    colors[i] = effect.getColors().get(i).getRGB();
                }
                compoundTag.setTag(EXPLOSION_COLORS, new NBTIntArray(colors));
                int[] fadeColors = new int[effect.getFadeColors().size()];
                for (int i = 0; i < effect.getFadeColors().size(); i++) {
                    fadeColors[i] = effect.getFadeColors().get(i).getRGB();
                }
                compoundTag.setTag(EXPLOSION_FADE, new NBTIntArray(fadeColors));
                compoundTag.setTag(EXPLOSION_TYPE, new NBTByte((byte) effect.getType().ordinal()));
            }
            if (effects.size() > 0) compound.setTag(EXPLOSIONS, firework);
            if (this.power != 0) firework.setTag(FLIGHT, new NBTByte((byte) this.power));
        }
    }

}
