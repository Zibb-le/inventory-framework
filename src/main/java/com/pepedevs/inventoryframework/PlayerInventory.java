package com.pepedevs.inventoryframework;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class PlayerInventory {

    private final SlotClickAction[] slotClickActions;

    public PlayerInventory() {
        this.slotClickActions = new SlotClickAction[36];
    }

    public void setClickAction(int slot, SlotClickAction clickAction) {
        this.slotClickActions[slot] = clickAction;
    }

    public void setClickAction(int x, int y, SlotClickAction clickAction) {
        this.slotClickActions[x + y * 9] = clickAction;
    }

    public SlotClickAction getClickAction(int slot) {
        return this.slotClickActions[slot];
    }

    public SlotClickAction getClickAction(int x, int y) {
        return this.slotClickActions[x + y * 9];
    }

    public List<SlotClickAction> getSlotClickActions() {
        return Collections.unmodifiableList(Arrays.asList(this.slotClickActions));
    }

    public void removeClickAction(BiPredicate<Integer, SlotClickAction> predicate) {
        for (int i = 0; i < this.slotClickActions.length; i++) {
            if (predicate.test(i, this.slotClickActions[i])) this.slotClickActions[i] = null;
        }
    }

}
