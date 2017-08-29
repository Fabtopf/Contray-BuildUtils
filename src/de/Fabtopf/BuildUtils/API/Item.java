package de.Fabtopf.BuildUtils.API;

import de.Fabtopf.BuildUtils.Utilities.MySQL.Utils;

/**
 * Created by Fabi on 20.08.2017.
 */
public class Item {

    int id;

    boolean interact;
    boolean place;
    boolean emptyBucket;
    boolean fillBucket;
    boolean inventory;
    boolean interactAt;

    public Item(int id) {
        this.id = id;
        this.interact = Utils.getItemInteract(id);
        this.place = Utils.getItemPlace(id);
        this.emptyBucket = Utils.getItemEmpty(id);
        this.fillBucket = Utils.getItemFill(id);
        this.inventory = Utils.getItemInventory(id);
        this.interactAt = Utils.getItemInteractAt(id);
    }

    public int getId() {
        return id;
    }

    public boolean isInteract() {
        return interact;
    }

    public boolean isPlace() {
        return place;
    }

    public boolean isEmptyBucket() {
        return emptyBucket;
    }

    public boolean isFillBucket() {
        return fillBucket;
    }

    public boolean isInventory() {
        return inventory;
    }

    public boolean isInteractAt() {return interactAt;}

    public void setInteract(boolean interact) {
        if(interact != this.interact) {
            this.interact = interact;
            Utils.updateItemInteract(id, interact);
        }
    }

    public void setPlace(boolean place) {
        if(place != this.place) {
            this.place = place;
            Utils.updateItemPlace(id, place);
        }
    }

    public void setEmptyBucket(boolean emptyBucket) {
        if(emptyBucket != this.emptyBucket) {
            this.emptyBucket = emptyBucket;
            Utils.updateItemEmpty(id, emptyBucket);
        }
    }

    public void setFillBucket(boolean fillBucket) {
        if(fillBucket != this.fillBucket) {
            this.fillBucket = fillBucket;
            Utils.updateItemFill(id, fillBucket);
        }
    }

    public void setInventory(boolean inventory) {
        if(inventory != this.inventory) {
            this.inventory = inventory;
            Utils.updateItemInventory(id, inventory);
        }
    }

    public void setInteractAt(boolean interactAt) {
        if(interactAt != this.interactAt) {
            this.interactAt = interactAt;
            Utils.updateItemInteractAt(id, interactAt);
        }
    }
}
