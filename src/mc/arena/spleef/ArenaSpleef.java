package mc.arena.spleef;

import mc.alk.arena.BattleArena;
import mc.alk.arena.util.Log;
import mc.arena.spleef.util.WorldGuardUtil;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ArenaSpleef extends JavaPlugin {
	static ArenaSpleef plugin;

	@Override
	public void onEnable(){	
		plugin = this;
		BattleArena.registerMatchType(this, "Spleef", "spleef", SpleefArena.class, new SpleefExecutor());

		BattleArena.registerEventType(this, "ESpleef", "espleef", SpleefArena.class, new ESpleefExecutor());

		WorldGuardUtil.loadWorldGuardPlugin();

		FileConfiguration config = this.getConfig();
		SpleefArena.superpick = config.getBoolean("superpick", false);
		SpleefArena.superpick_item = config.getInt("superpick_item", 284);
		saveDefaultConfig();

		Log.info("[" + getName()+ "] v" + getDescription().getVersion()+ " enabled!");
	}

	@Override
	public void onDisable(){
		Log.info("[" + getName()+ "] v" + getDescription().getVersion()+ " stopping!");
	}

	public static ArenaSpleef getSelf() {
		return plugin;
	}

}