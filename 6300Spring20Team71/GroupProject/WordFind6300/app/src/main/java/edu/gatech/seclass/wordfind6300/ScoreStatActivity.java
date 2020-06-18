package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Map;


public class ScoreStatActivity extends AppCompatActivity implements View.OnClickListener {
    TextView boardSize, gameTime, highScoreWord;
    private Player player;
    private GameStat gameStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_stat);
        Bundle bundle = getIntent().getExtras();
        int gameID = bundle.getInt("gameID");
        System.out.println("in scorestatActivity Game Id clicked : " + gameID);
        boardSize = (TextView) findViewById(R.id.boardSize);
        gameTime = (TextView) findViewById(R.id.gameTime);
        highScoreWord = (TextView) findViewById(R.id.highScoredWord);
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        Intent intent = getIntent();
        player = intent.getParcelableExtra("player");
        gameStat = intent.getParcelableExtra("stat");

        populateScoreStat(gameStat);
    }

    public void populateScoreStat(GameStat stat) {

        boardSize.setText(String.valueOf(stat.getBoardSize()));
        gameTime.setText(String.valueOf(stat.getDuration()));
        highScoreWord.setText(stat.getTopScoreWord());
    }

//    public void populateScoreStat(int gameId) {
//        if (player != null) {
//            if (player.getSetting() != null) {
//                GameSetting gameSetting = player.getSetting();
////                System.out.println("getBoardSize : " + gameSetting.getBoardSize());
////                System.out.println("getNoOfMinutes : " + gameSetting.getGameDuration());
//                boardSize.setText(String.valueOf(gameSetting.getBoardSize()));
//                gameTime.setText(String.valueOf(gameSetting.getGameDuration()));
//            }
//            if (player.gameStats.size() > 0 && player.gameStats.get(gameId) != null) {
//                System.out.println("getHighScoredWord : " + player.gameStats.get(gameId).getTopScoreWord());
//                highScoreWord.setText(player.gameStats.get(gameId).getTopScoreWord());
//            }
//
//        }
//    }



    @Override
    public void onClick(View view) {

        Intent i = new Intent(ScoreStatActivity.this, GameStatsActivity.class);
        i.putExtra("player",player);
        startActivity(i);
    }
}
