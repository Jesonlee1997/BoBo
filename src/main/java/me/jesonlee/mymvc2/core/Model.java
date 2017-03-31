package me.jesonlee.mymvc2.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
public class Model {
    private Map<String, Object> map;

    public Model() {
        map = new HashMap<>();
    }

    public void addAttribute(String name, Object value) {
        map.put(name, value);
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
