package com.vicky7230.chat.ui.contacts;

import com.vicky7230.chat.ui.base.MvpPresenter;

/**
 * Created by vicky on 13/11/17.
 */

public interface ContactsMvpPresenter<V extends ContactsMvpView> extends MvpPresenter<V> {
    void getRoster();
}
