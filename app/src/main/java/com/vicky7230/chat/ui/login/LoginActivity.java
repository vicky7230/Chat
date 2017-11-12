package com.vicky7230.chat.ui.login;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;

import com.vicky7230.chat.R;
import com.vicky7230.chat.ui.base.BaseActivity;
import com.vicky7230.chat.ui.chat.ChatActivity;
import com.vicky7230.chat.ui.contacts.ContactsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginMvpPresenter<LoginMvpView> presenter;

    @BindView(R.id.user_name)
    AppCompatEditText userNameEditText;
    @BindView(R.id.password)
    AppCompatEditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter.onAttach(this);
        init();
    }

    private void init() {

    }

    @OnClick(R.id.login_button)
    public void onLoginClick(View view) {
        if (!TextUtils.isEmpty(userNameEditText.getText()) &&
                !TextUtils.isEmpty(passwordEditText.getText())) {
            presenter.performLogin(
                    userNameEditText.getText().toString(),
                    passwordEditText.getText().toString()
            );
        }
    }

    @Override
    public void goToContacts() {
        startActivity(ContactsActivity.getStartIntent(this));
        finish();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }
}
