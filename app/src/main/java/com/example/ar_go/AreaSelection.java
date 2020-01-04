package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AreaSelection extends AppCompatActivity {

    EditText edtsearch;
    TextView tvdetect;
    TextView tvareas;
    ListView ltcities;

    ArrayList<String> AreaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_selection);

        edtsearch = (EditText) findViewById(R.id.edtsearch);
        tvdetect = (TextView) findViewById(R.id.tvdetect);
        tvareas = (TextView) findViewById(R.id.tvareas);
        ltcities = (ListView) findViewById(R.id.ltcities);

        AreaList.add("Vasna");
        AreaList.add("Alkapuri");
        AreaList.add("Atladra");
        AreaList.add("Gotri");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(AreaSelection.this,android.R.layout.simple_list_item_1,AreaList);
        ltcities.setAdapter(adapter);


    }
}
