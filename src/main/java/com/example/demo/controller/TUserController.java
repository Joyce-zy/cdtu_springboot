package com.example.demo.controller;

import com.example.demo.pojo.TArticle;
import com.example.demo.pojo.TTag;
import com.example.demo.pojo.TUser;
import com.example.demo.service.TArticleService;
import com.example.demo.service.TTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class TUserController {
    @Autowired
    private TArticleService tArticleService;
    @Autowired
    private TTagService tTagService;
//    渲染数据并跳转到admin.html页面
    @RequestMapping("/admin")
    public String intoAdmin(Model model, HttpServletRequest request)
    {
        TUser tUser = (TUser) request.getSession().getAttribute("tUser");
        List<TArticle> articleList = tArticleService.getArticleMap();
        List<TTag> tagList = tTagService.getTagList();
        List<TArticle> sortList = tArticleService.queryTArticleSortList();
        model.addAttribute("tUser",tUser);
        model.addAttribute("articleList",articleList);
        model.addAttribute("tagList",tagList);
        model.addAttribute("sortList",sortList);
        return  "admin";
    }
//    修改文章
    @RequestMapping("/edit/{id}")
    public String editArticleById(@PathVariable long id,Model model)
    {
        TArticle tArticle = tArticleService.getTarticleById(id);
        model.addAttribute("tArticle",tArticle);
        List<TTag> tTagList = tTagService.queryTageList(id);
        model.addAttribute("tTagList",tTagList);
        return  "edit";
    }
}
