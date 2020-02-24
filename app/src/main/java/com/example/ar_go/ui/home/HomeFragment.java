package com.example.ar_go.ui.home;

import android.content.Intent;
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
import com.example.ar_go.AddProperty;
import com.example.ar_go.Models.PropertyinfoVo;
import com.example.ar_go.R;
import com.example.ar_go.utils.AllSharedPrefernces;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class HomeFragment extends Fragment implements DataInterface {

    private HomeViewModel homeViewModel;
    RecyclerView recvBuilderhome;
    FloatingActionButton fabAdd;


    Webservice_Volley volley;

    AllSharedPrefernces allSharedPrefernces;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recvBuilderhome = (RecyclerView)root.findViewById(R.id.recvBuilderhome);
        fabAdd = (FloatingActionButton) root.findViewById(R.id.fabAdd);

        volley = new Webservice_Volley(getActivity(), this);

        allSharedPrefernces= new AllSharedPrefernces(getActivity());


        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), AddProperty.class));

            }
        });


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        HashMap<String, String> params = new HashMap<>();
        params.put("b_id",allSharedPrefernces.getCustomerNo());

        String url = Constants.Webserive_Url + "get_property_based_on_builders.php";

        volley.CallVolley(url, params, "get_property_based_on_builders");
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

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