package com.zzm.dataBase.userData;

public class isUserNewPasswordIn {
    private static String NewPassword;
    private static boolean newPasswordIn = false;

    public isUserNewPasswordIn() {}

    public static void newPassword(String newPassword) {
        NewPassword = newPassword;
        newPasswordIn = true;
        System.out.println("New Password Stored");
    }

    public static void logout() {
        NewPassword = null;
        newPasswordIn = false;
    }

    public static boolean isPasswordIn() {
        return newPasswordIn;
    }

    public static String getPassword() {
        return NewPassword;
    }
}
