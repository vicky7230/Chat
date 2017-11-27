package com.vicky7230.chat.ui.login

import android.os.Bundle
import android.support.v7.widget.AppCompatEditText
import android.text.TextUtils
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.vicky7230.chat.R
import com.vicky7230.chat.ui.base.BaseActivity
import com.vicky7230.chat.ui.contacts.ContactsActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginMvpView {

    @Inject
    lateinit var presenter: LoginMvpPresenter<LoginMvpView>

    @BindView(R.id.user_name)
    lateinit var userNameEditText: AppCompatEditText
    @BindView(R.id.password)
    lateinit var passwordEditText: AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
        presenter.onAttach(this)
        user_name
    }

    @OnClick(R.id.login_button)
    fun onLoginClick(view: View) {
        if (!TextUtils.isEmpty(userNameEditText.text) && !TextUtils.isEmpty(passwordEditText.text)) {
            presenter.performLogin(
                    userNameEditText.text.toString(),
                    passwordEditText.text.toString()
            )
        }
    }

    override fun goToContacts() {
        startActivity(ContactsActivity.getStartIntent(this))
        finish()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
