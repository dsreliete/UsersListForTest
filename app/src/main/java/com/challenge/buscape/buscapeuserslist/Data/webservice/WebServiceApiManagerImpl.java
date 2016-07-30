package com.challenge.buscape.buscapeuserslist.data.webservice;

import android.content.Context;

import com.crashlytics.android.Crashlytics;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eliete on 7/25/16.
 */
public class WebServiceApiManagerImpl implements WebServiceManager {

    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private WebServiceApi webServiceApi;
//    private Context context;

    public WebServiceApiManagerImpl(Context context) {
//        this.context = context;
        Crashlytics.log("setup Web Service Api failed");
        setupWebServiceApi();
    }

    @Override
    public WebServiceApi getWebServiceApiInstance() {
        return webServiceApi;
    }

    @Override
    public void setupWebServiceApi() {

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient okHTTPClient = new OkHttpClient
//                .Builder()
//                .cache(new Cache(new File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString()), 8 * 1024 * 1024))
//                .addInterceptor(responseInterceptor())
//                .addInterceptor(logging)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .connectTimeout(5, TimeUnit.SECONDS)
//                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHTTPClient)
                .build();

        webServiceApi = retrofit.create(WebServiceApi.class);
    }

//    private Interceptor responseInterceptor(){
//        Interceptor interceptor = new Interceptor() {
//        @Override public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            if (MainActivity.hasConnection(context)) {
//                request = request.newBuilder()
//                .header("Cache-Control", "public, max-age=" + 60).build();
//            } else {
//                request = request.newBuilder()
//                .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24).build();
//            }
//            return chain.proceed(request);
//        }
//        };
//        return interceptor;
//    }
}

