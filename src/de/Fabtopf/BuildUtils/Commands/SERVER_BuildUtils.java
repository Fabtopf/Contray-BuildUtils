package de.Fabtopf.BuildUtils.Commands;

import de.Fabtopf.BuildUtils.API.Converter;
import de.Fabtopf.BuildUtils.API.CustomPerm;
import de.Fabtopf.BuildUtils.API.Enum.MessagerType;
import de.Fabtopf.BuildUtils.API.Manager.PermissionManager;
import de.Fabtopf.BuildUtils.API.Message;
import de.Fabtopf.BuildUtils.API.Messager;
import de.Fabtopf.BuildUtils.Commands.Inventory.INV_BuildUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Fabi on 20.08.2017.
 */
public class SERVER_BuildUtils implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.openinv", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                INV_BuildUtils.openGUI(p);
                return true;
            } else {
                Messager.toPlayer(MessagerType.COLORED, Message.plugin_noperm, p);
                return true;
            }
        } else {
            Messager.toConsole(MessagerType.COLORED, Message.command_playerOnly);
            return true;
        }

    }

}
