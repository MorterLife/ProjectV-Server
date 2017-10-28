package org.rs2server.rs2.model.skills;

import org.rs2server.rs2.Constants;
import org.rs2server.rs2.action.Action;
import org.rs2server.rs2.model.Animation;
import org.rs2server.rs2.model.Entity;
import org.rs2server.rs2.model.GameObject;
import org.rs2server.rs2.model.GroundItem;
import org.rs2server.rs2.model.Hit;
import org.rs2server.rs2.model.Item;
import org.rs2server.rs2.model.Mob;
import org.rs2server.rs2.model.Mob.InteractionMode;
import org.rs2server.rs2.model.Skills;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.model.container.Equipment;
import org.rs2server.rs2.model.npc.NPC;
import org.rs2server.rs2.model.player.Player;
import org.rs2server.rs2.net.ActionSender;
import org.rs2server.rs2.tickable.Tickable;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ThievingAction extends Action {

	public static final HashMap<Integer, ThievingStalls> THIEVING_STALLS = new HashMap<Integer, ThievingStalls>();
	public static final HashMap<Integer, PickpocketableNPC> NPCS = new HashMap<Integer, PickpocketableNPC>();

	public static final Random RANDOM = new Random();

	private static final Animation STEAL_ANIMATION = Animation.create(833);

	private static final Animation STUN_ANIMATION = Animation.create(422);

	private static final Animation PICKPOCKETING_ANIMATION = Animation.create(881);

	private GameObject object;
	private NPC npc;
	private ThievingStalls stall;
	private PickpocketableNPC npcData;
	private boolean succesful = true;

	public ThievingAction(Mob mob, Entity e) {
		super(mob, 0);
		if (e instanceof GameObject) {
			this.object = (GameObject) e;
			this.stall = THIEVING_STALLS.get(object.getId());
		} else if (e instanceof NPC) {
			this.npc = (NPC) e;
			this.npcData = NPCS.get(npc.getDefinition().getId());
		}
	}

	public enum PickpocketableNPC {

		MAN(new short[] { 0 }, new byte[] { 1, 11, 21, 31 }, new byte[] { 1, 1, 11, 21 }, 8.0, 8, 1,
				new Item(995, 500)),

		FARMER(new short[] { 3086, 3087, 3088 }, new byte[] { 10, 20, 30, 40 }, new byte[] { 1, 10, 20, 30 }, 14.5, 8,
				3, new Item(995, 9), new Item(5318, 1)),

		FEMALE_HAM(new short[] { 2541 }, new byte[] { 15, 25, 35, 45 }, new byte[] { 1, 15, 25, 35 }, 18.5, 7, 10,
				new Item(995, 12), new Item(590), new Item(1621), new Item(1623), new Item(1625), new Item(1269),
				new Item(321), new Item(2138), new Item(4298), new Item(4300), new Item(4302), new Item(4304),
				new Item(4306), new Item(4308), new Item(4310), new Item(1267), new Item(1353), new Item(199),
				new Item(201), new Item(203), new Item(205), new Item(686), new Item(697), new Item(453), new Item(688),
				new Item(314, 3)),
		// this.npcIds = npcIds;
		// this.thievingLevels = thievingLevel;
		// this.agilityLevels = agilityLevel;
		// this.experience = experience;
		// this.stunTime = (byte) stunTime;
		// this.stunDamage = (byte) stunDamage;
		// this.loot = loot;

		MALE_HAM(new short[] { 2540 }, new byte[] { 20, 30, 40, 50 }, new byte[] { 1, 20, 30, 40 }, 22.5, 7, 20,
				new Item(995, 12), new Item(590), new Item(1621), new Item(1623), new Item(1625), new Item(1269),
				new Item(321), new Item(2138), new Item(4298), new Item(4300), new Item(4302), new Item(4304),
				new Item(4306), new Item(4308), new Item(4310), new Item(1267), new Item(1353), new Item(199),
				new Item(201), new Item(203), new Item(205), new Item(686), new Item(697), new Item(453), new Item(688),
				new Item(314, 3)),

		WARRIOR(new short[] { 3100 }, new byte[] { 25, 35, 45, 55 }, new byte[] { 1, 25, 35, 45 }, 26, 8, 1,
				new Item(995, 18)),

		ROGUE(new short[] { 2884 }, new byte[] { 32, 42, 52, 62 }, new byte[] { 1, 32, 42, 52 }, 35.5, 8, 20,
				new Item(995, 25), new Item(995, 40), new Item(1523), new Item(555, 8), new Item(245), new Item(1523),
				new Item(1944)),

		CAVE_GOBLIN(
				new short[] { 2285, 2284, 2283, 2282, 2281, 2280, 2279, 2278, 2277, 2276, 2275, 2274, 2273, 2272, 2271,
						2270, 2269, 2268 },
				new byte[] { 36, 46, 56, 66 }, new byte[] { 1, 36, 46, 56 }, 35, 8, 10, new Item(995, 30),
				new Item(440), new Item(590), new Item(1939), new Item(5437), new Item(595), new Item(10960),
				new Item(4544), new Item(7905)),

		MASTER_FARMER(new short[] { 3257 }, new byte[] { 38, 48, 58, 68 }, new byte[] { 1, 38, 48, 58 }, 45, 8, 2,
				new Item(5318, 1), new Item(7550, 1), new Item(7562, 1), new Item(5323, 1), new Item(5324, 1),
				new Item(5291, 1), new Item(5292, 1), new Item(5293, 1), new Item(5294, 1), new Item(5295, 1),
				new Item(5296, 1), new Item(5297, 1), new Item(5298, 1), new Item(5299, 1)),

		GUARD(new short[] { 3010, 3094, 1547, 1548, 1549, 1550 }, new byte[] { 40, 50, 60, 70 },
				new byte[] { 1, 40, 50, 60 }, 65.5, 8, 2, new Item(995, 30)),

		ARDOUGNE_KNIGHT(new short[] { 3108, 3111 }, new byte[] { 55, 65, 75, 85 }, new byte[] { 1, 55, 65, 75 }, 95.3,
				8, 3, new Item(995, 50)),

		MENAPHITE_THUG(new short[] { 3550 }, new byte[] { 65, 75, 85, 95 }, new byte[] { 1, 65, 75, 85 }, 120.5, 8, 5,
				new Item(995, 200)),

		PALADIN(new short[] { 3104, 3105 }, new byte[] { 70, 80, 90, 100 }, new byte[] { 1, 70, 80, 90 }, 140.75, 8, 3,
				new Item(995, 80), new Item(562, 2)),

		GNOME(new short[] { 6096, 6095, 6094 }, new byte[] { 75, 85, 95, 105 }, new byte[] { 1, 75, 85, 95 }, 185.5, 8,
				1, new Item(995, 300), new Item(557), new Item(444), new Item(569), new Item(2150), new Item(2162)),

		HERO(new short[] { 3106 }, new byte[] { 80, 90, 100, 110 }, new byte[] { 1, 80, 90, 100 }, 210, 10, 4,
				new Item(995, 200), new Item(995, 300), new Item(560, 2), new Item(565), new Item(569), new Item(1601),
				new Item(444), new Item(1993)),

		// TODO: Find what elfs are pickpocketable. ELF(new short[] { }, new
		// byte[] { 85, 95, 105, 115 }, new byte[] { 1, 85, 95, 105 }, 353, 10,
		// 50),

		ELF(new short[] { 5299, 5300, 5301, 5298, 5297 }, new byte[] { 90, 100, 110, 120 },
				new byte[] { 1, 90, 100, 110 }, 250.5, 8, 1, new Item(995, 100), new Item(995, 400), new Item(2350),
				new Item(2352), new Item(2354), new Item(2360), new Item(2362), new Item(2364), new Item(437),
				new Item(439), new Item(441), new Item(448), new Item(450), new Item(452), new Item(454));

		public static PickpocketableNPC get(int id) {
			return NPCS.get((short) id);
		}

		private final short[] npcIds;

		private final byte[] thievingLevels;

		private final byte[] agilityLevels;

		private final double experience;

		private final byte stunTime;

		private final byte stunDamage;

		private final Item[] loot;

		// private int petRate;

		private PickpocketableNPC(short[] npcIds, byte[] thievingLevel, byte[] agilityLevel, double experience,
				int stunTime, int stunDamage, Item... loot) {
			this.npcIds = npcIds;
			this.thievingLevels = thievingLevel;
			this.agilityLevels = agilityLevel;
			this.experience = experience;
			this.stunTime = (byte) stunTime;
			this.stunDamage = (byte) stunDamage;
			this.loot = loot;
			// this.petRate= petRate;
		}

		public short[] getNpcIds() {
			return npcIds;
		}

		public byte[] getThievingLevels() {
			return thievingLevels;
		}

		public byte[] getAgilityLevels() {
			return agilityLevels;
		}

		public double getExperience() {
			return experience;
		}

		public byte getStunTime() {
			return stunTime;
		}

		public byte getStunDamage() {
			return stunDamage;
		}

		public Item[] getLoot() {
			return loot;
		}
	}

	public enum ThievingStalls {

		VEGETABLE_STALL(new int[] { 4706, 4708 }, new int[] { 4276, 4276  }, 1, 13.0, 3, 10000, new Item(1957), new Item(1965), new Item(1942),
				new Item(995, 1000), new Item(1982), new Item(1550), new Item(7478), new Item(1933), new Item(1944), new Item(2116), new Item(1927), new Item(1887), new Item(1937), new Item(1982),
				new Item(1942), new Item(6697), new Item(5320), new Item(5988), new Item(1985)), 
		
		FUR_STALL(new int[] { 4278 }, new int[] { 4276 }, 50, 25.0, 9, 5000, new Item(958), new Item(6814), new Item(7478)),
		
		FISH_STALL(new int[] { 4705, 4707, 4277 }, new int[] { 4276, 4276, 4276 }, 35, 38.0, 25, 5000, new Item(331), new Item(359), new Item(377), new Item( 7478)), 
		/**
		 * Home stalls
		 */
		DAGGER_STALL(new int[] { 20331 }, new int[] { 4276 }, 75, 80.0, 18, 10000, new Item(995, 2500), new Item(1205), new Item(1203), new Item(1207), new Item(1217), new Item(1209), new Item(1211)), 
		
		CRAFTING_STALL(new int[] { 20332 }, new int[] { 4276 }, 20, 25.0, 6, 6000, new Item(1635), new Item(1724), new Item(21120), new Item(1737), new Item(5356), new Item(1779), new Item(13446)),
		
		BAKERY_STALL1(new int[] { 20345 }, new int[] { 4276 }, 1, 8.0, 6, 6000, new Item(1933), new Item(1944), new Item(2116), new Item(1927), new Item(1887), new Item(1937), new Item(1982),
		new Item(1942), new Item(6697), new Item(5320), new Item(5988), new Item(1985)),
		
		//SILK_STALL( new int[] { 20344, 11729 }, new int[] { 634, 4276 }, 20, 24.0, 13, 6000, new Item(950), new Item(7478)),
		
		SILK_STALL( new int[] { 20344, 11729 }, new int[] { 4276, 4276 }, 20, 24.0, 13, 6000, new Item(950), new Item(7478)), 
		
		BAKER_STALL(new int[] { 11730 }, new int[] { 4276 }, 5, 16.0, 4, 5000, new Item(1891), new Item(1901), new Item(2309), new Item(7478)), 
			
		MONKEY_FOOD_STALL(new int[] {}, new int[] { 4276 }, 5, 16.0, 12, 8000, new Item(1963), new Item(7478)), 
		
		MONKEY_GENERAL_STORE(new int[] {}, new int[] { 4276 }, 5, 16.0, 12, 7000, new Item(1931), new Item(2347), new Item(590), new Item(7478)), 
		
		TEA_STALL(new int[] {}, new int[] { 4276 }, 5, 16.0, 12, 7000, new Item(712), new Item(7478)), 
		
		SCIMITAR_STALL( new int[] { 4878 }, new int[] { 4276 }, 20, 24.0, 13, 6000, new Item(950), new Item(7478)), 
		
		WINE_STALL( new int[] { 14011 }, new int[] { 4276 }, 22, 27.0, 27, 6000, new Item(1937), new Item(1993), new Item(1987), new Item(1935), new Item(7919), new Item(7478)), 
		
		SEED_STALL(new int[] { 7053 }, new int[] { 4276 }, 27, 10.0, 18, 5000, new Item(5096), new Item(5097), new Item(5098), new Item(5099), new Item(5100), new Item(5101), new Item(5102), new Item(5103),
		new Item(5104), new Item(5105), new Item(5106), new Item(5291), new Item(5292), new Item(5293), new Item(5294), new Item(5295), new Item(5296), new Item(5297), new Item(5298), new Item(5299),
		new Item(5300), new Item(5301), new Item(5302), new Item(5304), new Item(5305), new Item(5306), new Item(5307), new Item(5308), new Item(5309), new Item(5310), new Item(5311), new Item(5312),
		new Item(5318), new Item(5319), new Item(5320), new Item(5321), new Item(5322), new Item(5323), new Item(5324), new Item(7478)), 
		

		CROSSBOW_STALL(new int[] {}, new int[] { 4276 }, 49, 52.0, 14, 5000, new Item(877, 3), new Item(9420), new Item(9440), new Item(7478)), 
		
		SILVER_STALL(new int[] { 11734 }, new int[] { 4276 }, 50, 54.0, 15, 5000, new Item(442), new Item(7478)), 
		
		SPICE_STALL(new int[] { 11733 }, new int[] { 4276 }, 65, 66.0, 65, 5000, new Item(2007), new Item(7478)), 
		
		MAGIC_STALL(new int[] { 4877 }, new int[] { 4276}, 65,100.0, 50, 5000, new Item(554), new Item(555), new Item(556), new Item(557), new Item(563), new Item(7478)), 
		
		GEM_STALL(new int[] { 11731, 20346 }, new int[] { 4276 }, 75, 120.0, 100, 5000, new Item(1617), new Item(1619), new Item(1621) ,new Item(1623), new Item(7478));

		private final int[] objectIds;

		private final int[] replaceIds;

		private final int thievingLevel;

		private final double experience;

		private final Item[] loot;

		private final int restoreDelay;

		private int petRate;

		private ThievingStalls(int[] objectIds, int[] replaceIds, int thievingLevel, double experience,
				int restoreDelay, int petRate, Item... items) {
			this.objectIds = objectIds;
			this.replaceIds = replaceIds;
			this.thievingLevel = thievingLevel;
			this.experience = experience;
			this.restoreDelay = restoreDelay;
			this.petRate = petRate;
			this.loot = items;
		}

		public int[] getObjectIds() {
			return objectIds;
		}

		public int[] getReplaceIds() {
			return replaceIds;
		}

		public int getThievingLevel() {
			return thievingLevel;
		}

		public double getExperience() {
			return experience;
		}

		public Item[] getLoot() {
			return loot;
		}

		public int getRestoreDelay() {
			return restoreDelay / 2;
		}

		public int getpetRate() {
			return petRate;
		}

	}

	static {
		for (ThievingStalls data : ThievingStalls.values()) {
			for (int id : data.objectIds) {
				THIEVING_STALLS.put(id, data);
			}
		}
		for (PickpocketableNPC data : PickpocketableNPC.values()) {
			for (short id : data.npcIds) {
				NPCS.put((int) id, data);
			}
		}
	}

	@Override
	public CancelPolicy getCancelPolicy() {
		return CancelPolicy.ALWAYS;
	}

	@Override
	public StackPolicy getStackPolicy() {
		return StackPolicy.NEVER;
	}

	@Override
	public AnimationPolicy getAnimationPolicy() {
		return AnimationPolicy.RESET_ALL;
	}

	int ticks = 1;

	private String message;

	@Override
	public void execute() {
		final Player player = (Player) getMob();
		if (ticks == 1) {
			if (object != null) {
				if (player.getSkills().getLevel(Skills.THIEVING) < stall.getThievingLevel()) {
					player.getActionSender().sendMessage(
							"You need a Thieving level of " + stall.getThievingLevel() + " to steal from this stall.");
					this.stop();
					return;
				}
				if (player.getAttribute("stunned") == Boolean.TRUE) {
					this.stop();
					return;
				}
				if (player.getInventory().freeSlots() < 1) {
					player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE, -1, null,
							"Not enough space in your inventory.");
					player.getActionSender().sendMessage("Not enough space in your inventory.");
					this.stop();
					return;
				}
				player.faceObject(object);
				player.getActionSender().sendMessage("You attempt to steal something from the "
						+ stall.name().toLowerCase().replaceAll("_", " ") + ".");
				int increasedChance = getIncreasedChance(player);
				int thievingLevel = player.getSkills().getLevel(Skills.THIEVING);
				int level = RANDOM.nextInt(thievingLevel + increasedChance) + 1;
				double ratio = level / (RANDOM.nextInt(stall.getThievingLevel() + 5) + 1);
				if (Math.round(ratio * thievingLevel) < stall.getThievingLevel()) {
					succesful = false;
				}
				player.playAnimation(STEAL_ANIMATION);
				player.setAttribute("cantWalk", true);
				player.setAttribute("isStealing", true);
			} else {
				if (player.getSkills().getLevel(Skills.THIEVING) < npcData.getThievingLevels()[0]) {
					player.getActionSender().sendMessage("You need a Thieving level of "
							+ npcData.getThievingLevels()[0] + " to steal this npc's pocket.");
					this.stop();
					return;
				}
				if (player.getAttribute("stunned") == Boolean.TRUE) {
					this.stop();
					return;
				}
				if (player.getInventory().freeSlots() < 1) {
					player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE, -1, null,
							"Not enough space in your inventory.");
					player.getActionSender().sendMessage("Not enough space in your inventory.");
					this.stop();
					return;
				}
				player.setTempInteractingEntity(InteractionMode.TALK, npc);
				int thievingLevel = player.getSkills().getLevel(Skills.THIEVING);
				player.getActionSender().sendMessage(
						"You attempt to pick the " + npc.getDefinition().getName().toLowerCase() + "'s pocket...");
				message = "You succesfully pick the " + npc.getDefinition().getName().toLowerCase() + "'s pocket.";
				int increasedChance = getIncreasedChance(player);
				int level = RANDOM.nextInt(thievingLevel + increasedChance) + 1;
				double ratio = (double) level / (double) (RANDOM.nextInt(npcData.getThievingLevels()[0] + 5) + 1);
				if (Math.round(ratio * thievingLevel) < npcData.getThievingLevels()[0] && RANDOM.nextBoolean()) {
					succesful = false;
					message = "You fail to pick the " + npc.getDefinition().getName().toLowerCase() + "'s pocket.";
				}
				player.playAnimation(PICKPOCKETING_ANIMATION);
				player.setAttribute("cantWalk", true);
				player.setAttribute("isStealing", true);
			}
		} else {
			player.removeAttribute("cantWalk");
			player.removeAttribute("isStealing");
			if (object != null) {
				if (!succesful) {
					List<NPC> guards = player.getLocalNPCs();
					for (NPC npc : guards) {
						if (!npc.getDefinition().getName().toLowerCase().contains("guard")
								|| npc.getCombatState().getAttackEvent() != null) {
							continue;
						}
						npc.forceChat("Hey! Get your hands off there!");
						npc.getCombatState().startAttacking(player, false);
						this.stop();
					}
				}
				player.getSkills().addExperience(Skills.THIEVING, stall.getExperience());
				Item item = stall.getLoot()[RANDOM.nextInt(stall.getLoot().length)];
				player.getActionSender()
						.sendMessage("You steal some " + item.getDefinition2().getName().toLowerCase() + ".");
				player.getInventory().add(new Item(item.getId(), item.getCount()));
				int id = -1;
				for (int i = 0; i < stall.getObjectIds().length; i++) {
					if (stall.getObjectIds()[i] == object.getId()) {
						id = stall.getReplaceIds()[i];
						break;
					}
				}
				if (id < 0) {
					this.stop();
				}
				World.getWorld().replaceObject(object,
						new GameObject(object.getLocation(), id, object.getType(), object.getDirection(), false),
						stall.getRestoreDelay());
			} else {
				player.getActionSender().sendMessage(message);
				player.removeAttribute("cantWalk");
				player.removeAttribute("isStealing");
				if (!succesful) {
					npc.forceChat("What do you think you're doing?");
					npc.setTempInteractingEntity(InteractionMode.TALK, player);
					npc.playAnimation(STUN_ANIMATION);
					player.playAnimation(player.getDefendAnimation());
					World.getWorld().submit(new Tickable(1) {
						@Override
						public void execute() {
							player.inflictDamage(new Hit(npcData.getStunDamage()), player);
							player.stun(npcData.getStunTime(), "You have been stunned!", true);
							this.stop();
							return;
						}
					});
					if (npcData.equals(PickpocketableNPC.MASTER_FARMER) || npcData.equals(PickpocketableNPC.FARMER)) {
						npc.forceChat("Cor blimey mate, what are ye doing in me pockets?");
						this.stop();
						return;
					}
					this.stop();
					return;
				}
				((Skills) player.getSkills()).addExperience(Skills.THIEVING, npcData.getExperience());
				Item item = npcData.getLoot()[RANDOM.nextInt(npcData.getLoot().length)];
				if (player.getInventory().hasRoomFor(item))
					player.getInventory().add(item);
				else
					World.getWorld().createGroundItem(new GroundItem(player.getName(), item, player.getLocation()),
							player);
			}
		}
		ticks--;
		if (ticks < 0)
			this.stop();
	}

	private int getIncreasedChance(Player player) {
		int chance = 0;
		if (npc != null) {
			if (player.getEquipment().getSlot(Equipment.SLOT_GLOVES) == 10075) {
				chance += 12;
			}
			if (npc.getDefinition().getName().contains("H.A.M")) {
				for (Item item : player.getEquipment().toArray()) {
					if (item != null && item.getDefinition().getName().contains("H.A.M")) {
						chance += 3;
					}
				}
			}
		}
		return chance;
	}

}
