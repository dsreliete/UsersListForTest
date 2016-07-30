package com.challenge.buscape.buscapeuserslist.data;

import android.content.Context;

import com.challenge.buscape.buscapeuserslist.data.model.User;

import java.util.List;

/**
 * Created by eliete on 7/25/16.
 */
public interface UserRepository {

    interface getListOnFinishedListener {
        void onFinishedList(List<User> items);
    }

    void getUsersList(getListOnFinishedListener listener, Context context);

//    void saveFirstTimeCondition(boolean firstTime, Activity mainContract);
//
//    boolean getSavedFirstCondition(Activity mainContract);


}
