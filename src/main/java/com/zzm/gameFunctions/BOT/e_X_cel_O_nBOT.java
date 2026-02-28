package com.zzm.gameFunctions.BOT;

import com.zzm.gameFunctions.hardBoardFunctions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class e_X_cel_O_nBOT {
    private final JButton[] board;
    private final Random random = new Random();
    private final hardBoardFunctions logic;
    public boolean cornerWinningSequence;
    public int cornerWinningSequenceMoveCount = 0;


    public e_X_cel_O_nBOT(JButton[] board, hardBoardFunctions logic) {
        this.board = board;
        this.logic = logic;
    }

    public void e_x_cel_o_nAI() {
        if(logic.isPlayerTurn()) return;

        List<Integer> emptySquares = new ArrayList<>();

        int move =
                findWinningMove("O") != -1 ? findWinningMove("O") :
                        findWinningMove("X") != -1 ? findWinningMove("X") :
                                findForkingMoves("X") != -1 ? findForkingMoves("X") :
                                        findForkingMoves("O") != -1 ? findForkingMoves("O") :
                                                findOppositeCornerOnGameStart() != -1 ? findOppositeCornerOnGameStart() :
                                                        takeCornerWinningSequence() != -1 ? takeCornerWinningSequence():
                                                                takeCenter() != -1 ? takeCenter() :
                                                                        takeCorner() != -1 ? takeCorner() :
                                                                                randomMove();

        if (move != -1) {
            JButton btn = board[move];
            btn.setText(logic.isXTurn() ? "X" : "O");
            btn.setFont(new Font("Adlam Display", Font.PLAIN, 125));
            btn.setForeground(logic.isXTurn() ? java.awt.Color.RED : java.awt.Color.BLUE);
        }


        if(emptySquares.isEmpty()) return;

        int choice = emptySquares.get(random.nextInt(emptySquares.size()));
        board[choice].setText(logic.isXTurn() ? "X" : "O");
        board[choice].setFont(new Font("Adlam Display", Font.PLAIN, 125));
        board[choice].setForeground(logic.isXTurn() ? java.awt.Color.RED : java.awt.Color.BLUE);
    }

    private int findWinningMove(String symbol) {
        int[][] wins = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };

        for(int[] win : wins) {
            int count = 0;
            int empty = -1;

            for(int pos : win) {
                if(board[pos].getText().equals(symbol)) count++;
                else if(board[pos].getText().isEmpty()) empty = pos;
            }

            if(count == 2 && empty != -1) return empty;
        }
        return -1;
    }

    private int findForkingMoves(String symbol) {
        for(int i = 0; i < 9; i++) {
            if(!board[i].getText().isEmpty()) continue;
            board[i].setText(symbol);

            int winningMoves = 0;
            if(findWinningMove(symbol) != -1) winningMoves++;
            board[i].setText("");

            if(winningMoves >= 2) return i;
        }
        return -1;
    }

    private int findOppositeCornerOnGameStart() {
        if(board[0].getText().equals(logic.isXTurn() ? "X" : "O") && board[8].getText().isEmpty()) return 8;
        if(board[8].getText().equals(logic.isXTurn() ? "X" : "O") && board[0].getText().isEmpty()) return 0;
        if(board[2].getText().equals(logic.isXTurn() ? "X" : "O") && board[6].getText().isEmpty()) return 6;
        if(board[6].getText().equals(logic.isXTurn() ? "X" : "O") && board[2].getText().isEmpty()) return 2;
        return -1;
    }

    private int takeCenter() {
        return board[4].getText().isEmpty() ? 4 : -1;
    }

    private int takeCorner() {
        int[] corners = {0,2,6,8};
        List<Integer> free = new ArrayList<>();

        for(int c : corners)
            if(board[c].getText().isEmpty()) free.add(c);

        return free.isEmpty() ? -1 : free.get(random.nextInt(free.size()));
    }

    private boolean playerHasCenter() {
        return board[4].getText().equals(logic.xTurn ? "X" : "O");
    }

    private int getPlayerCorner() {
        int[] corners = {0, 2, 6, 8};
        for (int c : corners) {
            if (board[c].getText().equals(logic.xTurn ? "X" : "O")) {
                return c;
            }
        }
        return -1;
    }

    private int getOppositeCorner(int corner)  {
        switch (corner) {
            case 0: return 8;
            case 2: return 6;
            case 6: return 2;
            case 8: return 0;
            default: return -1;
        }
    }

    private int takeCornerWinningSequence() {
        int[] corners = {0,2,6,8};
        List<Integer> free = new ArrayList<>();

        if(board[4].getText().isEmpty() && !cornerWinningSequence) {
            for(int c : corners)
                if(!board[0].getText().isEmpty()) free.add(8);
                else if(!board[2].getText().isEmpty()) free.add(6);
                else if(!board[6].getText().isEmpty()) free.add(2);
                else if(!board[8].getText().isEmpty()) free.add(0);
           cornerWinningSequence = true;
           cornerWinningSequenceMoveCount++;
        }

        if(!board[4].getText().isEmpty() && cornerWinningSequence && !board[0].getText().isEmpty() && cornerWinningSequenceMoveCount == 1) {
            free.add(8);
            cornerWinningSequenceMoveCount++;
        } else if(!board[4].getText().isEmpty() && cornerWinningSequence && !board[2].getText().isEmpty() && cornerWinningSequenceMoveCount == 1) {
            free.add(6);
            cornerWinningSequenceMoveCount++;
        } else if(!board[4].getText().isEmpty() && cornerWinningSequence && !board[6].getText().isEmpty() && cornerWinningSequenceMoveCount == 1) {
            free.add(2);
            cornerWinningSequenceMoveCount++;
        } else if(!board[4].getText().isEmpty() && cornerWinningSequence && !board[8].getText().isEmpty() && cornerWinningSequenceMoveCount == 1) {
            free.add(0);
            cornerWinningSequenceMoveCount++;
        }

        if(!board[4].getText().isEmpty() && cornerWinningSequence && !board[0].getText().isEmpty() && !board[8].getText().isEmpty() && cornerWinningSequenceMoveCount == 2) {
            if(!board[2].getText().isEmpty()) {
                free.add(6);
            } else {
                free.add(2);
            }
        } else if(!board[4].getText().isEmpty() && cornerWinningSequence && !board[2].getText().isEmpty() && !board[6].getText().isEmpty() && cornerWinningSequenceMoveCount == 1) {
            if(!board[0].getText().isEmpty()) {
                free.add(8);
            } else {
                free.add(0);
            }
        }

        if (!playerHasCenter()) return -1;

        int playerCorner = getPlayerCorner();
        if (playerCorner == -1) return -1;

        int opposite = getOppositeCorner(playerCorner);
        return board[opposite].getText().isEmpty() ? opposite : -1;
    }

    private int randomMove() {
        List<Integer> free = new ArrayList<>();
        for(int i = 0; i < 9; i++)
            if(board[i].getText().isEmpty()) free.add(i);

        return free.isEmpty() ? -1 : free.get(random.nextInt(free.size()));
    }


}
