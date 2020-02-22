package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ar_go.utils.CommonFunctions;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements DataInterface {

    EditText edtName,edtEmail,edtContactNo,edtAddress,edtPassword;
    Button btnSignUp;
    CheckBox chkAgree;

    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_user);

        edtName = (EditText)findViewById(R.id.edtName);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtContactNo = (EditText)findViewById(R.id.edtContactNo);
        edtAddress = (EditText)findViewById(R.id.edtAddress);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        chkAgree = (CheckBox) findViewById(R.id.chkAgree);

        btnSignUp=(Button)findViewById(R.id.btnSignUp);

        volley = new Webservice_Volley(this,this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!CommonFunctions.checkstring(edtName.getText().toString()))
                {
                    edtName.setError("Enter your name");
                    return;
                }

                if (!CommonFunctions.checkemail(edtEmail.getText().toString()))
                {
                    edtEmail.setError("Enter your Email");
                    return;
                }

                if (!CommonFunctions.checkmobilenumber(edtContactNo.getText().toString()))
                {
                    edtContactNo.setError("Enter your Mobile Number");
                    return;
                }

                if (!CommonFunctions.checkstring(edtAddress.getText().toString()))
                {
                    edtAddress.setError("Enter your Address");
                    return;
                }

                if (!CommonFunctions.checkpassword(edtPassword.getText().toString()))
                {
                    edtPassword.setError("Enter your Password");
                    return;
                }

                if (!chkAgree.isChecked()) {
                    Toast.makeText(MainActivity.this, "Please agree terms & conditions", Toast.LENGTH_LONG).show();
                    return;
                }


                HashMap<String, String> params = new HashMap<>();
                params.put("u_name", edtName.getText().toString());
                params.put("u_email", edtEmail.getText().toString());
                params.put("u_contactno", edtContactNo.getText().toString());
                params.put("u_address", edtAddress.getText().toString());
                params.put("u_password", edtPassword.getText().toString());

                String url = Constants.Webserive_Url + "user_registration.php";
                volley.CallVolley(url, params, "user_registration");
            }
        });
    }




    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {
            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
