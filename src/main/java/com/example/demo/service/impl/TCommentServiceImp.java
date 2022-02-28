package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.TCommentMapper;
import com.example.demo.pojo.TComment;
import com.example.demo.service.TCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TCommentServiceImp extends ServiceImpl<TCommentMapper, TComment> implements TCommentService {
    @Autowired
    private TCommentMapper tCommentMapper;
    //    查询文章的评论
    @Override
    public List<TComment> getCommentByArticleId(long id) {
        QueryWrapper<TComment> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id",id);
        List<TComment> tCommentList = tCommentMapper.selectList(wrapper);
        return tCommentList;
    }
//添加评论
    @Override
    public Integer insertComment(long articleId, String nickname, String content) {
        TComment tComment = new TComment();
        tComment.setContent(content);
        tComment.setNickname(nickname);
        tComment.setArticleId(articleId);
        tComment.setStar(0);
        tComment.setDiss(0);
        tComment.setTime(new Timestamp(new Date().getTime()));
        int insert = tCommentMapper.insert(tComment);
        return insert;
    }
}
