package net.playsocket.damagefilter;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.entity.DamageHook;
import net.canarymod.plugin.PluginListener;

public class DamageFilterListener implements PluginListener {

	@HookHandler
	public void onDamage(DamageHook hook) {
		Entity d = hook.getDefender();
		if (d != null && d.isLiving()){
			if (d.isPlayer()){
				Player p = (Player)d;
				if (!DamageFilter.offList.contains(p)){
					Entity a = hook.getAttacker();
					String W = p.getWorld().getName().toLowerCase();
					String t = hook.getDamageSource().getDamagetype().name().toLowerCase();
					if (a != null && a.isLiving()){
						EntityLiving al = (EntityLiving)a;

						if (al.isMob()){
							String mn = al.getName().toLowerCase();
							if (p.hasPermission("damagefilter.mobs."+mn+"."+W)){
								hook.setCanceled();
							}
						}else{
							if (al.isPlayer()){
								if (p.hasPermission("damagefilter.pvp."+W)){
									hook.setCanceled();
								}
							}
						}
					}else{
						if (p.hasPermission("damagefilter."+t+"."+W)){
							hook.setCanceled();
						}
					}
				}
			}
		}
	}
}
