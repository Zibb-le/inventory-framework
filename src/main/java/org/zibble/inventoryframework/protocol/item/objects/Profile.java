package org.zibble.inventoryframework.protocol.item.objects;

import com.github.retrooper.packetevents.protocol.player.TextureProperty;
import com.github.retrooper.packetevents.protocol.player.UserProfile;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Representation of Mojang's GameProfile for Inventory Framework.
 */
public class Profile {

    private UUID uuid;
    private String name;
    private final List<Property> properties = new ArrayList<>();

    public Profile(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
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

    @ApiStatus.Internal
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
