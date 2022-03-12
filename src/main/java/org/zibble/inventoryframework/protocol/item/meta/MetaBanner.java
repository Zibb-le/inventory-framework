package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.*;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.protocol.ItemMaterial;
import org.zibble.inventoryframework.protocol.item.objects.PatternBanner;
import org.zibble.inventoryframework.protocol.item.objects.enums.EnumDye;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MetaBanner extends MetaItem {

    private static final String BASE = "Base";
    private static final String PATTERNS = "Patterns";
    private static final String COLOR = "Color";
    private static final String PATTERN = "Pattern";
    private static final String BLOCK_ENTITY_TAG = "BlockEntityTag";

    private EnumDye baseColor;
    private @NotNull final List<PatternBanner> patterns;

    protected MetaBanner(@NotNull ItemMaterial material) {
        this.patterns = new ArrayList<>();
    }

    public EnumDye getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(EnumDye baseColor) {
        this.baseColor = baseColor;
    }

    @NotNull
    public List<PatternBanner> getPatterns() {
        return Collections.unmodifiableList(this.patterns);
    }

    public void addPattern(@NotNull PatternBanner pattern) {
        this.patterns.add(pattern);
    }

    public void removePattern(@NotNull PatternBanner pattern) {
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
        for (PatternBanner pattern : this.patterns) {
            NBTCompound patternCompound = new NBTCompound();
            patternCompound.setTag(COLOR, new NBTInt(pattern.getColor().getID()));
            patternCompound.setTag(PATTERN, new NBTString(pattern.getType().getID()));
            nbtList.addTag(patternCompound);
        }
        entityTag.setTag(PATTERNS, nbtList);
        compound.setTag(BLOCK_ENTITY_TAG, entityTag);
    }
}
