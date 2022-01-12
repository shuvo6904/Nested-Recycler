package com.example.assignmentapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static String BASE_URL = "https://aadhyandashboard.in/";
    public static RetrofitClient retrofitClient;
    public static Retrofit retrofit;

    //for debugging purpose
    public OkHttpClient.Builder builder = new OkHttpClient.Builder();
    public HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    public RetrofitClient() {
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(builder.build())
                .build();

    }

    public static synchronized RetrofitClient getInstance(){
        if (retrofitClient == null)
            retrofitClient = new RetrofitClient();
        return retrofitClient;
    }

    public ApiInterface getApi(){
        return  retrofit.create(ApiInterface.class);
    }


}