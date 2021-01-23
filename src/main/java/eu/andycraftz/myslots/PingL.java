package eu.andycraftz.myslots;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

/**
 * MYSlots - Simple Slots Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @category Bukkit Plugin
 * @version 2.3
 */
public class PingL implements Listener {

    private final MySlots plugin;

    public PingL(MySlots plugin) {
	this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerListPing(ServerListPingEvent e) {
        Config cfg = new Config(this.plugin);
	e.setMaxPlayers(cfg.getConfig().getInt("Slots"));
    }
}
