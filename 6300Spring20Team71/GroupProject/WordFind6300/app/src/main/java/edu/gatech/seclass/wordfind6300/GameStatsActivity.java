package edu.gatech.seclass.wordfind6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class GameStatsActivity extends AppCompatActivity implements View.OnClickListener {

    TableLayout scoreTable;
    Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_stats);
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);
        scoreTable = (TableLayout) findViewById(R.id.scoretable);
        Intent intent = getIntent();
        player = intent.getParcelableExtra("player");
        populateScoreTable(player.sortGameStat());
    }

    public void populateScoreTable(final List<GameStat> gameStatList) {
        int id = 0;
        for (GameStat g : gameStatList) {
            TableRow tr = new TableRow(this);
            tr.setId(id++);
            tr.setClickable(true);
            Button c1 = new Button(this);
            c1.setText(String.valueOf(g.getFinalScore()));
            c1.setClickable(false);
            Button c2 = new Button(this);
            c2.setText(String.valueOf(g.getNumOfReset()));
            c2.setClickable(false);
            Button c3 = new Button(this);
            c3.setText(String.valueOf(g.getNumOfWords()));
            c3.setClickable(false);
            tr.addView(c1);
            tr.addView(c2);
            tr.addView(c3);
            scoreTable.addView(tr);
            tr.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent intent = new Intent(GameStatsActivity.this, ScoreStatActivity.class);
                    intent.putExtra("stat", gameStatList.get(v.getId()));
                    intent.putExtra("player", player);
                    startActivity(intent);

                }
            });

        }
    }
//This method populates the data in score table
//    public void populateScoreTable() {
//
//        //Add data to table
//        System.out.print("gameMap : " + gameMap);
//
//        for (Map.Entry<Integer,GameStat>  entry : gameMap.entrySet()) {
//            GameStat g = entry.getValue();
//            TableRow tr = new TableRow(this);
//            tr.setId(entry.getKey());
//            tr.setClickable(true);
//            Button c1 = new Button(this);
//            c1.setText(String.valueOf(g.getFinalScore()));
//            c1.setClickable(false);
//            Button c2 = new Button(this);
//            c2.setText(String.valueOf(g.getNumOfReset()));
//            c2.setClickable(false);
//            Button c3 = new Button(this);
//            c3.setText(String.valueOf(g.getNumOfWords()));
//            c3.setClickable(false);
//            tr.addView(c1);
//            tr.addView(c2);
//            tr.addView(c3);
//            scoreTable.addView(tr);
//            tr.setOnClickListener(new View.OnClickListener() {
//
//                public void onClick(View v) {
//                    System.out.println("Game clicked :" + v.getId());
//                    Intent intent = new Intent(GameStatsActivity.this, ScoreStatActivity.class);
//                    intent.putExtra("gameID",v.getId());
//                    startActivity(intent);
//
//                }
//            });
//
//        }
//
//    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(GameStatsActivity.this, StatisticsActivity.class);
        i.putExtra("player", player);
        startActivity(i);
    }

//    public  void  populateGameData() {
//        gameMap = new HashMap<Integer,GameStat>();
//        if(player != null && player.gameStats.size() > 0) {
//            List<GameStat> list = player.gameStats;
//            for (int i = 0; i < list.size(); i++) {
//                gameMap.put(i, list.get(i));
//            }
//        }
//        System.out.print("gameMap : " + gameMap);
//    }
}
