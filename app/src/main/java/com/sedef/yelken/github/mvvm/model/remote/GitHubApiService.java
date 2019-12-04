package com.sedef.yelken.github.mvvm.model.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApiService {

    @GET("users/{user}/repos")
    Call<List<GitHubRepoResponse>> getRepositories(@Path("user") String user);
}
