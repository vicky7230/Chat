package com.vicky7230.chat

import android.app.Activity
import android.app.Application

import com.vicky7230.chat.di.component.DaggerApplicationComponent

import org.jivesoftware.smack.SmackConfiguration
import org.jivesoftware.smack.debugger.ReflectionDebuggerFactory

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber

/**
 * Created by vicky on 11/11/17.
 */

class ChatApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)

        Timber.plant(Timber.DebugTree())
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }
}
