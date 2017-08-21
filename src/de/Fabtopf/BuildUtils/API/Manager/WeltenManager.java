package de.Fabtopf.BuildUtils.API.Manager;

import de.Fabtopf.BuildUtils.API.Welt;
import de.Fabtopf.BuildUtils.Utilities.Main;
import de.Fabtopf.BuildUtils.Utilities.MySQL.Utils;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Fabi on 15.08.2017.
 */
public class WeltenManager {

    private static HashMap<String, Welt> welten = new HashMap<String, Welt>();

    public static void registerWelt(String name) {
        if(!welten.containsKey(name)) {
            if (Utils.worldExists(name)) {
                welten.put(name, new Welt(name));
            } else {
                Utils.registerWorld(name);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        welten.put(name, new Welt(name));
                    }
                }.runTaskLater(Main.getInstance(), 5);
            }
        }
    }

    public static void unregisterWelt(String name) {
        welten.remove(name);
    }

    public static Welt getWelt(String name) {
        return welten.get(name);
    }

    public static List<Welt> getWeltenList() {
        List<Welt> weltenList = new ArrayList<>();

        for(Welt w : welten.values()) {
            weltenList.add(w);
        }

        return weltenList;
    }

}
