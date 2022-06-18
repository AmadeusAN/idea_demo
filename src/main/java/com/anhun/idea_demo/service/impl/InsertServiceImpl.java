package com.anhun.idea_demo.service.impl;

import com.anhun.idea_demo.service.IInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Component
public class InsertServiceImpl implements IInsertService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertUser(int id, String name, String sex, String account, String password) {
        String sql = "INSERT INTO `user` VALUES (?,?,?,?,?)";
        Object[] params = new Object[]{id, name, sex, account, password};
        int reslut = jdbcTemplate.update(sql, params);
        System.out.println("成功运行sql 语句，受影响" + reslut + "行");
    }

    @Override
    public void insertImg(String filepath) {
        File file = new File(filepath);
        String filename = file.getName();
        try {
            InputStream in = new FileInputStream(file);

//            使用默认提供的 LOBHandler 处理器
            LobHandler lobHandler = new DefaultLobHandler();
            jdbcTemplate.execute("insert into imgandtext(id,img,text) values (?,?,?)", new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
                @Override
                protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
                    ps.setInt(1, 2);
                    lobCreator.setBlobAsBinaryStream(ps, 2, in, (int) file.length());
                    ps.setString(3, filename);
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertImgandText(int id, byte[] img, String text) {
        String sql = "INSERT INTO `imgandtext` VALUES (?,?,?)";
        Object[] params = new Object[]{id, img, text};
        int result = jdbcTemplate.update(sql, params);
        System.out.println(result > 0 ? "语句执行成功" : "语句执行失败");
    }
}
