package edu.gatech.seclass.wordfind6300;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GameStatisticsTest {
    private Player player;

    @Before
    public void before() {

         player = new Player();
    }
    @Test
    public void testGameStats() {
        String[][] boardLetters = player.startApplication();
        String wordToPlay1 = boardLetters[1][0] +boardLetters[1][1]+boardLetters[1][2]+boardLetters[1][3];
        player.playGame(wordToPlay1);
        String wordToPlay2 = boardLetters[2][1] +boardLetters[2][2]+boardLetters[2][3];
        player.playGame(wordToPlay2);

        player.getGame().processInput(wordToPlay1);
        player.getGame().processInput(wordToPlay2);
        player.reRoll();
        player.exit();

        assertEquals(2,  player.getGameStats().get(0).getFinalScore());
        assertEquals(4, player.getGameStats().get(0).getHighestScore());
        assertEquals(1,  player.getGameStats().get(0).getNumOfReset());
        assertEquals(2,  player.getGameStats().get(0).getNumOfWords());
        assertEquals(wordToPlay1,  player.getGameStats().get(0).getTopScoreWord());
    }
    @Test
    public void testGameStatsWithSetting() {
        String[][] boardLetters = player.startApplication();
        String wordToPlay1 = boardLetters[1][0] +boardLetters[1][1]+boardLetters[1][2]+boardLetters[1][3];
        player.playGame(wordToPlay1);
        String wordToPlay2 = boardLetters[2][1] +boardLetters[2][2]+boardLetters[2][3];
        player.playGame(wordToPlay2);

        player.getGame().processInput(wordToPlay1);
        player.getGame().processInput(wordToPlay2);
        player.reRoll();
        player.exit();

        assertEquals(3,  player.getGameStats().get(0).getDuration());
        assertEquals(4, player.getGameStats().get(0).getBoardSize());
        assertEquals(wordToPlay1,  player.getGameStats().get(0).getTopScoreWord());
    }
    }



