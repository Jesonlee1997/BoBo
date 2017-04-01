package me.jesonlee.mymvc2.util;

import me.jesonlee.mymvc2.core.Param;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/1 0001.
 */
public class MethodParameterUtilTest {
    @Test
    public void getParamsMap() throws Exception {
        Method[] methods = MethodParameterUtilTest.class.getMethods();
        Map<String, Param> map = null;
        for (Method method : methods) {
            if (method.getName().equals("test"))
                map = MethodParameterUtil.getParamsMap(method);
        }
        for (Map.Entry<String, Param> entry : map.entrySet()) {
            System.out.println("name: " + entry.getKey()+ " param value " + entry.getValue());
        }
    }

    public void test(String name, Date date, int number) {

    }

}