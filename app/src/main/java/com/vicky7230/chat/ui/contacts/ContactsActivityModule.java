package com.vicky7230.chat.ui.contacts;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vicky on 13/11/17.
 */

@Module
public class ContactsActivityModule {

    @Provides
    ContactsMvpPresenter<ContactsMvpView> provideContactsMvpPresenter(ContactsPresenter<ContactsMvpView> presenter) {
        return presenter;
    }
}
