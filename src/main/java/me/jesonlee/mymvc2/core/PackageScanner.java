package me.jesonlee.mymvc2.core;

import java.io.IOException;
import java.util.List;

/**
 * 包扫描类
 * Created by Administrator on 2017/3/9 0009.
 */
public interface PackageScanner {
    public List<String> getFullyQualifiedClassNameList() throws IOException;
}
