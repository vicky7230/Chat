package com.vicky7230.chat.ui.chat;

import com.vicky7230.chat.ui.base.MvpPresenter;

/**
 * Created by vicky on 11/11/17.
 */

public interface ChatMvpPresenter<V extends ChatMvpView> extends MvpPresenter<V> {
    void sendMessage(String message);
}
