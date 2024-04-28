package com.example.zhaokl.db;
//用户信息实体类
public class User {

    private String id;
    private String name;
    private String pwd;

    public User() {}
    public User(String _name, String _pwd) {
        this.name = _name;
        this.pwd = _pwd;
    }
    public User(String _id, String _name, String _pwd) {
        this.id = _id;
        this.name = _name;
        this.pwd = _pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
