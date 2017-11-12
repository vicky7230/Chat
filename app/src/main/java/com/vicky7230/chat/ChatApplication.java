package com.vicky7230.chat;

import android.app.Activity;
import android.app.Application;

import com.vicky7230.chat.di.component.DaggerApplicationComponent;

import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.debugger.ReflectionDebuggerFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

/**
 * Created by vicky on 11/11/17.
 */

public class ChatApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this);

        Timber.plant(new Timber.DebugTree());
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
