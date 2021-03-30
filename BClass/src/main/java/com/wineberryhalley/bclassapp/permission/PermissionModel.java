package com.wineberryhalley.bclassapp.permission;

public class PermissionModel {
    String namePermission;

    public String getDescriptionPermission() {
        return descriptionPermission;
    }

    public void setDescriptionPermission(String descriptionPermission) {
        this.descriptionPermission = descriptionPermission;
    }

    String descriptionPermission;

    public String getNamePermission() {
        return namePermission;
    }

    public void setNamePermission(String namePermission) {
        this.namePermission = namePermission;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    String permissionCode;
}
