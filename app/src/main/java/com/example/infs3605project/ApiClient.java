package com.example.infs3605project;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //final String API_KEY = "08d54cfbf9e24f6d98db5e591a6f019e";

    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static ApiClient apiClient;
    private static Retrofit retrofit;

    private ApiClient(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized ApiClient getInstance(){
        if (apiClient == null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }


    public ApiInterface getApi(){
        return retrofit.create(ApiInterface.class);
    }
}
