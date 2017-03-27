package com.u.tallerify.presenter.home.cards;

import android.support.annotation.NonNull;
import com.u.tallerify.contract.abstracts.cards.NoAccountCardContract;
import com.u.tallerify.controller.login.LoginDialogController;
import com.u.tallerify.utils.adapter.GenericAdapter;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by saguilera on 3/12/17.
 */
public class NoAccountCardPresenter extends GenericAdapter.ItemPresenter<NoAccountCardContract.View>
        implements NoAccountCardContract.Presenter {

    @Override
    protected void onAttach(@NonNull final NoAccountCardContract.View view) {
        view.observeCreateAccountsClicks()
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(this.<Void>bindToLifecycle())
            .subscribe(new Action1<Void>() {
                @Override
                public void call(final Void aVoid) {
                    showDialog(new LoginDialogController(), LoginDialogController.class.getName());
                }
            });
    }

}
