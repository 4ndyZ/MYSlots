package eu.andycraftz.myslots;

import com.google.common.io.ByteStreams;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * MYSlots - Simple Slots Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @category Bukkit Plugin
 * @version 2.3
 */
public class ConfigM {

    private final MySlots plugin;

    private final File file;
    private FileConfiguration cfg;

    public ConfigM(MySlots plugin, String name) {
	this.plugin = plugin;

	this.file = new File(this.plugin.getDataFolder(), name + ".yml");

	if (!this.plugin.getDataFolder().exists()) {
	    this.plugin.getDataFolder().mkdir();
	}
	if (!file.exists()) {
	    try {
		file.createNewFile();
		InputStream is = plugin.getResource(name + ".yml");
		OutputStream os = new FileOutputStream(file);
		ByteStreams.copy(is, os);
	    } catch (IOException err) {
		this.plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
	    }
	}
	this.cfg = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
	return cfg;
    }

    public void saveConfig() {
	try {
	    cfg.save(file);
	} catch (IOException err) {
	    plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
	}
    }

    public void reloadConfig() {
	this.cfg = YamlConfiguration.loadConfiguration(file);
    }
}
