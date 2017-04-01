package me.jesonlee.bobo.core;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
public class ModelAndView {
    private Model model;
    private String viewName;

    public Model getModel() {
        return model;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Map<String, Object> getModelMap() {
        return model.getMap();
    }
}
