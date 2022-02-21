package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.*;
import org.zibble.inventoryframework.protocol.item.objects.Pattern;
import org.zibble.inventoryframework.protocol.item.objects.enums.DyeColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BannerMeta extends ItemMeta {

    private static final String BASE = "Base";
    private static final String PATTERNS = "Patterns";
    private static final String COLOR = "Color";
    private static final String PATTERN = "Pattern";
    private static final String BLOCK_ENTITY_TAG = "BlockEntityTag";

    private DyeColor baseColor;
    private List<Pattern> patterns;

    protected BannerMeta() {
        this.patterns = new ArrayList<>();
    }

    public DyeColor baseColor() {
        return baseColor;
    }

    public void baseColor(DyeColor baseColor) {
        this.baseColor = baseColor;
    }

    public List<Pattern> patterns() {
        return Collections.unmodifiableList(this.patterns);
    }

    public void patterns(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    @Override
    public void applyTo(NBTCompound compound) {
        super.applyTo(compound);
        NBTCompound entityTag = new NBTCompound();
        if (this.baseColor != null) {
            entityTag.setTag(BASE, new NBTInt(this.baseColor.id()));
        }
        NBTList<NBTCompound> nbtList = new NBTList<>(NBTType.COMPOUND);
        for (Pattern pattern : this.patterns) {
            NBTCompound patternCompound = new NBTCompound();
            patternCompound.setTag(COLOR, new NBTInt(pattern.dyeColor().id()));
            patternCompound.setTag(PATTERN, new NBTString(pattern.type().id()));
            nbtList.addTag(patternCompound);
        }
        entityTag.setTag(PATTERNS, nbtList);
        compound.setTag(BLOCK_ENTITY_TAG, entityTag);
    }
}
