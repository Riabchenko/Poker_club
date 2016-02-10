package com.club_friend.game.club_for_game;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MainActivity extends ActionBarActivity {

    private static int res_ok;
    public static String et_wellcome_text;
    private static String mes;
    public static String mes_s;

    public  static final String TAG="my";

//**************************
    private static final String SOAP_ACTION = "http://clients.club_friend.com/Game/table_in_gameRequest";
    private static final String SOAP_ACTION_out = "http://clients.club_friend.com/Game/table_out_gameRequest";
    private static final String SOAP_ACTION_reg_cl = "http://clients.club_friend.com/Game/status_gameRequest";
    private static final String METHOD_NAME = "table_in_game";
    private static final String METHOD_NAME_reg_cl = "status_game";
    private static final String METHOD_NAME_out = "table_out_game";
    private static final String NAMESPACE = "http://clients.club_friend.com/";
    private static final String URL = "http://192.168.3.102:9991/clients/game?WSDL";

//***********************
    private static final String SOAP_ACTION_review = "http://clients.club_friend.com/Hostes/review_Request";
    private static final String METHOD_NAME_photo = "new_client_photo";
    private static final String METHOD_NAME_review = "review_";
    private static final String NAMESPACE_hostes = "http://clients.club_friend.com/";
    private static final String URL_hostes = "http://192.168.3.102:9998/clients/hostes?WSDL";

//***********************


    static String message001 = "Неверно указан телефон друга!Такого номера нет в базе данных.";
    static String message002 = "Данные успешно сохранены.";
    static String message003 = "Ошибка при попытке внести данные в базу данных!";
    static String message004 = "Произошла ошибка!Повторите действия.";
    static String message005 = "Произошла ошибка!Не найден драйвер для подключения к базе данных";
    static String message006 = "Ошибка!Нет соединения с базой данных!";
    static String message007 = "Ошибка при добавлении фото!Не найден клиент,которому оно присваивается!";
    static String message008 = "Фото успешно добавлено в базу данных.";
    static String message009 = "Ошибка при записи фото в базу данных!";
    static String message010 = "Не все данные указаны!";
    static String message011 = "Клиент с таким номером телефона уже существует!Новый клиент не добавлен!";
    static String message012 = "Клиент с таким email уже существует! Новый клиент не добавлен!";
    static String message013 = "Клиент с такими данными не найден!";
    static String message014 = "Друга нет!";
    static String message015 = "";
    static String message016 = "Ошибка!Данные о статусе не найдены!";
    static String message017 = "Ошибка!Данные о балансе не найдены";
    static String message018 = "Введите,пожалуйста,данные!";
    static String message019 = "Нет соединения с сервером!";
    static String message020 = "Введите,пожалуйста, правильно номер телефона друга! Номер должен начинаться с + .";
    static String message021 = "Введите,пожалуйста,  номер телефона.";
    static String message022 = "Номер телефона должен начинаться с  + .";
    static String message023 = "Введите, пожалуйста, дату рождения.";
    static String message024 = "Введите, пожалуйста, имя.";
    static String message025 = "Клиентов с такими данными больше одного!";
    static String message026 = "Изменения успешно сохранены.";
    static String message027 = "Произошла ошибка при удалении!";
    static String message028 = "Не удалось изменить друга, поскольку указанного друга нет в базе данных!";
    static String message029 = "Изменений небыло.";
    static String message030 = "Стол не найден!";
    static String message031 = "Такой стол уже зарегистрирован! Укажите,пожалуйста,другой стол.";
    static String message032 = "Стол зарегистрирован.";
    static String message033 = "Стол закрыт";
    static String message034 = "Ошибка! Не удалось зарегистрировать стол.";
    static String message035 = "Этот стол уже был закрыт!";
    static String message036 = "Ошибка!Не удалось закрыть стол!";
    static String message037 = "Клиент не зарегистрирован ни за одним столом!";
    static String message038 = "Клиент не играет за данным столом!";
    static String message039 = "Клиент в игре";
    static String message040 = "Клиент вышел с игры";
    static String message041 = "Игра остановлена";
    static String message042 = "Не удалось добавить клиента в игру!Он уже зарегистрирован за другим столом.";
    static String message043 = "Не удалось добавить клиента в игру!Он заблокирован.";
    static String message044 = "Произошла ошибка!У клиента нет статуса.";
    static String message045 = "Произошла ошибка!Не удалось зарегистрировать клиента!";
    static String message046 = "Не найден стол за которым зарегистрирован клиент!";
    static String message047 = "Не удалось остановить игру!Повторите попытку.";
    private static int rcount;
    private static int ccount;
    private MyTask_cl_table mt_cl_table;
    private static String tv_number_table_text;
    private ProgressBar pb1;
    private static String[][] rev;
    private MyTask_add_client mt_ac;
    private static String et1;
    private MyTask_add_clientT mt_acT;
    private MyTask_add_clientE mt_acE;
    private ImageView iv1;
    private ImageView iv1_add;
    private Button bt1;
    private static TextView tv1_id;
    private TextView tv1_fio;
    private TextView tv1_bal;
    private MyTask_reg_cl mt_reg_cl;
    private ImageView iv1_pause;
    private static TextView tv1_i;
    private static int vse_ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            quit();
            return true;
        }

        return super.onOptionsItemSelected(item);


    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public static EditText et_wellcome;
        public static ProgressBar pb_wellcome;
        public static Button bt_wellcome;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            et_wellcome=(EditText)getActivity().findViewById(R.id.et_wellcome);
            pb_wellcome=(ProgressBar)getActivity().findViewById(R.id.pb_wellcome);
            bt_wellcome=(Button)getActivity().findViewById(R.id.bt_wellcome);

        }
    }

//=================================
//сообщения об ошибках
//нет имени
public void message(final String mes_s) {

    final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

    // Setting Dialog Title
    alertDialog.setTitle("Сообщение");

    // Setting Dialog Message
    alertDialog.setMessage(mes_s);

    // Setting Icon to Dialog
    alertDialog.setIcon(R.drawable.alert);

    // Setting OK Button
    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            // Write your code here to execute after dialog closed
//            Toast.makeText(getApplicationContext(),
//                    "You clicked on OK", Toast.LENGTH_SHORT).show();
        if (mes_s.compareToIgnoreCase(message032)==0){
            Log.d(TAG,"Новое окошко и назначаем столу номер "+et_wellcome_text);//вносится полученное значение в поле

            // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
            Game newFragment = new Game();
            android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
            if (mes_s.compareToIgnoreCase(message033)==0){
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }


        }
    });

    // Showing Alert Message
    alertDialog.show();
}

    //нет имени
    public void message_cl(final String mes_s) {

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Сообщение");

        // Setting Dialog Message
        alertDialog.setMessage(mes_s);

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.alert);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
//            Toast.makeText(getApplicationContext(),
//                    "You clicked on OK", Toast.LENGTH_SHORT).show();
                
                if(res_ok!=2 && res_ok!=4){
                    iv1.setVisibility(View.INVISIBLE);
                    iv1_add.setVisibility(View.VISIBLE);
                    bt1.setVisibility(View.INVISIBLE);
                    tv1_id.setVisibility(View.INVISIBLE);
                    tv1_fio.setVisibility(View.INVISIBLE);
                    tv1_bal.setVisibility(View.INVISIBLE);
                    tv1_i.setVisibility(View.INVISIBLE);
                    tv1_id.setText("");//вставить id
                    tv1_fio.setText("");//вставить фио
                    tv1_bal.setText("");//вставить баланс
                    tv1_i.setText("");//вставить баланс
                }

                if(res_ok==4){ //pause
                    iv1_pause.setVisibility(View.VISIBLE);
                    bt1.setVisibility(View.INVISIBLE);

                }


            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
//нажать стоп/пауза
    public void stop1(View rootView){
    iv1= Game.iv1;
    iv1_add = Game.iv1_add;
    iv1_pause = Game.iv1_pause;
    bt1 = Game.bt1;
    tv1_id = Game.tv1_id;
    tv1_fio = Game.tv1_fio;
    tv1_bal = Game.tv1_bal;
    tv1_i = Game.tv1_i;
    message_game();
}
    public void stop2(View rootView){
        iv1_pause = Game.iv2_pause;
        iv1= Game.iv2;
        iv1_add = Game.iv2_add;
        bt1 = Game.bt2;
        tv1_id = Game.tv2_id;
        tv1_fio = Game.tv2_fio;
        tv1_bal = Game.tv2_bal;
        tv1_i = Game.tv2_i;
        message_game();
    }
    public void stop3(View rootView){
        iv1_pause = Game.iv3_pause;
        iv1= Game.iv3;
        iv1_add = Game.iv3_add;
        bt1 = Game.bt3;
        tv1_id = Game.tv3_id;
        tv1_fio = Game.tv3_fio;
        tv1_bal = Game.tv3_bal;
        tv1_i = Game.tv3_i;
        message_game();
    }
    public void stop4(View rootView){
        iv1_pause = Game.iv4_pause;
        iv1= Game.iv4;
        iv1_add = Game.iv4_add;
        bt1 = Game.bt4;
        tv1_id = Game.tv4_id;
        tv1_fio = Game.tv4_fio;
        tv1_bal = Game.tv4_bal;
        tv1_i = Game.tv4_i;
        message_game();
    }
    public void stop5(View rootView){
        iv1_pause = Game.iv5_pause;
        iv1= Game.iv5;
        iv1_add = Game.iv5_add;
        bt1 = Game.bt5;
        tv1_id = Game.tv5_id;
        tv1_fio = Game.tv5_fio;
        tv1_bal = Game.tv5_bal;
        tv1_i = Game.tv5_i;
        message_game();
    }
    public void stop6(View rootView){
        iv1_pause = Game.iv6_pause;
        iv1= Game.iv6;
        iv1_add = Game.iv6_add;
        bt1 = Game.bt6;
        tv1_id = Game.tv6_id;
        tv1_fio = Game.tv6_fio;
        tv1_bal = Game.tv6_bal;
        tv1_i = Game.tv6_i;
        message_game();
    }
    public void stop7(View rootView){
        iv1_pause = Game.iv7_pause;
        iv1= Game.iv7;
        iv1_add = Game.iv7_add;
        bt1 = Game.bt7;
        tv1_id = Game.tv7_id;
        tv1_fio = Game.tv7_fio;
        tv1_bal = Game.tv7_bal;
        tv1_i = Game.tv7_i;
        message_game();
    }
    public void stop8(View rootView){
        iv1_pause = Game.iv8_pause;
        iv1= Game.iv8;
        iv1_add = Game.iv8_add;
        bt1 = Game.bt8;
        tv1_id = Game.tv8_id;
        tv1_fio = Game.tv8_fio;
        tv1_bal = Game.tv8_bal;
        tv1_i = Game.tv8_i;
        message_game();
    }
    public void stop9(View rootView){
        iv1_pause = Game.iv9_pause;
        iv1= Game.iv9;
        iv1_add = Game.iv9_add;
        bt1 = Game.bt9;
        tv1_id = Game.tv9_id;
        tv1_fio = Game.tv9_fio;
        tv1_bal = Game.tv9_bal;
        tv1_i = Game.tv9_i;
        message_game();
    }
    public void stop10(View rootView){
        iv1_pause = Game.iv10_pause;
        iv1= Game.iv10;
        iv1_add = Game.iv10_add;
        bt1 = Game.bt10;
        tv1_id = Game.tv10_id;
        tv1_fio = Game.tv10_fio;
        tv1_bal = Game.tv10_bal;
        tv1_i = Game.tv10_i;
        message_game();
    }
    public void message_game(){
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

    // Setting Dialog Title
    alertDialog.setTitle("Сообщение");

    // Setting Dialog Message
    alertDialog.setMessage("Выберите действие");

    // Setting Icon to Dialog
    alertDialog.setIcon(R.drawable.alert);

    // Setting Positive "Yes" Button
    alertDialog.setPositiveButton("Стоп",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //метод для stop игры
                    //берем значение id и его отправляем на сервер
                    //если успешно,то скрываем все его данные в окошке
                    MyTask_stop mt_stop = (MyTask_stop) getLastCustomNonConfigurationInstance();
                    if (mt_stop == null) {
                        mt_stop = new MyTask_stop();
                        mt_stop.execute();
                    }
                }
            });

    // Setting Negative "NO" Button
    alertDialog.setNegativeButton("Пауза",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //метод для pause игры
                    //берем значение id и его отправляем на сервер

                    //если успешно,то появится на фото надпись "Пауза"
                    //и кнопка поменяется на "Продолжить игру"

                    MyTask_pause mt_pause = (MyTask_pause) getLastCustomNonConfigurationInstance();
                    if (mt_pause == null) {
                        mt_pause = new MyTask_pause();
                        mt_pause.execute();
                    }
                   // dialog.cancel();
                }
            });

    // Showing Alert Message
    alertDialog.show();

}

//снять паузу
    public  void pause_to_start1(View rootView){
        iv1= Game.iv1;
        iv1_add = Game.iv1_add;
        iv1_pause = Game.iv1_pause;
        bt1 = Game.bt1;
        tv1_id = Game.tv1_id;
        tv1_fio = Game.tv1_fio;
        tv1_bal = Game.tv1_bal;
        tv1_i = Game.tv1_i;

        MyTask_play mt_pl = (MyTask_play) getLastCustomNonConfigurationInstance();
        if (mt_pl == null) {
            mt_pl = new MyTask_play();
            mt_pl.execute();
        }
        iv1_pause.setVisibility(View.INVISIBLE);
        bt1.setVisibility(View.VISIBLE);
    }
    public  void pause_to_start2(View rootView){
        iv1_pause = Game.iv2_pause;
        iv1= Game.iv2;
        iv1_add = Game.iv2_add;
        bt1 = Game.bt2;
        tv1_id = Game.tv2_id;
        tv1_fio = Game.tv2_fio;
        tv1_bal = Game.tv2_bal;
        tv1_i = Game.tv2_i;

        MyTask_play mt_pl = (MyTask_play) getLastCustomNonConfigurationInstance();
        if (mt_pl == null) {
            mt_pl = new MyTask_play();
            mt_pl.execute();
        }
        iv1_pause.setVisibility(View.INVISIBLE);
        bt1.setVisibility(View.VISIBLE);
    }
    public  void pause_to_start3(View rootView){
        iv1_pause = Game.iv3_pause;
        iv1= Game.iv3;
        iv1_add = Game.iv3_add;
        bt1 = Game.bt3;
        tv1_id = Game.tv3_id;
        tv1_fio = Game.tv3_fio;
        tv1_bal = Game.tv3_bal;
        tv1_i = Game.tv3_i;

        MyTask_play mt_pl = (MyTask_play) getLastCustomNonConfigurationInstance();
        if (mt_pl == null) {
            mt_pl = new MyTask_play();
            mt_pl.execute();
        }
        iv1_pause.setVisibility(View.INVISIBLE);
        bt1.setVisibility(View.VISIBLE);
    }
    public  void pause_to_start4(View rootView){
        iv1_pause = Game.iv4_pause;
        iv1= Game.iv4;
        iv1_add = Game.iv4_add;
        bt1 = Game.bt4;
        tv1_id = Game.tv4_id;
        tv1_fio = Game.tv4_fio;
        tv1_bal = Game.tv4_bal;
        tv1_i = Game.tv4_i;

        MyTask_play mt_pl = (MyTask_play) getLastCustomNonConfigurationInstance();
        if (mt_pl == null) {
            mt_pl = new MyTask_play();
            mt_pl.execute();
        }
        iv1_pause.setVisibility(View.INVISIBLE);
        bt1.setVisibility(View.VISIBLE);
    }
    public  void pause_to_start5(View rootView){
        iv1_pause = Game.iv5_pause;
        iv1= Game.iv5;
        iv1_add = Game.iv5_add;
        bt1 = Game.bt5;
        tv1_id = Game.tv5_id;
        tv1_fio = Game.tv5_fio;
        tv1_bal = Game.tv5_bal;
        tv1_i = Game.tv5_i;

        MyTask_play mt_pl = (MyTask_play) getLastCustomNonConfigurationInstance();
        if (mt_pl == null) {
            mt_pl = new MyTask_play();
            mt_pl.execute();
        }
        iv1_pause.setVisibility(View.INVISIBLE);
        bt1.setVisibility(View.VISIBLE);
    }
    public  void pause_to_start6(View rootView){
        iv1_pause = Game.iv6_pause;
        iv1= Game.iv6;
        iv1_add = Game.iv6_add;
        bt1 = Game.bt6;
        tv1_id = Game.tv6_id;
        tv1_fio = Game.tv6_fio;
        tv1_bal = Game.tv6_bal;
        tv1_i = Game.tv6_i;

        MyTask_play mt_pl = (MyTask_play) getLastCustomNonConfigurationInstance();
        if (mt_pl == null) {
            mt_pl = new MyTask_play();
            mt_pl.execute();
        }
        iv1_pause.setVisibility(View.INVISIBLE);
        bt1.setVisibility(View.VISIBLE);
    }
    public  void pause_to_start7(View rootView){
        iv1_pause = Game.iv7_pause;
        iv1= Game.iv7;
        iv1_add = Game.iv7_add;
        bt1 = Game.bt7;
        tv1_id = Game.tv7_id;
        tv1_fio = Game.tv7_fio;
        tv1_bal = Game.tv7_bal;
        tv1_i = Game.tv7_i;

        MyTask_play mt_pl = (MyTask_play) getLastCustomNonConfigurationInstance();
        if (mt_pl == null) {
            mt_pl = new MyTask_play();
            mt_pl.execute();
        }
        iv1_pause.setVisibility(View.INVISIBLE);
        bt1.setVisibility(View.VISIBLE);
    }
    public  void pause_to_start8(View rootView){
        iv1_pause = Game.iv8_pause;
        iv1= Game.iv8;
        iv1_add = Game.iv8_add;
        bt1 = Game.bt8;
        tv1_id = Game.tv8_id;
        tv1_fio = Game.tv8_fio;
        tv1_bal = Game.tv8_bal;
        tv1_i = Game.tv8_i;

        MyTask_play mt_pl = (MyTask_play) getLastCustomNonConfigurationInstance();
        if (mt_pl == null) {
            mt_pl = new MyTask_play();
            mt_pl.execute();
        }
        iv1_pause.setVisibility(View.INVISIBLE);
        bt1.setVisibility(View.VISIBLE);
    }
    public  void pause_to_start9(View rootView){
        iv1_pause = Game.iv9_pause;
        iv1= Game.iv9;
        iv1_add = Game.iv9_add;
        bt1 = Game.bt9;
        tv1_id = Game.tv9_id;
        tv1_fio = Game.tv9_fio;
        tv1_bal = Game.tv9_bal;
        tv1_i = Game.tv9_i;

        MyTask_play mt_pl = (MyTask_play) getLastCustomNonConfigurationInstance();
        if (mt_pl == null) {
            mt_pl = new MyTask_play();
            mt_pl.execute();
        }
        iv1_pause.setVisibility(View.INVISIBLE);
        bt1.setVisibility(View.VISIBLE);
    }
    public  void pause_to_start10(View rootView){
        iv1_pause = Game.iv10_pause;
        iv1= Game.iv10;
        iv1_add = Game.iv10_add;
        bt1 = Game.bt10;
        tv1_id = Game.tv10_id;
        tv1_fio = Game.tv10_fio;
        tv1_bal = Game.tv10_bal;
        tv1_i = Game.tv10_i;

        MyTask_play mt_pl = (MyTask_play) getLastCustomNonConfigurationInstance();
        if (mt_pl == null) {
            mt_pl = new MyTask_play();
            mt_pl.execute();
        }
        iv1_pause.setVisibility(View.INVISIBLE);
        bt1.setVisibility(View.VISIBLE);
    }
//=================================
TextView tv_ch, tv_q, tv_b;
    static Dialog dialog;
    String newname;
    String sp_selected;
    CheckBox cb;
    Button btndialog;

//разные клиенты
    public void add_cl1(View rootView){
        iv1= Game.iv1;
        iv1_add = Game.iv1_add;
        iv1_pause = Game.iv1_pause;
        bt1 = Game.bt1;
        tv1_id = Game.tv1_id;
        tv1_fio = Game.tv1_fio;
        tv1_bal = Game.tv1_bal;
        tv1_i = Game.tv1_i;
        dialog();
        Log.d(TAG,"res_ok "+res_ok);

        //берем id и по нему включаем таймер
        //метод для включения таймера
    }
    public void add_cl2(View rootView){
        iv1= Game.iv2;
        iv1_add = Game.iv2_add;
        iv1_pause = Game.iv2_pause;
        bt1 = Game.bt2;
        tv1_id = Game.tv2_id;
        tv1_fio = Game.tv2_fio;
        tv1_bal = Game.tv2_bal;
        tv1_i = Game.tv2_i;
        dialog();
    }
    public void add_cl3(View rootView){
        iv1= Game.iv3;
        iv1_add = Game.iv3_add;
        iv1_pause = Game.iv3_pause;
        bt1 = Game.bt3;
        tv1_id = Game.tv3_id;
        tv1_fio = Game.tv3_fio;
        tv1_bal = Game.tv3_bal;
        tv1_i = Game.tv3_i;
        dialog();
    }
    public void add_cl4(View rootView){
        iv1= Game.iv4;
        iv1_add = Game.iv4_add;
        iv1_pause = Game.iv4_pause;
        bt1 = Game.bt4;
        tv1_id = Game.tv4_id;
        tv1_fio = Game.tv4_fio;
        tv1_bal = Game.tv4_bal;
        tv1_i = Game.tv4_i;
        dialog();
    }
    public void add_cl5(View rootView){
        iv1= Game.iv5;
        iv1_add = Game.iv5_add;
        iv1_pause = Game.iv5_pause;
        bt1 = Game.bt5;
        tv1_id = Game.tv5_id;
        tv1_fio = Game.tv5_fio;
        tv1_bal = Game.tv5_bal;
        tv1_i = Game.tv5_i;
        dialog();
    }
    public void add_cl6(View rootView){
        iv1= Game.iv6;
        iv1_add = Game.iv6_add;
        iv1_pause = Game.iv6_pause;
        bt1 = Game.bt6;
        tv1_id = Game.tv6_id;
        tv1_fio = Game.tv6_fio;
        tv1_bal = Game.tv6_bal;
        tv1_i = Game.tv6_i;
        dialog();
    }
    public void add_cl7(View rootView){
        iv1= Game.iv7;
        iv1_add = Game.iv7_add;
        iv1_pause = Game.iv8_pause;
        bt1 = Game.bt7;
        tv1_id = Game.tv7_id;
        tv1_fio = Game.tv7_fio;
        tv1_bal = Game.tv7_bal;
        tv1_i = Game.tv7_i;
        dialog();
    }
    public void add_cl8(View rootView){
        iv1= Game.iv8;
        iv1_add = Game.iv8_add;
        iv1_pause = Game.iv8_pause;
        bt1 = Game.bt8;
        tv1_id = Game.tv8_id;
        tv1_fio = Game.tv8_fio;
        tv1_bal = Game.tv8_bal;
        tv1_i = Game.tv8_i;
        dialog();
    }
    public void add_cl9(View rootView){
        iv1= Game.iv9;
        iv1_add = Game.iv9_add;
        iv1_pause = Game.iv9_pause;
        bt1 = Game.bt9;
        tv1_id = Game.tv9_id;
        tv1_fio = Game.tv9_fio;
        tv1_bal = Game.tv9_bal;
        tv1_i = Game.tv9_i;
        dialog();
    }
    public void add_cl10(View rootView){
        iv1= Game.iv10;
        iv1_add = Game.iv10_add;
        iv1_pause = Game.iv10_pause;
        bt1 = Game.bt10;
        tv1_id = Game.tv10_id;
        tv1_fio = Game.tv10_fio;
        tv1_bal = Game.tv10_bal;
        tv1_i = Game.tv10_i;
        dialog();
    }
 //поиск клиента по
 public void  dialog(){
{

     final Dialog dialog = new Dialog(MainActivity.this, R.style.CenterDialog);

     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
     dialog.setContentView(R.layout.dialog_chocolate);
     dialog.setCancelable(true);

     ArrayList<String> arr_chocolate = new ArrayList<String>();
     arr_chocolate.add("id");
     arr_chocolate.add("Телефон");
     arr_chocolate.add("Email");
     final Spinner sp = (Spinner) dialog.findViewById(R.id.spinner);
     ArrayAdapter<String> adapter_chocolate = new ArrayAdapter<String>(MainActivity.this,
             android.R.layout.simple_spinner_dropdown_item, arr_chocolate);

     sp.setAdapter(adapter_chocolate);
     sp.setOnItemSelectedListener(new MyOnItemSelectedListener());
     final EditText et_q = (EditText) dialog.findViewById(R.id.EditText_Qty);//строка,с которой изымаем данные

     Button btnSave = (Button) dialog.findViewById(R.id.Button_Save);

//     pb1=(ProgressBar)findViewById(R.id.pb1);
     btnSave.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View arg0) {
//             pb1.setVisibility(View.VISIBLE);
             if (et_q.getText().toString().length() > 0)
             {

               //что выполнять после введения данных и нажатия на кнопку"найти"
                 et1 = et_q.getText().toString();

                 Log.d(TAG,"Искать по: " + sp_selected);
                 Log.d(TAG,"Введенные данные: " + et_q.getText().toString());

                 if(sp_selected.compareToIgnoreCase("id")==0) {
                     MyTask_add_client mt_ac = (MyTask_add_client) getLastCustomNonConfigurationInstance();
                     if (mt_ac == null) {
                         mt_ac = new MyTask_add_client();
                         mt_ac.execute();
                     }
                     dialog.dismiss();//выключается диалог
                 }

                 if(sp_selected.compareToIgnoreCase("Телефон")==0) {

                     Pattern pattern = Pattern.compile("^[+]\\d*"); //не уверена нужен ли значек ^
                     Matcher matcher = pattern.matcher(et1);
                     if (matcher.matches()) {

                         MyTask_add_clientT mt_acT = (MyTask_add_clientT) getLastCustomNonConfigurationInstance();
                         if (mt_acT == null) {
                             mt_acT = new MyTask_add_clientT();
                             mt_acT.execute();
                         }
                         dialog.dismiss();//выключается диалог
                     }
                  else {
                     AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                     alertDialogBuilder.setTitle("Ошибка");
                     alertDialogBuilder.setCancelable(true); //if you set this false, the user will not be able to cancel the dialog by clicking BACK button
                     alertDialogBuilder.setMessage("Номер телефона должен начинаться с '+'");
                     alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int which) {

                         }
                     });

                     alertDialogBuilder.show(); //don't forget to show the dialog! It is a common mistake.
                 }
                 }

                 if(sp_selected.compareToIgnoreCase("Email")==0) {
                     MyTask_add_clientE mt_acE = (MyTask_add_clientE) getLastCustomNonConfigurationInstance();
                     if (mt_acE == null) {
                         mt_acE = new MyTask_add_clientE();
                         mt_acE.execute();
                     }
                     dialog.dismiss();//выключается диалог
                 }


             }
             else
             {
                // pb1.setVisibility(View.INVISIBLE);
                 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                 alertDialogBuilder.setTitle("Ошибка");
                 alertDialogBuilder.setCancelable(true); //if you set this false, the user will not be able to cancel the dialog by clicking BACK button
                 alertDialogBuilder.setMessage("Введите,пожалуйста, данные для поиска!");
                 alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {

                     }
                 });

                 alertDialogBuilder.show(); //don't forget to show the dialog! It is a common mistake.
             }

         }
     });

     Button btnCancel = (Button) dialog.findViewById(R.id.Button_Cancel);
     btnCancel.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View arg0) {
             dialog.dismiss();
         }
     });

     dialog.show();
 }
 }
 public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {



        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
            sp_selected = parent.getItemAtPosition(pos).toString();
        }

        public void onNothingSelected(AdapterView parent) {
        }
    }

//**************************************************************************************************
//**************************************************************************************************
//по id
//получаем регистрационные данные в отдельном потоке
    public class MyTask_add_client extends AsyncTask<Void, Void, String[][]> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            rev=new String[1][1];
            Game.pb_game.setVisibility(View.VISIBLE);
            not_act();
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String[][] doInBackground(Void... params) {
            try {
                review();
            } catch (Exception e) {
                rev[0][0]=message019;
                e.printStackTrace();
            }
            return rev;
        }

        @Override
        protected void onPostExecute(String[][] rev) {
            super.onPostExecute(rev);
            //прячем клавиатуру
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            if ((rev[0][0]).compareToIgnoreCase(message019)!=0) {
                if(ccount>1 && rcount>1) {
                    to_game(rev);
                    vse_ok=2;
                    rcl();
                }else{
                    act();
                for (int a = 0; a < rcount; a++) {
                    for (int b = 0; b < ccount; b++) {
                        mes = rev[a][b];
                        Log.d(TAG,"mes="+mes);
                        mes_to_string(mes);

                    }
                   }
                }

            }else{
                 mes_s=message019;
                 message(mes_s);
            }


            Game.pb_game.setVisibility(View.INVISIBLE);

        }
        public Object onRetainNonConfigurationInstance() {
            return mt_ac;
        }

    }

    //метод для получения анкеты
    public static String[][] review() throws Exception {


        SoapObject request = new SoapObject(NAMESPACE_hostes, METHOD_NAME_review);

        PropertyInfo propInfo = new PropertyInfo();
        propInfo.setName("arg0");
        propInfo.setValue(et1);
        propInfo.setType(String.class);
        Log.d(TAG, "создали свойства " + propInfo.toString());


        PropertyInfo f = new PropertyInfo();
        f.setName("arg1");
        f.setValue("");
        f.setType(String.class);


        PropertyInfo i = new PropertyInfo();
        i.setName("arg2");
        i.setValue("");
        i.setType(String.class);


        PropertyInfo t = new PropertyInfo();
        t.setName("arg3");
        t.setValue("");
        t.setType(String.class);


        PropertyInfo e = new PropertyInfo();
        e.setName("arg4");
        e.setValue("");
        e.setType(String.class);


        request.addProperty(f);
        request.addProperty(i);
        request.addProperty(e);
        request.addProperty(t);
        request.addProperty(propInfo);
        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL_hostes);

//это нам не нужно
//        androidHttpTransport.debug = true;
//        androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//        envelope.dotNet = true;
        androidHttpTransport.call(SOAP_ACTION_review, envelope);

        SoapObject Table = (SoapObject) envelope.bodyIn;
        String[][] output = null;
        if (Table != null) {
            SoapObject row = (SoapObject) Table.getProperty(0);
            if (row != null) {
                rcount = Table.getPropertyCount();
                ccount = ((SoapObject) Table.getProperty(0)).getPropertyCount();
                rev = new String[rcount + 1][ccount + 1];
                Log.d(TAG, "Получили " + rcount + " " + ccount);
                for (int a = 0; a < rcount; a++) {
                    for (int b = 0; b < ccount; b++) {
                        rev[a][b] = ((SoapObject) Table.getProperty(a)).getProperty(b).toString();
                        Log.d(TAG, a + " " + b + " получили " + rev[a][b]);
                        if((rev[a][b]).toString().compareToIgnoreCase("015") == 0||(rev[a][b]).toString().compareToIgnoreCase("anyType{}") == 0){
                            rev[a][b]=message015;
                            Log.d(TAG, a + " " + b + " переписали " + rev[a][b]);
                        }


                    }
                }

            }
        }
        // SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        // Log.d(TAG, "Данные отправлены,получен результат :"+resultsRequestSOAP.toString());
        // reg_res=resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();


        return rev;
    }
    //расстановка по местам
    public void to_game(String[][] rev) {
//        String f = rev[0][0];
        String i = rev[1][0];
        String o = rev[2][0];

        if(i.compareToIgnoreCase("")!=0){
         i=i+" ";
        }
        String fio=i+o;

//        String dr = rev[3][0];
//        String reg_c = rev[4][0];
//        String friend0 = rev[8][0];
//        String friend1 =rev[8][1];
//        String friend2 =rev[8][2];
//        String fri=friend0+" "+friend1+" "+friend2;
        String status0 = rev[9][0];
        String status1 =rev[9][1];
        String sta=status0+" - изменен "+status1;
        String balance = rev[10][0];
        String id= rev[11][0];
        String id_="id "+id;
        String bi ="($"+balance+")";
        Log.d(TAG,"image "+rev[5][0].toString());
        if(rev[5][0].compareToIgnoreCase("")!=0) {
            String en = rev[5][0];
            if (en.compareToIgnoreCase("") != 0) {
                byte[] decodedString = Base64.decode(en, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                iv1.setImageBitmap(decodedByte);
            }
        }
        iv1.setVisibility(View.VISIBLE);
        iv1_add.setVisibility(View.INVISIBLE);
        bt1.setVisibility(View.VISIBLE);
        tv1_id.setVisibility(View.VISIBLE);
        tv1_fio.setVisibility(View.VISIBLE);
        tv1_bal.setVisibility(View.VISIBLE);
        tv1_i.setVisibility(View.VISIBLE);
        tv1_id.setText(i.toString());//вставить id
        tv1_fio.setText(o.toString());//вставить фио
        tv1_bal.setText(bi.toString());//вставить баланс
        tv1_i.setText(id_.toString());//вставить баланс

    };

//**************************************************************************************************
//**************************************************************************************************
//по tel
//получаем регистрационные данные в отдельном потоке
    public class MyTask_add_clientT extends AsyncTask<Void, Void, String[][]> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        rev=new String[1][1];
        Game.pb_game.setVisibility(View.VISIBLE);
        //запускаем диалог показывающий что ты работаешь во всю
    }

    @Override
    protected String[][] doInBackground(Void... params) {
        try {
            reviewT();
        } catch (Exception e) {
            rev[0][0]=message019;
            e.printStackTrace();
        }
        return rev;
    }

    @Override
    protected void onPostExecute(String[][] rev) {
        super.onPostExecute(rev);
        //прячем клавиатуру
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        if ((rev[0][0]).compareToIgnoreCase(message019)!=0) {
            if(ccount>1 && rcount>1) {
                to_game(rev);
                vse_ok=2;
                rcl();
            }else{
                for (int a = 0; a < rcount; a++) {
                    for (int b = 0; b < ccount; b++) {
                        mes = rev[a][b];
                        Log.d(TAG,"mes="+mes);
                        mes_to_string(mes);
                    }
                }
            }

        }else{
            mes_s=message019;
            message(mes_s);
        }
        Game.pb_game.setVisibility(View.INVISIBLE);
    }

    public Object onRetainNonConfigurationInstance() {
        return mt_acT;
    }

}
//метод для получения анкеты
    public static String[][] reviewT() throws Exception {


        SoapObject request = new SoapObject(NAMESPACE_hostes, METHOD_NAME_review);

        PropertyInfo propInfo = new PropertyInfo();
        propInfo.setName("arg0");
        propInfo.setValue("");
        propInfo.setType(String.class);
        Log.d(TAG, "создали свойства " + propInfo.toString());


        PropertyInfo f = new PropertyInfo();
        f.setName("arg1");
        f.setValue("");
        f.setType(String.class);


        PropertyInfo i = new PropertyInfo();
        i.setName("arg2");
        i.setValue("");
        i.setType(String.class);


        PropertyInfo t = new PropertyInfo();
        t.setName("arg3");
        t.setValue(et1);
        t.setType(String.class);


        PropertyInfo e = new PropertyInfo();
        e.setName("arg4");
        e.setValue("");
        e.setType(String.class);


        request.addProperty(f);
        request.addProperty(i);
        request.addProperty(e);
        request.addProperty(t);
        request.addProperty(propInfo);
        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL_hostes);

//это нам не нужно
//        androidHttpTransport.debug = true;
//        androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//        envelope.dotNet = true;
        androidHttpTransport.call(SOAP_ACTION_review, envelope);

        SoapObject Table = (SoapObject) envelope.bodyIn;
        String[][] output = null;
        if (Table != null) {
            SoapObject row = (SoapObject) Table.getProperty(0);
            if (row != null) {
                rcount = Table.getPropertyCount();
                ccount = ((SoapObject) Table.getProperty(0)).getPropertyCount();
                rev = new String[rcount + 1][ccount + 1];
                Log.d(TAG, "Получили " + rcount + " " + ccount);
                for (int a = 0; a < rcount; a++) {
                    for (int b = 0; b < ccount; b++) {
                        rev[a][b] = ((SoapObject) Table.getProperty(a)).getProperty(b).toString();
                        Log.d(TAG, a + " " + b + " получили " + rev[a][b]);
                        if((rev[a][b]).toString().compareToIgnoreCase("015") == 0||(rev[a][b]).toString().compareToIgnoreCase("anyType{}") == 0 ){
                            rev[a][b]=message015;
                            Log.d(TAG, a + " " + b + " переписали " + rev[a][b]);
                        }


                    }
                }

            }
        }
        // SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        // Log.d(TAG, "Данные отправлены,получен результат :"+resultsRequestSOAP.toString());
        // reg_res=resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();


        return rev;
    }

//**************************************************************************************************
//**************************************************************************************************
//по email
//получаем регистрационные данные в отдельном потоке
    public class MyTask_add_clientE extends AsyncTask<Void, Void, String[][]> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            rev=new String[1][1];
            Game.pb_game.setVisibility(View.VISIBLE);
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String[][] doInBackground(Void... params) {
            try {
                reviewE();
            } catch (Exception e) {
                rev[0][0]=message019;
                e.printStackTrace();
            }
            return rev;
        }

        @Override
        protected void onPostExecute(String[][] rev) {
            super.onPostExecute(rev);
            //прячем клавиатуру
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            if ((rev[0][0]).compareToIgnoreCase(message019)!=0) {
                if(ccount>1 && rcount>1) {
                    to_game(rev);
                    rcl();
                }else{
                    for (int a = 0; a < rcount; a++) {
                        for (int b = 0; b < ccount; b++) {
                            mes = rev[a][b];
                            Log.d(TAG,"mes="+mes);
                            mes_to_string(mes);

                        }
                    }
                }

            }else{
                mes_s=message019;
                message(mes_s);
            }
            Game.pb_game.setVisibility(View.INVISIBLE);
        }

        public Object onRetainNonConfigurationInstance() {
            return mt_acE;
        }

    }

//метод для получения анкеты
    public static String[][] reviewE() throws Exception {


        SoapObject request = new SoapObject(NAMESPACE_hostes, METHOD_NAME_review);

        PropertyInfo propInfo = new PropertyInfo();
        propInfo.setName("arg0");
        propInfo.setValue("");
        propInfo.setType(String.class);
        Log.d(TAG, "создали свойства " + propInfo.toString());


        PropertyInfo f = new PropertyInfo();
        f.setName("arg1");
        f.setValue("");
        f.setType(String.class);


        PropertyInfo i = new PropertyInfo();
        i.setName("arg2");
        i.setValue("");
        i.setType(String.class);


        PropertyInfo t = new PropertyInfo();
        t.setName("arg3");
        t.setValue("");
        t.setType(String.class);


        PropertyInfo e = new PropertyInfo();
        e.setName("arg4");
        e.setValue(et1);
        e.setType(String.class);


        request.addProperty(f);
        request.addProperty(i);
        request.addProperty(e);
        request.addProperty(t);
        request.addProperty(propInfo);
        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL_hostes);

//это нам не нужно
//        androidHttpTransport.debug = true;
//        androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//        envelope.dotNet = true;
        androidHttpTransport.call(SOAP_ACTION_review, envelope);

        SoapObject Table = (SoapObject) envelope.bodyIn;
        String[][] output = null;
        if (Table != null) {
            SoapObject row = (SoapObject) Table.getProperty(0);
            if (row != null) {
                rcount = Table.getPropertyCount();
                ccount = ((SoapObject) Table.getProperty(0)).getPropertyCount();
                rev = new String[rcount + 1][ccount + 1];
                Log.d(TAG, "Получили " + rcount + " " + ccount);
                for (int a = 0; a < rcount; a++) {
                    for (int b = 0; b < ccount; b++) {
                        rev[a][b] = ((SoapObject) Table.getProperty(a)).getProperty(b).toString();
                        Log.d(TAG, a + " " + b + " получили " + rev[a][b]);
                        if((rev[a][b]).toString().compareToIgnoreCase("015") == 0||(rev[a][b]).toString().compareToIgnoreCase("anyType{}") == 0){
                            rev[a][b]=message015;
                            Log.d(TAG, a + " " + b + " переписали " + rev[a][b]);
                        }


                    }
                }

            }
        }
        return rev;
    }

//=================================================================================================
//=================================================================================================
//*************************************************************************************************
//Добавить клиента в игру
    public void rcl(){
        MyTask_reg_cl mt_rcl = (MyTask_reg_cl) getLastCustomNonConfigurationInstance();
        if (mt_rcl == null) {
            mt_rcl = new MyTask_reg_cl();
            mt_rcl.execute();
        }
    }
//получаем регистрационные данные в отдельном потоке
    public class MyTask_reg_cl extends AsyncTask<String, Void, String> {

        private Object mt_reg_table;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                reg_cl();
            } catch (Exception e) {
                mes="019";
                e.printStackTrace();
            }
            return mes;
        }

        @Override
        protected void onPostExecute(String mes) {
            super.onPostExecute(mes);
            act();
            if (mes.compareToIgnoreCase("019")!=0){
                mes_to_string_cl(mes);
            }else{
                mes_s=message019;
                message_cl(mes_s);
            }

            //PlaceholderFragment.pb_wellcome.setVisibility(View.INVISIBLE);

        }

        public Object onRetainNonConfigurationInstance() {
            return mt_reg_cl;
        }

    }

//метод для регистрации
    public static String reg_cl() throws Exception {
        String cl=rev[11][0];
        String table= String.valueOf(Game.tv_number_table.getText());
        String new_status="play";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_reg_cl);


        PropertyInfo f = new PropertyInfo();
        f.setName("arg0");
        f.setValue(cl);
        f.setType(String.class);


        PropertyInfo t = new PropertyInfo();
        t.setName("arg1");
        t.setValue(table);
        t.setType(String.class);

        PropertyInfo s = new PropertyInfo();
        s.setName("arg2");
        s.setValue(new_status);
        s.setType(String.class);

        request.addProperty(f);
        request.addProperty(t);
        request.addProperty(s);
        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

//это нам не нужно
//        androidHttpTransport.debug = true;
//        androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//        envelope.dotNet = true;
        androidHttpTransport.call(SOAP_ACTION_reg_cl, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        mes = resultsRequestSOAP.toString();
        return mes;

    }
//*************************************************************************************************
//Cнять с паузы
//получаем регистрационные данные в отдельном потоке
    public class MyTask_play extends AsyncTask<String, Void, String> {

    private Object mt_reg_table;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        not_act();
        Game.pb_game.setVisibility(View.VISIBLE);
        //запускаем диалог показывающий что ты работаешь во всю
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            play();
        } catch (Exception e) {
            mes="019";
            e.printStackTrace();
        }
        return mes;
    }

    @Override
    protected void onPostExecute(String mes) {
        super.onPostExecute(mes);
        act();
        if (mes.compareToIgnoreCase("019")!=0){
            mes_to_string_cl(mes);
        }else{
            mes_s=message019;
            message_cl(mes_s);
        }
        Game.pb_game.setVisibility(View.INVISIBLE);
      //  PlaceholderFragment.pb_wellcome.setVisibility(View.INVISIBLE);
    }

    public Object onRetainNonConfigurationInstance() {
        return mt_reg_cl;
    }

}
//метод для регистрации
    public static String play() throws Exception {
        String cl_=String.valueOf(tv1_i.getText());
        String cl=cl_.split(" ")[1];
        String table= String.valueOf(Game.tv_number_table.getText());
        String new_status="play";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_reg_cl);


        PropertyInfo f = new PropertyInfo();
        f.setName("arg0");
        f.setValue(cl);
        f.setType(String.class);


        PropertyInfo t = new PropertyInfo();
        t.setName("arg1");
        t.setValue(table);
        t.setType(String.class);

        PropertyInfo s = new PropertyInfo();
        s.setName("arg2");
        s.setValue(new_status);
        s.setType(String.class);

        request.addProperty(f);
        request.addProperty(t);
        request.addProperty(s);
        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

//это нам не нужно
//        androidHttpTransport.debug = true;
//        androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//        envelope.dotNet = true;
        androidHttpTransport.call(SOAP_ACTION_reg_cl, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        mes = resultsRequestSOAP.toString();
        return mes;

    }
//*************************************************************************************************
//STOP игра
   public class MyTask_stop extends AsyncTask<String, Void, String> {

    private Object mt_reg_table;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        not_act();
        Game.pb_game.setVisibility(View.VISIBLE);
        //запускаем диалог показывающий что ты работаешь во всю
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            stop();
        } catch (Exception e) {
            mes="019";
            e.printStackTrace();
        }
        return mes;
    }

    @Override
    protected void onPostExecute(String mes) {
        super.onPostExecute(mes);
        act();
        if (mes.compareToIgnoreCase("019")!=0){
            mes_to_string_cl(mes);
        }else{
            mes_s=message019;
            message_cl(mes_s);
        }
        Game.pb_game.setVisibility(View.INVISIBLE);
    }

    public Object onRetainNonConfigurationInstance() {
        return mt_reg_table;
    }

}
//метод
    public static String stop() throws Exception {
        String cl_=String.valueOf(tv1_i.getText());
        String cl=cl_.split(" ")[1];
        String table= String.valueOf(Game.tv_number_table.getText());
        String new_status="free";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_reg_cl);


        PropertyInfo f = new PropertyInfo();
        f.setName("arg0");
        f.setValue(cl);
        f.setType(String.class);


        PropertyInfo t = new PropertyInfo();
        t.setName("arg1");
        t.setValue(table);
        t.setType(String.class);

        PropertyInfo s = new PropertyInfo();
        s.setName("arg2");
        s.setValue(new_status);
        s.setType(String.class);

        request.addProperty(f);
        request.addProperty(t);
        request.addProperty(s);
        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

//это нам не нужно
//        androidHttpTransport.debug = true;
//        androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//        envelope.dotNet = true;
        androidHttpTransport.call(SOAP_ACTION_reg_cl, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        mes = resultsRequestSOAP.toString();
        return mes;

    }
//*************************************************************************************************
//PAUSE игра
    public class MyTask_pause extends AsyncTask<String, Void, String> {

        private Object mt_reg_table;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            not_act();
            Game.pb_game.setVisibility(View.VISIBLE);
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                pause();
            } catch (Exception e) {
                mes="019";
                e.printStackTrace();
            }
            return mes;
        }

        @Override
        protected void onPostExecute(String mes) {
            super.onPostExecute(mes);
            act();
            if (mes.compareToIgnoreCase("019")!=0){
                mes_to_string_cl(mes);
            }else{
                mes_s=message019;
                message_cl(mes_s);
            }
            Game.pb_game.setVisibility(View.INVISIBLE);
        }

        public Object onRetainNonConfigurationInstance() {
            return mt_reg_table;
        }

    }
    //метод
    public static String pause() throws Exception {
        String cl_=String.valueOf(tv1_i.getText());
        String cl=cl_.split(" ")[1];
        String table= String.valueOf(Game.tv_number_table.getText());
        String new_status="pause";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_reg_cl);


        PropertyInfo f = new PropertyInfo();
        f.setName("arg0");
        f.setValue(cl);
        f.setType(String.class);


        PropertyInfo t = new PropertyInfo();
        t.setName("arg1");
        t.setValue(table);
        t.setType(String.class);

        PropertyInfo s = new PropertyInfo();
        s.setName("arg2");
        s.setValue(new_status);
        s.setType(String.class);

        request.addProperty(f);
        request.addProperty(t);
        request.addProperty(s);
        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

//это нам не нужно
//        androidHttpTransport.debug = true;
//        androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//        envelope.dotNet = true;
        androidHttpTransport.call(SOAP_ACTION_reg_cl, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        mes = resultsRequestSOAP.toString();
        return mes;

    }
//=================================================================================================
//=================================================================================================
//*************************************************************************************************
//РЕГИСТРИРУЕМ СТОЛ
//Метод для OnClick (регистрация стола)
    public void reg_table_onClic(View rootView) {
        PlaceholderFragment.pb_wellcome.setVisibility(View.VISIBLE);
        //прячем клавиатуру
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        et_wellcome_text = String.valueOf(PlaceholderFragment.et_wellcome.getText());

        if ( et_wellcome_text .compareToIgnoreCase("")!=0) {

                MyTask_reg_table mt_reg_table = (MyTask_reg_table) this.getLastCustomNonConfigurationInstance();
                if (mt_reg_table == null) {
                    mt_reg_table = new MyTask_reg_table();
                    mt_reg_table.execute();

                }

        }else{
            mes_s=message018;
            message(mes_s);
            PlaceholderFragment.pb_wellcome.setVisibility(View.INVISIBLE);

        }

    }

//**************************************************************************************************
//получаем регистрационные данные в отдельном потоке
    public class MyTask_reg_table extends AsyncTask<String, Void, String> {

        private Object mt_reg_table;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PlaceholderFragment.bt_wellcome.setClickable(false);
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                reg_table();
            } catch (Exception e) {
                mes="019";
                e.printStackTrace();
            }
            return mes;
        }

        @Override
        protected void onPostExecute(String mes) {
            super.onPostExecute(mes);
            PlaceholderFragment.bt_wellcome.setClickable(true);
            if (mes.compareToIgnoreCase("019")!=0){
               mes_to_string(mes);
            }else{
                mes_s=message019;
                message(mes_s);
            }

            PlaceholderFragment.pb_wellcome.setVisibility(View.INVISIBLE);
        }

        public Object onRetainNonConfigurationInstance() {
            return mt_reg_table;
        }

    }

//метод для регистрации стола
    public static String reg_table() throws Exception {

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


            PropertyInfo f = new PropertyInfo();
            f.setName("arg0");
            f.setValue(et_wellcome_text);
            f.setType(String.class);


            request.addProperty(f);
            Log.d(TAG, "присвоили свойства " + request.toString());


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

//это нам не нужно
//        androidHttpTransport.debug = true;
//        androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//        envelope.dotNet = true;
            androidHttpTransport.call(SOAP_ACTION, envelope);

            SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
            Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
            mes = resultsRequestSOAP.toString();
            return mes;

    }

//метод для перевода кода сообщения в сообщение и отображение его на экране
    public String mes_to_string1(String mes){

        if ((mes.compareToIgnoreCase("001") == 0)) {
            mes_s = message001;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("002") == 0)) {
            mes_s = message002;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("003") == 0)) {
            mes_s = message003;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("004") == 0)) {
            mes_s = message004;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("005") == 0)) {
            mes_s = message005;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("006") == 0)) {
            mes = message006;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("007") == 0)) {
            mes_s = message007;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("008") == 0)) {
            mes_s = message008;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("009") == 0)) {
            mes_s = message009;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("010") == 0)) {
            mes_s= message010;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("011") == 0)) {
            mes_s = message011;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("012") == 0)) {
            mes_s = message012;
            res_ok = 0;
        }

        if ((mes.compareToIgnoreCase("013") == 0)) {
            mes_s = message013;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("014") == 0)) {
            mes_s = message014;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("015") == 0)) {
            mes_s = message015;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("016") == 0)) {
            mes_s = message016;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("017") == 0)) {
            mes_s = message017;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("018") == 0)) {
            mes_s = message018;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("019") == 0)) {
            mes_s = message019;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("020") == 0)) {
            mes_s = message020;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("021") == 0)) {
            mes_s = message021;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("022") == 0)) {
            mes_s = message022;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("023") == 0)) {
            mes_s = message023;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("024") == 0)) {
            mes_s = message024;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("025") == 0)) {
            mes_s = message025;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("026") == 0)) {
            mes_s = message026;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("027") == 0)) {
            mes_s = message027;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("028") == 0)) {
            mes_s = message028;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("029") == 0)) {
            mes_s = message029;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("030") == 0)) {
            mes_s = message030;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("031") == 0)) {
            mes_s = message031;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("032") == 0)) {
            mes_s = message032;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("033") == 0)) {
            mes_s = message033;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("034") == 0)) {
            mes_s = message034;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("035") == 0)) {
            mes_s = message035;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("036") == 0)) {
            mes_s = message036;
            res_ok = 0;
        }
Log.d(TAG,"res_ok="+res_ok+"mes-"+mes_s);
        return mes_s;
    }

//метод для перевода кода сообщения в сообщение и отображение его на экране
    public String mes_to_string(String mes_s){
        res_ok = 1;
        if ((mes.compareToIgnoreCase("001") == 0)) {
            mes_s = message001;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("002") == 0)) {
            mes_s = message002;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("003") == 0)) {
            mes_s = message003;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("004") == 0)) {
            mes_s = message004;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("005") == 0)) {
            mes_s = message005;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("006") == 0)) {
            mes = message006;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("007") == 0)) {
            mes_s = message007;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("008") == 0)) {
            mes_s = message008;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("009") == 0)) {
            mes_s = message009;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("010") == 0)) {
            mes_s= message010;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("011") == 0)) {
            mes_s = message011;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("012") == 0)) {
            mes_s = message012;
            res_ok = 0;
        }

        if ((mes.compareToIgnoreCase("013") == 0)) {
            mes_s = message013;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("014") == 0)) {
            mes_s = message014;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("015") == 0)) {
            mes_s = message015;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("016") == 0)) {
            mes_s = message016;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("017") == 0)) {
            mes_s = message017;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("018") == 0)) {
            mes_s = message018;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("019") == 0)) {
            mes_s = message019;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("020") == 0)) {
            mes_s = message020;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("021") == 0)) {
            mes_s = message021;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("022") == 0)) {
            mes_s = message022;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("023") == 0)) {
            mes_s = message023;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("024") == 0)) {
            mes_s = message024;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("025") == 0)) {
            mes_s = message025;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("026") == 0)) {
            mes_s = message026;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("027") == 0)) {
            mes_s = message027;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("028") == 0)) {
            mes_s = message028;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("029") == 0)) {
            mes_s = message029;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("030") == 0)) {
            mes_s = message030;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("031") == 0)) {
            mes_s = message031;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("032") == 0)) {
            mes_s = message032;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("033") == 0)) {
            mes_s = message033;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("034") == 0)) {
            mes_s = message034;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("035") == 0)) {
            mes_s = message035;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("036") == 0)) {
            mes_s = message036;
            res_ok = 0;
        }

        if ((mes.compareToIgnoreCase("037") == 0)) {
            mes_s = message037;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("038") == 0)) {
            mes_s = message038;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("039") == 0)) {
            mes_s = message039;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("040") == 0)) {
            mes_s = message040;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("041") == 0)) {
            mes_s = message041;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("042") == 0)) {
            mes_s = message042;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("043") == 0)) {
            mes_s = message043;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("044") == 0)) {
            mes_s = message044;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("045") == 0)) {
            mes_s = message045;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("046") == 0)) {
            mes_s = message046;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("047") == 0)) {
            mes_s = message047;
            res_ok = 0;
        }

        Log.d(TAG,"в методе "+mes_s);
        message(mes_s);//показываем сообщение

        return mes_s;
    }

//метод для перевода кода сообщения в сообщение и отображение его на экране
    public String mes_to_string_cl(String mes_s){
        res_ok = 1;
        if ((mes.compareToIgnoreCase("001") == 0)) {
            mes_s = message001;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("002") == 0)) {
            mes_s = message002;
            res_ok = 2;
        }
        if ((mes.compareToIgnoreCase("003") == 0)) {
            mes_s = message003;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("004") == 0)) {
            mes_s = message004;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("005") == 0)) {
            mes_s = message005;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("006") == 0)) {
            mes = message006;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("007") == 0)) {
            mes_s = message007;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("008") == 0)) {
            mes_s = message008;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("009") == 0)) {
            mes_s = message009;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("010") == 0)) {
            mes_s= message010;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("011") == 0)) {
            mes_s = message011;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("012") == 0)) {
            mes_s = message012;
            res_ok = 0;
        }

        if ((mes.compareToIgnoreCase("013") == 0)) {
            mes_s = message013;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("014") == 0)) {
            mes_s = message014;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("015") == 0)) {
            mes_s = message015;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("016") == 0)) {
            mes_s = message016;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("017") == 0)) {
            mes_s = message017;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("018") == 0)) {
            mes_s = message018;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("019") == 0)) {
            mes_s = message019;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("020") == 0)) {
            mes_s = message020;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("021") == 0)) {
            mes_s = message021;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("022") == 0)) {
            mes_s = message022;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("023") == 0)) {
            mes_s = message023;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("024") == 0)) {
            mes_s = message024;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("025") == 0)) {
            mes_s = message025;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("026") == 0)) {
            mes_s = message026;
            res_ok = 2;
        }
        if ((mes.compareToIgnoreCase("027") == 0)) {
            mes_s = message027;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("028") == 0)) {
            mes_s = message028;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("029") == 0)) {
            mes_s = message029;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("030") == 0)) {
            mes_s = message030;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("031") == 0)) {
            mes_s = message031;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("032") == 0)) {
            mes_s = message032;
            res_ok = 2;
        }
        if ((mes.compareToIgnoreCase("033") == 0)) {
            mes_s = message033;
            res_ok = 2;
        }
        if ((mes.compareToIgnoreCase("034") == 0)) {
            mes_s = message034;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("035") == 0)) {
            mes_s = message035;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("036") == 0)) {
            mes_s = message036;
            res_ok = 0;
        }

        if ((mes.compareToIgnoreCase("037") == 0)) {
            mes_s = message037;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("038") == 0)) {
            mes_s = message038;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("039") == 0)) {
            mes_s = message039;
            res_ok = 2;
        }
        if ((mes.compareToIgnoreCase("040") == 0)) {
            mes_s = message040;
            res_ok = 3;
        }
        if ((mes.compareToIgnoreCase("041") == 0)) {
            mes_s = message041;
            res_ok = 4;
        }
        if ((mes.compareToIgnoreCase("042") == 0)) {
            mes_s = message042;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("043") == 0)) {
            mes_s = message043;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("044") == 0)) {
            mes_s = message044;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("045") == 0)) {
            mes_s = message045;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("046") == 0)) {
            mes_s = message046;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("047") == 0)) {
            mes_s = message047;
            res_ok = 0;
        }

        Log.d(TAG,"в методе "+mes_s);
        message_cl(mes_s);//показываем сообщение

        return mes_s;
    }
//*************************************************************************************************
//=================================
//=================================
//*************************************************************************************************
//Table
//Метод для OnClick (закрытие стола)
public void cl_table_onClic() {
    tv_number_table_text= String.valueOf(Game.tv_number_table.getText());
    Log.d(TAG,"tv_number_table_text="+tv_number_table_text);

        MyTask_cl_table mt_cl_table = (MyTask_cl_table) this.getLastCustomNonConfigurationInstance();
        if (mt_cl_table == null) {
            mt_cl_table = new MyTask_cl_table();
            mt_cl_table.execute();

        }


}

//**************************************************************************************************
//получаем результат закрытия
    public class MyTask_cl_table extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Game.pb_game.setVisibility(View.VISIBLE);
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                cl_table();
            } catch (Exception e) {
                mes="019";
                e.printStackTrace();
            }
            return mes;
        }

        @Override
        protected void onPostExecute(String mes) {
            super.onPostExecute(mes);

            if (mes.compareToIgnoreCase("019")!=0){
                mes_to_string(mes);
            }else{
                mes_s=message019;
                message(mes_s);
            }
            Game.pb_game.setVisibility(View.INVISIBLE);
        }

        public Object onRetainNonConfigurationInstance() {
            return mt_cl_table;
        }

    }

    //метод для закрытия стола
    public String cl_table() throws Exception {

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_out);


        PropertyInfo f = new PropertyInfo();
        f.setName("arg0");
        f.setValue(tv_number_table_text);
        f.setType(String.class);


        request.addProperty(f);
        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

//это нам не нужно
//        androidHttpTransport.debug = true;
//        androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//        envelope.dotNet = true;
        androidHttpTransport.call(SOAP_ACTION_out, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        mes = resultsRequestSOAP.toString();
        return mes;

    }
//===================
//все кнопки неактивные //запретить нажатие
    public void not_act(){
        Log.d(TAG,"Неактивные кнопки");
        Game.iv1_add.setClickable(false);
        Game.iv1_pause.setClickable(false);
        Game.bt1.setClickable(false);

        Game.iv2_add.setClickable(false);
        Game.iv2_pause.setClickable(false);
        Game.bt2.setClickable(false);

        Game.iv3_add.setClickable(false);
        Game.iv3_pause.setClickable(false);
        Game.bt3.setClickable(false);

        Game.iv4_add.setClickable(false);
        Game.iv4_pause.setClickable(false);
        Game.bt4.setClickable(false);

        Game.iv5_add.setClickable(false);
        Game.iv5_pause.setClickable(false);
        Game.bt5.setClickable(false);

        Game.iv6_add.setClickable(false);
        Game.iv6_pause.setClickable(false);
        Game.bt6.setClickable(false);

        Game.iv7_add.setClickable(false);
        Game.iv7_pause.setClickable(false);
        Game.bt7.setClickable(false);

        Game.iv8_add.setClickable(false);
        Game.iv8_pause.setClickable(false);
        Game.bt8.setClickable(false);

        Game.iv9_add.setClickable(false);
        Game.iv9_pause.setClickable(false);
        Game.bt9.setClickable(false);

        Game.iv10_add.setClickable(false);
        Game.iv10_pause.setClickable(false);
        Game.bt10.setClickable(false);
    }
//все кнопки неактивные //запретить нажатие
    public void act(){
        Log.d(TAG,"Активные кнопки");
        Game.iv1_add.setClickable(true);
        Game.iv1_pause.setClickable(true);
        Game.bt1.setClickable(true);

        Game.iv2_add.setClickable(true);
        Game.iv2_pause.setClickable(true);
        Game.bt2.setClickable(true);

        Game.iv3_add.setClickable(true);
        Game.iv3_pause.setClickable(true);
        Game.bt3.setClickable(true);

        Game.iv4_add.setClickable(true);
        Game.iv4_pause.setClickable(true);
        Game.bt4.setClickable(true);

        Game.iv5_add.setClickable(true);
        Game.iv5_pause.setClickable(true);
        Game.bt5.setClickable(true);

        Game.iv6_add.setClickable(true);
        Game.iv6_pause.setClickable(true);
        Game.bt6.setClickable(true);

        Game.iv7_add.setClickable(true);
        Game.iv7_pause.setClickable(true);
        Game.bt7.setClickable(true);

        Game.iv8_add.setClickable(true);
        Game.iv8_pause.setClickable(true);
        Game.bt8.setClickable(true);

        Game.iv9_add.setClickable(true);
        Game.iv9_pause.setClickable(true);
        Game.bt9.setClickable(true);

        Game.iv10_add.setClickable(true);
        Game.iv10_pause.setClickable(true);
        Game.bt10.setClickable(true);
    }

//===================
    @Override
    public void onBackPressed() {
        quit();

    }

    public void quit(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Выйти?");

        alertDialog.setMessage("Вы действительно хотите закрыть стол?");

        alertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                //проверить нет ли клиентов за столом
                String one=String.valueOf(Game.tv1_i.getText());
                String two=String.valueOf(Game.tv2_i.getText());
                String tree=String.valueOf(Game.tv3_i.getText());
                String four=String.valueOf(Game.tv4_i.getText());
                String five=String.valueOf(Game.tv5_i.getText());
                String six=String.valueOf(Game.tv6_i.getText());
                String seven=String.valueOf(Game.tv7_i.getText());
                String eight=String.valueOf(Game.tv8_i.getText());
                String nine=String.valueOf(Game.tv9_i.getText());
                String ten=String.valueOf(Game.tv10_i.getText());
                Log.d(TAG,"проверка....");
                String[]quit;
                quit = new String[]{one, two, tree,four,five,six,seven,eight,nine,ten};
                int zero=0;
                for(int q=0;q<10;q++){
                    Log.d(TAG," "+quit[q].toString());
                    if(quit[q].compareToIgnoreCase("")!=0){
                        zero++;
                    }
                }
                if(zero==0) {
                    //закрыть стол
                    cl_table_onClic();
                }else{
                    mes_s="За столом есть игроки!";
                    message(mes_s);
                }
                // dialog.cancel();

            }
        });

        alertDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
        return;
    }

}
