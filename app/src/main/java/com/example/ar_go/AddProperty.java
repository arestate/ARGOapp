package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ar_go.utils.CommonFunctions;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class AddProperty extends AppCompatActivity implements DataInterface {

    Webservice_Volley volley;


    EditText edt_num1,edt_num2,edt_num3,edt_num4,edt_num5,edt_num6;
    Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        volley = new Webservice_Volley(this, this);






        edt_num1 = (EditText) findViewById(R.id.edt_num1);
        edt_num2 = (EditText) findViewById(R.id.edt_num2);
        edt_num3 = (EditText) findViewById(R.id.edt_num3);
        edt_num4 = (EditText) findViewById(R.id.edt_num4);
        edt_num5 = (EditText) findViewById(R.id.edt_num5);
        edt_num6 = (EditText) findViewById(R.id.edt_num6);

        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!CommonFunctions.checkstring(edt_num1.getText().toString())) {
                    edt_num1.setError("Enter your name");
                    edt_num1.requestFocus();
                    return;
                }

                if (!CommonFunctions.checkstring(edt_num2.getText().toString())) {
                    edt_num2.setError("Enter your address");
                    edt_num2.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkstring(edt_num3.getText().toString())) {
                    edt_num3.setError("Enter your Dimension");
                    edt_num3.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkstring(edt_num4.getText().toString())) {
                    edt_num4.setError("Enter your type");
                    edt_num4.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkstring(edt_num5.getText().toString())) {
                    edt_num5.setError("Enter your category");
                    edt_num5.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkstring(edt_num6.getText().toString())) {
                    edt_num6.setError("Enter your plan file");
                    edt_num6.requestFocus();
                    return;
                }


                HashMap<String, String> params = new HashMap<>();

                params.put("p_name", edt_num1.getText().toString());
                params.put("b_id", "2");
                params.put("p_address", edt_num2.getText().toString());
                params.put("p_dimensions", edt_num3.getText().toString());
                params.put("p_type",edt_num4.getText().toString());
                params.put("p_category",edt_num5.getText().toString());
                params.put("p_plan_file",edt_num6.getText().toString());
                params.put("p_external_photo", "");
                params.put("p_internal_photo", "");
                params.put("p_latitude", "73.15");
                params.put("p_longitude", "23.31");


                String url = Constants.Webserive_Url + "add_property.php";

                volley.CallVolley(url, params, "add_property");

            }
        });






    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

            if (jsonObject.getString("response").equalsIgnoreCase("1")) {
                finish();
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
