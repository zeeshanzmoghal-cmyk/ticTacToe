package com.zzm.gameFunctions;

import com.zzm.gameFunctions.BOT.e_X_cel_O_nBOT;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class hardBoardFunctions {
    Random random = new Random();
    private final JButton[] playTheHardBotButtons;
    public boolean xTurn;
    public boolean playerTurn;
    private final Runnable onExit;
    private final Runnable onTurnChange;
    private final Runnable onPlayerWin;
    private final Runnable onBotWin;
    private final e_X_cel_O_nBOT bot;
    private int checker = 0;

    public boolean isXTurn() {
        return xTurn;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean value) {
        playerTurn = value;
    }


    public hardBoardFunctions(JButton[] playTheHardBotButtons, Runnable onExit, Runnable onTurnChange, Runnable onPlayerWin, Runnable onBotWin) {
        this.playTheHardBotButtons = playTheHardBotButtons;
        this.onExit = onExit;
        this.onTurnChange = onTurnChange;
        this.bot = new e_X_cel_O_nBOT(playTheHardBotButtons, this);
        this.onPlayerWin = onPlayerWin;
        this.onBotWin = onBotWin;
        init();
        gameStartTurnDecider();
        botTurn();
    }

    private void init() {
        for (int i = 0; i < playTheHardBotButtons.length; i++) {
            int index = i;
            playTheHardBotButtons[i].addActionListener(e -> {
                if(!playerTurn) { return;}
                moveHandler(index);
                onTurnChange.run();
                checkWin();
            });
        }
    }

    public void moveHandler(int index) {
        JButton playTheHardBotButton = playTheHardBotButtons[index];

        if (!playTheHardBotButton.getText().isEmpty()) return;

        playTheHardBotButton.setText(xTurn ? "X" : "O");
        playTheHardBotButton.setFont(new Font("Adlam Display", Font.PLAIN, 125));
        playTheHardBotButton.setForeground(xTurn ? Color.RED : Color.BLUE);

        xTurn = !xTurn;
        playerTurn = false;
        onTurnChange.run();

        checkWin();

        SwingUtilities.invokeLater(() -> onTurnChange.run());

        if(!playerTurn) {
            SwingUtilities.invokeLater(() -> {
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                bot.e_x_cel_o_nAI();
                xTurn = !xTurn;
                playerTurn = true;
                onTurnChange.run();
                checkWin();
            });
        }
    }

    private void gameStartTurnDecider() {
        xTurn = true;
        playerTurn = random.nextBoolean();
    }

    private void botTurn() {
        if(!playerTurn) {
            SwingUtilities.invokeLater(() -> {
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                bot.e_x_cel_o_nAI();
                xTurn = !xTurn;
                playerTurn = true;
                onTurnChange.run();
                checkWin();
            });
        }
    }

    boolean currentTurn = xTurn;

    public void reset(boolean keepTurn) {
        for (JButton playTheBotButton : playTheHardBotButtons) {
            playTheBotButton.setText("");
            playTheBotButton.setBackground(null);
        }
        if(!keepTurn) xTurn = true;
        if(!keepTurn) playerTurn = random.nextBoolean();
        else xTurn = currentTurn;

        onTurnChange.run();
        if(!playerTurn) { botTurn(); }
    }

    public void checkWin() {
        String[][] singplayerBoard = new String[3][3];

        for (int i = 0; i < 9; i++) {
            singplayerBoard[i / 3][i % 3] = playTheHardBotButtons[i].getText();
            playTheHardBotButtons[i].setBackground(null);
        }

        int[][] winPositions = null;

        for (int i = 0; i < 3; i++) {
            if (!singplayerBoard[i][0].isEmpty() && singplayerBoard[i][0].equals(singplayerBoard[i][1]) && singplayerBoard[i][1].equals(singplayerBoard[i][2])) {
                winPositions = new int[][]{{i, 0}, {i, 1}, {i, 2}};
            }
        }

        for (int i = 0; i < 3; i++) {
            if (!singplayerBoard[0][i].isEmpty() && singplayerBoard[0][i].equals(singplayerBoard[1][i]) && singplayerBoard[1][i].equals(singplayerBoard[2][i])) {
                winPositions = new int[][]{{0, i}, {1, i}, {2, i}};
            }
        }

        if (!singplayerBoard[0][0].isEmpty() && singplayerBoard[0][0].equals(singplayerBoard[1][1]) && singplayerBoard[1][1].equals(singplayerBoard[2][2])) {
            winPositions = new int[][]{{0, 0}, {1, 1}, {2, 2}};
        }

        if (!singplayerBoard[0][2].isEmpty() && singplayerBoard[0][2].equals(singplayerBoard[1][1]) && singplayerBoard[1][1].equals(singplayerBoard[2][0])) {
            winPositions = new int[][]{{0, 2}, {1, 1}, {2, 0}};
        }

        if (winPositions != null) {
            for (int[] pos : winPositions) {
                int index = pos[0] * 3 + pos[1];
                playTheHardBotButtons[index].setBackground(Color.GREEN);
            }
            if(playerTurn) {
                playerTurn = false;
                onBotWin.run();
            } else if (!playerTurn) {
                playerTurn = true;
                onPlayerWin.run();
            }
            int playAgain = JOptionPane.showConfirmDialog(null,
                    playerTurn ? "You Won, Play Again?" : "e_X_cel_O_n BOT Won, Play Again?",
                    "FINISH",
                    JOptionPane.YES_NO_OPTION
            );
            if(playAgain == JOptionPane.YES_OPTION) {
                reset(true);
            } else {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                onExit.run();
                reset(false);
            }
            return;
        }

        boolean draw = true;
        for (JButton pbtn : playTheHardBotButtons) {
            if (pbtn.getText().isEmpty()) {
                draw = false;
                break;
            }
        }

        if (draw) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int playAgain = JOptionPane.showConfirmDialog(null,
                    "Draw, Play Again?",
                    "FINISH",
                    JOptionPane.YES_NO_OPTION
            );
            if(playAgain == JOptionPane.YES_OPTION) {
                reset(false);
            } else {
                onExit.run();
                reset(false);
            }
            reset(false);
        }


    }
}
