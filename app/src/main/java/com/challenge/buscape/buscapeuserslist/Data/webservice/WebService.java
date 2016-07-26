package com.challenge.buscape.buscapeuserslist.Data.webservice;

import com.challenge.buscape.buscapeuserslist.Data.model.User;

import java.util.List;

/**
 * Created by eliete on 7/25/16.
 */
public interface WebService {

    interface getListOnFinishedListener {
        void onFinishedList(List<User> items);
    }

    void getUsersList(getListOnFinishedListener listener);
}
