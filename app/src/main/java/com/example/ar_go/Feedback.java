package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ar_go.Models.PropertyResultVo;
import com.example.ar_go.utils.AllSharedPrefernces;
import com.example.ar_go.utils.CommonFunctions;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Feedback extends AppCompatActivity implements DataInterface {

    EditText edtFeed;
    Button btngetRate;
    RatingBar Ratebar;

    TextView txt_propertyName;

    Webservice_Volley volley;
    AllSharedPrefernces allSharedPrefernces;

    PropertyResultVo propertyResultVo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        getSupportActionBar().setTitle("Give your feedback");

        edtFeed = (EditText)findViewById(R.id.edtFeed);
        btngetRate = (Button) findViewById(R.id.btngetRate);
        Ratebar = (RatingBar) findViewById(R.id.Ratebar);

        txt_propertyName = (TextView) findViewById(R.id.txt_propertyName);


        volley = new Webservice_Volley(this, this);
        allSharedPrefernces=new AllSharedPrefernces(this);

        String data = getIntent().getStringExtra("data");

        if (!TextUtils.isEmpty(data)) {
            propertyResultVo = new Gson().fromJson(data,PropertyResultVo.class);

            txt_propertyName.setText((propertyResultVo != null) ? propertyResultVo.getPName() : "");
        }

        btngetRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!CommonFunctions.checkstring(edtFeed.getText().toString())) {
                    edtFeed.setError("Enter Details");
                    edtFeed.requestFocus();
                    return;
                }



                String rating=String.valueOf(Ratebar.getRating());
                HashMap<String, String> params = new HashMap<>();
                params.put("f_details", edtFeed.getText().toString());
                params.put("p_id",(propertyResultVo != null) ? propertyResultVo.getPId() : "0");
                params.put("b_id",(propertyResultVo != null) ? propertyResultVo.getBId() : "0");
                params.put("u_id",allSharedPrefernces.getCustomerNo());
                params.put("f_date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                params.put("f_ratings",rating);


                String url = Constants.Webserive_Url + "add_feedback.php";

                volley.CallVolley(url, params, "add_feedback");

            }
        });



    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

            if (jsonObject.getString("response").equalsIgnoreCase("1")) {
                finish();
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
