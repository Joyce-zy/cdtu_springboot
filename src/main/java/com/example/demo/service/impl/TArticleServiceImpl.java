package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.TArticleMapper;
import com.example.demo.pojo.TArticle;
import com.example.demo.service.TArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TArticleServiceImpl extends ServiceImpl<TArticleMapper, TArticle> implements TArticleService {
    @Autowired
    private TArticleMapper tArticleMapper;
//    查询总记录数
    @Override
    public Integer queryTArticleNUm() {
        QueryWrapper<TArticle> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("id","0");
        Integer integer = tArticleMapper.selectCount(queryWrapper);
        return integer;
    }
//查询分类
    @Override
    public Integer queryTArticleSort() {
        QueryWrapper<TArticle> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT sort");
        Integer count = tArticleMapper.selectCount(queryWrapper);
        return count;
    }
//查询分类
    @Override
    public Map<String, Object> queryTarticleMap() {
        QueryWrapper<TArticle> wrapper = new QueryWrapper<TArticle>();
        wrapper.select("sort","count(sort) as num").groupBy("sort");
        List<Map<String, Object>> maps = tArticleMapper.selectMaps(wrapper);
//        System.out.println("maps==>"+maps);
        HashMap<String, Object> resultMap = new HashMap<>();
        for (Map<String, Object> map : maps) {
           resultMap.put((String) map.get("sort"),map.get("num"));
        }
//        System.out.println(resultMap);
       
        return resultMap;
    }
//查询阅读排行榜
    @Override
    public Map<String, Object> queryTarticleTitleMap() {
        QueryWrapper<TArticle> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT title","visit").orderByDesc("visit");
        List<Map<String, Object>> maps = tArticleMapper.selectMaps(wrapper);
//        System.out.println("maps==>"+maps);
//        LinkedHashMap保证了put顺序和遍历顺序一致的问题
        Map<String, Object> resultMap = new LinkedHashMap<>();
        for (Map<String, Object> map : maps) {
//            System.out.println(map);
            resultMap.put((String)map.get("title"),map.get("visit"));
        }
//        System.out.println(resultMap);

        return resultMap;

    }
//查询所有文章
    @Override
    public List<TArticle> getArticleMap() {
        QueryWrapper<TArticle> wrapper = new QueryWrapper<>();
        wrapper.gt("id",0);
        List<TArticle> list = tArticleMapper.selectList(wrapper);
//        System.out.println(list);
        return list;
    }
//根据id查询文章
    @Override
    public TArticle getTarticleById(long id) {
        TArticle tArticle = tArticleMapper.selectById(id);
        return tArticle;
    }
//点赞
    @Override
    public Integer updateStar(long id) {
        TArticle tArticle = tArticleMapper.selectById(id);
        tArticle.setStar(tArticle.getStar()+1);
        int i = tArticleMapper.updateById(tArticle);
        return i;
    }
//查询分类集合
    @Override
    public List<TArticle> queryTArticleSortList() {
        QueryWrapper<TArticle> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT sort");
        List<TArticle> list = tArticleMapper.selectList(wrapper);
//        System.out.println("queryTArticleSortList==>"+list);
        return list;
    }
//添加文章
    @Override
    public long addTArticle(TArticle tArticle) {
        if (tArticle != null) {
            int insert = tArticleMapper.insert(tArticle);
            long id = tArticle.getId();
            return id;
        } else {
            return -1;
        }
    }

    @Override
    public List<TArticle> getArticlePage(int i, int i1) {
        Page<TArticle> page = new Page<>(i, i1);
        QueryWrapper<TArticle> wrapper = new QueryWrapper<>();
        wrapper.gt("id",0);
        Page<TArticle> tArticlePage = tArticleMapper.selectPage(page, wrapper);
        List<TArticle> records = tArticlePage.getRecords();
        return records;
    }
//查询sort
    @Override
    public Map<String, List<TArticle>> getSortMap() {
        List<TArticle> sortList = this.queryTArticleSortList();
        HashMap<String, List<TArticle>> result = new HashMap<>();
        for (TArticle article : sortList) {
            List<TArticle> tArticleList = this.getTarticleListBySort(article.getSort());
            result.put(article.getSort(),tArticleList);
        }

        return result;
    }
//根据sort查询文章列表
    @Override
    public List<TArticle> getTarticleListBySort(String sort) {
        QueryWrapper<TArticle> wrapper = new QueryWrapper<>();
        wrapper.eq("sort",sort);
        List<TArticle> articleList = tArticleMapper.selectList(wrapper);
        return articleList;
    }

}
