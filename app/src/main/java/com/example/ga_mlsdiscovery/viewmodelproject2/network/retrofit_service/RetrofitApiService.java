package com.example.ga_mlsdiscovery.viewmodelproject2.network.retrofit_service;

import com.example.ga_mlsdiscovery.viewmodelproject2.network.endpoints.FotoServiceEndpoint;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class RetrofitApiService {

    private final static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static FotoServiceEndpoint endpoint;
    private static Retrofit retrofit;

    //adding a logging interceptor
    private static HttpLoggingInterceptor httpLoggingInterceptor =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    //Client to hold interceptor. In our case "httpLoggingInterceptor"
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static FotoServiceEndpoint getInstance() {
        if(endpoint != null){
            return endpoint;
        }

        if(retrofit == null){
            initializeRetrofit();
        }

        endpoint = retrofit.create(FotoServiceEndpoint.class);
        return endpoint;
    }

    public static void initializeRetrofit() {
        httpClient.addInterceptor(httpLoggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient.build())
                .build();
    }



    //The service interface class is placed in here and generated
//    public <s> s createService(Class<s>serviceClass){
//        if(!httpClient.interceptors().contains(httpLoggingInterceptor)){
//            httpClient.addInterceptor(httpLoggingInterceptor);
//            builder.client(httpClient.build());
//            retrofit = builder.build();
//        }
//        return retrofit.create(serviceClass);
//    }
    //make singleton
    private RetrofitApiService(){}
}

