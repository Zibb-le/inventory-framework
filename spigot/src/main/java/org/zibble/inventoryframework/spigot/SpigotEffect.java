package org.zibble.inventoryframework.spigot;

import com.github.retrooper.packetevents.protocol.potion.PotionType;
import io.github.retrooper.packetevents.util.SpigotDataHelper;
import org.bukkit.potion.PotionEffectType;
import org.zibble.inventoryframework.protocol.Effect;

public class SpigotEffect implements Effect {

    private final org.bukkit.potion.PotionEffectType potionType;

    public SpigotEffect(org.bukkit.potion.PotionEffectType potionType) {
        this.potionType = potionType;
    }

    public PotionEffectType getPotionType() {
        return potionType;
    }

    @Override
    public int getId() {
        return this.potionType.getId();
    }

    @Override
    public PotionType asProtocol() {
        return SpigotDataHelper.fromBukkitPotionEffectType(this.potionType);
    }

}
