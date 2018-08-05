package org.terraprime.TerraStaffChat;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.Listener;

import com.google.common.base.Joiner;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;

public class Main extends JavaPlugin implements Listener {

	
	@Override
	public void onEnable() {
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[TerraStaffChat] Starting");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[TerraStaffChat] Loading config...");
		
		getConfig().addDefault("Version", "1.0");
		getConfig().addDefault("PluginPrefix", "&7[&4TerraStaffChat&7] ");
		getConfig().addDefault("MainPrefix", "&7[&4TP Staff&7] ");
		getConfig().addDefault("ModPrefix", "&7[&4TP Mod&7] ");
		getConfig().addDefault("AdminPrefix", "&7[&4TP Admin&7] ");
		getConfig().addDefault("nickname", true);
		getConfig().addDefault("No Permission", "&4ERROR: You do not have permission to run this command!");
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[TerraStaffChat] Successfully loaded config.");
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[TerraStaffChat] Thank you for using TerraStaffChat by Kuller!");
		
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[TerraStaffChat] Stopped!");
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[TerraStaffChat] Thank you for using TerraStaffChat by Kuller!");
		
	}
	
	//private HashMap<String, Boolean> tsmtoggle = new HashMap();
    //private HashMap<String, Boolean> tsatoggle = new HashMap();
    //private HashMap<String, Boolean> tstoggle = new HashMap();
    //private ArrayList<String> tstoggle = new ArrayList();
	
	public boolean onCommand(CommandSender sender, Command cmd, String list, String args[]) {
		
		boolean nickname = getConfig().getBoolean("nickname");
		
		// Prefix for plugin functions
		String PluginNonColoredPrefix = getConfig().getString("PluginPrefix");
		String PluginColoredPrefix = ChatColor.translateAlternateColorCodes('&', PluginNonColoredPrefix);
		
		// Prefix for General Staff Channel
		String MainNonColoredPrefix = getConfig().getString("MainPrefix");
		String MainColoredPrefix = ChatColor.translateAlternateColorCodes('&', MainNonColoredPrefix);
		
		//Prefix for Moderator Channel
		String ModNonColoredPrefix = getConfig().getString("ModPrefix");
		String ModColoredPrefix = ChatColor.translateAlternateColorCodes('&', ModNonColoredPrefix);
		
		//Prefix for Admin Channel
		String AdminNonColoredPrefix = getConfig().getString("AdminPrefix");
		String AdminColoredPrefix = ChatColor.translateAlternateColorCodes('&', AdminNonColoredPrefix);

		if(cmd.getName().equalsIgnoreCase("terrastaffchat")) {
			
			if(sender.hasPermission("terrastaffchat.staffchat")) {
				
				if(args.length == 0) {
					
					sender.sendMessage(PluginColoredPrefix + ChatColor.RED + "Please type /terrastaffchat [message], /tsc [message], or /sc [message] to chat privately with other staff members.");
					return true;
					
				}
								
				StringBuilder x = new StringBuilder();
				for(int i = 0; i < args.length; i++) {
					
					x.append(args[i] + " ");
					
				}
				
				for(Player all : Bukkit.getOnlinePlayers()){
					if (all.hasPermission("terrastaffchat.staffchat")) {
						
						if (nickname) {
							all.sendMessage(MainColoredPrefix + ChatColor.RED + ((Player)sender).getDisplayName() + ": " + ChatColor.DARK_GREEN + x.toString());
						}
						else if (!nickname) {
							all.sendMessage(MainColoredPrefix + ChatColor.RED + sender.getName() + ": " + ChatColor.DARK_GREEN + x.toString());
						}
						//all.sendMessage(MainColoredPrefix + ChatColor.RED + sender.getName() + ": " + x.toString());
						//all.sendMessage(MainColoredPrefix + ChatColor.RED + ((Player)sender).getDisplayName() + ": " + ChatColor.DARK_GREEN + x.toString());
						
					}
				}
				return true;
				
			}
			else {
				
				String nonColoredPermError = getConfig().getString("No Permission");
				String coloredPermError  = ChatColor.translateAlternateColorCodes('&', nonColoredPermError);
				
				sender.sendMessage(PluginColoredPrefix + " " + coloredPermError);
				return true;
				
			}
			
		}
		
		
		if(cmd.getName().equalsIgnoreCase("adminchat")) {
			
			if(sender.hasPermission("terrastaffchat.admin")) {
				
				if(args.length == 0) {
					
					sender.sendMessage(PluginColoredPrefix + ChatColor.RED + "Please type /adminchat [message], /tsa [message], ac [message], or /sa [message] to chat privately with admins.");
					return true;
					
				}
								
				StringBuilder x = new StringBuilder();
				for(int i = 0; i < args.length; i++) {
					
					x.append(args[i] + " ");
					
				}
				
				for(Player all : Bukkit.getOnlinePlayers()){
					if (all.hasPermission("terrastaffchat.admin")) {
						
						//all.sendMessage(AdminColoredPrefix + ChatColor.RED + sender.getName() + ": " + x.toString());
						
						if (nickname) {
							all.sendMessage(AdminColoredPrefix + ChatColor.RED + ((Player)sender).getDisplayName() + ": " + ChatColor.DARK_GREEN + x.toString());
						}
						else if (!nickname) {
							all.sendMessage(AdminColoredPrefix + ChatColor.RED + sender.getName() + ": " + ChatColor.DARK_GREEN + x.toString());
						}
						
					}
				}
				return true;
				
			}
			else {
				
				String nonColoredPermError = getConfig().getString("No Permission");
				String coloredPermError  = ChatColor.translateAlternateColorCodes('&', nonColoredPermError);
				
				sender.sendMessage(PluginColoredPrefix + " " + coloredPermError);
				return true;
				
			}
			
		}
		
		
		if(cmd.getName().equalsIgnoreCase("modchat")) {
			
			if(sender.hasPermission("terrastaffchat.mod")) {
				
				if(args.length == 0) {
					
					sender.sendMessage(PluginColoredPrefix + ChatColor.RED + "Please type /modchat [message], /tsm [message], mc [message], or /sm [message] to chat privately with moderators.");
					return true;
					
				}
								
				StringBuilder x = new StringBuilder();
				for(int i = 0; i < args.length; i++) {
					
					x.append(args[i] + " ");
					
				}
				
				for(Player all : Bukkit.getOnlinePlayers()){
					if (all.hasPermission("terrastaffchat.mod")) {
						
						//all.sendMessage(ModColoredPrefix + ChatColor.RED + sender.getName() + ": " + x.toString());
						
						if (nickname) {
							all.sendMessage(ModColoredPrefix + ChatColor.RED + ((Player)sender).getDisplayName() + ": " + ChatColor.DARK_GREEN + x.toString());
						}
						else if (!nickname) {
							all.sendMessage(ModColoredPrefix + ChatColor.RED + sender.getName() + ": " + ChatColor.DARK_GREEN + x.toString());
						}
						
					}
				}
				return true;
				
			}
			else {
				
				String nonColoredPermError = getConfig().getString("No Permission");
				String coloredPermError  = ChatColor.translateAlternateColorCodes('&', nonColoredPermError);
				
				sender.sendMessage(PluginColoredPrefix + " " + coloredPermError);
				return true;
				
			}
			
		}
		//chat toggle start
		
		/*
		 * if(cmd.getName().equalsIgnoreCase("terrastaffchattoggle")) {
			
			if(sender.hasPermission("terrastaffchat.staffchat")) {
				
				if(tstoggle.contains(sender.getName())) {
				//if(tstoggle.containsKey(sender.getName())) {
					tstoggle.remove(sender.getName());
					sender.sendMessage(PluginColoredPrefix + ChatColor.GOLD + "Your messages will now go to public chat.");
				}
				else if (tsmtoggle.containsKey(sender.getName())) {
					sender.sendMessage(PluginColoredPrefix + ChatColor.DARK_RED + "You cannot toggle Staff and Moderator Chat streams on at the same time. Please toggle Moderator Chat off.");
				}
				else if (tsatoggle.containsKey(sender.getName())) {
					sender.sendMessage(PluginColoredPrefix + ChatColor.DARK_RED + "You cannot toggle Staff and Administrator Chat streams on at the same time. Please toggle Administrator Chat off."); 
				}
				else {
					//tstoggle.put(sender.getName(), Boolean.valueOf(false));
					tstoggle.add(sender.getName());
					sender.sendMessage(PluginColoredPrefix + ChatColor.GOLD + "You have switched on Staff Chat. All your messages will now go to the Staff Chat Stream.");
				}
			}
		}
		*/
		//chat toggle end
		
		if(cmd.getName().equalsIgnoreCase("tscreload")) {
			
			if(sender.hasPermission("terrastaffchat.reload")) {
				
				sender.sendMessage(PluginColoredPrefix + ChatColor.RED + "Reloading!");
				reloadConfig();
				sender.sendMessage(PluginColoredPrefix + ChatColor.RED + "Reloaded!");
				
				return true;
				// I love how this is the only one of my plugins that can reload their config ;D
				
			}
			else {
				
				String nonColoredPermError = getConfig().getString("No Permission");
				String coloredPermError  = ChatColor.translateAlternateColorCodes('&', nonColoredPermError);
				
				sender.sendMessage(PluginColoredPrefix + " " + coloredPermError);
				return true;
				
			}
			
		}
		
		return true;
		
	}
	
	/*@EventHandler
	public void onPlayerChat(org.bukkit.event.player.AsyncPlayerChatEvent e) {
		
		boolean nickname = getConfig().getBoolean("nickname");
		
		// Prefix for plugin functions
		String PluginNonColoredPrefix = getConfig().getString("PluginPrefix");
		String PluginColoredPrefix = ChatColor.translateAlternateColorCodes('&', PluginNonColoredPrefix);
		
		// Prefix for General Staff Channel
		String MainNonColoredPrefix = getConfig().getString("MainPrefix");
		String MainColoredPrefix = ChatColor.translateAlternateColorCodes('&', MainNonColoredPrefix);
		
		//Prefix for Moderator Channel
		String ModNonColoredPrefix = getConfig().getString("ModPrefix");
		String ModColoredPrefix = ChatColor.translateAlternateColorCodes('&', ModNonColoredPrefix);
		
		//Prefix for Admin Channel
		String AdminNonColoredPrefix = getConfig().getString("AdminPrefix");
		String AdminColoredPrefix = ChatColor.translateAlternateColorCodes('&', AdminNonColoredPrefix);
		
		Player p = e.getPlayer();
		String m = e.getMessage();

		
		if (p.hasPermission("terrastaffchat.staffchat")) {
			if (tstoggle.contains(p.getName())) {
			//if (tstoggle.containsKey(p.getName())) {
				if (!m.startsWith("/")) {
					
					/*StringBuilder x = new StringBuilder();
					for(int i = 0; i < args.length; i++) {
						
						x.append(args[i] + " ");
						
					}
					String msg = MainColoredPrefix + ChatColor.RED + ((Player)p).getDisplayName() + ": " + ChatColor.DARK_GREEN + m;
					//e.setMessage(msg);
					for(Player all : Bukkit.getOnlinePlayers()){
						if (all.hasPermission("terrastaffchat.staffchat")) {
							
							//all.sendMessage(ModColoredPrefix + ChatColor.RED + sender.getName() + ": " + x.toString());
							
							if (nickname) {
								//String msg = MainColoredPrefix + ChatColor.RED + ((Player)p).getDisplayName() + ": " + ChatColor.DARK_GREEN + m;
								all.sendMessage(MainColoredPrefix + ChatColor.RED + ((Player)p).getDisplayName() + ": " + ChatColor.DARK_GREEN + m);
								//e.setMessage(msg);
							}
							else if (!nickname) {
								all.sendMessage(MainColoredPrefix + ChatColor.RED + ((Player)p).getName() + ": " + ChatColor.DARK_GREEN + m);
							}
						}
					}
					e.setCancelled(true);
				}
			}
		}
		
	}*/

	
}
