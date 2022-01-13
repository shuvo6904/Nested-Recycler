package com.example.assignmentapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.assignmentapp.R;
import com.example.assignmentapp.databinding.ActivityLoginBinding;
import com.example.assignmentapp.model.CategoryResponseModel;
import com.example.assignmentapp.model.LoginDataModel;
import com.example.assignmentapp.model.LoginResponseModel;
import com.example.assignmentapp.service.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    public static String token;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://aadhyandashboard.in/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);

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
                Call<LoginResponseModel> call = userClient.login(loginDataModel);

                call.enqueue(new Callback<LoginResponseModel>() {
                    @Override
                    public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                        if (response.isSuccessful()){

                            Toast.makeText(LoginActivity.this, "Token : " + response.body().getData().getToken(), Toast.LENGTH_SHORT).show();
                            token = response.body().getData().getToken();
                            getCategoryResponse(token);

                        }
                        else {
                            Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                        Toast.makeText(LoginActivity.this, "Error :(", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }

    private void getCategoryResponse(String token) {
        Call<CategoryResponseModel> call = userClient.getCategoryList(token);

        call.enqueue(new Callback<CategoryResponseModel>() {
            @Override
            public void onResponse(Call<CategoryResponseModel> call, Response<CategoryResponseModel> response) {
                if (response.isSuccessful()){

                    Toast.makeText(LoginActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();

                }

                else {
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponseModel> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Error to load category data :(", Toast.LENGTH_SHORT).show();

            }
        });
    }
}