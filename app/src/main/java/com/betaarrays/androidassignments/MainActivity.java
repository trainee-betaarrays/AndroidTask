package com.betaarrays.androidassignments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.betaarrays.androidassignments.adapter.AdapterClass;
import com.betaarrays.androidassignments.databinding.ActivityMainBinding;
import com.betaarrays.androidassignments.mvvm.MainViewModel;
import com.betaarrays.androidassignments.mvvm.Repo;
import com.betaarrays.androidassignments.util.CheckInternet;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
MainViewModel model;
Repo repo;
AdapterClass adapter;
CheckInternet internet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repo=new Repo(this);
        model=new MainViewModel(this);
        internet=new CheckInternet(this);
        binding.progressBar.setVisibility(View.VISIBLE);

        model.getData(this).observe(this,responseClass -> {
            if (responseClass.getProducts()!=null){
                if (internet.isOnline()){
                    binding.progressBar.setVisibility(View.GONE);
                    binding.noInternet.setVisibility(View.GONE);
                    binding.recyclerView.setVisibility(View.VISIBLE);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adapter=new AdapterClass(responseClass.getProducts(),MainActivity.this);
                    binding.recyclerView.setAdapter(adapter);
                }
                else {
                    binding.recyclerView.setVisibility(View.GONE);
                    binding.noInternet.setVisibility(View.VISIBLE);
                }
                }
        });

        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                model.getData(MainActivity.this).observe(MainActivity.this,responseClass -> {
                    if (responseClass.getProducts()!=null){
                        if (internet.isOnline()){
                            binding.noInternet.setVisibility(View.GONE);
                            binding.recyclerView.setVisibility(View.VISIBLE);
                            binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            adapter=new AdapterClass(responseClass.getProducts(),MainActivity.this);
                            binding.recyclerView.setAdapter(adapter);
                        }
                        else {
                            binding.recyclerView.setVisibility(View.GONE);
                            binding.noInternet.setVisibility(View.VISIBLE);
                        }
                    }
                });

                binding.refresh.setRefreshing(false);
            }
        });


    }
}