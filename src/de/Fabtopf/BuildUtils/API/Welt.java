package de.Fabtopf.BuildUtils.API;

import de.Fabtopf.BuildUtils.Utilities.MySQL.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabi on 15.08.2017.
 */
public class Welt {

    private World world;

    private String name;

    private boolean open;
    private boolean lobby;
    private boolean managed;

    private int neededGrade;

    private List<OfflinePlayer> builders = new ArrayList<>();
    private List<OfflinePlayer> customers = new ArrayList<>();

    public Welt(String name) {
        this.world = Bukkit.getWorld(name);
        this.name = name;
        this.open = Utils.getWorldPublic(name);
        this.managed = Utils.getWorldManaged(name);
        this.lobby = Utils.getWorldLobby(name);
        this.neededGrade = Utils.getWorldNeededGrade(name);
        this.customers = Converter.idStringToOpList(Utils.getWorldCustomers(name));
        this.builders = Converter.idStringToOpList(Utils.getWorldBuilders(name));
    }

    public World getWorld() {
        return world;
    }

    public List<OfflinePlayer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<OfflinePlayer> customers) {
        Utils.updateWorldCustomers(name, Converter.opListToIdString(customers));
        this.customers = customers;
    }

    public List<OfflinePlayer> getBuilders() {

        return builders;
    }

    public void setBuilders(List<OfflinePlayer> builders) {
        Utils.updateWorldBuilders(name, Converter.opListToIdString(builders));
        this.builders = builders;
    }

    public int getNeededGrade() {

        return neededGrade;
    }

    public void setNeededGrade(int neededGrade) {
        Utils.updateWorldNeededGrade(name, neededGrade);
        this.neededGrade = neededGrade;
    }

    public boolean isManaged() {

        return managed;
    }

    public void setManaged(boolean managed) {
        Utils.updateWorldManaged(name, managed);
        this.managed = managed;
    }

    public boolean isLobby() {

        return lobby;
    }

    public void setLobby(boolean lobby) {
        Utils.updateWorldLobby(name, lobby);
        this.lobby = lobby;
    }

    public boolean isOpen() {

        return open;
    }

    public void setOpen(boolean open) {
        Utils.updateWorldPublic(name, open);
        this.open = open;
    }

    public String getName() {

        return name;
    }
}
