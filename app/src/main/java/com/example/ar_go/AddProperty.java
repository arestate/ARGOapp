package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ar_go.utils.AllSharedPrefernces;
import com.example.ar_go.utils.CommonFunctions;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.TextHttpResponseHandler;
import com.mvc.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class AddProperty extends AppCompatActivity implements DataInterface {

    Webservice_Volley volley;
    AllSharedPrefernces allSharedPrefernces;
    Button btnselectimage;

    LinearLayout ll_roomData;
    EditText edtname,edtaddress,edtdimension,edtcategory,edtplanfile,edtdetails,edtamenities;
    Button btnAdd;
    Spinner sptype;

    ArrayList<String> propertytypelist = new ArrayList<String>();
    ArrayList<String> residentialroomcomponentlist = new ArrayList<String>();
    ArrayList<String> commercialroomcomponentlist = new ArrayList<String>();

    JSONArray RoomImagesArray = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);
        ImagePicker.setMinQuality(300, 300);


        volley = new Webservice_Volley(this, this);
        allSharedPrefernces=new AllSharedPrefernces(this);

        propertytypelist.add("Select property type");
        propertytypelist.add("Residential");
        propertytypelist.add("Commercial");


        residentialroomcomponentlist.add("Select room type");
        residentialroomcomponentlist.add("Living Room");
        residentialroomcomponentlist.add("Bedroom");
        residentialroomcomponentlist.add("Kitchen");
        residentialroomcomponentlist.add("Dining Room");
        residentialroomcomponentlist.add("Puja Room");


        commercialroomcomponentlist.add("Select room type");
        commercialroomcomponentlist.add("Work Place");
        commercialroomcomponentlist.add("Reception");
        commercialroomcomponentlist.add("Cabin");

        edtname = (EditText) findViewById(R.id.edtname);
        edtaddress = (EditText) findViewById(R.id.edtaddress);
        edtdimension= (EditText) findViewById(R.id.edtdimension);
        sptype = (Spinner) findViewById(R.id.sptype);
        edtcategory = (EditText) findViewById(R.id.edtcategory);
        edtplanfile = (EditText) findViewById(R.id.edtplanfile);
        edtdetails = (EditText) findViewById(R.id.edtdetails);
        edtamenities = (EditText) findViewById(R.id.edtamenities);



        btnAdd = (Button) findViewById(R.id.btnAdd);

        ll_roomData = (LinearLayout) findViewById(R.id.ll_roomData);


        final ArrayAdapter<String> adapter = new ArrayAdapter<>(AddProperty.this,android.R.layout.simple_spinner_dropdown_item,propertytypelist);
        sptype.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!CommonFunctions.checkstring(edtname.getText().toString())) {
                    edtname.setError("Enter your name");
                    edtname.requestFocus();
                    return;
                }

                if (!CommonFunctions.checkstring(edtaddress.getText().toString())) {
                    edtaddress.setError("Enter your address");
                    edtaddress.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkstring(edtdimension.getText().toString())) {
                    edtdimension.setError("Enter your Dimension");
                    edtdimension.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkstring(edtcategory.getText().toString())) {
                    edtcategory.setError("Enter your category");
                    edtcategory.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkstring(edtplanfile.getText().toString())) {
                    edtplanfile.setError("Enter your plan file");
                    edtplanfile.requestFocus();
                    return;
                }

                if (!CommonFunctions.checkstring(edtdetails.getText().toString())) {
                    edtdetails.setError("Enter your property details");
                    edtdetails.requestFocus();
                    return;
                }

                if (!CommonFunctions.checkstring(edtamenities.getText().toString())) {
                    edtamenities.setError("Enter your property amenities");
                    edtamenities.requestFocus();
                    return;
                }


                HashMap<String, String> params = new HashMap<>();

                params.put("p_name", edtname.getText().toString());
                params.put("b_id",allSharedPrefernces.getCustomerNo());
                params.put("p_address", edtaddress.getText().toString());
                params.put("p_dimensions", edtdimension.getText().toString());
                params.put("p_type",sptype.getSelectedItem().toString());
                params.put("p_category",edtcategory.getText().toString());
                params.put("p_plan_file",edtplanfile.getText().toString());
                params.put("p_details",edtdetails.getText().toString());
                params.put("p_amenities",edtamenities.getText().toString());
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

            if (tag.equalsIgnoreCase("add_propertyarea")){

                Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();


            }
            else {

                Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                if (jsonObject.getString("response").equalsIgnoreCase("1")) {

                    String id = jsonObject.getString("id");

                    String url = Constants.Webserive_Url + "add_propertyarea.php";

                    JSONObject params = new JSONObject();
                    params.put("p_id", id);
                    params.put("data", RoomImagesArray);

                    volley.CallVolleyJSON(url, params, "add_propertyarea");


                }
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    ImageView img_room;
    Spinner sproom;
    String image_path = "";
    public void showaddroomdialog()
    {
        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.dialog_addpropertyroom);

        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(d.getWindow().getAttributes());
        lp.width = width;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        d.show();
        d.getWindow().setAttributes(lp);

        sproom = (Spinner) d.findViewById(R.id.sproom);
        img_room = (ImageView) d.findViewById(R.id.img_room);
        btnselectimage = (Button) d.findViewById(R.id.btnselectimage);
        Button btnAdd = (Button) d.findViewById(R.id.btnAdd);

        btnselectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.pickImage(AddProperty.this, "Select your image:");
            }
        });

        if (sptype.getSelectedItem().toString().equalsIgnoreCase("Residential")){

            final ArrayAdapter<String> adapter = new ArrayAdapter<>(AddProperty.this,android.R.layout.simple_spinner_dropdown_item,residentialroomcomponentlist);
            sproom.setAdapter(adapter);

        }

        else {

            final ArrayAdapter<String> adapter = new ArrayAdapter<>(AddProperty.this,android.R.layout.simple_spinner_dropdown_item,commercialroomcomponentlist);
            sproom.setAdapter(adapter);

        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("pa_type", sptype.getSelectedItem().toString());
                    jsonObject.put("pa_roomtype", sproom.getSelectedItem().toString());
                    jsonObject.put("pa_image", image_path);

                    RoomImagesArray.put(jsonObject);

                    image_path = "";

                    addRoomData();

                    d.dismiss();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        d.show();
    }

    public void addRoomData() {

        try {

            if (RoomImagesArray.length() > 0) {

                ll_roomData.removeAllViews();

                for (int i = 0; i < RoomImagesArray.length(); i++) {

                    JSONObject jobj = RoomImagesArray.getJSONObject(i);

                    View vs = LayoutInflater.from(this).inflate(R.layout.layout_room_data, null);

                    ImageView room_image = (ImageView) vs.findViewById(R.id.room_image);
                    TextView room_name = (TextView) vs.findViewById(R.id.room_name);

                    room_name.setText(jobj.getString("pa_roomtype"));

                    Picasso.get().load(Constants.Webserive_Url + jobj.getString("pa_image")).into(room_image);

                    ll_roomData.addView(vs);


                }


            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    public void ClickonSelectImages(View view) {

        showaddroomdialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String img_path = ImagePicker.getImagePathFromResult(this, requestCode, resultCode, data);
        // TODO do something with the bitmap

        File f = new File(img_path);

        Uri uri = Uri.fromFile(f);

        img_room.setImageURI(uri);

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

                        Toast.makeText(AddProperty.this, responseString, Toast.LENGTH_SHORT).show();
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

    }
}
