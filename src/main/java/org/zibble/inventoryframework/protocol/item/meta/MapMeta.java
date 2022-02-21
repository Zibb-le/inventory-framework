package org.zibble.inventoryframework.protocol.item.meta;

public class MapMeta extends ItemMeta {

    private Scaling scaling;

    protected MapMeta() {
        super();
    }

    public Scaling scaling() {
        return scaling;
    }

    public void scaling(Scaling scaling) {
        this.scaling = scaling;
    }

    public enum Scaling {
        ZOOM_0(),
        ZOOM_1(),
        ZOOM_2(),
        ZOOM_3(),
        ZOOM_4(),
        ;
    }

}
