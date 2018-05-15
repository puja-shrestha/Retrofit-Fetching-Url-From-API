package com.example.puza.retrofitfetchingurlfromapi.Interface;

import com.example.puza.retrofitfetchingurlfromapi.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Item>> getApi();
}
