package com.pepedevs.inventoryframework.protocol.item.objects;

import com.pepedevs.inventoryframework.protocol.item.objects.enums.DyeColor;
import com.pepedevs.inventoryframework.protocol.item.objects.enums.PatternType;

public class Pattern {

    private DyeColor dyeColor;
    private PatternType patternType;

    public Pattern(DyeColor dyeColor, PatternType patternType) {
        this.dyeColor = dyeColor;
        this.patternType = patternType;
    }

    public DyeColor getDyeColor() {
        return dyeColor;
    }

    public void setDyeColor(DyeColor dyeColor) {
        this.dyeColor = dyeColor;
    }

    public PatternType getPatternType() {
        return patternType;
    }

    public void setPatternType(PatternType patternType) {
        this.patternType = patternType;
    }
}
