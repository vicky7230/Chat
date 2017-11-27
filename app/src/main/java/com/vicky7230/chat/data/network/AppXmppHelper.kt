package com.vicky7230.chat.data.network

import com.vicky7230.chat.data.Config
import io.reactivex.Observable
import org.jivesoftware.smack.AbstractXMPPConnection
import org.jivesoftware.smack.chat2.ChatManager
import org.jivesoftware.smack.roster.Roster
import org.jivesoftware.smack.roster.RosterEntry
import org.jivesoftware.smackx.search.UserSearchManager
import org.jxmpp.jid.impl.JidCreate
import javax.inject.Inject

/**
 * Created by vicky on 11/11/17.
 */

class AppXmppHelper @Inject
constructor(private val abstractXMPPConnection: AbstractXMPPConnection) : XmppHelper {

    override fun connect(): Observable<AbstractXMPPConnection> {
        return Observable.fromCallable {
            abstractXMPPConnection.connect()
            abstractXMPPConnection
        }
    }

    override fun login(userName: String, password: String): Observable<AbstractXMPPConnection> {
        return Observable.fromCallable {
            abstractXMPPConnection.login(userName, password)
            abstractXMPPConnection
        }
    }

    override fun disconnect(): Observable<AbstractXMPPConnection> {
        return Observable.fromCallable {
            abstractXMPPConnection.disconnect()
            abstractXMPPConnection
        }
    }

    override fun getChatManager(): ChatManager = ChatManager.getInstanceFor(abstractXMPPConnection)

    override fun getRoster(): Roster = Roster.getInstanceFor(abstractXMPPConnection)

    override fun getUserSearchManager(): UserSearchManager = UserSearchManager(abstractXMPPConnection)

    override fun sendMessage(message: String): Observable<Boolean> {
        return Observable.fromCallable {
            val chatManager = ChatManager.getInstanceFor(abstractXMPPConnection)
            val jid = JidCreate.entityBareFrom("vipinclassic" + "@" + Config.XMPP_DOMAIN)
            val chat = chatManager.chatWith(jid)
            chat.send(message)
            true
        }
    }

    override fun getRosterEntries(): Observable<Collection<RosterEntry>> {
        return Observable.fromCallable {
            val roster = Roster.getInstanceFor(abstractXMPPConnection)
            roster.subscriptionMode = Roster.SubscriptionMode.accept_all
            roster.entries
        }
    }
}
