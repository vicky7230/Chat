package com.vicky7230.chat.ui.base;


import com.vicky7230.chat.data.DataManager;

import org.jivesoftware.smack.AbstractXMPPConnection;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by vicky on 9/10/17.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = BasePresenter.class.getSimpleName();

    private final DataManager dataManager;
    private final CompositeDisposable compositeDisposable;

    private V mvpView;

    @Inject
    public BasePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        this.dataManager = dataManager;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
        this.mvpView = null;
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public V getMvpView() {
        return mvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

    @Override
    public void handleApiError(Throwable throwable) {

        Timber.e(throwable);

        /*String errorMessage;
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            switch (httpException.code()) {
                case 504:
                    errorMessage = "Network ValidationError";
                    break;
                default:
                    errorMessage = "Some network ValidationError";
                    break;
            }
        } else if (throwable instanceof SocketTimeoutException) {
            errorMessage = "Connection timed out";
        } else if (throwable instanceof IOException) {
            errorMessage = "Network ValidationError";
        } else {
            errorMessage = "Some ValidationError";
        }*/

        getMvpView().showError(throwable.getLocalizedMessage().split(":")[0]);

    }

    @Override
    public void disconnect() {
        getMvpView().showLoading();
        compositeDisposable.add(getDataManager().disconnect()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(abstractXMPPConnection -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                    Timber.e(throwable);
                    getMvpView().showError(throwable.getMessage());
                }));
    }

/*
    @Override
    public void setUserAsLoggedOut() {
        getDataManager().setUserAsLoggedOut();
    }
*/


}
