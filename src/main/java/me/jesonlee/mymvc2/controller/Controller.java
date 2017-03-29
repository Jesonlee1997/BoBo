package me.jesonlee.mymvc2.controller;

import me.jesonlee.mymvc2.core.Model;

/**
 * 视图类，用来存放url映射的方法。
 * 方法命名规范：不要与Object类中的方法同名
 * 方法大小写不敏感
 * Created by Administrator on 2017/3/7 0007.
 */
public class Controller {
    public String hello(String name, int age) {
        System.out.println("name: " + name + " age: " + age);
        return "hello.jsp";
    }

    public String jeson() {

        return "jeson";
    }

    public String java(Model model, int age) {
        model.addAttribute("name", "jeson");

        return "java";
    }
}
