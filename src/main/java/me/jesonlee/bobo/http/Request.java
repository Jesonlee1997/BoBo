package me.jesonlee.bobo.http;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
public class Request {
    //参数名-参数值（一般为String[]）
    private Map<String, Object> paramValueMap;
    private String url;

    public Request(Map<String, Object> paramValueMap, String url) {
        this.paramValueMap = paramValueMap;
        this.url = url;
    }

    public Map<String, Object> getParamValueMap() {
        return paramValueMap;
    }

    public String getUrl() {
        return url;
    }

    public void addParam(String name, Object value) {
        paramValueMap.put(name, value);
    }

    @Override
    public String toString() {
        return "url: "+ url + "map: " + paramValueMap;
    }
}
