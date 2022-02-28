package com.example.demo.controller;

import com.example.demo.pojo.TArticle;
import com.example.demo.pojo.TUser;
import com.example.demo.service.TTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class TagController {
    @Autowired
    private TTagService tTagService;
    //渲染页面数据并且跳转到tag.html页面
    @RequestMapping("/getTagInfo")
    public String TagInfo(Model model, HttpServletRequest request)
    {
        TUser tUser = (TUser) request.getSession().getAttribute("tUser");
        Map<String, List<TArticle>> listMap=tTagService.getTagMap();
        model.addAttribute("TTagmap",listMap);
        model.addAttribute("tUser",tUser);
        return "tags";
    }
}
