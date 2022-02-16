package com.pepedevs.inventoryframework;

import com.github.retrooper.packetevents.protocol.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuItem {

    private ItemStack itemStack;
    private final List<ClickAction> clickActions;

    private MenuItem(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.clickActions = new ArrayList<>();
    }

    public static MenuItem of(ItemStack itemStack) {
        return new MenuItem(itemStack);
    }

    public List<ClickAction> getClickActions() {
        return Collections.unmodifiableList(this.clickActions);
    }

    public void addClickAction(ClickAction clickAction) {
        this.clickActions.add(clickAction);
    }

    public void removeClickAction(ClickAction clickAction) {
        this.clickActions.remove(clickAction);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
