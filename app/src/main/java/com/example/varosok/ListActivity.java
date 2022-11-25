package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    private Button back_but;
    private TextView list_text;
    private String base_url = "https://retoolapi.dev/wOGCeg/varosok";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        init();

        back_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        back_but = findViewById(R.id.back_but);
        list_text = findViewById(R.id.list_text);
        list_text.setMovementMethod(new ScrollingMovementMethod());
    }
}
