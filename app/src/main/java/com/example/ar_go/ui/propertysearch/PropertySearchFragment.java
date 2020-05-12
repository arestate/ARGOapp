package com.example.ar_go.ui.propertysearch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ar_go.Adapter.MyListAdapter;
import com.example.ar_go.Models.PropertyResultVo;
import com.example.ar_go.Models.PropertyinfoVo;
import com.example.ar_go.R;
import com.example.ar_go.utils.AllSharedPrefernces;
import com.example.ar_go.utils.Constants;
import com.example.ar_go.utils.DataInterface;
import com.example.ar_go.utils.Webservice_Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PropertySearchFragment extends Fragment implements DataInterface {

    public PropertySearchFragment() {
        // Required empty public constructor
    }
    Webservice_Volley volley;

    AllSharedPrefernces allSharedPrefernces = null;

    RecyclerView recycle_properties;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_property_search, container, false);

        recycle_properties = (RecyclerView)rootview.findViewById(R.id.recycle_properties);
        recycle_properties.setLayoutManager(new GridLayoutManager(getActivity(),2));

        allSharedPrefernces = new AllSharedPrefernces(getActivity());

        volley = new Webservice_Volley(getActivity(),this);

        HashMap<String, String> params = new HashMap<>();
        params.put("p_type","Residential");

        String url = Constants.Webserive_Url + "get_property_based_on_type.php";
        volley.CallVolley(url, params, "get_property_based_on_type");


        return rootview;
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        PropertyinfoVo propertyinfoVo = new Gson().fromJson(jsonObject.toString(), PropertyinfoVo.class);

        if (propertyinfoVo != null) {

            if (propertyinfoVo.getResult() != null) {

                if (propertyinfoVo.getResult().size() > 0) {

                    MyListAdapter adapter = new MyListAdapter(getActivity(),propertyinfoVo.getResult());
                    recycle_properties.setAdapter(adapter);

                }

            }

        }

    }
}
