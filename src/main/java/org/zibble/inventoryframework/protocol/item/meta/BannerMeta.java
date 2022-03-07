package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.*;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.protocol.Material;
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
    private @NotNull final List<Pattern> patterns;

    protected BannerMeta(@NotNull Material material) {
        this.patterns = new ArrayList<>();
    }

    public DyeColor getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(DyeColor baseColor) {
        this.baseColor = baseColor;
    }

    @NotNull
    public List<Pattern> getPatterns() {
        return Collections.unmodifiableList(this.patterns);
    }

    public void addPattern(@NotNull Pattern pattern) {
        this.patterns.add(pattern);
    }

    public void removePattern(@NotNull Pattern pattern) {
        this.patterns.remove(pattern);
    }

    @ApiStatus.Internal
    @Override
    public void applyTo(@NotNull NBTCompound compound) {
        super.applyTo(compound);
        NBTCompound entityTag = new NBTCompound();
        if (this.baseColor != null) {
            entityTag.setTag(BASE, new NBTInt(this.baseColor.getID()));
        }
        NBTList<NBTCompound> nbtList = new NBTList<>(NBTType.COMPOUND);
        for (Pattern pattern : this.patterns) {
            NBTCompound patternCompound = new NBTCompound();
            patternCompound.setTag(COLOR, new NBTInt(pattern.getColor().getID()));
            patternCompound.setTag(PATTERN, new NBTString(pattern.getType().getID()));
            nbtList.addTag(patternCompound);
        }
        entityTag.setTag(PATTERNS, nbtList);
        compound.setTag(BLOCK_ENTITY_TAG, entityTag);
    }
}
