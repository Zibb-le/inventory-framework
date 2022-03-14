package org.zibble.inventoryframework.protocol.item.objects;

import org.zibble.inventoryframework.protocol.item.objects.enums.EnumEffect;

import java.time.Duration;

public class EffectPotion {

    private final EnumEffect effectType;
    private final int amplifier;
    private final Duration duration;
    private final boolean ambient;
    private final boolean particles;

    public EffectPotion(EnumEffect effectType, int amplifier, Duration duration, boolean ambient, boolean particles) {
        this.effectType = effectType;
        this.amplifier = amplifier;
        this.duration = duration;
        this.ambient = ambient;
        this.particles = particles;
    }

    public EnumEffect getType() {
        return effectType;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public Duration getDuration() {
        return duration;
    }

    public boolean isAmbient() {
        return ambient;
    }

    public boolean hasParticles() {
        return particles;
    }

}
