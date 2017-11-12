package com.vicky7230.chat.ui.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vicky7230.chat.R;
import com.vicky7230.chat.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class ChatActivity extends BaseActivity implements ChatMvpView {

    @Inject
    ChatMvpPresenter<ChatMvpView> presenter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    ChatAdapter chatAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.chat_list)
    RecyclerView recyclerView;
    @BindView(R.id.message_edit_text)
    AppCompatEditText messageEditText;
    @BindView(R.id.send_message)
    AppCompatImageButton sendButton;


    public static Intent getStartIntent(Context context) {
        return new Intent(context, ChatActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        presenter.onAttach(this);
        init();
    }

    private void init() {
        setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(chatAdapter);
    }

    @OnClick(R.id.send_message)
    public void onSendClick(View view) {
        if (messageEditText.getText().toString().length() != 0) {
            presenter.sendMessage(messageEditText.getText().toString());
        }
    }

    @Override
    public void addMessageInList(String message) {
        runOnUiThread(() -> {//TODO fix memory leak
            chatAdapter.addItem(message);
            messageEditText.getText().clear();
        });
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }
}

