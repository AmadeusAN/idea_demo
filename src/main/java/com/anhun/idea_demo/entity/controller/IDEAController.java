package com.anhun.idea_demo.entity.controller;

import com.anhun.idea_demo.entity.FormTestUser;
import com.anhun.idea_demo.entity.JSR303User;
import com.anhun.idea_demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
public class IDEAController {

    //    自定义的模板引擎对象
    @Autowired
    TemplateEngine templateEngine;

    @RequestMapping("/idea")
    @ResponseBody
    public Date test() {
        return new Date();
    }

    @RequestMapping("/str")
    @ResponseBody
    public String getString() {
        return new String("AN_HUN_");
    }

    @RequestMapping("/date")
    @ResponseBody
    public Date getDate(@RequestParam("d") Date d) {
        System.out.println("看样子不行");
        System.out.println("进行日期绑定测试");
        return d;
    }

    @RequestMapping("/exp2")
    public String getView(Model model, HttpServletRequest request, @ModelAttribute User user) {

        String[] sexs = {"man", "lady"};
        Map<Integer, User> userMap = new HashMap<>();
        List<User> users = new ArrayList<>();
        users.add(User.quickSetting(4, "anhun"));
        users.add(User.quickSetting(5, "quitesoul"));
        userMap.put(1, User.quickSetting(2, "alian"));
        userMap.put(2, User.quickSetting(3, "buck"));
        FormTestUser testUser = new FormTestUser();
        testUser.setSingle("single number");
        testUser.setPlural(new String[]{"one", "two", "three"});
//        单选的可选范围
        String[] singles = {"single number", "plural numbers"};
//        多选的可选范围
        String[] plural = {"one", "two", "three", "four"};
        JSR303User jsruser = new JSR303User();
        jsruser.setName("非空");
        jsruser.setEmail("25146426260@qq.com");
        jsruser.setPassword("123456879");


        model.addAttribute("users", users);
        model.addAttribute("userMap", userMap);
        model.addAttribute("user", User.CreateUser(1, "AN_HUN_", "lady"));
        model.addAttribute("sexs", sexs);
        model.addAttribute("testUser", testUser);
        model.addAttribute("plurals", plural);
        model.addAttribute("singles", singles);
        model.addAttribute("jsruser", jsruser);

//        获取当前页面位置
        System.out.println(request.getContextPath());


        return "expression";
    }

    @RequestMapping("/tobaidu")
    public void toanotherWebsite(HttpServletResponse response, String question) throws IOException {
        System.out.println(question);

        class toUTF8 {
            String toUtf8String(String string) {
                StringBuilder stringBuffer = new StringBuilder();
                for (int i = 0; i < string.length(); i++) {
                    char c = string.charAt(i);
                    if (c <= 255) {
                        stringBuffer.append(c);
                    } else {
                        byte[] b;
                        try {
                            b = Character.toString(c).getBytes(StandardCharsets.UTF_8);
                        } catch (Exception ex) {
                            b = new byte[0];
                        }
                        for (int value : b) {
                            int k = value;
                            if (k < 0) k += 256;
                            stringBuffer.append("%").append(Integer.toHexString(k).toUpperCase());
                        }
                    }
                }
                return stringBuffer.toString();
            }
        }
        toUTF8 change = new toUTF8();

        System.out.println(change.toUtf8String(question));
        response.sendRedirect("https://www.baidu.com/s?ie=utf-8&wd=" + change.toUtf8String(question));
    }

    @RequestMapping("/test")
    @ResponseBody
    public String simpletest() {

//        配置属性
        Map<Integer, User> userMap = new HashMap<>();
        List<User> users = new ArrayList<>();
        users.add(User.quickSetting(4, "anhun"));
        users.add(User.quickSetting(5, "quitesoul"));

        userMap.put(1, User.quickSetting(2, "alian"));
        userMap.put(2, User.quickSetting(3, "buck"));

//        注入属性
        Context ctx = new Context();

        ctx.setVariable("users", users);
        ctx.setVariable("userMap", userMap);
        ctx.setVariable("user", User.quickSetting(1, "chen"));

        String result = templateEngine.process("expression", ctx);
        return result;

    }

    @RequestMapping("/form")
    @ResponseBody
    public String testform(@ModelAttribute("user") User user) {
        System.out.println(user);
        return user.toString();
    }

    @RequestMapping("/radioandcheckbox")
    @ResponseBody
    public String radioandcheckbox(@ModelAttribute FormTestUser testUser) {
        return testUser.toString();
    }

    @RequestMapping("/valid")
    public String abc(Model model) {
        JSR303User jsr303User = new JSR303User();
        jsr303User.setName("非空");
        jsr303User.setEmail("25146426260@qq.com");
        jsr303User.setPassword("123456879");
        model.addAttribute("jsr303User", jsr303User);
        return "validresult";
    }

    @RequestMapping("/validuser")
    public String valid(@Valid @ModelAttribute("jsr303User") JSR303User jsr303User, BindingResult result, Model model) {
        System.out.println(jsr303User.toString());
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error.getCode() + "_____" + error.getDefaultMessage());
            }
            model.addAttribute("validmsg", "验证错误");
        } else {
            model.addAttribute("validmsg", "验证正确");
        }
//        model.addAttribute("jsr303User",jsr303User);
        return "validresult";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }
}
