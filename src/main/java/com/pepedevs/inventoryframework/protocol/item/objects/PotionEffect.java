package com.pepedevs.inventoryframework.protocol.item.objects;

import com.pepedevs.inventoryframework.protocol.item.objects.enums.PotionEffectType;

import java.time.Duration;

public class PotionEffect {

    private final PotionEffectType effectType;
    private final int amplifier;
    private final Duration duration;
    private final boolean ambient;
    private final boolean particles;

    public PotionEffect(PotionEffectType effectType, int amplifier, Duration duration, boolean ambient, boolean particles) {
        this.effectType = effectType;
        this.amplifier = amplifier;
        this.duration = duration;
        this.ambient = ambient;
        this.particles = particles;
    }

    public PotionEffectType getEffectType() {
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

    public boolean isParticles() {
        return particles;
    }
}
