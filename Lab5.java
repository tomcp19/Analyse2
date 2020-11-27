
//package com.example.tictactoc;
import java.util.ArrayList;

public class Lab5 {

    public static void main(String[] args) {

        TicTacToc tictactoc = new TicTacToc();
        tictactoc.Initialize();
        tictactoc.printBoardStatus();
        tictactoc.printBoardChoices();

        /*
         * tictactoc.getBoard().add("aaa"); System.out.println(Board.get(0).ToString());
         * 
         * /* while(!tictactoc.isEnded()){ tictactoc.play(0,1); //joue sur la case en
         * position 0,1 sur le tableau de jeu du joueur courant et passe au suivant;
         * tictactoc.play(0,1); //joue sur la case en position 0,1 sur le tableau de jeu
         * du joueur courant et passe au suivant; tictactoc.play(0,1); //joue sur la
         * case en position 0,1 sur le tableau de jeu du joueur courant et passe au
         * suivant; tictactoc.play(0,1); //joue sur la case en position 0,1 sur le
         * tableau de jeu du joueur courant et passe au suivant; tictactoc.play(0,1);
         * //joue sur la case en position 0,1 sur le tableau de jeu du joueur courant et
         * passe au suivant; tictactoc.play(0,1); //joue sur la case en position 0,1 sur
         * le tableau de jeu du joueur courant et passe au suivant; tictactoc.play(0,1);
         * //joue sur la case en position 0,1 sur le tableau de jeu du joueur courant et
         * passe au suivant; tictactoc.play(0,1); //joue sur la case en position 0,1 sur
         * le tableau de jeu du joueur courant et passe au suivant; tictactoc.play(0,1);
         * //joue sur la case en position 0,1 sur le tableau de jeu du joueur courant et
         * passe au suivant; tictactoc.play(0,1); //joue sur la case en position 0,1 sur
         * le tableau de jeu du joueur courant et passe au suivant;
         * 
         * }
         */

    }
}

class TicTacToc {

    public ArrayList<Character> Board;
    boolean winStatus;
    boolean even;
    char token;
    int player;
    int turnCounter;

    /*
     * public void TicTacToc(){
     * 
     * for(int i = 0; i < 9; i++) { Board.set(i, "."); }
     * 
     * 
     * }
     */

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

    }

    public ArrayList getBoard() {
        return Board;
    }

    public boolean isEnded() {
        return win;
    }

    void Play(int position) {
        if (!winStatus) {
            int cell = position - 1;
            while (!isValidEntry(cell)) {
                println("Invalid entry, enter a new one");
            }
            Board.set(cell, token);
            checkCondition();
            if (winStatus) {
                println("Player " + player + " has won. Congratulation!");
            } else {
                switchPlayer();
            }
        }

    }

    void checkCondition() {
        int condition;

        /*
         * if ((Board.get(0)).equals(Board.get(1)) &&
         * Board.get(1).equals(Board.get(2))){ System.out.println("Player-" +
         * Board.get(0) +" has won!"); return true;
         */
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
        // return winStatus;
    }

    // https://stackoverflow.com/questions/4035244/java-arraylist-comparison-tictactoe
    public boolean checkForWin(int x, int y, int z) {
        if (!Board.get(x).equals('.')) {
            return Board.get(x).equals(Board.get(y)) && Board.get(y).equals(Board.get(z));
        } else
            return false;
    }

    boolean isEven() {
        if (turnCounter == 9 && win == false) {
            return true;
        } else
            return false;
    }

    boolean isValidEntry(int x) {
        if (!Board.get(x).equals('.')) {
            println("The cell is already taken");
            return false;
        } else if (x < 1 || x > 9) {
            println("The cell chosen isn't between 1 and 9");
            return false;
        }
        /*
         * else if(x.hasNextInt())) { println("The entry must be a number"); return
         * false; }
         */

        return true;
    }

    private void switchPlayer() {
        player = player == 1 ? 2 : 1; // si player est 1 retourne 2 sinon retourne 1
        token = token == 'X' ? 'O' : 'X';
    }

    public void printBoardStatus() {
        System.out.println("\n Board Status \n \n It's player's " + player + " turn! \n \n  " + Board.get(0) + " | "
                + Board.get(1) + " | " + Board.get(2) + "\n ___________ \n  " + Board.get(3) + " | " + Board.get(4)
                + " | " + Board.get(5) + "\n ___________\n  " + Board.get(6) + " | " + Board.get(7) + " | "
                + Board.get(8) + "\n \n Please place your token in available spaces (type a number from 1 to 9)");
    }

    public void printBoardChoices() {
        System.out.println(
                "\n El ultimo TicTacToc de la muerte! \n \n  Choose an entry between a and 9 \n \n 1 | 2 | 3 \n _________ \n 4 | 5 | 6 \n _________ \n 7 | 8 | 9");
    }

}
