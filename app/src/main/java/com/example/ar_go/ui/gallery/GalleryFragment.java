package com.example.ar_go.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ar_go.Adapter.EnquiryListAdapter;
import com.example.ar_go.Adapter.FeedbackListAdapter;
import com.example.ar_go.Feedback;
import com.example.ar_go.Models.EnquiryInfoVo;
import com.example.ar_go.Models.FeedbackInfoVo;
import com.example.ar_go.R;
import com.example.ar_go.utils.AllSharedPrefernces;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class GalleryFragment extends Fragment implements DataInterface {

    private GalleryViewModel galleryViewModel;
    Webservice_Volley volley;
    RecyclerView recvFeedback;
    AllSharedPrefernces allSharedPrefernces;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        recvFeedback = (RecyclerView) root.findViewById(R.id.recycle_feedback);
        recvFeedback.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        recvFeedback.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        volley = new Webservice_Volley( getActivity() , this);
        HashMap<String, String> params = new HashMap<>();
        allSharedPrefernces=new AllSharedPrefernces(getActivity());
        params.put("b_id",allSharedPrefernces.getCustomerNo());

        String url = Constants.Webserive_Url + "get_feedback_based_on_builders.php";
        volley.CallVolley(url, params, "get_feedback_based_on_builders");




        return root;

    }
    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {

            if (tag.equalsIgnoreCase("get_feedback_based_on_builders")) {

                FeedbackInfoVo feedbackInfoVo= new Gson().fromJson(jsonObject.toString(), FeedbackInfoVo.class);

                if (feedbackInfoVo != null) {

                    if (feedbackInfoVo.getResult() != null) {

                        if (feedbackInfoVo.getResult().size() > 0) {

                            FeedbackListAdapter adapter = new FeedbackListAdapter(feedbackInfoVo.getResult());
                            recvFeedback.setAdapter(adapter);
                        }

                    }

                }

            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }
}
