package com.vicky7230.chat.ui.chat;

import com.vicky7230.chat.data.DataManager;
import com.vicky7230.chat.ui.base.BasePresenter;

import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.EntityBareJid;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by vicky on 11/11/17.
 */

public class ChatPresenter<V extends ChatMvpView> extends BasePresenter<V> implements ChatMvpPresenter<V>, IncomingChatMessageListener {

    @Inject
    public ChatPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
        getDataManager().getChatManager().addIncomingListener(this);
    }

    @Override
    public void sendMessage(String message) {
        getCompositeDisposable().add(getDataManager().sendMessage(message)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    if (aBoolean) {
                        getMvpView().showMessage("Sent");
                        getMvpView().addMessageInList(message);
                    } else
                        getMvpView().showMessage("Something went wrong.");

                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    Timber.e(throwable);
                    getMvpView().showError(throwable.getMessage());
                })
        );
    }

    @Override
    public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
        getMvpView().addMessageInList(message.getBody());
    }
}
