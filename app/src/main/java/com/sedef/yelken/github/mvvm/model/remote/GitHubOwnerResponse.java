package com.sedef.yelken.github.mvvm.model.remote;

import com.google.gson.annotations.SerializedName;

public class GitHubOwnerResponse {
    @SerializedName("login")
    private String userName;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
