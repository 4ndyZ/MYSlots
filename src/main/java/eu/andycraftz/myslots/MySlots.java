package eu.andycraftz.myslots;

import java.util.ArrayList;

import eu.andycraftz.utils.AbstractCommand;

import java.util.logging.Level;
import org.bstats.bukkit.MetricsLite;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * MYSlots - Simple Slots Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @category Bukkit Plugin
 * @version 2.3
 */
public class MySlots extends JavaPlugin {
    
    // Config
    public Config cfg;
    public Lang lang;
    
    @Override
    public void onEnable() {
        // Message
        getLogger().log(Level.INFO, "[#]==========< MYSlots >==========[#]");
        getLogger().log(Level.INFO, "Version: {0}", getDescription().getVersion());
        getLogger().log(Level.INFO, "Web: https://dev.bukkit.org/bukkit-plugins/myslots/");
        // Metrics
        MetricsLite metrics = new MetricsLite(this, 10078);
        // Config
        cfg = new Config(this);
        lang = new Lang(this, cfg.getConfig().getString("MYSlots.Language"));
        // Message
        getLogger().log(Level.INFO, "Plugin by AndyCraftz");
        // Listener
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new LoginL(this), this);
        pm.registerEvents(new PingL(this), this);
        // Commands
        ArrayList<AbstractCommand> Commands = new ArrayList();
        Commands.add(new MySlotsC(this));
	Commands.forEach(all -> {
	    all.register();
	});
	 // Check if enabled
        if (cfg.getConfig().getBoolean("MYSlots.Enabled") != true) {
            getLogger().log(Level.INFO, "[#]==========< MYSlots >==========[#]");
            getLogger().log(Level.INFO, "Plugin was disabled. If you wish to enable it please turn it back on in the 'config.yml'.");
            Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("MYSlots"));
        }
    }
}
