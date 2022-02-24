package org.zibble.inventoryframework.protocol.item.objects;

import org.zibble.inventoryframework.protocol.item.objects.enums.DyeColor;
import org.zibble.inventoryframework.protocol.item.objects.enums.PatternType;

public class Pattern {

    private final DyeColor dyeColor;
    private final PatternType patternType;

    public Pattern(DyeColor dyeColor, PatternType patternType) {
        this.dyeColor = dyeColor;
        this.patternType = patternType;
    }

    public DyeColor getColor() {
        return dyeColor;
    }

    public PatternType type() {
        return patternType;
    }
}
