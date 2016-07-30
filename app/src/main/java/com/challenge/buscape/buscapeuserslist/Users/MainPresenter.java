package com.challenge.buscape.buscapeuserslist.users;

import android.content.Context;
import android.support.annotation.NonNull;

import com.challenge.buscape.buscapeuserslist.data.UserRepository;
import com.challenge.buscape.buscapeuserslist.data.model.User;

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
        userRepository.getUsersList(this, (Context) mainContract);
    }

    @Override
    public void onFinishedList(List<User> list) {
        mainContract.hideProgress();
        mainContract.showUsersList(list);
    }

    @Override
    public void openItemDetails(@NonNull User requestedUser) {
        mainContract.showUserDetailActivity(requestedUser);
    }

//    @Override
//    public void saveConditionTime(boolean firstTime) {
//        userRepository.saveFirstTimeCondition(firstTime, (Activity) mainContract);
//    }
//
//    @Override
//    public boolean getSavedConditionTime() {
//        return userRepository.getSavedFirstCondition((Activity) mainContract);
//    }

}
