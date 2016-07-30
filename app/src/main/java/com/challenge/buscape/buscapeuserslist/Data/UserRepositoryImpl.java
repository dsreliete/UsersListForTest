package com.challenge.buscape.buscapeuserslist.data;

import android.content.Context;
import android.os.Handler;

import com.challenge.buscape.buscapeuserslist.data.model.User;
import com.challenge.buscape.buscapeuserslist.data.webservice.WebServiceApi;
import com.challenge.buscape.buscapeuserslist.data.webservice.WebServiceApiManagerImpl;
import com.challenge.buscape.buscapeuserslist.data.webservice.WebServiceManager;
import com.crashlytics.android.Crashlytics;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eliete on 7/25/16.
 */
public class UserRepositoryImpl implements UserRepository {

//    public static final String PREF_TIME = "first_time";
    private List<User> list;
    boolean handler;

    @Override
    public void getUsersList(final getListOnFinishedListener listener, Context context) {
        Crashlytics.log("Web Service Api response");

        WebServiceManager manager = new WebServiceApiManagerImpl(context);
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

//    @Override
//    public void saveFirstTimeCondition(boolean firstTime, Activity mainContract) {
//        SharedPreferences sharedPref = mainContract.getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putBoolean(PREF_TIME, firstTime);
//        editor.apply();
//    }
//
//    @Override
//    public boolean getSavedFirstCondition(Activity mainContract) {
//        SharedPreferences sharedPref = mainContract.getPreferences(Context.MODE_PRIVATE);
//        return sharedPref.getBoolean(PREF_TIME, false);
//    }
}
