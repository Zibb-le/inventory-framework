package org.zibble.inventoryframework.protocol.item.objects;

import org.zibble.inventoryframework.protocol.item.objects.enums.EffectType;

import java.time.Duration;

public class PotionEffect {

    private final EffectType effectType;
    private final int amplifier;
    private final Duration duration;
    private final boolean ambient;
    private final boolean particles;

    public PotionEffect(EffectType effectType, int amplifier, Duration duration, boolean ambient, boolean particles) {
        this.effectType = effectType;
        this.amplifier = amplifier;
        this.duration = duration;
        this.ambient = ambient;
        this.particles = particles;
    }

    public EffectType getType() {
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
