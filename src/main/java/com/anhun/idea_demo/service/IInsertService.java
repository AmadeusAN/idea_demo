package com.anhun.idea_demo.service;

public interface IInsertService {
    void insertUser(int id, String name, String sex, String account, String password);

    void insertImgandText(int id, byte[] img, String text);

    void insertImg(String filename);
}

