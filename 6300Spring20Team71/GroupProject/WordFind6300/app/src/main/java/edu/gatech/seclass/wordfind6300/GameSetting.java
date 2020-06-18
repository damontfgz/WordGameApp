package edu.gatech.seclass.wordfind6300;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Arrays;

public class GameSetting implements Parcelable {
    private int boardSize;
    private int gameDuration;
    private int[] letterWeights;

    /**
     * Constructor to create GameSetting with default setting values
     */
    public GameSetting() {
        boardSize = 4;
        gameDuration = 3;
        letterWeights = new int[26];
        Arrays.fill(letterWeights, 1);
    }

    /**
     * Constructor to create GameSetting with the input parameters.
     * @param boardSize size of the board
     * @param gameDuration duration of the game in minutes
     * @param letterWeights weights of the letters to be displayed in the board
     */
    public GameSetting(int boardSize, int gameDuration, int[] letterWeights) {
        this.boardSize = boardSize;
        this.gameDuration = gameDuration;
        this.letterWeights = letterWeights;
    }

    protected GameSetting(Parcel in) {
        boardSize = in.readInt();
        gameDuration = in.readInt();
        letterWeights = in.createIntArray();
    }

    public static final Creator<GameSetting> CREATOR = new Creator<GameSetting>() {
        @Override
        public GameSetting createFromParcel(Parcel in) {
            return new GameSetting(in);
        }

        @Override
        public GameSetting[] newArray(int size) {
            return new GameSetting[size];
        }
    };

    public int getBoardSize() {
        return boardSize;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public int[] getLetterWeights() {
        return letterWeights;
    }

    public void changeDuration(int duration) {
        //valid duration 1 - 5 min
        if (duration <1 || duration > 5) {
            throw new IllegalArgumentException("Invalid timer value!");
        }
        this.gameDuration = duration;

    }

    public void changeSize(int boardSize) {
        //valid size 4 - 8
        if (boardSize < 4 || boardSize > 8) {
            throw new IllegalArgumentException("Invalid size value");
        }
        this.boardSize = boardSize;
    }

    public void changeWeight(char letter, int weight) {
        if (weight < 0 || weight > 5) {
            throw new IllegalArgumentException("Invalid weight value!");
        }
        int index = letter - 'a';
        if (index < 0 || index > 25) {
            throw new IllegalArgumentException("Invalid letter!");
        }
        letterWeights[index] = weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(boardSize);
        dest.writeInt(gameDuration);
        dest.writeIntArray(letterWeights);
    }
}
