package org.zibble.inventoryframework;

import org.zibble.inventoryframework.protocol.item.ItemStack;

public class MenuItem<C> {

    public static MenuItem<ItemStack> of(ItemStack itemStack) {
        return new MenuItem<>(itemStack);
    }

    private C content;
    private ClickAction clickAction;

    private MenuItem(C content) {
        this.content = content;
    }

    public ClickAction clickAction() {
        return clickAction;
    }

    public void clickAction(ClickAction clickAction) {
        this.clickAction = clickAction;
    }

    public C content() {
        return this.content;
    }

    public void content(C content) {
        this.content = content;
    }
}
