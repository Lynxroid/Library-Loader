package org.lynxroid.jblee.libloader;

import org.bukkit.plugin.java.JavaPlugin;

public final class LibLoader extends JavaPlugin {

    @Override
    public void onLoad() {

        getDataFolder().mkdirs();

    }

    @Override
    public void onEnable() {

        JarClassLoader.getInstance(getDataFolder().getPath());

    }
}
