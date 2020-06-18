package edu.gatech.seclass.wordfind6300;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class BoardTest {
    private Board board;
    private GameSetting setting;
    private int numOfRun = 500;
    private final Set<String> vowels = new HashSet<>(Arrays.asList("a", "e", "i", "o", "u"));
    /**
    generate letter randomness,
    num of vowels and consonant
    game rules
     check word rule


     **/


    @Before
    public void before() {
        setting = new GameSetting();

    }

    @Test
    public void BoardCreation1() {
        board = new Board(setting);
        assertFalse(board.grid.length == 0);
        assertTrue(board.grid.length == 4);
    }

    @Test
    public void BoardCreation2() {
        setting.changeSize(8);
        board = new Board(setting);
        assertFalse(board.grid.length == 0);
        assertTrue(board.grid.length == 8);
    }

    @Test
    public void checkVowel1() {
        board = new Board(setting);
        Map<String, Integer> map = new HashMap<>();
        for (String s : vowels) {
            map.put(s, 0);
        }
        for (int i = 0; i < numOfRun; i++) {
            String str = board.generateVowel();
            assertFalse(!map.containsKey(str));
            map.put(str, map.get(str) + 1);
        }
        for (String s : vowels) {
            Integer count = map.get(s);
            System.out.println(s + "=" +count);
            assertFalse(count > numOfRun * 1/5 * 1.3 || count < numOfRun * 1/5 * 0.7);
        }

    }

    @Test
    public void checkVowel2() {
        setting.changeWeight('a', 5);
        board = new Board(setting);
        Map<String, Integer> map = new HashMap<>();
        for (String s : vowels) {
            map.put(s, 0);
        }
        for (int i = 0; i < numOfRun; i++) {
            String str = board.generateVowel();
            assertFalse(!map.containsKey(str));
            map.put(str, map.get(str) + 1);
        }
        for (String s : vowels) {
            Integer count = map.get(s);
            System.out.println(s + "=" +count);
            assertFalse(s.equals("a") && (count < numOfRun * 5/9 * 0.8 || count > numOfRun * 5/9 * 1.2));
        }

    }

    @Test
    public void checkVowelNum1() {
        board = new Board(setting);
        board.generateLetter();
        int size = setting.getBoardSize();
        int count = 0;
        for (int i = 0; i < setting.getBoardSize(); i++) {
            for (int j = 0; j < setting.getBoardSize(); j++) {
                if (vowels.contains(board.grid[i][j])) {
                    count++;
                }
            }
        }
        System.out.println("vowelNum = " + count);
        assertFalse(count != size * size / 5 + 1);
    }

    @Test
    public void checkVowelNum2() {
        setting.changeSize(8);
        board = new Board(setting);
        board.generateLetter();
        int size = setting.getBoardSize();
        int count = 0;
        for (int i = 0; i < setting.getBoardSize(); i++) {
            for (int j = 0; j < setting.getBoardSize(); j++) {
                if (vowels.contains(board.grid[i][j])) {
                    count++;
                }
            }
        }
        System.out.println("vowelNum = " + count);
        assertFalse(count != size * size / 5 + 1);
    }

    @Test
    public void checkReset1() {
        board = new Board(setting);
        board.reset();
        Map<String, Integer> map = new HashMap<>();
        for (String s : vowels) {
            map.put(s, 0);
        }
        for (int i = 0; i < numOfRun; i++) {
            String str = board.generateVowel();
            assertFalse(!map.containsKey(str));
            map.put(str, map.get(str) + 1);
        }
        for (String s : vowels) {
            Integer count = map.get(s);
            System.out.println(s + "=" +count);
            assertFalse(count > numOfRun * 1/5 * 1.3 || count < numOfRun * 1/5 * 0.7);
        }

    }

    @Test
    public void checkReset2() {
        setting.changeWeight('a', 5);
        board = new Board(setting);
        board.reset();
        Map<String, Integer> map = new HashMap<>();
        for (String s : vowels) {
            map.put(s, 0);
        }
        for (int i = 0; i < numOfRun; i++) {
            String str = board.generateVowel();
            assertFalse(!map.containsKey(str));
            map.put(str, map.get(str) + 1);
        }
        for (String s : vowels) {
            Integer count = map.get(s);
            System.out.println(s + "=" +count);
            assertFalse(s.equals("a") && (count < numOfRun * 5/9 * 0.8 || count > numOfRun * 5/9 * 1.2));
        }
    }

    /**
    1. word less than 2 is false
     2. repeated word without reset return false
     3. reperate word with reset return true;
     4. single word use same position return false
     5. word using not adjucent letter return false
     6.
     **/
    @Test
    public void checkWord1() {
        board = new Board(setting);
        board.generateLetter();
        String word = board.grid[0][0];
        assertFalse(board.checkWord(word));
        word += board.grid[0][0] + board.grid[0][0];
        System.out.println(word);
        assertFalse(board.checkWord(word));
    }

    @Test
    public void checkWord2() {
        board = new Board(setting);
        board.generateLetter();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < setting.getBoardSize(); i++) {
            sb.append(board.grid[0][i]);
        }
        assertFalse(!board.checkWord(sb.toString()));
        assertFalse(board.checkWord(sb.toString()));
    }

    @Test
    public void checkWord3() {
        board = new Board(setting);
        board.generateLetter();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < setting.getBoardSize(); i++) {
            sb.append(board.grid[0][i]);
        }
        assertFalse(!board.checkWord(sb.toString()));
        assertEquals(board.getUsedWord().size(), 1);
        board.reset();
        assertEquals(board.getUsedWord().size(), 0);
    }

    public void checkWord4() {
        board = new Board(setting);
        board.generateLetter();
        int size = setting.getBoardSize();
        String word = board.grid[0][0];
        word += board.grid[size - 1][size - 1] + board.grid[0][size - 1];
        assertFalse(board.checkWord(word));
    }
}
