package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.TVisitorMapper;
import com.example.demo.pojo.TVisitor;
import com.example.demo.service.TVisitorService;
import org.springframework.stereotype.Service;

@Service
public class TVisitorServiceImpl extends ServiceImpl<TVisitorMapper, TVisitor> implements TVisitorService {
}
