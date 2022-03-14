package org.zibble.inventoryframework.menu.property;

import org.zibble.inventoryframework.protocol.ProtocolPlayer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * A window data property that can be sent to the {@link ProtocolPlayer}.
 * Refer to <a href = "https://wiki.vg/Protocol#Window_Property">Window Property</a>
 */
@ApiStatus.Internal
public interface PropertyPair {

    /**
     * @return the id of this property
     */
    @Range(from = 0, to = Integer.MAX_VALUE) int id();

    /**
     * @return the data of this property
     */
    @Range(from = 0, to = Integer.MAX_VALUE) int value();

    @NotNull
    static PropertyPair of(@Range(from = 0, to = Integer.MAX_VALUE) int id,
                                    @Range(from = 0, to = Integer.MAX_VALUE) int value) {
        return new PropertyPair() {
            @Override
            public int id() {
                return id;
            }

            @Override
            public int value() {
                return value;
            }
        };
    }

}
