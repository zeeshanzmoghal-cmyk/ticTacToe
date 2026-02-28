package com.zzm.dataBase.userData;

public class isUserPasswordIn {

    private static String Password;
    private static boolean passwordIn = false;

    public isUserPasswordIn() {}

    public static void loginPassword(String password) {
        Password = password;
        passwordIn = true;
        System.out.println("Password Stored");
    }

    public static void logout() {
        Password = null;
        passwordIn = false;
    }

    public static boolean isPasswordIn() {
        return passwordIn;
    }

    public static String getPassword() {
        return Password;
    }
}
