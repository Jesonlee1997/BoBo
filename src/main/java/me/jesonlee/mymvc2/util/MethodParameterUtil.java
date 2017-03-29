package me.jesonlee.mymvc2.util;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import me.jesonlee.mymvc2.core.Param;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
public class MethodParameterUtil {
    //得到Map的参数名字和类型对象的映射，名字的顺序和在方法中定义的一样
    public static Map<String, Param> getParamsMap(Method method) throws NotFoundException {
        Class[] classes = method.getParameterTypes();
        Map<String, Param> nameParamMap = new LinkedHashMap<>();
        Class clazz = method.getDeclaringClass();
        String methodName = method.getName();
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(clazz));
        CtClass cc = pool.get(clazz.getName());
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr =
                (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            return null;
        }
        String[] paramNames = new String[cm.getParameterTypes().length];
        int pos = java.lang.reflect.Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < paramNames.length; i++) {
            paramNames[i] = attr.variableName(i + pos);
            nameParamMap.put(paramNames[i], new Param(classes[i]));
        }
        return nameParamMap;
    }

    public static void main(String[] args) throws NotFoundException {
        Method[] methods = MethodParameterUtil.class.getMethods();
        Map<String, Param> map = null;
        for (Method method : methods) {
            if (method.getName().equals("test"))
                map = getParamsMap(method);
        }
        for (Map.Entry<String, Param> entry : map.entrySet()) {
            System.out.println("name: " + entry.getKey()+ " param value " + entry.getValue());
        }
    }
    
    public void test(String name, Date date, int number) {
        
    }
}
