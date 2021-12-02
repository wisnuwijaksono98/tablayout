package com.example.mytaplayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private final ArrayList<Model> arrayList = new ArrayList<>();
    RecyclerView rvHeroes;

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(int index) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null){
            getArguments().getInt(ARG_SECTION_NUMBER);
            dataLoad();
        }
    }

    private void dataLoad() {

        AndroidNetworking.get("https://api.github.com/users/mojombo/followers")
                .addHeaders("Authorization", "token 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")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String user = jsonObject.getString("login");
                                String type = jsonObject.getString("type");
                                String avatarUrl = jsonObject.getString("avatar_url");
                                Log.e("anjing", "data ---   " + user);
                                arrayList.add(new Model(
                                        user,
                                        type,
                                        avatarUrl
                                ));

                                recyleViewList();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void recyleViewList() {

        rvHeroes.findViewById(R.id.rv_user);
        rvHeroes.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ListAdapter list = new ListAdapter(arrayList);
        rvHeroes.setLayoutManager(linearLayoutManager);
        rvHeroes.setAdapter(list);
    }
}