package de.Fabtopf.BuildUtils.API.Manager;

import de.Fabtopf.BuildUtils.API.Spieler;
import de.Fabtopf.BuildUtils.Utilities.Main;
import de.Fabtopf.BuildUtils.Utilities.MySQL.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Fabi on 15.08.2017.
 */
public class SpielerManager {

    private static HashMap<Player, Spieler> spieler = new HashMap<Player, Spieler>();

    public static void registerSpieler(Player p) {
        if(Utils.playerExists(Bukkit.getOfflinePlayer(p.getUniqueId()))) {
            spieler.put(p, new Spieler(p));
        } else {
            Utils.registerPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));

            new BukkitRunnable() {
                @Override
                public void run() {
                    Utils.registerPlayerSettings(Utils.getPlayerID(Bukkit.getOfflinePlayer(p.getUniqueId())));
                }
            }.runTaskLater(Main.getInstance(), 5);

            new BukkitRunnable() {
                @Override
                public void run() {
                    spieler.put(p, new Spieler(p));
                }
            }.runTaskLater(Main.getInstance(), 10);
        }
    }

    public static void unregisterSpieler(Player p) {
        spieler.remove(p);
    }

    public static Spieler getSpieler(Player p) {
        return spieler.get(p);
    }

    public static List<Spieler> getSpielerList() {
        List<Spieler> spielerList = new ArrayList<>();

        for(Spieler s : spieler.values()) {
            spielerList.add(s);
        }

        return spielerList;
    }
}
