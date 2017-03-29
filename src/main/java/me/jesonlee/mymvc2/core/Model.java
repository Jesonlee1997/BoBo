package me.jesonlee.mymvc2.core;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
public interface Model extends Map<Object, Object> {
    void addAttribute(Object name, Object value);
}
