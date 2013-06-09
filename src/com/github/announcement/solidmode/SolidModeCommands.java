package com.github.announcement.solidmode;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class SolidModeCommands implements CommandExecutor {
	private final SolidMode plugin;
	
	public SolidModeCommands(SolidMode plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("solidmode")){
			///solidmode <toggle|check|help|set|save|load|world>
			if (!(sender instanceof Player)){
				sender.sendMessage("This command is Player only for the time being");
				return true;
			}else{
				Player player = (Player) sender;
				if (args.length < 1){
					sender.sendMessage("Not enough arguments");
					return false;
				}
				Location loc = player.getLocation();
				World world = loc.getWorld();
				if (args[0].equalsIgnoreCase("toggle")) {
					if (world.getName().equalsIgnoreCase(plugin.planet)){
						boolean creating = plugin.isCreator(player);
						//Set this up to handle inventory
						if (creating){
							sender.sendMessage("You are no longer creating.");
							player.setAllowFlight(false);
						}else{
							sender.sendMessage("You are now a creator.");
							player.setAllowFlight(true);
						}
						plugin.setCreator(player, !creating);
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("check")){
					if (plugin.isCreator(player)){
						sender.sendMessage("You are a creator");
					}else{
						sender.sendMessage("You are not a creator");
					}
				}
				if(args[0].equalsIgnoreCase("world")){
					if (args.length == 2){
						plugin.planet = args[1];
						sender.sendMessage("Planet is now "+args[1]);
					}else{
						sender.sendMessage("Planet is "+plugin.planet);
					}
				}
				if (args[0].equalsIgnoreCase("name")) {
					if (args.length == 2){
						player.setDisplayName(args[1]);
					}else{
						player.setDisplayName("");
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("hand")){
					sender.sendMessage("Item: "+player.getInventory().getItemInHand().getType());
					return true;
				}
				sender.sendMessage("Null response args[0]=\""+args[0]+"\"");
			}
			return true;
		}
		return false;
	}
}
