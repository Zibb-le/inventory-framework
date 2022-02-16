package com.pepedevs.inventoryframework;

import com.github.retrooper.packetevents.protocol.player.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public abstract class Menu {

    private final MenuItem[][] items;
    private final int rows;
    private final int columns;
    private char[][] mask;
    private final Map<Character, MenuItem> maskMap;

    private final List<Consumer<User>> onOpen;
    private final List<Consumer<User>> onClose;


    public Menu(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.items = new MenuItem[columns][rows];
        this.mask = new char[columns][rows];
        this.maskMap = new ConcurrentHashMap<>();
    }

    public MenuItem getItemAt(int index) {
        return items[index / columns][index % columns];
    }

    public void setItemAt(int index, MenuItem item) {
        items[index / columns][index % columns] = item;
    }

    public MenuItem[][] getItems() {
        return items;
    }

    public char[][] getMask() {
        return mask;
    }

    public void setMask(char[][] mask) {
        this.mask = mask;
    }

    public Map<Character, MenuItem> getMaskMap() {
        return maskMap;
    }

    public void changeMaskItem(char maskKey, MenuItem item) {
        this.maskMap.put(maskKey, item);
    }

    public MenuItem getMaskItem(char maskKey) {
        return this.maskMap.get(maskKey);
    }
}
