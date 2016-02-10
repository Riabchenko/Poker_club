package com.club_friend.manager.fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.club_friend.manager.R;

/**
 * Created by apple on 2/12/15.
 */
public class ScreenThree_email extends Fragment {

    public  static ProgressBar pb_email;
    public static TextView a_by_email;
    public static TextView ank_email_id;
    public static TextView ank_email_f;
    public static TextView ank_email_i;
    public static TextView ank_email_o;
    public static TextView ank_email_dr;
    public TextView ank_email_tel;
    public TextView ank_email_email;
    public static TextView ank_email_friend;
    public static TextView ank_email_balance;
    public static TextView ank_email_status;
    public static TextView ank_email_reg;
    public static ImageView ank_email_picture;
    public static LinearLayout ank_email_f3;
    public static LinearLayout ank_email_f2;
    private ImageButton btnAlpha;
    public static Animation animAlpha,animTranslate;

    public ScreenThree_email() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.screen_three_email, container,
                false);


        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pb_email=(ProgressBar) getActivity().findViewById(R.id.progress_email);
        a_by_email=(TextView)getActivity().findViewById(R.id.anketa_by_email);

        setRetainInstance(true);

        ank_email_id=(TextView)getActivity().findViewById(R.id.ank_email_id);
        ank_email_f=(TextView)getActivity().findViewById(R.id.ank_email_f);
        ank_email_i=(TextView)getActivity().findViewById(R.id.ank_email_i);
        ank_email_o=(TextView)getActivity().findViewById(R.id.ank_email_o);
        ank_email_dr=(TextView)getActivity().findViewById(R.id.ank_email_dr);
        ank_email_friend=(TextView)getActivity().findViewById(R.id.ank_email_friend);
        ank_email_balance=(TextView)getActivity().findViewById(R.id.ank_email_balance);
        ank_email_status=(TextView)getActivity().findViewById(R.id.ank_email_status);
        ank_email_reg=(TextView)getActivity().findViewById(R.id.ank_email_reg);


        ank_email_picture=(ImageView)getActivity().findViewById(R.id.picture_email);
        Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
        ank_email_picture.setImageURI(path);


        ank_email_f2=(LinearLayout) getActivity().findViewById(R.id.f22_email);
        ank_email_f3=(LinearLayout) getActivity().findViewById(R.id.f33_email);



//        btnAlpha=(ImageButton)getActivity().findViewById(R.id.btn_alpha);
//        animAlpha = AnimationUtils.loadAnimation(getActivity(), R.anim.alfa);
//
////        btnAlpha.setOnClickListener(new Button.OnClickListener() {
////            @Override
////            public void onClick(View rootView) {
////                rootView.startAnimation(animTranslate);
////
////            }
////        });
//
//        animTranslate = AnimationUtils.loadAnimation(getActivity(), R.anim.translate);
    }
}
