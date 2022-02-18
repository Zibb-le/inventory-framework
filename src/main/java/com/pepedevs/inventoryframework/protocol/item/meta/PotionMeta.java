package com.pepedevs.inventoryframework.protocol.item.meta;

import com.pepedevs.inventoryframework.protocol.item.objects.PotionEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PotionMeta extends ItemMeta {

    private List<PotionEffect> potionEffects;

    protected PotionMeta() {
        super();
        this.potionEffects = new ArrayList<>();
    }

    public List<PotionEffect> getPotionEffects() {
        return Collections.unmodifiableList(potionEffects);
    }

    public void setPotionEffects(List<PotionEffect> potionEffects) {
        this.potionEffects = potionEffects;
    }
}
