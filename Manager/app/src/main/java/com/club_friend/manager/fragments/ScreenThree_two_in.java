package com.club_friend.manager.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.club_friend.manager.MainActivity;
import com.club_friend.manager.R;


public class ScreenThree_two_in extends Fragment {


    public static LinearLayout ll_cash_t;
    public static LinearLayout ll_table_t;
    public static ProgressBar bp_table;
    public static TextView tv_table_t;
    public static EditText[] in;
    private static TableLayout tl_table_t;

    public ScreenThree_two_in() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.screen_two_in, container,
                    false);


        return rootView;

    }
    public  final static String TAG="my";
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);

        tl_table_t=(TableLayout)getActivity().findViewById(R.id.tl_table_in);
        tv_table_t=(TextView)getActivity().findViewById(R.id.tv_table_in);

        String l="C "+MainActivity.day1_text+" по "+MainActivity.day2_text;
        tv_table_t.setText(l);

        bp_table=(ProgressBar)getActivity().findViewById(R.id.pb_table_in);
        in=new EditText [MainActivity.rcount-1];
        for (int x = 0; x < MainActivity.rcount; x++) {
            //   from[x] = res_game0[x];
            String day=MainActivity.rev[x][0]; //день
            String table=MainActivity.rev[x][1]; //стол
            String cash=MainActivity.rev[x][2]; //деньги

            if(table.compareToIgnoreCase("")!=0) {
                // создаём текстовое предстввление

                LinearLayout ll=new LinearLayout(getActivity());
                ll.setGravity(View.TEXT_ALIGNMENT_CENTER);
                ll.setOrientation(LinearLayout.HORIZONTAL);

                TextView textPoint = new TextView(getActivity());
                textPoint.setTextColor(0xFFFFFFFF);
//                Drawable bd = getResources().getDrawable(R.drawable.abc_popup_background_mtrl_mult);// вызываем getDrawable для получения изображения
//                textPoint.setBackground(bd);// Затем можно использовать полученый объект, чтобы установить фон
                textPoint.setWidth(300);
                textPoint.setHeight(50);
                textPoint.setGravity(View.FOCUS_RIGHT);
                textPoint.setTextSize(20);
                textPoint.setMinLines(1);
                textPoint.setMaxLines(1);

                textPoint.setText(day);

                TextView textPoint1 = new TextView(getActivity());
                textPoint1.setTextColor(0xFFFFFFFF);
//                Drawable bd = getResources().getDrawable(R.drawable.abc_popup_background_mtrl_mult);// вызываем getDrawable для получения изображения
//                textPoint.setBackground(bd);// Затем можно использовать полученый объект, чтобы установить фон
                textPoint1.setWidth(100);
                textPoint1.setHeight(50);
                textPoint1.setGravity(View.FOCUS_RIGHT);
                textPoint1.setTextSize(26);
                textPoint1.setMinLines(1);
                textPoint1.setMaxLines(1);
                textPoint1.setText(table);

                TextView textPoint2 = new TextView(getActivity());
                textPoint2.setTextColor(0xFFFFFFFF);
//                Drawable bd = getResources().getDrawable(R.drawable.abc_popup_background_mtrl_mult);// вызываем getDrawable для получения изображения
//                textPoint.setBackground(bd);// Затем можно использовать полученый объект, чтобы установить фон
                textPoint2.setWidth(100);
                textPoint2.setHeight(50);
                textPoint2.setGravity(View.FOCUS_RIGHT);
                textPoint2.setTextSize(26);
                textPoint2.setMinLines(1);
                textPoint2.setMaxLines(1);
                textPoint2.setText(cash);

                ll.addView(textPoint);
                ll.addView(textPoint1);
                ll.addView(textPoint2);
               // in[x]=textPoint2;

                ScreenThree_two_in.tl_table_t.addView(ll);


            }
        }


    }

}
