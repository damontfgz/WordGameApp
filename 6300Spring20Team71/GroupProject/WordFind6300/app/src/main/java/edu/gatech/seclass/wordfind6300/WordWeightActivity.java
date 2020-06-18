package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WordWeightActivity extends AppCompatActivity  implements  View.OnClickListener{
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        player = intent.getParcelableExtra("player");
        setContentView(R.layout.activity_word_weight);
        populateGridWithWeights();
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
    }

    public void populateGridWithWeights() {

        char[] list = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        GridLayout gv = (GridLayout) findViewById(R.id.weightGrid);
        gv.setBackgroundColor(Color.WHITE);
        int[] letterWeights = player.getSetting().getLetterWeights();
        for (int j = 0; j < list.length; j++) {
            LinearLayout parent = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, 1);
            parent.setOrientation(LinearLayout.VERTICAL);
            TextView tv = new TextView(this);
            tv.setGravity(Gravity.CENTER);
            EditText et = new EditText(this);
            tv.setText(String.valueOf(list[j]));
            et.setText("" + letterWeights[j]);
            et.setGravity(Gravity.CENTER);
            parent.addView(tv);
            parent.addView(et);
            et.setId(j);
            parent.setBackgroundResource(R.drawable.border_weight);
            parent.setLayoutParams(params);
            GridLayout.LayoutParams title_layout = new GridLayout.LayoutParams();
            title_layout.setGravity(Gravity.CENTER_HORIZONTAL);
            parent.setLayoutParams(title_layout);
            parent.setMinimumWidth(25);
            gv.addView(parent);

        }
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.saveButton:
                Intent i1 = new Intent(WordWeightActivity.this, SettingActivity.class);
                boolean result = readWordWeight();
                if (!result) {
                    TextView errorMsg = (TextView) findViewById(R.id.errorMsg);
                    errorMsg.setText("       !! Error: Weight must be between 1 - 5 !!");
                } else {
                    i1.putExtra("player", player);
                    startActivity(i1);
                }
                break;
            case R.id.backButton:
                Intent i = new Intent(WordWeightActivity.this, SettingActivity.class);
                i.putExtra("player", player);
                startActivity(i);
                break;
        }
    }

    public boolean readWordWeight(){
        char[] list = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int[] weights = new int[26];
        int weight = 1;

        for (int j = 0; j < list.length; j++) {
            EditText editText = (EditText) findViewById(j);
            String text = editText.getText().toString();
            if(text != null && text.length() > 0) {
                try {
                    weight = Integer.parseInt(text);
                    if (weight < 0 || weight > 5) {
                        return false;
                    }
                }
                catch(NumberFormatException e){
                    weight = 1;
                }
            }
            weights[j]=weight;
            //player.getSetting().changeWeight(list[j],weight);
        }
        for (int i = 0; i < weights.length; i++) {
            player.getSetting().changeWeight(list[i], weights[i]);
        }
        return true;
    }

}
