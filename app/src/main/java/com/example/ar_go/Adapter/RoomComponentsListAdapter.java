package com.example.ar_go.Adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ar_go.ApiModels.BuilderResultVo;
import com.example.ar_go.Models.RoomComponentResultVo;
import com.example.ar_go.Models.RoomComponentsInfoVo;
import com.example.ar_go.R;
import com.example.ar_go.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RoomComponentsListAdapter extends RecyclerView.Adapter<RoomComponentsListAdapter.ViewHolder>{
    private List<RoomComponentResultVo> listdata;

    OnItemClickListner mListner;




   // RecyclerView recyclerView;
    public RoomComponentsListAdapter(List<RoomComponentResultVo> listdata,OnItemClickListner listner) {
        this.listdata = listdata;
        this.mListner = listner;
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.layout_builder_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }


    @Override  
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final RoomComponentResultVo roomComponentResultVo = listdata.get(position);

        if (!TextUtils.isEmpty(roomComponentResultVo.getRImage())) {
            Picasso.get().load(Constants.IMAGE_Url + roomComponentResultVo.getRImage()).into(holder.img_builder);
        }

        holder.txt_buildername.setText(roomComponentResultVo.getRRoom());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mListner != null) {
                    mListner.onItemListener(position,roomComponentResultVo);
                }

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

    public interface OnItemClickListner {

        public void onItemListener(int pos,RoomComponentResultVo roomComponentResultVo);

    }
}  
