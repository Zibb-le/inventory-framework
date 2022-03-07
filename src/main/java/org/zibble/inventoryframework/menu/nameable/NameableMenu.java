package org.zibble.inventoryframework.menu.nameable;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

/**
 * An interface for the menu's that can be named
 */
public interface NameableMenu {

    @Nullable
    Component title();

}
