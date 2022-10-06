package com.example.appluce.helper;

import android.util.Base64;

public class Base64Alterar {
    public static String codificar64(String txt){
        return  Base64.encodeToString(txt.getBytes(),Base64.DEFAULT).replaceAll("(\\n|\\r)", "");

    }
    public static String descodificar64(String txt){
        return new String (Base64.decode(txt, Base64.DEFAULT));
    }
}
