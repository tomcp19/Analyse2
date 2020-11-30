package com.example.lab4et5;
//import TicTacToc


public class Lab5 {

    public static void main(String[] args) {

        TicTacToc tictactoc = new TicTacToc();
        tictactoc.Initialize();

        if (!tictactoc.isEnded()) {

            tictactoc.Play(10);
            tictactoc.Play(2);
            tictactoc.Play(1);
            tictactoc.Play(3);
            tictactoc.Play(4);
            tictactoc.Play(5);
            tictactoc.Play(7);
            tictactoc.Play(6);
            tictactoc.Play(9);
            tictactoc.Play(8);
        }

    }
}
