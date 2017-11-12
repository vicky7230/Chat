package com.vicky7230.chat.ui.base;

/**
 * Created by vicky on 9/10/17.
 */

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(Throwable throwable);

    //void setUserAsLoggedOut();

    void disconnect();
}