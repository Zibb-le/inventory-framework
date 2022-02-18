package com.pepedevs.inventoryframework.protocol.item.meta;

import com.pepedevs.inventoryframework.protocol.item.objects.FireworkEffect;

public class ChargeMeta extends ItemMeta {

    private FireworkEffect fireworkEffect;

    protected ChargeMeta() {
        super();
    }

    public FireworkEffect getFireworkEffect() {
        return fireworkEffect;
    }

    public void setFireworkEffect(FireworkEffect fireworkEffect) {
        this.fireworkEffect = fireworkEffect;
    }
}
