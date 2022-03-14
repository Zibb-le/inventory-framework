package org.zibble.inventoryframework;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zibble.inventoryframework.protocol.Item;
import org.zibble.inventoryframework.protocol.object.EnchantOption;

public class MenuItem<C> {

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull MenuItem<? extends Item> of(@Nullable Item itemStack) {
        return new MenuItem<>(itemStack);
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull MenuItem<EnchantOption> of(@Nullable EnchantOption option) {
        return new MenuItem<>(option);
    }

    private @Nullable C content;
    private @Nullable ClickAction clickAction;

    private MenuItem(@Nullable C content) {
        this.content = content;
    }

    @Nullable
    public ClickAction getClickAction() {
        return clickAction;
    }

    public void setClickAction(@Nullable ClickAction clickAction) {
        this.clickAction = clickAction;
    }

    @Nullable
    public C getContent() {
        return this.content;
    }

    public void setContent(@Nullable C content) {
        this.content = content;
    }
}
