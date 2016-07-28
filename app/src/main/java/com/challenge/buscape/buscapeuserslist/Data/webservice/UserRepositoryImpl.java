package com.challenge.buscape.buscapeuserslist.data.webservice;

import android.os.Handler;

import com.challenge.buscape.buscapeuserslist.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eliete on 7/25/16.
 */
public class UserRepositoryImpl implements UserRepository {

    private List<User> list;
    boolean handler;

    @Override
    public void getUsersList(final getListOnFinishedListener listener) {

        WebServiceManager manager = new WebServiceApiManagerImpl();
        WebServiceApi webServiceApi = manager.getWebServiceApiInstance();
        webServiceApi.getUsersList().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    list = response.body();
                    callMainThreadToReturnList(list, listener);


                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                list = null;
                callMainThreadToReturnList(list, listener);
            }
        });


    }

    public void callMainThreadToReturnList(final List<User> list, final getListOnFinishedListener listener) {
        handler = new Handler().post(new Runnable() {
            @Override
            public void run() {
                listener.onFinishedList(list);
            }
        });
    }

}
