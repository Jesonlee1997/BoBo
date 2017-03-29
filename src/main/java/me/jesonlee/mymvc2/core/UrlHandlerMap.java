package me.jesonlee.mymvc2.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/27 0027.
 */
public class UrlHandlerMap  {
    private Map<String, RequestHandler> urlHandlerMap;

    public UrlHandlerMap() {
        urlHandlerMap = new HashMap<>();
    }

    public void put(String url, RequestHandler handler) {
        urlHandlerMap.put(url.toLowerCase(), handler);
    }

    public RequestHandler get(String url) {
        return urlHandlerMap.get(url);
    }
}
