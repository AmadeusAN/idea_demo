package com.anhun.idea_demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

//自定义Json 映射器
@Configuration
public class JsonConfig {

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper ob = new ObjectMapper();
        ob.setDateFormat(new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss"));
        return ob;
    }
}
