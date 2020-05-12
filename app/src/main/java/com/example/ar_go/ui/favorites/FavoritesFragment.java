package com.example.ar_go.ui.favorites;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ar_go.Adapter.MyListAdapter;
import com.example.ar_go.Models.PropertyResultVo;
import com.example.ar_go.PropertyListActivity;
import com.example.ar_go.R;
import com.example.ar_go.utils.AllSharedPrefernces;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {


    public FavoritesFragment() {
        // Required empty public constructor
    }

    AllSharedPrefernces allSharedPrefernces;

    RecyclerView recycle_favorites;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_favorites, container, false);

        recycle_favorites = (RecyclerView)rootview.findViewById(R.id.recycle_favorites);
        recycle_favorites.setLayoutManager(new GridLayoutManager(getActivity(),2));

        allSharedPrefernces = new AllSharedPrefernces(getActivity());

        Type listType = new TypeToken<List<PropertyResultVo>>(){}.getType();
        List<PropertyResultVo> propertyResultVoList = new Gson().fromJson(allSharedPrefernces.getCartList(), listType);

        if(propertyResultVoList != null) {
            if (propertyResultVoList.size() > 0) {

                MyListAdapter adapter = new MyListAdapter(getActivity(),propertyResultVoList);
                recycle_favorites.setAdapter(adapter);

            }
        }


        return rootview;
    }

}
