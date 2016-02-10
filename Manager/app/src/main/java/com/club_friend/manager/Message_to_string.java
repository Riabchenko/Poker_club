package com.club_friend.manager;

import android.util.Log;

/**
 * Created by apple on 3/20/15.
 */
public class Message_to_string {

    public static String mes, mes_s;
    static int res_ok;

    public static final String TAG="my";
    //***********************


    static String message001 = "Неверно указан телефон друга!Такого номера нет в базе данных";
    static String message002 = "Данные успешно сохраненны";
    static String message003 = "Ошибка при попытке внести данные в базу данных!";
    static String message004 = "Произошла ошибка!Повторите действия";
    static String message005 = "Произошла ошибка!Не найден драйвер для подключения к базе данных";
    static String message006 = "Ошибка!Нет соединения с базой данных!";
    static String message007 = "Ошибка при добавлении фото!Не найден клиент,которому оно присваивается!";
    static String message008 = "Данные успешно сохраненны";//фото
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
    static String message020 = "Введите,пожалуйста, правильно номер телефона друга! Номер должен начинаться с + ";
    static String message021 = "Введите,пожалуйста,  номер телефона";
    static String message022 = "Номер телефона должен начинаться с  + ";
    static String message023 = "Введите, пожалуйста, дату рождения";
    static String message024 = "Введите, пожалуйста, имя";
    static String message025 = "Клиентов с такими данными больше одного!";
    static String message026 = "Изменения успешно сохранены";
    static String message027 = "Произошла ошибка при удалении!";
    static String message028 = "Не удалось изменить друга, поскольку указанного друга нет в базе данных!";
    static String message029 = "Изменений небыло";
    static String message030 = "Стол не найден!";
    static String message031 = "Такой стол уже зарегистрирован! Укажите,пожалуйста,другой стол";
    static String message032 = "Стол зарегистрирован";
    static String message033 = "Стол закрыт";
    static String message034 = "Ошибка! Не удалось зарегистрировать стол";
    static String message035 = "Этот стол уже был закрыт!";
    static String message036 = "Ошибка!Не удалось закрыть стол!";
    static String message037 = "Клиент не зарегистрирован ни за одним столом!";
    static String message038 = "Клиент не играет за данным столом!";
    static String message039 = "Клиент в игре";
    static String message040 = "Клиент вышел с игры";
    static String message041 = "Игра остановлена";
    static String message042 = "Не удалось добавить клиента в игру!Он уже зарегистрирован за другим столом";
    static String message043 = "Не удалось добавить клиента в игру!Он заблокирован";
    static String message044 = "Произошла ошибка!У клиента нет статуса";
    static String message045 = "Произошла ошибка!Не удалось зарегистрировать клиента!";
    static String message046 = "Не найден стол за которым зарегистрирован клиент!";
    static String message047 = "Не удалось остановить игру!Повторите попытку";
    static String message049 = "Проверьте снимаемую сумму! Она не может быть меньше '0' или превышать баланс";
    static String message050 = "Все столы закрыты!";
    static String message051 = "Действия отменены!";
    static String message052 = "Бонус успешно зачислен на столы";
    static String message053 = "Произошла ошибка при выводе информации";
    static String message054 = "На столы деньги не вносились";
    static String message055 = "Бонус уже распределен,поэтому сумму менять нельзя!";
    static String message056 = " сумма успешно измененна ";
    static String message057 = "Бонус успешно распределен";
    static String message058 = "Произошла неизвестная ошибка при распределении бонуса!";


    public static String mes_to_string(String mes){
        res_ok = 1;
        mes_s=mes;
        Log.d(TAG,"cc"+mes_s+" "+mes);
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
            res_ok = 1;
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
            Log.d(TAG,"qqq"+mes_s+" "+res_ok);
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
        if ((mes.compareToIgnoreCase("049") == 0)) {
            mes_s = message049;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("050") == 0)) {
            mes_s = message050;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("051") == 0)) {
            mes_s = message051;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("052") == 0)) {
            mes_s = message052;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("053") == 0)) {
            mes_s = message053;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("054") == 0)) {
            mes_s = message054;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("055") == 0)) {
            mes_s = message055;
            res_ok = 0;
        }
        if ((mes.compareToIgnoreCase("056") == 0)) {
            mes_s = message056;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("057") == 0)) {
            mes_s = message057;
            res_ok = 1;
        }
        if ((mes.compareToIgnoreCase("058") == 0)) {
            mes_s = message058;
            res_ok = 0;
        }
        Log.d(TAG, "в методе " + mes_s);
        Log.d(TAG, "в методе " + res_ok);

        return mes_s;
    }


}

// перенаправление в этот класс делается следующим образом:
//String mes=rev[0][0];
//Message_to_string.mes_to_string(mes);
//        String reg=Message_to_string.mes_s;
//        if(Message_to_string.res_ok==0){
//        message(reg);
////        }


