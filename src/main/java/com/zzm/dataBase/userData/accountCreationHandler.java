package com.zzm.dataBase.userData;

import com.zzm.dataBase.dbManager;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class accountCreationHandler {
    private final String usernameText;
    private final String passwordText;
    private final String confirmPasswordText;
    private final dbManager dbManager = new dbManager();
    public  boolean success;

    public accountCreationHandler(String usernameText, String passwordText, String confirmPasswordText) {
        this.usernameText = usernameText;
        this.passwordText = passwordText;
        this.confirmPasswordText = confirmPasswordText;
    }

    public void getaccountCreationHandler() {
        System.out.println("Create Account listener Active");
        System.out.println(usernameText + " " + passwordText + " " + confirmPasswordText);

        boolean checkExistance = accountExistsAlready(usernameText, passwordText);
        boolean creationSuccess = createAccount(usernameText, passwordText);


        if(creationSuccess){
            success = true;
        } else {
            success = false;
        }



    }

    public boolean createAccount(String username, String password){
        String createUserDataInSQL = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection connection = dbManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(createUserDataInSQL)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean accountExistsAlready(String username, String password) {
        final String exists = "SELECT 1 FROM users WHERE username = ? AND password = ?";
        try (Connection connection = dbManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(exists)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    }
