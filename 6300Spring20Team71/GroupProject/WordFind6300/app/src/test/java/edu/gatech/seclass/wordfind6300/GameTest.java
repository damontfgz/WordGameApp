package edu.gatech.seclass.wordfind6300;

import org.junit.Before;
import org.junit.Test;
import static edu.gatech.seclass.wordfind6300.Game.PENALITY;
import static org.junit.Assert.*;

public class GameTest {
    private Game game;
    private GameSetting setting;


    @Before
    public void before() {
        game = new Game();

    }

    @Test
    public void createGameTest() {
        assertEquals(game.getHighestScore(), 0);
        assertEquals(game.getNumOfResets(), 0);
        assertEquals(game.getNumOfWords(), 0);
        assertEquals(game.getHighestScore(), 0);
        assertEquals(game.getTopScoreWord(), null);
    }

    @Test
    public void gameStartTest() {
        setting = new GameSetting();
        game.start(setting);
        int size = setting.getBoardSize();
        assertFalse(game.getBoard().grid.length != size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertFalse(game.getBoard().grid[i][j] == null);
            }
        }
    }

    @Test
    public void topScoreWordTest() {
        setting = new GameSetting();
        game.start(setting);
        assertFalse(game.getTopScoreWord() != null);
        Board board = game.getBoard();
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < setting.getBoardSize() / 2; i++) {
            sb1.append(board.grid[0][i]);
        }
        assertTrue(game.processInput(sb1.toString()));
        assertEquals(game.getTopScoreWord(), sb1.toString());

        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < setting.getBoardSize(); i++) {
            sb2.append(board.grid[1][i]);
        }
        assertTrue(game.processInput(sb2.toString()));
        assertEquals(game.getTopScoreWord(), sb2.toString());

    }

    @Test
    public void highestScoreTest() {
        setting = new GameSetting();
        game.start(setting);
        assertFalse(game.getHighestScore() != 0);
        Board board = game.getBoard();
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < setting.getBoardSize() / 2; i++) {
            sb1.append(board.grid[0][i]);
        }
        assertTrue(game.processInput(sb1.toString()));
        assertEquals(game.getHighestScore(), sb1.length());

        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < setting.getBoardSize(); i++) {
            sb2.append(board.grid[1][i]);
        }
        assertTrue(game.processInput(sb2.toString()));
        assertEquals(game.getHighestScore(), sb2.length());

    }

    @Test
    public void numOfWrodsTest() {
        setting = new GameSetting();
        game.start(setting);
        assertFalse(game.getNumOfWords() != 0);
        Board board = game.getBoard();
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < setting.getBoardSize() / 2; i++) {
            sb1.append(board.grid[0][i]);
        }
        assertTrue(game.processInput(sb1.toString()));
        assertEquals(game.getNumOfWords(), 1);

        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < setting.getBoardSize(); i++) {
            sb2.append(board.grid[1][i]);
        }
        assertTrue(game.processInput(sb2.toString()));
        assertEquals(game.getNumOfWords(), 2);

    }

    @Test
    public void numOfResetsTest() {
        setting = new GameSetting();
        game.start(setting);
        assertFalse(game.getNumOfResets() != 0);
        game.reroll();
        assertEquals(game.getNumOfResets(), 1);

    }

    @Test
    public void totalScoreTest() {
        setting = new GameSetting();
        game.start(setting);
        assertFalse(game.getTotalScore() != 0);
        Board board = game.getBoard();
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < setting.getBoardSize() / 2; i++) {
            sb1.append(board.grid[0][i]);
        }
        assertTrue(game.processInput(sb1.toString()));
        assertEquals(game.getTotalScore(), sb1.length());

        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < setting.getBoardSize(); i++) {
            sb2.append(board.grid[1][i]);
        }
        assertTrue(game.processInput(sb2.toString()));
        assertEquals(game.getTotalScore(), sb1.length() + sb2.length());

        game.reroll();
        assertEquals(game.getTotalScore(),sb1.length() + sb2.length() - PENALITY);

    }

}
