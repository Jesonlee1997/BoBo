package me.jesonlee.mymvc2.core;

import org.junit.Test;

/**
 * Created by Administrator on 2017/4/1 0001.
 */
public class XMLLoaderTest {
    XMLLoader xmlLoader = new XMLLoader();

    @Test
    public void loadConfigs() throws Exception {
        xmlLoader.loadConfigs("/test.xml");
    }

    @Test
    public void getConfig() throws Exception {
        xmlLoader.loadConfigs("/test.xml");
        Object o = xmlLoader.getConfig("urlMethodMap");
    }

}