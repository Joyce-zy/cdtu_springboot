package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.TArticleMapper;
import com.example.demo.mapper.TTagMapper;
import com.example.demo.pojo.TArticle;
import com.example.demo.pojo.TTag;
import com.example.demo.service.TTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TTagServiceImpl extends ServiceImpl<TTagMapper, TTag> implements TTagService {
    @Autowired
    private TTagMapper tTagMapper;
    @Autowired
    private TArticleMapper tArticleMapper;
    @Override
    public Integer getTagNum() {
        QueryWrapper<TTag> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT tag");
        Integer integer = tTagMapper.selectCount(wrapper);
        return integer;
    }
//根据id查询标签
    @Override
    public List<TTag> queryTageList(long id) {
        QueryWrapper<TTag> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        List<TTag> tTagList = tTagMapper.selectList(wrapper);
        return tTagList;
    }
//查询所有的标签
    @Override
    public List<TTag> getTagList() {
        List<TTag> tTagList = tTagMapper.selectList(null);
        return tTagList;
    }

    @Override
    public Map<String, List<TArticle>> getTagMap() {
        List<TTag> tTagList = this.getOnlyTagList();
        HashMap<String,List<TArticle>> result = new HashMap<>();

        for (TTag tag : tTagList) {
            String tag1 = tag.getTag();
            QueryWrapper<TTag> wrapper = new QueryWrapper<>();
            wrapper.eq("tag",tag1);
            List<TTag> tagList = tTagMapper.selectList(wrapper);
            List<TArticle> articleList = new ArrayList<>();
            for (TTag tTag : tagList) {
                TArticle tArticle = tArticleMapper.selectById(tTag.getId());
                articleList.add(tArticle);
            }
            result.put(tag1,articleList);
        }
        System.out.println("getTagMap==>"+result);
        return result;
    }
//获取去重后的标签
    @Override
    public List<TTag> getOnlyTagList() {
        QueryWrapper<TTag> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT tag");
        List<TTag> tTagList = tTagMapper.selectList(wrapper);
        return tTagList;
    }
}
