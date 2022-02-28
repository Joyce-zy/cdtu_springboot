package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.TArticle;
import com.example.demo.pojo.TComment;
import com.example.demo.pojo.TTag;
import com.example.demo.pojo.TUser;
import com.example.demo.service.TArticleService;
import com.example.demo.service.TCommentService;
import com.example.demo.service.TTagService;
import com.example.demo.vo.TArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TArticleController {
    @Autowired
    private TArticleService tArticleService;
    @Autowired
    private TTagService tTagService;
    @Autowired
    private TCommentService tCommentService;

    /**
     * 第一次进入主页面进行数据的渲染
     * @param model
     * @param request
     * @return
     */

    @RequestMapping("/init")
 public String init(Model model, HttpServletRequest request)
 {
     TUser tUser = (TUser) request.getSession().getAttribute("tUser");

     model.addAttribute("tUser",tUser);
//     查询文章总数
     Integer num=tArticleService.queryTArticleNUm();
//     查询分类总数
     Integer sortNum= tArticleService.queryTArticleSort();
//     查询标签数量
     Integer tagNm=tTagService.getTagNum();
     List<TTag>  tTagList=tTagService.getTagList();
     model.addAttribute("articleNum",num);
     model.addAttribute("sortNum",sortNum);
     model.addAttribute("tagNum",tagNm);
     model.addAttribute("tTagList",tTagList);
//初始化分类侧边栏
    Map<String,Object> tarticleMap=tArticleService.queryTarticleMap();
//初始化阅读侧边栏
     Map<String,Object>titleMap=tArticleService.queryTarticleTitleMap();
//     初始化文章并进行分页
     Page<TArticle> tArticlePage = new Page<>(1,6);
     Page<TArticle> page1 = tArticleService.page(tArticlePage);
     List<TArticle> records = page1.getRecords();
     model.addAttribute("tarticlesList",records);
     model.addAttribute("totalPages",page1.getPages());
     model.addAttribute("currentPage",1);
     model.addAttribute("previousPage",0);
     model.addAttribute("NextPage",2);
//     初始化标签和阅读排行榜
    model.addAttribute("tarticleMap",tarticleMap);
    model.addAttribute("titelMap",titleMap);
//    model.addAttribute("tarticlesList",tarticlesList);

//     System.out.println("controller:trticleMap==>"+tarticleMap);
     return "main";
 }

    /**
     * 查询带分页并初始化数据
     * @param page
     * @param limit
     * @param model
     * @param request
     * @return
     */
 @RequestMapping("/pageArticle/{page}/{limit}")
 public String pageArticle(@PathVariable long page,
                                   @PathVariable long limit,Model model,HttpServletRequest request)
 {
     Page<TArticle> tArticlePage = new Page<>(page,limit);
     Page<TArticle> page1 = tArticleService.page(tArticlePage);
     List<TArticle> records = page1.getRecords();
     model.addAttribute("tarticlesList",records);
     model.addAttribute("totalPages",page1.getPages());
     model.addAttribute("currentPage",page);
     model.addAttribute("previousPage",page-1);
     model.addAttribute("NextPage",page+1);
//     初始化数据
     TUser tUser = (TUser) request.getSession().getAttribute("tUser");
     model.addAttribute("tUser",tUser);
     Integer num=tArticleService.queryTArticleNUm();
     Integer sortNum= tArticleService.queryTArticleSort();
     Integer tagNm=tTagService.getTagNum();
     List<TTag>  tTagList=tTagService.getTagList();
     Map<String,Object>titleMap=tArticleService.queryTarticleTitleMap();
     model.addAttribute("articleNum",num);
     model.addAttribute("sortNum",sortNum);
     model.addAttribute("tagNum",tagNm);
     model.addAttribute("tTagList",tTagList);//初始化分类侧边栏
     Map<String,Object> tarticleMap=tArticleService.queryTarticleMap();
     model.addAttribute("tarticleMap",tarticleMap);
     model.addAttribute("titelMap",titleMap);
     return "main";
 }

    /**
     * 根据id返回文章详情
     * @param id
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/getTarticle/{id}")
    public String getTarticleById(@PathVariable long id,Model model,HttpServletRequest request)
    {
        TUser user = (TUser)request.getSession().getAttribute("tUser");
//        System.out.println("tUser==>"+user);
        model.addAttribute("tUser",user);
        TArticle tArticle=tArticleService.getTarticleById(id);
        List<TTag> tTagList=tTagService.queryTageList(id);
         List<TComment> tCommentList=tCommentService.getCommentByArticleId(id);
        model.addAttribute("tArticle",tArticle);
//        返回文章所属标签
        model.addAttribute("tag_List",tTagList);
//        添加文章评论
        model.addAttribute("tCommentList",tCommentList);
        TUser tUser = (TUser) model.getAttribute("tUser");
        System.out.println("tUser==>"+tUser);
        return "article";
    }

    /**
     * 更新文章的点赞操作
     * @param id
     * @return
     */

    @PostMapping("/tArticle/updateArticleStar")
    @ResponseBody
    public Map<String,Object> updateStar(long id){
        Integer integer=tArticleService.updateStar(id);
        HashMap<String, Object> map = new HashMap<>();
        if(integer>0)
        {
            map.put("code","40000");
            map.put("msg","点赞成功");
        }
        else {
            map.put("code","40001");
            map.put("msg","点赞失败");
        }
        return  map;
    }
//    跳转到add页面
    @RequestMapping("/addArticle/{userName}")
    public String addArticle(@PathVariable String userName,Model model){
        Date date = new Date();
//        查询分类集合
         List<TArticle> SortList=tArticleService.queryTArticleSortList();
//         查询标签集合
        List<TTag> tagList = tTagService.getTagList();
        model.addAttribute("nowTime",date);
        model.addAttribute("autorName",userName);
        model.addAttribute("sortList",SortList);
        model.addAttribute("tagList",tagList);
        return "add";
    }
//    添加文章到数据库
    @PostMapping("/tArticle/insert")
    @ResponseBody
    public Map<String,Object> insertArticle(TArticleVo tArticleVo){
//        System.out.println("/tArticle/insert==>"+tArticleVo);
        TArticle tArticle = tArticleVo.buildTArticle();
        long i=tArticleService.addTArticle(tArticle);
//        System.out.println(i);
        Map<String, Object> result = new HashMap<>();
        if(i!=-1)
        {
            result.put("code","20000");
            result.put("msg","添加成功");
            result.put("articleId",String.valueOf(i));
        }
        else {
            result.put("code","20001");
            result.put("msg","添加失败");
        }
        return result;
    }
//    显示添加文章结果
    @RequestMapping("/tArticle/result/{id}")
    public String AddResult(@PathVariable long id,Model model)
    {
        TArticle tarticle = tArticleService.getTarticleById(id);
        model.addAttribute("newArticleTitle",tarticle.getTitle());
        model.addAttribute("articleId",id);
        return "result";
    }
//    渲染页面数据并且跳转到sort.html
    @RequestMapping("/sortArticle")
    public String SortArticle(Model model,HttpServletRequest request){
        TUser tUser = (TUser) request.getSession().getAttribute("tUser");
        Map<String,List<TArticle>> listMap=tArticleService.getSortMap();
        model.addAttribute("tUser",tUser);
        model.addAttribute("sortMap",listMap);

        return "sort";
    }

//    游客登录
    @RequestMapping("/main")
    public String Visitor(HttpServletRequest request)
    {
        request.getSession().removeAttribute("tUser");
        return "forward:/init";

    }
//    跳转关于页面
    @RequestMapping("/about")
    public String About(Model model)
    {
        return "about";

    }
    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("tUser");
        return "index";
    }
}
