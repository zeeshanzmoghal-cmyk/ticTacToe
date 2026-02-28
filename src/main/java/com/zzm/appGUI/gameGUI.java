package com.zzm.appGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.*;
import javax.swing.border.Border;
import com.zzm.appGUI.splashText.*;
import com.zzm.dataBase.userData.*;
import com.zzm.imageRenderAction.imageRenderer;
import com.zzm.gameFunctions.*;

public class gameGUI {

    String version = new launcherGUI().gameVersion;
    String splashSubTitle = new titlePageSplashText().splashSubtitle;
    public int XPCount;
    private Random xpChallengeRandom = new Random();
    public int xpChallenge = xpChallengeRandom.nextInt(1000);

    JFrame gameFrame = new JFrame("TiccyToeTac " + version);

    CardLayout gameLayout = new CardLayout();
    JPanel gamePanel = new JPanel(gameLayout);

    private singlePlayerBoardFunctions singlePlayerLogic;
    private intermediateBoardFunctions interMediateBoardLogic;
    private hardBoardFunctions hardBoardLogic;

    private JLabel turnLabel;
    private JLabel turnLabel2;
    private JLabel turnLabel3;
    private JLabel XPLabel = new JLabel();
    private JLabel XPChallengeLabel = new JLabel();

    private isUserLoggedIn isUserLoggedIn = new isUserLoggedIn();
    private isUserPasswordIn isUserPasswordIn = new isUserPasswordIn();

    private boolean passwordRevealer = false;

    public JFrame getGameFrame() {
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);

        Border highLightedBorder = BorderFactory.createLineBorder(Color.RED);
        Border clearBorder = BorderFactory.createLineBorder(Color.WHITE);

        ImageIcon preSettingIcon = imageRenderer.load("/imageLibrary/TiccyToeTac-Logo-Setting-Buton.png");
        ImageIcon iconImage = imageRenderer.load("/imageLibrary/TiccyToeTac-Logo.png");

        Image preSettingImage = preSettingIcon.getImage();
        Image settingImage = preSettingImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon settingIcon = new ImageIcon(settingImage);


        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;

        JPanel titlePanel = new JPanel(new GridBagLayout());
        JPanel singlePlayerPanel = new JPanel(new GridBagLayout());
        JPanel singleplayerPlayBotPanel = new JPanel(new GridBagLayout());
        JPanel intermediateBotPanel = new JPanel(new GridBagLayout());
        JPanel hardBotPanel = new JPanel(new GridBagLayout());
        JPanel practiceModePanel = new JPanel(new GridBagLayout());
        JPanel settingsPanel = new JPanel(new GridBagLayout());
        JPanel messagesPanel = new JPanel(new GridBagLayout());
        JPanel passwordChangePanel = new JPanel(new GridBagLayout());

        JLabel titleLabel = new JLabel("TiccyToeTac");
        JLabel subTitleLabel = new JLabel(splashSubTitle);
        JLabel settingsPageLabel = new JLabel("Settings");
        JLabel youVSbotLabel = new JLabel("      You VS. X_eni_O_n BOT     ");
        JLabel youVSbotLabel2 = new JLabel("     You VS. X_ab_O_r BOT     ");
        JLabel youVSbotLabel3 = new JLabel("     You VS. e_X_cel_O_n BOT     ");
        turnLabel = new JLabel();
        turnLabel2 = new JLabel();
        turnLabel3 = new JLabel();
        JLabel usernameLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JLabel passwordDummySettingsLabel = new JLabel();
        JLabel changePasswordLabel = new JLabel("Change Your Password");
        JLabel newPasswordLabel = new JLabel("New Password");
        JLabel confirmNewPasswordLabel = new JLabel("Confirm Password");


        JPasswordField changePassowordField = new JPasswordField(35);
        JPasswordField confirmChangePasswordField = new JPasswordField(35);

        JButton settingsButton = new JButton(settingIcon);
        JButton singlePlayerButton = new JButton("Play a bot");
        JButton practiceModeButton = new JButton("Single divice mode");
        JButton exitButton = new JButton("X");
        JButton messageButton = new JButton("msg's");
        JButton playSinglePlayerButton = new JButton("Easy Mode");
        JButton intermediateModeButton = new JButton("Intermediate Mode");
        JButton hardModeButton = new JButton("Hard Mode");
        JButton singlePlayerMenuBackButton = new JButton("⬅️ Back");
        JButton requestChallengeButton = new JButton("Request a Challenge");
        JButton leaderBoardButton = new JButton("Leaderboard");
        JButton[] practiceModeButtons = new JButton[9];
        JButton practiceModeBackButton = new JButton("⬅️ Back");
        JButton[] playTheBotButtons = new JButton[9];
        JButton[] playTheItermediateBotButtons = new JButton[9];
        JButton[] playTheHardBotButtons = new JButton[9];
        JButton exitBotGameButton = new JButton("Exit Game");
        JButton exitIntermediateBotGameButton = new JButton("Exit Game");
        JButton exitHardBotGameButton = new JButton("Exit Game");
        JButton exitSettingsButton = new JButton("⬅️ Back");
        JButton revealPasswordButton = new JButton("Reveal Password");
        JButton changePasswordButton = new JButton("Change Password");
        JButton backToSettingsFromPasswordResetButton = new JButton("⬅️ Back");
        JButton submitPasswordChangeRequestButton = new JButton("Submit");
        JButton deleteAccButton = new JButton("Delete Account");
        Action changePasswordAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitPasswordChangeRequestButton.doClick();
            }
        };


        settingsButton.setSize(200, 200);
        settingsButton.setRolloverEnabled(true);
        settingsButton.setPreferredSize(new Dimension(settingIcon.getIconWidth(), settingIcon.getIconHeight()));
        settingsButton.setContentAreaFilled(false);
        settingsButton.setOpaque(false);
        settingsButton.setBorder(clearBorder);
        settingsButton.addChangeListener(e -> {
            ButtonModel buttonModel = settingsButton.getModel();
            if (buttonModel.isRollover()) {
                settingsButton.setBorder(highLightedBorder);
                System.out.println("Roll Over Enabled");
            } else {
                settingsButton.setBorder(clearBorder);
            }
        });
        settingsButton.setFocusPainted(false);

        deleteAccButton.setForeground(Color.RED);

        singlePlayerButton.addActionListener(e -> {
            gameLayout.show(gamePanel, "SINGLEPLAYER");
        });

        practiceModeButton.addActionListener(e -> {
            try {
                Thread.sleep(750);
            } catch (InterruptedException interruptedException) {
                System.out.println("Practice Mode Didn't wait");
            }
            gameLayout.show(gamePanel, "PRACTICEMODE");
        });


        settingsButton.addActionListener(e -> {
            try {
                Thread.sleep(750);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            gameLayout.show(gamePanel, "SETTINGS");
        });

        exitButton.addActionListener(e -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException interruptedException) {
                System.out.println("Game Shutdown Didn't wait");
            }
            gameFrame.dispose();
        });

        messageButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(gameFrame, "Messages Listener Pressed");
            gameLayout.show(gamePanel, "MESSAGES");
        });

        playSinglePlayerButton.addActionListener(e -> {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            gameLayout.show(gamePanel, "PLAYBOT");
            JOptionPane.showMessageDialog(gameFrame, "Game has started, GO!", "", JOptionPane.INFORMATION_MESSAGE);
        });

        singlePlayerMenuBackButton.addActionListener(e -> {
            gameLayout.show(gamePanel, "TITLEPAGE");
        });

        requestChallengeButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(gameFrame, "Request Challenge Listener Activated");
        });

        leaderBoardButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(gameFrame, "LeaderBoard Listener Activated");
        });

        practiceModeBackButton.addActionListener(e -> {
            int leavePracticeMode = JOptionPane.showConfirmDialog(
                    gameFrame,
                    "It won't save, You sure you want to leave?",
                    "HOLD UP!",
                    JOptionPane.YES_NO_OPTION
            );

            if (leavePracticeMode == JOptionPane.YES_OPTION) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println("Practice Mode Return Didn't wait");
                }
                gameLayout.show(gamePanel, "TITLEPAGE");
                new practiceMode(practiceModeButtons).reset(false);
            } else {
                System.out.println("Nothing Happened");
            }
        });

        exitBotGameButton.addActionListener(e -> {
            int exitGame = JOptionPane.showConfirmDialog(gameFrame,
                    "Are you sure you want to exit?",
                    "WAIT",
                    JOptionPane.YES_NO_OPTION
            );
            if (exitGame == JOptionPane.YES_OPTION) {
                gameLayout.show(gamePanel, "TITLEPAGE");
                singlePlayerLogic.reset(false);
            } else {
                System.out.println("Bot Game hasn't ended");
            }
        });

        exitIntermediateBotGameButton.addActionListener(e -> {
            int exitGame = JOptionPane.showConfirmDialog(gameFrame,
                    "Are you sure you want to exit?",
                    "WAIT",
                    JOptionPane.YES_NO_OPTION
            );
            if (exitGame == JOptionPane.YES_OPTION) {
                gameLayout.show(gamePanel, "TITLEPAGE");
                interMediateBoardLogic.reset(false);
            } else {
                System.out.println("Bot Game hasn't ended");
            }
        });

        exitHardBotGameButton.addActionListener(e -> {
            int exitGame = JOptionPane.showConfirmDialog(gameFrame,
                    "Are you sure you want to exit?",
                    "WAIT",
                    JOptionPane.YES_NO_OPTION
            );
            if (exitGame == JOptionPane.YES_OPTION) {
                gameLayout.show(gamePanel, "TITLEPAGE");
                hardBoardLogic.reset(false);
            } else {
                System.out.println("Bot Game hasn't ended");
            }
        });

        changePasswordButton.addActionListener(e -> {
            gameLayout.show(gamePanel, "CHANGE-PASSWORD");
        });

        backToSettingsFromPasswordResetButton.addActionListener(e -> {
            gameLayout.show(gamePanel, "SETTINGS");
        });

        submitPasswordChangeRequestButton.addActionListener(e -> {
            String newPasswordText = changePassowordField.getText();
            String confirmNewPasswordText = confirmChangePasswordField.getText();

            if(!newPasswordText.isEmpty() && !confirmNewPasswordText.isEmpty()) {
                changePasswordHandler changePasswordHandler = new changePasswordHandler(newPasswordText, isUserLoggedIn.getUsername(), confirmNewPasswordText);
                changePasswordHandler.getChangePasswordCreationHandler();

                if(changePasswordHandler.changeSuccessPassword) {
                    try {
                        Thread.sleep(750);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(gameFrame,
                            "Your password's been changed",
                            "SUCCESS",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    gameFrame.dispose();
                    JOptionPane.showMessageDialog(gameFrame,
                            "App Restarted After Password Change\nPlease re-login for authentification",
                            "System",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    launcherGUI launcherGUI = new launcherGUI();
                    launcherGUI.getjFrame();
                    isUserNewPasswordIn.newPassword(newPasswordText);
                    passwordRevealer = false;
                    updatePasswordUI(passwordLabel);
                    System.out.println(isUserNewPasswordIn.getPassword());
                } else if(!changePasswordHandler.changeSuccessPassword) {
                    JOptionPane.showMessageDialog(
                            gameFrame,
                            "Password Change Failed, Please try again",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else if(newPasswordText.isEmpty() && !confirmNewPasswordText.isEmpty()) {
                JOptionPane.showMessageDialog(
                        gameFrame,
                        "Please state your password",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                );
            } else if(newPasswordText.isEmpty() && confirmNewPasswordText.isEmpty()) {
                JOptionPane.showMessageDialog(
                        gameFrame,
                        "Please enter your credentials",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                );
            } else if(!newPasswordText.isEmpty() && confirmNewPasswordText.isEmpty()) {
                JOptionPane.showMessageDialog(
                        gameFrame,
                        "Please confirm your password",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                );
            } else if(!newPasswordText.equals(confirmNewPasswordText) || !confirmNewPasswordText.equals(newPasswordText)) {
                JOptionPane.showMessageDialog(
                        gameFrame,
                        "Please enter for fields",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            changePassowordField.setText("");
            confirmChangePasswordField.setText("");

        });

        intermediateModeButton.addActionListener(e -> {
            gameLayout.show(gamePanel, "INTERMEDIATE-BOT");
        });

        hardModeButton.addActionListener(e -> {
            gameLayout.show(gamePanel, "HARD-BOT");
            try {
                Thread.sleep(750);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });

        deleteAccButton.addActionListener(e -> {
            int deleteAcc = JOptionPane.showConfirmDialog(
                    gameFrame,
                    "Are you sure you want to delete your account?",
                    "HOLD UP!",
                    JOptionPane.YES_NO_OPTION
                    );
            if(deleteAcc == JOptionPane.YES_OPTION) {
                deleteAccountHandler deleteAccountFunction = new deleteAccountHandler(isUserLoggedIn.getUsername(), isUserPasswordIn.getPassword());
                deleteAccountFunction.getDeleteAccountHandler();
                if(deleteAccountFunction.deleteSuccess) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    gameFrame.dispose();
                    launcherGUI launcherGUI = new launcherGUI();
                    JOptionPane.showMessageDialog(
                            gameFrame,
                            "Account Deleted",
                            "SUCCESS",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    launcherGUI.getjFrame();
                } else {
                    JOptionPane.showMessageDialog(
                            gameFrame,
                            "Account deletion failed",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
                System.out.println("Account delete button pressed");
            } else {
                System.out.println("delete canceled");
            }
        });



        InputMap inputMapChangePassword = submitPasswordChangeRequestButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMapChangePassword.put(enterKey, "changePasswordPerformed");

        submitPasswordChangeRequestButton.getActionMap().put("changePasswordPerformed", changePasswordAction);

        titleLabel.setFont(new Font("Adlam Display", Font.BOLD, 125));
        subTitleLabel.setFont(new Font("Adlam Display", Font.PLAIN, 25));
        singlePlayerButton.setFont(new Font("Adlam Display", Font.PLAIN, 40));
        practiceModeButton.setFont(new Font("Adlam Display", Font.PLAIN, 40));
        exitButton.setFont(new Font("Adlam Display", Font.PLAIN, 20));
        messageButton.setFont(new Font("Adlam Display", Font.PLAIN, 20));
        playSinglePlayerButton.setFont(new Font("Adlam Display", Font.PLAIN, 50));
        intermediateModeButton.setFont(new Font("Adlam Display", Font.PLAIN, 50));
        hardModeButton.setFont(new Font("Adlam Display", Font.PLAIN, 50));
        requestChallengeButton.setFont(new Font("Adlam Display", Font.PLAIN, 40));
        deleteAccButton.setFont(new Font("Adlam Display", Font.PLAIN, 20));
        youVSbotLabel.setFont(new Font("Adlam Display", Font.PLAIN, 30));
        turnLabel.setFont(new Font("Adlam Display", Font.PLAIN, 40));
        turnLabel2.setFont(new Font("Adlam Display", Font.PLAIN, 40));
        turnLabel3.setFont(new Font("Adlam Display", Font.PLAIN, 40));
        settingsPageLabel.setFont(new Font("Adlam Display", Font.PLAIN, 100));
        usernameLabel.setFont(new Font("Adlam Display", Font.PLAIN, 40));
        passwordLabel.setFont(new Font("Adlam Display", Font.PLAIN, 40));
        changePasswordLabel.setFont(new Font("Adlam Display", Font.PLAIN, 100));
        newPasswordLabel.setFont(new Font("Adlam Display", Font.PLAIN, 25));
        confirmNewPasswordLabel.setFont(new Font("Adlam Display", Font.PLAIN, 25));
        youVSbotLabel2.setFont(new Font("Adlam Display", Font.PLAIN, 30));
        youVSbotLabel3.setFont(new Font("Adlam Display", Font.PLAIN, 30));
        XPLabel.setFont(new Font("Adlam Display", Font.PLAIN, 30));
        XPChallengeLabel.setFont(new Font("Adlam Display", Font.PLAIN, 30));


        String XPChallenge = String.valueOf(xpChallenge);


        gameFrame.setIconImage(iconImage.getImage());
        gameFrame.setSize(1920, 1080);
        gameFrame.setVisible(true);
        gamePanel.add(titlePanel, "TITLEPAGE");
        gamePanel.add(singlePlayerPanel, "SINGLEPLAYER");
        gamePanel.add(singleplayerPlayBotPanel, "PLAYBOT");
        gamePanel.add(practiceModePanel, "PRACTICEMODE");
        gamePanel.add(settingsPanel, "SETTINGS");
        gamePanel.add(passwordChangePanel, "CHANGE-PASSWORD");
        gamePanel.add(messagesPanel, "MESSAGES");
        gamePanel.add(intermediateBotPanel, "INTERMEDIATE-BOT");
        gamePanel.add(hardBotPanel, "HARD-BOT");
        gameFrame.add(gamePanel);
        gameLayout.show(gamePanel, "TITLEPAGE");
        JOptionPane.showMessageDialog(
                gameFrame,
                "Lets see if you can make it past " + XPChallenge + " XP",
                "SESSION CHALLENGE",
                JOptionPane.INFORMATION_MESSAGE
        );



        /* Title Panel */


        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 7, 0);
        titlePanel.add(titleLabel, gridBagConstraints);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 1300, 0, 0);
        titlePanel.add(exitButton, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 28, 0);
        titlePanel.add(subTitleLabel, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 20, 0);
        titlePanel.add(singlePlayerButton, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 0, 10, 0);
        titlePanel.add(practiceModeButton, gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        titlePanel.add(XPLabel, gridBagConstraints);

        XPChallengeLabel.setText("Current Challenge: Reach " + XPChallenge + " XP");
        XPChallengeLabel.setForeground(Color.getHSBColor(0.15f, 0.8f, 0.7f));

        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(0, 0, 50, 0);
        titlePanel.add(XPChallengeLabel, gridBagConstraints);

        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new Insets(0, 0, 5, 1200);
        titlePanel.add(settingsButton, gridBagConstraints);


        /*Single Player Parent Panel */


        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 20, 0);
        singlePlayerPanel.add(playSinglePlayerButton, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 20, 0);
        singlePlayerPanel.add(intermediateModeButton, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 20, 0);
        singlePlayerPanel.add(hardModeButton, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        singlePlayerPanel.add(singlePlayerMenuBackButton, gridBagConstraints);


        /* Practice Mode Panel*/


        for (int i = 0; i < 9; i++) {
            int row = i / 3;
            int col = i % 3;
            int index = i;

            gridBagConstraints.gridx = col;
            gridBagConstraints.gridy = row;
            gridBagConstraints.insets = new Insets(2, 2, 2, 2);
            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;

            practiceModeButtons[i] = new JButton();
            practiceModePanel.add(practiceModeButtons[i], gridBagConstraints);
            practiceModeButtons[i].setPreferredSize(new Dimension(175, 175));
        }

        practiceMode pMode = new practiceMode(practiceModeButtons);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(30, 0, 0, 0);
        practiceModePanel.add(practiceModeBackButton, gridBagConstraints);


        /* Play a Bot Panel */


        for (int i = 0; i < 9; i++) {
            int row = i / 3;
            int col = i % 3;
            int index = i;

            gridBagConstraints.gridx = col;
            gridBagConstraints.gridy = row;
            gridBagConstraints.insets = new Insets(2, 2, 2, 2);
            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;

            playTheBotButtons[i] = new JButton();
            singleplayerPlayBotPanel.add(playTheBotButtons[i], gridBagConstraints);
            playTheBotButtons[i].setPreferredSize(new Dimension(175, 175));
        }

        singlePlayerLogic = new singlePlayerBoardFunctions(playTheBotButtons, () -> {
            gameLayout.show(gamePanel, "TITLEPAGE");
        }, this::updateTurnLabel, () -> {
            updateXP(+5);
            XPLabel.setText("Your XP: " + XPCount);
        },
                () -> {
                    updateXP(XPCount >= 0 ? -5  : 0);
                    XPLabel.setText("Your XP: " + XPCount);
                });
        updateTurnLabel();

        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 7;
        gridBagConstraints.insets = new Insets(0, 150, 0, 0);
        singleplayerPlayBotPanel.add(youVSbotLabel, gridBagConstraints);

        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 7;
        gridBagConstraints.insets = new Insets(30, 150, 0, 0);
        singleplayerPlayBotPanel.add(exitBotGameButton, gridBagConstraints);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 7;
        gridBagConstraints.insets = new Insets(30, 150, 0, 0);
        singleplayerPlayBotPanel.add(turnLabel, gridBagConstraints);


        /* Intermediate bot panel */


        for (int i = 0; i < 9; i++) {
            int row = i / 3;
            int col = i % 3;
            int index = i;

            gridBagConstraints.gridx = col;
            gridBagConstraints.gridy = row;
            gridBagConstraints.insets = new Insets(2, 2, 2, 2);
            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;

            playTheItermediateBotButtons[i] = new JButton();
            intermediateBotPanel.add(playTheItermediateBotButtons[i], gridBagConstraints);
            playTheItermediateBotButtons[i].setPreferredSize(new Dimension(175, 175));
        } interMediateBoardLogic = new intermediateBoardFunctions(playTheItermediateBotButtons, () -> {
            gameLayout.show(gamePanel, "TITLEPAGE");
        }, this::updateTurnLabel2, () -> {
            updateXP(+10);
            XPLabel.setText("Your XP: " + XPCount);
        },
                () -> {
                    updateXP(XPCount >= 0 ? -10  : 0);
                    XPLabel.setText("Your XP: " + XPCount);
                });
        updateTurnLabel2();

        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 7;
        gridBagConstraints.insets = new Insets(0, 150, 0, 0);
        intermediateBotPanel.add(youVSbotLabel2, gridBagConstraints);

        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 7;
        gridBagConstraints.insets = new Insets(30, 150, 0, 0);
        intermediateBotPanel.add(exitIntermediateBotGameButton, gridBagConstraints);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 7;
        gridBagConstraints.insets = new Insets(30, 150, 0, 0);
        intermediateBotPanel.add(turnLabel2, gridBagConstraints);


        /* Hard bot Panel */


        for (int i = 0; i < 9; i++) {
            int row = i / 3;
            int col = i % 3;
            int index = i;

            gridBagConstraints.gridx = col;
            gridBagConstraints.gridy = row;
            gridBagConstraints.insets = new Insets(2, 2, 2, 2);
            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;

            playTheHardBotButtons[i] = new JButton();
            hardBotPanel.add(playTheHardBotButtons[i], gridBagConstraints);
            playTheHardBotButtons[i].setPreferredSize(new Dimension(175, 175));
        } hardBoardLogic = new hardBoardFunctions(playTheHardBotButtons, () -> {
            gameLayout.show(gamePanel, "TITLEPAGE");
        }, this::updateTurnLabel3, () -> {
            updateXP(+20);
            XPLabel.setText("Your XP: " + XPCount);
        },
                () -> {
                    updateXP(XPCount >= 0 ? -20  : 0);
                    XPLabel.setText("Your XP: " + XPCount);
                });
        updateTurnLabel3();


        System.out.println("x_eni_o_nXP = " + singlePlayerLogic.x_nio_nXP + " & is " + XPCount);
        String XPString = String.valueOf(XPCount);
        XPLabel.setText("Your XP: " + XPString);
        System.out.println(XPCount);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 7;
        gridBagConstraints.insets = new Insets(0, 150, 0, 0);
        hardBotPanel.add(youVSbotLabel3, gridBagConstraints);

        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 7;
        gridBagConstraints.insets = new Insets(30, 150, 0, 0);
        hardBotPanel.add(exitHardBotGameButton, gridBagConstraints);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 7;
        gridBagConstraints.insets = new Insets(30, 150, 0, 0);
        hardBotPanel.add(turnLabel3, gridBagConstraints);


        /* Settings Panel*/


        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 40, 0);
        settingsPanel.add(settingsPageLabel, gridBagConstraints);

        if(isUserLoggedIn.isLoggedIn()) {
            usernameLabel.setText(isUserLoggedIn.getUsername());
        }

        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 30, 1250);
        settingsPanel.add(usernameLabel, gridBagConstraints);

        passwordRevealer = false;
        updatePasswordUI(passwordLabel);


        revealPasswordButton.addActionListener(e -> {
            passwordRevealer = !passwordRevealer;
            updatePasswordUI(passwordLabel);
            System.out.println(passwordRevealer);
        });

        exitSettingsButton.addActionListener(e -> {
            gameLayout.show(gamePanel, "TITLEPAGE");
        });

        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 30, 1250);
        settingsPanel.add(passwordLabel, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 0, 25, 1250);
        settingsPanel.add(revealPasswordButton, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 0, 25, 900);
        settingsPanel.add(changePasswordButton, gridBagConstraints);

        int half350 = 350/2;

        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(0, 0, 25, 1250-half350);
        settingsPanel.add(deleteAccButton, gridBagConstraints);

        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(0, 0, 250, 0);
        settingsPanel.add(exitSettingsButton, gridBagConstraints);


        /* Change Password Label */


        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 75, 0);
        passwordChangePanel.add(changePasswordLabel, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 275, 25, 0);
        passwordChangePanel.add(changePassowordField, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 25, 275);
        passwordChangePanel.add(newPasswordLabel, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 300, 25, 0);
        passwordChangePanel.add(confirmChangePasswordField, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 25, 300);
        passwordChangePanel.add(confirmNewPasswordLabel, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 0, 25, 0);
        passwordChangePanel.add(submitPasswordChangeRequestButton, gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(0, 0, 275, 0);
        passwordChangePanel.add(backToSettingsFromPasswordResetButton, gridBagConstraints);

        return gameFrame;
    }

    public void updateTurnLabel() {
        if (singlePlayerLogic == null) return;
        if (singlePlayerLogic.isPlayerTurn()) {
            turnLabel.setText("Your Turn");
            turnLabel.setForeground(singlePlayerLogic.isXTurn() ? Color.RED : Color.BLUE);
        } else {
            turnLabel.setText("X_eni_O_n BOT's Turn");
            turnLabel.setForeground(singlePlayerLogic.isXTurn() ? Color.RED : Color.BLUE);
        }
    }

    public void updateTurnLabel2() {
        if (interMediateBoardLogic == null) return;
        if (interMediateBoardLogic.isPlayerTurn()) {
            turnLabel2.setText("Your Turn");
            turnLabel2.setForeground(interMediateBoardLogic.isXTurn() ? Color.RED : Color.BLUE);
        } else {
            turnLabel2.setText("X_ab_O_r BOT's Turn");
            turnLabel2.setForeground(interMediateBoardLogic.isXTurn() ? Color.RED : Color.BLUE);
        }
    }

    public void updateTurnLabel3() {
        if (hardBoardLogic == null) return;
        if (hardBoardLogic.isPlayerTurn()) {
            turnLabel3.setText("Your Turn");
            turnLabel3.setForeground(hardBoardLogic.isXTurn() ? Color.RED : Color.BLUE);
        } else {
            turnLabel3.setText("e_X_cel_O_n BOT's Turn");
            turnLabel3.setForeground(hardBoardLogic.isXTurn() ? Color.RED : Color.BLUE);
        }
    }

    private void updatePasswordUI(JLabel passwordLabel) {
        String currentPassword =
                isUserNewPasswordIn.isPasswordIn()
                        ? isUserNewPasswordIn.getPassword()
                        : isUserPasswordIn.getPassword();

        if (!passwordRevealer) {
            if(currentPassword != null) {
                passwordLabel.setText("•".repeat(currentPassword.length()));
            } else {
                passwordLabel.setText("•".repeat(isUserPasswordIn.getPassword().length()));
            }
        } else {
            passwordLabel.setText(currentPassword);
        }
    }
    private void updateXP(int XP) {
         XPCount += XP;
        if(XPCount >= xpChallenge) {
            JOptionPane.showMessageDialog(
                    gameFrame,
                    "You completed the challenge",
                    "CONGRATULATIONS",
                    JOptionPane.INFORMATION_MESSAGE
            );
            XPChallengeLabel.setText("Challenge Complete");
            XPChallengeLabel.setForeground(Color.GREEN);
        }
        System.out.println("XP updated to: " + XPCount);
    }

}
