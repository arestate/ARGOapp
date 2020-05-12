package com.example.ar_go.ui.tools;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import com.example.ar_go.Models.EnquiryResultVo;
import com.example.ar_go.Models.PropertyinfoVo;
import com.example.ar_go.R;
import com.example.ar_go.utils.AllSharedPrefernces;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.google.gson.Gson;
import com.vuforia.VIEW;

import org.json.JSONObject;

import java.util.HashMap;

public class ToolsFragment extends Fragment implements DataInterface, EnquiryListAdapter.OnItemClickListner {

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

        volley = new Webservice_Volley( getActivity() , this);
        allSharedPrefernces=new AllSharedPrefernces(getActivity());

        loadData();



        return root;
    }

    public void loadData() {

        HashMap<String, String> params = new HashMap<>();
        params.put("b_id",allSharedPrefernces.getCustomerNo());

        String url = Constants.Webserive_Url + "get_enquiry_based_on_builders.php";
        volley.CallVolley(url, params, "get_enquiry_based_on_builders");

    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {

            if (tag.equalsIgnoreCase("get_enquiry_based_on_builders")) {

                EnquiryInfoVo enquiryInfoVo = new Gson().fromJson(jsonObject.toString(), EnquiryInfoVo.class);

                if (enquiryInfoVo != null) {

                    if (enquiryInfoVo.getResult() != null) {

                        if (enquiryInfoVo.getResult().size() > 0) {

                            EnquiryListAdapter adapter = new EnquiryListAdapter(enquiryInfoVo.getResult(),this);
                            recvEnquiry.setAdapter(adapter);
                        }

                    }

                }

            }
            else {

                Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                if (jsonObject.getString("response").equalsIgnoreCase("1")) {

                    loadData();

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

        showReplyDialog(enquiryResultVo);

    }

    AlertDialog ad  = null;

    public void showReplyDialog(final EnquiryResultVo enquiryResultVo) {

        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setTitle("Give your reply");

        View vs = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_reply,null);
        adb.setView(vs);
        final EditText edtReply = (EditText)vs.findViewById(R.id.edtReply);

        adb.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (TextUtils.isEmpty(edtReply.getText().toString().trim())) {
                    edtReply.setError("Please enter your text");
                    return;
                }

                String url = Constants.Webserive_Url + "update_enquiry_reply.php";

                HashMap<String, String> params = new HashMap<>();
                params.put("e_id",enquiryResultVo.getEId());
                params.put("e_reply",edtReply.getText().toString().trim());

                volley.CallVolley(url, params, "update_enquiry_reply");

                ad.dismiss();
            }
        });

        ad = adb.create();


        ad.show();


    }
}