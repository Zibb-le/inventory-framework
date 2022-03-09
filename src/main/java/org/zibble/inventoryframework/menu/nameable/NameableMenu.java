package org.zibble.inventoryframework.menu.nameable;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public interface NameableMenu {

    @Nullable
    Component title();

    void title(@Nullable Component title);

}
