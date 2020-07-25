package com.example.infs3605project;

import com.example.infs3605project.model.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    /*
    @GET("top-headlines")
    Call<Headlines> getHeadlines(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    ); */

    @GET("everything")
    Call<Headlines> getSpecificData(
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );

    @GET("everything?q=cyber&apiKey=08d54cfbf9e24f6d98db5e591a6f019e")
    Call<Headlines> getCyberHeadlines(
    );


}
