package me.jesonlee.mymvc2.core;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 视图管理器，负责将视图映射为模板的名称
 * Created by Administrator on 2017/3/9 0009.
 */
public class ViewManager {
    private ProjectConfig config = ProjectConfig.getInstance();

    public void render(ModelAndView mv, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        //TODO:渲染页面
    }

    public String getTemplateFullName(String templateName) {
        String templatePackageLocation = config.templateLocation;
        return "/WEB-INF/classes/" + templatePackageLocation.replace(".", "/") + "/" + templateName;
    }
}
