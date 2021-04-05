package com.sixhack.troom_troom.Fragments_Troom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixhack.troom_troom.Adapter_Troom.Adapter_Troom;
import com.sixhack.troom_troom.R;


public class Home_Fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        int[] home_images = { R.drawable.h_1, R.drawable.h_2,
                R.drawable.h_3,R.drawable.h_4,R.drawable.h_5,R.drawable.h_6,
                R.drawable.h_7,R.drawable.h_8,R.drawable.h_9,R.drawable.h_10,
                R.drawable.h_11,R.drawable.h_12,R.drawable.h_13,R.drawable.h_14,R.drawable.h_15,
                R.drawable.h_16,R.drawable.h_17,R.drawable.h_18,R.drawable.h_19,R.drawable.h_20,
                R.drawable.h_21,R.drawable.h_22,R.drawable.h_23,R.drawable.h_24,R.drawable.h_25,
                R.drawable.h_26,R.drawable.h_27,R.drawable.h_28,R.drawable.h_29};

        String[] home_tile = getResources().getStringArray(R.array.Home_title);
        String[] home_time = getResources().getStringArray(R.array.Home_time);
        String[] home_view = getResources().getStringArray(R.array.Home_views);
        String[] home_link = getResources().getStringArray(R.array.Home_links);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewhomeId);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        Adapter_Troom adapterTroom = new Adapter_Troom(getContext(),home_images,home_tile,home_view,home_time,home_link);
        recyclerView.setAdapter(adapterTroom);

        return view;

    }
}