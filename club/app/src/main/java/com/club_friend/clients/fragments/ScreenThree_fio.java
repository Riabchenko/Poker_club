package com.club_friend.clients.fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.club_friend.clients.R;

/**
 * Created by apple on 2/12/15.
 */
public class ScreenThree_fio extends Fragment {

    public static ProgressBar pb_fio;
    public static TextView a_fio_i;
    public static TextView a_fio_f;
    public static TextView ank_fio_id;
    public static TextView ank_fio_f;
    public static TextView ank_fio_i;
    public static TextView ank_fio_o;
    public static TextView ank_fio_dr;
    public TextView ank_fio_tel;
    public TextView ank_fio_email;
    public static TextView ank_fio_friend;
    public static TextView ank_fio_balance;
    public static TextView ank_fio_status;
    public static TextView ank_fio_reg;
    public static ImageView ank_fio_picture;
    public static LinearLayout ank_fio_f2;
    public static LinearLayout ank_fio_f3;
    public static LinearLayout ank_fio_tr1,ank_fio_tr2,ank_fio_tr3,ank_fio_tr4,ank_fio_tr5,ank_fio_tr6,ank_fio_tr7,ank_fio_tr8,ank_fio_tr31,ank_fio_f33,ank_fio_f22;
    public static TextView ank_fio_head2,ank_fio_head1;
    public static ImageButton btn_three_search;
    public static Button btn_three_edit;

    public ScreenThree_fio() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.screen_three_fio, container,
                false);



        return rootView;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pb_fio=(ProgressBar) getActivity().findViewById(R.id.progress_fio);
        a_fio_f=(TextView)getActivity().findViewById(R.id.anketa_fio_f);
        a_fio_i=(TextView)getActivity().findViewById(R.id.anketa_fio_i);
        setRetainInstance(true);

        ank_fio_id=(TextView)getActivity().findViewById(R.id.ank_fio_id);
        ank_fio_f=(TextView)getActivity().findViewById(R.id.ank_fio_f);
        ank_fio_i=(TextView)getActivity().findViewById(R.id.ank_fio_i);
        ank_fio_o=(TextView)getActivity().findViewById(R.id.ank_fio_o);
        ank_fio_dr=(TextView)getActivity().findViewById(R.id.ank_fio_dr);
        ank_fio_friend=(TextView)getActivity().findViewById(R.id.ank_fio_friend);
        ank_fio_balance=(TextView)getActivity().findViewById(R.id.ank_fio_balance);
        ank_fio_status=(TextView)getActivity().findViewById(R.id.ank_fio_status);
        ank_fio_reg=(TextView)getActivity().findViewById(R.id.ank_fio_reg);

        ank_fio_head1=(TextView)getActivity().findViewById(R.id.head1);
        ank_fio_head2=(TextView)getActivity().findViewById(R.id.head2);

        ank_fio_picture=(ImageView)getActivity().findViewById(R.id.picture_fio);
        Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
        ank_fio_picture.setImageURI(path);


        ank_fio_f2=(LinearLayout) getActivity().findViewById(R.id.f2);
        ank_fio_f3=(LinearLayout) getActivity().findViewById(R.id.f3);

        ank_fio_f22=(LinearLayout) getActivity().findViewById(R.id.f22_fio);
        ank_fio_f33=(LinearLayout) getActivity().findViewById(R.id.f33_fio);

        ank_fio_tr1=(LinearLayout) getActivity().findViewById(R.id.tr1);
        ank_fio_tr2=(LinearLayout) getActivity().findViewById(R.id.tr2);
        ank_fio_tr3=(LinearLayout) getActivity().findViewById(R.id.tr3);
        ank_fio_tr31=(LinearLayout) getActivity().findViewById(R.id.tr31);
        ank_fio_tr4=(LinearLayout) getActivity().findViewById(R.id.tr4);
        ank_fio_tr5=(LinearLayout) getActivity().findViewById(R.id.tr5);
        ank_fio_tr6=(LinearLayout) getActivity().findViewById(R.id.tr6);
        ank_fio_tr7=(LinearLayout) getActivity().findViewById(R.id.tr7);
        ank_fio_tr8=(LinearLayout) getActivity().findViewById(R.id.tr8);

        btn_three_search=(ImageButton)getActivity().findViewById(R.id.btn_three_search);
        btn_three_edit=(Button)getActivity().findViewById(R.id.edit_fio);



    }
}
