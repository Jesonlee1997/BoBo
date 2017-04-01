package me.jesonlee.bobo.util;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import me.jesonlee.bobo.core.Param;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 将方法的名称和方法的类型对应起来（Map中的顺序很重要，表示参数出现的顺序，所以要用LinkedHashMap）
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


}
