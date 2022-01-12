package com.example.assignmentapp.network;

import com.example.assignmentapp.model.CategoryResponseModel;
import com.example.assignmentapp.model.LoginDataModel;
import com.example.assignmentapp.model.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("api/login")
    Call<LoginResponseModel> login(@Body LoginDataModel loginDataModel);

    @POST("api/get-category-list")
    Call<CategoryResponseModel> getCategoryList(@Header("Authorization") String authToken);
}
