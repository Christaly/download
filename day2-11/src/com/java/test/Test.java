package com.java.test;


import com.java.dao.UserDao;
import com.java.entity.User;

public class Test {
    public static void main(String[] args) {
        User user=new User();
        user.setUsername("huge");
        user.setPassword("888");
        User login = new UserDao().login(user);
        System.out.println(login);
    }



}
