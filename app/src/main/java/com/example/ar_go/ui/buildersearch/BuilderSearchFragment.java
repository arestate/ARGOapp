package com.example.ar_go.ui.buildersearch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ar_go.Adapter.BuilderListAdapter;
import com.example.ar_go.Adapter.BuilderListAdapter1;
import com.example.ar_go.Adapter.MyListAdapter;
import com.example.ar_go.ApiModels.BuilderinfoVo;
import com.example.ar_go.Models.PropertyinfoVo;
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
public class BuilderSearchFragment extends Fragment implements DataInterface {

    public BuilderSearchFragment() {
        // Required empty public constructor
    }

    RecyclerView recycler_builder;
    EditText edtSearch;

    Webservice_Volley volley;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_builder_search, container, false);

        edtSearch = (EditText)root.findViewById(R.id.edtSearch);
        recycler_builder = (RecyclerView) root.findViewById(R.id.recycler_builder);

        recycler_builder.setLayoutManager(new LinearLayoutManager(getActivity()));

        recycler_builder.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        volley = new Webservice_Volley( getActivity() , this);


        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    searchBuilder(v.getText().toString());

                    return true;
                }
                return false;
            }
        });

        return root;
    }

    public void searchBuilder(String text) {

        HashMap<String, String> params = new HashMap<>();
        params.put("b_name",text);

        String url = Constants.Webserive_Url + "get_search_builder.php";
        volley.CallVolley(url, params, "get_search_builder");


    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            if (tag.equalsIgnoreCase("get_search_builder")) {

                BuilderinfoVo builderinfoVo = new Gson().fromJson(jsonObject.toString(), BuilderinfoVo.class);

                if (builderinfoVo != null) {

                    if (builderinfoVo.getResult() != null) {

                        if (builderinfoVo.getResult().size() > 0) {

                            BuilderListAdapter1 adapter = new BuilderListAdapter1(builderinfoVo.getResult());
                            recycler_builder.setAdapter(adapter);

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
