package com.example.ar_go.Adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ar_go.Models.PropertyResultVo;
import com.example.ar_go.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class BuilderPropertyListAdapter extends RecyclerView.Adapter<BuilderPropertyListAdapter.ViewHolder>{
    private List<PropertyResultVo> listdata;




   // RecyclerView recyclerView;
    public BuilderPropertyListAdapter(List<PropertyResultVo> listdata) {
        this.listdata = listdata;  
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.builder_property_list_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }


    @Override  
    public void onBindViewHolder(ViewHolder holder, int position) {  

        PropertyResultVo propertyResultVo = listdata.get(position);

        holder.tvaddress.setText(propertyResultVo.getPAddress());
        holder.tvdimensions.setText(propertyResultVo.getPDimensions());
        holder.aptname.setText(propertyResultVo.getPName());

        if(!TextUtils.isEmpty(propertyResultVo.getPExternalPhoto())){
            Picasso.get().load(propertyResultVo.getPExternalPhoto()).resize(200,200).into(holder.imgProperty);
        }
    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {  

        TextView aptname,tvaddress,tvdimensions;
        ImageView imgProperty;


        public ViewHolder(View itemView) {  
            super(itemView);

            imgProperty = (ImageView)itemView.findViewById(R.id.imgProperty);

            aptname=(TextView)itemView.findViewById(R.id.Aptname);
            tvaddress = (TextView)itemView.findViewById(R.id.tvaddress);
            tvdimensions = (TextView)itemView.findViewById(R.id.tvdimensions);
        }  
    }  
}  
