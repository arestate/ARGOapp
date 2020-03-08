package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ar_go.Adapter.MyListAdapter;
import com.example.ar_go.Models.PropertyAreaInfoVo;
import com.example.ar_go.Models.PropertyResultVo;
import com.example.ar_go.Models.PropertyinfoVo;
import com.example.ar_go.Models.RoomComponentsInfoVo;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;

public class PropertyDetails extends AppCompatActivity implements DataInterface {
    ImageView imageView,img_amn1;
    TextView tvpname,tvaddress,tvamn1,tvdescription;
    LinearLayout ll_amenities;
    Webservice_Volley volley;

    PropertyResultVo propertyResultVo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);

        imageView=(ImageView) findViewById(R.id.imageView);
        tvpname=(TextView) findViewById(R.id.tvpname);
        tvaddress=(TextView) findViewById(R.id.tvaddress);
        img_amn1=(ImageView) findViewById(R.id.img_amn1);
        tvamn1=(TextView) findViewById(R.id.tvamn1);
        tvdescription=(TextView) findViewById(R.id.tvdescription);
        ll_amenities = (LinearLayout)findViewById(R.id.ll_amenities);
        volley = new Webservice_Volley(this, this);

        propertyResultVo= new Gson().fromJson(getIntent().getStringExtra("data"),PropertyResultVo.class);

        if (propertyResultVo != null) {

            tvpname.setText(propertyResultVo.getPName());
            tvaddress.setText(propertyResultVo.getPAddress());
            tvdescription.setText(propertyResultVo.getPDetails());

            if(!TextUtils.isEmpty(propertyResultVo.getPExternalPhoto())){
                Picasso.get().load(propertyResultVo.getPExternalPhoto()).resize(200,200).into(imageView);
            }

            String[] amenities = propertyResultVo.getpAmenities().split(",");
            if (amenities!= null)
            {
                if (amenities.length > 0)
                {

                    for (int i = 0; i < amenities.length; i++) {

                        String s = amenities[i];



                        View vs = LayoutInflater.from(this).inflate(R.layout.layout_amenities, null);

                        TextView tvamn1 = (TextView) vs.findViewById(R.id.tvamn1);

                        tvamn1.setText(s);

                        ll_amenities.addView(vs);


                    }

                }
            }

        }

    }

    public void ClickonCustomize(View view) {

        String url = Constants.Webserive_Url + "get_roomcomponent.php";

        HashMap<String,String> params = new HashMap<>();
        params.put("r_type", propertyResultVo.getPType());

        volley.CallVolley(url, params, "get_roomcomponent");

    }

    RoomComponentsInfoVo propertyinfoVo;


    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            if (tag.equalsIgnoreCase("get_roomcomponent")){

                propertyinfoVo = new Gson().fromJson(jsonObject.toString(),RoomComponentsInfoVo.class);

                if (propertyinfoVo != null) {

                    if (propertyinfoVo.getResult() != null) {

                        if (propertyinfoVo.getResult().size() > 0) {

                            getpropertyarea();



                        }
                        else {
                            Intent i = new Intent(PropertyDetails.this,PropertyPreview.class);
                            i.putExtra("data",new Gson().toJson(propertyinfoVo));
                            startActivity(i);
                        }

                    }

                    }

                }



            else if (tag.equalsIgnoreCase("get_propertyarea")){

                 PropertyAreaInfoVo propertyAreaInfoVo = new Gson().fromJson(jsonObject.toString(),PropertyAreaInfoVo.class);

                if (propertyAreaInfoVo != null) {

                    if (propertyAreaInfoVo.getResult() != null) {

                        if (propertyAreaInfoVo.getResult().size() > 0) {

                            Intent i = new Intent(PropertyDetails.this,PropertyPreview.class);
                            i.putExtra("data",new Gson().toJson(propertyinfoVo));
                            i.putExtra("area_data",new Gson().toJson(propertyAreaInfoVo));
                            startActivity(i);

                        }
                        else {
                            Intent i = new Intent(PropertyDetails.this,PropertyPreview.class);
                            i.putExtra("data",new Gson().toJson(propertyinfoVo));
                            startActivity(i);
                        }

                    }
                    else {
                        Intent i = new Intent(PropertyDetails.this,PropertyPreview.class);
                        i.putExtra("data",new Gson().toJson(propertyinfoVo));
                        startActivity(i);
                    }

                }
                else {
                    Intent i = new Intent(PropertyDetails.this,PropertyPreview.class);
                    i.putExtra("data",new Gson().toJson(propertyinfoVo));
                    startActivity(i);
                }

            }

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void getpropertyarea()
    {
        String url = Constants.Webserive_Url + "get_propertyarea.php";

        HashMap<String,String> params = new HashMap<>();
        params.put("p_id", propertyResultVo.getPId());

        volley.CallVolley(url, params, "get_propertyarea");
    }
}
