package com.example.assignmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.assignmentapp.adapter.CategoryAdapter;
import com.example.assignmentapp.databinding.ActivityLoginBinding;
import com.example.assignmentapp.databinding.ActivityMainBinding;
import com.example.assignmentapp.model.CategoryResponseModel;
import com.example.assignmentapp.model.Datum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    CategoryAdapter categoryAdapter;
    CategoryResponseModel categoryResponseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

       // categoryResponseModel = getIntent().getSerializableExtra("categoryModel");
        categoryResponseModel = (CategoryResponseModel) getIntent().getSerializableExtra("categoryModel");
        List<Datum> categoryData = categoryResponseModel.getData();

        categoryAdapter = new CategoryAdapter(categoryData);
        mainBinding.mainRecyclerId.setAdapter(categoryAdapter);

    }
}