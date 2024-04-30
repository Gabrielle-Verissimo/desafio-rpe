package com.rpe.desafiorpe.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
public class Data {
    public static String gerarData() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        return df.format(calendar.getTime());
    }
}
