package de.Fabtopf.BuildUtils.API.Manager;

import de.Fabtopf.BuildUtils.API.FinishedPlot;
import de.Fabtopf.BuildUtils.Utilities.MySQL.Utils;
import org.bukkit.OfflinePlayer;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Fabi on 24.08.2017.
 */
public class PlotManager {

    private static HashMap<OfflinePlayer, FinishedPlot> plots = new HashMap<OfflinePlayer, FinishedPlot>();

    public static void registerPlot(OfflinePlayer op, Location loc) {
        if(!plots.containsKey(op)) {
            Utils.registerPlot(op, loc);
            plots.put(op, new FinishedPlot(loc, op));
        }
    }

    public static void registerPlot(int id) {
        for(FinishedPlot p : plots.values()) {
            if(p.getID() == id) return;
        }

        FinishedPlot plot = new FinishedPlot(id);
        plots.put(plot.getPlayer(), plot);
    }

    public static void unregisterPlot(OfflinePlayer op) {
        plots.remove(op);
    }

    public static FinishedPlot getPlot(OfflinePlayer op) {
        return plots.get(op);
    }

    public static List<FinishedPlot> getPlots() {
        List<FinishedPlot>  plotlist = new ArrayList<FinishedPlot>();

        for(FinishedPlot p : plots.values()) {
            if(!plotlist.contains(p)) {
                plotlist.add(p);
            }
        }

        return plotlist;
    }

}
