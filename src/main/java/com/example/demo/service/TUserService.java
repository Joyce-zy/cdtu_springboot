package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.TUser;
import org.springframework.stereotype.Service;


public interface TUserService extends IService<TUser> {
//    根据姓名查询数据
    public TUser queryByName(String name);
}
