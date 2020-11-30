package com.example.lab4et5;


import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestTicTacToc {
    TicTacToc tictactoc;

    @Before
    public void setUp(){tictactoc = new TicTacToc();}

    @Test
    public void board_init_conditions()
    {
        tictactoc.Initialize();
        assertFalse(tictactoc.getWinStatus());//winStatus devrait etre à false;
        assertFalse(tictactoc.getEven());//even devrait etre à false;
        assertEquals(0, tictactoc.getTurn());//turn devrait etre a 0;
        assertEquals(1, tictactoc.getPlayer());//player devrait etre 1;
        assertEquals('X', tictactoc.getToken());//token devrait etre X;

        for(int i=0;i<9;i++) {
            assertEquals('.', tictactoc.getBoard().get(i));// devrait etre '.'';
        }
    }

    @Test
    public void PlayTurn_wrongCell()
    {
        tictactoc.Initialize();

        //les conditions jusqu'ici/avant ici ont été testées par les tests précédents

        tictactoc.Play(0); //erreur si <1 ou > 9. les tours ne doivent pas changer

        assertEquals(1, tictactoc.getPlayer());//player devrait etre toujours a 1;
        assertEquals('X', tictactoc.getToken());//token devrait etre X;
        assertEquals(0, tictactoc.getTurn());//turn devrait etre a 0;

        //rien sur le board
        for(int i=0;i<9;i++)
        {
            assertEquals('.', tictactoc.getBoard().get(i));// devrait etre '.'';
        }

        tictactoc.Play(10); //erreur si <1 ou > 9. les tours ne doivent pas changer

        assertEquals(1, tictactoc.getPlayer());//player devrait etre toujours a 1;
        assertEquals('X', tictactoc.getToken());//token devrait etre X;
        assertEquals(0, tictactoc.getTurn());//turn devrait etre a 0;

        //rien sur le board
        for(int i=0;i<9;i++)
        {
            assertEquals('.', tictactoc.getBoard().get(i));// devrait etre '.'';
        }

    }

    @Test
    public void PlayTurn_changeCondition()
    {
        tictactoc.Initialize();

        //les conditions jusqu'ici/avant ici ont été testées par les tests précédents

        tictactoc.Play(1);

        //le tour  devrait  avoir changé, ainsi que le joueur et le token
        assertEquals(2, tictactoc.getPlayer());//player devrait etre rendu a 2;
        assertEquals('O', tictactoc.getToken());//token devrait etre O;
        assertEquals(1, tictactoc.getTurn());//turn devrait etre a 1;
        assertEquals('X', tictactoc.getBoard().get(0));// devrait etre 'X'
        for(int i=1;i<9;i++)
        {
            assertEquals('.', tictactoc.getBoard().get(i));// devrait etre '.'';
        }

        tictactoc.Play(2);

        //devrait être revenu au joueur 1, incrément de tour, etc.
        assertEquals(1, tictactoc.getPlayer());//player devrait etre rendu a 2;
        assertEquals('X', tictactoc.getToken());//token devrait etre O;
        assertEquals(2, tictactoc.getTurn());//turn devrait etre a 1;
        assertEquals('X', tictactoc.getBoard().get(0));// devrait etre 'X'
        assertEquals('O', tictactoc.getBoard().get(1));// devrait etre 'O'
        for(int i=2;i<9;i++)
        {
            assertEquals('.', tictactoc.getBoard().get(i));// devrait etre '.'';
        }
    }

    @Test
    public void PlayTurn_wrongChoice_dontchange()
    {
        tictactoc.Initialize();

        tictactoc.Play(1); //joueur 1 a jouer a la position 1

        //les conditions jusqu'ici ont été testées par les tests précédents

        tictactoc.Play(1); //joueur 2 a jouer a la position 1 aussi

        //le tour ne devrait pas avoir changé, ni le joueur, ni le token
        assertEquals(2, tictactoc.getPlayer());//player devrait etre encore a 2;
        assertEquals('O', tictactoc.getToken());//token devrait etre encore à O;
        assertEquals(1, tictactoc.getTurn());//turn devrait etre à 1 (seul le 1er, X, a compté;
        assertEquals('X', tictactoc.getBoard().get(0));// devrait etre 'X' car 1er joueur a eu priorité

    }

    @Test
    public void PlayTurn_wrongChoice_legitReplay()
    {
        tictactoc.Initialize();

        tictactoc.Play(1); //joueur 1 a jouer a la position 1
        tictactoc.Play(1); //joueur 2 a jouer a la position 1 aussi

        //les conditions jusqu'ici ont été testées par les tests précédents

        tictactoc.Play(2); //joueur 2 a jouer a la position 2

        //le tour  devrait  avoir changé, ainsi que le joueur et le token, sans compter le mauvais coup
        assertEquals(1, tictactoc.getPlayer());//player devrait etre encore a 1;
        assertEquals('X', tictactoc.getToken());//token devrait etre encore à X;
        assertEquals(2, tictactoc.getTurn());//turn a 2 car le mauvais tour n'a pas compté
        assertEquals('X', tictactoc.getBoard().get(0));// devrait etre 'X' car 1er joueur a eu priorité
        assertEquals('O', tictactoc.getBoard().get(1));// reprise du joueur 2 à la case 2 avec O
    }

    @Test
    public void gameWin_player1()
    {
        tictactoc.Initialize();

        tictactoc.Play(1); //joueur 1 a jouer a la position 1
        tictactoc.Play(4); //joueur 2 a jouer a la position 4
        tictactoc.Play(2); //joueur 1 a jouer a la position 2
        tictactoc.Play(5); //joueur 2 a jouer a la position 5
        tictactoc.Play(3); //joueur 1 a jouer a la position 3

        //les conditions jusqu'ici ont été testées par les tests précédents

        assertEquals(1, tictactoc.getPlayer());//player devrait etre encore a 1;
        assertTrue(tictactoc.getWinStatus());// devrait etre a true car on a un gagnant
        assertFalse(tictactoc.getEven());// devrait etre a false car on a un gagnant
        assertTrue(tictactoc.isEnded());// devrait etre a true car on a un gagnat
    }

    @Test
    public void LineWinPossibilities()
    {
        //ligne 2 horizontale, ligne 1 testée dans el test précédent
        tictactoc.Initialize();

        tictactoc.Play(4); //joueur 1 a jouer a la position 4
        tictactoc.Play(1); //joueur 2 a jouer a la position 1
        tictactoc.Play(5); //joueur 1 a jouer a la position 5
        tictactoc.Play(2); //joueur 2 a jouer a la position 2
        tictactoc.Play(6); //joueur 1 a jouer a la position 6

        assertTrue(tictactoc.getWinStatus());// devrait etre a true car on a un gagnant
        assertTrue(tictactoc.isEnded());// devrait etre a true car on a un gagnant

        //ligne 3 horizontale
        tictactoc.Initialize();

        tictactoc.Play(7); //joueur 1 a jouer a la position 7
        tictactoc.Play(1); //joueur 2 a jouer a la position 1
        tictactoc.Play(8); //joueur 1 a jouer a la position 8
        tictactoc.Play(2); //joueur 2 a jouer a la position 2
        tictactoc.Play(9); //joueur 1 a jouer a la position 9

        assertTrue(tictactoc.getWinStatus());// devrait etre a true car on a un gagnant
        assertTrue(tictactoc.isEnded());// devrait etre a true car on a un gagnant

        //ligne 1 verticale
        tictactoc.Initialize();

        tictactoc.Play(1); //joueur 1 a jouer a la position 1
        tictactoc.Play(2); //joueur 2 a jouer a la position 2
        tictactoc.Play(4); //joueur 1 a jouer a la position 4
        tictactoc.Play(5); //joueur 2 a jouer a la position 5
        tictactoc.Play(7); //joueur 1 a jouer a la position 7

        assertTrue(tictactoc.getWinStatus());// devrait etre a true car on a un gagnant
        assertTrue(tictactoc.isEnded());// devrait etre a true car on a un gagnant

        //ligne 2 verticale
        tictactoc.Initialize();

        tictactoc.Play(2); //joueur 1 a jouer a la position 2
        tictactoc.Play(1); //joueur 2 a jouer a la position 1
        tictactoc.Play(5); //joueur 1 a jouer a la position 5
        tictactoc.Play(4); //joueur 2 a jouer a la position 4
        tictactoc.Play(8); //joueur 1 a jouer a la position 8

        assertTrue(tictactoc.getWinStatus());// devrait etre a true car on a un gagnant
        assertTrue(tictactoc.isEnded());// devrait etre a true car on a un gagnant

        //ligne 3 verticale
        tictactoc.Initialize();

        tictactoc.Play(3); //joueur 1 a jouer a la position 3
        tictactoc.Play(1); //joueur 2 a jouer a la position 1
        tictactoc.Play(6); //joueur 1 a jouer a la position 6
        tictactoc.Play(4); //joueur 2 a jouer a la position 4
        tictactoc.Play(9); //joueur 1 a jouer a la position 9

        assertTrue(tictactoc.getWinStatus());// devrait etre a true car on a un gagnant
        assertTrue(tictactoc.isEnded());// devrait etre a true car on a un gagnant

        //ligne 1 diagonale
        tictactoc.Initialize();

        tictactoc.Play(1); //joueur 1 a jouer a la position 1
        tictactoc.Play(2); //joueur 2 a jouer a la position 2
        tictactoc.Play(5); //joueur 1 a jouer a la position 5
        tictactoc.Play(4); //joueur 2 a jouer a la position 4
        tictactoc.Play(9); //joueur 1 a jouer a la position 9

        assertTrue(tictactoc.getWinStatus());// devrait etre a true car on a un gagnant
        assertTrue(tictactoc.isEnded());// devrait etre a true car on a un gagnant

        //ligne 2 diagonale
        tictactoc.Initialize();

        tictactoc.Play(3); //joueur 1 a jouer a la position 3
        tictactoc.Play(1); //joueur 2 a jouer a la position 1
        tictactoc.Play(5); //joueur 1 a jouer a la position 5
        tictactoc.Play(4); //joueur 2 a jouer a la position 4
        tictactoc.Play(7); //joueur 1 a jouer a la position 7

        assertTrue(tictactoc.getWinStatus());// devrait etre a true car on a un gagnant
        assertTrue(tictactoc.isEnded());// devrait etre a true car on a un gagnant
    }


    @Test
    public void gameEven()
    {
        tictactoc.Initialize();

        tictactoc.Play(1); //joueur 1 a jouer a la position 1
        tictactoc.Play(2); //joueur 2 a jouer a la position 2
        tictactoc.Play(3); //joueur 1 a jouer a la position 3
        tictactoc.Play(5); //joueur 2 a jouer a la position 5
        tictactoc.Play(4); //joueur 1 a jouer a la position 4
        tictactoc.Play(6); //joueur 1 a jouer a la position 6
        tictactoc.Play(8); //joueur 2 a jouer a la position 8
        tictactoc.Play(7); //joueur 1 a jouer a la position 7
        tictactoc.Play(9); //joueur 1 a jouer a la position 9

        assertEquals(9, tictactoc.getTurn());//player devrait etre a 9;
        assertFalse(tictactoc.getWinStatus());// devrait etre a false car on a pas de gagnant
        assertTrue(tictactoc.getEven());// devrait etre a true car la grille est pleine
        assertTrue(tictactoc.isEnded());// devrait etre a true car la grille est pleine

    }

    @Test
    public void CantPlayIfOver()
    {
        tictactoc.Initialize();

        tictactoc.Play(1); //joueur 1 a jouer a la position 1
        tictactoc.Play(4); //joueur 2 a jouer a la position 4
        tictactoc.Play(2); //joueur 1 a jouer a la position 2
        tictactoc.Play(5); //joueur 2 a jouer a la position 5
        tictactoc.Play(3); //joueur 1 a jouer a la position 3

        //les conditions jusqu'ici ont été testées par les tests précédents

        tictactoc.Play(6); //joueur 2 essait de jouer quand même, mauvais perdant!
        assertEquals('.', tictactoc.getBoard().get(5));// le coup du joueur ne devrait pas etre dans le tableau

    }
}