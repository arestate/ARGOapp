package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
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

public class UserEnquiry extends AppCompatActivity implements DataInterface {
    EditText edtEnq;
    Button btnSubmit;
    TextView txt_propertyName;

    Webservice_Volley volley;
    AllSharedPrefernces allSharedPrefernces;

    PropertyResultVo propertyResultVo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_enquiry);

        getSupportActionBar().setTitle("Post your enquiry");

        edtEnq = (EditText) findViewById(R.id.edtEnq);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        txt_propertyName = (TextView) findViewById(R.id.txt_propertyName);

        volley = new Webservice_Volley(this, this);
        allSharedPrefernces=new AllSharedPrefernces(this);

        String data = getIntent().getStringExtra("data");


        if (!TextUtils.isEmpty(data)) {
            propertyResultVo = new Gson().fromJson(data,PropertyResultVo.class);

            txt_propertyName.setText((propertyResultVo != null) ? propertyResultVo.getPName() : "");
        }

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
                params.put("p_id",(propertyResultVo != null) ? propertyResultVo.getPId() : "0");
                params.put("b_id",(propertyResultVo != null) ? propertyResultVo.getBId() : "0");
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