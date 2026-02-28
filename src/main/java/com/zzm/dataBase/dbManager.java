package com.zzm.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbManager {

    private static final String URL = "jdbc:mysql://localhost:3306/tic_tac_toe_db" + "?useSSL=false" + "&allowPublicKeyRetrieval=true" + "&serverTimezone=UTC";
    private static final String user = "tictactoe_user";
    private static final String password = "tttpass123";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
