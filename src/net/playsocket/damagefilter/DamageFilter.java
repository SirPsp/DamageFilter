package net.playsocket.damagefilter;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.plugin.Plugin;

public class DamageFilter extends Plugin {

	static ArrayList<Player> offList = new ArrayList<Player>();
	
	@Override
	public void disable() {
		getLogman().logInfo(getName() + " v" + getVersion() + " disabled");
	}

	@Override
	public boolean enable() {
        Canary.hooks().registerListener(new DamageFilterListener(), this);
        try {
			Canary.commands().registerCommands(new DamageFilterCommands(), this, true);
		} catch (CommandDependencyException e) {
			e.printStackTrace();
		}
        getLogman().logInfo(getName() + " v"+ getVersion() + " by " + getAuthor() + " enabled");
        return true;
	}

}
