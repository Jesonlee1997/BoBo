package me.jesonlee.mymvc2.core;

import me.jesonlee.mymvc2.exception.BuildRequestHandlerFailedException;
import me.jesonlee.mymvc2.http.Request;
import me.jesonlee.mymvc2.util.MethodParameterUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 处理Request对象，并返回ModelAndView
 * Created by Administrator on 2017/3/13 0013.
 */
public class RequestHandler {
    private Object origin;

    private Method method;

    //方法中的参数名-参数映射
    private Map<String, Param> nameParamMap;

    //传给方法的model
    private Model model;

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
        ModelAndView mv = null;
        try {
            mv = new ModelAndView();
            Object[] args = bind(request);
            String name = (String) (method.invoke(origin, args));
            mv.setViewName(name);
            mv.setModel(model);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;//生成视图失败
        }
        //TODO:返回ModelAndView
        return mv;
    }

    private Object[] bind(Request request) {
        //TODO:将请求参数绑定到方法参数
        Map<String, Object> paramValueMap = request.getParamValueMap();
        List<Object> list = new LinkedList<>();
        for (Map.Entry<String, Param> entry : nameParamMap.entrySet()) {
            //TODO:封装这样的操作
            String name = entry.getKey();//参数名
            Param param = entry.getValue();//对应的参数

            //如果方法参数中有名为model并且类型为Model的参数，就把Model类的实例放到对应的位置上
            if ("model".equals(name) && param.clazz == Model.class) {
                model = new Model();
                param.setValue(new Model[]{model});

            } else {

                //将参数的值设为请求参数中对应参数的值
                param.setValue(paramValueMap.get(name));
            }
            list.add(param.getValue());
        }

        return list.toArray();
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
