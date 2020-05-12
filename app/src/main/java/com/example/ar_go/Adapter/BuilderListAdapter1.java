package com.example.ar_go.Adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ar_go.ApiModels.BuilderResultVo;
import com.example.ar_go.PropertyListActivity;
import com.example.ar_go.R;
import com.example.ar_go.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;


public class BuilderListAdapter1 extends RecyclerView.Adapter<BuilderListAdapter1.ViewHolder>{
    private List<BuilderResultVo> listdata;


   // RecyclerView recyclerView;
    public BuilderListAdapter1(List<BuilderResultVo> listdata) {
        this.listdata = listdata;  
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.layout_builder_search_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }


    @Override  
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final BuilderResultVo builderResultVo = listdata.get(position);


        holder.txt_buildername.setText(builderResultVo.getBName());
        if (!TextUtils.isEmpty(builderResultVo.getBLogo())) {
            Picasso.get().load(Constants.Webserive_Url + builderResultVo.getBLogo()).into(holder.img_builder);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.itemView.getContext(), PropertyListActivity.class);
                i.putExtra("b_id",builderResultVo.getBId());
                i.putExtra("b_name",builderResultVo.getBName());
                holder.itemView.getContext().startActivity(i);
            }
        });

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
