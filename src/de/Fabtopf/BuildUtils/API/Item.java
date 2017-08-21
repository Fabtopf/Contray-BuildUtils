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

    public Item(int id) {
        this.id = id;
        this.interact = Utils.getItemInteract(id);
        this.place = Utils.getItemPlace(id);
        this.emptyBucket = Utils.getItemEmpty(id);
        this.fillBucket = Utils.getItemFill(id);
        this.inventory = Utils.getItemInventory(id);
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
}
