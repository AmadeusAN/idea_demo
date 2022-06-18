package com.anhun.idea_demo.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {

    private int id;
    private String name;
    private String sex;

    @NotBlank(message = "{user.account}")
    private String account;

    @Size(min = 6, max = 20)
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static final User quickSetting(int id, String name) {
        User user = new User();
        user.setName(name);
        user.setId(id);
        return user;
    }

    public static final User CreateUser(int id, String name, String sex) {
        User user = new User();
        user.setSex(sex);
        user.setId(id);
        user.setName(name);
        return user;
    }
}
