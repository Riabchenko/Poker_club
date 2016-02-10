package com.club_friend.clients.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.club_friend.clients.MainActivity;
import com.club_friend.clients.R;
public class ScreenThree_edit extends Fragment {


    public static Button btn_save,btn_addTel,btn_addEm;
    public static LinearLayout edit_telephone,edit_email;
    public static ImageView edit_photo;
    public static EditText edit_f;
    public static EditText edit_i;
    public static EditText edit_o;
    public static TextView edit_dr;
    public static EditText edit_friend;
    public static ImageButton btn_edit_dr;
    public static ProgressBar pb_edit;
    public static int how_tel, how_em;
    public static int res_c_e=0;
    public static int res_c_photo=0;
    public static int res_c_f=0;
    public static int res_c_i=0;
    public static int res_c_o=0;
    public static int res_c_dr=0;
    public static int res_c_fr=0;
    public static int res_c_t=0;
    public static String get_f;
    public static String get_i;
    public static String get_o;
    public static String get_dr;
    public static String get_fr;
    public static String get_t;
    public static String get_e;
    public static String[][] mobile;
    public static String[][] email;


    public EditText textPoint,textPoint1;
    public static EditText[]textP;
    public static EditText[]textE;
    private static String f;
    private static String i;
    private static String o;
    private static String dr;
    private static String fr;



    public final static String TAG = "my";

    public ScreenThree_edit() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.screen_three_edit, container,
                false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);


        edit_photo = (ImageView) getActivity().findViewById(R.id.edit_photo);
        Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
        edit_photo.setImageURI(path);

        edit_f = (EditText) getActivity().findViewById(R.id.edit_f);
        f= MainActivity.rev[0][0];
        if(f.compareToIgnoreCase("")!=0) {
        edit_f.setText(f);}

        edit_i = (EditText) getActivity().findViewById(R.id.edit_i);
        i= MainActivity.rev[1][0];
        edit_i.setText(i.toString());
        Log.d(TAG,"i "+i);

        edit_o = (EditText) getActivity().findViewById(R.id.edit_o);
        o= MainActivity.rev[2][0];
        if(o.compareToIgnoreCase("")!=0) {
        edit_o.setText(o.toString());}

        edit_dr = (TextView) getActivity().findViewById(R.id.edit_dr);
        dr= MainActivity.rev[3][0];
        edit_dr.setText(dr.toString());

        edit_friend = (EditText) getActivity().findViewById(R.id.edit_friend);
        fr= MainActivity.rev[8][0];
        Log.d(TAG,"fr "+fr);
        if(fr.compareToIgnoreCase("")!=0) {
            edit_friend.setText(fr.toString());
        }

        edit_telephone=(LinearLayout)getActivity().findViewById(R.id.f22_edit);

//телефон
        how_tel=0;
        textP = new EditText[10];
        int r=MainActivity.ccount;
        String[] from = new String[r];
        for (int x = 0; x < r; x++) {
            from[x] = MainActivity.rev[6][x];
            String te=from[x];
            if(te.compareToIgnoreCase("")!=0 && how_tel!=10){

                // создаём текстовое предстввление
                textPoint = new EditText(getActivity());
                textPoint.setTextColor(0xFF000000);
                Drawable bd = getResources().getDrawable(R.drawable.abc_popup_background_mtrl_mult);// вызываем getDrawable для получения изображения
                textPoint.setBackground(bd);// Затем можно использовать полученый объект, чтобы установить фон
                textPoint.setWidth(350);
                textPoint.setTextSize(20);
                textPoint.setMinLines(1);
                textPoint.setMaxLines(1);
                textPoint.setInputType(InputType.TYPE_CLASS_PHONE);
                textPoint.setText(te.toString());

                edit_telephone.addView(textPoint);
                textP[how_tel]=textPoint;
                how_tel++;

            }
        }

        edit_email=(LinearLayout)getActivity().findViewById(R.id.f33_edit);

//email
        how_em=0;
        textE = new EditText[10];
        int u=MainActivity.ccount;
        for (int x = 0; x < u; x++) {
            String[] from1 = new String[r];
            from1[x] = MainActivity.rev[7][x];
            String em=from1[x];
            if(em.compareToIgnoreCase("")!=0 && how_em!=10){

                // создаём текстовое предстввление
                textPoint1 = new EditText(getActivity());
                textPoint1.setTextColor(0xFF000000);
                Drawable bd = getResources().getDrawable(R.drawable.abc_popup_background_mtrl_mult);// вызываем getDrawable для получения изображения
                textPoint1.setBackground(bd);// Затем можно использовать полученый объект, чтобы установить фон
                textPoint1.setWidth(350);
                textPoint1.setTextSize(20);
                textPoint1.setMinLines(1);
                textPoint1.setMaxLines(1);
                textPoint1.setText(em.toString());

                textE[how_em]=textPoint1;
                Log.d(TAG,"textE[how_em] "+textE[how_em]);

                edit_email.addView(textPoint1);

                how_em++;
            }
        }

        String en=MainActivity.rev[5][0];
        if(en.compareToIgnoreCase("")!=0) {
            byte[] decodedString = Base64.decode(en, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            edit_photo.setImageBitmap(decodedByte);
        }


        btn_edit_dr = (ImageButton) getActivity().findViewById(R.id.btn_dr_edit);

        btn_save = (Button) getActivity().findViewById(R.id.btn_save);
        btn_addTel = (Button) getActivity().findViewById(R.id.btn_edit_tel);
        btn_addEm = (Button) getActivity().findViewById(R.id.btn_edit_addEm);

        pb_edit = (ProgressBar) getActivity().findViewById(R.id.pb_edit);

//
    }
//===========================================================================================
//сравнение данных
// 1 - заменить
// 2 - удалить
// 3 - добавить
    public static void edit_compare() {
        Log.d(TAG,"MainActivity.Camera_my="+MainActivity.Camera_my);
        if(MainActivity.Camera_my==1){
            Log.d(TAG,"res_c_photo="+res_c_photo);
            res_c_photo=1;
            //значит фото менялось и его нужно пересохранить
        }

        get_f= String.valueOf(edit_f.getText());
        if(get_f.compareToIgnoreCase(f)!=0){
            get_f=get_f.trim();
            res_c_f=1;
            Log.d(TAG,"1 изменения были");
        }

        get_i=String.valueOf(edit_i.getText());
        if(get_i.compareToIgnoreCase(i)!=0){
           get_i= get_i.trim();
            res_c_i=1;
            Log.d(TAG,"2 изменения были");
        }

        get_o=String.valueOf(edit_o.getText());
        if(get_o.compareToIgnoreCase(o)!=0){
            get_o=get_o.trim();
            res_c_o=1;
            Log.d(TAG,"3 изменения были");
        }

        get_dr = String.valueOf(edit_dr.getText());
        if(get_dr.compareToIgnoreCase(dr)!=0){
            res_c_dr=1;
            Log.d(TAG,"4 изменения были");
        }

        get_fr=String.valueOf(edit_friend.getText());
        if(get_fr.compareToIgnoreCase(fr)!=0){
            get_fr=get_fr.trim();
            res_c_fr=1;
            Log.d(TAG,"5 изменения были");
        }

//телефон
        mobile= new String[how_tel][3];
        int l=0;
        for(int r=0;r<how_tel;r++) {
            EditText f=textP[r];
            String t;
            get_t= String.valueOf(f.getText());
            if(MainActivity.rev[6][r]==null){
               t="";
            }else{
            t=MainActivity.rev[6][r];}
            if(get_t.compareToIgnoreCase(t)!=0){
                if(t.compareToIgnoreCase("")==0){
                    get_t=get_t.trim();
                    res_c_t=3;
                    mobile[l][0]="3";
                    mobile[l][1]=get_t;
                    l++;
                    //добавить get_t
                }else{
                    if(get_t.compareToIgnoreCase("")==0){
                        res_c_t=2;
                        mobile[l][0]=String.valueOf(res_c_t);
                        mobile[l][1]=t;
                        l++;
                        //удалить номер с rev
                        //берем t и удаляем его с БД
                    }else{
                        get_t=get_t.trim();
                        res_c_t=1;
                        mobile[l][0]=String.valueOf(res_c_t);
                        mobile[l][1]=t;
                        mobile[l][2]=get_t;
                        l++;
                        //перезаписать
                        //берем t и вместо него записываем get_t
                    }
                }
            }

        }

//email
        email= new String[how_em][3];
        int d=0;
        String em;
        for(int r=0;r<how_em;r++) {
            EditText e=textE[r];
            get_e= String.valueOf(e.getText());
            if(MainActivity.rev[7][r]==null){
              em="";
            }else{
              em=MainActivity.rev[7][r];}
            if(get_e.compareToIgnoreCase(em)!=0){
                if(em.compareToIgnoreCase("")==0){
                    get_e=get_e.trim();
                    Log.d(TAG,"01");
                    res_c_e=3;
                    email[d][0]=String.valueOf(res_c_e);
                    email[d][1]=get_e;
                    d++;
                    //добавить get_t
                }else{
                    Log.d(TAG,"02");
                    if(get_e.compareToIgnoreCase("")==0){
                        Log.d(TAG,"03");
                        res_c_e=2;
                        email[d][0]=String.valueOf(res_c_e);
                        email[d][1]=em;
                        d++;
                        //удалить номер с rev
                        //берем t и удаляем его с БД
                        Log.d(TAG,"удалить "+ e);
                    }else{
                        get_e=get_e.trim();
                        Log.d(TAG,"04");
                        res_c_e=1;
                        email[d][0]=String.valueOf(res_c_e);
                        email[d][1]=em;
                        email[d][2]=get_e;
                        d++;
                        //перезаписать
                        //берем t и вместо него записываем get_t
                    }
                }
            }
        }


    };

//**************************************************************************************************


}
