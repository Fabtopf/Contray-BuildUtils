package de.Fabtopf.BuildUtils.API;

import de.Fabtopf.BuildUtils.API.Enum.MessagerType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Fabi on 29.06.2017.
 */
public class Messager {

    public static void toConsole(MessagerType type, String message) {

        switch(type.name()) {

            case "COLORED":
                message = ChatColor.translateAlternateColorCodes('&', message);
                Bukkit.getConsoleSender().sendMessage(message);
                break;

            case "DELAYED":

                break;

            default:
                break;

        }

    }

    public static void toPlayer(MessagerType type, String message, Player p) {

        switch(type.name()) {

            case "COLORED":
                message = ChatColor.translateAlternateColorCodes('&', message);
                p.sendMessage(message);
                break;

            case "DELAYED":

                break;

            default:
                break;

        }

    }

}
