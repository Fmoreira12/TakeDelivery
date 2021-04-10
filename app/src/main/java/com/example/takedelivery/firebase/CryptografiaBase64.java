package com.example.takedelivery.firebase;

import android.util.Base64;

public class CryptografiaBase64 {

    public static String codificarBase64(String texto){
        return Base64.encodeToString(texto.getBytes(),
                Base64.DEFAULT).replaceAll("(\\n|\\r)","");
    }

    public static String decodificarBase64(String textoCodificado){
        return new String(Base64.decode(textoCodificado, Base64.DEFAULT));
    }
}
