package com.example.ar_go.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ar_go.Models.EnquiryResultVo;
import com.example.ar_go.Models.PropertyResultVo;
import com.example.ar_go.R;

import java.util.List;


public class EnquiryListAdapter extends RecyclerView.Adapter<EnquiryListAdapter.ViewHolder>{
    private List<EnquiryResultVo> listdata;




   // RecyclerView recyclerView;
    public EnquiryListAdapter(List<EnquiryResultVo> listdata) {
        this.listdata = listdata;  
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.layout_enquiry_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }


    @Override  
    public void onBindViewHolder(ViewHolder holder, int position) {

        EnquiryResultVo enquiryResultVo = listdata.get(position);

        holder.txt_details.setText(enquiryResultVo.getEDetails());
        holder.txt_date.setText(enquiryResultVo.getEDate());



    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {  

        TextView txt_name,txt_details,txt_date;


        public ViewHolder(View itemView) {  
            super(itemView);  

            txt_name=(TextView)itemView.findViewById(R.id.p_name);
            txt_details = (TextView)itemView.findViewById(R.id.p_details);
            txt_date= (TextView)itemView.findViewById(R.id.p_date);
        }  
    }  
}  
