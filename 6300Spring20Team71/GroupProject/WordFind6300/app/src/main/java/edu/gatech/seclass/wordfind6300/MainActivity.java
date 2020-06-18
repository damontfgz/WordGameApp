package edu.gatech.seclass.wordfind6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Button playButton;
    private Button settingButton;
    private Button viewStatButton;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (Button)findViewById(R.id.playButton);
        settingButton = (Button)findViewById(R.id.settingButton);
        viewStatButton = (Button)findViewById(R.id.viewStatButton);
        Intent intent = getIntent();
        player = intent.getParcelableExtra("player");
        if (player == null) {
            player = new Player();
        }
        //Here we retrive settings from player object
        System.out.println("Selected Board Size : "+player.getSetting().getBoardSize());
        System.out.println("Selected Game Duration : "+player.getSetting().getGameDuration());
        System.out.println("Selected Letter of Weights : "+ Arrays.toString(player.getSetting().getLetterWeights()));

    }

    public void handleClick(View view) {
        switch (view.getId()) {
            case R.id.playButton:
                Intent intent1 = new Intent(this, PlayGameActivity.class);
                intent1.putExtra("player", player);
                startActivity(intent1);
                break;

           case R.id.settingButton:
                Intent intent2 = new Intent(this, SettingActivity.class);
                intent2.putExtra("player", player);
                startActivity(intent2);
                break;

            case R.id.viewStatButton:
                Intent intent3 = new Intent(this, StatisticsActivity.class);
                intent3.putExtra("player", player);
                startActivity(intent3);
                break;
        }
    }


}
