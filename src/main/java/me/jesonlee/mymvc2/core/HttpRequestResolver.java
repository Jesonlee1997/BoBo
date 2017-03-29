package me.jesonlee.mymvc2.core;

import me.jesonlee.mymvc2.http.Request;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
public class HttpRequestResolver {
    public static Request resolve(HttpServletRequest servletRequest) {
        String url = servletRequest.getRequestURI();
        Map<String, Object> parameterMap = new HashMap<>();
        Enumeration<String> names = servletRequest.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            parameterMap.put(name, servletRequest.getParameterValues(name));
        }
        return new Request(parameterMap, url);
    }
}
