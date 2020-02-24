package com.example.ar_go.Adapter;

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
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;



public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private List<PropertyResultVo> listdata;




   // RecyclerView recyclerView;  
    public MyListAdapter(List<PropertyResultVo> listdata) {
        this.listdata = listdata;  
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
        ImageView imgproperty;


        public ViewHolder(View itemView) {  
            super(itemView);  

            aptname=(TextView)itemView.findViewById(R.id.Aptname);
            tvaddress = (TextView)itemView.findViewById(R.id.tvaddress);
            tvdimensions = (TextView)itemView.findViewById(R.id.tvdimensions);
            imgproperty=itemView.findViewById(R.id.imgproperty);

        }  
    }  
}  
