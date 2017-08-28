package de.Fabtopf.BuildUtils.Utilities.MySQL;

import de.Fabtopf.BuildUtils.API.Connector;
import de.Fabtopf.BuildUtils.API.Enum.MessagerType;
import de.Fabtopf.BuildUtils.API.Enum.PlotState;
import de.Fabtopf.BuildUtils.API.Message;
import de.Fabtopf.BuildUtils.API.Messager;
import de.Fabtopf.BuildUtils.API.UUID_Fetcher;
import de.Fabtopf.BuildUtils.Utilities.Cache.Settings;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Location;
import org.bukkit.World;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Fabi on 15.08.2017.
 */
public class Utils {

    public static void setupTables() {

        Connector mysql = Settings.mysql;

        if(!mysql.tableExists("ContrayBU_Players")) mysql.update("CREATE TABLE IF NOT EXISTS ContrayBU_Players (ID MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(16), UUID_Online VARCHAR(64) UNIQUE, UUID_Offline VARCHAR(64) UNIQUE)");
        if(!mysql.tableExists("ContrayBU_PlayerSettings")) mysql.update("CREATE TABLE IF NOT EXISTS ContrayBU_PlayerSettings (ID MEDIUMINT UNIQUE, BuilderGrade MEDIUMINT)");
        if(!mysql.tableExists("ContrayBU_Worlds")) mysql.update("CREATE TABLE IF NOT EXISTS ContrayBU_Worlds (ID MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(100), UID VARCHAR(64) UNIQUE, Managed BOOLEAN, Lobby BOOLEAN, NeededGrade MEDIUMINT, Public BOOLEAN, Gamemode INT(1), Builders TEXT, Customers TEXT)");
        if(!mysql.tableExists("ContrayBU_Modules")) mysql.update("CREATE TABLE IF NOT EXISTS ContrayBU_Modules (ID MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(64) UNIQUE, Enabled BOOLEAN, Devmode BOOLEAN)");
        if(!mysql.tableExists("ContrayBU_Items")) mysql.update("CREATE TABLE IF NOT EXISTS ContrayBU_Items (ID MEDIUMINT NOT NULL UNIQUE, Place BOOLEAN, Fill BOOLEAN, Empty BOOLEAN, Interact BOOLEAN, Inventory BOOLEAN)");
        if(!mysql.tableExists("ContrayBU_Plots")) mysql.update("CREATE TABLE IF NOT EXISTS ContrayBU_Plots (ID MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY, PlayerID MEDIUMINT UNIQUE, State INT(1), Noticed BOOLEAN, World VARCHAR(64), X DOUBLE, Y DOUBLE, Z DOUBLE, Pitch DOUBLE, Yaw DOUBLE)");
        if(!mysql.tableExists("ContrayBU_Settings")) mysql.update("CREATE TABLE IF NOT EXISTS ContrayBU_Settings (Einstellung VARCHAR(64) UNIQUE, Wert TEXT)");

    }

    public static void registerPlayer(OfflinePlayer p) {

        Connector mysql = Settings.mysql;

        if(!playerExists(p)) {
            if (Settings.online_mode) {
                mysql.update("INSERT INTO ContrayBU_Players (Name, UUID_Online) VALUES ('" + p.getName() + "','" + p.getUniqueId().toString() + "')");
            } else {
                mysql.update("INSERT INTO ContrayBU_Players (Name, UUID_Online, UUID_Offline) VALUES ('" + p.getName() + "','" + UUID_Fetcher.getUUID(p.getName()) + "','" + p.getUniqueId().toString() + "')");
            }
        }

    }

    public static boolean playerExists(OfflinePlayer p) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Players");
        String uuid = UUID_Fetcher.getUUID(p.getName()).toString();

        try {
            while (rs.next()) {
                if (Settings.online_mode) {
                    if (rs.getString("UUID_Online").equalsIgnoreCase(p.getUniqueId().toString())) return true;
                } else {
                    if (rs.getString("UUID_Offline").equalsIgnoreCase(p.getUniqueId().toString())) {
                        return true;
                    } else {
                        if(rs.getString("UUID_Online").equalsIgnoreCase(uuid)) {
                            mysql.update("UPDATE ContrayBU_Players SET UUID_Offline='" + p.getUniqueId().toString() + "' WHERE UUID_Online='" + uuid + "'");
                            return true;
                        }
                    }
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;
    }

    public static int getPlayerID(OfflinePlayer p) {
        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Players");

        try {
            while(rs.next()) {
                if(Settings.online_mode) {
                    if(rs.getString("UUID_Online").equalsIgnoreCase(p.getUniqueId().toString())) return rs.getInt("ID");
                } else {
                    if(rs.getString("UUID_Offline").equalsIgnoreCase(p.getUniqueId().toString())) return rs.getInt("ID");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return -1;
    }

    public static String getPlayerName(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Players");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) return rs.getString("Name");
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return null;
    }

    public static String getPlayerUUID(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Players");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) {
                    if(Settings.online_mode) return rs.getString("UUID_Online");
                    if(!Settings.online_mode) return rs.getString("UUID_Offline");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return null;

    }

    public static void updatePlayerName(int id, String name) { Settings.mysql.update("UPDATE ContrayBU_Players SET Name='" + name + "' WHERE ID='" + id + "'"); }

    public static void registerPlayerSettings(int id) {

        if(!playerSettingsExist(id)) {
            Connector mysql = Settings.mysql;

            mysql.update("INSERT INTO ContrayBU_PlayerSettings (ID, BuilderGrade) VALUES ('" + id + "','0')");
        }

    }

    public static boolean playerSettingsExist(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_PlayerSettings");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) return true;
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static int getBuilderGrade(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_PlayerSettings");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) return rs.getInt("BuilderGrade");
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return -1;
    }

    public static void updateBuilderGrade(int id, int grade) { Settings.mysql.update("UPDATE ContrayBU_PlayerSettings SET BuilderGrade='" + grade +"' WHERE ID='" + id + "'");}

    // Welten-Management

    public static void registerWorld(String name) {
        if(!worldExists(name)) {
            Settings.mysql.update("INSERT INTO ContrayBU_Worlds (Name, UID, Lobby, Managed, NeededGrade, Public, Gamemode, Builders, Customers) VALUES ('" + name + "','" + Bukkit.getWorld(name).getUID().toString() + "','0','0','0','1','2','','')");
        }
    }

    public static boolean worldExists(String name) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Worlds");

        try {
            while(rs.next()) {
                if(rs.getString("Name").equalsIgnoreCase(name)) {
                    return true;
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static boolean getWorldPublic(String name) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Worlds");

        try {
            while(rs.next()) {
                if(rs.getString("Name").equalsIgnoreCase(name)) {
                    return rs.getBoolean("Public");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static boolean getWorldLobby(String name) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Worlds");

        try {
            while(rs.next()) {
                if(rs.getString("Name").equalsIgnoreCase(name)) {
                    return rs.getBoolean("Lobby");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static boolean getWorldManaged(String name) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Worlds");

        try {
            while(rs.next()) {
                if(rs.getString("Name").equalsIgnoreCase(name)) {
                    return rs.getBoolean("Managed");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static int getWorldNeededGrade(String name) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Worlds");

        try {
            while(rs.next()) {
                if(rs.getString("Name").equalsIgnoreCase(name)) {
                    return rs.getInt("NeededGrade");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return -1;

    }

    public static int getWorldGamemode(String name) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Worlds");

        try {
            while(rs.next()) {
                if(rs.getString("Name").equalsIgnoreCase(name)) {
                    return rs.getInt("Gamemode");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return -1;

    }

    public static String getWorldBuilders(String name) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Worlds");

        try {
            while(rs.next()) {
                if(rs.getString("Name").equalsIgnoreCase(name)) {
                    return rs.getString("Builders");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return "";

    }

    public static String getWorldCustomers(String name) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Worlds");

        try {
            while(rs.next()) {
                if(rs.getString("Name").equalsIgnoreCase(name)) {
                    return rs.getString("Customers");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return "";

    }

    public static void updateWorldPublic(String name, boolean open) { Settings.mysql.update("UPDATE ContrayBU_Worlds SET Public='" + (open ? 1 : 0) + "' WHERE Name='" + name + "'"); }
    public static void updateWorldLobby(String name, boolean lobby) { Settings.mysql.update("UPDATE ContrayBU_Worlds SET Lobby='" + (lobby ? 1 : 0) + "' WHERE Name='" + name + "'"); }
    public static void updateWorldManaged(String name, boolean managed) { Settings.mysql.update("UPDATE ContrayBU_Worlds SET Managed='" + (managed ? 1 : 0) + "' WHERE Name='" + name + "'"); }
    public static void updateWorldNeededGrade(String name, int grade) { Settings.mysql.update("UPDATE ContrayBU_Worlds SET NeededGrade='" + grade + "' WHERE Name='" + name + "'"); }
    public static void updateWorldBuilders(String name, String builders) { Settings.mysql.update("UPDATE ContrayBU_Worlds SET Builders='" + builders + "' WHERE Name='" + name +"'"); }
    public static void updateWorldCustomers(String name, String customers) { Settings.mysql.update("UPDATE ContrayBU_Worlds SET Customers='" + customers + "' WHERE Name='" + name + "'"); }
    public static void updateWorldGamemode(String name, int gamemode) { Settings.mysql.update("UPDATE ContrayBU_Worlds SET Gamemode='" + gamemode + "' WHERE Name='" + name + "'"); }

    // Module-Management

    public static void registerModule(String name) {
        if(!moduleExists(name)) {
            Settings.mysql.update("INSERT INTO ContrayBU_Modules (Name, Devmode, Enabled) VALUES ('" + name + "','0','1')");
        }
    }

    public static boolean moduleExists(String name) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Modules");

        try {
            while(rs.next()) {
                if(rs.getString("Name").equalsIgnoreCase(name)) {
                    return true;
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static boolean getModuleEnabled(String name) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Modules");

        try {
            while(rs.next()) {
                if(rs.getString("Name").equalsIgnoreCase(name)) {
                    return rs.getBoolean("Enabled");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static boolean getModuleDevmode(String name) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Modules");

        try {
            while(rs.next()) {
                if(rs.getString("Name").equalsIgnoreCase(name)) {
                    return rs.getBoolean("Devmode");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static void updateModuleEnabled(String name, boolean enabled) { Settings.mysql.update("UPDATE ContrayBU_Modules SET Enabled='" + (enabled ? 1 : 0) + "' WHERE Name='" + name + "'"); }
    public static void updateModuleDevmode(String name, boolean devmode) { Settings.mysql.update("UPDATE ContrayBU_Modules SET Devmode='" + (devmode ? 1 : 0) + "' WHERE Name='" + name + "'"); }

    // Item-Management

    public static void registerItem(int id) {
        if(!itemExists(id)) {
            Settings.mysql.update("INSERT INTO ContrayBU_Items (ID, Place, Fill, Empty, Interact, Inventory) VALUES ('" + id + "',1,1,1,1,1)");
        }
    }

    public static void unregisterItem(int id) {
        if(itemExists(id)) {
            Settings.mysql.update("DELETE FROM ContrayBU_Items WHERE ID='" + id + "'");
        }
    }

    public static boolean itemExists(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Items");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) {
                    return true;
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static boolean getItemPlace(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Items");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) {
                    return rs.getBoolean("Place");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static boolean getItemFill(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Items");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) {
                    return rs.getBoolean("Fill");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static boolean getItemEmpty(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Items");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) {
                    return rs.getBoolean("Empty");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static boolean getItemInteract(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Items");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) {
                    return rs.getBoolean("Interact");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static boolean getItemInventory(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Items");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) {
                    return rs.getBoolean("Inventory");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static void updateItemPlace(int id, boolean place) { Settings.mysql.update("UPDATE ContrayBU_Items SET Place='" + (place ? 1 : 0) + "' WHERE ID='" + id + "'"); }
    public static void updateItemFill(int id, boolean fill) { Settings.mysql.update("UPDATE ContrayBU_Items SET Fill='" + (fill ? 1 : 0) + "' WHERE ID='" + id + "'"); }
    public static void updateItemEmpty(int id, boolean empty) { Settings.mysql.update("UPDATE ContrayBU_Items SET Empty='" + (empty ? 1 : 0) + "' WHERE ID='" + id + "'"); }
    public static void updateItemInteract(int id, boolean interact) { Settings.mysql.update("UPDATE ContrayBU_Items SET Interact='" + (interact ? 1 : 0) + "' WHERE ID='" + id + "'"); }
    public static void updateItemInventory(int id, boolean inventory) { Settings.mysql.update("UPDATE ContrayBU_Items SET Inventory='" + (inventory ? 1 : 0) + "' WHERE ID='" + id + "'"); }

    public static List<Integer> getItemIds() {
        List<Integer> list = new ArrayList<Integer>();

        Connector mysql = Settings.mysql;
        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Items");

        try {
            while(rs.next()) {
                list.add(rs.getInt("ID"));
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return list;
    }

    // Plot-Management

    public static void registerPlot(OfflinePlayer op, Location loc) {
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        String world = loc.getWorld().getName();
        float pitch = loc.getPitch();
        float yaw = loc.getYaw();

        int player = getPlayerID(op);
        int state = 0;
        int noticed = 0;

        Settings.mysql.update("INSERT INTO ContrayBU_Plots (PlayerID, State, Noticed, World, X, Y, Z, Pitch, Yaw) VALUES ('" + player + "','" + state + "','" + noticed + "','" + world + "','" + x + "','" + y + "','" + z + "','" + pitch + "','" + yaw + "')");

    }

    public static boolean plotExists(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Plots");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) {
                    return true;
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static boolean getPlotNoticed(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Plots");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) {
                    return rs.getBoolean("Noticed");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static int getPlotID(int playerID) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Plots");

        try {
            while(rs.next()) {
                if(rs.getInt("PlayerID") == playerID) {
                    return rs.getInt("ID");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return -1;

    }

    public static PlotState getPlotState(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Plots");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) {
                    int state = rs.getInt("State");
                    if(state == 0) return PlotState.notRated;
                    if(state == 1) return PlotState.bad;
                    if(state == 2) return PlotState.good;
                    if(state == 3) return PlotState.accepted;
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return null;

    }

    public static Location getPlotSide(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Plots");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) {
                    double x = rs.getDouble("X");
                    double y = rs.getDouble("Y");
                    double z = rs.getDouble("Z");
                    World world = Bukkit.getWorld(rs.getString("World"));
                    float pitch = (float) rs.getDouble("Pitch");
                    float yaw = (float) rs.getDouble("Yaw");

                    Location loc = new Location(world, x, y, z, pitch, yaw);
                    return loc;
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return null;

    }

    public static OfflinePlayer getPlotPlayer(int id) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Plots");

        try {
            while(rs.next()) {
                if(rs.getInt("ID") == id) {
                    return Bukkit.getOfflinePlayer(UUID.fromString(getPlayerUUID(rs.getInt("PlayerID"))));
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return null;

    }

    public static void updatePlotNoticed(int id, boolean noticed) { Settings.mysql.update("UPDATE ContrayBU_Plots SET Noticed='" + (noticed ? 1 : 0) + "' WHERE ID='" + id + "'"); }
    public static void updatePlotState(int id, PlotState state) {
        int i = -1;
        if(state == PlotState.notRated) i = 0;
        if(state == PlotState.bad) i = 1;
        if(state == PlotState.good) i = 2;
        if(state == PlotState.accepted) i = 3;
        Settings.mysql.update("UPDATE ContrayBU_Plots SET State='" + i + "' WHERE ID='" + id + "'");
    }
    public static void updatePlotSide(int id, Location loc) {
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        String world = loc.getWorld().getName();
        double pitch = (double) loc.getPitch();
        double yaw = (double) loc.getYaw();

        Settings.mysql.update("UPDATE ContrayBU_Plots SET X='" + x + "' WHERE ID='" + id + "'");
        Settings.mysql.update("UPDATE ContrayBU_Plots SET Y='" + y + "' WHERE ID='" + id + "'");
        Settings.mysql.update("UPDATE ContrayBU_Plots SET Z='" + z + "' WHERE ID='" + id + "'");
        Settings.mysql.update("UPDATE ContrayBU_Plots SET World='" + world + "' WHERE ID='" + id + "'");
        Settings.mysql.update("UPDATE ContrayBU_Plots SET Pitch='" + pitch + "' WHERE ID='" + id + "'");
        Settings.mysql.update("UPDATE ContrayBU_Plots SET Yaw='" + yaw + "' WHERE ID='" + id + "'");
    }

    /*
     *  Server-Settings
     */

    public static void registerSettings(HashMap<String, String> einstellungen) {
        for(String einstellung : einstellungen.keySet()) {
            if(!settingExists(einstellung)) {
                Settings.mysql.update("INSERT INTO ContrayBU_Settings (Einstellung, Wert) VALUES ('" + einstellung + "','" + einstellungen.get(einstellung) + "')");
            }
        }
    }

    public static boolean settingExists(String einstellung) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Settings");

        try {
            while(rs.next()) {
                if(rs.getString("Einstellung").equalsIgnoreCase(einstellung)) {
                    return true;
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return false;

    }

    public static String getEinstellung(String einstellung) {

        Connector mysql = Settings.mysql;

        ResultSet rs = mysql.getResult("SELECT * FROM ContrayBU_Settings");

        try {
            while(rs.next()) {
                if(rs.getString("Einstellung").equalsIgnoreCase(einstellung)) {
                    return rs.getString("Wert");
                }
            }
        } catch(SQLException e) {
            if(Settings.devmode) e.printStackTrace();
            Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
        }

        return null;

    }

    public static void updateEinstellung(String einstellung, String wert) { Settings.mysql.update("UPDATE ContrayBU_Settings SET Wert='" + wert + "' WHERE Einstellung='" + einstellung + "'"); }

}
