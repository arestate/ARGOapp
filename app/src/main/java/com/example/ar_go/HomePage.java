package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class HomePage extends AppCompatActivity implements DataInterface {

    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        volley = new Webservice_Volley(this, this);

        HashMap<String, String> params = new HashMap<>();


        String url = Constants.Webserive_Url + "get_property.php";
        volley.CallVolley(url, params, "get_property");


    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

    }
}
