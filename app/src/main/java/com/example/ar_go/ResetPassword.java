package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ar_go.utils.CommonFunctions;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;

import java.util.HashMap;

import java.util.HashMap;

import org.json.JSONObject;


public class ResetPassword extends AppCompatActivity implements DataInterface {

    EditText edtNewPass,edtConfirm;
    Button btnReset;

    Webservice_Volley volley;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        userid = getIntent().getStringExtra("id");

        edtNewPass = (EditText) findViewById(R.id.edtNewPass);
        edtConfirm = (EditText) findViewById(R.id.edtConfirm);
        btnReset = (Button) findViewById(R.id.btnReset);

        volley = new Webservice_Volley(this, this);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!CommonFunctions.checkpassword(edtNewPass.getText().toString()))
                {
                    edtNewPass.setError("Enter your Password");
                    return;
                }

                if (!CommonFunctions.checkpassword(edtConfirm.getText().toString()))
                {
                    edtConfirm.setError("Enter your Password");
                    return;
                }



                String url = Constants.Webserive_Url + "user_resetpassword.php";

                HashMap<String, String> params = new HashMap<>();
                params.put("u_id", userid);
                params.put("u_password", edtNewPass.getText().toString());

                volley.CallVolley(url, params, "user_resetpassword");



            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {
            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

            if (jsonObject.getString("response").equalsIgnoreCase("1")) {

                Intent i = new Intent(ResetPassword.this, Login.class);
                startActivity(i);

                finishAffinity();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
