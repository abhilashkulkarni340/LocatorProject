package com.example.abhilashsk.storelocator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by abhilashsk on 03/03/18.
 */

public class Validator {
    public boolean isValidName(String name){
        String PATTERN = "^[A-Za-z]+[A-Za-z]*[A-Za-z]$";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public boolean isValidPhoneNumber(String phone){
        String PATTERN = "^[789][0-9]{9}$";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    public boolean isValidEmail(String email){
        String PATTERN = "^[a-zA-Z][a-zA-Z0-9\\._]*[@]{1}[a-z]+[\\.]{1}[a-z]{2,}$";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean isValidUsername(String user){
        String PATTERN = "^[A-Za-z0-9]+[A-Za-z0-9_]*$";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(user);
        return matcher.matches();
    }
    public boolean isValidPassword(String pass){
        String PATTERN = "^[A-Za-z0-9$@#]{6,}$";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }
}
