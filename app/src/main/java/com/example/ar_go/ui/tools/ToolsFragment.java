package com.example.ar_go.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ar_go.Adapter.BuilderListAdapter;
import com.example.ar_go.Adapter.EnquiryListAdapter;
import com.example.ar_go.Adapter.MyListAdapter;
import com.example.ar_go.ApiModels.BuilderinfoVo;
import com.example.ar_go.Models.EnquiryInfoVo;
import com.example.ar_go.Models.PropertyinfoVo;
import com.example.ar_go.R;
import com.example.ar_go.utils.AllSharedPrefernces;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class ToolsFragment extends Fragment implements DataInterface {

    private ToolsViewModel toolsViewModel;
    Webservice_Volley volley;
    RecyclerView recvEnquiry;
    AllSharedPrefernces allSharedPrefernces;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);



        recvEnquiry = (RecyclerView) root.findViewById(R.id.recycle_enquiry);
        recvEnquiry.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        recvEnquiry.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        volley = new Webservice_Volley( getActivity() , this);
        allSharedPrefernces=new AllSharedPrefernces(getActivity());
        HashMap<String, String> params = new HashMap<>();
        params.put("b_id",allSharedPrefernces.getCustomerNo());

        String url = Constants.Webserive_Url + "get_enquiry_based_on_builders.php";
        volley.CallVolley(url, params, "get_enquiry_based_on_builders");


        return root;
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {

            if (tag.equalsIgnoreCase("get_enquiry_based_on_builders")) {

                EnquiryInfoVo enquiryInfoVo = new Gson().fromJson(jsonObject.toString(), EnquiryInfoVo.class);

                if (enquiryInfoVo != null) {

                    if (enquiryInfoVo.getResult() != null) {

                        if (enquiryInfoVo.getResult().size() > 0) {

                            EnquiryListAdapter adapter = new EnquiryListAdapter(enquiryInfoVo.getResult());
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
}