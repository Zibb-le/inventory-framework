package com.pepedevs.inventoryframework;

public enum InventoryType {

    CHEST("minecraft:chest", 0),
    ANVIL("minecraft:anvil", 8),
    DISPENSER("minecraft:dispenser", 3),
    DROPPER("minecraft:dropper", 3),
    FURNACE("minecraft:furnace", 2),
    HOPPER("minecraft:hopper", 9),
    BREWING_STAND("minecraft:brewing_stand", 5),
    BEACON("minecraft:beacon", 7),
    ENCHANTING_TABLE("minecraft:enchanting_table", 4),
    WORKBENCH("minecraft:crafting", 1),
    MERCHANT_GUI("minecraft:villager"),
    ;

    private final String legacy_id;
    private final int notch_inv_id;

    InventoryType(String legacy_id) {
        this(legacy_id, -1);
    }

    InventoryType(String legacy_id, int notch_inv_id) {
        this.legacy_id = legacy_id;
        this.notch_inv_id = notch_inv_id;
    }

    public String getLegacyId() {
        return legacy_id;
    }

    public int getNotchInvId() {
        return notch_inv_id;
    }

}
