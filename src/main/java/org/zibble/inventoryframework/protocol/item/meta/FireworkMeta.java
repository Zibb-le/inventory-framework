package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.NBTByte;
import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.github.retrooper.packetevents.protocol.nbt.NBTList;
import com.github.retrooper.packetevents.protocol.nbt.NBTType;
import org.zibble.inventoryframework.protocol.item.objects.FireworkEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FireworkMeta extends ItemMeta {

    private static final String FIREWORKS = "Fireworks";
    private static final String EXPLOSIONS = "Explosions";
    private static final String FLIGHT = "Flight";

    private int power;
    private List<FireworkEffect> effects;

    protected FireworkMeta() {
        this.power = 0;
        this.effects = new ArrayList<>();
    }

    public List<FireworkEffect> effects() {
        return Collections.unmodifiableList(effects);
    }

    public void effects(List<FireworkEffect> effects) {
        this.effects = effects;
    }

    public int power() {
        return power;
    }

    public void power(int power) {
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
                MetaUtil.applyFireworkEffect(effect, compoundTag);
                effects.addTag(compoundTag);
            }
            if (effects.size() > 0) compound.setTag(EXPLOSIONS, firework);
            if (this.power != 0) firework.setTag(FLIGHT, new NBTByte((byte) this.power));
        }
    }

}
