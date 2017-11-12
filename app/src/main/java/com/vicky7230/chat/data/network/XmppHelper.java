package com.vicky7230.chat.data.network;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;

import java.util.Collection;

import io.reactivex.Observable;

/**
 * Created by vicky on 11/11/17.
 */

public interface XmppHelper {
    Observable<AbstractXMPPConnection> connect();

    Observable<AbstractXMPPConnection> login(String userName, String password);

    Observable<AbstractXMPPConnection> disconnect();

    ChatManager getChatManager();

    Roster getRoster();

    Observable<Boolean> sendMessage(String message);

    Observable<Collection<RosterEntry>> getRosterEntries();
}
