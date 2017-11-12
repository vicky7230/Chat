package com.vicky7230.chat.ui.login;


import com.vicky7230.chat.data.DataManager;
import com.vicky7230.chat.ui.base.BasePresenter;

import org.jivesoftware.smack.AbstractXMPPConnection;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by vicky on 9/10/17.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    @Inject
    public LoginPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void performLogin(String username, String password) {
        getMvpView().showLoading();

        Observable<AbstractXMPPConnection> connectObservable = getDataManager().connect();
        Observable<AbstractXMPPConnection> loginObservable = getDataManager().login(username, password);

        Observable<Boolean> zip = Observable.zip(connectObservable, loginObservable, (abstractXMPPConnection, abstractXMPPConnection1) -> (abstractXMPPConnection.isConnected() && abstractXMPPConnection1.isAuthenticated()));

        getCompositeDisposable().add(zip
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            getMvpView().hideLoading();
                            if (aBoolean) {
                                getMvpView().showMessage("Login Successful.");
                                getMvpView().goToContacts();
                            } else
                                getMvpView().showMessage("Login Failed.");
                        }, throwable -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            getMvpView().hideLoading();
                            Timber.e(throwable);
                            getMvpView().showError(throwable.getMessage());
                            disconnect();
                        }
                )
        );
    }
}
