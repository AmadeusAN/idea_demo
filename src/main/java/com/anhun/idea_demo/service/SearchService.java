package com.anhun.idea_demo.service;

import com.anhun.idea_demo.entity.User;

import java.util.List;
import java.util.Map;

public interface SearchService {
    List<User> searchAllUsers();

    User searchUserById(int id);

    boolean searchIfExist(String account, String password);

    Map<String, Object> readImg(int id);
}
