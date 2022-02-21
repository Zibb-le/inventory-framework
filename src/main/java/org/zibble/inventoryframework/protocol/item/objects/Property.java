package org.zibble.inventoryframework.protocol.item.objects;

import com.github.retrooper.packetevents.protocol.player.TextureProperty;

public class Property {

    private String property;
    private String value;
    private String signature;

    public Property(String property, String value, String signature) {
        this.property = property;
        this.value = value;
        this.signature = signature;
    }

    public String property() {
        return property;
    }

    public void property(String property) {
        this.property = property;
    }

    public String value() {
        return value;
    }

    public void value(String value) {
        this.value = value;
    }

    public String signature() {
        return signature;
    }

    public void signature(String signature) {
        this.signature = signature;
    }

    public TextureProperty asProtocol() {
        return new TextureProperty(property, value, signature);
    }

}
