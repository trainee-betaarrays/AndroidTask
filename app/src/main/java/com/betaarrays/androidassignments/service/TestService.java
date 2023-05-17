package com.betaarrays.androidassignments.service;

import com.betaarrays.androidassignments.response.ResponseClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestService {
    @GET("/products")
    Call<ResponseClass> getPosts();
}
