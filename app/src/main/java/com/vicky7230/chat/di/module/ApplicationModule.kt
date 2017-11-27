package com.vicky7230.chat.di.module

import android.content.Context
import com.vicky7230.chat.ChatApplication
import com.vicky7230.chat.data.AppDataManager
import com.vicky7230.chat.data.Config
import com.vicky7230.chat.data.DataManager
import com.vicky7230.chat.data.network.AppXmppHelper
import com.vicky7230.chat.data.network.XmppHelper
import com.vicky7230.chat.data.prefs.AppPreferencesHelper
import com.vicky7230.chat.data.prefs.PreferencesHelper
import com.vicky7230.chat.di.ApplicationContext
import com.vicky7230.chat.di.HostAddress
import com.vicky7230.chat.di.XmppDomain
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by vicky on 11/11/17.
 */

@Module
class ApplicationModule {
    @Provides
    @ApplicationContext
    fun provideContext(paprikaApplication: ChatApplication): Context {
        return paprikaApplication.applicationContext
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @HostAddress
    fun provideHostAddress(): String {
        return Config.HOST_ADDRESS
    }

    @Provides
    @XmppDomain
    fun provideXmppDomain(): String {
        return Config.XMPP_DOMAIN
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: AppXmppHelper): XmppHelper {
        return appDbHelper
    }

    @Provides
    @Singleton
    fun provideApiHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }
}
