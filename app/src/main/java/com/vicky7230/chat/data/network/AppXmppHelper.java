package com.vicky7230.chat.data.network;

import com.vicky7230.chat.data.Config;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;

import java.util.Collection;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by vicky on 11/11/17.
 */

public class AppXmppHelper implements XmppHelper {

    private final AbstractXMPPConnection abstractXMPPConnection;

    @Inject
    public AppXmppHelper(AbstractXMPPConnection abstractXMPPConnection) {
        this.abstractXMPPConnection = abstractXMPPConnection;
    }

    @Override
    public Observable<AbstractXMPPConnection> connect() {
        return Observable.fromCallable(() -> {
            abstractXMPPConnection.connect();
            return abstractXMPPConnection;
        });
    }

    @Override
    public Observable<AbstractXMPPConnection> login(String userName, String password) {
        return Observable.fromCallable(() -> {
            abstractXMPPConnection.login(userName, password);
            return abstractXMPPConnection;
        });
    }

    @Override
    public Observable<AbstractXMPPConnection> disconnect() {
        return Observable.fromCallable(() -> {
            abstractXMPPConnection.disconnect();
            return abstractXMPPConnection;
        });
    }

    @Override
    public ChatManager getChatManager() {
        return ChatManager.getInstanceFor(abstractXMPPConnection);
    }

    @Override
    public Roster getRoster() {
        return Roster.getInstanceFor(abstractXMPPConnection);
    }

    @Override
    public Observable<Boolean> sendMessage(String message) {
        return Observable.fromCallable(() -> {
            ChatManager chatManager = ChatManager.getInstanceFor(abstractXMPPConnection);
            EntityBareJid jid = JidCreate.entityBareFrom("vipinclassic" + "@" + Config.XMPP_DOMAIN);
            Chat chat = chatManager.chatWith(jid);
            chat.send(message);
            return true;
        });
    }

    @Override
    public Observable<Collection<RosterEntry>> getRosterEntries() {
        return Observable.fromCallable(() -> {
            Roster roster = Roster.getInstanceFor(abstractXMPPConnection);
            roster.setSubscriptionMode(Roster.SubscriptionMode.accept_all);
            return roster.getEntries();
        });
    }
}
