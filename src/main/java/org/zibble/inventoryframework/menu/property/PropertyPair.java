package org.zibble.inventoryframework.menu.property;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public interface PropertyPair {

    @Range(from = 0, to = Integer.MAX_VALUE) int id();

    @Range(from = 0, to = Integer.MAX_VALUE) int value();

    @NotNull
    static PropertyPair of(@Range(from = 0, to = Integer.MAX_VALUE) int id,
                                    @Range(from = 0, to = Integer.MAX_VALUE) int value) {
        return new PropertyPair() {
            @Override
            public int id() {
                return id;
            }

            @Override
            public int value() {
                return value;
            }
        };
    }

}
