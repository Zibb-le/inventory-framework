package com.pepedevs.inventoryframework.protocol.item.meta;

import net.kyori.adventure.text.Component;

public class BookMeta extends ItemMeta {

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

    public enum Generation {
        ORIGINAL(),
        COPY_OF_ORIGINAL(),
        COPY_OF_COPY(),
        TATTERED(),
        ;
    }
}
