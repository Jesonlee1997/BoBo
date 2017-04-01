package me.jesonlee.mymvc2.core;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Administrator on 2017/3/31 0031.
 */
public class PropertiesLoader implements ConfigLoader {
    private Properties properties = new Properties();

    @Override
    public boolean loadConfigs(String path) {
        try {
            properties.load(this.getClass().getResourceAsStream(path));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public Object getConfig(String name) {
        return properties.get(name);
    }
}
