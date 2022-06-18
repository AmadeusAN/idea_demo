package com.anhun.thymeleaftest;

import com.anhun.idea_demo.entity.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.*;

/**
 * 使用Thymeleaf 进行单独测试时，不通过web容器需要单独传入整个完整依赖,
 * 并更换运行器
 */
//@RunWith(JUnit4.class)
public class ThymeleafTest {

    //    @Test
    public void test() {

        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        templateEngine.setTemplateResolver(resolver);
        Context context = new Context();

        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");

        Double[] datas = new Double[]{2.5, 3.5};
        User user = new User();
        user.setId(1);
        user.setName("陈佳豪");


        System.out.println(datas[1]);
        System.out.println(datas);
        System.out.println(datas[0]);

        List list = Arrays.asList(datas);

        Set set = new HashSet(list);

        context.setVariable("arr", datas);
        context.setVariable("list", list);
        context.setVariable("set", set);
        context.setVariable("user", user);
        context.setVariable("date", new Date());

        String result = templateEngine.process("hello", context);
        System.out.println(result);

    }
}
