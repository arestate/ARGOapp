package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ar_go.utils.CommonFunctions;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;

import java.util.HashMap;

import org.json.JSONObject;

import java.util.HashMap;

public class ForgotPassword extends AppCompatActivity implements DataInterface {

    EditText edtEmail;
    Button btnSubmit;

    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);


        volley = new Webservice_Volley(this, this);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!CommonFunctions.checkemail(edtEmail.getText().toString())) {
                    edtEmail.setError("Enter your Email");
                    return;
                }



                String url = Constants.Webserive_Url + "user_forgotpassword.php";

                HashMap<String, String> params = new HashMap<>();
                params.put("u_email", edtEmail.getText().toString());

                volley.CallVolley(url, params, "user_forgotpassword");



            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {
            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

            if (jsonObject.getString("response").equalsIgnoreCase("1")) {

                String id  = jsonObject.getString("id");
                String otp = jsonObject.getString("otp");

                Log.d("##MY_CODE","==> " + otp);

                Intent i = new Intent(ForgotPassword.this,VerificationCode.class);
                i.putExtra("id",id);
                i.putExtra("otp",otp);
                startActivity(i);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

