package me.jesonlee.mymvc2.core;

import me.jesonlee.mymvc2.exception.BuildRequestHandlerFailedException;
import me.jesonlee.mymvc2.http.Request;
import me.jesonlee.mymvc2.util.MethodParameterUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 处理Request对象，并返回ModelAndView
 * Created by Administrator on 2017/3/13 0013.
 */
public class RequestHandler {
    private Object origin;

    private Method method;

    private Object[] args;

    //参数名-参数映射
    private Map<String, Param> nameParamMap;

    private void setNameParamMap(Map<String, Param> nameParamMap) {
        this.nameParamMap = nameParamMap;
    }

    private RequestHandler() {}


    public static RequestHandler buildRequestHandler(Object origin, Method method)
            throws BuildRequestHandlerFailedException  {
        try {
            //TODO:解析方法的参数
            RequestHandler handler = new RequestHandler();
            handler.setMethod(method);
            handler.setOrigin(origin);
            handler.setNameParamMap(MethodParameterUtil.getParamsMap(method));
            return handler;
        } catch (Exception e) {
            throw new BuildRequestHandlerFailedException(e);
        }
    }

    public ModelAndView handle(Request request) {
        try {
            String name = (String) method.invoke(origin, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;//生成视图失败
        }
        //TODO:返回ModelAndView
        return null;
    }

    private void bind(Request request) {
        //TODO:将请求参数绑定到方法参数
        Map<String, Object> paramValueMap = request.getParamValueMap();
        for (Map.Entry<String, Param> entry : nameParamMap.entrySet()) {
            //TODO:封装这样的操作
            String name = entry.getKey();//参数名
            Param param = entry.getValue();

            param.setValue(paramValueMap.get(name));
        }
    }

    public Object getOrigin() {
        return origin;
    }

    public void setOrigin(Object origin) {
        this.origin = origin;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}
