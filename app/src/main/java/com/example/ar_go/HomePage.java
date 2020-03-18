package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ar_go.Adapter.BuilderListAdapter;
import com.example.ar_go.Adapter.MyListAdapter;
import com.example.ar_go.ApiModels.BuilderinfoVo;
import com.example.ar_go.Models.PropertyinfoVo;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class HomePage extends AppCompatActivity implements DataInterface {

    Webservice_Volley volley;

    RecyclerView recvProperties,recvBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recvProperties = (RecyclerView)findViewById(R.id.recvProperties);
        recvProperties.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        recvBuilder = (RecyclerView)findViewById(R.id.recvBuilder);
        recvBuilder.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        volley = new Webservice_Volley(this, this);

        HashMap<String, String> params = new HashMap<>();

        String url = Constants.Webserive_Url + "get_property.php";

        volley.CallVolley(url, params, "get_property");


        String url1 = Constants.Webserive_Url + "get_builder.php";

        volley.CallVolley(url1, params, "get_builder");


    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            if (tag.equalsIgnoreCase("get_builder")) {

                BuilderinfoVo builderinfoVo = new Gson().fromJson(jsonObject.toString(), BuilderinfoVo.class);

                if (builderinfoVo != null) {

                    if (builderinfoVo.getResult() != null) {

                        if (builderinfoVo.getResult().size() > 0) {

                            BuilderListAdapter adapter = new BuilderListAdapter(builderinfoVo.getResult());
                            recvBuilder.setAdapter(adapter);

                        }

                    }

                }

            }
            else {

//                Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();

                PropertyinfoVo propertyinfoVo = new Gson().fromJson(jsonObject.toString(), PropertyinfoVo.class);

                if (propertyinfoVo != null) {

                    if (propertyinfoVo.getResult() != null) {

                        if (propertyinfoVo.getResult().size() > 0) {

                            MyListAdapter adapter = new MyListAdapter(HomePage.this,propertyinfoVo.getResult());
                            recvProperties.setAdapter(adapter);

                        }

                    }

                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
