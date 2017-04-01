package test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/4/1 0001.
 */
public class Dom4JDemo {

    public static void main(String[] args) {
        SAXReader reader = new SAXReader();
        String path = Dom4JDemo.class.getResource("/test.xml").getPath();
        File file = new File(path);
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        Element view = root.element("view");
        Element controller = root.element("controller");
        Element urlPatterns = root.element("url-patterns");

        Element viewLocation = view.element("location");
        Element controllerLocation = controller.element("location");
        List<Element> urlPattern = urlPatterns.elements();

        System.out.println(viewLocation.getText());
        System.out.println(controllerLocation.getText());
        for (Element element : urlPattern) {
            System.out.println(element.element("url").getText());
            System.out.println(element.element("method").getText());
        }
    }

}
