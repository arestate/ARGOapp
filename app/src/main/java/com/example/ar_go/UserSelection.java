package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ar_go.utils.AllSharedPrefernces;

public class UserSelection extends AppCompatActivity {

    AllSharedPrefernces allSharedPrefernces = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);

        allSharedPrefernces = new AllSharedPrefernces(this);

        if (allSharedPrefernces.isUserLogin()) {

            if (allSharedPrefernces.getUserType().equalsIgnoreCase("user")) {

                Intent i = new Intent(UserSelection.this, DashboardActivity.class);
                startActivity(i);

                finishAffinity();

            }
            else if (allSharedPrefernces.getUserType().equalsIgnoreCase("builder")) {

                Intent i = new Intent(UserSelection.this, BuilderHomepage.class);
                startActivity(i);

                finishAffinity();

            }


        }


    }

    public void ClickOnBuilderLayout(View view) {

        allSharedPrefernces.setUserType("builder");


        Intent i = new Intent(UserSelection.this, LoginBuilder.class);
        startActivity(i);
    }

    public void ClickOnUserLayout(View view) {

        allSharedPrefernces.setUserType("user");

        Intent i = new Intent(UserSelection.this, Login.class);
        startActivity(i);
    }
}
