package me.jesonlee.mymvc2.http;

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
        this.url = url.toLowerCase();
    }

    public Map<String, Object> getParamValueMap() {
        return paramValueMap;
    }

    public void setParamValueMap(Map<String, Object> paramValueMap) {
        this.paramValueMap = paramValueMap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url.toLowerCase();
    }

    @Override
    public String toString() {
        return "url: "+ url + "map: " + paramValueMap;
    }
}
