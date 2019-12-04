package com.sedef.yelken.github.mvvm.model.remote;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubApiServiceClient {

    private static GitHubApiServiceClient instance;
    private static final String BASE_URL = "https://api.github.com/";

    private GitHubApiService gitHubApiService;


    private GitHubApiServiceClient() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubApiService = retrofit.create(GitHubApiService.class);
    }

    public static synchronized GitHubApiServiceClient getInstance() {
        if (instance == null) {
            instance = new GitHubApiServiceClient();
        }
        return instance;
    }

    public MutableLiveData<List<GitHubRepoResponse>> getRepositois(String userName) {

        final MutableLiveData<List<GitHubRepoResponse>> data = new MutableLiveData<>();
        gitHubApiService.getRepositories(userName).enqueue(new Callback<List<GitHubRepoResponse>>() {
            @Override
            public void onResponse(Call<List<GitHubRepoResponse>> call, Response<List<GitHubRepoResponse>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<GitHubRepoResponse>> call, Throwable t) {
                data.setValue(new ArrayList<>());
                t.printStackTrace();
            }
        });
        return data;
    }
}
