package com.challenge.buscape.userslist.Data.webservice;

import com.challenge.buscape.buscapeuserslist.Data.webservice.WebService;
import com.challenge.buscape.buscapeuserslist.Data.webservice.WebServiceImpl;

/**
 * Created by eliete on 7/25/16.
 */
public class Injection {

    public static WebService provideUsersList(){
        return new WebServiceImpl();
    }
}
