package me.jesonlee.mymvc2.core;

/**
 * 加载相关配置类
 * Created by Administrator on 2017/3/31 0031.
 */
public interface ConfigLoader {
    //加载成功返回true，失败返回false
    boolean loadConfigs(String path);

    //返回对应配置名的值
    Object getConfig(String name);
}
