package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

public class InsertActivity extends AppCompatActivity {

    private Button back_but;
    private Button add_but;
    private EditText name_etext;
    private EditText country_etext;
    private EditText populis_etext;
    private String  base_url = "https://retoolapi.dev/wOGCeg/varosok";

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
        add_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name_etext.getText().toString().equals("")) {
                    Toast.makeText(InsertActivity.this, "Nem adott meg város nevet!", Toast.LENGTH_SHORT).show();
                } else if (country_etext.getText().toString().equals("")) {
                    Toast.makeText(InsertActivity.this, "Nem adott meg országot", Toast.LENGTH_SHORT).show();
                } else if (populis_etext.getText().toString().equals("")) {
                    Toast.makeText(InsertActivity.this, "Nem adott meglakosságot", Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(populis_etext.getText().toString()) < 0){
                    Toast.makeText(InsertActivity.this, "A lakosság minimum 0 lehet!", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        String json = createJsonFromFormdata();
                        RequestTask task = new RequestTask(base_url, "POST", json);
                        task.execute();
                    } catch (IllegalArgumentException e) {
                        Toast.makeText(InsertActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void init(){
        back_but = findViewById(R.id.back_but);
        add_but = findViewById(R.id.add_but);
        name_etext = findViewById(R.id.name_etext);
        country_etext = findViewById(R.id.country_etext);
        populis_etext = findViewById(R.id.populis_etext);
    }

    private String createJsonFromFormdata() {
        String nev = name_etext.getText().toString().trim();
        String orszag = country_etext.getText().toString().trim();
        String lakossagText = populis_etext.getText().toString().trim();
        int lakossag = Integer.parseInt(lakossagText);
        City city = new City(0, nev, orszag, lakossag);
        Gson converter = new Gson();
        return converter.toJson(city);
    }

    private class RequestTask extends AsyncTask<Void, Void, Response> {
        private String requestUrl;
        private String requestMethod;
        private String requestBody;

        public RequestTask(String requestUrl) {
            this.requestUrl = requestUrl;
            this.requestMethod = "GET";
        }

        public RequestTask(String requestUrl, String requestMethod) {
            this.requestUrl = requestUrl;
            this.requestMethod = requestMethod;
        }

        public RequestTask(String requestUrl, String requestMethod, String requestBody) {
            this.requestUrl = requestUrl;
            this.requestMethod = requestMethod;
            this.requestBody = requestBody;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                switch (requestMethod) {
                    case "GET":
                        response = RequestHandler.get(base_url);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestBody);
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestBody);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }
    }
}