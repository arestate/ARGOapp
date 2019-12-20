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

public class Login extends AppCompatActivity implements DataInterface {


    EditText edtEmail, edtPass;
    Button btnLogin;
    TextView txtForgotPassword, txtNewUser;

    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtForgotPassword = (TextView) findViewById(R.id.txtForgotPassword);
        txtNewUser = (TextView) findViewById(R.id.txtNewUser);

        volley = new Webservice_Volley(this, this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> params = new HashMap<>();
                params.put("u_email", edtEmail.getText().toString());
                params.put("u_password", edtPass.getText().toString());

                String url = Constants.Webserive_Url + "login.php";
                volley.CallVolley(url, params, "login");
            }
        });
    }

    public void ClickonSignUp(View view) {
        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
    }



    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {
            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ClickonFP(View view) {

        Intent i = new Intent(Login.this, ForgotPassword.class);
        startActivity(i);
    }
}