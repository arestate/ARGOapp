package com.example.ar_go.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ar_go.Adapter.BuilderPropertyListAdapter;
import com.example.ar_go.Adapter.MyListAdapter;
import com.example.ar_go.Models.PropertyinfoVo;
import com.example.ar_go.R;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class HomeFragment extends Fragment implements DataInterface {

    private HomeViewModel homeViewModel;
    RecyclerView recvBuilderhome;
    Webservice_Volley volley;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recvBuilderhome = (RecyclerView)root.findViewById(R.id.recvBuilderhome);
        volley = new Webservice_Volley(getActivity(), this);

        HashMap<String, String> params = new HashMap<>();
        params.put("b_id","2");

        String url = Constants.Webserive_Url + "get_property_based_on_builders.php";

        volley.CallVolley(url, params, "get_property_based_on_builders");
        return root;
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            Toast.makeText(getActivity(), jsonObject.toString(), Toast.LENGTH_SHORT).show();

            PropertyinfoVo propertyinfoVo = new Gson().fromJson(jsonObject.toString(),PropertyinfoVo.class);

            if (propertyinfoVo != null) {

                if (propertyinfoVo.getResult() != null) {

                    if (propertyinfoVo.getResult().size() > 0) {

                        BuilderPropertyListAdapter adapter = new BuilderPropertyListAdapter(propertyinfoVo.getResult());
                        recvBuilderhome.setAdapter(adapter);

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