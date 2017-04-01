package me.jesonlee.bobo.http;

import me.jesonlee.bobo.util.UrlUtil;

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
        //如果传来的url是动态url，则解析其中的参数
        url = UrlUtil.fixUrl(url);
        Map<String, Object> parameterMap = new HashMap<>();
        Enumeration<String> names = servletRequest.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            parameterMap.put(name, servletRequest.getParameterValues(name));
        }
        return new Request(parameterMap, url);
    }

}
