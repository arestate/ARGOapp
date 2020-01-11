package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

        AreaList.add("Vasna-Bhayli");
        AreaList.add("Alkapuri");
        AreaList.add("Atladra");
        AreaList.add("Gotri");
        AreaList.add("Tarsali");
        AreaList.add("Gorwa");
        AreaList.add("Makarpura");
        AreaList.add("Manjalpur");
        AreaList.add("Maneja");
        AreaList.add("Karelibaug");
        AreaList.add("Sangam");
        AreaList.add("Channi");
        AreaList.add("Sama Savli");
        AreaList.add("Kalali");
        AreaList.add("Waghodiya");


        final ArrayAdapter<String> adapter = new ArrayAdapter<>(AreaSelection.this,android.R.layout.simple_list_item_1,AreaList);
        ltcities.setAdapter(adapter);

        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                adapter.getFilter().filter(editable.toString());

                /*if (!TextUtils.isEmpty(editable.toString()))
                {
                    adapter.getFilter().filter(editable.toString());
                }*/

            }
        });


    }
}
