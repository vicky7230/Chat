package com.vicky7230.chat.data


import com.vicky7230.chat.data.network.XmppHelper
import com.vicky7230.chat.data.prefs.PreferencesHelper

import org.jivesoftware.smack.AbstractXMPPConnection
import org.jivesoftware.smack.chat2.ChatManager
import org.jivesoftware.smack.roster.Roster
import org.jivesoftware.smack.roster.RosterEntry
import org.jivesoftware.smackx.search.UserSearchManager

import javax.inject.Inject

import io.reactivex.Observable

/**
 * Created by vicky on 9/10/17.
 */

class AppDataManager @Inject
constructor(private val xmppHelper: XmppHelper, private val preferencesHelper: PreferencesHelper) : DataManager {

    override fun getIsUserLoggedIn(): Boolean {
        return preferencesHelper.isUserLoggedIn
    }

    override fun setIsUserLoggedIn() {
        preferencesHelper.setIsUserLoggedIn()
    }

    override fun getLoggedInUserId(): String {
        return preferencesHelper.loggedInUserId
    }

    override fun setLoggedInUserId(userId: String) {
        preferencesHelper.loggedInUserId = userId
    }

    override fun getLoggedInUserName(): String {
        return preferencesHelper.loggedInUserName
    }

    override fun setLoggedInUserName(userName: String) {
        preferencesHelper.loggedInUserName = userName
    }

    override fun getLoggedInUserToken(): String {
        return preferencesHelper.loggedInUserToken
    }

    override fun setLoggedInUserToken(token: String) {
        preferencesHelper.loggedInUserToken = token
    }

    override fun setUserAsLoggedOut() {
        preferencesHelper.setUserAsLoggedOut()
    }

    override fun connect(): Observable<AbstractXMPPConnection> {
        return xmppHelper.connect()
    }

    override fun login(userName: String, password: String): Observable<AbstractXMPPConnection> {
        return xmppHelper.login(userName, password)
    }

    override fun disconnect(): Observable<AbstractXMPPConnection> {
        return xmppHelper.disconnect()
    }

    override fun getChatManager(): ChatManager {
        return xmppHelper.getChatManager()
    }

    override fun getRoster(): Roster {
        return xmppHelper.getRoster()
    }

    override fun getUserSearchManager(): UserSearchManager {
        return xmppHelper.getUserSearchManager()
    }

    override fun sendMessage(message: String): Observable<Boolean> {
        return xmppHelper.sendMessage(message)
    }

    override fun getRosterEntries(): Observable<Collection<RosterEntry>> {
        return xmppHelper.getRosterEntries()
    }
}
