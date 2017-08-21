package de.Fabtopf.BuildUtils.API;

/**
 * Created by Fabi on 20.08.2017.
 */
public class CustomPerm {

    private String permission;

    private String[] permissions;

    private boolean allowOp;
    private boolean allowStar;

    public CustomPerm(String permission, String[] permissions, boolean allowOp, boolean allowStar) {
        this.permission = permission;
        this.permissions = permissions;
        this.allowOp = allowOp;
        this.allowStar = allowStar;
    }

    public String getPermission() {
        return permission;
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
}
