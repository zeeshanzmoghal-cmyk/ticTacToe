package com.zzm.dataBase.userData;

public class isUserLoggedIn {

    private static String username;
    private static boolean loggedIn = false;

    public isUserLoggedIn() {}

    public static void login(String user) {
        username = user;
        loggedIn = true;
        System.out.println("Username Stored");
    }

    public static void logout() {
        username = null;
        loggedIn = false;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static String getUsername() {
        return username;
    }
}
