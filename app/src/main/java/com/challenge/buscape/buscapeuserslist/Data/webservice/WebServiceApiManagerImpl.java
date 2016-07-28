package com.challenge.buscape.buscapeuserslist.data.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eliete on 7/25/16.
 */
public class WebServiceApiManagerImpl implements WebServiceManager {

    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private WebServiceApi webServiceApi;

    public WebServiceApiManagerImpl() {
        setupWebServiceApi();
    }

    @Override
    public WebServiceApi getWebServiceApiInstance() {
        return webServiceApi;
    }

    @Override
    public void setupWebServiceApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webServiceApi = retrofit.create(WebServiceApi.class);
    }
}
