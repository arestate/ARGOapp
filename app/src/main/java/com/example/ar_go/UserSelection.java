package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);
    }

    public void ClickOnBuilderLayout(View view) {
        Intent i = new Intent(UserSelection.this, PropertyListActivity.class);
        startActivity(i);
    }

    public void ClickOnUserLayout(View view) {
        Intent i = new Intent(UserSelection.this, Feedback.class);
        startActivity(i);
    }
}
