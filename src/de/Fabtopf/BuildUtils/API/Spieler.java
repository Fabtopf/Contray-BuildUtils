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
}
