package com.anhun.idea_demo.service.impl;

import com.anhun.idea_demo.entity.User;
import com.anhun.idea_demo.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UpdateServiceImpl implements UpdateService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int updateUser(User user) {
        String sql = "UPDATE user SET name=?,sex=?,account=?,password=? WHERE id=?";
        Object[] params = new Object[]{user.getName(), user.getSex(), user.getAccount(), user.getPassword(), user.getId()};

        int result = jdbcTemplate.update(sql, params);
        return result;
    }
}
