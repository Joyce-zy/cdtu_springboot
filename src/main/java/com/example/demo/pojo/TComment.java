package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TComment {

  private long id;
  private long articleId;
  private String nickname;
  private String content;
  private java.sql.Timestamp time;
  private long star;
  private long diss;


}
