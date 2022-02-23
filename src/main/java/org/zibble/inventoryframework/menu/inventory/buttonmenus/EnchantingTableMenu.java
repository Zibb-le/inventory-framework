package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.nameable.NamedButtonMenu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.zibble.inventoryframework.menu.property.DataPropertyHolder;
import org.zibble.inventoryframework.menu.property.PropertyPair;
import org.zibble.inventoryframework.protocol.item.objects.buttons.EnchantOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnchantingTableMenu extends NamedButtonMenu<EnchantOption> implements DataPropertyHolder {

    private int xpSeed;

    public EnchantingTableMenu(@Nullable final Component title) {
        super(1, 2, 3, title);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.ENCHANTING_TABLE;
    }

    @Override
    public boolean isSupported() {
        return true;
    }

    public int xpSeed() {
        return xpSeed;
    }

    public void xpSeed(int xpSeed) {
        this.xpSeed = xpSeed;
    }

    @Override
    protected void update(@NotNull AbstractOpenInventory openInventory) {
        openInventory.sendItems(this.items());
        openInventory.updateWindowData(this.properties());
    }

    @Override
    @NotNull
    public PropertyPair[] properties() {
        List<PropertyPair> properties = new ArrayList<>(6);

        for (int i = 0; i < this.buttons.length; i++) {
            MenuItem<EnchantOption> button = this.buttons[i];
            if (button == null || button.content() == null) continue;
            properties.add(PropertyPair.of(i, button.content().xpCost()));
            if (InventoryFramework.framework().serverVersion().isNewerThanOrEquals(ServerVersion.V_1_9)) {
                properties.add(PropertyPair.of(i + 4, button.content().id()));
                properties.add(PropertyPair.of(i + 7, button.content().level()));
            } else {
                properties.add(PropertyPair.of(i + 4, button.content().id() | button.content().level() << 8));
            }
        }

        properties.add(PropertyPair.of(3, this.xpSeed & -16));
        properties.removeIf(Objects::isNull);

        return properties.toArray(new PropertyPair[0]);
    }
}
