package me.jesonlee.mymvc2.core;

import me.jesonlee.mymvc2.http.Request;
import me.jesonlee.mymvc2.util.UrlUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Created by Administrator on 2017/3/30 0030.
 */
public class UrlPattern {
    private Pattern pattern;
    private List<String> urlParamNames;

    public UrlPattern(String localUrl) {
        Pattern pattern = UrlUtil.SETTINGPATTERN;
        Matcher matcher = pattern.matcher(localUrl);
        urlParamNames = new ArrayList<>();
        for (int i = 0;matcher.find(); i++) {
            String name = matcher.group(1);
            urlParamNames.add(name);
            localUrl = localUrl.replace("(" + name + ")", "(?<" + name + ">\\w+)");
        }
        this.pattern = Pattern.compile("^" + localUrl + "$");
    }

    public boolean match(Request request) {
        String requestUrl = request.getUrl();
        Matcher matcher = pattern.matcher(requestUrl);
        //如果匹配成功，在请求中添加对应的参数和参数值
        if (matcher.matches()) {
            for (String urlParamName : urlParamNames) {
                request.addParam(urlParamName, new Object[]{matcher.group(urlParamName)});
            }
            return true;
        }
        return false;

    }
}
