package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ar_go.Adapter.MyListAdapter;
import com.example.ar_go.Models.PropertyinfoVo;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class PropertyListActivity extends AppCompatActivity implements DataInterface {

    Webservice_Volley volley;
    RecyclerView recvBuilder;

    String b_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_list);

        recvBuilder = (RecyclerView)findViewById(R.id.recvBuilder);
        recvBuilder.setLayoutManager(new GridLayoutManager(this,2));

        b_id = getIntent().getStringExtra("b_id");

        volley = new Webservice_Volley(this, this);

        HashMap<String, String> params = new HashMap<>();
        params.put("b_id",b_id);

        String url = Constants.Webserive_Url + "get_property_based_on_builders.php";

        volley.CallVolley(url, params, "get_property_based_on_builders");


    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

           // Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();

            PropertyinfoVo propertyinfoVo = new Gson().fromJson(jsonObject.toString(),PropertyinfoVo.class);

            if (propertyinfoVo != null) {

                if (propertyinfoVo.getResult() != null) {

                    if (propertyinfoVo.getResult().size() > 0) {

                        MyListAdapter adapter = new MyListAdapter(PropertyListActivity.this,propertyinfoVo.getResult());
                        recvBuilder.setAdapter(adapter);

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
