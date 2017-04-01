package me.jesonlee.bobo.util;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
public class UrlUtil {
    public static final Pattern SETTINGPATTERN = Pattern.compile("\\((\\w+)\\)");

    public static String fixUrl(String requestUrl) {
        if (requestUrl.endsWith("/")) {
            return requestUrl;
        }
        return requestUrl + "/";
    }


}
