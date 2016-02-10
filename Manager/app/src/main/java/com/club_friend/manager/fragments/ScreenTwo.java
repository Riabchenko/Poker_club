package com.club_friend.manager.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.club_friend.manager.R;


public class ScreenTwo extends Fragment {

    public static ImageView ivCamera;
    public static TextView reg_f, reg_i, reg_o, reg_dr, reg_tel, reg_email, reg_friend;

    private ImageButton btn_reg_dr;

    public static ProgressBar pb2;
    public static TextView day1,day2;
    public static ImageButton btn_kalendar2,btn_kalendar1;


    public ScreenTwo() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.screen_two, container,
                false);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);

       day1=(TextView)getActivity().findViewById(R.id.day1);
       day2=(TextView)getActivity().findViewById(R.id.day2);

      //  btn_kalendar1=(ImageButton)getActivity().findViewById(R.id.btn_kalendar1);
       // btn_kalendar2=(ImageButton)getActivity().findViewById(R.id.btn_kalendar2);

        pb2 = (ProgressBar) getActivity().findViewById(R.id.progress_2);

//
    }

}

//













