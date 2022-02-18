package com.pepedevs.inventoryframework.protocol.item.objects.enums;

import com.github.retrooper.packetevents.protocol.item.enchantment.type.EnchantmentType;
import com.github.retrooper.packetevents.protocol.item.enchantment.type.EnchantmentTypes;

public enum Enchantment {

    POWER(EnchantmentTypes.POWER_ARROWS),
    FLAMING(EnchantmentTypes.FLAMING_ARROWS),
    INFINITY(EnchantmentTypes.INFINITY_ARROWS),
    PUNCH(EnchantmentTypes.PUNCH_ARROWS),
    CURSE_OF_BINDING(EnchantmentTypes.BINDING_CURSE),
    CHANNELING(EnchantmentTypes.CHANNELING),
    SHARPNESS(EnchantmentTypes.SHARPNESS),
    BANE_OF_ARTHROPODS(EnchantmentTypes.BANE_OF_ARTHROPODS),
    SMITE(EnchantmentTypes.SMITE),
    DEPTH_STRIDER(EnchantmentTypes.DEPTH_STRIDER),
    EFFICIENCY(EnchantmentTypes.BLOCK_EFFICIENCY),
    UNBREAKING(EnchantmentTypes.UNBREAKING),
    FIRE_ASPECT(EnchantmentTypes.FIRE_ASPECT),
    FROST_WALKER(EnchantmentTypes.FROST_WALKER),
    IMPALING(EnchantmentTypes.IMPALING),
    SOUL_SPEED(EnchantmentTypes.SOUL_SPEED),
    KNOCKBACK(EnchantmentTypes.KNOCKBACK),
    FORTUNE(EnchantmentTypes.BLOCK_FORTUNE),
    LOOTING(EnchantmentTypes.MOB_LOOTING),
    LOYALTY(EnchantmentTypes.LOYALTY),
    LUCK(EnchantmentTypes.FISHING_LUCK),
    LURE(EnchantmentTypes.FISHING_SPEED),
    MENDING(EnchantmentTypes.MENDING),
    MULTISHOT(EnchantmentTypes.MULTISHOT),
    RESPIRATION(EnchantmentTypes.RESPIRATION),
    PIERCING(EnchantmentTypes.PIERCING),
    PROTECTION(EnchantmentTypes.ALL_DAMAGE_PROTECTION),
    BLAST_PROTECTION(EnchantmentTypes.BLAST_PROTECTION),
    FEATHER_FALLING(EnchantmentTypes.FALL_PROTECTION),
    FIRE_PROTECTION(EnchantmentTypes.FIRE_PROTECTION),
    PROJECTILE_PROTECTION(EnchantmentTypes.PROJECTILE_PROTECTION),
    QUICK_CHARGE(EnchantmentTypes.QUICK_CHARGE),
    RIPTIDE(EnchantmentTypes.RIPTIDE),
    SILK_TOUCH(EnchantmentTypes.SILK_TOUCH),
    SWEEPING_EDGE(EnchantmentTypes.SWEEPING_EDGE),
    THORNS(EnchantmentTypes.THORNS),
    CURSE_OF_VANISHING(EnchantmentTypes.VANISHING_CURSE),
    AQUA_AFFINITY(EnchantmentTypes.AQUA_AFFINITY);

    private final EnchantmentType packetEventsType;

    Enchantment(EnchantmentType enchantmentType) {
        this.packetEventsType = enchantmentType;
    }

    public EnchantmentType getPacketType() {
        return packetEventsType;
    }

    public int getID() {
        return this.packetEventsType.getId();
    }
}
