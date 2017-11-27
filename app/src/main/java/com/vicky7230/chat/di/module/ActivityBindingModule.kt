package com.vicky7230.chat.di.module

import com.vicky7230.chat.ui.chat.ChatActivity
import com.vicky7230.chat.ui.chat.ChatActivityModule
import com.vicky7230.chat.ui.contacts.ContactsActivity
import com.vicky7230.chat.ui.contacts.ContactsActivityModule
import com.vicky7230.chat.ui.login.LoginActivity
import com.vicky7230.chat.ui.login.LoginActivityModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by vicky on 11/11/17.
 */

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = arrayOf(LoginActivityModule::class))
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = arrayOf(ContactsActivityModule::class))
    abstract fun bindContactsActivity(): ContactsActivity

    @ContributesAndroidInjector(modules = arrayOf(ChatActivityModule::class))
    abstract fun bindHomeActivity(): ChatActivity
}
