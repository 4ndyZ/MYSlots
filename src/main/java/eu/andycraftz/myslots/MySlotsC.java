package eu.andycraftz.myslots;

import java.util.Arrays;

import eu.andycraftz.utils.AbstractCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * MYSlots - Simple Slots Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @category Bukkit Plugin
 * @version 2.3
 */
public class MySlotsC extends AbstractCommand {

    private final MySlots plugin;

    public MySlotsC(MySlots plugin) {
        super("myslots", "MySlots Command", Arrays.asList("ms", "slots", "slot"));
	this.plugin = plugin;
    }
        
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {      
            
        if (!(sender instanceof Player)) {
            if (args.length ==  1) {
                int slots;
                try {
                    slots = Integer.parseInt(args[0]);
                }
                catch (ArrayIndexOutOfBoundsException | NumberFormatException err) {
                    sender.sendMessage("§c" + this.plugin.lang.getLang().getString("Messages.Usage").replace("&", "§") + " §8| §7/myslots [" + this.plugin.lang.getLang().getString("Messages.Number").replace("&", "§") + "]");
                    return true;
                }
                this.plugin.cfg.getConfig().set("Slots", slots);
                this.plugin.cfg.saveConfig();
                sender.sendMessage("§6[#] §b" + this.plugin.lang.getLang().getString("Messages.SetUpSlots").replace("%slots%", String.valueOf(slots)).replace("&", "§"));
                return true;
                }
            else {
                sender.sendMessage("§6[#]==========< §eMYSlots §6>==========[#]");
                sender.sendMessage("§6[#]");
                sender.sendMessage("§6[#] §b" + this.plugin.lang.getLang().getString("Messages.Slots") + ": §e" + this.plugin.cfg.getConfig().getInt("Slots"));
                sender.sendMessage("§6[#]");
	        sender.sendMessage("§6[1] §b/myslots §e[" + this.plugin.lang.getLang().getString("Messages.Number").replace("&", "") + "]");
                sender.sendMessage("§6[#]");
                sender.sendMessage("§6[#]==========< §eMYSlots §6>==========[#]");	
                return true;
            }			            
        }
            
        Player p = (Player)sender; 
		
        if (p.hasPermission("myslots.use")) {
            if (args.length ==  1) {
                int slots;
                try {
                    slots = Integer.parseInt(args[0]);
                }
                catch (ArrayIndexOutOfBoundsException | NumberFormatException err) {
                    p.sendMessage("§c" + this.plugin.lang.getLang().getString("Messages.Usage").replace("&", "§") + " §8| §7/myslots [" + this.plugin.lang.getLang().getString("Messages.Number").replace("&", "§") + "]");
                    return true;
                }
                this.plugin.cfg.getConfig().set("Slots", slots);
                this.plugin.cfg.saveConfig();
                p.sendMessage("§6[#] §b" + this.plugin.lang.getLang().getString("Messages.SetUpSlots").replace("%slots%", String.valueOf(slots)).replace("&", "§"));
                return true;
            }
            else {
                p.sendMessage("§6[#]==========< §eMYSlots §6>==========[#]");
                p.sendMessage("§6[#]");
                p.sendMessage("§6[#] §b" + this.plugin.lang.getLang().getString("Messages.Slots") + ": §e" + this.plugin.cfg.getConfig().getInt("Slots"));
                p.sendMessage("§6[#]");
	        p.sendMessage("§6[1] §b/myslots §e[" + this.plugin.lang.getLang().getString("Messages.Number").replace("&", "") + "]");
                p.sendMessage("§6[#]");
                p.sendMessage("§6[#]==========< §eMYSlots §6>==========[#]");	
                return true;
            }                
        }
        else {
	    p.sendMessage("§6[#] §b" + this.plugin.lang.getLang().getString("Messages.NoPerm").replace("&", "§"));	
        }     
        return false;
    }
}
