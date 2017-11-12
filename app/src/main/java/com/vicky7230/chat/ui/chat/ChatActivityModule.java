package com.vicky7230.chat.ui.chat;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.vicky7230.chat.di.ApplicationContext;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vicky on 11/11/17.
 */

@Module
public class ChatActivityModule {

    @Provides
    ChatMvpPresenter<ChatMvpView> provideChatMvpPresenter(ChatPresenter<ChatMvpView> presenter) {
        return presenter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(@ApplicationContext Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    ChatAdapter provideChatAdapter() {
        return new ChatAdapter(new ArrayList<>());
    }
}
