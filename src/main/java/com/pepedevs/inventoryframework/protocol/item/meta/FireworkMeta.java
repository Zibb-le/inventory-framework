package com.pepedevs.inventoryframework.protocol.item.meta;

import com.pepedevs.inventoryframework.protocol.item.objects.FireworkEffect;

import java.util.Collections;
import java.util.List;

public class FireworkMeta extends ItemMeta {

    private List<FireworkEffect> effects;

    protected FireworkMeta() {
        super();
    }

    public List<FireworkEffect> getEffects() {
        return Collections.unmodifiableList(effects);
    }

    public void setEffects(List<FireworkEffect> effects) {
        this.effects = effects;
    }
}
