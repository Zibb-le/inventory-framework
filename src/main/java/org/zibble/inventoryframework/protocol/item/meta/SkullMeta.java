package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.github.retrooper.packetevents.protocol.nbt.NBTList;
import com.github.retrooper.packetevents.protocol.nbt.NBTString;
import com.github.retrooper.packetevents.protocol.nbt.NBTType;
import org.zibble.inventoryframework.protocol.item.objects.Profile;
import org.zibble.inventoryframework.protocol.item.objects.Property;

public class SkullMeta extends ItemMeta {

    public static final String SKULL_OWNER = "SkullOwner";

    private Profile profile;

    protected SkullMeta() {
        super();
    }

    public Profile profile() {
        return profile;
    }

    public void profile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public void applyTo(NBTCompound compound) {
        super.applyTo(compound);
        if (profile != null) {
            NBTCompound owner = new NBTCompound();
            if (profile.name() != null && !profile.name().isEmpty()) {
                owner.setTag("Name", new NBTString(profile.name()));
            }

            if (profile.uuid() != null) {
                owner.setTag("Id", new NBTString(profile.uuid().toString()));
            }

            if (!profile.properties().isEmpty()) {
                NBTCompound properties = new NBTCompound();
                NBTList<NBTCompound> list = new NBTList<>(NBTType.COMPOUND);
                for (Property property : profile.properties()) {
                    NBTCompound prop = new NBTCompound();
                    prop.setTag("Value", new NBTString(property.value()));
                    if (property.signature() != null) {
                        prop.setTag("Signature", new NBTString(property.signature()));
                    }
                }
                properties.setTag("textures", list);
                owner.setTag("Properties", properties);
            }

            compound.setTag(SKULL_OWNER, owner);
        }
    }

}
