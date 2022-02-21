package org.zibble.inventoryframework.protocol.item.meta;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.github.retrooper.packetevents.protocol.nbt.NBTInt;
import com.github.retrooper.packetevents.protocol.nbt.NBTString;
import com.github.retrooper.packetevents.util.AdventureSerializer;
import net.kyori.adventure.text.Component;

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

    public Component title() {
        return title;
    }

    public void title(Component title) {
        this.title = title;
    }

    public Component author() {
        return author;
    }

    public void author(Component author) {
        this.author = author;
    }

    public Generation generation() {
        return generation;
    }

    public void generation(Generation generation) {
        this.generation = generation;
    }

    @Override
    public void applyTo(NBTCompound compound) {
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
