package me.jesonlee.mymvc2.others;

import me.jesonlee.mymvc2.exception.NoClassScannedException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Logger;

/**
 * 用于扫描特定子目录下的工具类
 * Created by Administrator on 2017/3/9 0009.
 */
public class ClasspathPackageScanner implements PackageScanner {
    private static Logger logger = Logger.getLogger(ClasspathPackageScanner.class.toString());
    private String basePackage;
    private ClassLoader classLoader;

    /**
     * Construct an instance and specify the base package it should scan.
     * @param basePackage The base package to scan.
     */
    public ClasspathPackageScanner(String basePackage) {
        this.basePackage = basePackage;
        this.classLoader = getClass().getClassLoader();
    }

    /**
     * Construct an instance with base package and class loader.
     * 使用
     * @param basePackage The base package to scan.
     * @param classLoader Use this class load to locate the package.
     */
    public ClasspathPackageScanner(String basePackage, ClassLoader classLoader) {
        this.basePackage = basePackage;
        this.classLoader = classLoader;
    }

    /**
     * Get all fully qualified names located in the specified package
     * and its sub-package.
     * 获得所有的指定包下的类
     * @return A list of fully qualified names. 返回类名列表
     * @throws IOException
     */
    @Override
    public List<Class> scanClasses() throws IOException {
        logger.info("开始扫描包" + basePackage + "下的所有类");
        List<String> classNameList = new ArrayList<>();
        doScan(basePackage, classNameList);
        List<Class> classes = new ArrayList<>();
        for (String className : classNameList) {
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return classes;
    }

    /**
     * Actually perform the scanning procedure.
     *
     * @param basePackage 将要扫描的基包
     * @param nameList A list to contain the result.
     * @return A list of fully qualified names.
     *
     * @throws IOException, NoClassScannedException（当没有在指定的包路径下扫描到class文件抛出）
     */
    private List<String> doScan(String basePackage, List<String> nameList) throws IOException {
        // replace dots with splashes 使用'/'代替'.'
        String splashPath = StringUtil.dotToSplash(basePackage);

        // get file path  获得文件的路径
        URL url = classLoader.getResource(splashPath);
        String filePath = StringUtil.getRootPath(url);

        /*
         If the web server unzips the jar file, then the classes will exist in the form of
         normal file in the directory.
         If the web server does not unzip the jar file, then classes will exist in jar file.*/

        // contains the name of the class file. e.g., Apple.class will be stored as "Apple"
        List<String> names = null;
        if (isJarFile(filePath)) {
            // jar file
            logger.fine(filePath + " 是一个JAR包");

            names = readFromJarFile(filePath, splashPath);
        } else {
            // directory
            logger.fine(filePath + " 是一个目录");
            names = readFromDirectory(filePath);
        }

        if (names == null) {
            throw new NoClassScannedException(basePackage + "下没有发现class类文件");
        }

        for (String name : names) {
            if (isClassFile(name)) {
                //nameList.add(basePackage + "." + StringUtil.trimExtension(name));
                //如果是类文件
                nameList.add(toFullyQualifiedName(name, basePackage));
            } else {
                // this is a directory 如果是一个目录
                // check this directory for more classes 扫描这个目录下的更多文件
                // do recursive invocation 递归调用
                doScan(basePackage + "." + name, nameList);
            }
        }
        return nameList;
    }

    /**
     * Convert short class name to fully qualified name.
     * e.g., String -> java.lang.String
     */
    private String toFullyQualifiedName(String shortName, String basePackage) {
        StringBuilder sb = new StringBuilder(basePackage);
        sb.append('.');
        sb.append(StringUtil.trimExtension(shortName));

        return sb.toString();
    }

    private List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {
        /*if (logger.isDebugEnabled()) {
            logger.debug("从JAR包中读取类: {}", jarPath);
            TODO:LOG
        }*/

        JarInputStream jarIn = new JarInputStream(new FileInputStream(jarPath));
        JarEntry entry = jarIn.getNextJarEntry();

        List<String> nameList = new ArrayList<>();
        while (null != entry) {
            String name = entry.getName();

            if (name.startsWith(splashedPackageName) && isClassFile(name)) {
                nameList.add(name);
            }

            entry = jarIn.getNextJarEntry();
        }

        return nameList;
    }

    private List<String> readFromDirectory(String path) {
        File file = new File(path);

        //返回file目录下的所有文件和文件夹的名字
        String[] names = file.list();

        if (null == names) {
            return null;
        }

        return Arrays.asList(names);
    }

    private boolean isClassFile(String name) {
        return name.endsWith(".class");
    }

    private boolean isJarFile(String name) {
        return name.endsWith(".jar");
    }

    /**
     * For test purpose.
     */
    public static void main(String[] args) throws Exception {
        PackageScanner scan = new ClasspathPackageScanner("me.jesonlee.mymvc2.core");
    }

}
