package de.Fabtopf.BuildUtils.API.Manager;

import de.Fabtopf.BuildUtils.API.Item;
import de.Fabtopf.BuildUtils.API.Module;
import de.Fabtopf.BuildUtils.Utilities.Main;
import de.Fabtopf.BuildUtils.Utilities.MySQL.Utils;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Fabi on 20.08.2017.
 */
public class ItemManager {

    private static HashMap<Integer, Item> items = new HashMap<Integer, Item>();

    public static void registerItem(int id) {
        if(Utils.itemExists(id)) {
            items.put(id, new Item(id));
        } else {
            Utils.registerItem(id);

            new BukkitRunnable() {
                @Override
                public void run() {
                    items.put(id, new Item(id));
                }
            }.runTaskLater(Main.getInstance(), 5);
        }
    }

    public static void unregisterItem(int id) {
        items.remove(id);
        Utils.unregisterItem(id);
    }

    public static Item getItem(int id) {
        return items.get(id);
    }

    public static List<Item> getItems() {
        List<Item> itemlist = new ArrayList<Item>();

        for(Item i : items.values()) {
            itemlist.add(i);
        }

        return itemlist;
    }

}
