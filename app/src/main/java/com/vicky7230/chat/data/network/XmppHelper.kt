package com.vicky7230.chat.data.network

import io.reactivex.Observable
import org.jivesoftware.smack.AbstractXMPPConnection
import org.jivesoftware.smack.chat2.ChatManager
import org.jivesoftware.smack.roster.Roster
import org.jivesoftware.smack.roster.RosterEntry
import org.jivesoftware.smackx.search.UserSearchManager

/**
 * Created by vicky on 26/11/17.
 */
interface XmppHelper {

    fun connect(): Observable<AbstractXMPPConnection>

    fun login(userName: String, password: String): Observable<AbstractXMPPConnection>

    fun disconnect(): Observable<AbstractXMPPConnection>

    fun getChatManager(): ChatManager

    fun getRoster(): Roster

    fun getUserSearchManager(): UserSearchManager

    fun sendMessage(message: String): Observable<Boolean>

    fun getRosterEntries(): Observable<Collection<RosterEntry>>
}
