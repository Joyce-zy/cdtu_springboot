package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.TArticle;
import com.example.demo.pojo.TTag;

import java.util.List;
import java.util.Map;

public interface TTagService extends IService<TTag>{
    Integer getTagNum();

    List<TTag> queryTageList(long id);
//查询所有标签
    List<TTag> getTagList();

    Map<String, List<TArticle>> getTagMap();

    List<TTag> getOnlyTagList();
}
