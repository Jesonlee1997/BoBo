package me.jesonlee.mymvc2.core;

import me.jesonlee.mymvc2.http.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/27 0027.
 */
public class UrlHandlerMap  {
    private Map<UrlPattern, RequestHandler> urlHandlerMap;

    public UrlHandlerMap() {
        urlHandlerMap = new HashMap<>();
    }

    public void put(String url, RequestHandler handler) {
        UrlPattern pattern = new UrlPattern(url);
        urlHandlerMap.put(pattern, handler);
    }

    public RequestHandler getHandler(Request request) {
        //TODO:match 输入的url，并在Request上添加参数
        for (UrlPattern pattern : urlHandlerMap.keySet()) {
            if (pattern.match(request)) {
                return urlHandlerMap.get(pattern);
            }
        }
        return null;
    }
}
