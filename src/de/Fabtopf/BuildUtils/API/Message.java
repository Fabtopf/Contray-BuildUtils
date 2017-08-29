package de.Fabtopf.BuildUtils.API;

import de.Fabtopf.BuildUtils.Utilities.Cache.Settings;
import de.Fabtopf.BuildUtils.Utilities.Main;

/**
 * Created by Fabi on 15.08.2017.
 */
public class Message {

    public static String plugin_name = Main.getInstance().getDescription().getName();
    public static String plugin_version = Main.getInstance().getDescription().getVersion();
    public static String plugin_author = Main.getInstance().getDescription().getAuthors().get(0);
    public static String plugin_prefix = "[" + plugin_name + "] ";
    public static String plugin_coloredprefix = "&8[&c" + plugin_name + "&8] &a";
    public static String plugin_disable = plugin_coloredprefix + "&cDas Plugin wird aufgrund eines plugininternen Scripts deaktiviert!";
    public static String plugin_noperm = Settings.prefix + "&4Fehler: &cDu hast keine Berechtigung um diesen Befehl auszuführen!";

    public static String mysql_alreadyconnected = plugin_coloredprefix + "&4Fehler: &cEs besteht bereits eine MySQL-Verbindung!";
    public static String mysql_notconnected = plugin_coloredprefix + "&4Fehler: &cEs besteht keine MySQL-Verbindung!";
    public static String mysql_couldnotconnect = plugin_coloredprefix + "&4Fehler: &cEs konnte keine MySQL-Verbindung hergestellt werden!";
    public static String mysql_couldnotdisconnect = plugin_coloredprefix + "&4Fehler: &cDie MySQL-Verbindung konnte nicht beendet werden!";
    public static String mysql_updatefail = plugin_coloredprefix + "&4Fehler: &cEs ist ein Fehler bei der Datenübertragung zur MySQL-Datenbank aufgetreten!";
    public static String mysql_resultfail = plugin_coloredprefix + "&4Fehler: &cEs ist ein Fehler beim Abrufen eines Datensatzes aus der MySQL-Datenbank aufgetreten!";
    public static String mysql_playerDoesntExist = Settings.prefix + "&4Fehler: &cDer angegebene Spieler ist nicht in der Datenbank registriert!";

    public static String worldmanagement_notPermittetToEnter = Settings.prefix + "&cDu darfst diese Welt nicht betreten!";
    public static String worldmanagement_notPermittetToStayIn = Settings.prefix + "&cDu darfst dich nicht in dieser Welt aufhalten!";
    public static String worldmanagement_notPermittetToChangeWorld = Settings.prefix + "&cDu darfst diese Welt nicht verändern!";
    public static String worldmanagement_successfullyTeleportet(String welt) { return Settings.prefix + "&eDu befindest dich nun in der Welt &9" + welt + "&e!"; }
    public static String worldmanagement_teleportFailed = Settings.prefix + "§cEs tut uns Leid, aber diese Welt existiert nicht!";

    public static String command_playerOnly = Settings.prefix + "&4Fehler: &cDieser Befehl kann nicht in der Konsole ausgeführt werden!";

    public static String itemprotection_notAllowed = Settings.prefix + "&4Fehler: &cDu darfst dieses Item nicht verwenden!";
    public static String itemprotection_notAllowedToInteractWith = Settings.prefix + "&4Fehler: &cDu darfst mit diesem Block nicht interagieren!";
    public static String itemprotection_notAllowedToPlace = Settings.prefix + "&4Fehler: &cDu darfst diesen Block nicht platzieren!";

    public static String plotmanager_notOnPlot = Settings.prefix + "&4Fehler: &cDu befindest dich nicht auf einem Plot!";
    public static String plotmanager_notOwnPlot = Settings.prefix + "&4Fehler: &cDu kannst nur eigene Plot abgeben!";
    public static String plotmanager_successfullyDone = Settings.prefix + "&aDu hast deinen Plot erfolgreich abgegeben!";
    public static String plotmanager_alreadyDone = Settings.prefix + "&4Fehler: &cDu hast deinen Plot bereits abgegeben!";
    public static String plotmanager_alreadyRating = Settings.prefix + "&4Fehler: &cDu bewertest bereits einen Plot!";

    public static String specialitem_noFreeSlot = Settings.prefix + "&4Fehler: &cEs ist kein leerer Slot im Inventar verfügbar!";
    public static String specialitem_speedFast = Settings.prefix + "&aDeine Geschwindigkeit ist jetzt &bschnell&a!";
    public static String specialitem_speedSlow = Settings.prefix + "&aDeine Geschwindigkeit ist jetzt &blangsam&a!";

}
