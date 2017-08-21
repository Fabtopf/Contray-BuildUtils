package de.Fabtopf.BuildUtils.API;

import de.Fabtopf.BuildUtils.Utilities.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;

/**
 * Created by Fabi on 20.08.2017.
 */
public class Command {

    private String name;
    private String usage;
    private String description;
    private String permission;
    private String permissionMessage;

    private String[] permissions;

    private boolean allowOp;
    private boolean allowStar;

    private CommandExecutor executor;

    public Command(String name, String usage, String description, String permission, String[] permissions, String permissionMessage, boolean allowOp, boolean allowStar, CommandExecutor executor) {
        this.name = name;
        this.usage = usage;
        this.description = description;
        this.permission = permission;
        this.permissions = permissions;
        this.permissionMessage = permissionMessage;
        this.allowOp = allowOp;
        this.allowStar = allowStar;
        this.executor = executor;

        register();
    }

    private void register() {
        PluginCommand cmd = Main.getInstance().getCommand(name);

        cmd.setUsage(usage);
        cmd.setDescription(description);
        cmd.setPermission(permission);
        cmd.setPermissionMessage(ChatColor.translateAlternateColorCodes('&', permissionMessage));
        cmd.setExecutor(executor);
    }

    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public String getPermissionMessage() {
        return permissionMessage;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public boolean isAllowOp() {
        return allowOp;
    }

    public boolean isAllowStar() {
        return allowStar;
    }

    public CommandExecutor getExecutor() {
        return executor;
    }
}
