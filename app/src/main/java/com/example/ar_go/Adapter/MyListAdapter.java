package com.example.ar_go.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.ImageView;  
import android.widget.RelativeLayout;  
import android.widget.TextView;  
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ar_go.Models.PropertyResultVo;
import com.example.ar_go.PropertyDetails;
import com.example.ar_go.R;
import com.example.ar_go.utils.AllSharedPrefernces;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private List<PropertyResultVo> listdata;

    AllSharedPrefernces allSharedPrefernces;



   // RecyclerView recyclerView;  
    public MyListAdapter(Context context ,List<PropertyResultVo> listdata) {
        this.listdata = listdata;

        allSharedPrefernces = new AllSharedPrefernces(context);
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.layout_property_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }


    @Override  
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final PropertyResultVo propertyResultVo = listdata.get(position);

        holder.tvaddress.setText(propertyResultVo.getPAddress());
        holder.tvdimensions.setText(propertyResultVo.getPDimensions());
        holder.aptname.setText(propertyResultVo.getPName());

        if (CheckProductInCart(propertyResultVo)) {
            holder.imgfavorites.setImageResource(R.drawable.favorites_selected);
            holder.imgfavorites.setTag("in_cart");
        }
        else {
            holder.imgfavorites.setImageResource(R.drawable.favorites);
            holder.imgfavorites.setTag("not_in_cart");
        }

        holder.imgfavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.imgfavorites.getTag().toString().equalsIgnoreCase("in_cart")) {

                    RemoveProductInCart(propertyResultVo);

                }
                else {
                    addProductToCart(propertyResultVo);
                    notifyDataSetChanged();
                }

            }
        });



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(holder.itemView.getContext(), PropertyDetails.class);
                i.putExtra("data",new Gson().toJson(propertyResultVo));
                holder.itemView.getContext().startActivity(i);

            }
        });

        if(!TextUtils.isEmpty(propertyResultVo.getPExternalPhoto())){
            Picasso.get().load(propertyResultVo.getPExternalPhoto()).resize(200,200).into(holder.imgproperty);
        }

    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {  

        TextView aptname,tvaddress,tvdimensions;
        ImageView imgproperty,imgfavorites;


        public ViewHolder(View itemView) {  
            super(itemView);  

            aptname=(TextView)itemView.findViewById(R.id.Aptname);
            tvaddress = (TextView)itemView.findViewById(R.id.tvaddress);
            tvdimensions = (TextView)itemView.findViewById(R.id.tvdimensions);
            imgproperty=itemView.findViewById(R.id.imgproperty);
            imgfavorites=itemView.findViewById(R.id.imgfavorite);
        }  
    }

    public boolean CheckProductInCart(PropertyResultVo propertyResultVo) {

        try {

            boolean isExist = false;

            Type listType = new TypeToken<List<PropertyResultVo>>(){}.getType();
            List<PropertyResultVo> propertyResultVoList = new Gson().fromJson(allSharedPrefernces.getCartList(), listType);

            if(propertyResultVoList != null) {
                if(propertyResultVoList.size() > 0) {
                    for (int i =0;i<propertyResultVoList.size();i++) {
                        if(propertyResultVo.getPId().equalsIgnoreCase(propertyResultVoList.get(i).getPId())) {

                            isExist = true;
                            break;
                        }
                    }
                }
            }
            return isExist;
        }
        catch (Exception e) {
            e.printStackTrace();

            return false;
        }

    }


    public boolean RemoveProductInCart(PropertyResultVo propertyResultVo) {

        try {

            boolean isExist = false;

            Type listType = new TypeToken<List<PropertyResultVo>>(){}.getType();
            List<PropertyResultVo> propertyResultVoList = new Gson().fromJson(allSharedPrefernces.getCartList(), listType);

            if(propertyResultVoList != null) {
                if(propertyResultVoList.size() > 0) {
                    for (int i =0;i<propertyResultVoList.size();i++) {
                        if(propertyResultVo.getPId().equalsIgnoreCase(propertyResultVoList.get(i).getPId())) {

                            isExist = true;

                            propertyResultVoList.remove(i);
                            allSharedPrefernces.setCartList(new Gson().toJson(propertyResultVoList));

                            notifyDataSetChanged();

                            break;
                        }
                    }
                }
            }

            return isExist;
        }
        catch (Exception e) {
            e.printStackTrace();

            return false;
        }

    }

    public void addProductToCart(PropertyResultVo propertyResultVos) {

        try {

            Type listType = new TypeToken<List<PropertyResultVo>>(){}.getType();
            List<PropertyResultVo> propertyResultVoList = new Gson().fromJson(allSharedPrefernces.getCartList(), listType);

            if(propertyResultVoList == null)
                propertyResultVoList = new ArrayList<>();

            propertyResultVoList.add(propertyResultVos);

            allSharedPrefernces.setCartList(new Gson().toJson(propertyResultVoList));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


}  
