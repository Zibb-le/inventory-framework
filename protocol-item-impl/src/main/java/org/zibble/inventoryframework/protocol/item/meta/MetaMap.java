package org.zibble.inventoryframework.protocol.item.meta;

public class MetaMap extends MetaItem {

    private Scaling scaling;

    protected MetaMap() {
        super();
    }

    public Scaling getScaling() {
        return scaling;
    }

    public void setScaling(Scaling scaling) {
        this.scaling = scaling;
    }

    public enum Scaling {
        ZOOM_0,
        ZOOM_1,
        ZOOM_2,
        ZOOM_3,
        ZOOM_4
    }

}
