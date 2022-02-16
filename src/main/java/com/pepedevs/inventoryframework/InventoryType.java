package com.pepedevs.inventoryframework;

import com.github.retrooper.packetevents.manager.server.ServerVersion;

public enum InventoryType {

    CHEST("minecraft:chest", 0) {
        @Override
        public int getLatestId(int size) {
            return Math.max(size / 9 - 1, 0);
        }
    },
    ANVIL("minecraft:anvil", 8) {
        @Override
        public int getLatestId(int size) {
            return 7;
        }
    },
    DISPENSER("minecraft:dispenser", 3) {
        @Override
        public int getLatestId(int size) {
            return 6;
        }
    },
    FURNACE("minecraft:furnace", 2) {
        @Override
        public int getLatestId(int size) {
            return 13;
        }
    },
    BLAST_FURNACE("minecraft:blast_furnace", ServerVersion.V_1_14) {
        @Override
        public int getLatestId(int size) {
            return 9;
        }
    },
    SMOKER("minecraft:smoker", ServerVersion.V_1_14) {
        @Override
        public int getLatestId(int size) {
            return 21;
        }
    },
    HOPPER("minecraft:hopper", 9) {
        @Override
        public int getLatestId(int size) {
            return 15;
        }
    },
    BREWING_STAND("minecraft:brewing_stand", 5) {
        @Override
        public int getLatestId(int size) {
            return 10;
        }
    },
    BEACON("minecraft:beacon", 7) {
        @Override
        public int getLatestId(int size) {
            return 8;
        }
    },
    ENCHANTING_TABLE("minecraft:enchanting_table", 4) {
        @Override
        public int getLatestId(int size) {
            return 12;
        }
    },
    WORKBENCH("minecraft:crafting", 1) {
        @Override
        public int getLatestId(int size) {
            return 11;
        }
    },
    MERCHANT_GUI("minecraft:villager") {
        @Override
        public int getLatestId(int size) {
            return 18;
        }
    },
    GRINDSTONE("minecraft:grindstone", ServerVersion.V_1_14) {
        @Override
        public int getLatestId(int size) {
            return 14;
        }
    },
    LECTERN("minecraft:lectern", ServerVersion.V_1_14) {
        @Override
        public int getLatestId(int size) {
            return 16;
        }
    },
    LOOM("minecraft:loom", ServerVersion.V_1_14) {
        @Override
        public int getLatestId(int size) {
            return 17;
        }
    },
    SMITHING("minecraft:smithing", ServerVersion.V_1_14) {
        @Override
        public int getLatestId(int size) {
            return 20;
        }
    },
    CARTOGRAPHY_TABLE("minecraft:cartography_table", ServerVersion.V_1_14) {
        @Override
        public int getLatestId(int size) {
            return 22;
        }
    },
    STONE_CUTTER("minecraft:stonecutter", ServerVersion.V_1_14) {
        @Override
        public int getLatestId(int size) {
            return 23;
        }
    },
    ;

    private final String legacy_id;
    private final int notch_inv_id;
    private ServerVersion introduced_version;

    InventoryType(String legacy_id) {
        this(legacy_id, ServerVersion.V_1_7_10);
    }

    InventoryType(String legacy_id, ServerVersion introduced_version) {
        this(legacy_id, -1);
        this.introduced_version = introduced_version;
    }

    InventoryType(String legacy_id, int notch_inv_id) {
        this.legacy_id = legacy_id;
        this.notch_inv_id = notch_inv_id;
        this.introduced_version = ServerVersion.V_1_7_10;
    }

    public String getLegacyId() {
        return legacy_id;
    }

    public int getNotchInvId() {
        return notch_inv_id;
    }

    public ServerVersion getIntroducedVersion() {
        return introduced_version;
    }

    public abstract int getLatestId(int size);

}
