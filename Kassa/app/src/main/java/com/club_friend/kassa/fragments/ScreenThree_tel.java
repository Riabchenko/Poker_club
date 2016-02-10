package com.club_friend.kassa.fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.club_friend.kassa.R;


public class ScreenThree_tel extends Fragment {

    public static ProgressBar pb_tel;
    public static EditText a_by_tel;
    public static TextView ank_tel_id;
    public static TextView ank_tel_f;
    public static TextView ank_tel_i;
    public static TextView ank_tel_o;
    public static TextView ank_tel_dr;
    public TextView ank_tel_tel;
    public TextView ank_tel_email;
    public static TextView ank_tel_friend;
    public static TextView ank_tel_balance;
    public static TextView ank_tel_status;
    public static TextView ank_tel_reg;
    public static ImageView ank_tel_picture;
    public static LinearLayout ank_tel_f2;
    public static LinearLayout ank_tel_f3;
    public static EditText eT_tel;

    public ScreenThree_tel() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.screen_three_tel, container,
                false);

        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);
        pb_tel=(ProgressBar) getActivity().findViewById(R.id.progress_tel);
        a_by_tel=(EditText)getActivity().findViewById(R.id.anketa_by_tel);

        setRetainInstance(true);

        ank_tel_id=(TextView)getActivity().findViewById(R.id.ank_tel_id);
        ank_tel_f=(TextView)getActivity().findViewById(R.id.ank_tel_f);
        ank_tel_i=(TextView)getActivity().findViewById(R.id.ank_tel_i);
        ank_tel_o=(TextView)getActivity().findViewById(R.id.ank_tel_o);
        ank_tel_dr=(TextView)getActivity().findViewById(R.id.ank_tel_dr);
        ank_tel_friend=(TextView)getActivity().findViewById(R.id.ank_tel_friend);
        ank_tel_balance=(TextView)getActivity().findViewById(R.id.ank_tel_balance);
        ank_tel_status=(TextView)getActivity().findViewById(R.id.ank_tel_status);
        ank_tel_reg=(TextView)getActivity().findViewById(R.id.ank_tel_reg);


        ank_tel_picture=(ImageView)getActivity().findViewById(R.id.picture_tel);
        Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
        ank_tel_picture.setImageURI(path);


        ank_tel_f2=(LinearLayout) getActivity().findViewById(R.id.f22_tel);
        ank_tel_f3=(LinearLayout) getActivity().findViewById(R.id.f33_tel);

        eT_tel=(EditText)getActivity().findViewById(R.id.eT_tel);
    }


}
