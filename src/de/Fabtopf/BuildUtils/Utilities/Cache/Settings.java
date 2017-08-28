package de.Fabtopf.BuildUtils.Utilities.Cache;

import de.Fabtopf.BuildUtils.API.Config;
import de.Fabtopf.BuildUtils.API.Connector;

/**
 * Created by Fabi on 15.08.2017.
 */
public class Settings {

    /*
     *  intern
     */

    public static boolean online_mode;
    public static boolean shutdown;

    public static boolean serversettings_drop;
    public static boolean serversettings_pickup;
    public static int serversettings_gamemode;
    public static boolean serversettings_seevisitable;

    public static String mysql_host;
    public static String mysql_database;
    public static String mysql_username;
    public static String mysql_password;

    public static int mysql_port;

    public static Config settings;
    public static Config mysqlc;

    public static Connector mysql;

    /*
     *  extern
     */

    public static String prefix;
    public static String pl_prefix;

    public static boolean devmode;
    public static boolean configured;

}
