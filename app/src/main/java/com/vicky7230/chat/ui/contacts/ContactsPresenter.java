package com.vicky7230.chat.ui.contacts;

import com.vicky7230.chat.data.DataManager;
import com.vicky7230.chat.ui.base.BasePresenter;

import org.jivesoftware.smack.roster.RosterEntry;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by vicky on 13/11/17.
 */

public class ContactsPresenter<V extends ContactsMvpView> extends BasePresenter<V> implements ContactsMvpPresenter<V> {

    @Inject
    public ContactsPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }


    @Override
    public void getRoster() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager().getRosterEntries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rosterEntries -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                    Timber.e("Size : " + rosterEntries.size() + "");
                    for (RosterEntry rosterEntry :
                            rosterEntries) {
                        Timber.e(rosterEntry.getName());
                    }
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                    Timber.e(throwable);
                    getMvpView().showError(throwable.getMessage());
                })
        );
    }
}
