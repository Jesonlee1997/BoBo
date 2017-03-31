package me.jesonlee.mymvc2.others;

import me.jesonlee.mymvc2.core.RequestHandler;
import me.jesonlee.mymvc2.exception.NoMethodFoundException;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Administrator on 2017/3/10 0010.
 */
public class ResolveMethodImpl implements ResolveMethod {
    //方法名-处理器映射  方法名是忽略大小写的
    private Map<String, RequestHandler> methodNameHandlerMap= new HashMap<>();

    @Override
    public RequestHandler findHandlerOfMethod(String methodName) throws NoMethodFoundException {
        RequestHandler requestHandler = methodNameHandlerMap.get(methodName);
        if (requestHandler == null) {
            throw new NoMethodFoundException("没有找到方法名对应的处理器。");
        }
        return requestHandler;
    }


    @Override
    public void resolve(List<Class> classes) {
        for (Class clazz : classes) {
            try {
                Object o = clazz.newInstance();
                Method[] methods = clazz.getMethods();
                String methodName;
                for (Method method : methods) {
                    methodName = method.getName();
                    if (UNVALID_METHODS.contains(methodName)) {
                        continue;
                    }
                    RequestHandler handler = RequestHandler.buildRequestHandler(o, method);
                    addMethodNameHandlerMap(methodName, handler);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void addMethodNameHandlerMap(String methodName, RequestHandler handler) {
        methodNameHandlerMap.put(methodName.toLowerCase(), handler);
    }
}
