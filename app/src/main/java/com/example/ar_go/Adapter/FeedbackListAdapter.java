package com.example.ar_go.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ar_go.Models.EnquiryResultVo;
import com.example.ar_go.Models.FeedbackResultVo;
import com.example.ar_go.R;

import java.util.List;


public class FeedbackListAdapter extends RecyclerView.Adapter<FeedbackListAdapter.ViewHolder>{
    private List<FeedbackResultVo> listdata;




   // RecyclerView recyclerView;
    public FeedbackListAdapter(List<FeedbackResultVo> listdata) {
        this.listdata = listdata;  
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.layout_feedback_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }


    @Override  
    public void onBindViewHolder(ViewHolder holder, int position) {

        FeedbackResultVo feedbackResultVo = listdata.get(position);

        holder.txt_details.setText(feedbackResultVo.getFDetails());
        holder.ratingBar.setRating(Float.parseFloat(feedbackResultVo.getFRatings()));
        holder.txt_name.setText(feedbackResultVo.getPId());



    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {  

        TextView txt_name,txt_details;
        RatingBar ratingBar;


        public ViewHolder(View itemView) {  
            super(itemView);  

            txt_name=(TextView)itemView.findViewById(R.id.pname);
            txt_details = (TextView)itemView.findViewById(R.id.fdetails);
            ratingBar= (RatingBar)itemView.findViewById(R.id.ratings);
        }  
    }  
}  
