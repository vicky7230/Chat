package com.vicky7230.chat.di.module;

import android.content.Context;

import com.vicky7230.chat.ChatApplication;
import com.vicky7230.chat.data.AppDataManager;
import com.vicky7230.chat.data.Config;
import com.vicky7230.chat.data.DataManager;
import com.vicky7230.chat.data.network.AppXmppHelper;
import com.vicky7230.chat.data.network.XmppHelper;
import com.vicky7230.chat.data.prefs.AppPreferencesHelper;
import com.vicky7230.chat.data.prefs.PreferencesHelper;
import com.vicky7230.chat.di.ApplicationContext;
import com.vicky7230.chat.di.HostAddress;
import com.vicky7230.chat.di.XmppDomain;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by vicky on 11/11/17.
 */

@Module
public class ApplicationModule {
    @Provides
    @ApplicationContext
    Context provideContext(ChatApplication paprikaApplication) {
        return paprikaApplication.getApplicationContext();
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @HostAddress
    String provideHostAddress() {
        return Config.HOST_ADDRESS;
    }

    @Provides
    @XmppDomain
    String provideXmppDomain() {
        return Config.XMPP_DOMAIN;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    XmppHelper provideDbHelper(AppXmppHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper provideApiHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }
}
