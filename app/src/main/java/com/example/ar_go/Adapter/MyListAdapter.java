package com.example.ar_go.Adapter;

import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.ImageView;  
import android.widget.RelativeLayout;  
import android.widget.TextView;  
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ar_go.Models.PropertyResultVo;
import com.example.ar_go.R;

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
    public void onBindViewHolder(ViewHolder holder, int position) {  

    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {  

        TextView aptname,tvname,tvdimensions;


        public ViewHolder(View itemView) {  
            super(itemView);  

            aptname=(TextView)itemView.findViewById(R.id.Aptname);
            tvname = (TextView)itemView.findViewById(R.id.tvname);
            tvdimensions = (TextView)itemView.findViewById(R.id.tvdimensions);
        }  
    }  
}  