package com.pepedevs.inventoryframework.protocol.item.objects;

import com.pepedevs.inventoryframework.protocol.item.objects.enums.DyeColor;
import com.pepedevs.inventoryframework.protocol.item.objects.enums.PatternType;

public class Pattern {

    private final DyeColor dyeColor;
    private final PatternType patternType;

    public Pattern(DyeColor dyeColor, PatternType patternType) {
        this.dyeColor = dyeColor;
        this.patternType = patternType;
    }

    public DyeColor getDyeColor() {
        return dyeColor;
    }

    public PatternType getPatternType() {
        return patternType;
    }
}
