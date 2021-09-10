package com.cadle.passwordmanager.database;

import com.orm.SugarRecord;


public class Service extends SugarRecord {

    private String username;
    private String password;
    private String name;


    public Service() {
    }
    public Service (String username, String password, String name) {
        this.password = password;
        this.username = username;
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
