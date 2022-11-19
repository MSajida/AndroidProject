package com.example.studibook;

import java.io.Serializable;
import java.util.ArrayList;

public class AddProjectmodel implements Serializable {
    String title;
    String description;
    String batch;
    String year;
    String category;
    String githubLink;
    String key;
    ArrayList<AddmemberModel> memberList;

    public AddProjectmodel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<AddmemberModel> getMemberList() {
        return memberList;
    }

    public void setMemberList(ArrayList<AddmemberModel> memberList) {
        this.memberList = memberList;
    }

    public AddProjectmodel(String title, String description, String batch, String year, String category, String githubLink, String key, ArrayList<AddmemberModel> memberList) {
        this.title = title;
        this.description = description;
        this.batch = batch;
        this.year = year;
        this.category = category;
        this.githubLink = githubLink;
        this.key = key;
        this.memberList = memberList;
    }
}
