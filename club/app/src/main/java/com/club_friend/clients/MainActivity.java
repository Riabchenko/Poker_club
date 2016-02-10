package com.club_friend.clients;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.club_friend.clients.fragments.ScreenOne;
import com.club_friend.clients.fragments.ScreenThree;
import com.club_friend.clients.fragments.ScreenThree_edit;
import com.club_friend.clients.fragments.ScreenThree_email;
import com.club_friend.clients.fragments.ScreenThree_fio;
import com.club_friend.clients.fragments.ScreenThree_id;
import com.club_friend.clients.fragments.ScreenThree_tel;
import com.club_friend.clients.fragments.ScreenTwo;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.club_friend.clients.R.id.drawer_layout;

public class MainActivity extends ActionBarActivity implements ScreenThree.onSomeEventListener {

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
    private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    public static MyTask mt;
    public static MyTask mt_reg;
    public static MyTask mt_reg_photo;
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

    private static final String SOAP_ACTION = "http://clients.club_friend.com/Hostes/new_clientRequest";
    private static final String SOAP_ACTION_review = "http://clients.club_friend.com/Hostes/review_Request";
    private static final String SOAP_ACTION_photo = "http://clients.club_friend.com/Hostes/new_client_photoRequest";
    private static final String SOAP_ACTION_edit_photo = "http://clients.club_friend.com/Hostes/edit_photoRequest";
    private static final String SOAP_ACTION_edit_fio = "http://clients.club_friend.com/Hostes/edit_Request";
    private static final String SOAP_ACTION_edit_dr = "http://clients.club_friend.com/Hostes/edit_birthdayRequest";
    private static final String SOAP_ACTION_edit_fr = "http://clients.club_friend.com/Hostes/edit_friendRequest";
    private static final String SOAP_ACTION_edit_t1 = "http://clients.club_friend.com/Hostes/edit_telephoneRequest";
    private static final String SOAP_ACTION_edit_e1 = "http://clients.club_friend.com/Hostes/edit_emailRequest";
    private static final String SOAP_ACTION_edit_t2 = "http://clients.club_friend.com/Hostes/delete_telephoneRequest";
    private static final String SOAP_ACTION_edit_e2 = "http://clients.club_friend.com/Hostes/delete_emailRequest";
    private static final String SOAP_ACTION_edit_t3 = "http://clients.club_friend.com/Hostes/new_telephoneRequest";
    private static final String SOAP_ACTION_edit_e3 = "http://clients.club_friend.com/Hostes/new_emailRequest";
    private static final String METHOD_NAME = "new_client";
    private static final String METHOD_NAME_photo = "new_client_photo";
    private static final String METHOD_NAME_review = "review_";
    private static final String METHOD_NAME_edit_photo = "edit_photo";
    private static final String METHOD_NAME_edit_fio = "edit_";
    private static final String METHOD_NAME_edit_dr = "edit_birthday";
    private static final String METHOD_NAME_edit_fr = "edit_friend";
    private static final String METHOD_NAME_edit_t1 = "edit_telephone";
    private static final String METHOD_NAME_edit_e1 = "edit_email";
    private static final String METHOD_NAME_edit_t2 = "delete_telephone";
    private static final String METHOD_NAME_edit_e2 = "delete_email";
    private static final String METHOD_NAME_edit_t3 = "new_telephone";
    private static final String METHOD_NAME_edit_e3 = "new_email";
    private static final String NAMESPACE = "http://clients.club_friend.com/";
    private static final String URL = "http://192.168.3.102:9998/clients/hostes?WSDL";


//    private static final String SOAP_ACTION = "http://clients.club_friend.com/Hostes/getHelloWorldAsStringRequest";
//    private static final String METHOD_NAME = "getHelloWorldAsString";
//    private static final String METHOD_NAME1 = "new_client_photo";
//    private static final String NAMESPACE = "http://clients.club_friend.com/";
//    private static final String URL = "http://192.168.3.101:9991/clients/hostes?WSDL";


//    private static final String SOAP_ACTION = "http://ws.mkyong.com/getHelloWorldAsString";
//    private static final String METHOD_NAME = "getHelloWorldAsString";
//    private static final String METHOD_NAME1 = "new_client_photo";
//    private static final String NAMESPACE = "http://ws.mkyong.com/";
//    private static final String URL = "http://192.168.3.101:9998/ws/hello?wsdl";

//    private static final String SOAP_ACTION = "http://192.168.3.101:9991/clients/hostes/new_client";
//    private static final String METHOD_NAME = "new_client";
//    private static final String METHOD_NAME1 = "new_client_photo";
//    private static final String NAMESPACE = "http://clients.club_friend.com/";
//    private static final String URL = "http://192.168.3.101:9991/clients/hostes?wsdl";

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
    private MyTask_review_byTel mt_rev_byTel;
    private MyTask_review_byEmail mt_rev_byEmail;
    private MyTask_review_byFio mt_rev_byFio;


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
    private String anketa_by_id_text1;
    private String[][] rev1;
    private ScreenThree_id newFragment;

    public static final String TAG = "my";
    private Bitmap v2;
    private static String anketa_by_f_text;
    private static String anketa_by_i_text;
    private int E=0;

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
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
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
                if (theImage != null) {
                    theImage.recycle();
                    theImage = null;
                }

                fragment = new ScreenOne();

                break;
            case 1:
                if (theImage != null) {
                    theImage.recycle();
                    theImage = null;
                }

                fragment = new ScreenTwo();
                break;
            case 2:
                if (theImage != null) {
                    theImage.recycle();
                    theImage = null;
                }

                fragment = new ScreenThree();
                break;
            default:
                if (theImage != null) {
                    theImage.recycle();
                    theImage = null;
                }

                break;
        }

        // Insert the fragment by replacing any existing fragment
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();

            // Highlight the selected item, update the title, and close the drawer
            mDrawerList.setItemChecked(position, true);
            setTitle(mScreenTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // Error
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
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
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
//SCREEN_TWO
//Метод для OnClick (регистрация)
    public void registration_onClic(View rootView) {
        ScreenTwo.pb2.setVisibility(View.VISIBLE);
        proverka = 1;
        reg_f_text = String.valueOf(ScreenTwo.reg_f.getText());
        reg_i_text = String.valueOf(ScreenTwo.reg_i.getText());
        if (reg_i_text.compareToIgnoreCase("") == 0) {
            proverka = 0;
            forma = message010 + message024;
            error(forma);
            ScreenTwo.pb2.setVisibility(View.INVISIBLE);
            // Toast.makeText(MainActivity.this, "Введите,пожалуйста, имя", Toast.LENGTH_LONG).show();
        } else {
            reg_i_text=reg_i_text.trim();
            reg_f_text=reg_f_text.trim();
            reg_o_text = String.valueOf(ScreenTwo.reg_o.getText());
            reg_dr_text = String.valueOf(ScreenTwo.reg_dr.getText());
            if (reg_dr_text.compareToIgnoreCase("") == 0) {
                proverka = 0;
                forma = message010 + message023;
                error(forma);
                ScreenTwo.pb2.setVisibility(View.INVISIBLE);
                //  Toast.makeText(MainActivity.this, "Введите,пожалуйста, дату рождения", Toast.LENGTH_LONG).show();
            } else {
                reg_o_text=reg_o_text.trim();
                        //для проверки номеров телефона
                reg_tel_text = String.valueOf(ScreenTwo.reg_tel.getText());
                if (reg_tel_text.compareToIgnoreCase("") != 0) {
                    reg_tel_text=reg_tel_text.trim();
                    Pattern pattern = Pattern.compile("^[+]\\d*"); //не уверена нужен ли значек ^
                    Matcher matcher = pattern.matcher(reg_tel_text);
                    if (matcher.matches()) {

                    } else {
                        proverka = 0;
                        forma = message022;
                        error(forma);
                        ScreenTwo.pb2.setVisibility(View.INVISIBLE);
                        //  Toast.makeText(MainActivity.this, "Номер телефона должен начинаться с  + ", Toast.LENGTH_LONG).show();
                    }
                } else {
                    proverka = 0;
                    forma = message010 + message021;
                    error(forma);
                    ScreenTwo.pb2.setVisibility(View.INVISIBLE);
                    // Toast.makeText(MainActivity.this, "Введите,пожалуйста, номер телефона", Toast.LENGTH_LONG).show();
                }

                reg_email_text = String.valueOf(ScreenTwo.reg_email.getText());
                reg_email_text=reg_email_text.trim();
                reg_friend_text = String.valueOf(ScreenTwo.reg_friend.getText());


                if (reg_friend_text.compareToIgnoreCase("") != 0) {
                    reg_friend_text.trim();
                    Pattern pattern = Pattern.compile("^[+]\\d*");
                    Matcher matcher = pattern.matcher(reg_friend_text);

                    if (matcher.matches()) {

                    } else {
                        proverka = 0;
                        forma = message020;
                        error(forma);
                        ScreenTwo.pb2.setVisibility(View.INVISIBLE);
                        // Toast.makeText(MainActivity.this, "Номер телефона должен начинаться с  + ", Toast.LENGTH_LONG);
                    }
                }


//        MyTask mt = (MyTask) this.getLastCustomNonConfigurationInstance();
//        if (mt == null) {
//            Log.d(TAG, "запускаем 1 задачу");
//            mt = new MyTask();
//            mt.execute();
//        }
//
                if (proverka == 1) {

                    MyTask_registration_photo mt_reg_photo = (MyTask_registration_photo) this.getLastCustomNonConfigurationInstance();
                    if (mt_reg_photo == null) {
                        mt_reg_photo = new MyTask_registration_photo();
                        mt_reg_photo.execute();
                    }

                }
            }
        }



    }

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

//сообщение о результатах загрузки
//с регистрации вызываем анкету
    public void message1( final String reg) {
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
                if(reg.compareToIgnoreCase("Данные успешно сохранены.")==0){
                    Log.d(TAG,"возвращаемся");
                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
                    ScreenThree_tel newFragment4 = new ScreenThree_tel();
                    android.app.FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
                    transaction4.replace(R.id.content_frame, newFragment4);
                    transaction4.addToBackStack(null);
                    transaction4.commit();
                anketa_by_tel_text = String.valueOf(ScreenTwo.reg_tel.getText());
                    MyTask_review_byTel mt_rev_byTel = (MyTask_review_byTel) getLastCustomNonConfigurationInstance();
                    if (mt_rev_byTel == null) {
                        mt_rev_byTel = new MyTask_review_byTel();
                        mt_rev_byTel.execute();


                    }

            }
        }
    });

    // Showing Alert Message
    alertDialog.show();
}
    public void message10( final String reg) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Результат");

        // Setting Dialog Message
        alertDialog.setMessage(reg);

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.alert);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
//            Toast.makeText(getApplicationContext(),
//                    "You clicked on OK", Toast.LENGTH_SHORT).show();
//                getFragmentManager().popBackStackImmediate();
                Log.d(TAG,"в кнопке "+reg);
                if(reg.compareToIgnoreCase("Данные успешно сохранены.")==0){
                    Log.d(TAG,"возвращаемся");
                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
                    ScreenThree_tel newFragment4 = new ScreenThree_tel();
                    android.app.FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
                    transaction4.replace(R.id.content_frame, newFragment4);
                    transaction4.addToBackStack(null);
                    transaction4.commit();
                    anketa_by_tel_text = String.valueOf(ScreenTwo.reg_tel.getText());
                    MyTask_review_byTel mt_rev_byTel = (MyTask_review_byTel) getLastCustomNonConfigurationInstance();
                    if (mt_rev_byTel == null) {
                        mt_rev_byTel = new MyTask_review_byTel();
                        mt_rev_byTel.execute();


                    }

                }
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

//сообщение о результатах загрузки
//с редактирования вызываем анкету
public void messageE1( final String reg) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Результат ");

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
                if(reg.compareToIgnoreCase("Изменения успешно сохранены.")==0 || reg.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
                    Log.d(TAG,"возвращаемся");
                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
                    ScreenThree_id newFragment = new ScreenThree_id();
                    android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    //____________________
                    ScreenThree_id.pb_id.setVisibility(View.VISIBLE);
                    Log.d(TAG, "Включили прогресс бар");

                    anketa_by_id_text = String.valueOf(ScreenThree_id.a_by_id.getText());

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

                        MyTask_review mt_rev = (MyTask_review) getLastCustomNonConfigurationInstance();
                        if (mt_rev == null) {
                            mt_rev = new MyTask_review();
                            mt_rev.execute();


                        }

                    }
                }
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    public void messageE10( final String reg) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Результат ");

        // Setting Dialog Message
        alertDialog.setMessage(reg);

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.alert);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
//            Toast.makeText(getApplicationContext(),
//                    "You clicked on OK", Toast.LENGTH_SHORT).show();
//                getFragmentManager().popBackStackImmediate();
                Log.d(TAG,"в кнопке "+reg);
                if(reg.compareToIgnoreCase("Изменения успешно сохранены.")==0 || reg.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
                    Log.d(TAG,"возвращаемся");
                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
                    ScreenThree_id newFragment = new ScreenThree_id();
                    android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    //____________________
                    ScreenThree_id.pb_id.setVisibility(View.VISIBLE);
                    Log.d(TAG, "Включили прогресс бар");

                    anketa_by_id_text = String.valueOf(ScreenThree_id.a_by_id.getText());

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

                        MyTask_review mt_rev = (MyTask_review) getLastCustomNonConfigurationInstance();
                        if (mt_rev == null) {
                            mt_rev = new MyTask_review();
                            mt_rev.execute();


                        }

                    }
                }
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

public void messageE2( final String reg) {
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
                if(reg.compareToIgnoreCase("Изменения успешно сохранены.")==0 || reg.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
                    Log.d(TAG,"возвращаемся");

                    for(int r=0;r<ScreenThree_edit.how_em;r++) {
                        EditText f = ScreenThree_edit.textE[r];
                        anketa_by_email_text = String.valueOf(f.getText());
                        break;
                    }
                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
                    ScreenThree_email newFragment2 = new ScreenThree_email();
                    android.app.FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                    transaction2.replace(R.id.content_frame, newFragment2);
                    transaction2.addToBackStack(null);
                    transaction2.commit();
                    //____________________
                    ScreenThree_id.pb_id.setVisibility(View.VISIBLE);
                    Log.d(TAG, "Включили прогресс бар");

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

                        MyTask_review_byEmail  mt_rev_byEmail  = (MyTask_review_byEmail) getLastCustomNonConfigurationInstance();
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
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    public void messageE20( final String reg) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Результат");

        // Setting Dialog Message
        alertDialog.setMessage(reg);

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.alert);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
//            Toast.makeText(getApplicationContext(),
//                    "You clicked on OK", Toast.LENGTH_SHORT).show();
//                getFragmentManager().popBackStackImmediate();
                Log.d(TAG,"в кнопке "+reg);
                if(reg.compareToIgnoreCase("Изменения успешно сохранены.")==0 || reg.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
                    Log.d(TAG,"возвращаемся");

                    for(int r=0;r<ScreenThree_edit.how_em;r++) {
                        EditText f = ScreenThree_edit.textE[r];
                        anketa_by_email_text = String.valueOf(f.getText());
                        break;
                    }
                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
                    ScreenThree_email newFragment2 = new ScreenThree_email();
                    android.app.FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                    transaction2.replace(R.id.content_frame, newFragment2);
                    transaction2.addToBackStack(null);
                    transaction2.commit();
                    //____________________
                    ScreenThree_id.pb_id.setVisibility(View.VISIBLE);
                    Log.d(TAG, "Включили прогресс бар");

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

                        MyTask_review_byEmail  mt_rev_byEmail  = (MyTask_review_byEmail) getLastCustomNonConfigurationInstance();
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
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
public void messageE3( final String reg) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Результат ");

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
                if(reg.compareToIgnoreCase("Изменения успешно сохранены.")==0 || reg.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
                    Log.d(TAG,"возвращаемся");
                    anketa_by_f_text = String.valueOf(ScreenThree_edit.edit_f.getText());
                    anketa_by_i_text = String.valueOf(ScreenThree_edit.edit_i.getText());

                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
                    ScreenThree_fio newFragment3 = new ScreenThree_fio();
                    android.app.FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
                    transaction3.replace(R.id.content_frame, newFragment3);
                    transaction3.addToBackStack(null);
                    transaction3.commit();
                    //____________________
                    ScreenThree_fio.pb_fio.setVisibility(View.VISIBLE);




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

                        MyTask_review_byFio  mt_rev_byFio  = (MyTask_review_byFio) getLastCustomNonConfigurationInstance();
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
            }

        });

        // Showing Alert Message
        alertDialog.show();
    }
    public void messageE30( final String reg) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Результат ");

        // Setting Dialog Message
        alertDialog.setMessage(reg);

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.alert);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
//            Toast.makeText(getApplicationContext(),
//                    "You clicked on OK", Toast.LENGTH_SHORT).show();
//                getFragmentManager().popBackStackImmediate();
                Log.d(TAG,"в кнопке "+reg);
                if(reg.compareToIgnoreCase("Изменения успешно сохранены.")==0 || reg.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
                    Log.d(TAG,"возвращаемся");
                    anketa_by_f_text = String.valueOf(ScreenThree_edit.edit_f.getText());
                    anketa_by_i_text = String.valueOf(ScreenThree_edit.edit_i.getText());

                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
                    ScreenThree_fio newFragment3 = new ScreenThree_fio();
                    android.app.FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
                    transaction3.replace(R.id.content_frame, newFragment3);
                    transaction3.addToBackStack(null);
                    transaction3.commit();
                    //____________________
                    ScreenThree_fio.pb_fio.setVisibility(View.VISIBLE);




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

                        MyTask_review_byFio  mt_rev_byFio  = (MyTask_review_byFio) getLastCustomNonConfigurationInstance();
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
            }

        });

        // Showing Alert Message
        alertDialog.show();
    }

public void messageE4 ( final String reg) {
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
                if(reg.compareToIgnoreCase("Изменения успешно сохранены.")==0 || reg.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
                    Log.d(TAG,"возвращаемся");

                    for(int r=0;r<ScreenThree_edit.how_tel;r++) {
                        EditText f = ScreenThree_edit.textP[r];
                        anketa_by_tel_text = String.valueOf(f.getText());
                        break;
                    }
                    Log.d(TAG,"anketa_by_tel_text ="+anketa_by_tel_text );
                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
                    ScreenThree_tel newFragment4 = new ScreenThree_tel();
                    android.app.FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
                    transaction4.replace(R.id.content_frame, newFragment4);
                    transaction4.addToBackStack(null);
                    transaction4.commit();

                    //____________________
                    ScreenThree_tel.pb_tel.setVisibility(View.VISIBLE);
                    Log.d(TAG, "Включили прогресс бар");

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


                            MyTask_review_byTel mt_rev_byTel = (MyTask_review_byTel)getLastCustomNonConfigurationInstance();
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
                    }}}
        });

        // Showing Alert Message
        alertDialog.show();
    }
    public void messageE40 ( final String reg) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Результат");

        // Setting Dialog Message
        alertDialog.setMessage(reg);

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.alert);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
//            Toast.makeText(getApplicationContext(),
//                    "You clicked on OK", Toast.LENGTH_SHORT).show();
//                getFragmentManager().popBackStackImmediate();
                Log.d(TAG,"в кнопке "+reg);
                if(reg.compareToIgnoreCase("Изменения успешно сохранены.")==0 || reg.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
                    Log.d(TAG,"возвращаемся");

                    for(int r=0;r<ScreenThree_edit.how_tel;r++) {
                        EditText f = ScreenThree_edit.textP[r];
                        anketa_by_tel_text = String.valueOf(f.getText());
                        break;
                    }
                    Log.d(TAG,"anketa_by_tel_text ="+anketa_by_tel_text );
                    // getFragmentManager().popBackStack(); вернуть назад!!!!!!!
                    ScreenThree_tel newFragment4 = new ScreenThree_tel();
                    android.app.FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
                    transaction4.replace(R.id.content_frame, newFragment4);
                    transaction4.addToBackStack(null);
                    transaction4.commit();

                    //____________________
                    ScreenThree_tel.pb_tel.setVisibility(View.VISIBLE);
                    Log.d(TAG, "Включили прогресс бар");

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


                            MyTask_review_byTel mt_rev_byTel = (MyTask_review_byTel)getLastCustomNonConfigurationInstance();
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
                    }}}
        });

        // Showing Alert Message
        alertDialog.show();
    }


//**************************************************************************************************
//Календарик
    int DIALOG_DATE = 1;
    int myYear = 1978;
    int myMonth = 00;
    int myDay = 01;

    public void on_click_dr(View rootView) {
        Kalendar = 0;
        showDialog(DIALOG_DATE);
    }
    public void edit_dr(View rootView) {
        Kalendar = 1;
        showDialog(DIALOG_DATE);
    }





    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    public OnDateSetListener myCallBack = new OnDateSetListener() {


        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            drYear = String.valueOf(myYear);

            myMonth = monthOfYear+1;//почему-то было +1
            if (myMonth < 10) {
                drMonth = "0" + String.valueOf(myMonth);
            } else {
                drMonth = String.valueOf(myMonth);
            }

            myDay = dayOfMonth;
            if (myDay < 10) {
                drDay = "0" + String.valueOf(myDay);
            } else {
                drDay = String.valueOf(myDay);
            }

            dr_string = drYear + "-" + drMonth + "-" + drDay;
            Log.d(TAG,"Календарь "+Kalendar);
            if(Kalendar==0) {
                ScreenTwo.reg_dr.setText(dr_string);
            }
            if(Kalendar==1){
                ScreenThree_edit.edit_dr.setText(dr_string);
            }

        }

    };


//**************************************************************************************************
// Делаем снимок для нового клиента

    public void new_Camera(View rootView){
        CameraTurnClick();
        Camera_my=0;
    }
    public void edit_Camera(View rootView){
        CameraTurnClick();
        Camera_my=1;
    }


    public void CameraTurnClick() {

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            theImage = (Bitmap) data.getExtras().get("data");
            String string = new String(String.valueOf(theImage));

            try {
                getBytes(string);//получен массив
            } catch (IOException e) {
                e.printStackTrace();
            }

            Bitmap v = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length); //массив в картинку
            if (Camera_my == 0) {
                Log.d(TAG, "Camera " + Camera_my);
                ScreenTwo.ivCamera.setImageBitmap(v);
                Log.d(TAG, "Картинка в виде Bitmap =" + v + " и она такая же как исходная картинка ");
            }
            if (Camera_my == 1) {
                Log.d(TAG, "Camera " + Camera_my);
                ScreenThree_edit.edit_photo.setImageBitmap(v);
            }
        }else if(resultCode==RESULT_CANCELED){
            Camera_my=0;
            Log.d(TAG,"Отмена камеры");
        }
    }

    //__________________________________________________________________________________________________
//картинка(строка) в массив байт
    private byte[] getBytes(String string) throws IOException {
        InputStream in = new ByteArrayInputStream(string.getBytes());//создается входной массив из картинки представленной в виде строки байт
        Log.d(TAG, "2-1 " + in);

        blob = new ByteArrayOutputStream();//создается выходной массив
        Log.d(TAG, "3 " + blob);
        theImage.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, blob);//картинка компресуется в формат и записывается в выходной массив
        Log.d(TAG, "4 " + theImage);

        byte[] buf = new byte[1024];
        try {
            for (int read; (read = in.read(buf)) != -1; ) {
                blob.write(buf, 0, read);
                Log.d(TAG, "5 " + blob);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmapdata = blob.toByteArray();
        // byte [] b64 = Base64.encode(bitmapdata, Base64.DEFAULT);//в массив
        // theImage.recycle();//стереть вроде...
        s64 = Base64.encodeToString(bitmapdata, Base64.DEFAULT);
        Log.d(TAG, "6 " + s64);
        return bitmapdata;
    }


//************
//public Bitmap convert(String img){
//    byte[] b = Base64.decode(img, Base64.DEFAULT);
//    return BitmapFactory.decodeByteArray(b, 0, b.length);
//}
//
//    public String convert(Bitmap bm, int quality){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.JPEG, quality, baos);
//
//        byte[] byt = baos.toByteArray();
//        bm.recycle();
//        return Base64.encodeToString(byt, Base64.DEFAULT);
//    }
//**************************************************************************************************
//описание паралельного потока для ScreenTwo
//регистрация
//    public static class MyTask_registration extends AsyncTask<String, Void, Void> {
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            //запускаем диалог показывающий что ты работаешь во всю
//        }
//
//        @Override
//        protected Void doInBackground(String... params) {
//            Log.d(TAG, "отображается c Activity!");
//
//                //вот тут пишем весь кошмар и ужас который будет выполнять в отдельном потоке, короче что угодно.
//            try {
//                registration();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return null;
//
//        }
//
//        @Override
//        public void onPostExecute(Void id_new_client) {
//            super.onPostExecute(id_new_client);
//            Log.d(TAG,"OK");
//            //а здесь мы прячем диалог и заканчиваем работу всех функций которые были запущены в doInBackground()
//        }
//        public Object onRetainNonConfigurationInstance() {
//
//            return mt_reg;
//        }

//    }


//*************************************************************************************************
//передаем регистрационные данные в отдельном потоке
    public class MyTask_registration_photo extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                registration();
                if (reg_res.compareToIgnoreCase(message002) == 0) {
                    if (reg_ok == 1 && s64 != null) {
                        registration_photo();
                        reg = photo_res;
                    } else {
                        reg = reg_res;
                    }
                } else {
                    reg = reg_res;
                }
            } catch (Exception e) {
                reg = message019;
                e.printStackTrace();
            }
            return reg;

        }

        @Override
        protected void onPostExecute(String reg) {
            super.onPostExecute(reg);
            //прячем клавиатуру
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            ScreenTwo.pb2.setVisibility(View.INVISIBLE);
            if(reg.compareToIgnoreCase("Данные успешно сохранены.")==0 || reg.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 || reg.compareToIgnoreCase("Изменения успешно сохранены.")==0 ){
                message1(reg);
            }else{
                message10(reg);
            }

        }

        public Object onRetainNonConfigurationInstance() {
            return mt_reg_photo;
        }

    }

    //метод для регистрации
    public static String registration() throws Exception {
        reg_ok = 0;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


        PropertyInfo f = new PropertyInfo();
        f.setName("arg0");
        f.setValue(reg_f_text);
        f.setType(String.class);


        PropertyInfo i = new PropertyInfo();
        i.setName("arg1");
        i.setValue(reg_i_text);
        i.setType(String.class);


        PropertyInfo o = new PropertyInfo();
        o.setName("arg2");
        o.setValue(reg_o_text);
        o.setType(String.class);


        PropertyInfo fr = new PropertyInfo();
        fr.setName("arg3");
        fr.setValue(reg_friend_text);
        fr.setType(String.class);


        PropertyInfo dr = new PropertyInfo();
        dr.setName("arg4");
        dr.setValue(dr_string);
        dr.setType(String.class);


        PropertyInfo tel = new PropertyInfo();
        tel.setName("arg5");
        tel.setValue(reg_tel_text);
        tel.setType(String.class);


        PropertyInfo e = new PropertyInfo();
        e.setName("arg6");
        e.setValue(reg_email_text);
        e.setType(String.class);


        request.addProperty(f);
        request.addProperty(i);
        request.addProperty(o);
        request.addProperty(fr);
        request.addProperty(dr);
        request.addProperty(tel);
        request.addProperty(e);
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
        reg_res = resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();
        if ((reg_res.compareToIgnoreCase("001") == 0)) {
            reg_res = message001;
            reg_ok = 0;
        }
        if ((reg_res.compareToIgnoreCase("002") == 0)) {
            reg_res = message002;
            reg_ok = 1;
        }
        if ((reg_res.compareToIgnoreCase("003") == 0)) {
            reg_res = message003;
            reg_ok = 0;
        }
        if ((reg_res.compareToIgnoreCase("004") == 0)) {
            reg_res = message004;
            reg_ok = 0;
        }
        if ((reg_res.compareToIgnoreCase("005") == 0)) {
            reg_res = message005;
            reg_ok = 0;
        }
        if ((reg_res.compareToIgnoreCase("006") == 0)) {
            reg_res = message006;
            reg_ok = 0;
        }
        if ((reg_res.compareToIgnoreCase("007") == 0)) {
            reg_res = message007;
            reg_ok = 0;
        }
        if ((reg_res.compareToIgnoreCase("008") == 0)) {
            reg_res = message008;
            reg_ok = 0;
        }
        if ((reg_res.compareToIgnoreCase("009") == 0)) {
            reg_res = message009;
            reg_ok = 0;
        }
        if ((reg_res.compareToIgnoreCase("010") == 0)) {
            reg_res = message010;
            reg_ok = 0;
        }
        if ((reg_res.compareToIgnoreCase("011") == 0)) {
            reg_res = message011;
            reg_ok = 0;
        }
        if ((reg_res.compareToIgnoreCase("012") == 0)) {
            reg_res = message012;
            reg_ok = 0;
        }

        return reg_res;
    }

    //метод для передачи фото
    public static String registration_photo() throws Exception {
        if (s64 != null) {
            System.out.println("s64 = " + s64);
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_photo);

            PropertyInfo photo = new PropertyInfo();
            photo.setName("arg0");
            photo.setValue(s64);
            photo.setType(String.class);
            Log.d(TAG, "создали свойства " + photo.toString());

            PropertyInfo tel = new PropertyInfo();
            tel.setName("arg1");
            tel.setValue(reg_tel_text);
            tel.setType(String.class);
            Log.d(TAG, "создали свойства " + tel.toString());

            request.addProperty(photo);
            Log.d(TAG, "присвоили свойства " + request.toString());

            request.addProperty(tel);
            Log.d(TAG, "присвоили свойства " + request.toString());


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            Log.d(TAG, "создали конверт для фото " + envelope.toString());
            //      new MarshalDouble().register(envelope);

            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            Log.d(TAG, "создали транспорт для фото" + androidHttpTransport.toString());
            androidHttpTransport.call(SOAP_ACTION_photo, envelope);
            Log.d(TAG, "отправили фото");
            SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
            photo_res = resultsRequestSOAP.toString();
            Log.d(TAG, "Фото загружено, получен результат :" + photo_res);
            if (photo_res.compareToIgnoreCase("007") == 0) {
                photo_res = message007;
            }
            if (photo_res.compareToIgnoreCase("008") == 0) {
                photo_res = message002;
            }
            if (photo_res.compareToIgnoreCase("002") == 0) {
                photo_res = message002;
            }
            if (photo_res.compareToIgnoreCase("009") == 0) {
                photo_res = message009;
            }
            if (photo_res.compareToIgnoreCase("003") == 0) {
                photo_res = message003;
            }
            if (photo_res.compareToIgnoreCase("004") == 0) {
                photo_res = message004;
            }
            if (photo_res.compareToIgnoreCase("005") == 0) {
                photo_res = message005;
            }
            if (photo_res.compareToIgnoreCase("006") == 0) {
                photo_res = message006;
            }
        } else {
            photo_res = message002;
        }
        return photo_res;
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
        ScreenThree_edit.res_c_photo=0;
        ScreenThree_edit.res_c_f=0;
        ScreenThree_edit.res_c_i=0;
        ScreenThree_edit.res_c_o=0;
        ScreenThree_edit.res_c_dr=0;
        ScreenThree_edit.res_c_dr=0;
        ScreenThree_edit.res_c_fr=0;

        Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
        ScreenThree_id.ank_id_picture.setImageURI(path);

        if (anketa_by_id_text.compareToIgnoreCase("")!=0) {
            rev=null;
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
//*************************************************************************************************
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


                if((rev[0][0]).toString().compareToIgnoreCase(message003) == 0){
                    forma=message003;
                    error(forma);

                }
                if((rev[0][0]).toString().compareToIgnoreCase(message005) == 0){
                    forma=message005;
                    error(forma);
                }
                if((rev[0][0]).toString().compareToIgnoreCase(message013) == 0){
                    forma=message013;
                    error(forma);
                }
                else{
                    to_anketa(rev);
                }
            }else{
                forma=message019;
                error(forma);
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
                        if((rev[a][b]).toString().compareToIgnoreCase("003") == 0){
                            rev[a][b]=message003;//нужно вызвать окошко с ошибкой

                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("013") == 0){
                            rev[a][b]=message013;//нужно вызвать окошко с ошибкой
                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("005") == 0){
                            rev[a][b]=message005;//нужно вызвать окошко с ошибкой
                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("016") == 0){
                            rev[a][b]=message016;

                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("017") == 0){
                            rev[a][b]=message017;
                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("014") == 0){
                            rev[a][b]=message014;
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
//SCREEN_THREE_tel
//Метод для OnClick (просмотр анкеты)
public void by_tel_review_onClic(View rootView) {
    ScreenThree_tel.pb_tel.setVisibility(View.VISIBLE);

    anketa_by_tel_text = String.valueOf(ScreenThree_tel.a_by_tel.getText());
//    Log.d(TAG,"id "+anketa_by_tel_text);
//    anketa_fio_f_text = String.valueOf(ScreenThree_fio.a_fio_f.getText());
//    anketa_fio_i_text = String.valueOf(ScreenThree_fio.a_fio_i.getText());
//    anketa_by_email_text = String.valueOf(ScreenThree_email.a_by_email.getText());
//
//    if(anketa_by_id_text.compareToIgnoreCase("")==0 &&
//       anketa_by_tel_text.compareToIgnoreCase("")==0 &&
//       anketa_fio_f_text.compareToIgnoreCase("")==0 &&
//       anketa_fio_i_text.compareToIgnoreCase("")==0 &&
//       anketa_by_email_text.compareToIgnoreCase("")==0){
//        reg="Введите,пожалуйста, данные.";
//        message(reg);
//    }else{
//    if(anketa_fio_f_text.compareToIgnoreCase("")!=0 && anketa_fio_i_text.compareToIgnoreCase("")==0 ){
//        reg="Введите,пожалуйста, имя.";
//        message(reg);
//    }
//    if(anketa_fio_f_text.compareToIgnoreCase("")==0 && anketa_fio_i_text.compareToIgnoreCase("")!=0 ){
//        reg="Введите,пожалуйста, фамилию.";
//        message(reg);
//    }
//    }
//    if(    (anketa_by_id_text.compareToIgnoreCase("")!=0 ||
//            anketa_by_tel_text.compareToIgnoreCase("")!=0 ||
//            anketa_by_email_text.compareToIgnoreCase("")!=0)||
//            (anketa_fio_f_text.compareToIgnoreCase("")!=0 && anketa_fio_i_text.compareToIgnoreCase("")!=0)) {
//

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
    ScreenThree_edit.res_c_photo=0;
    ScreenThree_edit.res_c_f=0;
    ScreenThree_edit.res_c_i=0;
    ScreenThree_edit.res_c_o=0;
    ScreenThree_edit.res_c_dr=0;
    ScreenThree_edit.res_c_dr=0;
    ScreenThree_edit.res_c_fr=0;

    Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
    ScreenThree_tel.ank_tel_picture.setImageURI(path);

    if (anketa_by_tel_text.compareToIgnoreCase("")!=0) {
        rev=null;
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

//**************************************************************************************************
//*************************************************************************************************
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


                if((rev[0][0]).toString().compareToIgnoreCase(message003) == 0){
                    forma=message003;
                    error(forma);

                }
                if((rev[0][0]).toString().compareToIgnoreCase(message005) == 0){
                    forma=message005;
                    error(forma);
                }
                if((rev[0][0]).toString().compareToIgnoreCase(message013) == 0){
                    forma=message013;
                    error(forma);
                }
                else{
                    to_anketa_byTel(rev);
                }
            }else{
                forma=message019;
                error(forma);
            }
            //прячем клавиатуру
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            ScreenThree_tel.pb_tel.setVisibility(View.INVISIBLE);
        }

        public Object onRetainNonConfigurationInstance() {
            return mt_rev_byTel;
        }

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
                        if((rev[a][b]).toString().compareToIgnoreCase("003") == 0){
                            rev[a][b]=message003;//нужно вызвать окошко с ошибкой

                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("013") == 0){
                            rev[a][b]=message013;//нужно вызвать окошко с ошибкой
                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("005") == 0){
                            rev[a][b]=message005;//нужно вызвать окошко с ошибкой
                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("016") == 0){
                            rev[a][b]=message016;

                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("017") == 0){
                            rev[a][b]=message017;
                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("014") == 0){
                            rev[a][b]=message014;
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
    //**************************************************************************************************
//**************************************************************************************************
//SCREEN_THREE_email
//Метод для OnClick (просмотр анкеты)
    public void by_email_review_onClic(View rootView) {
        ScreenThree_email.pb_email.setVisibility(View.VISIBLE);

        anketa_by_email_text = String.valueOf(ScreenThree_email.a_by_email.getText());

    Log.d(TAG,"email "+ anketa_by_email_text);
//    anketa_fio_f_text = String.valueOf(ScreenThree_fio.a_fio_f.getText());
//    anketa_fio_i_text = String.valueOf(ScreenThree_fio.a_fio_i.getText());
//    anketa_by_email_text = String.valueOf(ScreenThree_email.a_by_email.getText());
//
//    if(anketa_by_id_text.compareToIgnoreCase("")==0 &&
//       anketa_by_tel_text.compareToIgnoreCase("")==0 &&
//       anketa_fio_f_text.compareToIgnoreCase("")==0 &&
//       anketa_fio_i_text.compareToIgnoreCase("")==0 &&
//       anketa_by_email_text.compareToIgnoreCase("")==0){
//        reg="Введите,пожалуйста, данные.";
//        message(reg);
//    }else{
//    if(anketa_fio_f_text.compareToIgnoreCase("")!=0 && anketa_fio_i_text.compareToIgnoreCase("")==0 ){
//        reg="Введите,пожалуйста, имя.";
//        message(reg);
//    }
//    if(anketa_fio_f_text.compareToIgnoreCase("")==0 && anketa_fio_i_text.compareToIgnoreCase("")!=0 ){
//        reg="Введите,пожалуйста, фамилию.";
//        message(reg);
//    }
//    }
//    if(    (anketa_by_id_text.compareToIgnoreCase("")!=0 ||
//            anketa_by_tel_text.compareToIgnoreCase("")!=0 ||
//            anketa_by_email_text.compareToIgnoreCase("")!=0)||
//            (anketa_fio_f_text.compareToIgnoreCase("")!=0 && anketa_fio_i_text.compareToIgnoreCase("")!=0)) {
//

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
        ScreenThree_edit.res_c_photo=0;
        ScreenThree_edit.res_c_f=0;
        ScreenThree_edit.res_c_i=0;
        ScreenThree_edit.res_c_o=0;
        ScreenThree_edit.res_c_dr=0;
        ScreenThree_edit.res_c_dr=0;
        ScreenThree_edit.res_c_fr=0;

        Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
        ScreenThree_email.ank_email_picture.setImageURI(path);

        if (anketa_by_email_text.compareToIgnoreCase("")!=0) {
                rev=null;
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
//*************************************************************************************************
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


                if((rev[0][0]).toString().compareToIgnoreCase(message003) == 0){
                    forma=message003;
                    error(forma);

                }
                if((rev[0][0]).toString().compareToIgnoreCase(message005) == 0){
                    forma=message005;
                    error(forma);
                }
                if((rev[0][0]).toString().compareToIgnoreCase(message013) == 0){
                    forma=message013;
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

        public Object onRetainNonConfigurationInstance() {
            return mt_rev_byEmail;
        }

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
                        if((rev[a][b]).toString().compareToIgnoreCase("003") == 0){
                            rev[a][b]=message003;//нужно вызвать окошко с ошибкой

                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("013") == 0){
                            rev[a][b]=message013;//нужно вызвать окошко с ошибкой
                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("005") == 0){
                            rev[a][b]=message005;//нужно вызвать окошко с ошибкой
                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("016") == 0){
                            rev[a][b]=message016;

                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("017") == 0){
                            rev[a][b]=message017;
                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("014") == 0){
                            rev[a][b]=message014;
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
        ScreenThree_edit.res_c_photo=0;
        ScreenThree_edit.res_c_f=0;
        ScreenThree_edit.res_c_i=0;
        ScreenThree_edit.res_c_o=0;
        ScreenThree_edit.res_c_dr=0;
        ScreenThree_edit.res_c_dr=0;
        ScreenThree_edit.res_c_fr=0;

        Uri path = Uri.parse("android.resource://com.club_friend.clients/drawable/a9");
        ScreenThree_fio.ank_fio_picture.setImageURI(path);

        if (anketa_by_f_text.compareToIgnoreCase("")!=0 && anketa_by_i_text.compareToIgnoreCase("")!=0) {
            rev=null;
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
//*************************************************************************************************
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


                if((rev[0][0]).toString().compareToIgnoreCase("025") == 0){
                    to_ank_fio_many(rev);

                }
                if((rev[0][0]).toString().compareToIgnoreCase(message003) == 0){
                    forma=message003;
                    error(forma);

                }
                if((rev[0][0]).toString().compareToIgnoreCase(message005) == 0){
                    forma=message005;
                    error(forma);
                }
                if((rev[0][0]).toString().compareToIgnoreCase(message013) == 0){
                    forma=message013;
                    error(forma);
                }
                if((rev[0][0]).toString().compareToIgnoreCase("025") != 0 && (rev[0][0]).toString().compareToIgnoreCase(message003) != 0 && (rev[0][0]).toString().compareToIgnoreCase(message005) != 0 && (rev[0][0]).toString().compareToIgnoreCase(message013) != 0){
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

        public Object onRetainNonConfigurationInstance() {
            return mt_rev_byFio;
        }

    }

    //метод для вывода информации о многих клиентах

    public void to_ank_fio_many(String[][] rev){

        ScreenThree_fio.btn_three_edit.setVisibility(View.INVISIBLE);
//
//        ScreenThree_fio.ank_fio_f2.removeAllViews();
//        ScreenThree_fio.ank_fio_f3.removeAllViews();
//        if(rev[0][0]!=null||rev[1][0]!=null) {
//            String f_past=rev[0][0];
//            String i_past=rev[1][0];
//            ScreenThree_fio.a_fio_f.setText(f_past);
//            ScreenThree_fio.a_fio_i.setText(i_past);
//        }
        ScreenThree_fio.ank_fio_tr1.removeAllViews();
        ScreenThree_fio.ank_fio_tr2.removeAllViews();
        ScreenThree_fio.ank_fio_tr3.removeAllViews();
        ScreenThree_fio.ank_fio_tr31.removeAllViews();
        ScreenThree_fio.ank_fio_tr4.removeAllViews();
        ScreenThree_fio.ank_fio_tr5.removeAllViews();
        ScreenThree_fio.ank_fio_tr6.removeAllViews();
        ScreenThree_fio.ank_fio_tr7.removeAllViews();
        ScreenThree_fio.ank_fio_tr8.removeAllViews();
        ScreenThree_fio.ank_fio_f2.removeAllViews();
        ScreenThree_fio.ank_fio_f3.removeAllViews();

        ScreenThree_fio.btn_three_search.setVisibility(View.INVISIBLE);
        ScreenThree_fio.ank_fio_head1.setText("Клиенты с указанными данными:");
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
                        if((rev[a][b]).toString().compareToIgnoreCase("015") == 0||(rev[a][b]).toString().compareToIgnoreCase("anyType{}") == 0){
                            rev[a][b]=message015;
                            Log.d(TAG, a + " " + b + " переписали " + rev[a][b]);
                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("003") == 0){
                            rev[a][b]=message003;//нужно вызвать окошко с ошибкой

                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("013") == 0){
                            rev[a][b]=message013;//нужно вызвать окошко с ошибкой
                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("005") == 0){
                            rev[a][b]=message005;//нужно вызвать окошко с ошибкой
                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("016") == 0){
                            rev[a][b]=message016;

                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("017") == 0){
                            rev[a][b]=message017;
                        }
                        if((rev[a][b]).toString().compareToIgnoreCase("014") == 0){
                            rev[a][b]=message014;
                        }
                        Log.d(TAG,rev[a][b]);
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
//SCREEN_THREE_edit
//Метод для OnClick (edit анкеты)
public void edit_onClic(View rootView) {

    String ii= String.valueOf(ScreenThree_id.ank_id_id.getText());
    Log.d(TAG,"ii "+ii);
    if(ii.compareToIgnoreCase("")!=0) {

        ScreenThree_edit newFragment5 = new ScreenThree_edit();
        android.app.FragmentTransaction transaction5 = getFragmentManager().beginTransaction();
        transaction5.replace(R.id.content_frame, newFragment5);
        transaction5.addToBackStack(null);
        transaction5.commit();
    }
E=1;
}
public void editEm_onClic(View rootView) {

        String ii= String.valueOf(ScreenThree_email.ank_email_id.getText());
        Log.d(TAG,"ii "+ii);
        if(ii.compareToIgnoreCase("")!=0) {

            ScreenThree_edit newFragment5 = new ScreenThree_edit();
            android.app.FragmentTransaction transaction5 = getFragmentManager().beginTransaction();
            transaction5.replace(R.id.content_frame, newFragment5);
            transaction5.addToBackStack(null);
            transaction5.commit();
        }
    E=2;
    }
public void editFio_onClic(View rootView) {

        String ii= String.valueOf(ScreenThree_fio.ank_fio_id.getText());
        Log.d(TAG,"ii "+ii);
        if(ii.compareToIgnoreCase("")!=0) {

            ScreenThree_edit newFragment5 = new ScreenThree_edit();
            android.app.FragmentTransaction transaction5 = getFragmentManager().beginTransaction();
            transaction5.replace(R.id.content_frame, newFragment5);
            transaction5.addToBackStack(null);
            transaction5.commit();
        }
        E=3;
    }
public void editTel_onClic(View rootView) {

        String ii= String.valueOf(ScreenThree_tel.ank_tel_id.getText());
        Log.d(TAG,"ii "+ii);
        if(ii.compareToIgnoreCase("")!=0) {

            ScreenThree_edit newFragment5 = new ScreenThree_edit();
            android.app.FragmentTransaction transaction5 = getFragmentManager().beginTransaction();
            transaction5.replace(R.id.content_frame, newFragment5);
            transaction5.addToBackStack(null);
            transaction5.commit();
        }
        E=4;
    }


//Метод для Cancel (назад)
    public void edit_cancel(View rootView) {
        getFragmentManager().popBackStackImmediate();
          //  getFragmentManager().popBackStack();
        if(E==1){
            to_anketa(rev);
        }
        if(E==2){
            to_anketa_byEmail(rev);
        }
        if(E==3){
            to_anketa_byFio(rev);
        }
        if(E==4){
            to_anketa_byTel(rev);
        }

        Camera_my=0;
        Kalendar=0;
        ScreenThree_edit.res_c_photo=0;
        ScreenThree_edit.res_c_e=0;
        ScreenThree_edit.res_c_f=0;
        ScreenThree_edit.res_c_i=0;
        ScreenThree_edit.res_c_o=0;
        ScreenThree_edit.res_c_dr=0;
        ScreenThree_edit.res_c_fr=0;
        ScreenThree_edit.res_c_t=0;
    }

//добавление строчек в edit
    public void add_lineTel(View rootView){
        // создаём текстовое предстввление
        if(ScreenThree_edit.how_tel!=10) {
            EditText textPoint1 = new EditText(getApplicationContext());
            textPoint1.setTextColor(0xFF000000);


            Drawable bd = getResources().getDrawable(R.drawable.abc_popup_background_mtrl_mult);// вызываем getDrawable для получения изображения
            textPoint1.setBackground(bd);// Затем можно использовать полученый объект, чтобы установить фон
            textPoint1.setWidth(350);

            textPoint1.setTextSize(20);

            //  textPoint.setEnabled(true);
            textPoint1.setMinLines(1);
            textPoint1.setMaxLines(1);
            textPoint1.setText("");

            ScreenThree_edit.edit_telephone.addView(textPoint1);

            ScreenThree_edit.textP[ScreenThree_edit.how_tel] = textPoint1;
            ScreenThree_edit.how_tel++;
            Log.d(TAG, "количество телефонов " + ScreenThree_edit.how_tel);
        }
    }
    public void add_email(View rootView){
        // создаём текстовое предстввление
        if(ScreenThree_edit.how_em!=10) {
            EditText textPoint2 = new EditText(getApplicationContext());
            textPoint2.setTextColor(0xFF000000);


            Drawable bd = getResources().getDrawable(R.drawable.abc_popup_background_mtrl_mult);// вызываем getDrawable для получения изображения
            textPoint2.setBackground(bd);// Затем можно использовать полученый объект, чтобы установить фон
            textPoint2.setWidth(350);

            textPoint2.setTextSize(20);

            //  textPoint.setEnabled(true);
            textPoint2.setMinLines(1);
            textPoint2.setMaxLines(1);
            textPoint2.setText("");

            ScreenThree_edit.edit_email.addView(textPoint2);

            ScreenThree_edit.textE[ScreenThree_edit.how_em] = textPoint2;
            ScreenThree_edit.how_em++;
            Log.d(TAG, "количество email " + ScreenThree_edit.how_em);
        }
    }

//методы для сохранения изменений
    public void edit_comp(View rootView){
        ScreenThree_edit.pb_edit.setVisibility(View.VISIBLE);

        ScreenThree_edit.edit_compare();
        int proverka_tel=0;
        int zero=0;
        if(ScreenThree_edit.how_tel!=0){
        for(int r=0;r<ScreenThree_edit.how_tel;r++) {
            EditText f=ScreenThree_edit.textP[r];

            String get_t = String.valueOf(f.getText());
            if (get_t.compareToIgnoreCase("") != 0) {
                Pattern pattern = Pattern.compile("^[+]\\d*"); //не уверена нужен ли значек ^
                Matcher matcher = pattern.matcher(get_t);
                if (matcher.matches()) {
                proverka_tel=1;
                } else {
                    proverka_tel=0;
                    forma = message022;
                    error(forma);
                    ScreenThree_edit.pb_edit.setVisibility(View.INVISIBLE);
                    //  Toast.makeText(MainActivity.this, "Номер телефона должен начинаться с  + ", Toast.LENGTH_LONG).show();
                }
            }else{
            zero++;}
        }
            // Toast.makeText(MainActivity.this, "Введите,пожалуйста, номер телефона", Toast.LENGTH_LONG).show();
        }else {
            ScreenThree_edit.pb_edit.setVisibility(View.INVISIBLE);
            proverka_tel=0;
            forma = message010 + message021;
            error(forma);
            Log.d(TAG,"телефонов 0");
        }
        if(zero==ScreenThree_edit.how_tel){
            ScreenThree_edit.pb_edit.setVisibility(View.INVISIBLE);
            proverka_tel=0;
            forma = message010 + message021;
            error(forma);
            Log.d(TAG,"все телефоны с пустыми полями "+zero+" "+ScreenThree_edit.how_tel);
        }

        if(proverka_tel==1) {
            String i= String.valueOf(ScreenThree_edit.edit_i.getText());
            if(i.compareToIgnoreCase("")==0){
                forma = message010 + message024;
                error(forma);
            }else{
            MyTask_save mt_save = (MyTask_save) this.getLastCustomNonConfigurationInstance();
            if (mt_save == null) {
                mt_save = new MyTask_save();
                mt_save.execute();
            }
            }
            Camera_my = 0;
            Kalendar = 0;
            ScreenThree_edit.pb_edit.setVisibility(View.INVISIBLE);
        }
    }

//*************************************************************************************************
//сохраняем отредактированные данные в отдельном потоке
    public class MyTask_save extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected String doInBackground(Void... params) {
            int was=0;
            try {
                edit_ok=1;
                if(ScreenThree_edit.res_c_photo==1){
                    Log.d(TAG," Task "+ScreenThree_edit.res_c_photo);
                    с_photo1();
                    Log.d(TAG,"сохранены изменения в фото");
                    was=1;
                }
                if((ScreenThree_edit.res_c_f==1 || ScreenThree_edit.res_c_i==1 || ScreenThree_edit.res_c_o==1) && edit_ok==1){
                    с_fio1();
                    Log.d(TAG,"сохранены изменения в fio");
                    was=1;
                }
                if(ScreenThree_edit.res_c_dr==1 && edit_ok==1){
                    с_dr1();
                    Log.d(TAG,"сохранены изменения в dr");
                    was=1;
                }
                if(ScreenThree_edit.res_c_fr==1 && edit_ok==1){
                    с_fr1();
                    Log.d(TAG,"сохранены изменения в fr");
                    was=1;
                }
                if(ScreenThree_edit.res_c_t==1 && edit_ok==1){
                    Log.d(TAG,"001");
                  for(int d=0;d<ScreenThree_edit.mobile.length;d++){
                      if(ScreenThree_edit.mobile[d][0]!=null){
                      String ct=ScreenThree_edit.mobile[d][0];
                      if(ct.compareToIgnoreCase("1")==0) {
                          Log.d(TAG, "002");
                          String arg_t = ScreenThree_edit.mobile[d][1];
                          String arg_get = ScreenThree_edit.mobile[d][2];

                          Pattern pattern = Pattern.compile("^[+]\\d*"); //не уверена нужен ли значек ^
                          Matcher matcher = pattern.matcher(arg_get);
                          if (matcher.matches()) {
                              с_t1(arg_t, arg_get);
                          } else {

                              edit_res = message022;

                          }
                      }

                          was=1;
                      }
                  }

                }
                if(ScreenThree_edit.res_c_e==1 && edit_ok==1){
                    Log.d(TAG,"003");
                  for(int k=0;k<ScreenThree_edit.email.length;k++){
                      if(ScreenThree_edit.email[k][0]!=null){
                        String ce=ScreenThree_edit.email[k][0];
                        if(ce.compareToIgnoreCase("1")==0){
                            Log.d(TAG,"004");
                            String arg_e=ScreenThree_edit.email[k][1];
                            String arg_get_e=ScreenThree_edit.email[k][2];
                            с_e1(arg_e, arg_get_e);

                        }was=1;}
                  }

                }
                if(ScreenThree_edit.res_c_t==2 && edit_ok==1){
                    Log.d(TAG,"005");
                    for(int d=0;d<ScreenThree_edit.mobile.length;d++){
                        if(ScreenThree_edit.mobile[d][0]!=null){
                        String ct2=ScreenThree_edit.mobile[d][0];
                        if(ct2.compareToIgnoreCase("2")==0){
                            Log.d(TAG,"006");
                            if(ScreenThree_edit.mobile[d][1]!=null){
                                String arg_t2=ScreenThree_edit.mobile[d][1];
                                с_t2(arg_t2);
                            }
                        }
                            was=1;
                        }
                    }
                }
                if(ScreenThree_edit.res_c_e==2 && edit_ok==1){
                    Log.d(TAG,"007");
                    for(int f=0;f<ScreenThree_edit.email.length;f++){
                        if(ScreenThree_edit.email[f][0]!=null){
                        String ce2=ScreenThree_edit.email[f][0];
                        if(ce2.compareToIgnoreCase("2")==0) {
                            Log.d(TAG, "008");
                            if (ScreenThree_edit.email[f][1] != null) {
                                String arg_e2 = ScreenThree_edit.email[f][1];
                                с_e2(arg_e2);
                            }

                        }was = 1;
                        }
                    }
                }
                if(ScreenThree_edit.res_c_t==3 && edit_ok==1){
                    Log.d(TAG,"009");
                    for(int p=0;p<ScreenThree_edit.mobile.length;p++){
                        if(ScreenThree_edit.mobile[p][0]!=null){
                            Log.d(TAG,"010");
                        String ct3=ScreenThree_edit.mobile[p][0];
                        if(ct3.compareToIgnoreCase("3")==0) {
                            Log.d(TAG,"011");
                            if (ScreenThree_edit.mobile[p][1] != null) {
                                Log.d(TAG,"012");
                                String arg_get3 = ScreenThree_edit.mobile[p][1];
                                с_t3(arg_get3);
                            }
                        }
                            was=1;
                        }
                    }
                }
                if(ScreenThree_edit.res_c_e==3 && edit_ok==1){
                    Log.d(TAG,"013");
                    for(int m=0;m<ScreenThree_edit.email.length;m++){
                        if(ScreenThree_edit.email[m][0]!=null){
                            Log.d(TAG,"014");
                        String ce3=ScreenThree_edit.email[m][0];
                        if(ce3.compareToIgnoreCase("3")==0) {
                            Log.d(TAG,"015");
                            if (ScreenThree_edit.email[m][1] != null) {
                                Log.d(TAG,"016");
                                String arg_get_e3 = ScreenThree_edit.email[m][1];
                                с_e3(arg_get_e3);
                            }
                        }
                            was=1;
                        }
                    }
                }
                if(was==0){
                edit_res=message029;
                }
            } catch (Exception e) {
                edit_res=message019;
                e.printStackTrace();
            }
            return edit_res;
        }

        @Override
        protected void onPostExecute(String edit_res) {
            super.onPostExecute(edit_res);
            if (edit_res.compareToIgnoreCase(message019)!=0){
                int ook=0;
                if ((edit_res.compareToIgnoreCase("001") == 0)) {
                    edit_res = message001;
                }
                if ((edit_res.compareToIgnoreCase("002") == 0)) {
                    edit_res = message002;
                    ook=1;
                }
                if ((edit_res.compareToIgnoreCase("003") == 0)) {
                    edit_res = message003;
                }
                if ((edit_res.compareToIgnoreCase("004") == 0)) {
                    edit_res = message004;
                }
                if ((edit_res.compareToIgnoreCase("005") == 0)) {
                    edit_res = message005;
                }
                if ((edit_res.compareToIgnoreCase("006") == 0)) {
                    edit_res = message006;
                }
                if ((edit_res.compareToIgnoreCase("007") == 0)) {
                    edit_res = message007;
                }
                if ((edit_res.compareToIgnoreCase("008") == 0)) {
                    edit_res = message008;
                    ook=1;
                }
                if ((edit_res.compareToIgnoreCase("009") == 0)) {
                    edit_res = message009;
                }
                if ((edit_res.compareToIgnoreCase("010") == 0)) {
                    reg_res = message010;
                }
                if ((edit_res.compareToIgnoreCase("011") == 0)) {
                    edit_res = message011;
                }
                if ((edit_res.compareToIgnoreCase("012") == 0)) {
                    edit_res = message012;
                }
                if ((edit_res.compareToIgnoreCase("013") == 0)) {
                    edit_res = message013;
                }
                if ((edit_res.compareToIgnoreCase("027") == 0)) {
                    edit_res = message027;
                }
                if ((edit_res.compareToIgnoreCase("026") == 0)) {
                    edit_res = message026;
                    ook=1;
                }
                if ((edit_res.compareToIgnoreCase("027") == 0)) {
                    edit_res = message027;
                }
                if ((edit_res.compareToIgnoreCase("028") == 0)) {
                    edit_res = message028;
                }
                if(E==1){
                    if(edit_res.compareToIgnoreCase("Изменения успешно сохранены.")==0 || edit_res.compareToIgnoreCase("Данные успешно сохранены.")==0 || edit_res.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
                        messageE1(edit_res);
                    }else {messageE10(edit_res);}
                }
                if(E==2){
                    if(edit_res.compareToIgnoreCase("Изменения успешно сохранены.")==0 || edit_res.compareToIgnoreCase("Данные успешно сохранены.")==0 || edit_res.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
                        messageE2(edit_res);
                    }else {messageE20(edit_res);}

                }
                if(E==3){
                    if(edit_res.compareToIgnoreCase("Изменения успешно сохранены.")==0 || edit_res.compareToIgnoreCase("Данные успешно сохранены.")==0 || edit_res.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
                        messageE3(edit_res);
                    }else {messageE30(edit_res);}
                }
                if(E==4){
                    if(edit_res.compareToIgnoreCase("Изменения успешно сохранены.")==0 || edit_res.compareToIgnoreCase("Данные успешно сохранены.")==0 || edit_res.compareToIgnoreCase("Фото успешно добавлено в базу данных.")==0 ){
                        messageE4(edit_res);
                    }else {messageE40(edit_res);}
                }

            }else{
                forma=message019;
                error(forma);
            }

            //прячем клавиатуру
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
          //  ScreenThree_fio.pb_fio.setVisibility(View.INVISIBLE);
        }

        public Object onRetainNonConfigurationInstance() {
            return mt_rev_byFio;
        }

    }

//метод для редактировать photo
    public static String с_photo1() throws Exception {
      //  reg_ok = 0;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_edit_photo);


        PropertyInfo cl = new PropertyInfo();
        cl.setName("arg0");
        String rev_f=rev[11][0];
        cl.setValue(rev_f);
        cl.setType(String.class);


        PropertyInfo photo = new PropertyInfo();
        photo.setName("arg1");
        photo.setValue(s64);
        photo.setType(String.class);


        request.addProperty(cl);
        request.addProperty(photo);

        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        androidHttpTransport.call(SOAP_ACTION_edit_photo, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        edit_res = resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();
        if ((edit_res.compareToIgnoreCase("001") == 0)) {
            edit_res = message001;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("002") == 0)) {
            edit_res = message002;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("003") == 0)) {
            edit_res = message003;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("004") == 0)) {
            edit_res = message004;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("005") == 0)) {
            edit_res = message005;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("006") == 0)) {
            edit_res = message006;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("007") == 0)) {
            edit_res = message007;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("008") == 0)) {
            edit_res = message008;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("009") == 0)) {
            edit_res = message009;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("010") == 0)) {
            reg_res = message010;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("011") == 0)) {
            edit_res = message011;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("012") == 0)) {
            edit_res = message012;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("013") == 0)) {
            edit_res = message013;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("026") == 0)) {
            edit_res = message026;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("028") == 0)) {
            edit_res = message028;
            edit_ok = 0;
        }

        return edit_res;
    }
//метод для ред. fio
    public static String с_fio1() throws Exception {
        //  reg_ok = 0;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_edit_fio);


        PropertyInfo cl = new PropertyInfo();
        cl.setName("arg0");
        String rev_id=rev[11][0];
        cl.setValue(rev_id);
        cl.setType(String.class);


        PropertyInfo f = new PropertyInfo();
        f.setName("arg1");
        f.setValue(ScreenThree_edit.get_f);
        f.setType(String.class);

        PropertyInfo i = new PropertyInfo();
        i.setName("arg2");
        i.setValue(ScreenThree_edit.get_i);
        i.setType(String.class);

        PropertyInfo o = new PropertyInfo();
        o.setName("arg3");
        o.setValue(ScreenThree_edit.get_o);
        o.setType(String.class);

        request.addProperty(cl);
        request.addProperty(f);
        request.addProperty(i);
        request.addProperty(o);

        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        androidHttpTransport.call(SOAP_ACTION_edit_fio, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        edit_res = resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();
        if ((edit_res.compareToIgnoreCase("001") == 0)) {
            edit_res = message001;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("002") == 0)) {
            edit_res = message002;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("003") == 0)) {
            edit_res = message003;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("004") == 0)) {
            edit_res = message004;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("005") == 0)) {
            edit_res = message005;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("006") == 0)) {
            edit_res = message006;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("007") == 0)) {
            edit_res = message007;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("008") == 0)) {
            edit_res = message008;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("009") == 0)) {
            edit_res = message009;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("010") == 0)) {
            reg_res = message010;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("011") == 0)) {
            edit_res = message011;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("012") == 0)) {
            edit_res = message012;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("013") == 0)) {
            edit_res = message013;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("026") == 0)) {
            edit_res = message026;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("028") == 0)) {
            edit_res = message028;
            edit_ok = 0;
        }

        return edit_res;
    }
//метод для ред. dr
    public static String с_dr1() throws Exception {
        //  reg_ok = 0;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_edit_dr);


        PropertyInfo cl = new PropertyInfo();
        cl.setName("arg0");
        String rev_id=rev[11][0];
        cl.setValue(rev_id);
        cl.setType(String.class);


        PropertyInfo dr = new PropertyInfo();
        dr.setName("arg1");
        dr.setValue(ScreenThree_edit.get_dr);
        dr.setType(String.class);


        request.addProperty(cl);
        request.addProperty(dr);

        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        androidHttpTransport.call(SOAP_ACTION_edit_dr, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        edit_res = resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();
        if ((edit_res.compareToIgnoreCase("001") == 0)) {
            edit_res = message001;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("002") == 0)) {
            edit_res = message002;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("003") == 0)) {
            edit_res = message003;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("004") == 0)) {
            edit_res = message004;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("005") == 0)) {
            edit_res = message005;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("006") == 0)) {
            edit_res = message006;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("007") == 0)) {
            edit_res = message007;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("008") == 0)) {
            edit_res = message008;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("009") == 0)) {
            edit_res = message009;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("010") == 0)) {
            reg_res = message010;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("011") == 0)) {
            edit_res = message011;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("012") == 0)) {
            edit_res = message012;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("013") == 0)) {
            edit_res = message013;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("026") == 0)) {
            edit_res = message026;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("028") == 0)) {
            edit_res = message028;
            edit_ok = 0;
        }

        return edit_res;
    }
//метод для ред. fr
    public static String с_fr1() throws Exception {
        //  reg_ok = 0;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_edit_fr);


        PropertyInfo cl = new PropertyInfo();
        cl.setName("arg0");
        String rev_id=rev[11][0];
        cl.setValue(rev_id);
        cl.setType(String.class);


        PropertyInfo fr = new PropertyInfo();
        fr.setName("arg1");
        fr.setValue(ScreenThree_edit.get_fr);
        fr.setType(String.class);


        request.addProperty(cl);
        request.addProperty(fr);

        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        androidHttpTransport.call(SOAP_ACTION_edit_fr, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        edit_res = resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();
        if ((edit_res.compareToIgnoreCase("001") == 0)) {
            edit_res = message001;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("002") == 0)) {
            edit_res = message002;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("003") == 0)) {
            edit_res = message003;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("004") == 0)) {
            edit_res = message004;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("005") == 0)) {
            edit_res = message005;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("006") == 0)) {
            edit_res = message006;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("007") == 0)) {
            edit_res = message007;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("008") == 0)) {
            edit_res = message008;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("009") == 0)) {
            edit_res = message009;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("010") == 0)) {
            reg_res = message010;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("011") == 0)) {
            edit_res = message011;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("012") == 0)) {
            edit_res = message012;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("013") == 0)) {
            edit_res = message013;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("026") == 0)) {
            edit_res = message026;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("028") == 0)) {
            edit_res = message028;
            edit_ok = 0;
        }

        return edit_res;
    }
//метод для ред. t1
    public static String с_t1(String arg_get,String arg_t) throws Exception {
        //  reg_ok = 0;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_edit_t1);


        PropertyInfo t = new PropertyInfo();
        t.setName("arg1");
        t.setValue(arg_t);
        t.setType(String.class);


        PropertyInfo get = new PropertyInfo();
        get.setName("arg0");
        get.setValue(arg_get);
        get.setType(String.class);


        request.addProperty(t);
        request.addProperty(get);

        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        androidHttpTransport.call(SOAP_ACTION_edit_t1, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        edit_res = resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();
        if ((edit_res.compareToIgnoreCase("001") == 0)) {
            edit_res = message001;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("002") == 0)) {
            edit_res = message002;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("003") == 0)) {
            edit_res = message003;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("004") == 0)) {
            edit_res = message004;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("005") == 0)) {
            edit_res = message005;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("006") == 0)) {
            edit_res = message006;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("007") == 0)) {
            edit_res = message007;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("008") == 0)) {
            edit_res = message008;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("009") == 0)) {
            edit_res = message009;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("010") == 0)) {
            reg_res = message010;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("011") == 0)) {
            edit_res = message011;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("012") == 0)) {
            edit_res = message012;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("013") == 0)) {
            edit_res = message013;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("026") == 0)) {
            edit_res = message026;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("028") == 0)) {
            edit_res = message028;
            edit_ok = 0;
        }

        return edit_res;
    }
//метод для ред. e1
    public static String с_e1(String arg_get_e,String arg_e) throws Exception {
        //  reg_ok = 0;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_edit_e1);


        PropertyInfo t = new PropertyInfo();
        t.setName("arg1");
        t.setValue(arg_e);
        t.setType(String.class);


        PropertyInfo get = new PropertyInfo();
        get.setName("arg0");
        get.setValue(arg_get_e);
        get.setType(String.class);


        request.addProperty(t);
        request.addProperty(get);

        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        androidHttpTransport.call(SOAP_ACTION_edit_e1, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        edit_res = resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();
        if ((edit_res.compareToIgnoreCase("001") == 0)) {
            edit_res = message001;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("002") == 0)) {
            edit_res = message002;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("003") == 0)) {
            edit_res = message003;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("004") == 0)) {
            edit_res = message004;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("005") == 0)) {
            edit_res = message005;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("006") == 0)) {
            edit_res = message006;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("007") == 0)) {
            edit_res = message007;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("008") == 0)) {
            edit_res = message008;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("009") == 0)) {
            edit_res = message009;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("010") == 0)) {
            reg_res = message010;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("011") == 0)) {
            edit_res = message011;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("012") == 0)) {
            edit_res = message012;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("013") == 0)) {
            edit_res = message013;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("026") == 0)) {
            edit_res = message026;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("028") == 0)) {
            edit_res = message028;
            edit_ok = 0;
        }

        return edit_res;
    }
//метод для ред. t2
    public static String с_t2(String arg_t2) throws Exception {
        //  reg_ok = 0;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_edit_t2);

        PropertyInfo t = new PropertyInfo();
        t.setName("arg0");
        t.setValue(arg_t2);
        t.setType(String.class);

        request.addProperty(t);

        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        androidHttpTransport.call(SOAP_ACTION_edit_t2, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        edit_res = resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();
        if ((edit_res.compareToIgnoreCase("001") == 0)) {
            edit_res = message001;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("002") == 0)) {
            edit_res = message002;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("003") == 0)) {
            edit_res = message003;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("004") == 0)) {
            edit_res = message004;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("005") == 0)) {
            edit_res = message005;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("006") == 0)) {
            edit_res = message006;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("007") == 0)) {
            edit_res = message007;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("008") == 0)) {
            edit_res = message008;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("009") == 0)) {
            edit_res = message009;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("010") == 0)) {
            reg_res = message010;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("011") == 0)) {
            edit_res = message011;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("012") == 0)) {
            edit_res = message012;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("013") == 0)) {
            edit_res = message013;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("026") == 0)) {
            edit_res = message026;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("028") == 0)) {
            edit_res = message028;
            edit_ok = 0;
        }

        return edit_res;
    }
//метод для ред. e2
    public static String с_e2(String arg_e2) throws Exception {
        //  reg_ok = 0;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_edit_e2);

        PropertyInfo t = new PropertyInfo();
        t.setName("arg0");
        t.setValue(arg_e2);
        t.setType(String.class);

        request.addProperty(t);

        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        androidHttpTransport.call(SOAP_ACTION_edit_e2, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        edit_res = resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();
        if ((edit_res.compareToIgnoreCase("001") == 0)) {
            edit_res = message001;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("002") == 0)) {
            edit_res = message002;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("003") == 0)) {
            edit_res = message003;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("004") == 0)) {
            edit_res = message004;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("005") == 0)) {
            edit_res = message005;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("006") == 0)) {
            edit_res = message006;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("007") == 0)) {
            edit_res = message007;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("008") == 0)) {
            edit_res = message008;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("009") == 0)) {
            edit_res = message009;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("010") == 0)) {
            reg_res = message010;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("011") == 0)) {
            edit_res = message011;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("012") == 0)) {
            edit_res = message012;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("013") == 0)) {
            edit_res = message013;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("026") == 0)) {
            edit_res = message026;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("028") == 0)) {
            edit_res = message028;
            edit_ok = 0;
        }

        return edit_res;
    }
//метод для ред. t3
    public static String с_t3(String arg_get3) throws Exception {
        //  reg_ok = 0;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_edit_t3);

        PropertyInfo cl = new PropertyInfo();
        cl.setName("arg0");
        String rev_id=rev[11][0];
        cl.setValue(rev_id);
        cl.setType(String.class);

        PropertyInfo t = new PropertyInfo();
        t.setName("arg1");
        t.setValue(arg_get3);
        t.setType(String.class);

        request.addProperty(cl);
        request.addProperty(t);

        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        androidHttpTransport.call(SOAP_ACTION_edit_t3, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        edit_res = resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();
        if ((edit_res.compareToIgnoreCase("001") == 0)) {
            edit_res = message001;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("002") == 0)) {
            edit_res = message002;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("003") == 0)) {
            edit_res = message003;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("004") == 0)) {
            edit_res = message004;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("005") == 0)) {
            edit_res = message005;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("006") == 0)) {
            edit_res = message006;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("007") == 0)) {
            edit_res = message007;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("008") == 0)) {
            edit_res = message008;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("009") == 0)) {
            edit_res = message009;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("010") == 0)) {
            reg_res = message010;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("011") == 0)) {
            edit_res = message011;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("012") == 0)) {
            edit_res = message012;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("013") == 0)) {
            edit_res = message013;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("026") == 0)) {
            edit_res = message026;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("028") == 0)) {
            edit_res = message028;
            edit_ok = 0;
        }

        return edit_res;
    }
//метод для ред. e3
    public static String с_e3(String arg_get_e3) throws Exception {
        //  reg_ok = 0;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_edit_e3);

        PropertyInfo cl = new PropertyInfo();
        cl.setName("arg0");
        String rev_id=rev[11][0];
        cl.setValue(rev_id);
        cl.setType(String.class);

        PropertyInfo t = new PropertyInfo();
        t.setName("arg1");
        t.setValue(arg_get_e3);
        t.setType(String.class);

        request.addProperty(cl);
        request.addProperty(t);

        Log.d(TAG, "присвоили свойства " + request.toString());


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        androidHttpTransport.call(SOAP_ACTION_edit_e3, envelope);

        SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
        Log.d(TAG, "Данные отправлены,получен результат :" + resultsRequestSOAP.toString());
        edit_res = resultsRequestSOAP.toString();
//         id_new_client=(int) envelope.getResponse();
        if ((edit_res.compareToIgnoreCase("001") == 0)) {
            edit_res = message001;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("002") == 0)) {
            edit_res = message002;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("003") == 0)) {
            edit_res = message003;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("004") == 0)) {
            edit_res = message004;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("005") == 0)) {
            edit_res = message005;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("006") == 0)) {
            edit_res = message006;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("007") == 0)) {
            edit_res = message007;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("008") == 0)) {
            edit_res = message008;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("009") == 0)) {
            edit_res = message009;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("010") == 0)) {
            reg_res = message010;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("011") == 0)) {
            edit_res = message011;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("012") == 0)) {
            edit_res = message012;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("013") == 0)) {
            edit_res = message013;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("026") == 0)) {
            edit_res = message026;
            edit_ok = 1;
        }
        if ((edit_res.compareToIgnoreCase("027") == 0)) {
            edit_res = message027;
            edit_ok = 0;
        }
        if ((edit_res.compareToIgnoreCase("028") == 0)) {
            edit_res = message028;
            edit_ok = 0;
        }

        return edit_res;
    }
//**************************************************************************************************

//**************************************************************************************************

//**************************************************************************************************
//**************************************************************************************************
//TEST паралельный поток
//   SoapObject resultsRequestSOAP = (SoapObject)envelope.bodyIn;
//   lblResult.setText(resultsRequestSOAP.getPropertyAsString("result"));
//   "result" is the array, which is reported by WebService


    public static class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //запускаем диалог показывающий что ты работаешь во всю
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.d(TAG, "отображается c Activity!");
            try {
                //вот тут пишем весь кошмар и ужас который будет выполнять в отдельном потоке, короче что угодно.

//                for ( int i = 1; i <= 10; i++) {
//                    TimeUnit.SECONDS.sleep(1);
//                    Log.d(TAG, "i = " + i
//                            + ", MyTask: " + MainActivity.MyTask.this.hashCode()
//                            + ", MainActivity: " + this.hashCode());
//                }
//                    // загружаем файл
//                    downloadFile(url);
                poisk();

                // разъединяемся
                TimeUnit.SECONDS.sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //а здесь мы прячем диалог и заканчиваем работу всех функций которые были запущены в doInBackground()
        }

        public Object onRetainNonConfigurationInstance() {
            return mt;
        }


    //метод для паралельного потока в ScreenThree
    public static void poisk(){
        Log.d(TAG, "запуск poisk");
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        Log.d(TAG, "создали soapobject");
        PropertyInfo propInfo = new PropertyInfo();
        propInfo.setName("arg0");
        propInfo.setValue("Aliona");
        propInfo.setType(String.class);
        Log.d(TAG, "создали свойства " + propInfo.toString());

        request.addProperty(propInfo);
        Log.d(TAG, "присвоили свойства " + request.toString());

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        Log.d(TAG, "создали конверт " + envelope.toString());
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        Log.d(TAG, "создали транспорт"+androidHttpTransport.toString());
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Log.d(TAG, "отправили");
            SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
            Log.d(TAG, "App"+resultsRequestSOAP.toString());

        } catch (Exception e) {

        }

    }}
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

//        anketa_by_id_text=(String)getLastCustomNonConfigurationInstance();
//        if(anketa_by_id_text1!=null) {
//            anketa_by_id_text = savedInstanceState.getString(anketa_by_id_text1);
//            ScreenThree_id.ank_id_id.setText(anketa_by_id_text);
//            ScreenThree_id.ank_id_f.setText(f);
//            ScreenThree_id.ank_id_i.setText(i);
//            ScreenThree_id.ank_id_o.setText(o);
//            ScreenThree_id.ank_id_dr.setText(dr);
//            ScreenThree_id.ank_id_reg.setText(reg);
//            ScreenThree_id.ank_id_friend.setText(friend);
//            ScreenThree_id.ank_id_status.setText(status);
//            ScreenThree_id.ank_id_balance.setText(balance);
        }

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




//

        //outState.putString(anketa_by_id_text1,anketa_by_id_text);


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
    public void onclick(View v) {
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


}


