package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class LoginBuilder extends AppCompatActivity implements DataInterface {

    EditText edtEmail;
    EditText edtPassword;
    Button btnLogin;
    TextView txtForgotPassword;
    TextView txtSignup;

    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_builder);
        edtEmail =(EditText)findViewById(R.id.edtEmail);
        edtPassword =(EditText)findViewById(R.id.edtPassword);
        btnLogin =(Button) findViewById(R.id.btnLogin);
        txtForgotPassword=(TextView)findViewById(R.id.txtForgotPassword);
        txtSignup= (TextView)findViewById(R.id.btnSignUp);

        volley = new Webservice_Volley(this,this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String,String> params = new HashMap<>();
                params.put("b_email",edtEmail.getText().toString());
                params.put("b_password",edtPassword.getText().toString());

                String url = Constants.Webserive_Url + "login.php";

                volley.CallVolley(url,params,"login");


            }
        });

    }

    public void ClickOnSignup(View view) {
        Intent i= new Intent(LoginBuilder.this,SignUpBuilder.class);
        startActivity(i);
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
