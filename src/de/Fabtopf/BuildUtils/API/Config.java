package de.Fabtopf.BuildUtils.API;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Fabi on 29.06.2017.
 */
public class Config {

    private String configPath;
    private String configFile;

    private FileConfiguration cfg;
    private File file;

    public Config(String configPath, String configName) {
        this.configPath = configPath;
        this.configFile = configName;
        this.file = getFile();
        this.cfg = getFileConfiguration();

        standartConfigInput();
    }

    private File getFile() {
        return new File(configPath, configFile);
    }

    private FileConfiguration getFileConfiguration() {
        return YamlConfiguration.loadConfiguration(file);
    }

    private void save() {
        try {
            cfg.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFileConfiguration() {
        this.cfg = getFileConfiguration();
    }

    private void standartConfigInput() {
        updateFileConfiguration();

        //cfg.options().copyHeader(true);
        //cfg.options().header("+------------------------------+");
        cfg.options().header("+ File created by Contray-Varo +");
        //cfg.options().header("+------------------------------+");
        //cfg.options().header("");

        save();
    }

    public void addHeader(String text) {
        updateFileConfiguration();

        cfg.options().copyHeader(true);
        cfg.options().header(text);

        save();
    }

    public void create(String path, Object object) {
        updateFileConfiguration();

        if(!cfg.isSet(path)) {
            cfg.options().copyDefaults(true);
            cfg.addDefault(path, object);
            save();
        }
    }

    public void set(String path, Object object) {
        updateFileConfiguration();

        if (!cfg.isSet(path)) {
            cfg.options().copyDefaults(true);
            cfg.addDefault(path, object);
        } else {
            cfg.set(path, object);
        }

        save();
    }

    public void del(String path) {
        updateFileConfiguration();

        if (cfg.isSet(path)) {
            cfg.set(path, null);
        }

        save();
    }

    public Object getObject(String path) {
        updateFileConfiguration();

        if (cfg.isSet(path)) return cfg.get(path);
        return null;
    }

    public String getString(String path) {
        updateFileConfiguration();

        if (cfg.isSet(path)) return cfg.getString(path);
        return null;
    }

    public boolean getBoolean(String path) {
        updateFileConfiguration();

        return cfg.isSet(path) && cfg.getBoolean(path);
    }

    public int getInt(String path) {
        updateFileConfiguration();

        if (cfg.isSet(path)) return cfg.getInt(path);
        return 0;
    }


}

