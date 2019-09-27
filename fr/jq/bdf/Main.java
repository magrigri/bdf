package fr.jq.bdf;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("bdf").setExecutor(new CommandBdf());

    }

}
