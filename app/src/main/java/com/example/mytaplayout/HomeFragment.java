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

    private final ArrayList<Model> arrayList = new ArrayList<>();
    RecyclerView rvHeroes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataLoad();
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