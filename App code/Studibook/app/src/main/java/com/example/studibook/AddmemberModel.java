package com.example.studibook;

import java.io.Serializable;

public class AddmemberModel implements Serializable {
    String name;
    String sid;
    String mail;

    public AddmemberModel() {
    }

    public AddmemberModel(String name, String sid, String mail) {
        this.name = name;
        this.sid = sid;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
