package com.challenge.buscape.buscapeuserslist.users;

import android.support.annotation.NonNull;

import com.challenge.buscape.buscapeuserslist.data.model.User;
import com.challenge.buscape.buscapeuserslist.data.webservice.UserRepository;

import java.util.List;

/**
 * Created by eliete on 7/25/16.
 */
public class MainPresenter implements MainContract.UserActionListener,
        UserRepository.getListOnFinishedListener {

    private MainContract.View mainContract;
    private UserRepository userRepository;

    public MainPresenter(MainActivity mainActivity, UserRepository service) {
        mainContract = mainActivity;
        userRepository = service;
    }

    @Override
    public void fetchUserList() {
        if (mainContract != null) {
            mainContract.showProgress();
        }

        userRepository.getUsersList(this);
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
