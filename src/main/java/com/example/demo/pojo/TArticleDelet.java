package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TArticleDelet {

  private long id;
  private String title;
  private String author;
  private String sort;
  private java.sql.Timestamp time;
  private long star;
  private long comment;
  private long visit;
  private String content;


}
