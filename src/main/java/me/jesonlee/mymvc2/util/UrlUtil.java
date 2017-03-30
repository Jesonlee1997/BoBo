package me.jesonlee.mymvc2.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
public class UrlUtil {
    public static final Pattern SETTINGPATTERN = Pattern.compile("\\((\\w+)\\)");

    public static String fixUrl(String requestUrl) {
        //TODO:修正URL 为每个url加上/的后缀方便正则表达式匹配
        if (requestUrl.endsWith("/")) {
            return requestUrl;
        }
        return requestUrl + "/";
    }


    public static String getRegexOfLocalUrl(String localUrl) {
        Matcher matcher = SETTINGPATTERN.matcher(localUrl);
        while (matcher.find()) {
            String name = matcher.group(1);
            localUrl = localUrl.replace("(" + name + ")", "(?<" + name + ">\\w+)");
        }
        localUrl = "^" + localUrl + "$";
        return localUrl;
    }
}
