package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.TComment;

import java.util.List;

public interface TCommentService extends IService<TComment> {
    List<TComment> getCommentByArticleId(long id);
//添加评论
    Integer insertComment(long articleId, String nickname, String content);
}
