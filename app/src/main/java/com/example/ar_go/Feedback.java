package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.ar_go.utils.AllSharedPrefernces;
import com.example.ar_go.utils.CommonFunctions;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Feedback extends AppCompatActivity implements DataInterface {

    EditText edtFeed;
    Button btngetRate;
    RatingBar Ratebar;

    Webservice_Volley volley;
    AllSharedPrefernces allSharedPrefernces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        edtFeed = (EditText)findViewById(R.id.edtFeed);
        btngetRate = (Button) findViewById(R.id.btngetRate);
        Ratebar = (RatingBar) findViewById(R.id.Ratebar);

        volley = new Webservice_Volley(this, this);
        allSharedPrefernces=new AllSharedPrefernces(this);
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
                params.put("p_id","2");
                params.put("b_id","1");
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
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
