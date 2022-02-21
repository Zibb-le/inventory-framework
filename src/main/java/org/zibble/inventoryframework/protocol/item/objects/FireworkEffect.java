package org.zibble.inventoryframework.protocol.item.objects;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class FireworkEffect {

    private final Type type;
    private final boolean flicker;
    private final boolean trail;
    private final List<Color> colors;
    private final List<Color> fadeColors;

    public FireworkEffect(Type type, boolean flicker, boolean trail, List<Color> colors, List<Color> fadeColors) {
        this.type = type;
        this.flicker = flicker;
        this.trail = trail;
        this.colors = colors;
        this.fadeColors = fadeColors;
    }

    public Type type() {
        return this.type;
    }

    public boolean hasFlicker() {
        return this.flicker;
    }

    public boolean hasTrail() {
        return this.trail;
    }

    public List<Color> colors() {
        return Collections.unmodifiableList(this.colors);
    }

    public List<Color> fadeColors() {
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
