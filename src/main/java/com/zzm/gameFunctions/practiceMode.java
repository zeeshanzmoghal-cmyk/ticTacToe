package com.zzm.gameFunctions;


import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class practiceMode  {
    Random random = new Random();
    private final JButton[] practiceModeButtons;
    private boolean xTurn = random.nextBoolean();

    public practiceMode(JButton[] practiceButtons) {
        this.practiceModeButtons = practiceButtons;
        init();
    }

    private void init() {
        for(int i = 0; i < practiceModeButtons.length; i++) {
            int index = i;
            practiceModeButtons[i].addActionListener(e -> {
                moveHandler(index);
                checkWin();
            });
        }
    }

    public void moveHandler(int index) {
        JButton pbtn = practiceModeButtons[index];

        if(!pbtn.getText().isEmpty()) return;

        pbtn.setText(xTurn ? "X" : "O");
        pbtn.setFont(new Font("Adlam Display", Font.PLAIN, 125));
        if(pbtn.getText().equals("X")) {
            pbtn.setForeground(Color.red);
        } else {
            pbtn.setForeground(Color.blue);
        }
        xTurn = !xTurn;

    }

    boolean currentTurn = xTurn;
    //boolean colored;
    public void reset(boolean keepTurn) {
        for(JButton pbtn : practiceModeButtons) {
            pbtn.setText("");
            pbtn.setBackground(null);
        }
        if(!keepTurn) xTurn = true;
        else xTurn = currentTurn;
        //colored = false;
    }

    public void checkWin() {
        String[][] practiceBoard = new String[3][3];

        for(int i = 0; i<9; i++) {
            practiceBoard[i / 3][i % 3] = practiceModeButtons[i].getText();
            practiceModeButtons[i].setBackground(null);
        }

        int[][] winPositions = null;

        for(int i = 0; i < 3; i++) {
            if (!practiceBoard[i][0].isEmpty() && practiceBoard[i][0].equals(practiceBoard[i][1]) && practiceBoard[i][1].equals(practiceBoard[i][2])) {
                winPositions = new int[][] { {i,0}, {i,1}, {i,2} };
            }
        }

        for(int i = 0; i < 3; i++) {
            if (!practiceBoard[0][i].isEmpty() && practiceBoard[0][i].equals(practiceBoard[1][i]) && practiceBoard[1][i].equals(practiceBoard[2][i])) {
                winPositions = new int[][] { {0,i}, {1,i}, {2,i} };
            }
        }

        if(!practiceBoard[0][0].isEmpty() && practiceBoard[0][0].equals(practiceBoard[1][1]) && practiceBoard[1][1].equals(practiceBoard[2][2])) {
            winPositions = new int[][] { {0,0}, {1,1}, {2,2} };
        }

        if(!practiceBoard[0][2].isEmpty() && practiceBoard[0][2].equals(practiceBoard[1][1]) && practiceBoard[1][1].equals(practiceBoard[2][0])) {
            winPositions = new int[][] { {0,2}, {1,1}, {2,0} };
        }

        if(winPositions != null) {
            for (int[] pos : winPositions) {
                int index = pos[0] * 3 + pos[1];
                practiceModeButtons[index].setBackground(Color.GREEN);
                //colored = true;
            }
            int[][] finalWinPositions = winPositions;
            new javax.swing.Timer(4000, e -> {
                for (int[] pos : finalWinPositions) {
                    int index = pos[0] * 3 + pos[1];
                    practiceModeButtons[index].setBackground(null);
                    reset(false);
                }
                ((javax.swing.Timer)e.getSource()).stop(); // stop timer
            }).start();
            return;
        }

        boolean draw = true;
        for(JButton pbtn : practiceModeButtons) {
            if(pbtn.getText().isEmpty()) {
                draw = false;
                break;
            }
        }

        if(draw) {
            JOptionPane.showMessageDialog(null, "Draw!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            reset(false);
        }



    }

}
