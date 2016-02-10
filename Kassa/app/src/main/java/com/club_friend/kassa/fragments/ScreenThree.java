package com.club_friend.kassa.fragments;

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

import com.club_friend.kassa.R;

public class ScreenThree extends Fragment {
    private RadioGroup RadioGroup_by;
    private ProgressBar pb1;

//=====================================================================
//интерфейс реализуем
    public interface onSomeEventListener {
        public void someEvent(int a);
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
    public ScreenThree() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.screen_three_main, container,
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

        RadioGroup_by = (RadioGroup) getActivity().findViewById(R.id.RadioGroup_by);

        pb1 = (ProgressBar) getActivity().findViewById(R.id.progress_1);


        final RadioGroup.OnCheckedChangeListener check = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int a=0;
                switch (checkedId) {
                    case R.id.radioButton_id:
                        Log.d(TAG, "нажата ID");
                        pb1.setVisibility(View.VISIBLE);
                        a=1;
                        someEventListener.someEvent(a);
//                        MainActivity.MyTask mt = (MainActivity.MyTask) getActivity().getLastCustomNonConfigurationInstance();
//                        if (mt == null) {
//                            Log.d(TAG, "еще раз запустили с 0!!!");
//                            mt = new MainActivity.MyTask();
//                            mt.execute();
//                        }
//                          Log.d(TAG, "MyTask: " + this.hashCode()
//                                + ", MainActivity: " + ScreenThree.this.hashCode());
                        pb1.setVisibility(View.INVISIBLE);
                       break;
                    case R.id.radioButton_tel:
                        Log.d(TAG, "нажата tel");
                        pb1.setVisibility(View.VISIBLE);
                        a=2;
                        someEventListener.someEvent(a);
                        pb1.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.radioButton_email:
                        Log.d(TAG, "нажата email");
                        pb1.setVisibility(View.VISIBLE);
                        a=3;
                        someEventListener.someEvent(a);
                        pb1.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.radioButton_fio:
                        Log.d(TAG, "нажата fio");
                        pb1.setVisibility(View.VISIBLE);
                        a=4;
                        someEventListener.someEvent(a);
                        pb1.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        };
        RadioGroup_by.setOnCheckedChangeListener(check);
//

    }


}
