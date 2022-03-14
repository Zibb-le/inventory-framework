package org.zibble.inventoryframework.spigot;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;

import java.util.UUID;

public class SpigotProtocolPlayer implements ProtocolPlayer<Player> {

    private final Player player;

    public SpigotProtocolPlayer(Player player) {
        this.player = player;
    }

    @Override
    public @NotNull UUID getUniqueID() {
        return player.getUniqueId();
    }

    @Override
    public @NotNull String getName() {
        return player.getName();
    }

    @Override
    public @NotNull Player handle() {
        return player;
    }

    @Override
    public void updatePlayerInventory() {
        player.updateInventory();
    }

}
