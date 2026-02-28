package com.zzm.dataBase.userData;

import com.zzm.dataBase.dbManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class loginHandler {
    private final String usernameText;
    private final String passwordText;
    public boolean success;

    public loginHandler(String usernameText, String passwordText) {
        this.usernameText = usernameText;
        this.passwordText = passwordText;
    }

    public void getLoginHandler() {
        System.out.println(usernameText + " " + passwordText);

        boolean loginSuccess = Login(usernameText, passwordText);

        if(loginSuccess) {
            success = true;
        } else {
            success = false;
        }
    }

    public boolean Login(String username, String password){
        String useUserDataInSQL = "SELECT * FROM users WHERE username = ? AND password = ?";
        try(Connection connection = dbManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(useUserDataInSQL);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet results = preparedStatement.executeQuery();

            if(results.next()) {
                return true;
            }
        } catch (Exception exception) {
            System.out.println("ERROR: " + exception.getMessage());
        }
        return false;
    }


}
