package com.club_friend.game.club_for_game;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Game extends Fragment {

    public static TextView tv_number_table;

    // константы для ID пунктов меню
//    final int MENU = 1;
//    final int MENU_ID = 2;
//    final int MENU_TEL = 3;
//    final int MENU_EM = 4;
//    final int MENU_FIO = 5;
//    final private static int DIALOG_LOGIN = 1;
    public static ImageView iv1_add;
    public static ImageView iv1;
    public static Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10;

    public static TextView tv1_id,tv2_id,tv3_id,tv4_id,tv5_id,tv6_id,tv7_id,tv8_id,tv9_id,tv10_id;
    public static TextView tv1_fio,tv2_fio,tv3_fio,tv4_fio,tv5_fio,tv6_fio,tv7_fio,tv8_fio,tv9_fio,tv10_fio;
    public static TextView tv1_bal,tv2_bal,tv3_bal,tv4_bal,tv5_bal,tv6_bal,tv7_bal,tv8_bal,tv9_bal,tv10_bal;
    public static ImageView iv2_add,iv4_add,iv5_add,iv6_add,iv7_add,iv8_add,iv9_add,iv10_add;
    public static ImageView iv2,iv4,iv5,iv6,iv7,iv8,iv9,iv10;
    public static ImageView iv3_add;
    public static ImageView iv3;
    public static ImageView iv1_pause,iv2_pause,iv3_pause,iv4_pause,iv5_pause,iv6_pause,iv7_pause,iv8_pause,iv9_pause,iv10_pause;
    public static TextView tv1_i,tv2_i,tv3_i,tv4_i,tv5_i,tv6_i,tv7_i,tv8_i,tv9_i,tv10_i;
    public static ProgressBar pb_game;

    public static void Game(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_number_table=(TextView)getActivity().findViewById(R.id.tv_number_table);
        pb_game=(ProgressBar)getActivity().findViewById(R.id.pb_game);
        String second=MainActivity.et_wellcome_text;
        tv_number_table.setText(second);

//client1
        iv1_add = (ImageView)getActivity().findViewById(R.id.iv1_add); //кнопка
        iv1 = (ImageView)getActivity().findViewById(R.id.iv1);
        iv1_pause = (ImageView)getActivity().findViewById(R.id.iv1_pause); //кнопка

        bt1 = (Button) getActivity().findViewById(R.id.bt1);  //кнопка

        tv1_id=(TextView)getActivity().findViewById(R.id.tv1_id);
        tv1_fio=(TextView)getActivity().findViewById(R.id.tv1_fio);
        tv1_bal=(TextView)getActivity().findViewById(R.id.tv1_bal);
        tv1_i=(TextView)getActivity().findViewById(R.id.tv1_i);

//client2
        iv2_add = (ImageView)getActivity().findViewById(R.id.iv2_add);
        iv2 = (ImageView)getActivity().findViewById(R.id.iv2);
        iv2_pause = (ImageView)getActivity().findViewById(R.id.iv2_pause);

        bt2 = (Button) getActivity().findViewById(R.id.bt2);

        tv2_id=(TextView)getActivity().findViewById(R.id.tv2_id);
        tv2_fio=(TextView)getActivity().findViewById(R.id.tv2_fio);
        tv2_bal=(TextView)getActivity().findViewById(R.id.tv2_bal);
        tv2_i=(TextView)getActivity().findViewById(R.id.tv2_i);

//client3
        iv3_add = (ImageView)getActivity().findViewById(R.id.iv3_add);
        iv3 = (ImageView)getActivity().findViewById(R.id.iv3);
        iv3_pause = (ImageView)getActivity().findViewById(R.id.iv3_pause);

        bt3 = (Button) getActivity().findViewById(R.id.bt3);

        tv3_id=(TextView)getActivity().findViewById(R.id.tv3_id);
        tv3_fio=(TextView)getActivity().findViewById(R.id.tv3_fio);
        tv3_bal=(TextView)getActivity().findViewById(R.id.tv3_bal);
        tv3_i=(TextView)getActivity().findViewById(R.id.tv3_i);

//client4
        iv4_add = (ImageView)getActivity().findViewById(R.id.iv4_add);
        iv4 = (ImageView)getActivity().findViewById(R.id.iv4);
        iv4_pause = (ImageView)getActivity().findViewById(R.id.iv4_pause);

        bt4 = (Button) getActivity().findViewById(R.id.bt4);

        tv4_id=(TextView)getActivity().findViewById(R.id.tv4_id);
        tv4_fio=(TextView)getActivity().findViewById(R.id.tv4_fio);
        tv4_bal=(TextView)getActivity().findViewById(R.id.tv4_bal);
        tv4_i=(TextView)getActivity().findViewById(R.id.tv4_i);

//client5
        iv5_add = (ImageView)getActivity().findViewById(R.id.iv5_add);
        iv5 = (ImageView)getActivity().findViewById(R.id.iv5);
        iv5_pause = (ImageView)getActivity().findViewById(R.id.iv5_pause);

        bt5 = (Button) getActivity().findViewById(R.id.bt5);

        tv5_id=(TextView)getActivity().findViewById(R.id.tv5_id);
        tv5_fio=(TextView)getActivity().findViewById(R.id.tv5_fio);
        tv5_bal=(TextView)getActivity().findViewById(R.id.tv5_bal);
        tv5_i=(TextView)getActivity().findViewById(R.id.tv5_i);

//client6
        iv6_add = (ImageView)getActivity().findViewById(R.id.iv6_add);
        iv6 = (ImageView)getActivity().findViewById(R.id.iv6);
        iv6_pause = (ImageView)getActivity().findViewById(R.id.iv6_pause);

        bt6 = (Button) getActivity().findViewById(R.id.bt6);

        tv6_id=(TextView)getActivity().findViewById(R.id.tv6_id);
        tv6_fio=(TextView)getActivity().findViewById(R.id.tv6_fio);
        tv6_bal=(TextView)getActivity().findViewById(R.id.tv6_bal);
        tv6_i=(TextView)getActivity().findViewById(R.id.tv6_i);

//client7
        iv7_add = (ImageView)getActivity().findViewById(R.id.iv7_add);
        iv7 = (ImageView)getActivity().findViewById(R.id.iv7);
        iv7_pause = (ImageView)getActivity().findViewById(R.id.iv7_pause);

        bt7 = (Button) getActivity().findViewById(R.id.bt7);

        tv7_id=(TextView)getActivity().findViewById(R.id.tv7_id);
        tv7_fio=(TextView)getActivity().findViewById(R.id.tv7_fio);
        tv7_bal=(TextView)getActivity().findViewById(R.id.tv7_bal);
        tv7_i=(TextView)getActivity().findViewById(R.id.tv7_i);

//client8
        iv8_add = (ImageView)getActivity().findViewById(R.id.iv8_add);
        iv8 = (ImageView)getActivity().findViewById(R.id.iv8);
        iv8_pause = (ImageView)getActivity().findViewById(R.id.iv8_pause);

        bt8 = (Button) getActivity().findViewById(R.id.bt8);

        tv8_id=(TextView)getActivity().findViewById(R.id.tv8_id);
        tv8_fio=(TextView)getActivity().findViewById(R.id.tv8_fio);
        tv8_bal=(TextView)getActivity().findViewById(R.id.tv8_bal);
        tv8_i=(TextView)getActivity().findViewById(R.id.tv8_i);

//client9
        iv9_add = (ImageView)getActivity().findViewById(R.id.iv9_add);
        iv9 = (ImageView)getActivity().findViewById(R.id.iv9);
        iv9_pause = (ImageView)getActivity().findViewById(R.id.iv9_pause);

        bt9 = (Button) getActivity().findViewById(R.id.bt9);

        tv9_id=(TextView)getActivity().findViewById(R.id.tv9_id);
        tv9_fio=(TextView)getActivity().findViewById(R.id.tv9_fio);
        tv9_bal=(TextView)getActivity().findViewById(R.id.tv9_bal);
        tv9_i=(TextView)getActivity().findViewById(R.id.tv9_i);

//client10
        iv10_add = (ImageView)getActivity().findViewById(R.id.iv10_add);
        iv10 = (ImageView)getActivity().findViewById(R.id.iv10);
        iv10_pause = (ImageView)getActivity().findViewById(R.id.iv10_pause);

        bt10 = (Button) getActivity().findViewById(R.id.bt10);

        tv10_id=(TextView)getActivity().findViewById(R.id.tv10_id);
        tv10_fio=(TextView)getActivity().findViewById(R.id.tv10_fio);
        tv10_bal=(TextView)getActivity().findViewById(R.id.tv10_bal);
        tv10_i=(TextView)getActivity().findViewById(R.id.tv10_i);

        // регистрируем контекстное меню для компонента tv
        //registerForContextMenu(iv1_add);

    }



//_______________
//код для появляющегося списка
//________________
    //в onActivityCreated
    // регистрируем контекстное меню для компонента tv
    //registerForContextMenu(iv1_add);
//_________________
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        // TODO Auto-generated method stub
//        switch (v.getId()) {
//            case R.id.iv1_add:
//                // добавляем пункты
//                menu.add(0, MENU, 0, "Указать:");
//                menu.add(0, MENU_ID, 0, "id");
//                menu.add(0, MENU_TEL, 0, "телефон");
//                menu.add(0, MENU_EM, 0, "email");
//                menu.add(0, MENU_FIO, 0, "фамилию и имя");
//
//                break;
//        }
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        Animation anim = null;
//        // определяем какой пункт был нажат
//        switch (item.getItemId()) {
////            case MENU_ALPHA_ID:
////                // создаем объект анимации из файла anim/myalpha
////               // anim = AnimationUtils.loadAnimation(this, R.anim.myalpha);
////                break;
//            case MENU_ID:
//
//               // anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
//                break;
//            case MENU_TEL:
//               // anim = AnimationUtils.loadAnimation(this, R.anim.mytrans);
//                break;
//            case MENU_EM:
//               // anim = AnimationUtils.loadAnimation(this, R.anim.myrotate);
//                break;
//            case MENU_FIO:
//              //  anim = AnimationUtils.loadAnimation(this, R.anim.mycombo);
//                break;
//        }
//        // запускаем анимацию для компонента tv
//       // bt1_play.startAnimation(anim);
//        return super.onContextItemSelected(item);
//    }
//_________________


}
