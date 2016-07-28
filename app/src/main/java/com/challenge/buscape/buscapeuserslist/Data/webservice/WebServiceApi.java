package com.challenge.buscape.buscapeuserslist.data.webservice;

import com.challenge.buscape.buscapeuserslist.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by eliete on 7/25/16.
 */
public interface WebServiceApi {

    @GET("users")
    Call<List<User>> getUsersList();

}
