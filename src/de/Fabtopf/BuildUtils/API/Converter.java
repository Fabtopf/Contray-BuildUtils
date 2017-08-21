package de.Fabtopf.BuildUtils.API;

import de.Fabtopf.BuildUtils.Utilities.MySQL.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Fabi on 15.08.2017.
 */
public class Converter {

    public static List<String> stringToList(String ...args) {
        List<String> liste = new ArrayList<String>();
        for(String arg : args) {
            liste.add(arg);
        }
        return liste;
    }

    public static String[] stringToArray(String ...args) {
        String[] array = new String[args.length];
        int i = 0;
        for(String arg : args) {
            array[i] = arg;
            i++;
        }
        return array;
    }

    public static String opListToIdString(List<OfflinePlayer> list) {
        String l = "";
        for(OfflinePlayer op : list) {
            l = l + ", " + Utils.getPlayerID(op);
        }

        l = l.replaceFirst(", ", "");
        return l;
    }

    public static List<OfflinePlayer> idStringToOpList(String ids) {
        List<OfflinePlayer> oplist = new ArrayList<OfflinePlayer>();
        String[] idlist = ids.split(", ");

        for(String id : idlist) {
            if(!id.equals("")) {
                OfflinePlayer op = null;
                int i = Integer.valueOf(id);
                String uuid = Utils.getPlayerUUID(i);
                if (uuid != null && !uuid.equals("")) op = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
                if (op != null) oplist.add(op);
            }
        }

        return oplist;
    }

}
