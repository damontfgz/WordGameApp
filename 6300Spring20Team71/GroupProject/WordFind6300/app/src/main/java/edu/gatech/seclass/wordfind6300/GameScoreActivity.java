package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameScoreActivity extends AppCompatActivity {

    private Button back;
    private TextView finalScore;
    private Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_sore);

        back = (Button) findViewById(R.id.Back);
        finalScore = (TextView) findViewById(R.id.FinalScore);
        Intent intent = getIntent();
        finalScore.setText(intent.getStringExtra("score"));
        player = intent.getParcelableExtra("player");
    }

    public void exitClick(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("player",player);
        startActivity(intent);

    }
}
