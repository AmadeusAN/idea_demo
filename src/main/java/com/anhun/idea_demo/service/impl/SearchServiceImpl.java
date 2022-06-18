package com.anhun.idea_demo.service.impl;

import com.anhun.idea_demo.entity.User;
import com.anhun.idea_demo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SearchServiceImpl implements SearchService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> searchAllUsers() {
        String sql = "SELECT * FROM user";

        List<User> users = jdbcTemplate.queryForList(sql, User.class);
        return users;
    }

    @Override
    public boolean searchIfExist(String account, String password) {
        String sql = "SELECT password FROM user WHERE account = '" + account + "'";
        String result = jdbcTemplate.queryForObject(sql, String.class);
        if (result.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User searchUserById(int id) {
        String sql = "SELECT * FROM user WHERE id=" + id;

//        List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {
//
//            @Override
//            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//                User user = new User();
//                user.setId(Integer.parseInt(rs.getString("id")));
//                user.setName(rs.getString("name"));
//                user.setSex(rs.getString("sex"));
//                user.setAccount(rs.getString("account"));
//                user.setPassword(rs.getString("password"));
//                return user;
//            }
//        });

        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));

        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public Map<String, Object> readImg(int id) {

        String sql = "SELECT img,text FROM imgandtext WHERE id = " + id;
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);

        return result.get(0);
    }
}
