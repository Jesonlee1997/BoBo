package me.jesonlee.mymvc2.core;

import me.jesonlee.mymvc2.http.Request;
import me.jesonlee.mymvc2.others.ClasspathPackageScanner;
import me.jesonlee.mymvc2.others.PackageScanner;
import me.jesonlee.mymvc2.others.ResolveMethod;
import me.jesonlee.mymvc2.others.ResolveMethodImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 核心之二，映射管理器
 * 管理了两个映射
 * 间接管理：方法名-处理器映射（resolveMethod），
 * 直接管理：url-处理器映射（urlHandlerMap）
 * Created by Administrator on 2017/3/7 0007.
 */

public class MappingManager {

    //单例模式
    private MappingManager() {}
    private static MappingManager mappingManager = new MappingManager();
    public static MappingManager getMappingManager() {
        return mappingManager;
    }

    private ProjectConfigs config = ProjectConfigs.getInstance();

    //controller方法解析类
    private ResolveMethod resolveMethod;

    //存放映射的键值对，url的值为小写，Request类中的url也需要是小写
    private UrlHandlerMap urlHandlerMap ;

    //初始化，只有调用了init方法MappingManager才是准备好的
    public boolean init() {
        try {
            //初始化属性
            Map<Object, Object> urlMethodMap;
            urlHandlerMap = new UrlHandlerMap();
            resolveMethod = new ResolveMethodImpl();
            String path = config.controllerLocation;
            PackageScanner packageScanner = new ClasspathPackageScanner(path);

            //扫描controller包下的类
            List<Class> classes = packageScanner.scanClasses();
            resolveMethod.resolve(classes);

            //将urlMethodMap中的键值对转换为urlHandlerMap中
            urlMethodMap = config.urlMethodMap;
            addUrlHandlerMap(urlMethodMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    /*url-方法名解析成程序可用的url-Handler*/
    private void addUrlHandlerMap(Map<Object, Object> urlMethodNameMap) {
        RequestHandler requestHandler;
        String url;
        for (Map.Entry<Object, Object> entry : urlMethodNameMap.entrySet()) {
            //查找是否存在该方法名，findMethod会将methodName转化为小写之后再进行查找
            requestHandler = resolveMethod.findHandlerOfMethod((String) entry.getValue());
            url = (String) entry.getKey();
            urlHandlerMap.put(url, requestHandler);
        }
    }

    //获得请求中对应url的处理器
    RequestHandler getHandler(Request request) {
        return urlHandlerMap.getHandler(request);
    }
}
