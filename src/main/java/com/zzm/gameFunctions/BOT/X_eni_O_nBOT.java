package com.zzm.gameFunctions.BOT;

import com.zzm.gameFunctions.singlePlayerBoardFunctions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class X_eni_O_nBOT {
    private final JButton[] board;
    private final Random random = new Random();
    private final singlePlayerBoardFunctions logic;


    public X_eni_O_nBOT(JButton[] board, singlePlayerBoardFunctions logic) {
        this.board = board;
        this.logic = logic;
    }

    public void x_eni_o_n_AI() {
        if(logic.isPlayerTurn()) return;

        List<Integer> emptySquares = new ArrayList<>();

        int move =
                findWinningMove("O") != -1 ? findWinningMove("O") :
                        findWinningMove("X") != -1 ? findWinningMove("X") :
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

    private int randomMove() {
        List<Integer> free = new ArrayList<>();
        for(int i = 0; i < 9; i++)
            if(board[i].getText().isEmpty()) free.add(i);

        return free.isEmpty() ? -1 : free.get(random.nextInt(free.size()));
    }


}
