package eu.andycraftz.myslots;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * MYSlots - Simple Slots Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @category Bukkit Plugin
 * @version 2.3
 */
public class Config {

    private final MySlots plugin;

    private final ConfigM cfgm;

    public Config(MySlots plugin) {
	this.plugin = plugin;
	this.cfgm = new ConfigM(this.plugin, "config");
    }

    public void saveConfig() {
	this.cfgm.saveConfig();
    }

    public FileConfiguration getConfig() throws NullPointerException {
	return this.cfgm.getConfig();
    }
}
