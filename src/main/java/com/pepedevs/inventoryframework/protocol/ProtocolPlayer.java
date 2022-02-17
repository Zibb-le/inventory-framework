package com.pepedevs.inventoryframework.protocol;

import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.player.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ProtocolPlayer {

    public static final Set<ProtocolPlayer> PLAYERS = Collections.synchronizedSet(new HashSet<>());
    private static boolean handling = false;

    public static ProtocolPlayer handle(User user) {
        return new ProtocolPlayer(user);
    }

    public static void unhandle(User user) {
        PLAYERS.removeIf(player -> player.getUser().equals(user));
    }

    public static ProtocolPlayer player(User user) {
        for (ProtocolPlayer player : PLAYERS) {
            if (player.getUser().equals(user)) {
                return player;
            }
        }
        throw new IllegalStateException("Player not handled?");
    }

    public static void setHandling(boolean handling) {
        ProtocolPlayer.handling = handling;
    }

    public static boolean isHandling() {
        return handling;
    }

    private final User user;
    private ItemStack carried;

    private ProtocolPlayer(User user) {
        this.user = user;
        PLAYERS.add(this);
    }

    public User getUser() {
        return user;
    }

    public ItemStack getCarried() {
        return carried;
    }

    public void setCarried(ItemStack carried) {
        this.carried = carried;
    }

}
