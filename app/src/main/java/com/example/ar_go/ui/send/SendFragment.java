package com.example.ar_go.ui.send;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ar_go.ApiModels.BuilderResultVo;
import com.example.ar_go.ApiModels.BuilderinfoVo;
import com.example.ar_go.Models.UserInfoVo;
import com.example.ar_go.R;
import com.example.ar_go.utils.AllSharedPrefernces;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class SendFragment extends Fragment implements DataInterface {

    private SendViewModel sendViewModel;
    EditText edtemail, edtadd, edtcontact;
    TextView tvname;

    Webservice_Volley volley;
    AllSharedPrefernces allSharedPrefernces;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_share, container, false);

                tvname=(TextView) root.findViewById(R.id.tvname);
                edtemail = (EditText) root.findViewById(R.id.edtemail);
                edtadd = (EditText) root.findViewById(R.id.edtadd);
                edtcontact = (EditText) root.findViewById(R.id.edtcontact);

                volley = new Webservice_Volley(getActivity(), this);
                allSharedPrefernces=new AllSharedPrefernces(getActivity());

                HashMap<String, String> params = new HashMap<>();

                params.put("b_id",allSharedPrefernces.getCustomerNo());

                String url = Constants.Webserive_Url + "get_builderprofile.php";

                volley.CallVolley(url, params, "get_builderprofile");

        return root;
    }



    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {

            if (tag.equalsIgnoreCase("get_builderprofile")) {

                BuilderResultVo builderResultVo = new Gson().fromJson(jsonObject.toString(), BuilderResultVo.class);

                if (builderResultVo != null) {

                    edtadd.setText(builderResultVo.getBAddress());
                    edtemail.setText(builderResultVo.getBEmail());
                    edtcontact.setText(builderResultVo.getBContactno());
                    tvname.setText(builderResultVo.getBName());

                }

            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}