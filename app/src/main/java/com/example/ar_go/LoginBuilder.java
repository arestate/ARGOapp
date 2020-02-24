package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class LoginBuilder extends AppCompatActivity implements DataInterface {

    EditText edtEmail;
    EditText edtPassword;
    Button btnLogin;
    TextView txtForgotPassword;
    TextView txtSignup2;

    Webservice_Volley volley;

    AllSharedPrefernces allSharedPrefernces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_builder);
        edtEmail =(EditText)findViewById(R.id.edtEmail);
        edtPassword =(EditText)findViewById(R.id.edtPass);
        btnLogin =(Button) findViewById(R.id.btnLogin);
        txtForgotPassword=(TextView)findViewById(R.id.txtForgotPassword);
        txtSignup2= (TextView)findViewById(R.id.btnSignUp);

        volley = new Webservice_Volley(this,this);
        allSharedPrefernces = new AllSharedPrefernces(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!CommonFunctions.checkemail(edtEmail.getText().toString())) {
                    edtEmail.setError("Enter your Valid  Email");
                    edtEmail.requestFocus();
                    return;
                }

                if (!CommonFunctions.checkpassword(edtPassword.getText().toString())) {
                    edtPassword.setError("Enter password atleast 6 char long");
                    edtPassword.requestFocus();
                    return;
                }

                HashMap<String,String> params = new HashMap<>();
                params.put("b_email",edtEmail.getText().toString());
                params.put("b_password",edtPassword.getText().toString());

                String url = Constants.Webserive_Url + "builder_login.php";

                volley.CallVolley(url,params,"builder_login");


            }
        });

    }

    public void ClickonSignUp(View view) {
        Intent i= new Intent(LoginBuilder.this,SignUpBuilder.class);
        startActivity(i);
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
            if (jsonObject.getString("response").equalsIgnoreCase("1")){

                allSharedPrefernces.setUserLogin(true);
                allSharedPrefernces.setCustomerNo(jsonObject.getString("id"));
                allSharedPrefernces.setCustomerData(jsonObject.getJSONObject("data").toString());

                Intent i = new Intent(LoginBuilder.this,BuilderHomepage.class);
                startActivity(i);

                finishAffinity();
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void ClickonFP(View view) {

        Intent i = new Intent(LoginBuilder.this, ForgotPassword.class);
        i.putExtra("type","builder");
        startActivity(i);
    }
}
