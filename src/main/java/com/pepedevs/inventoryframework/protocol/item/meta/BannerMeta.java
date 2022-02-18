package com.pepedevs.inventoryframework.protocol.item.meta;

import com.pepedevs.inventoryframework.protocol.item.objects.DyeColor;
import com.pepedevs.inventoryframework.protocol.item.objects.Pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BannerMeta extends ItemMeta {

    private DyeColor baseColor;
    private List<Pattern> patterns;

    protected BannerMeta() {
        super();
        this.patterns = new ArrayList<>();
    }

    public DyeColor getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(DyeColor baseColor) {
        this.baseColor = baseColor;
    }

    public List<Pattern> getPatterns() {
        return Collections.unmodifiableList(this.patterns);
    }

    public void setPatterns(List<Pattern> patterns) {
        this.patterns = patterns;
    }
}
