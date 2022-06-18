package com.anhun.idea_demo.formatter;

import com.anhun.idea_demo.entity.User;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class UserFormatter implements Formatter<User> {
    @Override
    public String print(User user, Locale locale) {
        return "name:" + user.getName();
    }

    @Override
    public User parse(String text, Locale locale) throws ParseException {
        return null;
    }


}
