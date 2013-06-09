package com.github.announcement.solidmode;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
public class SolidModeListener implements Listener {
	public SolidMode plugin;
	
	public SolidModeListener(SolidMode instance) {
		plugin = instance;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
		if(plugin.isCreator(player)){
			Block block = event.getBlock();
			Material mat = block.getType();
			PlayerInventory pinv = player.getInventory();
			if (player.getGameMode() != GameMode.CREATIVE){
				pinv.setItemInHand(new ItemStack(mat));
				player.updateInventory();
				//pinv.setContents(pinv.getContents());
			}
			player.sendMessage("You placed block: "+mat);
		}
	}
	@EventHandler
	public void OnBlockDamage(BlockDamageEvent event)
	{
		Player player=event.getPlayer();
		Block block = event.getBlock();;
		@SuppressWarnings("unused")
		ItemStack itemInHand = event.getItemInHand();
		@SuppressWarnings("unused")
		boolean instaBreak = event.getInstaBreak();
		if(plugin.isCreator(player)){
			if (player.getGameMode() != GameMode.CREATIVE){
				block.setTypeId(0);
			}
		}
	}
}
