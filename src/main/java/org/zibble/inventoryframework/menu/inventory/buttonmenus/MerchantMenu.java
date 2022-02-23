package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.menu.nameable.NamedButtonMenu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.zibble.inventoryframework.protocol.item.objects.buttons.TradeOption;

public class MerchantMenu extends NamedButtonMenu<TradeOption> {

    private int villagerLevel;
    private int villagerXp;
    private boolean showProgress;
    private boolean canRestock;

    public MerchantMenu(@Range(from = 0, to = Integer.MAX_VALUE) final int buttonRows, @Nullable final Component title) {
        super(1, 3, buttonRows, title);
    }

    @Override
    @NotNull
    public InventoryType type() {
        return InventoryType.MERCHANT_GUI;
    }

    @Override
    public boolean isSupported() {
        return true;
    }

    @Override
    protected void update(@NotNull AbstractOpenInventory openInventory) {
        openInventory.sendItems(this.items());

    }

    public int villagerLevel() {
        return villagerLevel;
    }

    public void villagerLevel(int villagerLevel) {
        this.villagerLevel = villagerLevel;
    }

    public int villagerXp() {
        return villagerXp;
    }

    public void villagerXp(int villagerXp) {
        this.villagerXp = villagerXp;
    }

    public boolean showProgress() {
        return showProgress;
    }

    public void showProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public boolean canRestock() {
        return canRestock;
    }

    public void canRestock(boolean canRestock) {
        this.canRestock = canRestock;
    }

    enum VillagerLevel {
        NOVICE,
        APPRENTICE,
        JOURNEYMAN,
        EXPERT,
        MASTER;

        public int getInt() {
            return this.ordinal() + 1;
        }
    }

}
