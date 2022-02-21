package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.nameable.NamedButtonMenu;
import org.zibble.inventoryframework.menu.openinventory.ButtonOpenInventory;
import org.zibble.inventoryframework.menu.property.DataPropertyHolder;
import org.zibble.inventoryframework.menu.property.PropertyPair;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;
import org.zibble.inventoryframework.protocol.item.objects.buttons.EnchantOption;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class EnchantingTableMenu extends NamedButtonMenu<EnchantOption> implements DataPropertyHolder {

    public EnchantingTableMenu(@Nullable final Component title) {
        super(1, 2, 3, 1, title);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.ENCHANTING_TABLE;
    }

    @Override
    public void open(@NotNull final ProtocolPlayer<?> user) {
        ButtonOpenInventory openInventory = new ButtonOpenInventory(user, this);
        Menu.OPEN_INVENTORIES.put(user, openInventory);
        openInventory.show();
        openInventory.sendItems(this.items());
        openInventory.updateWindowData(this.properties());
    }

    @Override
    @NotNull
    public PropertyPair[] properties() {
        List<PropertyPair> properties = new ArrayList<>(6);

        for (int i = 0; i < this.buttons.length; i++) {
            MenuItem<EnchantOption> button = this.buttons[i][0];
            if (button == null || button.content() == null) continue;
            properties.add(PropertyPair.of(i, button.content().xpCost()));
            properties.add(PropertyPair.of(i + 4, button.content().id()));
            properties.add(PropertyPair.of(i + 7, button.content().level()));
        }

        properties.removeIf(new Predicate<PropertyPair>() {
            @Override
            public boolean test(PropertyPair propertyPair) {
                return propertyPair == null;
            }
        });

        return properties.toArray(new PropertyPair[0]);
    }
}
