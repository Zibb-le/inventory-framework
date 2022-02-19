package org.zibble.inventoryframework.protocol.item.objects;

import com.github.retrooper.packetevents.protocol.player.TextureProperty;
import com.github.retrooper.packetevents.protocol.player.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Profile {

    private UUID uuid;
    private String name;
    private List<Property> properties = new ArrayList<>();

    public Profile(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public UserProfile asProtocol() {
        UserProfile profile = new UserProfile(uuid, name);
        List<TextureProperty> properties = new ArrayList<>();
        for (Property property : this.properties) {
            properties.add(property.asProtocol());
        }
        profile.setTextureProperties(properties);
        return profile;
    }

}
