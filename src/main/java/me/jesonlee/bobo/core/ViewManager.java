package me.jesonlee.bobo.core;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 视图管理器，负责将视图映射为模板的名称
 * Created by Administrator on 2017/3/9 0009.
 */
public class ViewManager {
    private ProjectConfigs config = ProjectConfigs.getInstance();

    public void render(ModelAndView mv, HttpServletRequest servletRequest,
                       HttpServletResponse servletResponse) throws ServletException, IOException {
        Map<String, Object> modelMap = mv.getModelMap();
        for (Map.Entry<String, Object> entry : modelMap.entrySet()) {
            servletRequest.setAttribute(entry.getKey(), entry.getValue());
        }
        //TODO:渲染页面 将页面加上对应的前缀
        String viewName = config.viewLocation + mv.getViewName();
        servletRequest.getRequestDispatcher(viewName).forward(servletRequest, servletResponse);
    }

    public String getViewPath(String viewName) {
        String templatePackageLocation = config.viewLocation;
        return config.viewLocation + templatePackageLocation.replace(".", "/") + "/" + viewName;
    }
}
