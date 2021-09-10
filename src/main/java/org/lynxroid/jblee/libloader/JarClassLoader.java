package org.lynxroid.jblee.libloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class JarClassLoader {

    private static final Map<String, JarClassLoader> instanceMap = new HashMap<>();
    private static final Logger logger = Logger.getLogger("ClassLoader");
    private final String libDir;

    private JarClassLoader(String libDir) {
        this.libDir = libDir;

        try {
            load();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static JarClassLoader getInstance(String libDir) {
        JarClassLoader jcl;

        if (!instanceMap.containsKey(libDir)) {
            jcl = new JarClassLoader(libDir);
            instanceMap.put(libDir, jcl);
        } else {
            jcl = instanceMap.get(libDir);
        }
        return jcl;
    }

    private URL convertURL(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void load() throws NoSuchMethodException {

        File[] jars = new File(libDir).listFiles(pathname -> pathname.toString().toLowerCase().endsWith(".jar"));

        ClassLoader clsLoader = ClassLoader.getSystemClassLoader();
        URL[] urllist = Arrays.stream(jars)
                .map(this::convertURL).toArray(URL[]::new);

        URLClassLoader loader = new URLClassLoader(new URL[0], clsLoader);

//        TODO

    }
}
