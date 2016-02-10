package com.club_friend.manager.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.club_friend.manager.MainActivity;
import com.club_friend.manager.R;


public class ScreenThree_two_inEdit extends Fragment {


    public static LinearLayout ll_cash_t;
    public static LinearLayout ll_table_t;
    public static ProgressBar pb_bonus;
    public static TextView tv_table_t;
    public static EditText[] in;
    private static TableLayout tl_table_edit;
    public static TextView[] cop;
    public static String get_c,cash,get_t,table;
    public static int q;
    public static String[][] ed_array,ed1_array;
    public static Button btn_ok,btn_post;

    public ScreenThree_two_inEdit() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.screen_two_inedit, container,
                    false);


        return rootView;

    }
    public  final static String TAG="my";
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);

        tl_table_edit=(TableLayout)getActivity().findViewById(R.id.tl_table_edit);
        tv_table_t=(TextView)getActivity().findViewById(R.id.tv_table_edit);

        btn_ok=(Button)getActivity().findViewById(R.id.bonus_ok);
        btn_post=(Button)getActivity().findViewById(R.id.bonus_post);
        btn_ok.setClickable(true);
        btn_post.setVisibility(View.INVISIBLE);
//        String l=MainActivity.rev[0][0];
//        tv_table_t.setText(l);

        pb_bonus=(ProgressBar)getActivity().findViewById(R.id.pb_bonus);
        in=new EditText [MainActivity.rcount];
        cop=new TextView[MainActivity.rcount];
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
                cop[x]=textPoint1;

                EditText textPoint2 = new EditText(getActivity());
//                textPoint2.setTextColor(0xFFFFFFFF);
                Drawable bd = getResources().getDrawable(R.drawable.abc_popup_background_mtrl_mult);// вызываем getDrawable для получения изображения
                textPoint2.setBackground(bd);// Затем можно использовать полученый объект, чтобы установить фон
                textPoint2.setWidth(150);
                textPoint2.setHeight(50);
                textPoint2.setGravity(View.FOCUS_RIGHT);
                textPoint2.setTextSize(26);
                textPoint2.setMinLines(1);
                textPoint2.setMaxLines(1);
                textPoint2.setText(cash);

                ll.addView(textPoint);
                ll.addView(textPoint1);
                ll.addView(textPoint2);
                in[x]=textPoint2;

                ScreenThree_two_inEdit.tl_table_edit.addView(ll);


            }
        }


    }

//

}
