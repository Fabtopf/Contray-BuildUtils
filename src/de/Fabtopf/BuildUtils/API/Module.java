package de.Fabtopf.BuildUtils.API;

import de.Fabtopf.BuildUtils.Utilities.Main;
import de.Fabtopf.BuildUtils.Utilities.MySQL.Utils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

import java.util.HashMap;

/**
 * Created by Fabi on 20.08.2017.
 */
public class Module {

    private boolean devmode;
    private boolean enabled;

    private String name;
    private String displayname;

    private HashMap<String, Command> commands = new HashMap<String, Command>();
    private HashMap<String, Listener> listeners = new HashMap<String, Listener>();

    public Module(String name) {
        this.name = name;
        this.devmode = Utils.getModuleDevmode(name);
        this.enabled = Utils.getModuleEnabled(name);
    }

    public void registerCommand(String name, String usage, String description, String permission, String[] permissions, String permissionMessage, boolean allowOp, boolean allowStar, CommandExecutor executor) {
        commands.put(name, new Command(name, usage, description, permission, permissions, permissionMessage, allowOp, allowStar, executor));
    }

    public void registerListener(String name, Listener listener) {
        listeners.put(name, listener);
        Main.getInstance().getServer().getPluginManager().registerEvents(listener, Main.getInstance());
    }

    public void setDevmode(boolean devmode) {
        if(devmode != this.devmode) {
            this.devmode = true;
            Utils.updateModuleDevmode(name, devmode);
        }
    }

    public void setEnabled(boolean enabled) {
        if(enabled != this.enabled) {
            this.enabled = enabled;
            Utils.updateModuleEnabled(name, enabled);
        }
    }

    public boolean isEnabled() { return enabled; }
    public boolean isDevmode() { return devmode; }
    public String getName() { return name; }

}
