package com.challenge.buscape.buscapeuserslist.Data.webservice;

import android.os.Handler;
import android.util.Log;

import com.challenge.buscape.buscapeuserslist.Data.model.User;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eliete on 7/25/16.
 */
public class WebServiceImpl implements WebService {

    private List<User> list;
    boolean handler;

    @Override
    public void getUsersList(final getListOnFinishedListener listener) {

        RetrofitWebServiceManager manager = new RetrofitWebServiceApiManagerImpl();
        RetrofitWebServiceApi webServiceApi = manager.getWebServiceApiInstance();
        webServiceApi.getUsersList().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.e("eliete", " response raw " + response.raw());
                if (response.isSuccessful()){
                    Log.e("eliete", " response.body " + response.body());
                    list = response.body();
                    callMainThreadToReturnList(list, listener);


                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("Retrofit request", "failed");
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
