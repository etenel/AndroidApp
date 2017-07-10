package com.androidapp.util;

/**
 * Created by Administrator on 2017/7/9.
 */

public class DateChange {

    public static String dateFormat(String date){

        if(date.equals("01")) {
            return "JAN";
        }else if(date.equals("02")) {
            return "FEB";
        }else if(date.equals("03")) {
            return "MAR";
        }else if(date.equals("04")) {
            return "APR";
        }else if(date.equals("05")) {
            return "MAY";
        }else if(date.equals("06")) {
            return "JUN";
        }else if(date.equals("07")) {
            return "JUL";
        }else if(date.equals("08")) {
            return "AUG";
        }else if(date.equals("09")) {
            return "SEP";
        }else if(date.equals("010")) {
            return "OCT";
        }else if(date.equals("11")) {
            return "NOV";
        }else if(date.equals("12")) {
            return "DEC";
        }

        return null;
    }
}
