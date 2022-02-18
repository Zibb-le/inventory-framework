package com.pepedevs.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.*;
import com.pepedevs.inventoryframework.protocol.item.objects.enums.DyeColor;
import com.pepedevs.inventoryframework.protocol.item.objects.Pattern;

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
        super();
        this.patterns = new ArrayList<>();
    }

    public DyeColor getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(DyeColor baseColor) {
        this.baseColor = baseColor;
    }

    public List<Pattern> getPatterns() {
        return Collections.unmodifiableList(this.patterns);
    }

    public void setPatterns(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    @Override
    public void applyTo(NBTCompound compound) {
        super.applyTo(compound);
        NBTCompound entityTag = new NBTCompound();
        if (this.baseColor != null) {
            entityTag.setTag(BASE, new NBTInt(this.baseColor.getID()));
        }
        NBTList<NBTCompound> nbtList = new NBTList<>(NBTType.COMPOUND);
        for (Pattern pattern : this.patterns) {
            NBTCompound patternCompound = new NBTCompound();
            patternCompound.setTag(COLOR, new NBTInt(pattern.getDyeColor().getID()));
            patternCompound.setTag(PATTERN, new NBTString(pattern.getPatternType().getID()));
            nbtList.addTag(patternCompound);
        }
        entityTag.setTag(PATTERNS, nbtList);
        compound.setTag(BLOCK_ENTITY_TAG, entityTag);
    }
}
