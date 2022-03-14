package org.zibble.inventoryframework.menu.property;

import org.zibble.inventoryframework.protocol.ProtocolPlayer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
public interface DataPropertyHolder {

    /**
     * @return An array of data properties of the menu to be sent as window data to the {@link ProtocolPlayer}.
     */
    @NotNull PropertyPair[] properties();

}
