package edu.gatech.seclass.wordfind6300;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.*;

/**
 * This is the main class that the UI primarily interacts with. It manages the Game, GameSetting,
 * WordStat and the GameScoreStats for a player.
 */
public class Player implements Parcelable {
    Map<String, Integer> wordStat = new HashMap<>();
    List<GameStat> gameStats = new ArrayList<>();
    private Game game;
    private GameSetting setting;

    /**
     * Default constructor for Player class.
     */
    public Player() {
        this.setting = new GameSetting();
    }

    protected Player(Parcel in) {
        in.readMap(wordStat, Map.class.getClassLoader());
        in.readTypedList(gameStats, GameStat.CREATOR);
        this.game = in.readParcelable(Game.class.getClassLoader());
        this.setting = in.readParcelable(GameSetting.class.getClassLoader());
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    /**
     * This method gets called to start the game. This method creates the game object
     * and returns the board.
     * @return String[][] representing the board contents
     */
    public String[][] startApplication(){
        game = new Game();
        game.start(getSetting());
        return game.getBoard().display();
    }

    /**
     * process the input word entered by the player. If the word is valid, this method also stores the
     * word and the count of the word in the wordStat to record the statistics for the words played in
     * the game.
     * @param wordPlay the word entered by the user
     * @return boolean true if the word is valid, false otherwise.
     */
    public boolean playGame(String wordPlay){
        boolean result =  game.processInput(wordPlay);
        if (result){
            if (wordStat.containsKey(wordPlay)) {
                int count = wordStat.get(wordPlay);
                wordStat.put(wordPlay, count + 1);
            } else {
                wordStat.put(wordPlay, 1);
            }
        }
        return result;
    }

    /**
     * This method is used to change the settings for the game.
     * @param boardSize the new value for the board size
     * @param gameTime the new value of the game duration
     * @param weights the new value for the letter weights.
     */
    public void changeSettings(int boardSize, int gameTime, int[] weights){
        setSetting(new GameSetting(boardSize, gameTime, weights));
    }

    /**
     * This method returns the word statistics for the player. This statistic is the word and the
     * count of the number of times the word was played in the games. This statistics is ordered in
     * the descending order of the count.
     * @return list of pair of word and the count.
     */
//    public List<Map.Entry<String, Integer>> checkWordStat(){
//        List<Map.Entry<String, Integer>> sortedStat = new ArrayList<>(wordStat.entrySet());
//        Collections.sort(sortedStat, new Comparator<Map.Entry<String, Integer>>() {
//
//            @Override
//            public int compare(Map.Entry<String, Integer> wordStat1, Map.Entry<String, Integer> wordStat2) {
//                if (wordStat1.getValue() > wordStat2.getValue()) {
//                    return -1;
//                }
//                ;
//                if (wordStat1.getValue() < wordStat2.getValue()) {
//                    return 1;
//                }
//                ;
//                return 0;
//            }
//        });
//        return sortedStat;
//    }

    /**
     * This method returns the statistics for the game scores. This statistics is in descending order
     * of the {@GameStat#finalScore}
     * @return list of {@GameStat}
     */
    public List<GameStat> sortGameStat(){
        Collections.sort(gameStats, new Comparator<GameStat>() {
            @Override
            public int compare(GameStat game1, GameStat game2) {
                if (game1.getFinalScore() > game2.getFinalScore())
                    return -1;

                if (game1.getFinalScore() < game2.getFinalScore())
                    return 1;
                return 0;
            }
        });
        return gameStats;
    }

    /**
     * This method is called when the user re-rolls the board.
     * @return the 2D array of String that represents the board contents.
     */
    public String[][] reRoll(){
        return game.reroll();
    }

    /**
     * This method exits the game when either the time runs out or the user quits the game.
     */
    public void exit(){
        GameStat gameStat = new GameStat(
                game.getTotalScore(),
                game.getHighestScore(),
                game.getNumOfResets(),
                game.getNumOfWords(),
                game.getTopScoreWord(),
                getSetting().getBoardSize(),
                getSetting().getGameDuration());
        gameStats.add(gameStat);
    }

    public int getCurrentGameScore() {
        return game.getTotalScore();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(wordStat);
        dest.writeTypedList(gameStats);
        dest.writeParcelable(game, flags);
        dest.writeParcelable(setting, flags);
    }

    public GameSetting getSetting() {
        return setting;
    }

    public void setSetting(GameSetting setting) {
        this.setting = setting;
    }

    public Game getGame() {
        return game;
    }

    public Map<String, Integer> getWordStat() {
        return wordStat;
    }

    public List<GameStat> getGameStats() {
        return gameStats;
    }
}