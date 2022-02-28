package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.TArticle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface TArticleService extends IService<TArticle> {
//查询文章的数量
    Integer queryTArticleNUm();
//查询分类的数量（去重）
    Integer queryTArticleSort();
//查询标签
    Map<String, Object> queryTarticleMap();
//查询文章标题
    Map<String, Object> queryTarticleTitleMap();
//查询所有文章
    List<TArticle> getArticleMap();
//    根据id查询单个文章
    public TArticle getTarticleById(long id);

    Integer updateStar(long id);

    List<TArticle> queryTArticleSortList();

    long addTArticle(TArticle tArticle);

    List<TArticle> getArticlePage(int i, int i1);

    Map<String, List<TArticle>> getSortMap();
    List<TArticle> getTarticleListBySort(String sort);


}
