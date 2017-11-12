package com.vicky7230.chat.ui.base;

/**
 * Created by vicky on 9/10/17.
 */

public interface MvpView {

    void showLoading();

    void hideLoading();

    void showMessage(String message);

    void showError(String message);

    //void openLoginActivityOnTokenExpire();
}