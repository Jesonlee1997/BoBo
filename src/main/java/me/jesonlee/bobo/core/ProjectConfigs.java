package me.jesonlee.bobo.core;

import me.jesonlee.bobo.exception.ConfigLoadException;
import me.jesonlee.bobo.loader.ConfigLoader;
import me.jesonlee.bobo.loader.XMLLoader;

import java.util.Map;

/**
 * 项目的整体配置
 * Created by Administrator on 2017/3/11 0011.
 */
public final class ProjectConfigs {
    private ConfigLoader configLoader;

    public void setConfigLoader(ConfigLoader configLoader) {
        this.configLoader = configLoader;
    }

    private ProjectConfigs() {
        //设置加载器
        setConfigLoader(new XMLLoader());

        boolean success = configLoader.loadConfigs("/bobo-config.xml");
        if (!success) {
            throw new ConfigLoadException("加载配置文件失败");
        }

        String ctllocation = (String) configLoader.getConfig("controllerLocation");
        if (ctllocation.startsWith("classpath:")) {
            //TODO:绝对路径？
            controllerLocation = ctllocation.replace("classpath:", "");
        } else {
            controllerLocation = ctllocation;
        }
        String vlocation = (String) configLoader.getConfig("viewLocation");
        if (vlocation.endsWith("/")) {
            viewLocation = vlocation;
        } else {
            viewLocation = vlocation + "/";
        }
        urlMethodMap = (Map<Object, Object>) configLoader.getConfig("urlMethodMap");
        if (viewLocation == null || controllerLocation == null || urlMethodMap == null) {
            throw new ConfigLoadException("配置不能为空");
        }
    }
    private static ProjectConfigs projectConfigs = new ProjectConfigs();
    public static ProjectConfigs getInstance() { return projectConfigs; }

    /*controller所在的目录*/
    public final String controllerLocation;

    /*视图文件所在的位置*/
    public final String viewLocation;

    public final Map<Object, Object> urlMethodMap;

    /*静态文件所处的位置*/
    //public final String staticFileLocation;
}
