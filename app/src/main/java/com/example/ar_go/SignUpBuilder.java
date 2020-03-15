package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ar_go.utils.CommonFunctions;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.TextHttpResponseHandler;
import com.mvc.imagepicker.ImagePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class SignUpBuilder extends AppCompatActivity implements DataInterface {

    EditText edtName,edtEmail,edtLicense,edtContactNo,edtAddress,edtPassword,edtCnfPassword,edtWebsite;
    CheckBox chkAgree;
    Button btnSignUp;




    Webservice_Volley volley;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_builder);
        edtName = (EditText) findViewById(R.id.edtName);
        edtLicense = (EditText)findViewById(R.id.edtLicenseNo);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtContactNo = (EditText) findViewById(R.id.edtContactNo);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtCnfPassword = (EditText) findViewById(R.id.edtConfirm);
        edtWebsite = (EditText) findViewById(R.id.edtWebsite);
        chkAgree = (CheckBox) findViewById(R.id.chkAgree);
        img_builder=findViewById(R.id.img_builder);


        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        volley = new Webservice_Volley(this, this);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!CommonFunctions.checkstring(edtLicense.getText().toString())) {
                    edtName.setError("Enter your license number");
                    edtName.requestFocus();
                    return;
                }

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
                    params.put("b_licenseno",edtLicense.getText().toString());
                    params.put("b_website", edtWebsite.getText().toString());
                    params.put("b_logo", image_path);
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
    String image_path = "";
    ImageView img_builder;


    public void ClickonSelectImages(View view) {
        ImagePicker.pickImage(SignUpBuilder.this, "Select your image:");
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String img_path = ImagePicker.getImagePathFromResult(this, requestCode, resultCode, data);
        // TODO do something with the bitmap

        File f = new File(img_path);

        Uri uri = Uri.fromFile(f);

        img_builder.setImageURI(uri);

        try {
            uploadPhoto(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    private void uploadPhoto(File myFile) throws FileNotFoundException {


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        if (myFile != null) {
            Log.v("File ", "with compress===>");
            params.put("uploaded_file", myFile);
        } else {
            Log.v("File ", "without compress===>");
            params.put("uploaded_file", myFile);
        }
        params.put("image_name", myFile.getName());

        ResponseHandlerInterface handler = new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.v("image uplod onFailure", "onFailure===> " + responseString);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Toast.makeText(SignUpBuilder.this, responseString, Toast.LENGTH_SHORT).show();
                //  listOfImages.get(position).setImageName();
                //  {"status":"error","status_error":"Invalid request !!"}
                try {
                    JSONObject json = new JSONObject(responseString);
                    if (json.getBoolean("status")) {

                        image_path = json.getString("file_name");



                    } else {
                        // list_image.get(position).setImageName(json.get("uploded_file_name").toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        };

        client.post(Constants.Webserive_Url+"file_upload.php", params, handler);

    } }