package com.vicky7230.chat.di.component

import com.vicky7230.chat.ChatApplication
import com.vicky7230.chat.di.module.ActivityBindingModule
import com.vicky7230.chat.di.module.ApplicationModule
import com.vicky7230.chat.di.module.XmppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by vicky on 11/11/17.
 */

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        XmppModule::class,
        ApplicationModule::class,
        ActivityBindingModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(chatApplication: ChatApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(chatApplication: ChatApplication)

}
