package com.club_friend.kassa.fragments;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.club_friend.kassa.MainActivity;
import com.club_friend.kassa.R;


public class ScreenThree_two_table extends Fragment {


    public static LinearLayout ll_cash_t;
    public static LinearLayout ll_table_t;
    public static ProgressBar bp_table;
    public static TextView tv_table_t;
    public static EditText[] in;

    public ScreenThree_two_table() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.screen_two_table, container,
                    false);


        return rootView;

    }
    public  final static String TAG="my";
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);

        ll_table_t=(LinearLayout)getActivity().findViewById(R.id.ll_table);
        tv_table_t=(TextView)getActivity().findViewById(R.id.tv_table);

        String l=MainActivity.res_game0[0];
        tv_table_t.setText(l);

        bp_table=(ProgressBar)getActivity().findViewById(R.id.pb_table);
        in=new EditText [MainActivity.count_game0];
        for (int x = 1; x < MainActivity.count_game0; x++) {
            //   from[x] = res_game0[x];
            String table=MainActivity.res_game0[x];

            if(table.compareToIgnoreCase("")!=0) {
                // создаём текстовое предстввление

                LinearLayout ll=new LinearLayout(getActivity());
                ll.setGravity(View.TEXT_ALIGNMENT_CENTER);

                TextView textPoint = new TextView(getActivity());
                textPoint.setTextColor(0xFFFFFFFF);
//                Drawable bd = getResources().getDrawable(R.drawable.abc_popup_background_mtrl_mult);// вызываем getDrawable для получения изображения
//                textPoint.setBackground(bd);// Затем можно использовать полученый объект, чтобы установить фон
                textPoint.setWidth(100);
                textPoint.setHeight(50);
                textPoint.setGravity(View.FOCUS_RIGHT);
                textPoint.setTextSize(26);
                textPoint.setMinLines(1);
                textPoint.setMaxLines(1);

                textPoint.setText(table);

                // создаём текстовое предстввление
                EditText textPoint2 = new EditText(getActivity());
                textPoint2.setTextColor(0xFF000000);
                Drawable bd2 = getResources().getDrawable(R.drawable.abc_popup_background_mtrl_mult);// вызываем getDrawable для получения изображения
                textPoint2.setBackground(bd2);// Затем можно использовать полученый объект, чтобы установить фон
                textPoint2.setWidth(200);
                textPoint.setHeight(50);
                textPoint.setGravity(View.FOCUS_LEFT);
                textPoint2.setTextSize(26);
                textPoint2.setMinLines(1);
                textPoint2.setMaxLines(1);
                textPoint2.setInputType(InputType.TYPE_CLASS_PHONE);
                textPoint2.setText("");

                ll.addView(textPoint);
                ll.addView(textPoint2);
                in[x]=textPoint2;

                ScreenThree_two_table.ll_table_t.addView(ll);


            }
        }


    }

}
