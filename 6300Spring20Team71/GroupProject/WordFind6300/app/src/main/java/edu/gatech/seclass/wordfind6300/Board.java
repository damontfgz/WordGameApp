package edu.gatech.seclass.wordfind6300;
import java.util.*;

public class Board {
    String[][] grid;
    int[] weight;
    private int size;
    private boolean[][] dedup;
    private Random rand;
    private final Set<String> vowels = new HashSet<> (Arrays.asList("a", "e", "i", "o", "u"));
    private Set<String> usedWord;
    private int vowelTotalWeight;
    private int consonantTotalWeight;
    private TreeMap<Integer, String> vowelMap;
    private TreeMap<Integer, String> consonantMap;

    public Board(GameSetting setting) {
        weight = Arrays.copyOf(setting.getLetterWeights(), 26);
        usedWord = new HashSet<> ();
        size = setting.getBoardSize();
        grid = new String[size][size];
        dedup = new boolean[size][size];
        rand = new Random();
        vowelMap = new TreeMap<>();
        consonantMap = new TreeMap<>();
        loadVMap();
        loadCMap();
    }

    private void loadVMap() {
        vowelTotalWeight = 0;
        for (String str : vowels) {
            int index = str.charAt(0) - 'a';
            vowelTotalWeight += weight[index];
            vowelMap.put(vowelTotalWeight, str);
        }
    }

    private void loadCMap() {
        consonantTotalWeight = 0;
        for (int i = 0; i < 26; i++) {
            String str = (char)(i + 'a') + "";
            if (!vowels.contains(str)) {
                consonantTotalWeight += weight[i];
                consonantMap.put(consonantTotalWeight, str);
            }
        }
    }

    public String generateVowel() {
        int value = rand.nextInt(vowelTotalWeight) + 1;
        return vowelMap.ceilingEntry(value).getValue();

    }

    public String generateConsonant() {
        int value = rand.nextInt(consonantTotalWeight) + 1;
        return consonantMap.ceilingEntry(value).getValue();
    }

    public void generateLetter() {
        int numOfVowels = size * size / 5 + 1;
//        int numOfConsonants = size * size - numOfVowels;
        Set<Integer> filled = new HashSet<> ();
        while (numOfVowels > 0) {
            int pos = rand.nextInt(size * size);
            if (filled.add(pos)) {
                int row = pos / size;
                int col = pos % size;
                grid[row][col] = generateVowel();
                numOfVowels--;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == null || grid[i][j].length() == 0) {
                    String temp = generateConsonant();
                    grid[i][j] = temp.equals("q") ? "qu" : temp;
                }
            }
        }
    }

    public String[][] reset() {
        /**
        * 1. clear grid to empty
         * 2. refill gird letters
         * 3.
        * */
//        Arrays.fill(grid, null);
        grid = new String[size][size];
        generateLetter();
        usedWord = new HashSet<>();
        return grid;
    }

    public boolean checkWord(String word) {
        /**
         * check if word satisfy requirement below
         * 1. letter must exist on board
         * 2. each letter must be adjacent
         * 3. single letter cannot be used twice
         * 4. word len >= 2
         *
         *
         * enhancement: could return different value based on what rule does it violate
        **/
        if (word == null || word.length() < 2 || usedWord.contains(word)) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (checkWordHelper(word, i, j, 0)) {
                    usedWord.add(word);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkWordHelper(String word, int row, int col, int index) {
        if (index == word.length()) {
            return true;
        }
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        } else if (dedup[row][col] || grid[row][col].charAt(0) != word.charAt(index)) {
            return false;
        }
        String str = grid[row][col];
        dedup[row][col] = true;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (checkWordHelper(word, row + i, col + j, index + str.length())) {
                    dedup[row][col] = false;
                    return true;
                }
            }
        }
        dedup[row][col] = false;
        return false;
    }

    public String[][] display() {
        return grid;
    }

    public Set<String> getUsedWord() {
        return usedWord;
    }

    public static void main(String[] arg) {
        GameSetting setting = new GameSetting();
//        setting.changeSize(8);
//        System.out.println(setting.getBoardSize());
//        System.out.println(setting.getDuration());
//        for (int num : setting.getWeights()) {
//            System.out.print(num);
//        }

        Board board = new Board(setting);
//        for (int num : board.weight) {
//            System.out.print(num);
//        }
        board.generateLetter();

        int size = setting.getBoardSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board.grid[i][j]);
            }
            System.out.println();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(board.grid[0][0]);
        sb.append(board.grid[1][1]);
        sb.append(board.grid[1][1]);
//        sb.append(board.grid[3][3]);
        System.out.println(board.checkWord(sb.toString()));
        System.out.println(sb.toString());
        System.out.println(board.checkWord(sb.toString()));
        System.out.println(sb.toString());

//        System.out.println();
//        board.reset();
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                System.out.print(board.grid[i][j]);
//            }
//            System.out.println();
//        }
    }

}
