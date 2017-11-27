package com.vicky7230.chat.di.module

import com.vicky7230.chat.data.Config
import dagger.Module
import dagger.Provides
import org.jivesoftware.smack.AbstractXMPPConnection
import org.jivesoftware.smack.ConnectionConfiguration
import org.jivesoftware.smack.ReconnectionManager
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import org.jxmpp.stringprep.XmppStringprepException
import timber.log.Timber
import java.net.InetAddress
import java.net.UnknownHostException
import javax.inject.Singleton

/**
 * Created by vicky on 11/11/17.
 */

@Module
class XmppModule {

    @Singleton
    @Provides
    internal fun providesXMPPTCPConnectionConfiguration(): XMPPTCPConnectionConfiguration? {
        try {
            return XMPPTCPConnectionConfiguration.builder()
                    .setXmppDomain(Config.XMPP_DOMAIN)
                    .setHostAddress(InetAddress.getByName(Config.HOST_ADDRESS))
                    .setPort(5222)
                    .setSendPresence(true)
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                    .setDebuggerEnabled(true)
                    .build()
        } catch (e: XmppStringprepException) {
            Timber.e(e)
            return null//TODO
        } catch (e: UnknownHostException) {
            Timber.e(e)
            return null//TODO
        }

    }

    @Singleton
    @Provides
    internal fun providesAbstractXMPPConnection(config: XMPPTCPConnectionConfiguration?): AbstractXMPPConnection {
        val xmpptcpConnection = XMPPTCPConnection(config)
        ReconnectionManager.getInstanceFor(xmpptcpConnection).disableAutomaticReconnection()
        return xmpptcpConnection
    }
}
