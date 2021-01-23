package eu.andycraftz.myslots;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

/**
 * MYSlots - Simple Slots Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @category Bukkit Plugin
 * @version 2.3
 */
public class LoginL implements Listener {
	
    private final MySlots plugin;
    
    public LoginL(MySlots plugin) {
	this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent e) {
	int slots = this.plugin.cfg.getConfig().getInt("Slots");
	if (e.getResult() == PlayerLoginEvent.Result.KICK_FULL) {

	    if (Bukkit.getOnlinePlayers().size() < slots) {
		e.allow();
	    }
	    else {
                if (e.getPlayer().hasPermission("myslots.full")) {
                    e.allow();
                    return;
                }
		e.disallow(Result.KICK_FULL, "§c" + this.plugin.lang.getLang().getString("Messages.FullKick").replaceAll("&", "§"));
	    }
	}
        else if (e.getResult() == PlayerLoginEvent.Result.ALLOWED) {

	    if (Bukkit.getOnlinePlayers().size() < slots) {
		e.allow();
	    }
	    else {
                if (e.getPlayer().hasPermission("myslots.full")) {
                    e.allow();
                    return;
                }
                e.disallow(Result.KICK_FULL, "§c" + this.plugin.lang.getLang().getString("Messages.FullKick").replaceAll("&", "§"));
	    }            
        }
    }
}
