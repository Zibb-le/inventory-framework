package org.zibble.inventoryframework.protocol.item.objects.enums;

import com.github.retrooper.packetevents.protocol.potion.PotionType;
import com.github.retrooper.packetevents.protocol.potion.PotionTypes;

public enum PotionEffectType {

    ABSORPTION(PotionTypes.ABSORPTION),
    BAD_OMEN(PotionTypes.BAD_OMEN),
    BLINDNESS(PotionTypes.BLINDNESS),
    CONDUIT_POWER(PotionTypes.CONDUIT_POWER),
    CONFUSION(PotionTypes.NAUSEA),
    DAMAGE_RESISTANCE(PotionTypes.RESISTANCE),
    DOLPHINS_GRACE(PotionTypes.DOLPHINS_GRACE),
    FAST_DIGGING(PotionTypes.HASTE),
    FIRE_RESISTANCE(PotionTypes.FIRE_RESISTANCE),
    GLOWING(PotionTypes.GLOWING),
    HARM(PotionTypes.INSTANT_DAMAGE),
    HEAL(PotionTypes.INSTANT_HEALTH),
    HEALTH_BOOST(PotionTypes.HEALTH_BOOST),
    HERO_OF_THE_VILLAGE(PotionTypes.HERO_OF_THE_VILLAGE),
    HUNGER(PotionTypes.HUNGER),
    INCREASE_DAMAGE(PotionTypes.STRENGTH),
    INVISIBILITY(PotionTypes.INVISIBILITY),
    JUMP(PotionTypes.JUMP_BOOST),
    LEVITATION(PotionTypes.LEVITATION),
    LUCK(PotionTypes.LUCK),
    NIGHT_VISION(PotionTypes.NIGHT_VISION),
    POISON(PotionTypes.POISON),
    REGENERATION(PotionTypes.REGENERATION),
    SATURATION(PotionTypes.SATURATION),
    SLOW(PotionTypes.SLOWNESS),
    SLOW_DIGGING(PotionTypes.MINING_FATIGUE),
    SLOW_FALLING(PotionTypes.SLOW_FALLING),
    SPEED(PotionTypes.SPEED),
    UNLUCK(PotionTypes.UNLUCK),
    WATER_BREATHING(PotionTypes.WATER_BREATHING),
    WEAKNESS(PotionTypes.WEAKNESS),
    WITHER(PotionTypes.WITHER),
    ;

    private final PotionType potionType;

    PotionEffectType(PotionType potionType) {
        this.potionType = potionType;
    }

    public PotionType getPacketType() {
        return potionType;
    }

    public int getID() {
        return this.potionType.getId();
    }
}
