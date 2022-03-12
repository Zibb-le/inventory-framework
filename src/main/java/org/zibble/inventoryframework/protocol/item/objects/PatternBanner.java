package org.zibble.inventoryframework.protocol.item.objects;

import org.zibble.inventoryframework.protocol.item.objects.enums.EnumDye;
import org.zibble.inventoryframework.protocol.item.objects.enums.EnumPattern;

public class PatternBanner {

    private final EnumDye dyeColor;
    private final EnumPattern patternType;

    public PatternBanner(EnumDye dyeColor, EnumPattern patternType) {
        this.dyeColor = dyeColor;
        this.patternType = patternType;
    }

    public EnumDye getColor() {
        return dyeColor;
    }

    public EnumPattern getType() {
        return patternType;
    }
}
