package com.marto.tp_sqlite_polshu.Helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseHelper {
    public static String integerToDate(int n){
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = originalFormat.parse(String.valueOf(n));
            SimpleDateFormat nuevoFormato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaFormateaada = nuevoFormato.format(date);
            return fechaFormateaada;
        }
        catch (Exception ex){
            CustomLog.logException(ex);
        }
        return null;
    }

    public static int dateToInteger(Date d){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(format.format(d));
    }
}
