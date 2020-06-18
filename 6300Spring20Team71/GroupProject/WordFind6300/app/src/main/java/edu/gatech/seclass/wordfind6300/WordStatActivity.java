package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WordStatActivity extends AppCompatActivity implements View.OnClickListener{

    TableLayout wordTable;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_stat);
        Button gamestat = (Button) findViewById(R.id.backButton);
        gamestat.setOnClickListener(this);
        wordTable = (TableLayout) findViewById(R.id.wordtable);

        Intent intent = getIntent();
        player = intent.getParcelableExtra("player");
        populateWordTable();

    }

    private List<Map.Entry<String, Integer>> checkWordStat(){
        List<Map.Entry<String, Integer>> sortedStat = new ArrayList<>(player.wordStat.entrySet());
        Collections.sort(sortedStat, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> wordStat1, Map.Entry<String, Integer> wordStat2) {
                if (wordStat1.getValue() > wordStat2.getValue()) {
                    return -1;
                }
                ;
                if (wordStat1.getValue() < wordStat2.getValue()) {
                    return 1;
                }
                ;
                return 0;
            }
        });
        return sortedStat;
    }

    public void populateWordTable() {

        //Add data to table
        if(player != null) {
            List<Map.Entry<String, Integer>> wordMap = checkWordStat();
//            Map<String, Integer> wordMap = player.wordStat; // This will be real data
//            Map<String ,Integer> wordMap = fetchWordData(); // This is for dummy data
//            System.out.print("wordMap : " + wordMap);
            if (wordMap.size() > 0) {
                for (Map.Entry<String, Integer> entry : wordMap) {
                    TableRow tr = new TableRow(this);
                    Button c1 = new Button(this);
                    c1.setText(entry.getKey());
                    c1.setClickable(false);
                    Button c2 = new Button(this);
                    c2.setText(String.valueOf(entry.getValue()));
                    c2.setClickable(false);
                    tr.addView(c1);
                    tr.addView(c2);
                    wordTable.addView(tr);
                }
            }
        }

    }
//    public Map<String ,Integer> fetchWordData(){
//        Map<String ,Integer> wordMap = new TreeMap<String ,Integer>();
//
//        wordMap.put("apple",5);
//        wordMap.put("animal",3);
//        wordMap.put("boy",3);
//        wordMap.put("hat",2);
//        wordMap.put("vinegar",1);
//        wordMap.put("letter",1);
//        return wordMap;
//    }
    @Override
    public void onClick(View view) {

        Intent i = new Intent(WordStatActivity.this, StatisticsActivity.class);
        i.putExtra("player",player);
        startActivity(i);
    }
}
