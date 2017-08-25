package de.Fabtopf.BuildUtils.API;

import de.Fabtopf.BuildUtils.Utilities.MySQL.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * Created by Fabi on 15.08.2017.
 */
public class Spieler {

    private Player p;

    private OfflinePlayer op;

    private int id;
    private int builderGrade;

    private boolean rate;
    private boolean inv_speed;
    private boolean inv_fly;
    private boolean inv_rate;
    private boolean inv_finish;
    private boolean inv_teleport;
    private boolean inv_invsettings;
    private boolean speed;

    public Spieler(Player p) {
        this.p = p;
        this.op = Bukkit.getOfflinePlayer(p.getUniqueId());
        this.id = Utils.getPlayerID(op);
        this.builderGrade = Utils.getBuilderGrade(id);
        this.rate = false;
    }

    public void setBuilderGrade(int builderGrade) {
        if(builderGrade != this.builderGrade) {
            this.builderGrade = builderGrade;
            Utils.updateBuilderGrade(id, builderGrade);
        }
    }

    public Player getP() {
        return p;
    }

    public OfflinePlayer getOp() {
        return op;
    }

    public int getId() {
        return id;
    }

    public int getBuilderGrade() {
        return builderGrade;
    }

    public boolean isRate() { return rate; }

    public void setRate(boolean rate) { this.rate = rate; }

    public boolean isInv_speed() {
        return inv_speed;
    }

    public void setInv_speed(boolean inv_speed) {
        this.inv_speed = inv_speed;
    }

    public boolean isInv_fly() {
        return inv_fly;
    }

    public void setInv_fly(boolean inv_fly) {
        this.inv_fly = inv_fly;
    }

    public boolean isInv_rate() {
        return inv_rate;
    }

    public void setInv_rate(boolean inv_rate) {
        this.inv_rate = inv_rate;
    }

    public boolean isInv_finish() {
        return inv_finish;
    }

    public void setInv_finish(boolean inv_finish) {
        this.inv_finish = inv_finish;
    }

    public boolean isInv_teleport() {
        return inv_teleport;
    }

    public void setInv_teleport(boolean inv_teleport) {
        this.inv_teleport = inv_teleport;
    }

    public boolean isInv_invsettings() {
        return inv_invsettings;
    }

    public void setInv_invsettings(boolean inv_invsettings) {
        this.inv_invsettings = inv_invsettings;
    }

    public boolean isSpeed() {
        return speed;
    }

    public void setSpeed(boolean speed) {
        this.speed = speed;
    }
}
