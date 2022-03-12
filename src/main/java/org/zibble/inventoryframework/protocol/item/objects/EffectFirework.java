package org.zibble.inventoryframework.protocol.item.objects;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class EffectFirework {

    private final Type type;
    private final boolean flicker;
    private final boolean trail;
    private final List<Color> colors;
    private final List<Color> fadeColors;

    public EffectFirework(Type type, boolean flicker, boolean trail, List<Color> colors, List<Color> fadeColors) {
        this.type = type;
        this.flicker = flicker;
        this.trail = trail;
        this.colors = colors;
        this.fadeColors = fadeColors;
    }

    public Type getType() {
        return this.type;
    }

    public boolean hasFlicker() {
        return this.flicker;
    }

    public boolean hasTrail() {
        return this.trail;
    }

    public List<Color> getColors() {
        return Collections.unmodifiableList(this.colors);
    }

    public List<Color> getFadeColors() {
        return Collections.unmodifiableList(this.fadeColors);
    }

    public enum Type {
        BALL,
        BALL_LARGE,
        STAR,
        CREEPER,
        BURST
    }

}
