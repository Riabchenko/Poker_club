package com.club_friend.manager.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import com.club_friend.manager.R;

public class ScreenThree_m extends Fragment {
    private RadioGroup rg_search;
    private ProgressBar pr_m;

//=====================================================================
//интерфейс реализуем
    public interface onSomeEventListener {
        public void someEvent_m(int a);
    }
    onSomeEventListener someEventListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            someEventListener = (onSomeEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }
//=====================================================================
    public ScreenThree_m() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.screen_three_main_m, container,
                false);
        return rootView;
    }
    Button rb;
    private static String TAG ="my1";


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rg_search = (RadioGroup) getActivity().findViewById(R.id.rg_search);

        pr_m = (ProgressBar) getActivity().findViewById(R.id.pr_m);


        final RadioGroup.OnCheckedChangeListener check = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int a=0;
                switch (checkedId) {
                    case R.id.rb_who:
                        Log.d(TAG, "нажата who");
                        pr_m.setVisibility(View.VISIBLE);
                        a=1;
                        someEventListener.someEvent_m(a);
                        pr_m.setVisibility(View.INVISIBLE);
                       break;
                    case R.id.rb_ank:
                        Log.d(TAG, "нажата ank");
                        pr_m.setVisibility(View.VISIBLE);
                        a=2;
                        someEventListener.someEvent_m(a);
                        pr_m.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.rb_new:
                        Log.d(TAG, "нажата new");
                        pr_m.setVisibility(View.VISIBLE);
                        a=3;
                        someEventListener.someEvent_m(a);
                        pr_m.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        };
        rg_search.setOnCheckedChangeListener(check);
//

    }


}
