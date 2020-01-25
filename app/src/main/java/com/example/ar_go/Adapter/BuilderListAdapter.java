package com.example.ar_go.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ar_go.ApiModels.BuilderResultVo;
import com.example.ar_go.Models.PropertyResultVo;
import com.example.ar_go.R;

import java.util.List;


public class BuilderListAdapter extends RecyclerView.Adapter<BuilderListAdapter.ViewHolder>{
    private List<BuilderResultVo> listdata;




   // RecyclerView recyclerView;
    public BuilderListAdapter(List<BuilderResultVo> listdata) {
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
    public void onBindViewHolder(ViewHolder holder, int position) {

        BuilderResultVo builderResultVo = listdata.get(position);


        holder.txt_buildername.setText(builderResultVo.getBName());


    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {  

        TextView txt_buildername;
        ImageView img_builder;


        public ViewHolder(View itemView) {  
            super(itemView);  

            img_builder=(ImageView)itemView.findViewById(R.id.img_builder);
            txt_buildername = (TextView)itemView.findViewById(R.id.txt_buildername);

        }  
    }  
}  
