package com.vicky7230.chat.di.component;

import com.vicky7230.chat.ChatApplication;
import com.vicky7230.chat.di.module.ActivityBindingModule;
import com.vicky7230.chat.di.module.ApplicationModule;
import com.vicky7230.chat.di.module.XmppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by vicky on 11/11/17.
 */

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        XmppModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class
})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(ChatApplication chatApplication);

        ApplicationComponent build();
    }

    void inject(ChatApplication chatApplication);

}
