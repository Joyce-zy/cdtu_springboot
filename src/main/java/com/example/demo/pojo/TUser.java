package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TUser {

  private long userId;
  private String userName;
  private String userPassword;
  private String userImg;
  private String userSign;
  private long userStatus;


}
