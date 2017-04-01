package me.jesonlee.bobo.core;

import me.jesonlee.bobo.http.Request;

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
        for (UrlPattern pattern : urlHandlerMap.keySet()) {
            if (pattern.match(request)) {
                return urlHandlerMap.get(pattern);
            }
        }
        return null;
    }
}
