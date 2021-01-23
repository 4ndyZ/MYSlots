package eu.andycraftz.utils;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

/**
 * AbstractCommand
 *
 * @author AndyCraftz
 * @version 1.0
 */

public abstract class AbstractCommand implements CommandExecutor, TabExecutor {

    protected final String Command; // Command
    protected final String Beschreibung; // Beschreibung des Commands
    protected final List<String> Alias; // Alias des Commands
    protected final LinkedList<String> Verwendung; // Verwendung Messages des Commands
    protected final String Permission; // Permission des Commands
    protected final String NoPermisssion; // NoPermission Message des Commands

    protected static CommandMap CommandMap;

    /**
     * <b>AbstractCommand</b>
     * @param Command 
     * @param Beschreibung des Commands
     */
    public AbstractCommand(String Command, String Beschreibung) {
        this(Command, Beschreibung, null, null, null, null);
    }

    /**
     * <b>AbstractCommand</b>
     * @param Command
     * @param Beschreibung des Commands
     * @param Alias des Commands
     */
    public AbstractCommand(String Command, String Beschreibung, List<String> Alias) {
        this(Command, Beschreibung, Alias, null, null, null);
    }

    /**
     * <b>AbstractCommand</b>
     * @param Command
     * @param Beschreibung des Commands
     * @param Verwendung Messages des Commands
     */
    public AbstractCommand(String Command, String Beschreibung, LinkedList<String> Verwendung) {
        this(Command, Beschreibung, null, Verwendung, null, null);
    }  
    
    /**
     * <b>AbstractCommand</b>
     * @param Command
     * @param Beschreibung des Commands
     * @param Alias des Commands
     * @param Verwendung Message des Commands
     */
    public AbstractCommand(String Command, String Beschreibung, List<String> Alias, LinkedList<String> Verwendung) {
        this(Command, Beschreibung, Alias, Verwendung, null, null);
    }
    
    /**
     * <b>AbstractCommand</b>
     * @param Command
     * @param Beschreibung des Commands
     * @param Permission des Commands
     * @param NoPermission des Commands
     */
    public AbstractCommand(String Command, String Beschreibung, String Permission, String NoPermission) {
        this(Command, Beschreibung, null, null, Permission, NoPermission);
    }      
    
    /**
     * <b>AbstractCommand</b>
     * @param Command
     * @param Beschreibung des Commands
     * @param Verwendung Messages des Commands
     * @param Permission des Commands
     * @param NoPermission Message des Commands
     */
    public AbstractCommand(String Command, String Beschreibung, LinkedList<String> Verwendung, String Permission, String NoPermission) {
        this(Command, Beschreibung, null, Verwendung, Permission, NoPermission);
    }      
    
    /**
     * <b>AbstractCommand</b>
     * @param Command
     * @param Beschreibung des Commands
     * @param Alias des Commands
     * @param Permission des Commands-
     * @param NoPermission Message des Commands
     */
    public AbstractCommand(String Command, String Beschreibung, List<String> Alias, String Permission, String NoPermission) {
        this(Command, Beschreibung, Alias, null, Permission, NoPermission);
    }    

    /**
     * <b>AbstractCommand</b>
     * @param Command
     * @param Beschreibung des Commands
     * @param Alias des Commands
     * @param Verwendung Messages des Commands
     * @param Permission des Commands
     * @param NoPermission Message des Commands
     */
    public AbstractCommand(String Command, String Beschreibung, List<String> Alias, LinkedList<String> Verwendung, String Permission, String NoPermission) {
        this.Command = Command.toLowerCase();
        this.Beschreibung = Beschreibung;
        this.Alias = Alias;
        this.Verwendung = Verwendung;
        this.Permission = Permission;
        this.NoPermisssion = NoPermission;
    }
    
    //--------------------------------------------------------------------------

    public void register() {
        ReflectCommand Command_ = new ReflectCommand(this.Command);
        if (this.Alias != null) {
            Command_.setAliases(this.Alias);
        }
        if (this.Beschreibung != null) {
            Command_.setDescription(this.Beschreibung);
        }
        if (this.Verwendung != null) {
            StringBuilder sb = new StringBuilder();
            for (String s : this.Verwendung) {
                sb.append(s);
            }
            Command_.setUsage(sb.toString());
        }
        if (this.Permission != null) {
            Command_.setPermissionMessage(this.Permission);
        }
        getCommandMap().register("", Command_);
        Command_.setExecutor(this);
    }

    final CommandMap getCommandMap() {
        if (CommandMap == null) {
            try {
                final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                CommandMap = (CommandMap) f.get(Bukkit.getServer());
                return getCommandMap();
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException err) {
            }
        } else if (CommandMap != null) {
            return CommandMap;
        }
        return getCommandMap();
    }

    private final class ReflectCommand extends Command {

        private AbstractCommand Executor = null;

        protected ReflectCommand(String command) {
            super(command);
        }

        public void setExecutor(AbstractCommand Executor) {
            this.Executor = Executor;
        }

        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            if (Executor != null) {
                return Executor.onCommand(sender, this, commandLabel, args);
            }
            return false;
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String alais, String[] args) {
            if (Executor != null) {
                return Executor.onTabComplete(sender, this, alais, args);
            }
            return null;
        }
    }

    @Override
    public abstract boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return null;
    }
}
