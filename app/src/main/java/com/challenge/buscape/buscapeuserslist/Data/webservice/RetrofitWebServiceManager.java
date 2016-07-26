package com.challenge.buscape.buscapeuserslist.Data.webservice;

/**
 * Created by eliete on 7/25/16.
 */
public interface RetrofitWebServiceManager {

    RetrofitWebServiceApi getWebServiceApiInstance();

    void setupWebServiceApi();
}
