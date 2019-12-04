package com.sedef.yelken.github.mvvm.model.remote;

import com.google.gson.annotations.SerializedName;

public class GitHubRepoResponse {

    private String name;
    @SerializedName("open_issues_count")
    private String openIssuesCount;
    private GitHubOwnerResponse owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenIssuesCount() {
        return openIssuesCount;
    }

    public void setOpenIssuesCount(String openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    public GitHubOwnerResponse getOwner() {
        return owner;
    }

    public void setOwner(GitHubOwnerResponse owner) {
        this.owner = owner;
    }
}
