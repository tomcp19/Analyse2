package com.example.lab4et5;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FullscreenActivity extends AppCompatActivity {
    TicTacToc tictactoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            tictactoc = new TicTacToc();
            tictactoc.Initialize();

            while (!tictactoc.isEnded()) {

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