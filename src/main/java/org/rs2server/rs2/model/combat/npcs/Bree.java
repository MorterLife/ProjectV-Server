package org.rs2server.rs2.model.combat.npcs;

import org.rs2server.rs2.model.Animation;
import org.rs2server.rs2.model.Hit;
import org.rs2server.rs2.model.Mob;
import org.rs2server.rs2.model.Prayers;
import org.rs2server.rs2.model.Projectile;
import org.rs2server.rs2.model.Skills;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.model.combat.CombatAction;
import org.rs2server.rs2.model.combat.CombatState.AttackType;
import org.rs2server.rs2.model.combat.impl.AbstractCombatAction;
import org.rs2server.rs2.tickable.Tickable;
import org.rs2server.rs2.util.Misc;

public class Bree extends AbstractCombatAction {
	
	/**
	 * The singleton instance.
	 */
	private static final Bree INSTANCE = new Bree();
	
	/**
	 * Gets the singleton instance.
	 * @return The singleton instance.
	 */
	public static CombatAction getAction() {
		return INSTANCE;
	}
	
	@Override
	public void hit(final Mob attacker, final Mob victim) {
		super.hit(attacker, victim);
		
		if(!attacker.isNPC()) {
			return; //this should be an NPC!
		}
		int clientSpeed;
		int gfxDelay;
		if(attacker.getLocation().isWithinDistance(attacker, victim, 1)) {
			clientSpeed = 70;
			gfxDelay = 80;
		} else if(attacker.getLocation().isWithinDistance(attacker, victim, 5)) {
			clientSpeed = 90;
			gfxDelay = 100;
		} else if(attacker.getLocation().isWithinDistance(attacker, victim, 8)) {
			clientSpeed = 110;
			gfxDelay = 120;
		} else {
			clientSpeed = 130;
			gfxDelay = 140;
		}
		int delay = (gfxDelay / 20) - 1;
		attacker.playAnimation(Animation.create(7026));
		int newDist = attacker.getLocation().distanceToEntity(attacker, victim);
		attacker.playProjectile(Projectile.create(attacker.getLocation(), victim, 1190, 30, 50, 40 + (newDist * 5), 63, 35, 10, 36));
		//attacker.playProjectile(Projectile.create(attacker.getCentreLocation(), victim, 1185, 15 + (delay * 10), 50, 50 + (newDist * 10), 37, 37, 0, 96));
//		attacker.playProjectile(Projectile.create(attacker.getLocation(), victim, 1185, 65, 350, clientSpeed, 25, 0, 25, 64));
//		attacker.playProjectile(Projectile.create(attacker.getCentreLocation(), victim.getCentreLocation(), 1185, 45, 155, clientSpeed, 38, 0, victim.getProjectileLockonIndex(), -15, 48));
		int randomHit = Misc.random(damage(16, attacker, victim, AttackType.RANGE, Skills.RANGE , Prayers.PROTECT_FROM_MISSILES, false, false));
		
		attacker.getCombatState().setAttackDelay(5);
		World.getWorld().submit(new Tickable(delay) {

			@Override
			public void execute() {
				this.stop();

				victim.inflictDamage(new Hit(randomHit), attacker);
				smite(attacker, victim, randomHit);
				recoil(attacker, victim, randomHit);
				vengeance(attacker, victim, randomHit, 1);
			}
		});
	}

	@Override
	public int distance(Mob attacker) {
		return 4;
	}

}
