package test;

import me.jesonlee.bobo.util.UrlUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/30 0030.
 */
public class RegexTest {

    //测试分组
    public static void test1() {
        Pattern p=Pattern.compile("^/jeson/(\\w+)/(\\w+)/$");
        Matcher m=p.matcher("/jeson/2223/bb/");
        m.find();
        int i = m.groupCount();
        for (int j = 1; j <= i; j++) {
            System.out.println(m.group());
        }
    }

    //测试有命名的分组
    public static void test2(String regex, String url) {
        //^/jeson/(?<id>\d+)/(?<name>\w+)/
        Pattern p=Pattern.compile(regex);
        Matcher m=p.matcher(url);
        System.out.println(m.matches());
        System.out.println("id:" + m.group("id"));
        System.out.println("name:" + m.group("name"));
    }

    //测试写在配置文件url中的分组名，如/jeson/(id)/(name)/
    public static String test3(String localUrl) {
        String url = localUrl;

        Pattern pattern = UrlUtil.SETTINGPATTERN;
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            String name = matcher.group(1);
            url = url.replace("(" + name + ")", "(?<" + name + ">\\w+)");
        }
        url = "^" + url + "$";
        return url;
    }

    public static void main(String[] args) {
        String s = test3("/jeson/(id)/(name)/");
        test2(s, "/jeson/aef/faer/");
    }
}
