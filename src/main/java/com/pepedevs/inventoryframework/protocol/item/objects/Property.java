package com.pepedevs.inventoryframework.protocol.item.objects;

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

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public TextureProperty asProtocol() {
        return new TextureProperty(property, value, signature);
    }

}
