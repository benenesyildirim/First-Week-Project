package com.example.enesyildirim.firstweeksproject.service;

import com.example.enesyildirim.firstweeksproject.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface//TODO Kısa bir anlatayım?
{
    @GET("search/repositories?q=tetris+language:assembly&sort=stars&order=desc")
    Call<ResponseModel> getJSON();
}