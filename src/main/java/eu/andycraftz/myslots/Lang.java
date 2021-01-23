package eu.andycraftz.myslots;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * MYSlots - Simple Slots Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @category Bukkit Plugin
 * @version 2.3
 */
public class Lang {
    
    private final MySlots plugin;

    private final ConfigM cfgm;

    public Lang(MySlots plugin, String lang) {
	this.plugin = plugin;
	
	this.cfgm = new ConfigM(this.plugin, lang);
    }

    public void saveLang() {
	this.cfgm.saveConfig();
    }

    public FileConfiguration getLang() throws NullPointerException {
	return this.cfgm.getConfig();
    }
}
