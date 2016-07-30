package com.challenge.buscape.buscapeuserslist.users;

import android.support.annotation.NonNull;

import com.challenge.buscape.buscapeuserslist.data.model.User;

import java.util.List;

/**
 * Created by eliete on 7/25/16.
 */
public interface MainContract {

    interface View{

        void showProgress();

        void hideProgress();

        void showUsersList(List<User> list);

        void showUserDetailActivity(User user);

    }

    interface UserActionListener{

        void fetchUserList();

        void openItemDetails(@NonNull User requestedUser);

        //void saveConditionTime(boolean firstTime);

        //boolean getSavedConditionTime();

    }
}
