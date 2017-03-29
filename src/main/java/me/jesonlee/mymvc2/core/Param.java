package me.jesonlee.mymvc2.core;

/**
 *
 * Created by Administrator on 2017/3/26 0026.
 */
public class Param {
    Class clazz;//代表参数的类型
    Object[] value;//代表参数的值 TODO:对value进行合适的转换

    public Param(Class clazz) {
        this.clazz = clazz;
    }

    //
    public void setValue(Object value) {
        for (String s : (String[]) value) {
            if (clazz == int.class) {
                this.value[0] = Integer.parseInt(s);
            }
        }
    }

    public Object getValue() {
        if (value == null || value.length > 1) {
            return value;
        }
        return value[0];
    }

    @Override
    public String toString() {
        return "class: " + clazz + "  value: " + value;
    }
}
