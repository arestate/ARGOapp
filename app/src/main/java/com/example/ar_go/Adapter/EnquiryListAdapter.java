package com.example.ar_go.Adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ar_go.Models.EnquiryResultVo;
import com.example.ar_go.Models.PropertyResultVo;
import com.example.ar_go.R;

import java.util.List;


public class EnquiryListAdapter extends RecyclerView.Adapter<EnquiryListAdapter.ViewHolder>{
    private List<EnquiryResultVo> listdata;

    OnItemClickListner mListener;

   // RecyclerView recyclerView;
    public EnquiryListAdapter(List<EnquiryResultVo> listdata,OnItemClickListner listner) {
        this.listdata = listdata;
        this.mListener = listner;
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.layout_enquiry_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }


    @Override  
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final EnquiryResultVo enquiryResultVo = listdata.get(position);

        holder.txt_details.setText(enquiryResultVo.getEDetails());
        holder.txt_date.setText(enquiryResultVo.getEDate());
        holder.txt_name.setText(enquiryResultVo.getPName());
        holder.txt_ereply.setText("Re: " + enquiryResultVo.geteReply());

        if (TextUtils.isEmpty(enquiryResultVo.geteReply())) {
            holder.ll_reply.setVisibility(View.VISIBLE);
        }
        else {
            holder.ll_reply.setVisibility(View.GONE);
        }

        holder.txt_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mListener != null)
                    mListener.onItemListener(position,enquiryResultVo);

            }
        });


    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {  

        TextView txt_name,txt_details,txt_date,txt_reply,txt_ereply;
        LinearLayout ll_reply;


        public ViewHolder(View itemView) {  
            super(itemView);  

            txt_name=(TextView)itemView.findViewById(R.id.p_name);
            txt_details = (TextView)itemView.findViewById(R.id.p_details);
            txt_date= (TextView)itemView.findViewById(R.id.p_date);
            ll_reply= (LinearLayout) itemView.findViewById(R.id.ll_reply);
            txt_reply= (TextView)itemView.findViewById(R.id.txt_reply);
            txt_ereply= (TextView)itemView.findViewById(R.id.txt_ereply);
        }
    }

    public interface OnItemClickListner {
        public void onItemListener(int pos,EnquiryResultVo enquiryResultVo);
    }
}  
