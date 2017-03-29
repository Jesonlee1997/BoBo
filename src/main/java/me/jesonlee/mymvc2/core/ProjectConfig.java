package me.jesonlee.mymvc2.core;

/**
 * 项目的整体配置
 * Created by Administrator on 2017/3/11 0011.
 */
public final class ProjectConfig {
    private ProjectConfig() {}
    private static ProjectConfig projectConfig = new ProjectConfig();
    public static ProjectConfig getInstance() { return projectConfig; }

    /*视图的所存放的package的位置*/
    public final String viewLocation = "me.jesonlee.mymvc2.view";

    /*Jsp文件所在的位置*/
    public final String templateLocation = "templates";

    /*静态文件所处的位置*/
    public final String staticFileLocation = "resource";

}
