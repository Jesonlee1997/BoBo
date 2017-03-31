package me.jesonlee.mymvc2.others;

import me.jesonlee.mymvc2.core.RequestHandler;
import me.jesonlee.mymvc2.exception.NoMethodFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 定义了视图类-方法映射应该具有的方法
 * Created by Administrator on 2017/3/10 0010.
 */
public interface ResolveMethod {
    Set<String> UNVALID_METHODS = new  HashSet<String>() {{
        add("equals");
        add("getClass");
        add("hashCode");
        add("notify");
        add("notify");
        add("notifyAll");
        add("toString");
        add("wait");
    }};

    //寻找方法名对应的处理器，方法名应该是忽略大小写的，没有找到则抛出一个异常
    RequestHandler findHandlerOfMethod(String methodName) throws NoMethodFoundException;

    //解析Class数组中的方法，并建立一个方法名-RequestHandler的映射
    void resolve(List<Class> classes);
}
