package com.example.ar_go.ui.share;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ar_go.Adapter.EnquiryListAdapter;
import com.example.ar_go.Models.EnquiryInfoVo;
import com.example.ar_go.Models.UserInfoVo;
import com.example.ar_go.R;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class ShareFragment extends Fragment implements DataInterface {

    private ShareViewModel shareViewModel;
    EditText edtemail, edtadd, edtcontact;
    TextView tvname;

    Webservice_Volley volley;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_share, container, false);

        tvname=(TextView) root.findViewById(R.id.tvname);
        edtemail = (EditText) root.findViewById(R.id.edtemail);
        edtadd = (EditText) root.findViewById(R.id.edtadd);
        edtcontact = (EditText) root.findViewById(R.id.edtcontact);

        volley = new Webservice_Volley(getActivity(), this);

        HashMap<String, String> params = new HashMap<>();

        params.put("u_id","2");

        String url = Constants.Webserive_Url + "get_userprofile.php";

        volley.CallVolley(url, params, "get_userprofile");



        return root;
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            if (tag.equalsIgnoreCase("get_userprofile")) {

                UserInfoVo userInfoVo = new Gson().fromJson(jsonObject.toString(), UserInfoVo.class);

                if (userInfoVo != null) {

                    edtadd.setText(userInfoVo.getUAddress());
                    edtemail.setText(userInfoVo.getUEmail());
                    edtcontact.setText(userInfoVo.getUContactno());
                    tvname.setText(userInfoVo.getUName());

                }

            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
