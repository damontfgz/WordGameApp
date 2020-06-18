package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener{

    private Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Button gamestat = (Button) findViewById(R.id.GameStatButton);
        gamestat.setOnClickListener(this);
        Button wordstat = (Button) findViewById(R.id.wordStatButton);
        wordstat.setOnClickListener(this);
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        Intent intent = getIntent();
        player = intent.getParcelableExtra("player");
        if (player == null) {
            player = new Player();
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.GameStatButton:
                Intent i = new Intent(StatisticsActivity.this, GameStatsActivity.class);
                i.putExtra("player", player);
                startActivity(i);
                break;
            case R.id.wordStatButton:
                Intent i1 = new Intent(StatisticsActivity.this, WordStatActivity.class);
                i1.putExtra("player", player);
                startActivity(i1);
                break;
            case R.id.backButton:
                Intent i2 = new Intent(StatisticsActivity.this, MainActivity.class);
                i2.putExtra("player", player);
                startActivity(i2);
                break;
        }
    }
}
