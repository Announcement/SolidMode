package com.github.announcement.solidmode;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import org.bukkit.entity.Player;
public final class SolidMode extends JavaPlugin {
	private final HashMap<Player, Boolean> creators = new HashMap<Player, Boolean>();
	public String planet = "world";
	public void onEnable(){
		getLogger().info("SolidMode plugin is now enabled");
		getCommand("solidmode").setExecutor(new SolidModeCommands(this));
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new SolidModeListener(this),this);	
	}
	public void onDisable(){
		getLogger().info("SolidMode plugin is now disabled");
	}
	public boolean isCreator(final Player player) {
		if (creators.containsKey(player)){
			return creators.get(player);
		}else{
			return false;
		}
	}
	public void setCreator(final Player player, final boolean value){
		creators.put(player, value);
	}
}