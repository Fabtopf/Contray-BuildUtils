package de.Fabtopf.BuildUtils.API;

import de.Fabtopf.BuildUtils.API.Enum.PlotState;
import de.Fabtopf.BuildUtils.Utilities.Main;
import de.Fabtopf.BuildUtils.Utilities.MySQL.Utils;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitRunnable;


/**
 * Created by Fabi on 24.08.2017.
 */
public class FinishedPlot {

    private int id;

    private Location side;

    private OfflinePlayer player;

    private PlotState state;

    private boolean noticed;

    public FinishedPlot(Location side, OfflinePlayer finisher) {

        this.side = side;
        this.player = finisher;
        this.state = PlotState.notRated;
        this.noticed = false;

        new BukkitRunnable() {
            @Override
            public void run() {
                id = Utils.getPlotID(Utils.getPlayerID(player));
            }
        }.runTaskLater(Main.getInstance(), 5);

    }

    public FinishedPlot(int id) {

        this.side = Utils.getPlotSide(id);
        this.player = Utils.getPlotPlayer(id);
        this.state = Utils.getPlotState(id);
        this.noticed = Utils.getPlotNoticed(id);
        this.id = id;

    }

    public Location getSide() {
        return side;
    }

    public void setSide(Location side) {
        if(this.side != side) {
            this.side = side;
            Utils.updatePlotSide(id, side);
        }
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public PlotState getPlotState() {
        return state;
    }

    public void updatePlotState(PlotState state) {
        if(this.state != state) {
            this.state = state;
            Utils.updatePlotState(id, state);
        }
    }

    public boolean isNoticed() {
        return noticed;
    }

    public void setNoticed(boolean noticed) {
        if(this.noticed != noticed) {
            this.noticed = noticed;
            Utils.updatePlotNoticed(id, noticed);
        }
    }

    public int getID() {
        return id;
    }

}
