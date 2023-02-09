package com.zhashut.smartcityclient.utils;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

    public static void limitInput(EditText et) {
        String etStr = et.getText().toString();
        String regEx = "[^a-zA-Z0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(etStr);
        String resStr = matcher.replaceAll("").trim();
        if (!etStr.equals(resStr)) {
            et.setText(resStr);
            et.setSelection(resStr.length());
        }
    }
}
