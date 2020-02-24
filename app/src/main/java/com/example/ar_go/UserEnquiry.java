package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
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

public class UserEnquiry extends AppCompatActivity implements DataInterface {
    EditText edtEnq;
    Button btnSubmit;



    Webservice_Volley volley;
    AllSharedPrefernces allSharedPrefernces;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_enquiry);
        edtEnq = (EditText) findViewById(R.id.edtEnq);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        volley = new Webservice_Volley(this, this);
        allSharedPrefernces=new AllSharedPrefernces(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!CommonFunctions.checkstring(edtEnq.getText().toString())) {
                    edtEnq.setError("Enter Details");
                    edtEnq.requestFocus();
                    return;
                }
                HashMap<String, String> params = new HashMap<>();
                params.put("e_details", edtEnq.getText().toString());
                params.put("p_id","2");
                params.put("b_id","1");
                params.put("u_id",allSharedPrefernces.getCustomerNo());
                params.put("e_date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

                String url = Constants.Webserive_Url + "add_enquiry.php";

                volley.CallVolley(url, params, "add_enquiry");

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
    }}