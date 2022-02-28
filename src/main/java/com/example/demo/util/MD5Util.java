package com.example.demo.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import sun.security.provider.MD5;

import java.util.UUID;

public class MD5Util {
    public static String encryption(String source,String salt)
    {
        Md5Hash md5Hash = new Md5Hash(source, salt, 1000);
        String s = md5Hash.toString();
        return s;
    }

//    public static void main(String[] args) {
//        String s = encryption("123456", "admin");
//        System.out.println(s);
//
//    }



}
