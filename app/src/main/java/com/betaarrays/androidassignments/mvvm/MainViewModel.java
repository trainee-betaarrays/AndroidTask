package com.betaarrays.androidassignments.mvvm;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.betaarrays.androidassignments.response.ResponseClass;

import java.util.List;

public class MainViewModel extends ViewModel {

    private Repo repo;
    MutableLiveData<ResponseClass> data;

    public MainViewModel(Context context) {
        this.repo = new Repo(context);
    }

    public MutableLiveData<ResponseClass> getData(Context context){
        if (data==null){
            data=repo.getHomeData(context);
        }
        return data;
    }
}
