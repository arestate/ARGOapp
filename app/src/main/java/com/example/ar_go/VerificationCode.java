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


public class VerificationCode extends AppCompatActivity {

    EditText edt_num1,edt_num2,edt_num3,edt_num4;
    Button btnVerify;

    String id,otp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);

        id = getIntent().getStringExtra("id");
        otp = getIntent().getStringExtra("otp");

        edt_num1 = (EditText) findViewById(R.id.edt_num1);
        edt_num2 = (EditText) findViewById(R.id.edt_num2);
        edt_num3 = (EditText) findViewById(R.id.edt_num3);
        edt_num4 = (EditText) findViewById(R.id.edt_num4);
        btnVerify = (Button) findViewById(R.id.btnVerify);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code = edt_num1.getText().toString() + edt_num2.getText().toString() + edt_num3.getText().toString() + edt_num4.getText().toString();
                if (!CommonFunctions.checkcode(code))
                {
                    Toast.makeText(VerificationCode.this, "Enter 4 digit Verification Code", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (otp.equals(code)) {

                    Intent i = new Intent(VerificationCode.this,ResetPassword.class);
                    i.putExtra("id",id);
                    i.putExtra("type",getIntent().getStringExtra("type"));
                    startActivity(i);

                }
                else {
                    Toast.makeText(VerificationCode.this, "Invalid verification code.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}
