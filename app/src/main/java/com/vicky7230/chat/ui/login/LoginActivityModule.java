package com.vicky7230.chat.ui.login;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vicky on 9/10/17.
 */

@Module
public class LoginActivityModule {

    @Provides
    LoginMvpPresenter<LoginMvpView> provideSearchMvpPresenter(LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }
}
