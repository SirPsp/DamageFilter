package net.playsocket.damagefilter;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.Command;
import net.canarymod.commandsys.CommandListener;

public class DamageFilterCommands implements CommandListener {

	@Command(aliases = { "dmg" },
			description = "Toggle your damage filter on/off",
			permissions = { "damagefilter.dmg" },
			toolTip = "/dmg",
			min = 1)
	public void commandCMD(MessageReceiver caller, String[] split) {
		if (caller instanceof Player){
			Player p = (Player)caller;
			if (!DamageFilter.offList.contains(p)){
				p.notice("Damage Filter turned off");
				DamageFilter.offList.add(p);
			}else{
				p.notice("Damage Filter turned on");
				DamageFilter.offList.remove(p);
			}
		}
	}

}