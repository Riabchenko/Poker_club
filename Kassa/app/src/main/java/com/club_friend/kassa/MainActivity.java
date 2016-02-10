package com.club_friend.kassa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.club_friend.kassa.fragments.ScreenOne;
import com.club_friend.kassa.fragments.ScreenThree;
import com.club_friend.kassa.fragments.ScreenThree_email;
import com.club_friend.kassa.fragments.ScreenThree_fio;
import com.club_friend.kassa.fragments.ScreenThree_id;
import com.club_friend.kassa.fragments.ScreenThree_tel;
import com.club_friend.kassa.fragments.ScreenThree_two_table;
import com.club_friend.kassa.fragments.ScreenTwo;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.club_friend.kassa.R.id.drawer_layout;

public class MainActivity extends ActionBarActivity implements ScreenThree.onSomeEventListener,Serializable {

    private static int id_new_client;
    private static String ok;
    private static byte[] b64;
    private static int reg_ok;
    private static String photo_res;
    private static String reg_res;
    private static Object anketa_by_id;
    public static int ccount;
    public static int rcount;
    private static String edit_res;
    private static int edit_ok=1;
    private static String[] res_out;
    private static String[][] res_out1;
    public static String[] res_game0;
    public static int count_game0;
    private static String to_text;
    private static String res_in;
    private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    public static Object save;
    private static String result, result_photo;
    public static ByteArrayOutputStream blob;

    private String drMonth;
    private String drDay;
    public String drYear;
    public static String dr_string;

    private static int  Kalendar;

    private static String reg;

    public static String reg_f_text, reg_i_text, reg_o_text, reg_dr_text, reg_tel_text, reg_email_text, reg_friend_text;

    private static final String SOAP_ACTION_review = "http://clients.club_friend.com/Hostes/review_Request";
    private static final String SOAP_ACTION_out = "http://clients.club_friend.com/Kassa/out_with_rRequest";
    private static final String SOAP_ACTION_table = "http://clients.club_friend.com/Kassa/howRequest";
    private static final String SOAP_ACTION_how_to1 = "http://clients.club_friend.com/Kassa/how_to1Request";
    private static final String SOAP_ACTION_in = "http://clients.club_friend.com/Kassa/in_tableResponse";
    private static final String METHOD_NAME_review = "review_";
    private static final String METHOD_NAME_out = "out_with_r";
    private static final String METHOD_NAME_table = "how";
    private static final String METHOD_NAME_how_to1 = "how_to1";
    private static final String METHOD_NAME_in = "in_table";
    private static final String NAMESPACE = "http://clients.club_friend.com/";
    private static final String URL = "http://192.168.3.102:9998/clients/hostes?WSDL";
    private static final String URL_out = "http://192.168.3.102:9997/clients/kassa?WSDL";


    private static Fragment fragment;

    public Bitmap theImage = null;
    public static int Camera_my = 0;

    private static byte[] bitmapdata;

    public static final String LOG_TAG = "my";
    private byte[] bitmapdata_;
    private static String s64;
    private int proverka;

    private static String forma;
    public static String[][] rev;
    private String dr_string1;
    private MyTask_review mt_rev;


    private static String anketa_by_tel_text;
    private String anketa_fio_f_text;
    private String anketa_fio_i_text;
    private static String anketa_by_email_text;
    private static String anketa_by_id_text;

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
    static String message049 = "Проверьте снимаемую сумму! Она не может быть меньше '0' или превышать баланс";
    private String anketa_by_id_text1;
    private String[][] rev1;
    private ScreenThree_id newFragment;

    public static final String TAG = "my";
    private Bitmap v2;
    private static String anketa_by_f_text;
    private static String anketa_by_i_text;
    private int E=0;
    private static String ank_email_id_text,ank_email_balance_text;
    private static String ank_id_id_text,ank_id_balance_text;
    public static String eT_id_text;
    private static String[] r_out;
    private static ProgressBar pb_all;
    private static TextView bal_all;
    private static TextView id_all;
    private static EditText out_all;
    private static TextView textPoint_t;
    private String to;
    private static String[][] in_table;

    //**************************************************************************************************
//**************************************************************************************************
//ACTIVITY
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mScreenTitles = getResources().getStringArray(R.array.screen_array);
        mDrawerLayout = (DrawerLayout) findViewById(drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mScreenTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Initialize the first fragment when the application first loads.
        if (savedInstanceState == null) {
            selectItem(0);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        //прячем клавиатуру
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_search).setVisible(!drawerOpen);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //прячем клавиатуру
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch (item.getItemId()) {
            case R.id.action_search:
//                // Show toast about click.
//                Toast.makeText(this, R.string.action_search, Toast.LENGTH_SHORT).show();

               onBackPressed();
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


//**************************************************************************************************
    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

//**************************************************************************************************
//**************************************************************************************************
//SCREEN_THREE
//переход между фрагментами через меню
    private void selectItem(int position) {
        // Update the main content by replacing fragments
        fragment = null;
        switch (position) {
            case 0:
                fragment = new ScreenOne();

                break;
            case 1:
                fragment = new ScreenTwo();
                break;
            case 2:
                fragment = new ScreenThree();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            setTitle(mScreenTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

//**************************************************************************************************
//выполнение действий при выборе на ScreenThree
    public void someEvent(int a) {

        if (a == 1) {
            ScreenThree_id newFragment = new ScreenThree_id();
            android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();


        }
        if (a == 3) {
            ScreenThree_email newFragment2 = new ScreenThree_email();
            android.app.FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
            transaction2.replace(R.id.content_frame, newFragment2);
            transaction2.addToBackStack(null);
            transaction2.commit();

        }
        if (a == 4) {
            ScreenThree_fio newFragment3 = new ScreenThree_fio();
            android.app.FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
            transaction3.replace(R.id.content_frame, newFragment3);
            transaction3.addToBackStack(null);
            transaction3.commit();

        }
        if (a == 2) {
            ScreenThree_tel newFragment4 = new ScreenThree_tel();
            android.app.FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
            transaction4.replace(R.id.content_frame, newFragment4);
            transaction4.addToBackStack(null);
            transaction4.commit();

        }

        if (newFragment != null) {
            android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
        }
    }

//=====================================================================
//возврат на главный ScreenThree
    public void click_back(View rootView) {
//       rootView.startAnimation(ScreenThree_email.animTranslate);

//        getFragmentManager().popBackStack(); вернуть назад!!!!!!!

        fragment = new ScreenThree();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment).commit();
        Log.d(TAG, "зашли в метод click");
        //прячем клавиатуру
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


//возврат на ScreenTwo
//возврат на главный ScreenThree
    public void click_backTwo() {
//       rootView.startAnimation(ScreenThree_email.animTranslate);

//        getFragmentManager().popBackStack(); вернуть назад!!!!!!!

    fragment = new ScreenTwo();
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment).commit();
    Log.d(TAG, "зашли в метод click");
    //прячем клавиатуру
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
}


    public void bebe(){
        fragment = new ScreenThree();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment).commit();
        Log.d(TAG, "зашли в метод click");

    }


//**************************************************************************************************
//**************************************************************************************************
//SCREEN_THREE_id
//Метод для OnClick (просмотр анкеты)
    public void review_onClic(View rootView) {
        ScreenThree_id.pb_id.setVisibility(View.VISIBLE);
        Log.d(TAG, "Включили прогресс бар");

        anketa_by_id_text = String.valueOf(ScreenThree_id.a_by_id.getText());
        Log.d(TAG, "id =" + anketa_by_id_text);
//

        ScreenThree_id.ank_id_id.setText("");
        ScreenThree_id.ank_id_f.setText("");
        ScreenThree_id.ank_id_i.setText("");
        ScreenThree_id.ank_id_o.setText("");
        ScreenThree_id.ank_id_dr.setText("");
        ScreenThree_id.ank_id_reg.setText("");
        ScreenThree_id.ank_id_friend.setText("");
        ScreenThree_id.ank_id_status.setText("");
        ScreenThree_id.ank_id_balance.setText("");
        ScreenThree_id.ank_id_f2.removeAllViews();
        ScreenThree_id.ank_id_f3.removeAllViews();

        Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
        ScreenThree_id.ank_id_picture.setImageURI(path);

        if (anketa_by_id_text.compareToIgnoreCase("")!=0) {

            MyTask_review mt_rev = (MyTask_review) this.getLastCustomNonConfigurationInstance();
            if (mt_rev == null) {
                mt_rev = new MyTask_review();
                mt_rev.execute();


            }
        }else{
           forma=message018;
            error(forma);
            ScreenThree_id.pb_id.setVisibility(View.INVISIBLE);

        }

    }

//**************************************************************************************************
//**************************************************************************************************
//получаем регистрационные данные в отдельном потоке
    public class MyTask_review extends AsyncTask<Void, Void, String[][]> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            rev=new String[1][1];
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
            if ((rev[0][0]).compareToIgnoreCase(message019)!=0){

                String mes=rev[0][0];
                Message_to_string.mes_to_string(mes);
                String reg=Message_to_string.mes_s;

                if(Message_to_string.res_ok==0){
                    forma=reg;
                    error(forma);
                }

                else{
                    to_anketa(rev);
                }
            }else{
                reg=message019;
                message(reg);
            }
            //прячем клавиатуру
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            ScreenThree_id.pb_id.setVisibility(View.INVISIBLE);
        }

        public Object onRetainNonConfigurationInstance() {
            return mt_rev;
        }

    }

    //метод для получения анкеты
    public static String[][] review() throws Exception {


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_review);

        PropertyInfo propInfo = new PropertyInfo();
        propInfo.setName("arg0");
        propInfo.setValue(anketa_by_id_text);
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
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

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
    public void to_anketa(String[][] rev) {
        String f = rev[0][0];
        String i = rev[1][0];
        String o = rev[2][0];
        String dr = rev[3][0];
        String reg_c = rev[4][0];
            String friend0 = rev[8][0];
            String friend1 =rev[8][1];
            String friend2 =rev[8][2];
        String fri=friend0+" "+friend1+" "+friend2;
            String status0 = rev[9][0];
            String status1 =rev[9][1];
        String sta=status0+" - изменен "+status1;
        String balance = rev[10][0];
//
        ScreenThree_id.ank_id_id.setText(anketa_by_id_text);
        ScreenThree_id.a_by_id.setText(anketa_by_id_text);
        ScreenThree_id.ank_id_f.setText(f.toString());
        ScreenThree_id.ank_id_i.setText(i.toString());
        ScreenThree_id.ank_id_o.setText(o.toString());
        ScreenThree_id.ank_id_dr.setText(dr.toString());
        ScreenThree_id.ank_id_reg.setText(reg_c.toString());
        ScreenThree_id.ank_id_friend.setText(fri.toString());
        ScreenThree_id.ank_id_status.setText(sta.toString());
        ScreenThree_id.ank_id_balance.setText(balance.toString());
//телефон
        String[] from = new String[rcount + 1];
        for (int x = 0; x < ccount; x++) {
            from[x] = rev[6][x];
            String te=rev[6][x];

            if(te.compareToIgnoreCase("")!=0){

            // создаём текстовое предстввление
            TextView textPoint = new TextView(getApplicationContext());
            textPoint.setTextColor(0xFF000000);
            textPoint.setTextSize(20);

          //  textPoint.setEnabled(true);
            textPoint.setMinLines(1);
            textPoint.setMaxLines(20);
            textPoint.setText(te.toString());

            ScreenThree_id.ank_id_f2.addView(textPoint);
            }
        }


//email
       // String[] from1 = new String[rcount + 1];
        for (int x = 0; x < ccount; x++) {
          //  from[x] = rev[7][x];
            String te=rev[7][x];

            if(te.compareToIgnoreCase("")!=0){

                // создаём текстовое предстввление
                TextView textPoint1 = new TextView(getApplicationContext());
                textPoint1.setTextColor(0xFF000000);
                textPoint1.setTextSize(20);

                //  textPoint.setEnabled(true);
                textPoint1.setMinLines(1);
                textPoint1.setMaxLines(20);
                textPoint1.setText(te.toString());

                ScreenThree_id.ank_id_f3.addView(textPoint1);

            }
        }
        String en=rev[5][0];
        if(en.compareToIgnoreCase("")!=0) {
            byte[] decodedString = Base64.decode(en, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            ScreenThree_id.ank_id_picture.setImageBitmap(decodedByte);
        }
    };

//**************************************************************************************************
//**************************************************************************************************
//SCREEN_THREE_tel
//Метод для OnClick (просмотр анкеты)
    public void by_tel_review_onClic(View rootView) {
        ScreenThree_tel.pb_tel.setVisibility(View.VISIBLE);

        anketa_by_tel_text = String.valueOf(ScreenThree_tel.a_by_tel.getText());
        ScreenThree_tel.ank_tel_id.setText("");
        ScreenThree_tel.ank_tel_f.setText("");
        ScreenThree_tel.ank_tel_i.setText("");
        ScreenThree_tel.ank_tel_o.setText("");
        ScreenThree_tel.ank_tel_dr.setText("");
        ScreenThree_tel.ank_tel_reg.setText("");
        ScreenThree_tel.ank_tel_friend.setText("");
        ScreenThree_tel.ank_tel_status.setText("");
        ScreenThree_tel.ank_tel_balance.setText("");
        ScreenThree_tel.ank_tel_f2.removeAllViews();
        ScreenThree_tel.ank_tel_f3.removeAllViews();

        Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
        ScreenThree_tel.ank_tel_picture.setImageURI(path);

        if (anketa_by_tel_text.compareToIgnoreCase("")!=0) {
            Pattern pattern = Pattern.compile("^[+]\\d*"); //не уверена нужен ли значек ^
            Matcher matcher = pattern.matcher(anketa_by_tel_text);
            if (matcher.matches()) {



                MyTask_review_byTel mt_rev_byTel = (MyTask_review_byTel) this.getLastCustomNonConfigurationInstance();
                if (mt_rev_byTel == null) {
                    mt_rev_byTel = new MyTask_review_byTel();
                    mt_rev_byTel.execute();


                }
            } else {
                proverka = 0;
                forma = message022;
                error(forma);
                ScreenThree_tel.pb_tel.setVisibility(View.INVISIBLE);
                //  Toast.makeText(MainActivity.this, "Номер телефона должен начинаться с  + ", Toast.LENGTH_LONG).show();
            }
        }else{
            forma=message018;
            error(forma);
            ScreenThree_tel.pb_tel.setVisibility(View.INVISIBLE);

        }

    }

//**********************************************************************************************
//**************************************************************************************************
//получаем регистрационные данные в отдельном потоке
    public class MyTask_review_byTel extends AsyncTask<Void, Void, String[][]> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            rev=new String[1][1];
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String[][] doInBackground(Void... params) {
            try {
                review_byTel();
            } catch (Exception e) {
                rev[0][0]=message019;
                e.printStackTrace();
            }
            return rev;
        }

        @Override
        protected void onPostExecute(String[][] rev) {
            super.onPostExecute(rev);
            if ((rev[0][0]).compareToIgnoreCase(message019)!=0){
                String mes=rev[0][0];
                Message_to_string.mes_to_string(mes);
                String reg=Message_to_string.mes_s;
                if(Message_to_string.res_ok==0){
                    forma=reg;
                    error(forma);
                }
                else{
                    to_anketa_byTel(rev);
                }
            }else{
                reg=message019;
                message(reg);
            }
            //прячем клавиатуру
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            ScreenThree_tel.pb_tel.setVisibility(View.INVISIBLE);
        }

//        public Object onRetainNonConfigurationInstance() {
//            return mt_rev_byTel;
//        }

    }

    //метод для получения анкеты
    public static String[][] review_byTel() throws Exception {


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_review);

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
        t.setValue(anketa_by_tel_text);
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
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

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
    public void to_anketa_byTel(String[][] rev) {

        String id_ = rev[11][0];
        String f = rev[0][0];
        String i = rev[1][0];
        String o = rev[2][0];
        String dr = rev[3][0];
        String reg_c = rev[4][0];
        String friend0 = rev[8][0];
        String friend1 =rev[8][1];
        String friend2 =rev[8][2];
        String fri=friend0+" "+friend1+" "+friend2;
        String status0 = rev[9][0];
        String status1 =rev[9][1];
        String sta=status0+" - изменен "+status1;
        String balance = rev[10][0];
//
        if(rev[6][0]!=null){
            String em_past= rev[6][0];
            ScreenThree_tel.a_by_tel.setText(em_past);
        }

        ScreenThree_tel.ank_tel_id.setText(id_);
        ScreenThree_tel.ank_tel_f.setText(f.toString());
        ScreenThree_tel.ank_tel_i.setText(i.toString());
        ScreenThree_tel.ank_tel_o.setText(o.toString());
        ScreenThree_tel.ank_tel_dr.setText(dr.toString());
        ScreenThree_tel.ank_tel_reg.setText(reg_c.toString());
        ScreenThree_tel.ank_tel_friend.setText(fri.toString());
        ScreenThree_tel.ank_tel_status.setText(sta.toString());
        ScreenThree_tel.ank_tel_balance.setText(balance.toString());
//телефон
        String[] from = new String[rcount + 1];
        for (int x = 0; x < ccount; x++) {
            from[x] = rev[6][x];
            String te=rev[6][x];

            if(te.compareToIgnoreCase("")!=0){

                // создаём текстовое предстввление
                TextView textPoint = new TextView(getApplicationContext());
                // textPoint.setGravity(Gravity.LEFT);
                // textPoint.setPadding(0,0,0,0);
                // textPoint.setBackgroundColor(0xFFFBFF4D);
                textPoint.setTextColor(0xFF000000);
                textPoint.setTextSize(20);

                //  textPoint.setEnabled(true);
                textPoint.setMinLines(1);
                textPoint.setMaxLines(20);
                textPoint.setText(te.toString());

                ScreenThree_tel.ank_tel_f2.addView(textPoint);
            }
        }


//email
        // String[] from1 = new String[rcount + 1];
        for (int x = 0; x < ccount; x++) {
            //  from[x] = rev[7][x];
            String te=rev[7][x];

            if(te.compareToIgnoreCase("")!=0){

                // создаём текстовое предстввление
                TextView textPoint1 = new TextView(getApplicationContext());
                // textPoint.setGravity(Gravity.LEFT);
                // textPoint.setPadding(0,0,0,0);
                // textPoint.setBackgroundColor(0xFFFBFF4D);
                textPoint1.setTextColor(0xFF000000);
                textPoint1.setTextSize(20);

                //  textPoint.setEnabled(true);
                textPoint1.setMinLines(1);
                textPoint1.setMaxLines(20);
                textPoint1.setText(te.toString());

                ScreenThree_tel.ank_tel_f3.addView(textPoint1);

            }
        }
        String en=rev[5][0];
        if(en.compareToIgnoreCase("")!=0) {
            byte[] decodedString = Base64.decode(en, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            ScreenThree_tel.ank_tel_picture.setImageBitmap(decodedByte);
        }
//        ArrayList<String> simplifiedList = new ArrayList<String>();
//        for(int c =0;c<ccount;c++){
//            simplifiedList.add(rev[6][c]);



//        for(int y=0;y<simplifiedList.size();y++)
//        {
//
// //

//        // создаем адаптер
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, names);
//
//        // присваиваем адаптер списку
//        ScreenThree_id.ank_id_f2.setAdapter(adapter);
////        ScreenThree_id.ank_id_f2.setAdapter(new ArrayAdapter<String>(this,
////                android.R.layout.simple_list_item_1, names));
//
//        Log.d(TAG," телефон "+simplifiedList);
        // ScreenThree_id.ank_id_f2.setOnItemClickListener();

//        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, albums));
//        TextView t = (TextView) findViewById(R.id.albumName);
//        t.setText(setRes);
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, simplifiedList);
//        ScreenThree_id.ank_id_f2.setAdapter(arrayAdapter);
    };
//**********************************************************************************************
//**************************************************************************************************
//SCREEN_THREE_email
//Метод для OnClick (просмотр анкеты)
    public void by_email_review_onClic(View rootView) {
        ScreenThree_email.pb_email.setVisibility(View.VISIBLE);

        anketa_by_email_text = String.valueOf(ScreenThree_email.a_by_email.getText());

        ScreenThree_email.ank_email_id.setText("");
        ScreenThree_email.ank_email_f.setText("");
        ScreenThree_email.ank_email_i.setText("");
        ScreenThree_email.ank_email_o.setText("");
        ScreenThree_email.ank_email_dr.setText("");
        ScreenThree_email.ank_email_reg.setText("");
        ScreenThree_email.ank_email_friend.setText("");
        ScreenThree_email.ank_email_status.setText("");
        ScreenThree_email.ank_email_balance.setText("");
        ScreenThree_email.ank_email_f2.removeAllViews();
        ScreenThree_email.ank_email_f3.removeAllViews();

        Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
        ScreenThree_email.ank_email_picture.setImageURI(path);

        if (anketa_by_email_text.compareToIgnoreCase("")!=0) {

            MyTask_review_byEmail  mt_rev_byEmail  = (MyTask_review_byEmail) this.getLastCustomNonConfigurationInstance();
            if (mt_rev_byEmail  == null) {
                mt_rev_byEmail  = new MyTask_review_byEmail();
                mt_rev_byEmail.execute();
            }

        }else{
            forma=message018;
            error(forma);
            ScreenThree_email.pb_email.setVisibility(View.INVISIBLE);
        }

    }

//**************************************************************************************************
//**************************************************************************************************
//получаем регистрационные данные в отдельном потоке
    public class MyTask_review_byEmail extends AsyncTask<Void, Void, String[][]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            rev=new String[1][1];
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String[][] doInBackground(Void... params) {
            try {
                review_byEmail();
            } catch (Exception e) {
                rev[0][0]=message019;
                e.printStackTrace();
            }
            return rev;
        }

        @Override
        protected void onPostExecute(String[][] rev) {
            super.onPostExecute(rev);
            if ((rev[0][0]).compareToIgnoreCase(message019)!=0){
                String mes=rev[0][0];
                Message_to_string.mes_to_string(mes);
                String reg=Message_to_string.mes_s;
                if(Message_to_string.res_ok==0){
                    forma=reg;
                    error(forma);
                }
                else{
                    to_anketa_byEmail(rev);
                }
            }else{
                forma=message019;
                error(forma);
            }
            //прячем клавиатуру
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            ScreenThree_email.pb_email.setVisibility(View.INVISIBLE);
        }

//        public Object onRetainNonConfigurationInstance() {
//            return mt_rev_byEmail;
//        }

    }

    //метод для получения анкеты
    public static String[][] review_byEmail() throws Exception {


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_review);

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
        e.setValue(anketa_by_email_text);
        e.setType(String.class);
        Log.d(TAG,"email отправляем"+ anketa_by_email_text);

        request.addProperty(f);
        request.addProperty(i);
        request.addProperty(e);
        request.addProperty(t);
        request.addProperty(propInfo);
        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

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
    public void to_anketa_byEmail(String[][] rev) {

        String id_ = rev[11][0];
        String f = rev[0][0];
        String i = rev[1][0];
        String o = rev[2][0];
        String dr = rev[3][0];
        String reg_c = rev[4][0];
        String friend0 = rev[8][0];
        String friend1 =rev[8][1];
        String friend2 =rev[8][2];
        String fri=friend0+" "+friend1+" "+friend2;
        String status0 = rev[9][0];
        String status1 =rev[9][1];
        String sta=status0+" - изменен "+status1;
        String balance = rev[10][0];
//
        if(rev[7][0]!=null){
            String em_past= rev[7][0];
            ScreenThree_email.a_by_email.setText(em_past);
        }


        ScreenThree_email.ank_email_id.setText(id_);
        ScreenThree_email.ank_email_f.setText(f.toString());
        ScreenThree_email.ank_email_i.setText(i.toString());
        ScreenThree_email.ank_email_o.setText(o.toString());
        ScreenThree_email.ank_email_dr.setText(dr.toString());
        ScreenThree_email.ank_email_reg.setText(reg_c.toString());
        ScreenThree_email.ank_email_friend.setText(fri.toString());
        ScreenThree_email.ank_email_status.setText(sta.toString());
        ScreenThree_email.ank_email_balance.setText(balance.toString());
//телефон
        String[] from = new String[rcount + 1];
        for (int x = 0; x < ccount; x++) {
            from[x] = rev[6][x];
            String te=rev[6][x];

            if(te.compareToIgnoreCase("")!=0){

                // создаём текстовое предстввление
                TextView textPoint = new TextView(getApplicationContext());
                // textPoint.setGravity(Gravity.LEFT);
                // textPoint.setPadding(0,0,0,0);
                // textPoint.setBackgroundColor(0xFFFBFF4D);
                textPoint.setTextColor(0xFF000000);
                textPoint.setTextSize(20);

                //  textPoint.setEnabled(true);
                textPoint.setMinLines(1);
                textPoint.setMaxLines(20);
                textPoint.setText(te.toString());

                ScreenThree_email.ank_email_f2.addView(textPoint);
            }
        }


//email
        // String[] from1 = new String[rcount + 1];
        for (int x = 0; x < ccount; x++) {
            //  from[x] = rev[7][x];
            String te=rev[7][x];

            if(te.compareToIgnoreCase("")!=0){

                // создаём текстовое предстввление
                TextView textPoint1 = new TextView(getApplicationContext());
                // textPoint.setGravity(Gravity.LEFT);
                // textPoint.setPadding(0,0,0,0);
                // textPoint.setBackgroundColor(0xFFFBFF4D);
                textPoint1.setTextColor(0xFF000000);
                textPoint1.setTextSize(20);

                //  textPoint.setEnabled(true);
                textPoint1.setMinLines(1);
                textPoint1.setMaxLines(20);
                textPoint1.setText(te.toString());

                ScreenThree_email.ank_email_f3.addView(textPoint1);

            }
        }
        String en=rev[5][0];
        if(en.compareToIgnoreCase("")!=0) {
            byte[] decodedString = Base64.decode(en, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            ScreenThree_email.ank_email_picture.setImageBitmap(decodedByte);
        }
//        ArrayList<String> simplifiedList = new ArrayList<String>();
//        for(int c =0;c<ccount;c++){
//            simplifiedList.add(rev[6][c]);



//        for(int y=0;y<simplifiedList.size();y++)
//        {
//
// //

//        // создаем адаптер
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, names);
//
//        // присваиваем адаптер списку
//        ScreenThree_id.ank_id_f2.setAdapter(adapter);
////        ScreenThree_id.ank_id_f2.setAdapter(new ArrayAdapter<String>(this,
////                android.R.layout.simple_list_item_1, names));
//
//        Log.d(TAG," телефон "+simplifiedList);
        // ScreenThree_id.ank_id_f2.setOnItemClickListener();

//        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, albums));
//        TextView t = (TextView) findViewById(R.id.albumName);
//        t.setText(setRes);
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, simplifiedList);
//        ScreenThree_id.ank_id_f2.setAdapter(arrayAdapter);
    };
//**************************************************************************************************
//**************************************************************************************************
//SCREEN_THREE_fio
//Метод для OnClick (просмотр анкеты)
    public void by_fio_review_onClic(View rootView) {
        ScreenThree_fio.pb_fio.setVisibility(View.VISIBLE);

        anketa_by_f_text = String.valueOf(ScreenThree_fio.a_fio_f.getText());
        anketa_by_i_text = String.valueOf(ScreenThree_fio.a_fio_i.getText());

        ScreenThree_fio.ank_fio_id.setText("");
        ScreenThree_fio.ank_fio_f.setText("");
        ScreenThree_fio.ank_fio_i.setText("");
        ScreenThree_fio.ank_fio_o.setText("");
        ScreenThree_fio.ank_fio_dr.setText("");
        ScreenThree_fio.ank_fio_reg.setText("");
        ScreenThree_fio.ank_fio_friend.setText("");
        ScreenThree_fio.ank_fio_status.setText("");
        ScreenThree_fio.ank_fio_balance.setText("");
        ScreenThree_fio.ank_fio_f22.removeAllViews();
        ScreenThree_fio.ank_fio_f33.removeAllViews();

        Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
        ScreenThree_fio.ank_fio_picture.setImageURI(path);

        if (anketa_by_f_text.compareToIgnoreCase("")!=0 && anketa_by_i_text.compareToIgnoreCase("")!=0) {

            MyTask_review_byFio  mt_rev_byFio  = (MyTask_review_byFio) this.getLastCustomNonConfigurationInstance();
            if (mt_rev_byFio  == null) {
                mt_rev_byFio  = new MyTask_review_byFio();
                mt_rev_byFio.execute();
            }

        }else{
            forma=message018;
            error(forma);
            ScreenThree_fio.pb_fio.setVisibility(View.INVISIBLE);
        }

    }

//**************************************************************************************************
//**************************************************************************************************
//получаем регистрационные данные в отдельном потоке
    public class MyTask_review_byFio extends AsyncTask<Void, Void, String[][]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            rev=new String[1][1];
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String[][] doInBackground(Void... params) {
            try {
                review_byFio();
            } catch (Exception e) {
                rev[0][0]=message019;
                e.printStackTrace();
            }
            return rev;
        }

        @Override
        protected void onPostExecute(String[][] rev) {
            super.onPostExecute(rev);
            if ((rev[0][0]).compareToIgnoreCase(message019)!=0){

                String mes=rev[0][0];
                Message_to_string.mes_to_string(mes);
                String reg=Message_to_string.mes_s;
                if(Message_to_string.res_ok==0 && (rev[0][0]).toString().compareToIgnoreCase("025") != 0){
                    forma=reg;
                    error(forma);
                }
                if((rev[0][0]).toString().compareToIgnoreCase("025") == 0){
                    to_ank_fio_many(rev);
                }

                if(Message_to_string.res_ok==1){
                    to_anketa_byFio(rev);
                }
            }else{
                forma=message019;
                error(forma);
            }

            //прячем клавиатуру
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            ScreenThree_fio.pb_fio.setVisibility(View.INVISIBLE);
        }

//        public Object onRetainNonConfigurationInstance() {
//            return mt_rev_byFio;
//        }

    }

    //метод для вывода информации о многих клиентах
    public void to_ank_fio_many(String[][] rev){


        ScreenThree_fio.ank_fio_tr1.removeAllViews();
        ScreenThree_fio.ank_fio_tr2.removeAllViews();
        ScreenThree_fio.ank_fio_tr3.removeAllViews();
        ScreenThree_fio.ank_fio_tr31.removeAllViews();
        ScreenThree_fio.ank_fio_tr4.removeAllViews();
        ScreenThree_fio.ank_fio_tr5.removeAllViews();
        ScreenThree_fio.l_f1.removeAllViews();
        ScreenThree_fio.l_f2.removeAllViews();
        ScreenThree_fio.ank_fio_tr7.removeAllViews();
        ScreenThree_fio.ank_fio_tr8.removeAllViews();
        ScreenThree_fio.ank_fio_f2.removeAllViews();
        ScreenThree_fio.ank_fio_f3.removeAllViews();

        ScreenThree_fio.btn_three_search.setVisibility(View.INVISIBLE);
     //   ScreenThree_fio.ank_fio_head1.setText("Клиенты с указанными данными:");
        ScreenThree_fio.ank_fio_head2.setText("");
        ScreenThree_fio.ank_fio_picture.setImageBitmap(null);
        int p=rev[1].length;
        LinearLayout f000=new LinearLayout(getApplicationContext());
        f000.setOrientation(LinearLayout.VERTICAL);
        f000.setGravity(Gravity.CENTER_HORIZONTAL);
        ScreenThree_fio.ank_fio_f3.addView(f000);



        for (int x = 0; x < p-1; x++) {
            String te=rev[1][x];
            Log.d(TAG,"te ="+te+ " "+x+" "+(p-1));

            TextView textPoint1 = new TextView(getApplicationContext());
            textPoint1.setGravity(Gravity.LEFT);
            textPoint1.setWidth(700);
            // textPoint.setPadding(0,0,0,0);
            // textPoint.setBackgroundColor(0xFFFBFF4D);
            textPoint1.setTextColor(0xFF000000);
            textPoint1.setTextSize(20);

            //  textPoint.setEnabled(true);
            textPoint1.setMinLines(1);
            textPoint1.setMaxLines(20);
            textPoint1.setText(te);
            f000.addView(textPoint1);

        }


    }

    //метод для получения анкеты
    public static String[][] review_byFio() throws Exception {


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_review);

        PropertyInfo propInfo = new PropertyInfo();
        propInfo.setName("arg0");
        propInfo.setValue("");
        propInfo.setType(String.class);
        Log.d(TAG, "создали свойства " + propInfo.toString());


        PropertyInfo f = new PropertyInfo();
        f.setName("arg1");
        f.setValue(anketa_by_f_text);
        f.setType(String.class);


        PropertyInfo i = new PropertyInfo();
        i.setName("arg2");
        i.setValue(anketa_by_i_text);
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
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

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
                        if ((rev[a][b]).toString().compareToIgnoreCase("015") == 0 || (rev[a][b]).toString().compareToIgnoreCase("anyType{}") == 0) {
                            rev[a][b] = message015;
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
    public void to_anketa_byFio(String[][] rev) {

        String id_ = rev[11][0];
        String f = rev[0][0];
        String i = rev[1][0];
        String o = rev[2][0];
        String dr = rev[3][0];
        String reg_c = rev[4][0];
        String friend0 = rev[8][0];
        String friend1 =rev[8][1];
        String friend2 =rev[8][2];
        String fri=friend0+" "+friend1+" "+friend2;
        String status0 = rev[9][0];
        String status1 =rev[9][1];
        String sta=status0+" - изменен "+status1;
        String balance = rev[10][0];
//
        ScreenThree_fio.ank_fio_id.setText(id_);
        if(rev[0][0]!=null||rev[1][0]!=null) {
            String f_past=rev[0][0];
            String i_past=rev[1][0];
            ScreenThree_fio.a_fio_f.setText(f_past);
            ScreenThree_fio.a_fio_i.setText(i_past);
        }
        ScreenThree_fio.ank_fio_f.setText(f.toString());
        ScreenThree_fio.ank_fio_i.setText(i.toString());
        ScreenThree_fio.ank_fio_o.setText(o.toString());
        ScreenThree_fio.ank_fio_dr.setText(dr.toString());
        ScreenThree_fio.ank_fio_reg.setText(reg_c.toString());
        ScreenThree_fio.ank_fio_friend.setText(fri.toString());
        ScreenThree_fio.ank_fio_status.setText(sta.toString());
        ScreenThree_fio.ank_fio_balance.setText(balance.toString());

//телефон
//        LinearLayout ln=new LinearLayout(getApplication());
//        ScreenThree_fio.ank_fio_f2.addView(ln);
        String[] from = new String[rcount + 1];
        for (int x = 0; x < ccount; x++) {
            from[x] = rev[6][x];
            String te=rev[6][x];

            if(te.compareToIgnoreCase("")!=0){

                // создаём текстовое предстввление
                TextView textPoint = new TextView(getApplicationContext());
//                textPoint.setGravity(Gravity.LEFT);
                // textPoint.setPadding(0,0,0,0);
                // textPoint.setBackgroundColor(0xFFFBFF4D);
                textPoint.setTextColor(0xFF000000);
                textPoint.setTextSize(20);

                //  textPoint.setEnabled(true);
                textPoint.setMinLines(1);
                textPoint.setMaxLines(20);
                textPoint.setText(te.toString());

                ScreenThree_fio.ank_fio_f22.addView(textPoint);
            }
        }


//email
        // String[] from1 = new String[rcount + 1];
        for (int x = 0; x < ccount; x++) {
            //  from[x] = rev[7][x];
            String te=rev[7][x];

            if(te.compareToIgnoreCase("")!=0){

                // создаём текстовое предстввление
                TextView textPoint1 = new TextView(getApplicationContext());
//                textPoint1.setGravity(Gravity.LEFT);
                // textPoint.setPadding(0,0,0,0);
                // textPoint.setBackgroundColor(0xFFFBFF4D);
                textPoint1.setTextColor(0xFF000000);
                textPoint1.setTextSize(20);

                //  textPoint.setEnabled(true);
                textPoint1.setMinLines(1);
                textPoint1.setMaxLines(20);
                textPoint1.setText(te.toString());

                ScreenThree_fio.ank_fio_f33.addView(textPoint1);

            }
        }
        String en=rev[5][0];
        if(en.compareToIgnoreCase("")!=0) {
            byte[] decodedString = Base64.decode(en, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            ScreenThree_fio.ank_fio_picture.setImageBitmap(decodedByte);
        }
//        ArrayList<String> simplifiedList = new ArrayList<String>();
//        for(int c =0;c<ccount;c++){
//            simplifiedList.add(rev[6][c]);



//        for(int y=0;y<simplifiedList.size();y++)
//        {
//
// //

//        // создаем адаптер
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, names);
//
//        // присваиваем адаптер списку
//        ScreenThree_id.ank_id_f2.setAdapter(adapter);
////        ScreenThree_id.ank_id_f2.setAdapter(new ArrayAdapter<String>(this,
////                android.R.layout.simple_list_item_1, names));
//
//        Log.d(TAG," телефон "+simplifiedList);
        // ScreenThree_id.ank_id_f2.setOnItemClickListener();

//        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, albums));
//        TextView t = (TextView) findViewById(R.id.albumName);
//        t.setText(setRes);
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, simplifiedList);
//        ScreenThree_id.ank_id_f2.setAdapter(arrayAdapter);
    };

//**************************************************************************************************
//**************************************************************************************************
//OUT
    public void out_money_id(View rootView){
       pb_all= ScreenThree_id.pb_id;
       bal_all=ScreenThree_id.ank_id_balance;
       id_all=ScreenThree_id.ank_id_id;
       out_all=ScreenThree_id.eT_id;
        out_onClic();
    }
    public void out_money_email(View rootView){
        pb_all= ScreenThree_email.pb_email;
        bal_all=ScreenThree_email.ank_email_balance;
        id_all=ScreenThree_email.ank_email_id;
        out_all=ScreenThree_email.eT_em;
        out_onClic();
    }
    public void out_money_fio(View rootView){
        pb_all= ScreenThree_fio.pb_fio;
        bal_all=ScreenThree_fio.ank_fio_balance;
        id_all=ScreenThree_fio.ank_fio_id;
        out_all=ScreenThree_fio.eT_fio;
        out_onClic();
    }
    public void out_money_tel(View rootView){
        pb_all= ScreenThree_tel.pb_tel;
        bal_all=ScreenThree_tel.ank_tel_balance;
        id_all=ScreenThree_tel.ank_tel_id;
        out_all=ScreenThree_tel.eT_tel;
        out_onClic();
    }


//SCREEN_THREE_id_out
//Метод для OnClick (просмотр анкеты)
    public void out_onClic() {
    pb_all.setVisibility(View.VISIBLE);

    ank_id_id_text = String.valueOf(id_all.getText());
    ank_id_balance_text = String.valueOf(bal_all.getText());
    eT_id_text = String.valueOf(out_all.getText());

    if (eT_id_text.compareToIgnoreCase("")!=0 && ank_id_balance_text.compareToIgnoreCase("")!=0) {

        float x = Float.valueOf(eT_id_text);
        float y = Float.valueOf(ank_id_balance_text);
        if (y >= x && x>0) {

            MyTask_out mt_out = (MyTask_out) this.getLastCustomNonConfigurationInstance();
            if (mt_out == null) {
                mt_out = new MyTask_out();
                mt_out.execute();
            }

        } else {
            forma = message049;
            error(forma);
            pb_all.setVisibility(View.INVISIBLE);
        }
    }else{
        forma=message049;
        error(forma);
        pb_all.setVisibility(View.INVISIBLE);
    }
}

//**************************************************************************************************
//**************************************************************************************************
//получаем регистрационные данные в отдельном потоке
    public class MyTask_out extends AsyncTask<Void, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            res_out=new String[1];
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String[] doInBackground(Void... params) {
            try {
                out();
            } catch (Exception e) {
                res_out[0]=message019;
                e.printStackTrace();
            }
            return res_out;
        }

        @Override
        protected void onPostExecute(String[] res_out) {
            super.onPostExecute(res_out);
            String r=res_out[0];
            if (r.compareToIgnoreCase(message019)!=0){


                if((res_out[0]).toString().compareToIgnoreCase(message003) == 0){
                    forma=message003;
                    error(forma);

                }
                if((res_out[0]).toString().compareToIgnoreCase(message005) == 0){
                    forma=message005;
                    error(forma);
                }
                if((res_out[0]).toString().compareToIgnoreCase(message013) == 0){
                    forma=message013;
                    error(forma);
                }
                else{
                    to_anketa_out(res_out);
                    reg=res_out[0];
                    message(reg);
                }
            }else{
                forma=message019;
                error(forma);
            }
            //прячем клавиатуру
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            pb_all.setVisibility(View.INVISIBLE);
        }

//        public Object onRetainNonConfigurationInstance() {
//            return mt_rev_byEmail;
//        }

    }

    //метод для получения анкеты
    public static String[] out() throws Exception {


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_out);
//bal,cl,out
        PropertyInfo bal = new PropertyInfo();
        bal.setName("arg0");
        bal.setValue(ank_id_balance_text);
        bal.setType(String.class);


        PropertyInfo cl = new PropertyInfo();
        cl.setName("arg1");
        cl.setValue(ank_id_id_text);
        cl.setType(String.class);


        PropertyInfo out = new PropertyInfo();
        out.setName("arg2");
        out.setValue(eT_id_text);
        out.setType(String.class);


        request.addProperty(bal);
        request.addProperty(cl);
        request.addProperty(out);
        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL_out);

//это нам не нужно
//        androidHttpTransport.debug = true;
//        androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//        envelope.dotNet = true;
        androidHttpTransport.call(SOAP_ACTION_out, envelope);

        SoapObject Table1 = (SoapObject) envelope.bodyIn;

            res_out=null;
            if(Table1!=null)
            {
                int count= Table1.getPropertyCount();
                res_out = new String[count];
                for(int i=0;i<count;i++)
                {
                    res_out[i]=Table1.getProperty(i).toString();
                    Log.d(TAG,"www"+res_out[i]);
                }
            }



        // SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        // Log.d(TAG, "Данные отправлены,получен результат :"+resultsRequestSOAP.toString());
        // reg_res=resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();



//______
//        /For  retrieving Single dimensional array from SOAP Response Envelope, use :
//
//        public String[] getStringArray() throws Exception
//        {
//            SoapObject Table = (SoapObject)getEnvelope().bodyIn;
//
//            String []output=null;
//            if(Table!=null)
//            {
//                int count= Table.getPropertyCount();
//                output = new String[count];
//                for(int i=0;i<count;i++)
//                {
//                    output[i]=Table.getProperty(i).toString();
//                }
//            }
//            return output;
//
//        }
//
//        // or if you want 2 dimensional array ,
//
//        public String[][] getStringTable() throws Exception
//        {
//            SoapObject Table =(SoapObject)getEnvelope().bodyIn;
//
//            String [][]output=null;
//            if(Table!=null)
//            {
//                SoapObject row = (SoapObject) Table.getProperty(0);
//
//                if(row!=null)
//                {
//                    int rCount = Table.getPropertyCount();
//                    int cCount = ((SoapObject)Table.getProperty(0)).getPropertyCount();
//                    output = new String[rCount][cCount];
//                    for(int i=0;i<rCount;i++)
//                    {
//                        for(int j=0;j<cCount;j++)
//                            output[i][j] =((SoapObject)    Table.getProperty(i)).getProperty(j).toString();
//                    }
//
//                }
//            }
//            return output;
//
//        }

//______

        return res_out;
    }

    //расстановка по местам
    public void to_anketa_out(String[] res_out) {

        String balance = res_out[1];
        out_all.setText("");
        bal_all.setText(balance.toString());

    };
//**************************************************************************************************
//**************************************************************************************************
//**************************************************************************************************

//Календарик
//int DIALOG_DATE = 1;
//    int myYear = 2015;
//    int myMonth = 00;
//    int myDay = 01;
//указываем сегодняшнюю дату
//    Calendar calendar = Calendar.getInstance();
//    int day = calendar.get(Calendar.DAY_OF_MONTH);
//    int month = calendar.get(Calendar.MONTH);
//    int year = calendar.get(Calendar.YEAR);
//
//    int myYear = year;
//    int myMonth = month;
//    int myDay = day;
//
//    public void on_click_day1(View rootView) {
//        Kalendar = 0;
//        showDialog(DIALOG_DATE);
//    }
//    public void on_click_day2(View rootView) {
//        Kalendar = 1;
//        showDialog(DIALOG_DATE);
//    }

//
//    protected Dialog onCreateDialog(int id) {
//        if (id == DIALOG_DATE) {
//            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
//            return tpd;
//        }
//        return super.onCreateDialog(id);
//    }
//
//    public DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {
//
//
//        public void onDateSet(DatePicker view, int year, int monthOfYear,
//                              int dayOfMonth) {
//            myYear = year;
//            drYear = String.valueOf(myYear);
//
//            myMonth = monthOfYear+1;//почему-то было +1
//            if (myMonth < 10) {
//                drMonth = "0" + String.valueOf(myMonth);
//            } else {
//                drMonth = String.valueOf(myMonth);
//            }
//
//            myDay = dayOfMonth;
//            if (myDay < 10) {
//                drDay = "0" + String.valueOf(myDay);
//            } else {
//                drDay = String.valueOf(myDay);
//            }
//
//            dr_string = drYear + "-" + drMonth + "-" + drDay;
//            Log.d(TAG,"Календарь "+Kalendar);
//            if(Kalendar==0) {
//                ScreenTwo.day1.setText(dr_string);
//            }
//            if(Kalendar==1){
//                ScreenTwo.day2.setText(dr_string);
//            }

//        }

//    };

//**************************************************************************************************
//**************************************************************************************************

/**/
//**************************************************************************************************
//**************************************************************************************************
//создание списка игравших столов,но на которые небыли внесены еще деньги
//Метод для OnClick (просмотр анкеты)
    public void game_table0(View rootview) {
    ScreenTwo.pb2.setVisibility(View.VISIBLE);

    MyTask_game_table0 mt_table = (MyTask_game_table0) this.getLastCustomNonConfigurationInstance();
            if (mt_table == null) {
                mt_table = new MyTask_game_table0();
                mt_table.execute();
            }
}
//**************************************************************************************************
//получаем регистрационные данные в отдельном потоке
    public class MyTask_game_table0 extends AsyncTask<Void, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            res_game0 =new String[1];
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String[] doInBackground(Void... params) {
            try {
                for_game_table0();
            } catch (Exception e) {
                res_game0 [0]=message019;
                e.printStackTrace();
            }
            return res_game0;
        }

        @Override
        protected void onPostExecute(String[] res_game0 ) {
            super.onPostExecute(res_game0 );
            String r=res_game0 [0];
            if (r.compareToIgnoreCase(message019)!=0){
            String mes=r;
            Message_to_string.mes_to_string(mes);
            if(Message_to_string.res_ok==0){
                reg=Message_to_string.mes_s;
                forma=reg;
                error(forma);
            }else{
                to_table();
            }
            }else{
                forma=message019;
                error(forma);
            }
            //прячем клавиатуру
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            ScreenTwo.pb2.setVisibility(View.INVISIBLE);
        }

//        public Object onRetainNonConfigurationInstance() {
//            return mt_rev_byEmail;
//        }

    }

    //метод для получения анкеты
    public static String[] for_game_table0() throws Exception {


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_table);
//ничего не отсылаем
        PropertyInfo to = new PropertyInfo();
        to.setName("arg0");
        to.setValue("1");
        to.setType(String.class);


        request.addProperty(to);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL_out);

        androidHttpTransport.call(SOAP_ACTION_table, envelope);

        SoapObject Table1 = (SoapObject) envelope.bodyIn;

        res_game0 =null;
        if(Table1!=null)
        {
            count_game0= Table1.getPropertyCount();
            res_game0 = new String[count_game0];
            for(int i=0;i<count_game0;i++)
            {
                res_game0 [i]=Table1.getProperty(i).toString();
                Log.d(TAG,"www"+res_game0 [i]);
            }
        }



        // SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        // Log.d(TAG, "Данные отправлены,получен результат :"+resultsRequestSOAP.toString());
        // reg_res=resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();



//______
//        /For  retrieving Single dimensional array from SOAP Response Envelope, use :
//
//        public String[] getStringArray() throws Exception
//        {
//            SoapObject Table = (SoapObject)getEnvelope().bodyIn;
//
//            String []output=null;
//            if(Table!=null)
//            {
//                int count= Table.getPropertyCount();
//                output = new String[count];
//                for(int i=0;i<count;i++)
//                {
//                    output[i]=Table.getProperty(i).toString();
//                }
//            }
//            return output;
//
//        }
//
//        // or if you want 2 dimensional array ,
//
//        public String[][] getStringTable() throws Exception
//        {
//            SoapObject Table =(SoapObject)getEnvelope().bodyIn;
//
//            String [][]output=null;
//            if(Table!=null)
//            {
//                SoapObject row = (SoapObject) Table.getProperty(0);
//
//                if(row!=null)
//                {
//                    int rCount = Table.getPropertyCount();
//                    int cCount = ((SoapObject)Table.getProperty(0)).getPropertyCount();
//                    output = new String[rCount][cCount];
//                    for(int i=0;i<rCount;i++)
//                    {
//                        for(int j=0;j<cCount;j++)
//                            output[i][j] =((SoapObject)    Table.getProperty(i)).getProperty(j).toString();
//                    }
//
//                }
//            }
//            return output;
//
//        }

//______

        return res_game0 ;
    }

    //
    public void to_table(){
    ScreenThree_two_table newFragment = new ScreenThree_two_table();
    android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
    transaction.replace(R.id.content_frame, newFragment);
    transaction.addToBackStack(null);
    transaction.commit();
        Log.d(TAG,"перешли на другой фрейм");
    }
//**************************************************************************************************
//**************************************************************************************************
//создание списка игравших столов BACK
//Метод для OnClick (просмотр анкеты)
    public void game_table0_back(View rootview) {
    ScreenTwo.pb2.setVisibility(View.VISIBLE);

    MyTask_game_table0_back mt_table_back = (MyTask_game_table0_back) this.getLastCustomNonConfigurationInstance();
    if (mt_table_back == null) {
        mt_table_back = new MyTask_game_table0_back();
        mt_table_back.execute();
    }
}
    //**************************************************************************************************
//получаем регистрационные данные в отдельном потоке
    public class MyTask_game_table0_back extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                for_game_table0_back();
                String mes=to_text;
                Message_to_string.mes_to_string(mes);
                to=Message_to_string.mes_s;
            } catch (Exception e) {
                to=message019;
                e.printStackTrace();
            }
            return to;
        }

        @Override
        protected void onPostExecute(String to) {
            super.onPostExecute(to);
            String r=to;
            Log.d(TAG,"qqq"+r);
            if (r.compareToIgnoreCase(message019)!=0){
                String mes=r;
                Message_to_string.mes_to_string(mes);
                reg=Message_to_string.mes_s;
                if(Message_to_string.res_ok==1){
                    message(reg);
                    ScreenTwo.pb2.setVisibility(View.INVISIBLE);
                    click_backTwo();
                }else{
                    forma=reg;
                    error(forma);
                    ScreenTwo.pb2.setVisibility(View.INVISIBLE);}
            }else{
                forma=message019;
                error(forma);
                ScreenTwo.pb2.setVisibility(View.INVISIBLE);
            }
            //прячем клавиатуру
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


        }

//        public Object onRetainNonConfigurationInstance() {
//            return mt_rev_byEmail;
//        }

    }

    //метод для получения анкеты
    public static String for_game_table0_back() throws Exception {


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_how_to1);
//ничего не отсылаем
        PropertyInfo to = new PropertyInfo();
        to.setName("arg0");
        to.setValue("0");
        to.setType(String.class);


        request.addProperty(to);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL_out);

        androidHttpTransport.call(SOAP_ACTION_how_to1, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :"+resultsRequestSOAP.toString());
        to_text=resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();



//______
//        /For  retrieving Single dimensional array from SOAP Response Envelope, use :
//
//        public String[] getStringArray() throws Exception
//        {
//            SoapObject Table = (SoapObject)getEnvelope().bodyIn;
//
//            String []output=null;
//            if(Table!=null)
//            {
//                int count= Table.getPropertyCount();
//                output = new String[count];
//                for(int i=0;i<count;i++)
//                {
//                    output[i]=Table.getProperty(i).toString();
//                }
//            }
//            return output;
//
//        }
//
//        // or if you want 2 dimensional array ,
//
//        public String[][] getStringTable() throws Exception
//        {
//            SoapObject Table =(SoapObject)getEnvelope().bodyIn;
//
//            String [][]output=null;
//            if(Table!=null)
//            {
//                SoapObject row = (SoapObject) Table.getProperty(0);
//
//                if(row!=null)
//                {
//                    int rCount = Table.getPropertyCount();
//                    int cCount = ((SoapObject)Table.getProperty(0)).getPropertyCount();
//                    output = new String[rCount][cCount];
//                    for(int i=0;i<rCount;i++)
//                    {
//                        for(int j=0;j<cCount;j++)
//                            output[i][j] =((SoapObject)    Table.getProperty(i)).getProperty(j).toString();
//                    }
//
//                }
//            }
//            return output;
//
//        }

//______

        return to_text ;
    }

//**************************************************************************************************
//**************************************************************************************************
//изымаем с формы данные
    public void in(View rootview){
        ScreenThree_two_table.bp_table.setVisibility(View.VISIBLE);
       int ok = 0;
       int l = ScreenThree_two_table.in.length;
       in_table=new String [l-1][2];
       for(int r=1;r<l;r++){
           in_table[r-1][0]=MainActivity.res_game0[r];
           EditText f=ScreenThree_two_table.in[r];
           String get_t = String.valueOf(f.getText());
           in_table[r-1][1]=get_t;
           Log.d(TAG,"nn"+in_table[r-1][0]+" "+in_table[r-1][1]);
       }
        for(int e=0;e<l-1;e++){
        if(in_table[e][1].compareToIgnoreCase("")!=0){
            ok++;

        }
        }

        int p=l-1;
        Log.d(TAG,"ok "+ok+" p "+p);
        if(ok!=p){

            String mes = "010";
            Message_to_string.mes_to_string(mes);
            forma=Message_to_string.mes_s;
            error(forma);

            }else{MyTask_in mt_in = (MyTask_in) this.getLastCustomNonConfigurationInstance();
            if (mt_in == null) {
                mt_in = new MyTask_in();
                mt_in.execute();
            }
            //выводим сообщение об успешности
            //переходим на главное окно
        }
        ScreenThree_two_table.bp_table.setVisibility(View.INVISIBLE);
    }
//**************************************************************************************************
//получаем регистрационные данные в отдельном потоке
    public class MyTask_in extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                for_in();
//                String mes=res_in;
//                Message_to_string.mes_to_string(mes);
//                res_in=Message_to_string.mes_s;
            } catch (Exception e) {
                res_in=message019;
                e.printStackTrace();
            }
            return res_in;
        }

        @Override
        protected void onPostExecute(String res_in) {
            super.onPostExecute(res_in);
            String r=res_in;
            Log.d(TAG,"qqq"+r);
            if (r.compareToIgnoreCase(message019)!=0){
                String mes=r;
                Message_to_string.mes_to_string(mes);
                reg=Message_to_string.mes_s;
                if(Message_to_string.res_ok==1){
                message(reg);
                }
                else{
                    forma=reg;
                    error(forma);
                }
                if(Message_to_string.res_ok==1){
                 //   ScreenTwo.pb2.setVisibility(View.INVISIBLE);
                    click_backTwo();
                }else{
                   // ScreenTwo.pb2.setVisibility(View.INVISIBLE);
                }
            }else{
                forma=message019;
                error(forma);
               // ScreenTwo.pb2.setVisibility(View.INVISIBLE);
            }
            //прячем клавиатуру
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


        }

//        public Object onRetainNonConfigurationInstance() {
//            return mt_rev_byEmail;
//        }

    }
static String i_t;
    //метод для получения анкеты
    public static String for_in() throws Exception {
        String table=in_table[0][0];
        String money=in_table[0][1];
        String out_for_in=table+"="+money+"\n";
        i_t=out_for_in;
        Log.d(TAG,"i_t - 0 "+i_t);
        for(int t=1;t<in_table.length;t++){
            table=in_table[t][0];
            money=in_table[t][1];

            out_for_in=table+"="+money+"\n";
            i_t+=out_for_in;
        }
        Log.d(TAG,"i_t "+i_t);
        
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_in);
//ничего не отсылаем
//        serial_in = 0;
//        OutputStream fos = new ByteArrayOutputStream(serial_in);
//        Log.d(TAG,"fos "+fos);
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        Log.d(TAG,"oos "+oos);
//       // TestSerial ts = new TestSerial();
//        oos.writeObject(in_table);
//        oos.flush();
//        oos.close();
//        Log.d(TAG,"oos "+oos);

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//
//
//        oos.writeObject(in_table);
//
//        oos.flush();
//        oos.close();
//
//       InputStream is = new ByteArrayInputStream(baos.toByteArray());

//        ByteArrayOutputStream os =
//                new ByteArrayOutputStream();
////        Object objSave = new Integer(1);
//        ObjectOutputStream oos =
//                new ObjectOutputStream(os);
//        oos.writeObject(in_table);
//        Log.d(TAG,"oos "+oos);

        PropertyInfo one = new PropertyInfo();
        one.setName("arg0");
        one.setValue(i_t);
        one.setType(String.class);

        request.addProperty(one);
        Log.d(TAG,"request "+request);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);



        envelope.setOutputSoapObject(request);

//        new MarshalHashtable().register(envelope);
//        new MarshalBase64().register(envelope);
//        MarshalDate md = new MarshalDate();
//        md.register(envelope);


        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL_out);


        androidHttpTransport.call(SOAP_ACTION_in, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :"+resultsRequestSOAP.toString());
        res_in=resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();



//______
//        /For  retrieving Single dimensional array from SOAP Response Envelope, use :
//
//        public String[] getStringArray() throws Exception
//        {
//            SoapObject Table = (SoapObject)getEnvelope().bodyIn;
//
//            String []output=null;
//            if(Table!=null)
//            {
//                int count= Table.getPropertyCount();
//                output = new String[count];
//                for(int i=0;i<count;i++)
//                {
//                    output[i]=Table.getProperty(i).toString();
//                }
//            }
//            return output;
//
//        }
//
//        // or if you want 2 dimensional array ,
//
//        public String[][] getStringTable() throws Exception
//        {
//            SoapObject Table =(SoapObject)getEnvelope().bodyIn;
//
//            String [][]output=null;
//            if(Table!=null)
//            {
//                SoapObject row = (SoapObject) Table.getProperty(0);
//
//                if(row!=null)
//                {
//                    int rCount = Table.getPropertyCount();
//                    int cCount = ((SoapObject)Table.getProperty(0)).getPropertyCount();
//                    output = new String[rCount][cCount];
//                    for(int i=0;i<rCount;i++)
//                    {
//                        for(int j=0;j<cCount;j++)
//                            output[i][j] =((SoapObject)    Table.getProperty(i)).getProperty(j).toString();
//                    }
//
//                }
//            }
//            return output;
//
//        }

//______

        i_t=null;
        return res_in ;
    }

//**************************************************************************************************
//**************************************************************************************************

//все состояния,которые проходит Activity
    protected void onStart() {
    super.onStart();
    Log.d(LOG_TAG, "onStart");
}
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG,"востанавливаем "+theImage+" ");
        theImage = (Bitmap) getLastCustomNonConfigurationInstance();
        if(theImage!=null){
            theImage = savedInstanceState.getParcelable("bitmap");
            ScreenTwo.ivCamera.setImageBitmap(theImage);
        }
        dr_string=(String)getLastCustomNonConfigurationInstance();
       Log.d(TAG,"востанавливаем "+dr_string+" "+ dr_string1);
        if(dr_string!="") {
            Log.d(TAG,"dr = "+dr_string);
            dr_string = savedInstanceState.getString("dr_string_save");
            ScreenTwo.reg_dr.setText(dr_string);
        }
        }
//==================================================================================================
    protected void onResume() {
     super.onResume();
     Log.d(TAG, "onResume ");
 }
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("bitmap", theImage);
        outState.putString("dr_string_save", dr_string);
        Log.d(TAG,"сохраняем "+dr_string);


    }
    protected void onStop() {
        super.onStop();

        Log.d(LOG_TAG, "onStop");
    }
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return theImage;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Выйти?");

        alertDialog.setMessage("Вы действительно хотите выйти?");

        alertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
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
//==================================================================================================
//**************************************************************************************************
//сообщения об ошибках
//нет имени
    public void error(String forma) {

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Ошибка!");

        // Setting Dialog Message
        alertDialog.setMessage(forma);

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.alert);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
//            Toast.makeText(getApplicationContext(),
//                    "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

//сообщение о результатах загрузки
//нет имени
    public void message( final String reg) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Результат");

        // Setting Dialog Message
        alertDialog.setMessage(reg);

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.messagebox_info);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
//            Toast.makeText(getApplicationContext(),
//                    "You clicked on OK", Toast.LENGTH_SHORT).show();
//                getFragmentManager().popBackStackImmediate();
                Log.d(TAG,"в кнопке "+reg);


            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

//    //сообщение о результатах загрузки
////с регистрации вызываем анкету
//    public void message1( final String reg) {
//        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//
//        // Setting Dialog Title
//        alertDialog.setTitle("Результат внесения данных");
//
//        // Setting Dialog Message
//        alertDialog.setMessage(reg);
//
//        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.alert);
//
//        // Setting OK Button
//        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                // Write your code here to execute after dialog closed
////            Toast.makeText(getApplicationContext(),
////                    "You clicked on OK", Toast.LENGTH_SHORT).show();
////                getFragmentManager().popBackStackImmediate();
//                Log.d(TAG,"в кнопке "+reg);
//                if(reg.compareToIgnoreCase("Данные успешно сохранены.")==0){
//                    Log.d(TAG,"возвращаемся");
//                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
//                    ScreenThree_tel newFragment4 = new ScreenThree_tel();
//                    android.app.FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
//                    transaction4.replace(R.id.content_frame, newFragment4);
//                    transaction4.addToBackStack(null);
//                    transaction4.commit();
//                    anketa_by_tel_text = String.valueOf(ScreenTwo.reg_tel.getText());
//                    MyTask_review_byTel mt_rev_byTel = (MyTask_review_byTel) getLastCustomNonConfigurationInstance();
//                    if (mt_rev_byTel == null) {
//                        mt_rev_byTel = new MyTask_review_byTel();
//                        mt_rev_byTel.execute();
//
//
//                    }
//
//                }
//            }
//        });
//
//        // Showing Alert Message
//        alertDialog.show();
//    }

//    //сообщение о результатах загрузки
////с редактирования вызываем анкету
//    public void messageE1( final String reg) {
//        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//
//        // Setting Dialog Title
//        alertDialog.setTitle("Результат внесения данных");
//
//        // Setting Dialog Message
//        alertDialog.setMessage(reg);
//
//        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.alert);
//
//        // Setting OK Button
//        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                // Write your code here to execute after dialog closed
////            Toast.makeText(getApplicationContext(),
////                    "You clicked on OK", Toast.LENGTH_SHORT).show();
////                getFragmentManager().popBackStackImmediate();
//                Log.d(TAG,"в кнопке "+reg);
//                if(reg.compareToIgnoreCase("Изменения успешно сохранены.")==0 || reg.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
//                    Log.d(TAG,"возвращаемся");
//                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
//                    ScreenThree_id newFragment = new ScreenThree_id();
//                    android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.replace(R.id.content_frame, newFragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//                    //____________________
//                    ScreenThree_id.pb_id.setVisibility(View.VISIBLE);
//                    Log.d(TAG, "Включили прогресс бар");
//
//                    anketa_by_id_text = String.valueOf(ScreenThree_id.a_by_id.getText());
//
//                    ScreenThree_id.ank_id_id.setText("");
//                    ScreenThree_id.ank_id_f.setText("");
//                    ScreenThree_id.ank_id_i.setText("");
//                    ScreenThree_id.ank_id_o.setText("");
//                    ScreenThree_id.ank_id_dr.setText("");
//                    ScreenThree_id.ank_id_reg.setText("");
//                    ScreenThree_id.ank_id_friend.setText("");
//                    ScreenThree_id.ank_id_status.setText("");
//                    ScreenThree_id.ank_id_balance.setText("");
//                    ScreenThree_id.ank_id_f2.removeAllViews();
//                    ScreenThree_id.ank_id_f3.removeAllViews();
//
//                    Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
//                    ScreenThree_id.ank_id_picture.setImageURI(path);
//
//                    if (anketa_by_id_text.compareToIgnoreCase("")!=0) {
//
//                        MyTask_review mt_rev = (MyTask_review) getLastCustomNonConfigurationInstance();
//                        if (mt_rev == null) {
//                            mt_rev = new MyTask_review();
//                            mt_rev.execute();
//
//
//                        }
//
//                    }
//                }
//            }
//        });
//
//        // Showing Alert Message
//        alertDialog.show();
//    }
//
//    public void messageE2( final String reg) {
//        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//
//        // Setting Dialog Title
//        alertDialog.setTitle("Результат внесения данных");
//
//        // Setting Dialog Message
//        alertDialog.setMessage(reg);
//
//        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.alert);
//
//        // Setting OK Button
//        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                // Write your code here to execute after dialog closed
////            Toast.makeText(getApplicationContext(),
////                    "You clicked on OK", Toast.LENGTH_SHORT).show();
////                getFragmentManager().popBackStackImmediate();
//                Log.d(TAG,"в кнопке "+reg);
//                if(reg.compareToIgnoreCase("Изменения успешно сохранены.")==0 || reg.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
//                    Log.d(TAG,"возвращаемся");
//
//                    for(int r=0;r<ScreenThree_edit.how_em;r++) {
//                        EditText f = ScreenThree_edit.textE[r];
//                        anketa_by_email_text = String.valueOf(f.getText());
//                        break;
//                    }
//                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
//                    ScreenThree_email newFragment2 = new ScreenThree_email();
//                    android.app.FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
//                    transaction2.replace(R.id.content_frame, newFragment2);
//                    transaction2.addToBackStack(null);
//                    transaction2.commit();
//                    //____________________
//                    ScreenThree_id.pb_id.setVisibility(View.VISIBLE);
//                    Log.d(TAG, "Включили прогресс бар");
//
//                    ScreenThree_email.ank_email_id.setText("");
//                    ScreenThree_email.ank_email_f.setText("");
//                    ScreenThree_email.ank_email_i.setText("");
//                    ScreenThree_email.ank_email_o.setText("");
//                    ScreenThree_email.ank_email_dr.setText("");
//                    ScreenThree_email.ank_email_reg.setText("");
//                    ScreenThree_email.ank_email_friend.setText("");
//                    ScreenThree_email.ank_email_status.setText("");
//                    ScreenThree_email.ank_email_balance.setText("");
//                    ScreenThree_email.ank_email_f2.removeAllViews();
//                    ScreenThree_email.ank_email_f3.removeAllViews();
//
//                    Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
//                    ScreenThree_email.ank_email_picture.setImageURI(path);
//
//                    if (anketa_by_email_text.compareToIgnoreCase("")!=0) {
//
//                        MyTask_review_byEmail  mt_rev_byEmail  = (MyTask_review_byEmail) getLastCustomNonConfigurationInstance();
//                        if (mt_rev_byEmail  == null) {
//                            mt_rev_byEmail  = new MyTask_review_byEmail();
//                            mt_rev_byEmail.execute();
//                        }
//
//                    }else{
//                        forma=message018;
//                        error(forma);
//                        ScreenThree_email.pb_email.setVisibility(View.INVISIBLE);
//                    }
//                }
//            }
//        });
//
//        // Showing Alert Message
//        alertDialog.show();
//    }
//
//    public void messageE3( final String reg) {
//        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//
//        // Setting Dialog Title
//        alertDialog.setTitle("Результат внесения данных");
//
//        // Setting Dialog Message
//        alertDialog.setMessage(reg);
//
//        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.alert);
//
//        // Setting OK Button
//        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                // Write your code here to execute after dialog closed
////            Toast.makeText(getApplicationContext(),
////                    "You clicked on OK", Toast.LENGTH_SHORT).show();
////                getFragmentManager().popBackStackImmediate();
//                Log.d(TAG,"в кнопке "+reg);
//                if(reg.compareToIgnoreCase("Изменения успешно сохранены.")==0 || reg.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
//                    Log.d(TAG,"возвращаемся");
//                    anketa_by_f_text = String.valueOf(ScreenThree_edit.edit_f.getText());
//                    anketa_by_i_text = String.valueOf(ScreenThree_edit.edit_i.getText());
//
//                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
//                    ScreenThree_fio newFragment3 = new ScreenThree_fio();
//                    android.app.FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
//                    transaction3.replace(R.id.content_frame, newFragment3);
//                    transaction3.addToBackStack(null);
//                    transaction3.commit();
//                    //____________________
//                    ScreenThree_fio.pb_fio.setVisibility(View.VISIBLE);
//
//
//
//
//                    ScreenThree_fio.ank_fio_id.setText("");
//                    ScreenThree_fio.ank_fio_f.setText("");
//                    ScreenThree_fio.ank_fio_i.setText("");
//                    ScreenThree_fio.ank_fio_o.setText("");
//                    ScreenThree_fio.ank_fio_dr.setText("");
//                    ScreenThree_fio.ank_fio_reg.setText("");
//                    ScreenThree_fio.ank_fio_friend.setText("");
//                    ScreenThree_fio.ank_fio_status.setText("");
//                    ScreenThree_fio.ank_fio_balance.setText("");
//                    ScreenThree_fio.ank_fio_f22.removeAllViews();
//                    ScreenThree_fio.ank_fio_f33.removeAllViews();
//
//                    Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
//                    ScreenThree_fio.ank_fio_picture.setImageURI(path);
//
//                    if (anketa_by_f_text.compareToIgnoreCase("")!=0 && anketa_by_i_text.compareToIgnoreCase("")!=0) {
//
//                        MyTask_review_byFio  mt_rev_byFio  = (MyTask_review_byFio) getLastCustomNonConfigurationInstance();
//                        if (mt_rev_byFio  == null) {
//                            mt_rev_byFio  = new MyTask_review_byFio();
//                            mt_rev_byFio.execute();
//                        }
//
//                    }else{
//                        forma=message018;
//                        error(forma);
//                        ScreenThree_fio.pb_fio.setVisibility(View.INVISIBLE);
//                    }
//                }
//            }
//
//        });
//
//        // Showing Alert Message
//        alertDialog.show();
//    }
//
//    public void messageE4 ( final String reg) {
//        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//
//        // Setting Dialog Title
//        alertDialog.setTitle("Результат внесения данных");
//
//        // Setting Dialog Message
//        alertDialog.setMessage(reg);
//
//        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.alert);
//
//        // Setting OK Button
//        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                // Write your code here to execute after dialog closed
////            Toast.makeText(getApplicationContext(),
////                    "You clicked on OK", Toast.LENGTH_SHORT).show();
////                getFragmentManager().popBackStackImmediate();
//                Log.d(TAG,"в кнопке "+reg);
//                if(reg.compareToIgnoreCase("Изменения успешно сохранены.")==0 || reg.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
//                    Log.d(TAG,"возвращаемся");
//
//                    for(int r=0;r<ScreenThree_edit.how_tel;r++) {
//                        EditText f = ScreenThree_edit.textP[r];
//                        anketa_by_tel_text = String.valueOf(f.getText());
//                        break;
//                    }
//                    Log.d(TAG,"anketa_by_tel_text ="+anketa_by_tel_text );
//                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
//                    ScreenThree_tel newFragment4 = new ScreenThree_tel();
//                    android.app.FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
//                    transaction4.replace(R.id.content_frame, newFragment4);
//                    transaction4.addToBackStack(null);
//                    transaction4.commit();
//
//                    //____________________
//                    ScreenThree_tel.pb_tel.setVisibility(View.VISIBLE);
//                    Log.d(TAG, "Включили прогресс бар");
//
//                    ScreenThree_tel.ank_tel_id.setText("");
//                    ScreenThree_tel.ank_tel_f.setText("");
//                    ScreenThree_tel.ank_tel_i.setText("");
//                    ScreenThree_tel.ank_tel_o.setText("");
//                    ScreenThree_tel.ank_tel_dr.setText("");
//                    ScreenThree_tel.ank_tel_reg.setText("");
//                    ScreenThree_tel.ank_tel_friend.setText("");
//                    ScreenThree_tel.ank_tel_status.setText("");
//                    ScreenThree_tel.ank_tel_balance.setText("");
//                    ScreenThree_tel.ank_tel_f2.removeAllViews();
//                    ScreenThree_tel.ank_tel_f3.removeAllViews();
//
//                    Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
//                    ScreenThree_tel.ank_tel_picture.setImageURI(path);
//
//                    if (anketa_by_tel_text.compareToIgnoreCase("")!=0) {
//                        Pattern pattern = Pattern.compile("^[+]\\d*"); //не уверена нужен ли значек ^
//                        Matcher matcher = pattern.matcher(anketa_by_tel_text);
//                        if (matcher.matches()) {
//
//
//                            MyTask_review_byTel mt_rev_byTel = (MyTask_review_byTel)getLastCustomNonConfigurationInstance();
//                            if (mt_rev_byTel == null) {
//                                mt_rev_byTel = new MyTask_review_byTel();
//                                mt_rev_byTel.execute();
//
//
//                            }
//                        } else {
//                            proverka = 0;
//                            forma = message022;
//                            error(forma);
//                            ScreenThree_tel.pb_tel.setVisibility(View.INVISIBLE);
//                            //  Toast.makeText(MainActivity.this, "Номер телефона должен начинаться с  + ", Toast.LENGTH_LONG).show();
//                        }
//                    }}}
//        });
//
//        // Showing Alert Message
//        alertDialog.show();
//    }
//


}


