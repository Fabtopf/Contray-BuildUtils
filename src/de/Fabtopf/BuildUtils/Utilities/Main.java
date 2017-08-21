package de.Fabtopf.BuildUtils.Utilities;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Fabi on 15.08.2017.
 */
public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        Register.enable();
    }

    @Override
    public void onDisable() {
        Register.disable();
    }

    public static Main getInstance() {
        return instance;
    }

}
