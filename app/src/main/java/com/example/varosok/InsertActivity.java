package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InsertActivity extends AppCompatActivity {

    private Button back_but;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        init();

        back_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        back_but = findViewById(R.id.back_but);
    }
}