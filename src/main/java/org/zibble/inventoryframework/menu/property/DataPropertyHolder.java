package org.zibble.inventoryframework.menu.property;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
public interface DataPropertyHolder {

    @NotNull PropertyPair[] properties();

}
