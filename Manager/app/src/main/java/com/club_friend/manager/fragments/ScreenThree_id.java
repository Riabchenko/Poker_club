package com.club_friend.manager.fragments;

import android.app.Fragment;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.club_friend.manager.R;

/**
 * Created by apple on 2/12/15.
 */
public class ScreenThree_id extends Fragment {

    public static ProgressBar pb_id;
    public static EditText a_by_id;

    public static TextView ank_id_id,ank_id_f,ank_id_i,ank_id_o,ank_id_dr,ank_id_tel,ank_id_email,ank_id_friend,ank_id_balance,ank_id_status,ank_id_reg;

    public static ImageView ank_id_picture;
    private Cursor mCursor;
    private SimpleCursorAdapter mCursorAd;
    private ListView Ltelephone;
    public static LinearLayout ank_id_f2,ank_id_f3;

    public ScreenThree_id() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.screen_three_id, container,
                    false);


        return rootView;

    }
    public  final static String TAG="my";
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);

        pb_id=(ProgressBar) getActivity().findViewById(R.id.progress_id);
        a_by_id=(EditText)getActivity().findViewById(R.id.anketa_by_id);

        ank_id_id=(TextView)getActivity().findViewById(R.id.ank_id_id);
        ank_id_f=(TextView)getActivity().findViewById(R.id.ank_id_f);
        ank_id_i=(TextView)getActivity().findViewById(R.id.ank_id_i);
        ank_id_o=(TextView)getActivity().findViewById(R.id.ank_id_o);
        ank_id_dr=(TextView)getActivity().findViewById(R.id.ank_id_dr);

        ank_id_friend=(TextView)getActivity().findViewById(R.id.ank_id_friend);
        ank_id_balance=(TextView)getActivity().findViewById(R.id.ank_id_balance);
        ank_id_status=(TextView)getActivity().findViewById(R.id.ank_id_status);
        ank_id_reg=(TextView)getActivity().findViewById(R.id.ank_id_reg);
        ank_id_f2=(LinearLayout) getActivity().findViewById(R.id.f22);
        ank_id_f3=(LinearLayout) getActivity().findViewById(R.id.f33);

        ank_id_picture=(ImageView)getActivity().findViewById(R.id.picture_id);
        Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
        ank_id_picture.setImageURI(path);



    }



//
//    @Override
//    public void onViewStateRestored(Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//    }
//
//    public Object onRetainNonConfigurationInstance(Bundle outState) {
//        Log.d(TAG, "мы в onRetainNonConfigurationInstance и outState=" + outState.toString());
//        ScreenThree_id save=new ScreenThree_id();
//       return save;
//    }
}
