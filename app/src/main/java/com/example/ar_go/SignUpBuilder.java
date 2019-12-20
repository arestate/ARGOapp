package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    Button btnSignup;

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
        btnSignup = (Button) findViewById(R.id.btnSignUp);

        volley = new Webservice_Volley(this, this);


    btnSignup.setOnClickListener(new View.OnClickListener()

    {

        @Override
        public void onClick (View view){

        HashMap<String, String> params = new HashMap<>();
        params.put("b_name",edtName.getText().toString());
        params.put("b_email", edtEmail.getText().toString());
        params.put("b_contactno", edtContactNo.getText().toString());
        params.put("b_address", edtAddress.getText().toString());
        params.put("b_password", edtPassword.getText().toString());

        String url = Constants.Webserive_Url + "SignUpBuilder.php";

        volley.CallVolley(url, params, "SignUpBuilder");


    }
    });
}

    @Override
    public void getData(JSONObject jsonObject, String tag) {

            try {

                Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

            }catch (Exception e){
                e.printStackTrace();
            }
    }
}




