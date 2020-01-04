package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ar_go.utils.CommonFunctions;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class SignUpBuilder extends AppCompatActivity implements DataInterface {
    EditText edtName;
    EditText edtEmail;
    EditText edtContactNo;
    EditText edtAddress;
    EditText edtPassword;
    EditText edtCnfPassword;
    EditText edtWebsite;
    CheckBox chkAgree;
    Button btnSignUp;

    Webservice_Volley volley;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_builder);
        edtName = (EditText) findViewById(R.id.edtName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtContactNo = (EditText) findViewById(R.id.edtContactNo);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtCnfPassword = (EditText) findViewById(R.id.edtConfirm);
        edtWebsite = (EditText) findViewById(R.id.edtWebsite);
        chkAgree = (CheckBox) findViewById(R.id.chkAgree);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        volley = new Webservice_Volley(this, this);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!CommonFunctions.checkstring(edtName.getText().toString())) {
                    edtName.setError("Enter your name");
                    edtName.requestFocus();
                    return;
                }

                if (!CommonFunctions.checkemail(edtEmail.getText().toString())) {
                    edtEmail.setError("Enter your Valid  Email");
                    edtEmail.requestFocus();
                    return;
                }

                if (!CommonFunctions.checkmobilenumber(edtContactNo.getText().toString())) {
                    edtContactNo.setError("Enter 10 digit Mobile Number");
                    edtContactNo.requestFocus();
                    return;
                }

                if (!CommonFunctions.checkstring(edtAddress.getText().toString())) {
                    edtAddress.setError("Enter your Address");
                    edtAddress.requestFocus();
                    return;
                }

                if (!chkAgree.isChecked()) {
                    Toast.makeText(SignUpBuilder.this, "Please agree terms & conditions", Toast.LENGTH_LONG).show();
                    return;
                }






                    HashMap<String, String> params = new HashMap<>();
                    params.put("b_name", edtName.getText().toString());
                    params.put("b_email", edtEmail.getText().toString());
                    params.put("b_contactno", edtContactNo.getText().toString());
                    params.put("b_address", edtAddress.getText().toString());
                    params.put("b_password", edtPassword.getText().toString());
                    params.put("b_licenseno", "");
                    params.put("b_website", edtWebsite.getText().toString());
                    params.put("b_logo", "");
                    params.put("b_status", "1");


                    String url = Constants.Webserive_Url + "builder_registration.php";

                    volley.CallVolley(url, params, "builder_registration");


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

