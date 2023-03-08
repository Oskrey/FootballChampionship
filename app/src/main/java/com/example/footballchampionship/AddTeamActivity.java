package com.example.footballchampionship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        btSave=(Button)findViewById(R.id.saveTeam);
        btCancel=(Button)findViewById(R.id.exit);
        text=(EditText)findViewById(R.id.inputTeam);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teamName = text.getText().toString();
                Intent intent=getIntent();
                intent.putExtra("Team",teamName);
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
    private Button btSave,btCancel;
    private EditText text;

}