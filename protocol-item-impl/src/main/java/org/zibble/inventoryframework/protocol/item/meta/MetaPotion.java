package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.*;
import org.zibble.inventoryframework.protocol.item.objects.EffectPotion;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MetaPotion extends MetaItem {

    private static final String AMPLIFIER = "Amplifier";
    private static final String AMBIENT = "Ambient";
    private static final String DURATION = "Duration";
    private static final String SHOW_PARTICLES = "ShowParticles";
    private static final String POTION_EFFECTS = "CustomPotionEffects";
    private static final String ID = "Id";

    private List<EffectPotion> potionEffects;

    protected MetaPotion() {
        this.potionEffects = new ArrayList<>();
    }

    public List<EffectPotion> getPotionEffects() {
        return Collections.unmodifiableList(potionEffects);
    }

    public void setPotionEffects(List<EffectPotion> potionEffects) {
        this.potionEffects = potionEffects;
    }

    public void addPotionEffect(EffectPotion potionEffect) {
        this.potionEffects.add(potionEffect);
    }

    public void removePotionEffect(EffectPotion potionEffect) {
        this.potionEffects.remove(potionEffect);
    }

    @ApiStatus.Internal
    @Override
    public void applyTo(@NotNull NBTCompound compound) {
        super.applyTo(compound);
        if (this.potionEffects.isEmpty()) return;
        NBTList<NBTCompound> list = new NBTList<>(NBTType.COMPOUND);
        for (EffectPotion potionEffect : this.potionEffects) {
            NBTCompound potion = new NBTCompound();
            potion.setTag(ID, new NBTByte((byte) potionEffect.getType().getId()));
            potion.setTag(AMPLIFIER, new NBTByte((byte) potionEffect.getAmplifier()));
            potion.setTag(DURATION, new NBTInt((int) (potionEffect.getDuration().toMillis() / 50)));
            potion.setTag(AMBIENT, new NBTByte((byte) (potionEffect.isAmbient() ? 1 : 0)));
            potion.setTag(SHOW_PARTICLES, new NBTByte((byte) (potionEffect.hasParticles() ? 1 : 0)));
            list.addTag(potion);
        }
        compound.setTag(POTION_EFFECTS, list);
    }
}
