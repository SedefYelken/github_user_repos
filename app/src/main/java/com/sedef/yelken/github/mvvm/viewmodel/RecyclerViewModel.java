package com.sedef.yelken.github.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.sedef.yelken.github.mvvm.model.remote.GitHubApiServiceClient;
import com.sedef.yelken.github.mvvm.model.remote.GitHubRepoResponse;
import com.sedef.yelken.github.mvvm.viewmodel.Adapter.RecyclerViewAdapter;

import java.util.List;

public class RecyclerViewModel extends AndroidViewModel {


    private List<GitHubRepoResponse> repositories;
    public RecyclerViewAdapter adapter;
    MutableLiveData<GitHubRepoResponse> selected;

    public RecyclerViewModel(@NonNull Application application) {
        super(application);
        adapter = new RecyclerViewAdapter(repositories , this);
        selected = new MutableLiveData<>();

    }

    public MutableLiveData<List<GitHubRepoResponse>> fethData(String userName) {
        if (userName != null)
            return GitHubApiServiceClient.getInstance().getRepositois(userName);
        return GitHubApiServiceClient.getInstance().getRepositois("");
    }

    public MutableLiveData<GitHubRepoResponse> getSelected() {
        return selected;
    }

    public void onItemClick(Integer index) {
        if (repositories != null && !repositories.isEmpty()
                && index != null) {
            selected.setValue(repositories.get(index));
        }
        selected.setValue(null);
    }

    public List<GitHubRepoResponse> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<GitHubRepoResponse> repositories) {
        this.repositories = repositories;
        this.adapter.lists = repositories;
        this.adapter.notifyDataSetChanged();
    }
}