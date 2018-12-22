package com.example.dell1.github_okhttp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {
    @SerializedName("total_count")
    private Integer totalCount;
    @SerializedName("incomplete_results")
    private Boolean incompleteResults;
    @SerializedName("items")
    private ArrayList<User> users;

    public Result(Integer totalCount, Boolean incompleteResults, ArrayList<User> users) {
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.users = users;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
