package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.github.retrooper.packetevents.protocol.nbt.NBTInt;
import com.github.retrooper.packetevents.protocol.nbt.NBTString;
import com.github.retrooper.packetevents.util.AdventureSerializer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class BookMeta extends ItemMeta {

    protected static final String BOOK_TITLE = "title";
    protected static final String BOOK_AUTHOR = "author";
    protected static final String GENERATION = "generation";

    private Component title;
    private Component author;
    private Generation generation;

    protected BookMeta() {
        super();
    }

    public Component getTitle() {
        return title;
    }

    public void setTitle(Component title) {
        this.title = title;
    }

    public Component getAuthor() {
        return author;
    }

    public void setAuthor(Component author) {
        this.author = author;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    @ApiStatus.Internal
    @Override
    public void applyTo(@NotNull NBTCompound compound) {
        super.applyTo(compound);
        if (this.title != null)
            compound.setTag(BOOK_TITLE, new NBTString(AdventureSerializer.asVanilla(this.title)));
        if (this.author != null)
            compound.setTag(BOOK_AUTHOR, new NBTString(AdventureSerializer.asVanilla(this.author)));
        if (this.generation != null)
            compound.setTag(GENERATION, new NBTInt(this.generation.ordinal()));
        //TODO DOUBLE CHECK GENERATION

    }

    public enum Generation {
        ORIGINAL(),
        COPY_OF_ORIGINAL(),
        COPY_OF_COPY(),
        TATTERED(),
        ;
    }
}
