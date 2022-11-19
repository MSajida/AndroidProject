package com.example.studibook.ui;

public class AddMemberModel {
    String name;
    String email;
    String sid;


    public AddMemberModel() {
    }

    public AddMemberModel(String name, String email, String sid) {
        this.name = name;
        this.email = email;
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
