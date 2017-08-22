package de.Fabtopf.BuildUtils.API;

import de.Fabtopf.BuildUtils.API.Enum.MessagerType;
import de.Fabtopf.BuildUtils.Utilities.Cache.Settings;
import de.Fabtopf.BuildUtils.Utilities.Main;
import de.Fabtopf.BuildUtils.Utilities.Register;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;

/**
 * Created by Fabi on 29.06.2017.
 */
public class Connector {

    private String host;
    private int port;
    private String username;
    private String password;
    private String database;

    public Connection con;

    public Connector(String host, int port, String username, String password, String database) {
        this.port = port;
        this.host = host;
        this.username = username;
        this.password = password;
        this.database = database;
    }

    public void connect() {
        if(!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            } catch(SQLException e) {
                if(Settings.devmode) {
                    e.printStackTrace();
                }
                Messager.toConsole(MessagerType.COLORED, Message.mysql_couldnotconnect);
                Register.disable();
            }
        } else {
            Messager.toConsole(MessagerType.COLORED, Message.mysql_alreadyconnected);
            return;
        }
    }

    public void disconnect() {
        if(isConnected()) {
            try {
                con.close();
            } catch(SQLException e) {
                if(Settings.devmode) {
                    e.printStackTrace();
                }
                Messager.toConsole(MessagerType.COLORED, Message.mysql_couldnotdisconnect);
            }
        }
    }

    public void reconnect() {
        if(isConnected()) disconnect();
        connect();
    }

    public boolean isConnected() {
        return con != null;
    }

    public void update(String qry) {
        new BukkitRunnable() {

            @Override
            public void run() {
                if(isConnected()) {

                    try {
                        Statement st = con.createStatement();
                        st.execute(qry);
                        st.close();
                    } catch(SQLException e) {
                        if(Settings.devmode) {
                            e.printStackTrace();
                        }

                        Messager.toConsole(MessagerType.COLORED, Message.mysql_updatefail);
                    }

                } else {
                    Messager.toConsole(MessagerType.COLORED, Message.mysql_notconnected);
                    reconnect();

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            update(qry);
                        }
                    }.runTaskLaterAsynchronously(Main.getInstance(), 2 * 20L);
                }
            }

        }.runTaskAsynchronously(Main.getInstance());
    }

    public ResultSet getResult(String qry) {

        ResultSet rs = null;

        if (isConnected()) {

            try {
                rs = con.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                if (Settings.devmode) {
                    e.printStackTrace();
                }

                Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
            }

        } else {
            Messager.toConsole(MessagerType.COLORED, Message.mysql_notconnected);
            reconnect();
        }

        return rs;
    }

    public boolean tableExists(String table) {
        if(isConnected()) {
            try {
                return con.createStatement().executeQuery("SHOW TABLES LIKE '" + table + "'").next();
            } catch(SQLException e) {
                if(Settings.devmode) {
                    e.printStackTrace();
                }

                Messager.toConsole(MessagerType.COLORED, Message.mysql_resultfail);
            }
        } else {
            Messager.toConsole(MessagerType.COLORED, Message.mysql_notconnected);
            reconnect();
        }

        return false;
    }

}

