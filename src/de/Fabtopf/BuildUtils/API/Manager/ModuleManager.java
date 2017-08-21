package de.Fabtopf.BuildUtils.API.Manager;

import de.Fabtopf.BuildUtils.API.Module;
import de.Fabtopf.BuildUtils.API.Welt;
import de.Fabtopf.BuildUtils.Utilities.Main;
import de.Fabtopf.BuildUtils.Utilities.MySQL.Utils;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Fabi on 20.08.2017.
 */
public class ModuleManager {

    private static HashMap<String, Module> modules = new HashMap<String, Module>();

    public static void registerModule(String name) {
        if(Utils.moduleExists(name)) {
            modules.put(name, new Module(name));
        } else {
            Utils.registerModule(name);

            new BukkitRunnable() {
                @Override
                public void run() {
                    modules.put(name, new Module(name));
                }
            }.runTaskLater(Main.getInstance(), 5);
        }
    }

    public static void unregisterModule(String name) {
        modules.remove(name);
    }

    public static Module getModule(String name) {
        return modules.get(name);
    }

    public static List<Module> getModules() {
        List<Module> modulelist = new ArrayList<Module>();

        for(Module m : modules.values()) {
            modulelist.add(m);
        }

        return modulelist;
    }

}
