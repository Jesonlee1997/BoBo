package me.jesonlee.mymvc2.core;

import me.jesonlee.mymvc2.http.Request;
import me.jesonlee.mymvc2.others.ClasspathPackageScanner;
import me.jesonlee.mymvc2.others.ResolveMethod;
import me.jesonlee.mymvc2.others.ResolveMethodImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

    private PackageScanner packageScanner;


    public ResolveMethod resolveMethod;

    //代表url-方法名的键值对
    public Properties properties;

    //存放映射的键值对，url的值为小写，Request类中的url也需要是小写
    public UrlHandlerMap urlHandlerMap ;

    //初始化，只有调用了init方法MappingManager才是准备好的
    public boolean init() {
        try {
            //初始化属性
            properties = new Properties();
            urlHandlerMap = new UrlHandlerMap();
            resolveMethod = new ResolveMethodImpl();
            // TODO: 将路径换为Settings里的basePackage
            String path = "me.jesonlee.mymvc2.controller";
            packageScanner = new ClasspathPackageScanner(path);


            // TODO:需要建立url-Handler映射，hander需要不同类的实例，需要一个扫描方法返回Class数组
            //当没有扫描到类时抛出异常
            List<Class> classes = packageScanner.scanClasses();
            resolveMethod.resolve(classes);

            //加载urlMap配置文件，并将对应的键值对加载到urlHandlerMap中
            properties.load(MappingManager.class.getResourceAsStream("/urlMapMethod.properties"));
            addUrlHandlerMap(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    /*将properties中的url-方法名解析成程序可用的url-Handler*/
    private void addUrlHandlerMap(Map<Object, Object> properties) {
        RequestHandler requestHandler;
        String url;
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            //查找是否存在该方法名，findMethod会将methodName转化为小写之后再进行查找
            requestHandler = resolveMethod.findHandlerOfMethod((String) entry.getValue());
            url = (String) entry.getKey();
            urlHandlerMap.put(url, requestHandler);
        }
    }

    //获得请求中对应url的处理器
    RequestHandler getHandler(Request request) {
        //TODO:match URL

        return urlHandlerMap.getHandler(request);
    }
}
