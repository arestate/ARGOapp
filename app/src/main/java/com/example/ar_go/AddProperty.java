package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ar_go.utils.AllSharedPrefernces;
import com.example.ar_go.utils.CommonFunctions;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AddProperty extends AppCompatActivity implements DataInterface {

    Webservice_Volley volley;
    AllSharedPrefernces allSharedPrefernces;

    EditText edtname,edtaddress,edtdimension,edtcategory,edtplanfile,edtdetails;
    Button btnAdd;
    Spinner sptype;

    ArrayList<String> propertytypelist = new ArrayList<String>();
    ArrayList<String> residentialroomcomponentlist = new ArrayList<String>();
    ArrayList<String> commercialroomcomponentlist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

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

        btnAdd = (Button) findViewById(R.id.btnAdd);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerroomdata);


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


                HashMap<String, String> params = new HashMap<>();

                params.put("p_name", edtname.getText().toString());
                params.put("b_id",allSharedPrefernces.getCustomerNo());
                params.put("p_address", edtaddress.getText().toString());
                params.put("p_dimensions", edtdimension.getText().toString());
                params.put("p_type",sptype.getSelectedItem().toString());
                params.put("p_category",edtcategory.getText().toString());
                params.put("p_plan_file",edtplanfile.getText().toString());
                params.put("p_details",edtdetails.getText().toString());
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

    public void showaddroomdialog()
    {
        Dialog d = new Dialog(this);
        d.setContentView(R.layout.dialog_addpropertyroom);

        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(d.getWindow().getAttributes());
        lp.width = width;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        d.show();
        d.getWindow().setAttributes(lp);

        Spinner sproom = (Spinner) d.findViewById(R.id.sproom);
        ImageView img_room = (ImageView) d.findViewById(R.id.img_room);

        if (sptype.getSelectedItem().toString().equalsIgnoreCase("Residential")){

            final ArrayAdapter<String> adapter = new ArrayAdapter<>(AddProperty.this,android.R.layout.simple_spinner_dropdown_item,residentialroomcomponentlist);
            sproom.setAdapter(adapter);

        }

        else {

            final ArrayAdapter<String> adapter = new ArrayAdapter<>(AddProperty.this,android.R.layout.simple_spinner_dropdown_item,commercialroomcomponentlist);
            sproom.setAdapter(adapter);

        }

        d.show();
    }


    public void ClickonSelectImages(View view) {

        showaddroomdialog();
    }
}
