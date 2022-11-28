package com.example.footballchampionship;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        update(findViewById(R.id.textViewList));

    }
    private void update(TextView tw){
        DB db = new DB(MainActivity.this);
        for (Data data: db.GetAll()) {
            tw.setText(tw.getText()+ data.TeamHome + " - "+ data.TeamGuest+ " "+data.GoalsHome+":"+data.GoalsGuest+"\n");

        }
    }
    public void click(View view){
        DB db = new DB(MainActivity.this);
        db.Insert(findViewById(R.id.) teamHome, String teamGuest, String goalsHome, String goalsGuest)
    }
}