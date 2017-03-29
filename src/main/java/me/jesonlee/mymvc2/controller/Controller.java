package me.jesonlee.mymvc2.controller;

/**
 * 视图类，用来存放url映射的方法。
 * 方法命名规范：不要与Object类中的方法同名
 * 方法大小写不敏感
 * Created by Administrator on 2017/3/7 0007.
 */
public class Controller {
    public String hello() {
        return "hello.jsp";
    }

    public String jeson() {
        return "jeson";
    }

    public String java() {
        return "java";
    }
}
