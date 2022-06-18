package com.anhun.idea_demo.entity.controller;

import com.anhun.idea_demo.entity.User;
import com.anhun.idea_demo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/login")
    public String toLogin(Model model) {
        User user = new User();
        user.setAccount("anhun");
        user.setPassword("1234567");
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping("/check")
    public String checkUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("msg", "输入信息有误");
            return "login";
        } else {
            if (searchService.searchIfExist(user.getAccount(), user.getPassword())) {
                return "main";
            } else {
                model.addAttribute("msg", "账号或密码错误");
                return "login";
            }
        }
    }
}
