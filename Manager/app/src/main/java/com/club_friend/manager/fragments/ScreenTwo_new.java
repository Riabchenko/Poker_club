package com.club_friend.manager.fragments;

import android.net.Uri;
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

public class ScreenTwo_new extends Fragment {

    public static ImageView ivCamera;
    public static TextView reg_f, reg_i, reg_o, reg_dr, reg_tel, reg_email, reg_friend;

    private ImageButton btn_reg_dr;

    public static ProgressBar pb2;




    public ScreenTwo_new() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.screen_two_new, container,
                false);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);

        ivCamera = (ImageView) getActivity().findViewById(R.id.reg_photo);
        Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/camera");
        ivCamera.setImageURI(path);

        reg_f = (TextView) getActivity().findViewById(R.id.reg_f);
        reg_i = (TextView) getActivity().findViewById(R.id.reg_i);
        reg_o = (TextView) getActivity().findViewById(R.id.reg_o);
        reg_dr = (TextView) getActivity().findViewById(R.id.reg_dr);
        reg_tel = (TextView) getActivity().findViewById(R.id.reg_tel);
        reg_email = (TextView) getActivity().findViewById(R.id.reg_email);
        reg_friend = (TextView) getActivity().findViewById(R.id.reg_friend);
        btn_reg_dr = (ImageButton) getActivity().findViewById(R.id.btn_dr);

        pb2 = (ProgressBar) getActivity().findViewById(R.id.progress_2);

//
    }

}

//













