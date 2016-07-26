package com.challenge.buscape.buscapeuserslist.Data.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eliete on 7/25/16.
 */
public class RetrofitWebServiceApiManagerImpl implements RetrofitWebServiceManager {

    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private RetrofitWebServiceApi retrofitWebServiceApi;

    public RetrofitWebServiceApiManagerImpl() {
        setupWebServiceApi();
    }

    @Override
    public RetrofitWebServiceApi getWebServiceApiInstance() {
        return retrofitWebServiceApi;
    }

    @Override
    public void setupWebServiceApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitWebServiceApi = retrofit.create(RetrofitWebServiceApi.class);
    }
}
