package com.vicky7230.chat.di.module;

import com.vicky7230.chat.ui.chat.ChatActivity;
import com.vicky7230.chat.ui.chat.ChatActivityModule;
import com.vicky7230.chat.ui.contacts.ContactsActivity;
import com.vicky7230.chat.ui.contacts.ContactsActivityModule;
import com.vicky7230.chat.ui.login.LoginActivity;
import com.vicky7230.chat.ui.login.LoginActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by vicky on 11/11/17.
 */

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = ContactsActivityModule.class)
    abstract ContactsActivity bindContactsActivity();

    @ContributesAndroidInjector(modules = ChatActivityModule.class)
    abstract ChatActivity bindHomeActivity();
}
