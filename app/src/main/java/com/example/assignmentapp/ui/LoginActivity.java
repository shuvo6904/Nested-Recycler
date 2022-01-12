package com.example.assignmentapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.assignmentapp.MainActivity;
import com.example.assignmentapp.databinding.ActivityLoginBinding;
import com.example.assignmentapp.model.CategoryResponseModel;
import com.example.assignmentapp.model.LoginDataModel;
import com.example.assignmentapp.model.LoginResponseModel;
import com.example.assignmentapp.network.ApiInterface;
import com.example.assignmentapp.network.RetrofitClient;
import com.google.gson.Gson;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    public static String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.loginButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = binding.loginMobileId.getText().toString();
                String password = binding.loginPasswordId.getText().toString();

                LoginDataModel loginDataModel = new LoginDataModel(mobile, password);
                Call<LoginResponseModel> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .login(loginDataModel);

                call.enqueue(new Callback<LoginResponseModel>() {
                    @Override
                    public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                        if (response.isSuccessful()){

                            //Toast.makeText(LoginActivity.this, "Token : " + response.body().getData().getToken(), Toast.LENGTH_SHORT).show();
                            token = response.body().getData().getToken();
                            getCategoryResponse(token);

                        }
                        else {
                            Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }

    private void getCategoryResponse(String token) {
        Call<CategoryResponseModel> call = RetrofitClient
                .getInstance()
                .getApi()
                .getCategoryList("Bearer "+ token);



        call.enqueue(new Callback<CategoryResponseModel>() {
            @Override
            public void onResponse(Call<CategoryResponseModel> call, Response<CategoryResponseModel> response) {
                if (response.isSuccessful()){
                    //Log.e("getCategoryResponse", "onResponse: " + new Gson().toJson(response.body()));
                    //Toast.makeText(LoginActivity.this, new Gson().toJson(response.body()), Toast.LENGTH_SHORT).show();
                    //CategoryResponseModel categoryResponseModel = response.body();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("categoryModel", response.body());
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponseModel> call, Throwable t) {

                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}