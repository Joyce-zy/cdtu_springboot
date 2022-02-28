package com.example.demo.controller;

import com.example.demo.pojo.TUser;
import com.example.demo.service.TArticleService;
import com.example.demo.service.TUserService;
import com.example.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Login {
@Autowired
 private TArticleService tArticleService;
@Autowired
private TUserService tUserService;

@RequestMapping("/login")
 public String login(String username, String password, Model model, HttpServletRequest request)
{
 TUser tUser = tUserService.queryByName(username);
// System.out.println(tUser);
 if(tUser==null)
 {
  model.addAttribute("msg","用户名或者密码错误");
  return "index";
 }
//密码解密
 String s = MD5Util.encryption(password, username);
// 判断密码是否正确
 if(tUser.getUserPassword().equals(s))
 {
  model.addAttribute("name",tUser.getUserName());
  request.getSession().setAttribute("tUser",tUser);

 return "forward:/init";
 }else {

  model.addAttribute("msg","用户密码错误");
  return "index";
 }
}


}
