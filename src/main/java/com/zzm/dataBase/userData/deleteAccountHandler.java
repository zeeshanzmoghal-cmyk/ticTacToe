package com.zzm.dataBase.userData;

import com.zzm.dataBase.dbManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteAccountHandler {
    private final String passwordText;
    private final String usernameText;
    private final dbManager dbManager = new dbManager();
    public boolean deleteSuccess;

    public deleteAccountHandler(String passwordText, String usernameText) {
        this.passwordText = passwordText;
        this.usernameText = usernameText;
    }

    public void getDeleteAccountHandler() {
        System.out.println(usernameText + " " + passwordText + " Has been Deleted");

        boolean deletedSuccess = Deleted(usernameText, passwordText);

        if(deletedSuccess) {
            deleteSuccess = true;
        } else {
            deleteSuccess = false;
        }
    }

    public boolean Deleted(String username, String password) {
        String deleteDataInSQL = "DELETE FROM users WHERE username = ? AND password = ?";
        try(Connection connection = dbManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteDataInSQL);

            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("'" + username + "'");
            System.out.println("'" + password + "'");
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
