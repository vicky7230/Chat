package com.vicky7230.chat.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.vicky7230.chat.data.Config;
import com.vicky7230.chat.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by vicky on 9/10/17.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN";
    private static final String LOGGED_IN_USER_ID = "USER_ID";
    private static final String LOGGED_IN_USER_NAME = "USER_NAME";
    private static final String LOGGED_IN_USER_TOKEN = "USER_TOKEN";

    private final SharedPreferences sharedPreferences;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public boolean getIsUserLoggedIn() {
        return sharedPreferences.getBoolean(IS_USER_LOGGED_IN, false);
    }

    @Override
    public void setIsUserLoggedIn() {
        sharedPreferences.edit().putBoolean(IS_USER_LOGGED_IN, true).apply();
    }

    @Override
    public String getLoggedInUserId() {
        String userId = sharedPreferences.getString(LOGGED_IN_USER_ID, Config.NULL_ID);
        return userId.equalsIgnoreCase(Config.NULL_ID) ? null : userId;
    }

    @Override
    public void setLoggedInUserId(String userId) {
        String id = userId == null ? Config.NULL_ID : userId;
        sharedPreferences.edit().putString(LOGGED_IN_USER_ID, id).apply();
    }

    @Override
    public String getLoggedInUserName() {
        String userName = sharedPreferences.getString(LOGGED_IN_USER_NAME, Config.NULL_NAME);
        return userName.equalsIgnoreCase(Config.NULL_NAME) ? null : userName;
    }

    @Override
    public void setLoggedInUserName(String userName) {
        String name = userName == null ? Config.NULL_NAME : userName;
        sharedPreferences.edit().putString(LOGGED_IN_USER_NAME, name).apply();
    }

    @Override
    public String getLoggedInUserToken() {
        String token = sharedPreferences.getString(LOGGED_IN_USER_TOKEN, Config.NULL_TOKEN);
        return token.endsWith(Config.NULL_TOKEN) ? null : token;
    }

    @Override
    public void setLoggedInUserToken(String token) {
        String t = token == null ? Config.NULL_TOKEN : token;
        sharedPreferences.edit().putString(LOGGED_IN_USER_TOKEN, t).apply();
    }

    @Override
    public void setUserAsLoggedOut() {
        sharedPreferences.edit().clear().apply();
    }

}
