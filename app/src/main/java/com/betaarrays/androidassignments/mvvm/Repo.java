package com.betaarrays.androidassignments.mvvm;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.betaarrays.androidassignments.service.TestService;
import com.betaarrays.androidassignments.api.RetrofitHelper;
import com.betaarrays.androidassignments.response.ResponseClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repo {
    Context context;

    public Repo(Context context) {
        this.context = context;
    }

    public MutableLiveData<ResponseClass> getHomeData(Context context) {
        TestService service= RetrofitHelper.getRetrofitInstance(context).create(TestService.class);
        Call<ResponseClass> call= service.getPosts();
        final MutableLiveData<ResponseClass> data=new MutableLiveData<>();

        call.enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                if (response.isSuccessful()){
                    Log.d("The api call is successfull","Success");
                    data.setValue(response.body());
                }
                else {
                    Log.d("The failed to caleed an api ","Failed"+response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
             Log.d("Failujr to call an api",t.getLocalizedMessage());
            }
        });
        return data;
    }
}
