package com.anhun.idea_demo.entity.controller;

import com.anhun.idea_demo.entity.User;
import com.anhun.idea_demo.service.IInsertService;
import com.anhun.idea_demo.service.SearchService;
import com.anhun.idea_demo.service.UpdateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {

    Log log = LogFactory.getLog(MainController.class);

    @Autowired
    private IInsertService iInsertService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private UpdateService updateService;


    @RequestMapping("/insert")
    @ResponseBody
    public String insertUser(User user) {
        iInsertService.insertUser(user.getId(), user.getName(), user.getSex(), user.getAccount(), user.getPassword());
        return user.toString();
    }

    @RequestMapping("/search")
    @ResponseBody
    public String searchUser(int id) {
        User user = searchService.searchUserById(id);
        return user == null ? "查无此人" : user.toString();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(User user) {
        int i = updateService.updateUser(user);
        if (i > 0) {
            return "更新成功：" + user.toString();
        }
        return "更新失败";
    }

    @RequestMapping("/imginsert")
    @ResponseBody
    public String insetimg(String filepath) {
        iInsertService.insertImg(filepath);
        return "操作成功";
    }

    @RequestMapping("/searchimg")
    public String showimg(int id, HttpServletRequest request, Model model) {
        Map<String, Object> result = searchService.readImg(id);
        byte[] img = (byte[]) result.get("img");
        String filename = (String) result.get("text");
        String tempimgfile = request.getServletContext().getRealPath(filename);

        log.info("目标图片存放路径为" + tempimgfile);

        int len = -1;

        File file = new File(tempimgfile);
        try {
            FileOutputStream fout = new FileOutputStream(file);
            fout.write(img);
            model.addAttribute("imgname", filename);
            model.addAttribute("tempfile", tempimgfile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "display";
    }

}



