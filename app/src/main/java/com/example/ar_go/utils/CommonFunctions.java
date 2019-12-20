package com.example.ar_go.utils;

import android.util.Patterns;

import java.util.regex.Pattern;

public class CommonFunctions {

    public static boolean checkstring(String value)
    {
        if (value != null && value.length()>0 && !value.equalsIgnoreCase(""))
        {
            return true;
        }
        else
            return false;
    }

    public static boolean checkmobilenumber(String value)
    {
        if (value != null && value.length()==10 && !value.equalsIgnoreCase(""))
        {
            return true;
        }
        else
            return false;
    }

    public static boolean checkpassword(String value)
    {
        if (value != null && value.length()>=6 && !value.equalsIgnoreCase(""))
        {
            return true;
        }
        else
            return false;
    }

    public static boolean checkemail(String value)
    {
        if (value != null && value.length()>0 && !value.equalsIgnoreCase("") && Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),value))
        {
            return true;
        }
        else
            return false;
    }

    public static boolean checkcode(String value)
    {
        if (value != null && value.length()==4 && !value.equalsIgnoreCase(""))
        {
            return true;
        }
        else
            return false;
    }

}
