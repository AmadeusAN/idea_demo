package com.anhun.idea_demo.thydataconversion;

import com.anhun.idea_demo.entity.User;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.standard.expression.IStandardConversionService;

@Configuration
public class MyConverService implements IStandardConversionService {
    @Override
    public <T> T convert(IExpressionContext context, Object object, Class<T> targetClass) {
        if (object instanceof User) {
            User u = (User) object;
            return (T) ("name:" + u.getName());
        } else {
            return (T) object.toString();
        }
    }
}
