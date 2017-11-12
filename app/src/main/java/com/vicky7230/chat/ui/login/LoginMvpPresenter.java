package com.vicky7230.chat.ui.login;


import com.vicky7230.chat.ui.base.MvpPresenter;

/**
 * Created by vicky on 9/10/17.
 */


public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void performLogin(String username, String password);
}