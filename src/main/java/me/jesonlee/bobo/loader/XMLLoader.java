package me.jesonlee.bobo.loader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import test.Dom4JDemo;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用Dom4J进行解析
 * Created by Administrator on 2017/3/31 0031.
 */
public class XMLLoader implements ConfigLoader {
    private Map<Object, Object> configMap = new HashMap<>();

    @Override
    public boolean loadConfigs(String path) {
        try {
            SAXReader reader = new SAXReader();
            String absolutePath = Dom4JDemo.class.getResource(path).getPath();
            File file = new File(absolutePath);
            Document document = null;
            try {
                document = reader.read(file);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            Element root = document.getRootElement();
            Element view = root.element("view");
            Element controller = root.element("controller");
            List<Element> urlPatterns = root.element("url-patterns").elements();

            Element viewLocation = view.element("location");
            Element controllerLocation = controller.element("location");
            Map<Object, Object> urlMethodMap = new HashMap<>();

            configMap.put("viewLocation", viewLocation.getText());
            configMap.put("controllerLocation", controllerLocation.getText());

            for (Element urlPattern : urlPatterns) {
                urlMethodMap.put(urlPattern.element("url").getText(),
                        urlPattern.element("method").getText());
            }
            configMap.put("urlMethodMap", urlMethodMap);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Object getConfig(String name) {
        return configMap.get(name);
    }
}
