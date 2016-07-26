package com.challenge.buscape.buscapeuserslist.Users;

import android.support.annotation.NonNull;

import com.challenge.buscape.buscapeuserslist.Data.model.User;
import com.challenge.buscape.buscapeuserslist.Data.webservice.WebService;

import java.util.List;

/**
 * Created by eliete on 7/25/16.
 */
public class MainPresenter implements MainContract.UserActionListener, WebService.getListOnFinishedListener {

    private MainContract.View mainContract;
    private WebService webService;

    public MainPresenter(MainActivity mainActivity, WebService service) {
        mainContract = mainActivity;
        webService = service;
    }

    @Override
    public void fetchUserList() {
        if (mainContract != null) {
            mainContract.showProgress();
        }

        webService.getUsersList(this);
    }

    @Override
    public void onFinishedList(List<User> list) {
        mainContract.hideProgress();
        mainContract.showUsersList(list);
    }

    @Override
    public void onDestroy() {
        mainContract = null;

    }

    @Override
    public void openItemDetails(@NonNull User requestedUser) {
        mainContract.showUserDetailActivity(requestedUser);
    }
}
