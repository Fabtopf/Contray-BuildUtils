package de.Fabtopf.BuildUtils.API.Manager;

import de.Fabtopf.BuildUtils.API.Command;
import de.Fabtopf.BuildUtils.API.CustomPerm;
import org.bukkit.entity.Player;

/**
 * Created by Fabi on 20.08.2017.
 */
public class PermissionManager {

    public static boolean check(Player p, Command cmd) {

        if(p.hasPermission(cmd.getPermission())) return true;
        if(cmd.isAllowOp() && p.isOp()) return true;
        if(cmd.isAllowStar() && p.hasPermission("*")) return true;

        for(String perm : cmd.getPermissions()) {
            if(p.hasPermission(perm)) return true;
        }

        return false;

    }

    public static boolean check(Player p, CustomPerm perm) {

        if(p.hasPermission(perm.getPermission())) return true;
        if(perm.isAllowOp() && p.isOp()) return true;
        if(perm.isAllowStar() && p.hasPermission("*")) return true;

        for(String permission : perm.getPermissions()) {
            if(p.hasPermission(permission)) return true;
        }

        return false;

    }

}
