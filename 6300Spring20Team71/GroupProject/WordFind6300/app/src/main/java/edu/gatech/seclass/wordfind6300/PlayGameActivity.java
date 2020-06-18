package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.Display;
import android.view.Gravity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Locale;
import java.util.Map;

public class PlayGameActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private LinearLayout[] tableRowArray;
    private Button[][] tableButtonArray;
    private EditText word;
    private GameSetting setting;
    private Player player;
    private EditText score;
    private TextView timer;
    private long timeInMillis;
    private boolean isActive = false;

    private Intent exitIntent;
    private CountDownTimer countTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isActive = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        Intent intent = getIntent();
        player = intent.getParcelableExtra("player");
        setting = player.getSetting();
        timeInMillis = setting.getGameDuration() * 60000;
        String[][] boardLetters = player.startApplication();
        int size = setting.getBoardSize();
        tableLayout = (TableLayout) findViewById(R.id.Board);
        tableLayout.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        timer = (TextView) findViewById(R.id.Timer);
        tableRowArray = new LinearLayout[size];
        tableButtonArray = new Button[size][size];
        word = (EditText)findViewById(R.id.Wrod);
//        score = (EditText) findViewById(R.id.Score);
        fillBoard(boardLetters);
        exitIntent = new Intent(this, GameScoreActivity.class);
        startTimer();
        updateCountDownText();
    }

    private void startTimer() {
        countTimer = new CountDownTimer(timeInMillis, 1000) {
            @Override
            public void onTick(long timeTillFinish) {
                timeInMillis = timeTillFinish;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                if (isActive) {
                    String str = "" + player.getCurrentGameScore();
                    player.exit();
                    exitIntent.putExtra("score", str);
                    exitIntent.putExtra("player", player);
                    startActivity(exitIntent);
                    isActive = false;
                }
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeInMillis / 1000) / 60;
        int seconds = (int) (timeInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timer.setText(timeLeftFormatted);
    }



    private void fillBoard(String[][] boardArray) {
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, 1);
        int size = setting.getBoardSize();
        for (int i = 0; i < size; i++) {
            if (tableRowArray[i] == null) {
                tableRowArray[i] = new LinearLayout(this);
                tableLayout.addView(tableRowArray[i]);
            }

            for (int j = 0; j < size; j++) {
                if (tableButtonArray[i][j] == null) {
                    tableButtonArray[i][j] = new Button(this);
                    tableButtonArray[i][j].setLayoutParams(params);
                    tableRowArray[i].addView(tableButtonArray[i][j]);
                }


                tableButtonArray[i][j].setText(boardArray[i][j]);
            }
        }
    }

    public void resetClick(View view) {
        String[][] boardArray = player.reRoll();
        fillBoard(boardArray);
//        score.setText("" + player.getCurrentGameScore());
    }

    public void checkWordClick(View view) {
        String input = word.getText().toString();
        if (!player.playGame(input)) {
            word.setError("Invalid Input!");
        }
//        else {
//            Map<String, Integer> wordStat = player.getWordStat();
//            Integer count = wordStat.get(input);
//            if (count == null) {
//                wordStat.put(input, 1);
//            } else {
//                System.out.println(count);
//                wordStat.put(input, count + 1);
//            }
//            word.setText("");
//        }
//        score.setText("" + player.getCurrentGameScore());
    }

    public void exitClick(View view) {
        isActive = false;
        String str = "" + player.getCurrentGameScore();
        player.exit();
        exitIntent.putExtra("score", str);
        exitIntent.putExtra("player", player);
        startActivity(exitIntent);
    }
}
