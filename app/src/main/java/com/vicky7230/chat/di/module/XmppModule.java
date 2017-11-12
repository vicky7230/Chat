package com.vicky7230.chat.di.module;

import com.vicky7230.chat.data.Config;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ReconnectionManager;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.debugger.ReflectionDebuggerFactory;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.stringprep.XmppStringprepException;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

/**
 * Created by vicky on 11/11/17.
 */

@Module
public class XmppModule {

    @Singleton
    @Provides
    XMPPTCPConnectionConfiguration providesXMPPTCPConnectionConfiguration() {
        try {
            return XMPPTCPConnectionConfiguration.builder()
                    .setXmppDomain(Config.XMPP_DOMAIN)
                    .setHostAddress(InetAddress.getByName(Config.HOST_ADDRESS))
                    .setPort(5222)
                    .setSendPresence(true)
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                    .setDebuggerEnabled(true)
                    .build();
        } catch (XmppStringprepException e) {
            Timber.e(e);
            return null;//TODO
        } catch (UnknownHostException e) {
            Timber.e(e);
            return null;//TODO
        }
    }

    @Singleton
    @Provides
    AbstractXMPPConnection providesAbstractXMPPConnection(XMPPTCPConnectionConfiguration config) {
        XMPPTCPConnection xmpptcpConnection = new XMPPTCPConnection(config);
        ReconnectionManager.getInstanceFor(xmpptcpConnection).disableAutomaticReconnection();
        return xmpptcpConnection;
    }
}
