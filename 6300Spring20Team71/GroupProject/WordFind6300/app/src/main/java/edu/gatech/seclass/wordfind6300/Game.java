package edu.gatech.seclass.wordfind6300;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.VisibleForTesting;

public class Game implements Parcelable {
    public static final int PENALITY = 5;

    private Board board;
    private int totalScore;
    private int highestScore;
    private int numOfReset;
    private int numOfWords;
    private String topScoredWord;



    public Game () {
        totalScore = 0;
        numOfReset = 0;
        numOfWords = 0;
        highestScore = 0;
        topScoredWord = null;

    }

    protected Game(Parcel in) {
        totalScore = in.readInt();
        highestScore = in.readInt();
        numOfReset = in.readInt();
        numOfWords = in.readInt();
        topScoredWord = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public void start(GameSetting setting) {
        board = new Board(setting);
        board.generateLetter();
    }

    public boolean processInput(String input) {
        if (board.checkWord(input)) {
            totalScore += input.length();
            highestScore = Math.max(highestScore, input.length());
            numOfWords++;
            if (topScoredWord == null || topScoredWord.length() < input.length()) {
                topScoredWord = input;
            }
            return true;
        }
        return false;
    }

    public String[][] reroll() {
        numOfReset++;
        totalScore -= PENALITY;
        board.reset();
        return board.grid;
    }

    public Board getBoard() {
        return board;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public int getNumOfResets() {
        return numOfReset;
    }

    public int getNumOfWords() {
        return numOfWords;
    }

    public String getTopScoreWord() {
        return topScoredWord;
    }

    @VisibleForTesting
    void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(totalScore);
        dest.writeInt(highestScore);
        dest.writeInt(numOfReset);
        dest.writeInt(numOfWords);
        dest.writeString(topScoredWord);
    }
}
