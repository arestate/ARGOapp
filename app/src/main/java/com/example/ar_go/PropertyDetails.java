package com.example.ar_go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ar_go.Models.PropertyResultVo;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class PropertyDetails extends AppCompatActivity {
    ImageView imageView,img_amn1;
    TextView tvpname,tvaddress,tvamn1,tvdescription;

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

        propertyResultVo= new Gson().fromJson(getIntent().getStringExtra("data"),PropertyResultVo.class);

        if (propertyResultVo != null) {

            tvpname.setText(propertyResultVo.getPName());
            tvaddress.setText(propertyResultVo.getPAddress());
            tvdescription.setText(propertyResultVo.getPDetails());

            if(!TextUtils.isEmpty(propertyResultVo.getPExternalPhoto())){
                Picasso.get().load(propertyResultVo.getPExternalPhoto()).resize(200,200).into(imageView);
            }



        }

    }
}
