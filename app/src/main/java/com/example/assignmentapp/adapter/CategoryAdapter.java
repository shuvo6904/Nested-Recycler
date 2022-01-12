package com.example.assignmentapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentapp.databinding.ActivityMainBinding;
import com.example.assignmentapp.databinding.CategoryListSingleRowBinding;
import com.example.assignmentapp.model.Datum;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    List<Datum> categoryData;

    public CategoryAdapter(List<Datum> categoryData) {
        this.categoryData = categoryData;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CategoryListSingleRowBinding categoryListSingleRowBinding = CategoryListSingleRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryViewHolder(categoryListSingleRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        holder.setData(categoryData.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryData.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{


        CategoryListSingleRowBinding categoryListSingleRowBinding;

        public CategoryViewHolder(@NonNull CategoryListSingleRowBinding categoryListSingleRowBinding){
            super(categoryListSingleRowBinding.getRoot());

            this.categoryListSingleRowBinding = categoryListSingleRowBinding;

        }

        public void setData(Datum datum) {
            categoryListSingleRowBinding.itemId.setText(datum.getName());

        }
    }
}
