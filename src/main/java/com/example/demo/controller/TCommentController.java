package com.example.demo.controller;

import com.example.demo.service.TCommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller

public class TCommentController {
    @Autowired
    private TCommentService tCommentService;
    @PostMapping("/tComment/insert")
    @ResponseBody
    public HashMap<String, Object> insertComment(long articleId,String nickname,String content){
        Integer num=tCommentService.insertComment(articleId,nickname,content);
        HashMap<String, Object> result = new HashMap<>();
        if(num>0){
            result.put("code","200");
            result.put("msg","评论成功");
        }
        else {
            result.put("code","201");
            result.put("msg","评论失败");
        }
        return result;
    }
}
