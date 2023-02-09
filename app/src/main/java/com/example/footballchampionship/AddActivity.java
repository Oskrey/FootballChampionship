package com.example.footballchampionship;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddActivity extends Activity {
    private Button btSave,btCancel;
    private EditText etGoalsHome,etGoalsGuest;
    private Context context;
    private long MyMatchID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        DBMatches dbMatches = new DBMatches(this);
         ArrayList test= dbMatches.GetAll();
        Spinner spinHome = (Spinner)findViewById(R.id.TeamHome);
        Spinner spinGuest = (Spinner)findViewById(R.id.TeamGuest);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, test);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinHome.setAdapter(adapter);
        spinGuest.setAdapter(adapter);


        etGoalsHome=(EditText)findViewById(R.id.GoalsHome);
        etGoalsGuest=(EditText)findViewById(R.id.GoalsGuest);
        btSave=(Button)findViewById(R.id.butSave);
        btCancel=(Button)findViewById(R.id.butCancel);

        if(getIntent().hasExtra("Matches")){
            Matches matches=(Matches)getIntent().getSerializableExtra("Matches");
            spinHome.setSelection(matches.getTeamhouse()-1);
            spinGuest.setSelection(matches.getTeamguest()-1);
            etGoalsHome.setText(Integer.toString(matches.getGoalshouse()));
            etGoalsGuest.setText(Integer.toString(matches.getGoalsguest()));
            MyMatchID=matches.getId();
        }
        else
        {
            MyMatchID=-1;
        }
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int teamhome =spinHome.getSelectedItemPosition()+1;
                int teamguest =spinGuest.getSelectedItemPosition()+1;

                Matches matches=new Matches(MyMatchID,teamhome,teamguest, Integer.parseInt(etGoalsHome.getText().toString()), Integer.parseInt(etGoalsGuest.getText().toString()));
                Intent intent=getIntent();
                intent.putExtra("Matches",matches);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}