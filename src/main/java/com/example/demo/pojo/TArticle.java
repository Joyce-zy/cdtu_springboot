package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_article")
public class TArticle {
@TableId(value = "id",type = IdType.AUTO)
  private long id;
@TableField("title")
  private String title;
@TableField("author")
  private String author;
@TableField("sort")
  private String sort;
@TableField("time")
  private java.sql.Timestamp time;
  @TableField("star")
  private long star;
  @TableField("comment")
  private long comment;
  @TableField("visit")
  private long visit;
  @TableField("content")
  private String content;


}
