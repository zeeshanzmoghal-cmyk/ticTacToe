package com.zzm.dataBase.userData;

import com.zzm.dataBase.dbManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class changePasswordHandler {
    private final String passwordText;
    private final String usernameText;
    private final String confirmPasswordText;
    private final dbManager dbManager = new dbManager();
    public boolean changeSuccessPassword;

    public changePasswordHandler(String passwordText, String usernameText, String confirmPasswordText) {
        this.passwordText = passwordText;
        this.usernameText = usernameText;
        this.confirmPasswordText = confirmPasswordText;
    }

    public void getChangePasswordCreationHandler() {
        System.out.println("Change Password listener Active");
        System.out.println(usernameText + " " + passwordText + " " + confirmPasswordText);

        boolean passwordChangeSuccess = changePassword(usernameText, passwordText);

        if(passwordChangeSuccess) {
            changeSuccessPassword = true;
        } else {
            changeSuccessPassword = false;
        }

    }

    public boolean changePassword(String username, String newPassword) {
        String changePasswordInSQL = "UPDATE users SET password = ? WHERE username = ?";
        try(Connection connection = dbManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(changePasswordInSQL)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Username used:" + username);
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
