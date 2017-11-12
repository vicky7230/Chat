package com.vicky7230.chat.data;


import com.vicky7230.chat.data.network.XmppHelper;
import com.vicky7230.chat.data.prefs.PreferencesHelper;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;

import java.util.Collection;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by vicky on 9/10/17.
 */

public class AppDataManager implements DataManager {

    private final XmppHelper xmppHelper;
    private final PreferencesHelper preferencesHelper;

    @Inject
    public AppDataManager(XmppHelper xmppHelper, PreferencesHelper preferencesHelper) {
        this.xmppHelper = xmppHelper;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public boolean getIsUserLoggedIn() {
        return preferencesHelper.getIsUserLoggedIn();
    }

    @Override
    public void setIsUserLoggedIn() {
        preferencesHelper.setIsUserLoggedIn();
    }

    @Override
    public String getLoggedInUserId() {
        return preferencesHelper.getLoggedInUserId();
    }

    @Override
    public void setLoggedInUserId(String userId) {
        preferencesHelper.setLoggedInUserId(userId);
    }

    @Override
    public String getLoggedInUserName() {
        return preferencesHelper.getLoggedInUserName();
    }

    @Override
    public void setLoggedInUserName(String userName) {
        preferencesHelper.setLoggedInUserName(userName);
    }

    @Override
    public String getLoggedInUserToken() {
        return preferencesHelper.getLoggedInUserToken();
    }

    @Override
    public void setLoggedInUserToken(String token) {
        preferencesHelper.setLoggedInUserToken(token);
    }

    @Override
    public void setUserAsLoggedOut() {
        preferencesHelper.setUserAsLoggedOut();
    }

    @Override
    public Observable<AbstractXMPPConnection> connect() {
        return xmppHelper.connect();
    }

    @Override
    public Observable<AbstractXMPPConnection> login(String userName, String password) {
        return xmppHelper.login(userName, password);
    }

    @Override
    public Observable<AbstractXMPPConnection> disconnect() {
        return xmppHelper.disconnect();
    }

    @Override
    public ChatManager getChatManager() {
        return xmppHelper.getChatManager();
    }

    @Override
    public Roster getRoster() {
        return xmppHelper.getRoster();
    }

    @Override
    public Observable<Boolean> sendMessage(String message) {
        return xmppHelper.sendMessage(message);
    }

    @Override
    public Observable<Collection<RosterEntry>> getRosterEntries() {
        return xmppHelper.getRosterEntries();
    }
}
