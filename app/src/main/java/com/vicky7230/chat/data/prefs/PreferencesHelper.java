package com.vicky7230.chat.data.prefs;

/**
 * Created by vicky on 9/10/17.
 */

public interface PreferencesHelper {

    boolean getIsUserLoggedIn();

    void setIsUserLoggedIn();

    String getLoggedInUserId();

    void setLoggedInUserId(String userId);

    String getLoggedInUserName();

    void setLoggedInUserName(String userName);

    String getLoggedInUserToken();

    void setLoggedInUserToken(String token);

    void setUserAsLoggedOut();
}
