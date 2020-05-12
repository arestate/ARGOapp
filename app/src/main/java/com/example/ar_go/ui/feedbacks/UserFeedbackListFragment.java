package com.example.ar_go.ui.feedbacks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ar_go.Adapter.FeedbackListAdapter;
import com.example.ar_go.Models.FeedbackInfoVo;
import com.example.ar_go.R;
import com.example.ar_go.utils.AllSharedPrefernces;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFeedbackListFragment extends Fragment implements DataInterface {

    public UserFeedbackListFragment() {
        // Required empty public constructor
    }

    Webservice_Volley volley;
    RecyclerView recvFeedback;
    AllSharedPrefernces allSharedPrefernces;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_user_feedback_list, container, false);

        recvFeedback = (RecyclerView) root.findViewById(R.id.recycle_feedback);
        recvFeedback.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        volley = new Webservice_Volley( getActivity() , this);
        HashMap<String, String> params = new HashMap<>();
        allSharedPrefernces=new AllSharedPrefernces(getActivity());
        params.put("u_id",allSharedPrefernces.getCustomerNo());

        String url = Constants.Webserive_Url + "get_feedback_based_on_users.php";
        volley.CallVolley(url, params, "get_feedback_based_on_users");




        return root;
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            if (tag.equalsIgnoreCase("get_feedback_based_on_users")) {

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
