package com.example.demo.vo;

import com.example.demo.pojo.TArticle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TArticleVo {
    private String title;
    private String author;
    private String sort;
    private Date time;
    private String content;
//    产生TArticle类
    public TArticle buildTArticle(){
        TArticle tArticle = new TArticle();
        tArticle.setStar(0);
        tArticle.setAuthor(this.author);
        tArticle.setComment(0);
        tArticle.setTitle(this.title);
        tArticle.setContent(this.content);
        tArticle.setTime(new Timestamp(this.time.getTime()));
        tArticle.setSort(this.sort);
        return tArticle;
    }

}

