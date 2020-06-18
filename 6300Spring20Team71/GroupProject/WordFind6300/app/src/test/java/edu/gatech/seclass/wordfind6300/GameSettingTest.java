package edu.gatech.seclass.wordfind6300;

import org.junit.Before;
import org.junit.Test;

import static edu.gatech.seclass.wordfind6300.Game.PENALITY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GameSettingTest {
    private GameSetting gameSetting;

    @Before
    public void before() {
        gameSetting = new GameSetting();
    }

    @Test
    public void testDefaultSetting() {
        assertEquals(4, gameSetting.getBoardSize());
        assertEquals(3, gameSetting.getGameDuration());
        assertEquals(26, gameSetting.getLetterWeights().length);
        for (int i : gameSetting.getLetterWeights())  {
            assertEquals(1, i);
        }
    }
    @Test
    public void testchangeSizeSuccess() {
        gameSetting.changeSize(8);
        assertEquals(8, gameSetting.getBoardSize());

    }
    @Test(expected=IllegalArgumentException.class)
    public void testchangeSizeFail() {
        gameSetting.changeSize('c');

    }
    @Test
    public void testDurationSuccess() {
        gameSetting.changeDuration(5);
        assertEquals(5, gameSetting.getGameDuration());

    }
    @Test(expected=IllegalArgumentException.class)
    public void testDurationFail() {
        gameSetting.changeDuration('c');

    }
    @Test
    public void testchangeWeightSuccess() {
        gameSetting.changeWeight('c',2);

    }
    @Test(expected=IllegalArgumentException.class)
    public void testchangeWeightFail() {
        gameSetting.changeWeight('c','c');

    }

}

