package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.TUserMapper;
import com.example.demo.pojo.TUser;
import com.example.demo.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {
    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public TUser queryByName(String name) {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",name);
        TUser tUser = tUserMapper.selectOne(wrapper);
        return tUser;
    }
}
