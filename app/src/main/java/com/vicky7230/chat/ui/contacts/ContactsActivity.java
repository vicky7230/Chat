package com.vicky7230.chat.ui.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.vicky7230.chat.R;
import com.vicky7230.chat.ui.base.BaseActivity;

import org.jivesoftware.smackx.search.UserSearchManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class ContactsActivity extends BaseActivity implements ContactsMvpView {

    @Inject
    ContactsMvpPresenter<ContactsMvpView> presenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, ContactsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);
        presenter.onAttach(this);
        init();
    }

    private void init() {
        presenter.getRoster();
    }
}
