package com.zzm.appGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.security.Key;
import javax.swing.*;
import com.zzm.appGUI.*;
import com.zzm.dataBase.userData.accountCreationHandler;
import com.zzm.dataBase.userData.isUserLoggedIn;
import com.zzm.dataBase.userData.isUserPasswordIn;
import com.zzm.dataBase.userData.loginHandler;
import com.zzm.imageRenderAction.imageRenderer;

public class launcherGUI {

    public String gameVersion = "v1.0.0";

    JFrame jFrame = new JFrame("TiccyToeTac " + gameVersion + " Launcher");

    JTextField usernameField = new JTextField(25);
    JPasswordField passwordField = new JPasswordField(25);

    private isUserLoggedIn isUserLoggedIn = new isUserLoggedIn();
    private isUserPasswordIn isUserPasswordIn = new isUserPasswordIn();

    public JFrame getjFrame() {
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);

        ImageIcon iconImage = imageRenderer.load("/imageLibrary/TiccyToeTac-Logo.png");

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;

        JPanel loginPanel = new JPanel(new GridBagLayout());
        JPanel createAccPanel = new JPanel(new GridBagLayout());
        JPanel loggedInLaucherPanel = new JPanel(new GridBagLayout());



        JLabel userLabel = new JLabel("Username:");
        JLabel userLabelCreateAcc = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel passwordLabelCreateAcc = new JLabel("Password");
        JLabel confirmPasswordLabelCreateAcc = new JLabel("Confirm Password");
        JLabel noAccLabel = new JLabel("No Account?");
        JLabel noLabel = new JLabel("");
        JLabel loginPageTitle = new JLabel("Login to TiccyToeTac");
        JLabel createAccPageTitle = new JLabel("Create TiccyToeTac Account");
        JLabel launcherTitle = new JLabel("TiccyToeTac");
        JLabel launcherSubTitle = new JLabel(gameVersion);

        loginPageTitle.setFont(new Font("Adlam Display", Font.PLAIN, 24));
        createAccPageTitle.setFont(new Font("Adlam Display", Font.PLAIN, 24));
        launcherTitle.setFont(new Font("Adlam Display", Font.PLAIN, 55));
        launcherSubTitle.setFont(new Font("Adlam DIsplay", Font.PLAIN, 15));

        JTextField usernameFieldCreateAcc = new JTextField(25);

        JPasswordField passwordFieldCreateAcc = new JPasswordField(25);
        JPasswordField confirmPasswordCreateAcc = new JPasswordField(25);

        JButton loginButton = new JButton("Log in");
        Action loginAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        };
        JButton createAccButton = new JButton("Create Account");
        JButton accCreationButton = new JButton("Create Account");
        Action CreatAccAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accCreationButton.doClick();
            }
        };
        JButton createAccBackButton = new JButton("⬅️ Back");
        JButton launchGameButton = new JButton("PLAY GAME");
        Action gameLaunchAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchGameButton.doClick();
            }
        };

        launchGameButton.setSize(1920/7, 1080/7);
        launchGameButton.setFont(new Font("Adlam Display", Font.PLAIN, 23));


        jFrame.setIconImage(iconImage.getImage());
        jFrame.setSize(720, 360);
        jFrame.setLocation(412, 400/2);
        jFrame.setVisible(true);
        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(createAccPanel, "CREATE");
        mainPanel.add(loggedInLaucherPanel, "LAUNCHER");
        jFrame.add(mainPanel);

        cardLayout.show(mainPanel, "LOGIN");

        loginButton.addActionListener(e -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException interruptedException) {
                System.out.println("Didn't wait");
            }

            String usernameText = usernameField.getText();
            String passwordText = passwordField.getText();

            loginHandler loginFunction = new loginHandler(usernameText, passwordText);
            loginFunction.getLoginHandler();

            if(!usernameText.isEmpty() && !passwordText.isEmpty()) {
                if(loginFunction.success == true) {
                    cardLayout.show(mainPanel, "LAUNCHER");
                    System.out.println("Logged in");
                    isUserLoggedIn.login(usernameText);
                    isUserPasswordIn.loginPassword(passwordText);
                } else if(loginFunction.success == false) {
                    JOptionPane.showMessageDialog(
                            jFrame,
                            "Login Failed, Please try again",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if(!usernameText.isEmpty() && passwordText.isEmpty()) {
                JOptionPane.showMessageDialog(
                        jFrame,
                        "Please enter your password",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } else if(usernameText.isEmpty() && !passwordText.isEmpty()) {
                JOptionPane.showMessageDialog(
                        jFrame,
                        "Please enter your username",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(
                        jFrame,
                        "Please enter your credentials",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

            usernameField.setText("");
            passwordField.setText("");
        });

        InputMap inputMapLogin = loginButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMapLogin.put(enterKey, "loginPerformed");

        loginButton.getActionMap().put("loginPerformed", loginAction);

        createAccButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "CREATE");
        });

        createAccBackButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "LOGIN");
            usernameFieldCreateAcc.setText("");
            passwordFieldCreateAcc.setText("");
            confirmPasswordCreateAcc.setText("");
        });

        accCreationButton.addActionListener(e -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException interruptedException) {
                System.out.println("Didn't wait");
            }
            String usertext = usernameFieldCreateAcc.getText();
            String passwordText = passwordFieldCreateAcc.getText();
            String confirmPasswordText = confirmPasswordCreateAcc.getText();
            if(confirmPasswordText.equals(passwordText) && !usertext.isEmpty() && !confirmPasswordText.isEmpty() && !passwordText.isEmpty()) {
                accountCreationHandler createAccountHandler = new accountCreationHandler(usertext, passwordText, confirmPasswordText);
                createAccountHandler.getaccountCreationHandler();

                if(createAccountHandler.success == true) {
                    cardLayout.show(mainPanel, "LAUNCHER");
                    System.out.println("Account Created");
                    isUserLoggedIn.login(usertext);
                    isUserPasswordIn.loginPassword(passwordText);

                } else if(createAccountHandler.success == false) {
                    JOptionPane.showMessageDialog(
                            jFrame,
                            "Account Creation Failed, Please try again",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if(usertext.isEmpty() && !confirmPasswordText.isEmpty() && !passwordText.isEmpty()){
                JOptionPane.showMessageDialog(jFrame,
                        "Please enter a username",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } else if(!confirmPasswordText.equals(passwordText) && confirmPasswordText.isEmpty()){
                    JOptionPane.showMessageDialog(jFrame,
                        "Please confirm your password",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } else if(!confirmPasswordText.equals(passwordText) && passwordText.isEmpty()) {
                JOptionPane.showMessageDialog(jFrame,
                        "Please enter a password",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } else if(usertext.isEmpty() && confirmPasswordText.isEmpty() && passwordText.isEmpty()) {
                JOptionPane.showMessageDialog(jFrame,
                        "Please enter your credentials",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } else if(!usertext.isEmpty() && confirmPasswordText.isEmpty() && passwordText.isEmpty()) {
                JOptionPane.showMessageDialog(jFrame,
                        "Please make your passwords",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
            usernameFieldCreateAcc.setText("");
            passwordFieldCreateAcc.setText("");
            confirmPasswordCreateAcc.setText("");
        });

        InputMap inputMapAccCreation = accCreationButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMapAccCreation.put(enterKey, "AccCreationAction");

        accCreationButton.getActionMap().put("AccCreationAction", CreatAccAction);


        launchGameButton.addActionListener(e -> {
            loggedInLaucherPanel.remove(launchGameButton);
            try {
                Thread.sleep(2500);
            } catch (InterruptedException interruptedException) {
                System.out.println("System Didn't Pause");
            }
            jFrame.dispose();
            new gameGUI().getGameFrame();
        });

        InputMap inputMapLaunch = launchGameButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMapLaunch.put(enterKey, "LaunchActioner");

        launchGameButton.getActionMap().put("LaunchActioner", gameLaunchAction);


        /* Log-in Panel */


        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 20, 0);
        loginPanel.add(loginPageTitle, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 15, 225);
        loginPanel.add(userLabel, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 15, 225);
        loginPanel.add(passwordLabel, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 0, 5, 225);
        loginPanel.add(noLabel, gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(7, 0, 10, 225);
        loginPanel.add(noAccLabel, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 125, 15, 0);
        loginPanel.add(usernameField, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(3, 125, 15, 0);
        loginPanel.add(passwordField, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 125, 5, 0);
        loginPanel.add(loginButton, gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(0, 125, 5, 0);
        loginPanel.add(createAccButton, gridBagConstraints);


        /* Create Account Panel */


        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 20, 0);
        createAccPanel.add(createAccPageTitle, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 15, 225);
        createAccPanel.add(userLabelCreateAcc, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 15, 225);
        createAccPanel.add(passwordLabelCreateAcc, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 0, 5, 250);
        createAccPanel.add(confirmPasswordLabelCreateAcc, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 125, 15, 0);
        createAccPanel.add(usernameFieldCreateAcc, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(3, 125, 15, 0);
        createAccPanel.add(passwordFieldCreateAcc, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 125, 5, 0);
        createAccPanel.add(confirmPasswordCreateAcc, gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(0, 125, 5, 0);
        createAccPanel.add(accCreationButton, gridBagConstraints);

        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(50, 250, 5, 350);
        createAccPanel.add(createAccBackButton, gridBagConstraints);


        /* Launcher Panel */


        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 3 ,0);
        loggedInLaucherPanel.add(launcherTitle, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 25 ,0);
        loggedInLaucherPanel.add(launcherSubTitle, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(10, 0, 25,0);
        loggedInLaucherPanel.add(launchGameButton, gridBagConstraints);

        System.out.println("Window Opened");
        return jFrame;
    }
}
