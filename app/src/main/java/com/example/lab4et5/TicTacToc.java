package com.example.lab4et5;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class TicTacToc {

    private ArrayList<Character> Board;
    private boolean winStatus;
    private boolean even;
    private char token;
    private int player;
    private int turnCounter;

    public void Initialize() {
        winStatus = false;
        even = false;
        token = 'X';
        player = 1;
        turnCounter = 0;
        Board = new ArrayList<Character>();

        for (int i = 0; i < 9; i++) {
            Board.add('.');
        }

        printBoardChoices();
        System.out.println("\n It's player's " + player + " turn!");

    }

    public boolean isEnded() {
        return winStatus;
    }

    public void Play(int position) {
        if (!winStatus) {
            if (!isValidEntry(position)) {
                System.out.println("\n Invalid entry, enter a new one");
                // throws InterruptedException(){};
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Board.set(position - 1, token);
                turnCounter++;
                checkCondition();
                if (winStatus || even) {
                    printBoardStatus();
                    if (winStatus) {
                        System.out.println("Player " + player + " has won. Congratulation! \n");
                    } else {
                        System.out.println("The game is even. There's no winner \n");
                    }
                } else {
                    printBoardStatus();
                    switchPlayer();
                }
            }
        } else {
            System.out.println("The game is over. You can't play anymore until you run Initialize() again; \n");
        }

    }

    private void checkCondition() {
        int condition;

        // check for lines
        for (condition = 0; condition < 9; condition++) {
            switch (condition) {
                case 0:
                    winStatus = checkForWin(0, 1, 2);
                    break;

                case 1:
                    winStatus = checkForWin(3, 4, 5);
                    break;

                case 2:
                    winStatus = checkForWin(6, 7, 8);
                    break;

                case 3:
                    winStatus = checkForWin(0, 3, 6);
                    break;

                case 4:
                    winStatus = checkForWin(1, 4, 7);
                    break;

                case 5:
                    winStatus = checkForWin(2, 5, 8);
                    break;

                case 6:
                    winStatus = checkForWin(0, 4, 8);
                    break;

                case 7:
                    winStatus = checkForWin(2, 4, 6);
                    break;

                case 8:
                    isEven();
                    break;

            }
            if (winStatus == true) {
                condition = 8;
            }
        }
    }

    // https://stackoverflow.com/questions/4035244/java-arraylist-comparison-tictactoe
    private boolean checkForWin(int x, int y, int z) {
        if (!Board.get(x).equals('.')) {
            return Board.get(x).equals(Board.get(y)) && Board.get(y).equals(Board.get(z));
        } else
            return false;
    }

    private void isEven() {
        if (turnCounter == 9 && winStatus == false) {
            even = true;
        } else
            even = false;
    }

    private boolean isValidEntry(int x) {
        if (x < 1 || x > 9) {
            System.out.println("\n Wrong entry: The cell chosen isn't between 1 and 9 \n");
            return false;
        } else if (Board.get(x - 1) != '.') {
            System.out.println("\n  Wrong entry: The cell is already taken");
            return false;
        } else {
            return true;
        }
    }

    private void switchPlayer() {
        player = player == 1 ? 2 : 1;
        token = token == 'X' ? 'O' : 'X';
        if (!winStatus) {
            System.out.println("\n It's player's " + player + " turn! \n \n");
        }
    }

    private void printBoardStatus() {

        System.out.println("\n Board Status \n \n  " + Board.get(0) + " | " + Board.get(1) + " | " + Board.get(2)
                + "\n ___________ \n  " + Board.get(3) + " | " + Board.get(4) + " | " + Board.get(5)
                + "\n ___________\n  " + Board.get(6) + " | " + Board.get(7) + " | " + Board.get(8) + "\n\n");

    }

    private void printBoardChoices() {
        System.out.println(
                " \n \n El ultimo TicTacToc de la muerte! \n \n  Choose an entry between 1 and 9 \n \n 1 | 2 | 3 \n _________ \n 4 | 5 | 6 \n _________ \n 7 | 8 | 9 \n \n");
    }

}
