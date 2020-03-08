package com.example.ar_go;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ar_go.utils.AllSharedPrefernces;
import com.example.ar_go.utils.CommonFunctions;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class Login extends AppCompatActivity implements DataInterface {


    EditText edtEmail, edtPass;
    Button btnLogin;
    TextView txtForgotPassword, txtNewUser2;

    Webservice_Volley volley;
    AllSharedPrefernces allSharedPrefernces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtForgotPassword = (TextView) findViewById(R.id.txtForgotPassword);
        txtNewUser2 = (TextView) findViewById(R.id.txtNewUser2);

        volley = new Webservice_Volley(this, this);
        allSharedPrefernces=new AllSharedPrefernces(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!CommonFunctions.checkemail(edtEmail.getText().toString())) {
                    edtEmail.setError("Enter your Valid  Email");
                    edtEmail.requestFocus();
                    return;
                }

                if (!CommonFunctions.checkpassword(edtPass.getText().toString())) {
                    edtPass.setError("Enter password atleast 6 char long");
                    edtPass.requestFocus();
                    return;
                }

                HashMap<String, String> params = new HashMap<>();
                params.put("u_email", edtEmail.getText().toString());
                params.put("u_password", edtPass.getText().toString());

                String url = Constants.Webserive_Url + "user_login.php";
                volley.CallVolley(url, params, "user_login");
            }
        });
    }

    public void ClickonSignUp(View view) {
        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {
            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
            if (jsonObject.getString("response").equalsIgnoreCase("1")) {

                allSharedPrefernces.setUserLogin(true);
                allSharedPrefernces.setCustomerNo(jsonObject.getString("id"));
                allSharedPrefernces.setCustomerData(jsonObject.getJSONObject("data").toString());

                Intent i = new Intent(Login.this,DashboardActivity.class);
                startActivity(i);
                finishAffinity();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ClickonFP(View view) {

        Intent i = new Intent(Login.this, ForgotPassword.class);
        i.putExtra("type","user");
        startActivity(i);
    }
}