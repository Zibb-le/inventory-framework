package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.NBTByte;
import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.github.retrooper.packetevents.protocol.nbt.NBTList;
import com.github.retrooper.packetevents.protocol.nbt.NBTType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.protocol.item.objects.EffectFirework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MetaFirework extends MetaItem {

    private static final String FIREWORKS = "Fireworks";
    private static final String EXPLOSIONS = "Explosions";
    private static final String FLIGHT = "Flight";

    private int power;
    private List<EffectFirework> effects;

    protected MetaFirework() {
        this.power = 0;
        this.effects = new ArrayList<>();
    }

    public List<EffectFirework> getEffects() {
        return Collections.unmodifiableList(effects);
    }

    public void setEffects(List<EffectFirework> effects) {
        this.effects = effects;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @ApiStatus.Internal
    @Override
    public void applyTo(@NotNull NBTCompound compound) {
        super.applyTo(compound);

        if (power == 0 || this.effects.isEmpty()) {
            return;
        }

        NBTCompound firework = compound.getCompoundTagOrNull(FIREWORKS);
        if (firework == null)
            compound.setTag(FIREWORKS, firework = new NBTCompound());

        if (!this.effects.isEmpty()) {
            NBTList<NBTCompound> effects = new NBTList<>(NBTType.COMPOUND);
            for (EffectFirework effect : this.effects) {
                NBTCompound compoundTag = new NBTCompound();
                MetaUtil.applyFireworkEffect(effect, compoundTag);
                effects.addTag(compoundTag);
            }
            if (effects.size() > 0) compound.setTag(EXPLOSIONS, firework);
            if (this.power != 0) firework.setTag(FLIGHT, new NBTByte((byte) this.power));
        }
    }

}
