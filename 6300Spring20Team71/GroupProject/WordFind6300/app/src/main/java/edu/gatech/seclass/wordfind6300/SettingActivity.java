package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SettingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    String[] boardSize = {"4", "5", "6", "7", "8"};
    String selectedSize;
    String[] gameTime = {"1", "2", "3", "4", "5"};
    String selectedTime;
    private GameSetting gameSetting;
    private int settingPosition;
    private Player player;
    private Spinner boardSizeSpin, gameTimeSpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //gameSetting = new GameSetting();
        Intent intent = getIntent();
         player = intent.getParcelableExtra("player");
//        if (player != null) {
//            gameSetting = player.getSetting();
//        } else{
//            gameSetting = new GameSetting();
//        }

        //Setting the ArrayAdapter data on the Spinner

        boardSizeSpin = (Spinner) findViewById(R.id.boardSize);
        boardSizeSpin.setOnItemSelectedListener(this);
        ArrayAdapter sizeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,boardSize);
        sizeAdapter.setDropDownViewResource(R.layout.spinner_item);
        //Setting the ArrayAdapter data on the Spinner
        boardSizeSpin.setAdapter(sizeAdapter);

        gameTimeSpin = (Spinner) findViewById(R.id.gameTime);
        gameTimeSpin.setOnItemSelectedListener(this);
        ArrayAdapter timeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,gameTime);
        timeAdapter.setDropDownViewResource(R.layout.spinner_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // Setting the ArrayAdapter data on the Spinner
        gameTimeSpin.setAdapter(timeAdapter);

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        Button weightButton = (Button) findViewById(R.id.wordWeight);
        weightButton.setOnClickListener(this);
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
            selectedSize = boardSizeSpin.getSelectedItem().toString();
            selectedTime = gameTimeSpin.getSelectedItem().toString();

//        selectedSize = boardSizeSpin.getItemAtPosition(position).toString();
//        System.out.println("selectedSize : "+selectedSize);
//        selectedTime = gameTimeSpin.getItemAtPosition(position).toString();
//        System.out.println("selectedTime : "+selectedTime);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.saveButton:
                Intent i2 = new Intent(SettingActivity.this, MainActivity.class);
                //Save all the settings to player
                player.getSetting().changeDuration(Integer.parseInt(selectedTime));
                player.getSetting().changeSize(Integer.parseInt(selectedSize));
                i2.putExtra("player", player);
                startActivity(i2);

                break;
            case R.id.wordWeight:
                Intent i1 = new Intent(SettingActivity.this, WordWeightActivity.class);
                i1.putExtra("player", player);
                startActivity(i1);
                break;
            case R.id.backButton:
                Intent i = new Intent(SettingActivity.this, MainActivity.class);
                i.putExtra("player", player);
                startActivity(i);
//                i.putExtra("gamesetting",gameSetting);
                break;
        }
    }

}
