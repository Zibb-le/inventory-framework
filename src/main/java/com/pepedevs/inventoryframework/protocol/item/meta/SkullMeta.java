package com.pepedevs.inventoryframework.protocol.item.meta;

import com.pepedevs.inventoryframework.protocol.Profile;

public class SkullMeta extends ItemMeta {

    private Profile profile;

    protected SkullMeta() {
        super();
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
