package com.example.ecoreciclaje;

import android.content.Context;
import android.content.SharedPreferences;

public class UserManager {
    //Crear achivo en el pref
    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_EMAIL = "email";
    private static final String PASSWORD_EMAIL = "password";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    //Constructor
    public UserManager(Context context){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void RegisterUser(String email, String password){
        editor.putString(KEY_EMAIL,email);
        editor.putString(PASSWORD_EMAIL,password);

        editor.apply();
    }
    public boolean loginUser(String email, String password){
        String RegisteredEmail = sharedPreferences.getString(KEY_EMAIL, null);
        String RegisteredPassword = sharedPreferences.getString(PASSWORD_EMAIL, null);
        return email.equals(RegisteredEmail) && password.equals(RegisteredPassword);
    }
}
