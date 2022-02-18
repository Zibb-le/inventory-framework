package com.pepedevs.inventoryframework.protocol.item.objects;

import com.pepedevs.inventoryframework.protocol.item.objects.enums.PotionEffectType;

import java.time.Duration;

public class PotionEffect {

    private PotionEffectType effectType;
    private int amplifier;
    private Duration duration;
    private boolean ambient;
    private boolean particles;

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

    public void setEffectType(PotionEffectType effectType) {
        this.effectType = effectType;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public boolean isAmbient() {
        return ambient;
    }

    public void setAmbient(boolean ambient) {
        this.ambient = ambient;
    }

    public boolean isParticles() {
        return particles;
    }

    public void setParticles(boolean particles) {
        this.particles = particles;
    }
}
