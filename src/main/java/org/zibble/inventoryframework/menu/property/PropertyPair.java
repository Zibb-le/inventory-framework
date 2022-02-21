package org.zibble.inventoryframework.menu.property;

public interface PropertyPair {

    int id();

    int value();

    static PropertyPair of(int id, int value) {
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
