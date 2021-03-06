package org.zibble.inventoryframework.protocol.item.objects.enums;

import com.github.retrooper.packetevents.protocol.potion.PotionType;
import com.github.retrooper.packetevents.protocol.potion.PotionTypes;
import org.jetbrains.annotations.ApiStatus;

public enum EnumEffect {

    ABSORPTION(PotionTypes.ABSORPTION),
    BAD_OMEN(PotionTypes.BAD_OMEN),
    BLINDNESS(PotionTypes.BLINDNESS),
    CONDUIT_POWER(PotionTypes.CONDUIT_POWER),
    NAUSEA(PotionTypes.NAUSEA),
    RESISTANCE(PotionTypes.RESISTANCE),
    DOLPHINS_GRACE(PotionTypes.DOLPHINS_GRACE),
    HASTE(PotionTypes.HASTE),
    FIRE_RESISTANCE(PotionTypes.FIRE_RESISTANCE),
    GLOWING(PotionTypes.GLOWING),
    INSTANT_DAMAGE(PotionTypes.INSTANT_DAMAGE),
    INSTANT_HEALTH(PotionTypes.INSTANT_HEALTH),
    HEALTH_BOOST(PotionTypes.HEALTH_BOOST),
    HERO_OF_THE_VILLAGE(PotionTypes.HERO_OF_THE_VILLAGE),
    HUNGER(PotionTypes.HUNGER),
    STRENGTH(PotionTypes.STRENGTH),
    INVISIBILITY(PotionTypes.INVISIBILITY),
    JUMP_BOOST(PotionTypes.JUMP_BOOST),
    LEVITATION(PotionTypes.LEVITATION),
    LUCK(PotionTypes.LUCK),
    NIGHT_VISION(PotionTypes.NIGHT_VISION),
    POISON(PotionTypes.POISON),
    REGENERATION(PotionTypes.REGENERATION),
    SATURATION(PotionTypes.SATURATION),
    SLOWNESS(PotionTypes.SLOWNESS),
    MINER_FATIGUE(PotionTypes.MINING_FATIGUE),
    SLOW_FALLING(PotionTypes.SLOW_FALLING),
    SPEED(PotionTypes.SPEED),
    UNLUCK(PotionTypes.UNLUCK), //LMAO
    WATER_BREATHING(PotionTypes.WATER_BREATHING),
    WEAKNESS(PotionTypes.WEAKNESS),
    WITHER(PotionTypes.WITHER),

    ;

    private final PotionType protocol;

    EnumEffect(PotionType potionType) {
        this.protocol = potionType;
    }

    @ApiStatus.Internal
    public PotionType asProtocol() {
        return protocol;
    }

    public int getID() {
        return this.protocol.getId();
    }
}
