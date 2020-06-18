package edu.gatech.seclass.wordfind6300;

import org.junit.Before;
import org.junit.Test;

import static edu.gatech.seclass.wordfind6300.Game.PENALITY;
import static org.junit.Assert.*;

public class PlayerTest {
    //private String[][] board = new String[][] {{"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}};
    private Player player;

    @Before
    public void before() {
        player = new Player();
    }

    @Test
    public void testDefaultBoard() {
        String[][] boardLetters = player.startApplication();
        assertFalse(boardLetters.equals(null));
        assertEquals(4, boardLetters.length);
        for (int i = 0; i < boardLetters.length; i++) {
            for (int j = 0; j < boardLetters.length; j++) {
                if (!boardLetters.equals("qu")) {
                    char c = boardLetters[i][j].charAt(0);
                    assertTrue(c >= 'a' && c <= 'z');
                }
            }
        }
    }

    @Test
    public void testDefaultSetting() {
        GameSetting setting = player.getSetting();
        assertEquals(4, setting.getBoardSize());
        assertEquals(3, setting.getGameDuration());
        assertEquals(26, setting.getLetterWeights().length);
        for (int i : setting.getLetterWeights())  {
            assertEquals(1, i);
        }
    }

    @Test
    public void testDefaultGame() {
        player.startApplication();
        Game game = player.getGame();
        assertEquals(0, game.getHighestScore());
        assertEquals(0, game.getTotalScore());
        assertEquals(0, game.getNumOfResets());
        assertEquals(0, game.getNumOfWords());
        assertNull(game.getTopScoreWord());
        assertNotNull(game.getBoard());
    }

    @Test
    public void testValidWordHorizontal() {
        String[][] boardLetters = player.startApplication();
        String wordToPlay = "";
        for (int i = 0; i < boardLetters.length; i++) {
            wordToPlay += boardLetters[0][i];
        }
        boolean isValid = player.playGame(wordToPlay);
        assertTrue(isValid);
        assertEquals(1, player.getWordStat().size());
        assertTrue(player.getWordStat().containsKey(wordToPlay));
        assertEquals(1, (int) player.getWordStat().get(wordToPlay));
        assertEquals(wordToPlay.length(), player.getCurrentGameScore());
    }

    @Test
    public void testValidWordVertical() {
        String[][] boardLetters = player.startApplication();
        String wordToPlay = "";
        for (int i = 0; i < boardLetters.length; i++) {
            wordToPlay += boardLetters[i][0];
        }
        boolean isValid = player.playGame(wordToPlay);
        assertTrue(isValid);
        assertEquals(1, player.getWordStat().size());
        assertTrue(player.getWordStat().containsKey(wordToPlay));
        assertEquals(1, (int) player.getWordStat().get(wordToPlay));
        assertEquals(wordToPlay.length(), player.getCurrentGameScore());
    }

    @Test
    public void testValidDiagonal() {
        String[][] boardLetters = player.startApplication();
        String wordToPlay = "";
        for (int i = 0; i < boardLetters.length; i++) {
            wordToPlay += boardLetters[i][i];
        }
        boolean isValid = player.playGame(wordToPlay);
        assertTrue(isValid);
        assertEquals(1, player.getWordStat().size());
        assertTrue(player.getWordStat().containsKey(wordToPlay));
        assertEquals(1, (int) player.getWordStat().get(wordToPlay));
        assertEquals(wordToPlay.length(), player.getCurrentGameScore());
    }

    @Test
    public void testInvalidWordRepeated() {
        String[][] boardLetters = player.startApplication();
        String wordToPlay = "";
        for (int i = 0; i < boardLetters.length; i++) {
            wordToPlay += boardLetters[i][i];
        }
        boolean isValid = player.playGame(wordToPlay);
        isValid = player.playGame(wordToPlay);
        assertFalse(isValid);
        assertEquals(1, player.getWordStat().size());
        assertTrue(player.getWordStat().containsKey(wordToPlay));
        assertEquals(1, (int) player.getWordStat().get(wordToPlay));
        assertEquals(wordToPlay.length(), player.getCurrentGameScore());
    }

    @Test
    public void testInvalidWordSingleLetter() {
        String[][] boardLetters = player.startApplication();
        String wordToPlay = "";
        for (int i = 0; i < boardLetters.length; i++) {
            wordToPlay += boardLetters[i][i];
            break;
        }
        boolean isValid = player.playGame(wordToPlay);
        assertFalse(isValid);
        assertEquals(0, player.getWordStat().size());
        assertEquals(0, player.getCurrentGameScore());
    }

    @Test
    public void testInvalidWordEmpty() {
        String[][] boardLetters = player.startApplication();
        String wordToPlay = "";
        boolean isValid = player.playGame(wordToPlay);
        assertFalse(isValid);
        assertEquals(0, player.getWordStat().size());
        assertEquals(0, player.getCurrentGameScore());
    }

    @Test
    public void testInvalidWordNonAlphabets() {
        String[][] boardLetters = player.startApplication();
        String wordToPlay = "12";
        boolean isValid = player.playGame(wordToPlay);
        assertFalse(isValid);
        assertEquals(0, player.getWordStat().size());
        assertEquals(0, player.getCurrentGameScore());
    }

    @Test
    public void testInvalidWordNotPresent() {
        String[][] boardLetters = player.startApplication();
        String wordToPlay = "qq";
        boolean isValid = player.playGame(wordToPlay);
        assertFalse(isValid);
        assertEquals(0, player.getWordStat().size());
        assertEquals(0, player.getCurrentGameScore());
    }

    @Test
    public void testGameScoreIncrementedCorrectly() {
        String[][] boardLetters = player.startApplication();
        String wordToPlay = "";
        int expectedScore = 0;
        for (int i = 0; i < boardLetters.length; i++) {
            for (int j = 0; j < boardLetters.length; j++) {
                wordToPlay += boardLetters[i][j];
            }
            expectedScore += wordToPlay.length();
            player.playGame(wordToPlay);
            wordToPlay = "";
        }
        assertEquals(expectedScore, player.getCurrentGameScore());
        assertEquals(4, player.getWordStat().size());
    }

    @Test
    public void testGameScorePenalized() {
        String[][] boardLetters = player.startApplication();
        String wordToPlay = "";
        int expectedScore = 0;
        for (int i = 0; i < boardLetters.length; i++) {
            for (int j = 0; j < boardLetters.length; j++) {
                wordToPlay += boardLetters[i][j];
            }
            expectedScore += wordToPlay.length();
            player.playGame(wordToPlay);
            wordToPlay = "";
        }
        player.reRoll();
        assertEquals(expectedScore - PENALITY, player.getCurrentGameScore());
        assertEquals(4, player.getWordStat().size());
    }

    @Test
    public void testGameStatCreatedOnExit() {
        String[][] boardLetters = player.startApplication();
        String wordToPlay = "";
        int expectedScore = 0;
        int highestScore = Integer.MIN_VALUE;
        String highestWord = "";
        for (int i = 0; i < boardLetters.length; i++) {
            wordToPlay = "";
            for (int j = 0; j < boardLetters.length; j++) {
                wordToPlay += boardLetters[i][j];
            }
            int length = wordToPlay.length();
            expectedScore += length;
            if (highestScore < length) {
                highestScore = length;
                highestWord = wordToPlay;
            }
            player.playGame(wordToPlay);
        }
        player.exit();
        assertEquals(1, player.getGameStats().size());
        GameStat gameStat = player.getGameStats().get(0);
        assertEquals(expectedScore, gameStat.getFinalScore());
        assertEquals(highestScore, gameStat.getHighestScore());
        assertEquals(0, gameStat.getNumOfReset());
        assertEquals(boardLetters.length, gameStat.getNumOfWords());
        assertEquals(highestWord, gameStat.getTopScoreWord());
        assertEquals(3, gameStat.getDuration());
        assertEquals(4, gameStat.getBoardSize());
    }
}

