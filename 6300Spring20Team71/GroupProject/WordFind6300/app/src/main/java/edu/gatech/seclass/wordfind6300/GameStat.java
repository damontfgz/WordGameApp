package edu.gatech.seclass.wordfind6300;

import android.os.Parcel;
import android.os.Parcelable;

public class GameStat implements Parcelable {
    private int finalScore;
    private int highestScore;
    private int numOfReset;
    private int numOfWords;
    private int boardSize;
    private int duration;
    private String topScoreWord;

    /**
     * Constructor to create GameStat for a Game.
     * @param finalScore final score scored in a game
     * @param highestScore the highest score scored by a word played in the game
     * @param numOfReset number of times a board was reset during a game
     * @param numOfWords number of words played in a game
     * @param topScoreWord the word scoring the highest point in a game
     * @param boardSize the size of the board used in the game
     * @param duration the duration of the game
     */
    public GameStat(int finalScore, int highestScore, int numOfReset, int numOfWords, String topScoreWord, int boardSize, int duration) {
        this.finalScore = finalScore;
        this.highestScore = highestScore;
        this.numOfReset = numOfReset;
        this.numOfWords = numOfWords;
        this.topScoreWord = topScoreWord;
        this.boardSize = boardSize;
        this.duration = duration;
    }

    protected GameStat(Parcel in) {
        finalScore = in.readInt();
        highestScore = in.readInt();
        numOfReset = in.readInt();
        numOfWords = in.readInt();
        boardSize = in.readInt();
        duration = in.readInt();
        topScoreWord = in.readString();
    }

    public static final Creator<GameStat> CREATOR = new Creator<GameStat>() {
        @Override
        public GameStat createFromParcel(Parcel in) {
            return new GameStat(in);
        }

        @Override
        public GameStat[] newArray(int size) {
            return new GameStat[size];
        }
    };

    public int getFinalScore() {
        return finalScore;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public int getNumOfReset() {
        return numOfReset;
    }

    public int getNumOfWords() {
        return numOfWords;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getDuration() {
        return duration;
    }

    public String getTopScoreWord() {
        return topScoreWord;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(finalScore);
        dest.writeInt(highestScore);
        dest.writeInt(numOfReset);
        dest.writeInt(numOfWords);
        dest.writeInt(boardSize);
        dest.writeInt(duration);
        dest.writeString(topScoreWord);
    }
}

