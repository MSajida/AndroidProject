package com.example.studibook;

import java.io.Serializable;
import java.util.ArrayList;

public class AddProjectmodel implements Serializable {
    String title;
    String description;
    String batch;
    String year;
    String category;
    ArrayList<AddmemberModel> memberList;

    public AddProjectmodel() {
    }

    public AddProjectmodel(String title, String description, String batch, String year, String category, ArrayList<AddmemberModel> memberList) {
        this.title = title;
        this.description = description;
        this.batch = batch;
        this.year = year;
        this.category = category;
        this.memberList = memberList;
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

    public ArrayList<AddmemberModel> getMemberList() {
        return memberList;
    }

    public void setMemberList(ArrayList<AddmemberModel> memberList) {
        this.memberList = memberList;
    }
}
