package io.github.somesourcecode.someguiapi;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(new GuiListener(), this);
	}

}