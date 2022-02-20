package org.zibble.inventoryframework.menu.property;

public interface PropertyPair {

    int getID();

    int getValue();

    static PropertyPair of(int id, int value) {
        return new PropertyPair() {
            @Override
            public int getID() {
                return id;
            }

            @Override
            public int getValue() {
                return value;
            }
        };
    }

}
