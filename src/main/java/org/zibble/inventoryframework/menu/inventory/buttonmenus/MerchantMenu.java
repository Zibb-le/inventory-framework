package org.zibble.inventoryframework.menu.inventory.buttonmenus;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.netty.buffer.UnpooledByteBufAllocationHelper;
import com.github.retrooper.packetevents.protocol.recipe.data.MerchantRecipeData;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerPluginMessage;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerTradeList;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.ByteBufferUtil;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.nameable.NamedButtonMenu;
import org.zibble.inventoryframework.menu.openinventory.AbstractOpenInventory;
import org.zibble.inventoryframework.protocol.item.objects.buttons.TradeOption;

import java.util.ArrayList;
import java.util.List;

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
        List<MerchantRecipeData> recipes = new ArrayList<>();
        for (MenuItem<TradeOption> tradeOption : this.buttons) {
            if (tradeOption == null || tradeOption.content() == null) continue;
            recipes.add(tradeOption.content().asProtocol());
        }
        if (InventoryFramework.framework().serverVersion().isNewerThanOrEquals(ServerVersion.V_1_14)) {
            WrapperPlayServerTradeList packet = new WrapperPlayServerTradeList(
                    openInventory.windowId(),
                    recipes,
                    this.villagerLevel,
                    this.villagerXp,
                    this.showProgress,
                    this.canRestock);
            openInventory.player().sendPacket(packet);
        } else {
            Object buffer = UnpooledByteBufAllocationHelper.buffer();
            ByteBufferUtil.writeInt(buffer, openInventory.windowId());

            // Writing the recipes
            ByteBufferUtil.writeByte(buffer, recipes.size());
            for (MerchantRecipeData recipe : recipes) {
                ByteBufferUtil.writeItemStack(buffer, recipe.getBuyItem1());
                ByteBufferUtil.writeItemStack(buffer, recipe.getSellItem());
                ByteBufferUtil.writeBoolean(buffer, recipe.getBuyItem2() != null);
                if (recipe.getBuyItem2() != null) {
                    ByteBufferUtil.writeItemStack(buffer, recipe.getBuyItem2());
                }

                ByteBufferUtil.writeBoolean(buffer, recipe.getUses() >= recipe.getMaxUses());
                ByteBufferUtil.writeInt(buffer, recipe.getUses());
                ByteBufferUtil.writeInt(buffer, recipe.getMaxUses());
            }

            WrapperPlayServerPluginMessage packet = new WrapperPlayServerPluginMessage("Mc|TrList", ByteBufferUtil.toByteArray(buffer));
            openInventory.player().sendPacket(packet);
        }
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
