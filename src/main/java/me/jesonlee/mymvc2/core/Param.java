package me.jesonlee.mymvc2.core;

/**
 *
 * Created by Administrator on 2017/3/26 0026.
 */
public class Param {
    private Class clazz;//代表参数的类型
    private Object[] value;//代表参数的值 TODO:对value进行合适的转换

    public Param(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setValue(Object value) throws Exception {
        Object[] values = (Object[]) value;
        this.value = new Object[values.length];
        for (Object o : values) {
            if (clazz == int.class || clazz == Integer.class) {
                this.value[0] = Integer.parseInt((String) o);
            } else {
                this.value[0] = o;
            }
        }
    }

    /*如果参数只对应一个值则返回这个值*/
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
