package com.example.ar_go.ui.enquiry;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ar_go.Adapter.EnquiryListAdapter;
import com.example.ar_go.Adapter.EnquiryListAdapter1;
import com.example.ar_go.Models.EnquiryInfoVo;
import com.example.ar_go.Models.EnquiryResultVo;
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
public class UserEnquiryListFragment extends Fragment implements DataInterface, EnquiryListAdapter1.OnItemClickListner {

    public UserEnquiryListFragment() {
        // Required empty public constructor
    }

    Webservice_Volley volley;
    RecyclerView recvEnquiry;
    AllSharedPrefernces allSharedPrefernces;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_user_enquiry_list, container, false);

        recvEnquiry = (RecyclerView) root.findViewById(R.id.recycle_enquiry);
        recvEnquiry.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        volley = new Webservice_Volley( getActivity() , this);
        allSharedPrefernces=new AllSharedPrefernces(getActivity());

        HashMap<String, String> params = new HashMap<>();
        params.put("u_id",allSharedPrefernces.getCustomerNo());

        String url = Constants.Webserive_Url + "get_enquiry_based_on_users.php";
        volley.CallVolley(url, params, "get_enquiry_based_on_users");


        return root;
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {

            if (tag.equalsIgnoreCase("get_enquiry_based_on_users")) {

                EnquiryInfoVo enquiryInfoVo = new Gson().fromJson(jsonObject.toString(), EnquiryInfoVo.class);

                if (enquiryInfoVo != null) {

                    if (enquiryInfoVo.getResult() != null) {

                        if (enquiryInfoVo.getResult().size() > 0) {

                            EnquiryListAdapter1 adapter = new EnquiryListAdapter1(enquiryInfoVo.getResult(),this);
                            recvEnquiry.setAdapter(adapter);
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

    @Override
    public void onItemListener(int pos, EnquiryResultVo enquiryResultVo) {

    }
}
