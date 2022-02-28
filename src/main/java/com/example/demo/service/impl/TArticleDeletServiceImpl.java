package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.TArticleDeletMapper;
import com.example.demo.pojo.TArticleDelet;
import com.example.demo.service.TArticleDeletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TArticleDeletServiceImpl extends ServiceImpl<TArticleDeletMapper, TArticleDelet> implements TArticleDeletService {
    @Autowired
    private TArticleDeletMapper tArticleDeletMapper;
}
