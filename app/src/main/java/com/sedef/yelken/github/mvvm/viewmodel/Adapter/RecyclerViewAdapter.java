package com.sedef.yelken.github.mvvm.viewmodel.Adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sedef.yelken.github.mvvm.R;
import com.sedef.yelken.github.mvvm.databinding.RecyclerviewLayoutBinding;
import com.sedef.yelken.github.mvvm.model.remote.GitHubRepoResponse;
import com.sedef.yelken.github.mvvm.viewmodel.RecyclerViewModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public List<GitHubRepoResponse> lists;
    public RecyclerViewModel recyclerViewModel;

    public RecyclerViewAdapter(List<GitHubRepoResponse> lists, RecyclerViewModel recyclerViewModel) {
        this.lists = lists;
        this.recyclerViewModel=recyclerViewModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        RecyclerviewLayoutBinding recyclerviewLayoutBinding
                = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.recyclerview_layout
                , viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(recyclerviewLayoutBinding);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        GitHubRepoResponse githubRepo = lists.get(i);
        myViewHolder.recyclerviewLayoutBinding.setViewModel(recyclerViewModel);
        myViewHolder.recyclerviewLayoutBinding.setPosition(i);
        myViewHolder.recyclerviewLayoutBinding.setReposity(githubRepo);
    }

    @Override
    public int getItemCount() {
        return lists != null ? lists.size() : 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder  {
        @NonNull
        RecyclerviewLayoutBinding recyclerviewLayoutBinding;

        public MyViewHolder(@NonNull RecyclerviewLayoutBinding viewBinding) {
            super(viewBinding.getRoot());
            this.recyclerviewLayoutBinding = viewBinding;
        }
    }
}
