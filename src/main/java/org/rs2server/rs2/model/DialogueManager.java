package org.rs2server.rs2.model;

import org.rs2server.Server;
import org.rs2server.cache.format.CacheItemDefinition;
import org.rs2server.cache.format.CacheNPCDefinition;
import org.rs2server.rs2.Constants;
import org.rs2server.rs2.content.StarterMap;
import org.rs2server.rs2.content.dialogue.Dialogue;
import org.rs2server.rs2.domain.model.claim.ClaimType;
import org.rs2server.rs2.domain.service.api.*;
import org.rs2server.rs2.domain.service.api.content.GemBagService;
import org.rs2server.rs2.domain.service.api.content.LootingBagService;
import org.rs2server.rs2.domain.service.api.content.PotionDecanterService;
import org.rs2server.rs2.domain.service.api.content.bounty.BountyHunterService;
import org.rs2server.rs2.domain.service.impl.BankPinServiceImpl;
import org.rs2server.rs2.domain.service.impl.PermissionServiceImpl;
import org.rs2server.rs2.model.Animation.FacialAnimation;
import org.rs2server.rs2.model.container.Bank;
import org.rs2server.rs2.model.container.LootingBag;
import org.rs2server.rs2.model.minigame.warriorsguild.WarriorsGuild;
import org.rs2server.rs2.model.player.Player;
import org.rs2server.rs2.model.skills.slayer.SlayerTask;
import org.rs2server.rs2.model.skills.slayer.SlayerTask.Master;
import org.rs2server.rs2.net.ActionSender;
import org.rs2server.rs2.net.ActionSender.DialogueType;
import org.rs2server.rs2.tickable.Tickable;
import org.rs2server.util.DonationManager;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.*;


public class DialogueManager {

	public static void openDialogue(final Player player, int dialogueId) {
		final SlayerService slayerService = Server.getInjector().getInstance(SlayerService.class);
		final PermissionService permissionService = Server.getInjector().getInstance(PermissionService.class);
		final GemBagService gemBagService = Server.getInjector().getInstance(GemBagService.class);
		boolean starter = player.getAttribute("starter");
		if (dialogueId == -1) {
			return; 
		}
		for (int i = 0; i < 5; i++) {
			player.getInterfaceState().setNextDialogueId(i, -1);
		}
		player.getInterfaceState().setOpenDialogueId(dialogueId);
		//NPC npc = (NPC) player.getInteractingEntity();
		switch (dialogueId) {
		case 19000:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Normal Player|<img=2>Ironman|<img=3>Ultimate Ironman|<img=10>Hardcore Ironman");
			player.getInterfaceState().setNextDialogueId(0, 19001);
			player.getInterfaceState().setNextDialogueId(1, 19002);
			player.getInterfaceState().setNextDialogueId(2, 19003);
			player.getInterfaceState().setNextDialogueId(3, 19004);
			break;
		case 19001:
			ClaimService claimService1 = Server.getInjector().getInstance(ClaimService.class);
			PlayerService playerService1 = Server.getInjector().getInstance(PlayerService.class);
			playerService1.giveItem(player, new Item(995, 50000), true);
			playerService1.giveItem(player, new Item(1155, 1), true);
			playerService1.giveItem(player, new Item(1117, 1), true);
			playerService1.giveItem(player, new Item(1075, 1), true);
			playerService1.giveItem(player, new Item(1189, 1), true);
			playerService1.giveItem(player, new Item(4119, 1), true);
			player.getActionSender().removeChatboxInterface();
			player.getActionSender().sendInterface(269, false);
			break;
		case 19002:
			ClaimService claimService2 = Server.getInjector().getInstance(ClaimService.class);
			PlayerService playerService2 = Server.getInjector().getInstance(PlayerService.class);;
			PermissionService perms = Server.getInjector().getInstance(PermissionService.class);
			player.setIsIronMan(true);
			player.setUltimateIronMan(false);
			player.setHardcoreIronMan(false);
			perms.give(player, PermissionService.PlayerPermissions.IRON_MAN);
			player.getActionSender().sendInterface(269, false);
			playerService2.giveItem(player, new Item(12810, 1), true);
			playerService2.giveItem(player, new Item(12811, 1), true);
			playerService2.giveItem(player, new Item(12812, 1), true);
			playerService2.giveItem(player, new Item(1291, 1), true);
			playerService2.giveItem(player, new Item(1181, 1), true);
			playerService2.giveItem(player, new Item(4119, 1), true);
			playerService2.giveItem(player, new Item(995, 30000), true);
			player.getActionSender().removeChatboxInterface();
			player.getActionSender().sendInterface(269, false);
			break;
		case 19003:
			ClaimService claimService3 = Server.getInjector().getInstance(ClaimService.class);
			PlayerService playerService3 = Server.getInjector().getInstance(PlayerService.class);;
			PermissionService perms1 = Server.getInjector().getInstance(PermissionService.class);
			player.setIsIronMan(false);
			player.setUltimateIronMan(true);
			player.setHardcoreIronMan(false);
			perms1.give(player, PermissionService.PlayerPermissions.ULTIMATE_IRON_MAN);
			playerService3.giveItem(player, new Item(12813, 1), true);
			playerService3.giveItem(player, new Item(12814, 1), true);
			playerService3.giveItem(player, new Item(12815, 1), true);
			playerService3.giveItem(player, new Item(1291, 1), true);
			playerService3.giveItem(player, new Item(1181, 1), true);
			playerService3.giveItem(player, new Item(841, 1), true);
			playerService3.giveItem(player, new Item(882, 500), true);
			playerService3.giveItem(player, new Item(555, 500), true);
			playerService3.giveItem(player, new Item(556, 500), true);
			playerService3.giveItem(player, new Item(554, 500), true);
			playerService3.giveItem(player, new Item(558, 500), true);
			playerService3.giveItem(player, new Item(995, 25000), true);
			player.getActionSender().removeChatboxInterface();
			player.getActionSender().sendInterface(269, false);
			break;
		case 19004:
			ClaimService claimService4 = Server.getInjector().getInstance(ClaimService.class);
			PlayerService playerService4 = Server.getInjector().getInstance(PlayerService.class);;
			PermissionService perms2 = Server.getInjector().getInstance(PermissionService.class);
			player.setIsIronMan(false);
			player.setUltimateIronMan(false);
			player.setHardcoreIronMan(true);
			perms2.give(player, PermissionService.PlayerPermissions.HARDCORE_IRON_MAN);
			playerService4.giveItem(player, new Item(20792, 1), true);
			playerService4.giveItem(player, new Item(20794, 1), true);
			playerService4.giveItem(player, new Item(20796, 1), true);
			playerService4.giveItem(player, new Item(1291, 1), true);
			playerService4.giveItem(player, new Item(1181, 1), true);
			playerService4.giveItem(player, new Item(841, 1), true);
			playerService4.giveItem(player, new Item(882, 500), true);
			playerService4.giveItem(player, new Item(555, 500), true);
			playerService4.giveItem(player, new Item(556, 500), true);
			playerService4.giveItem(player, new Item(554, 500), true);
			playerService4.giveItem(player, new Item(558, 500), true);
			playerService4.giveItem(player, new Item(995, 25000), true);
			player.getActionSender().removeChatboxInterface();
			player.getActionSender().sendInterface(269, false);
			break;
		case 0:
			player.getActionSender().sendDialogue("Test", DialogueType.NPC, 2044, FacialAnimation.DEFAULT, "Hello, how may I help you?");
			player.getInterfaceState().setNextDialogueId(0, 1);
			break;
		case 1:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I'd like to bank my items.|Nevermind.");
			player.getInterfaceState().setNextDialogueId(0, 2);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 2:
			Bank.open(player);
			player.getActionSender().removeChatboxInterface();
			break;
		case 3:
			player.getActionSender().sendDialogue("Frodo", DialogueType.NPC, 2898, FacialAnimation.DEFAULT, "What would you like to do?");
			player.getInterfaceState().setNextDialogueId(0, 4);
			break;
		case 4:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I'd like to go somewhere.|Nothing.");
			player.getInterfaceState().setNextDialogueId(0, 5);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 5:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT, "I'd like to go somewhere.");
			player.getInterfaceState().setNextDialogueId(0, 6);
			break;
		case 6:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Bosses|Minigames|Training Areas");
			player.getInterfaceState().setNextDialogueId(0, 7);
			player.getInterfaceState().setNextDialogueId(1, 12);
			player.getInterfaceState().setNextDialogueId(2, 17);
			break;
		case 7: // Bosses
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Bandos|Armadyl|Saradomin|Zamorak|More...");
			player.getInterfaceState().setNextDialogueId(0, 8);
			player.getInterfaceState().setNextDialogueId(1, 9);
			player.getInterfaceState().setNextDialogueId(2, 10);
			player.getInterfaceState().setNextDialogueId(3, 11);
			player.getInterfaceState().setNextDialogueId(4, -1);
			break;
		case 8:
			player.setAttribute("teleportTo", Location.create(2864, 5354, 2));

			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT, "Good luck!");
			player.getInterfaceState().setNextDialogueId(0, 26);
			break;
		case 9:
			player.setAttribute("teleportTo", Location.create(2839, 5296, 2));
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT, "Good luck!");
			player.getInterfaceState().setNextDialogueId(0, 26);
			break;
		case 10:
			player.setAttribute("teleportTo", Location.create(2907, 5265, 0));
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT, "Good luck!");
			player.getInterfaceState().setNextDialogueId(0, 26);
			break;
		case 11:
			player.setAttribute("teleportTo", Location.create(2925, 5331, 2));
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT, "Good luck!");
			player.getInterfaceState().setNextDialogueId(0, 26);
			break;
		case 12: // Minigames
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Nothing|is|here|yet");
			player.getInterfaceState().setNextDialogueId(0, 13);
			player.getInterfaceState().setNextDialogueId(1, 14);
			player.getInterfaceState().setNextDialogueId(2, 15);
			player.getInterfaceState().setNextDialogueId(3, 16);
			break;
		case 13:

			break;
		case 14:

			break;
		case 15:

			break;
		case 16:

			break;
		case 17: // Training areas
		player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
				"Stronghold of Security|Pioneer's Dungeon|Rock Crabs|Nevermind");
		player.getInterfaceState().setNextDialogueId(0, 18);
		player.getInterfaceState().setNextDialogueId(1, 19);
		player.getInterfaceState().setNextDialogueId(2, 20);
		player.getInterfaceState().setNextDialogueId(3, -1);
		break;
		case 18:
			player.setTeleportTarget(Location.create(1860, 5244, 0));
			player.getActionSender().removeChatboxInterface();
			break;
		case 19:
			player.setTeleportTarget(Location.create(3167, 9572, 0));
			player.getActionSender().removeChatboxInterface();
			break;
		case 20:
			player.setTeleportTarget(Location.create(2673, 3714, 0));
			player.getActionSender().removeChatboxInterface();
			break;

		case 21:

			break;
		case 22:
			player.getActionSender().sendDialogue("TzHaar-Mej-Jal", DialogueType.NPC, 2180, FacialAnimation.DEFAULT, "You're on your own now, Jalyt.<br>Prepare to fight for your life!");
			player.getInterfaceState().setNextDialogueId(0, -1);
			break;
		case 23:
			player.getActionSender().sendDialogue(null, DialogueType.MESSAGE, -1, null, "Are you sure you want to merge the shards<br>and create a blade?");
			player.getInterfaceState().setNextDialogueId(0, 24);
			break;
		case 24:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No");
			player.getInterfaceState().setNextDialogueId(0, 25);
			player.getInterfaceState().setNextDialogueId(1, 25000);
			break;
		case 25000:
			player.getActionSender().removeChatboxInterface();
			break;
		case 25:
			player.getActionSender().removeChatboxInterface();
			player.getInventory().remove(new Item(11818, 1));
			player.getInventory().remove(new Item(11820, 1));
			player.getInventory().remove(new Item(11822, 1));
			player.getInventory().add(new Item(11798, 1));
			player.getActionSender().sendMessage("You combine the shards into a Godsword Blade.");
			player.getSkills().addExperience(Skills.SMITHING, 200);
			break;
		case 26:
			final Location teleportTo = player.getAttribute("teleportTo");
			if (teleportTo != null) {
				player.playAnimation(Animation.create(714));
				player.playGraphics(Graphic.create(308, 48, 100));
				World.getWorld().submit(new Tickable(4) {
					@Override
					public void execute() {
						player.setTeleportTarget(teleportTo);
						player.playAnimation(Animation.create(-1));
						player.playAnimation(Animation.create(715));
						this.stop();
					}
				});
			}
			player.getActionSender().removeChatboxInterface();
			break;
		case 27:
			player.getActionSender().sendDialogue("Make-over mage", DialogueType.NPC, 1306, FacialAnimation.DEFAULT, "Hello there! I am known as the make-over mage! I have<br>spent many years researching magics that can change<br>your physical appearance!");
			player.getInterfaceState().setNextDialogueId(0, 28);
			break;
		case 28:
			player.getActionSender().sendDialogue("Make-over mage", DialogueType.NPC, 1306, FacialAnimation.DEFAULT, "I can alter your physical form for a small fee of only<br>3000 gold coins! Would you like me to perform my<br>magics upon you?");
			player.getInterfaceState().setNextDialogueId(0, 29);
			break;
		case 29:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No");
			player.getInterfaceState().setNextDialogueId(0, 30);
			player.getInterfaceState().setNextDialogueId(1, 31);
			break;
		case 30:
			if (player.getInventory().hasItem(new Item(995, 3000))) {
				player.getActionSender().sendInterface(269, false);
				player.getInventory().remove(new Item(995, 3000));
			} else {
				player.getActionSender().sendMessage("You don't have enough gold to do this.");
			}
			player.getActionSender().removeChatboxInterface();
			break;
		case 31:
			player.getActionSender().removeChatboxInterface();
			break;
		case 32:// o.o?

			player.getActionSender().sendDialogue("Kamfreena", DialogueType.NPC, 2461, FacialAnimation.DEFAULT, "Time is up! You have ran out of Warrior Guild Tokens.<br>Please leave the room of Cyclopes as soon as possible.");
			player.getInterfaceState().setNextDialogueId(0, 33);
			break;
		case 33:
			player.getActionSender().removeChatboxInterface();
			break;
		case 34:
			player.getActionSender().sendDialogue("Kamfreena", DialogueType.NPC, 2461, FacialAnimation.ANGER_1, "I said TIME UP! Please leave by yourself next time.");
			player.getInterfaceState().setNextDialogueId(0, 35);
			break;
		case 35:
			player.getActionSender().removeChatboxInterface();
			break;
		case 36:
			player.getActionSender().sendDialogue("Shop keeper", DialogueType.NPC, 514, FacialAnimation.DEFAULT, "Hello! Would you like to see my wide variety of items?");
			player.getInterfaceState().setNextDialogueId(0, 37);
			break;
		case 37:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No");
			player.getInterfaceState().setNextDialogueId(0, 38);
			player.getInterfaceState().setNextDialogueId(1, 39);
			break;
		case 38:
			Shop.open(player, 0, 1);
			player.getActionSender().removeChatboxInterface();
			break;
		case 39:
			player.getActionSender().removeChatboxInterface();
			break;
		case 40:
			player.getActionSender().sendDialogue("Shop keeper", DialogueType.NPC, 516, FacialAnimation.DEFAULT, "Hello! Would you like to see my wide variety of items?");
			player.getInterfaceState().setNextDialogueId(0, 41);
			break;
		case 41:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No");
			player.getInterfaceState().setNextDialogueId(0, 42);
			player.getInterfaceState().setNextDialogueId(1, 43);
			break;
		case 42:
			Shop.open(player, 1, 1);
			player.getActionSender().removeChatboxInterface();
			break;
		case 43:
			player.getActionSender().removeChatboxInterface();
			break;
		case 44:
			player.getActionSender().sendDialogue("Shop keeper", DialogueType.NPC, 518, FacialAnimation.DEFAULT, "Hello! Would you like to see my wide variety of items?");
			player.getInterfaceState().setNextDialogueId(0, 45);
			break;
		case 45:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No");
			player.getInterfaceState().setNextDialogueId(0, 46);
			player.getInterfaceState().setNextDialogueId(1, 47);
			break;
		case 46:
			Shop.open(player, 2, 1);
			player.getActionSender().removeChatboxInterface();
			break;
		case 47:
			player.getActionSender().removeChatboxInterface();
			break;
		case 48:
			player.getActionSender().sendDialogue("You've found a hidden tunnel, do you want to enter?", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No.");
			player.getInterfaceState().setNextDialogueId(0, 49);
			player.getInterfaceState().setNextDialogueId(1, 50);
			break;
		case 49:
			player.setTeleportTarget(Location.create(3551, 9694, 0));
			player.getActionSender().updateMinimap(ActionSender.BLACKOUT_MAP);
			player.getActionSender().removeChatboxInterface();
			break;
		case 50:
			player.getActionSender().removeChatboxInterface();
			break;
		case 51:
			player.getActionSender().sendDialogue("Shop keeper", DialogueType.NPC, 519, FacialAnimation.DEFAULT, "Hello! Would you like to see my wide variety of items?");
			player.getInterfaceState().setNextDialogueId(0, 52);
			break;
		case 52:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No");
			player.getInterfaceState().setNextDialogueId(0, 53);
			player.getInterfaceState().setNextDialogueId(1, 54);
			break;
		case 53:
			Shop.open(player, 3, 1);
			player.getActionSender().removeChatboxInterface();
			break;
		case 54:
			player.getActionSender().removeChatboxInterface();
			break;
		case 55:
			player.getActionSender().sendDialogue("Emblem Trader", DialogueType.NPC, 315, FacialAnimation.DEFAULT, "Hello, what would you like to do?");
			player.getInterfaceState().setNextDialogueId(0, 56);
			break;
		case 56:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Trade in my emblems|Restore my Special Energy|Give me a skull|Change my switching mode|Nothing");
			player.getInterfaceState().setNextDialogueId(0, 10000);
			player.getInterfaceState().setNextDialogueId(1, 57);
			player.getInterfaceState().setNextDialogueId(2, 58);
			player.getInterfaceState().setNextDialogueId(3, 59);
			player.getInterfaceState().setNextDialogueId(4, 69);
			break;
		case 57:
			if (player.getCombatState().getSpecialEnergy() != 100) {
				player.getActionSender().sendMessage(
						"The Emblem Trader restores your special energy.");
				player.getCombatState().setSpecialEnergy(100);
				player.getActionSender().sendConfig(300, 1000);
			} else {
				player.getActionSender().sendMessage(
						"You already have the maximum amount of Special Energy.");
			}
			player.getActionSender().removeChatboxInterface();
			break;
		case 58:
			player.getCombatState().setSkullTicks(1000);
			player.getActionSender().sendMessage("The Emblem Trader marks you with a skull.");
			player.getActionSender().removeChatboxInterface();
			break;
		case 59:
			player.setQueuedSwitching(!player.hasQueuedSwitching());
			player.getActionSender().sendMessage("Switching type toggled. 07 mode: " + player.hasQueuedSwitching());
			player.getActionSender().removeChatboxInterface();
			break;
		case 60:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Buy 10|Buy 50|Buy 100");
			player.getInterfaceState().setNextDialogueId(0, 61);
			player.getInterfaceState().setNextDialogueId(1, 62);
			player.getInterfaceState().setNextDialogueId(2, 63);
			break;
		case 61:
			if (player.getInterfaceState().getShopItem() != -1 && player.getInterfaceState().getShopSlot() != -1) {
				Shop.buyItem(player, player.getInterfaceState().getShopSlot(), player.getInterfaceState().getShopItem(), 10);
				player.getInterfaceState().setShopItem(-1, -1);
			}
			player.getActionSender().removeChatboxInterface();
			break;
		case 62:
			if (player.getInterfaceState().getShopItem() != -1 && player.getInterfaceState().getShopSlot() != -1) {
				Shop.buyItem(player, player.getInterfaceState().getShopSlot(), player.getInterfaceState().getShopItem(), 50);
				player.getInterfaceState().setShopItem(-1, -1);
			}
			player.getActionSender().removeChatboxInterface();
			break;
		case 63:
			if (player.getInterfaceState().getShopItem() != -1 && player.getInterfaceState().getShopSlot() != -1) {
				Shop.buyItem(player, player.getInterfaceState().getShopSlot(), player.getInterfaceState().getShopItem(), 100);
				player.getInterfaceState().setShopItem(-1, -1);
			}
			player.getActionSender().removeChatboxInterface();
			break;
		case 64:
			player.getActionSender().sendDialogue("Telekinetic Guardian", DialogueType.NPC, 5979, FacialAnimation.DEFAULT, "Hello, would you like me to teleport you somewhere?");
			player.getInterfaceState().setNextDialogueId(0, 65);
			break;
		case 65:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"East Dragons|West Dragons|Dark Castle");
			player.getInterfaceState().setNextDialogueId(0, 66);
			player.getInterfaceState().setNextDialogueId(1, 67);
			player.getInterfaceState().setNextDialogueId(2, 68);
			break;
		case 66:
			player.setTeleportTarget(Constants.EAST_DRAGONS);
			player.getActionSender().removeChatboxInterface();
			break;
		case 67:
			player.setTeleportTarget(Constants.WEST_DRAGONS);
			player.getActionSender().removeChatboxInterface();
			break;
		case 68:
			player.setTeleportTarget(Constants.DARK_CASTLE);
			player.getActionSender().removeChatboxInterface();
			break;
		case 69:
			player.getActionSender().removeChatboxInterface();
			break;
		case 70:
			player.getActionSender().sendDialogue("Global Teleports", DialogueType.NPC, 3248, FacialAnimation.DEFAULT, "Greetings mortal, do you wish to travel?");
			player.getInterfaceState().setNextDialogueId(0, 71);
			break;
//		3513
		case 6990:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT, "'Ello and what are you after then?");
			player.getInterfaceState().setNextDialogueId(0, 6991);
			break;
		case 6991:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I need another assignment.|Do you have anything for trade?|About the task system...|Er...nothing...");
			player.getInterfaceState().setNextDialogueId(0, 6992);
			player.getInterfaceState().setNextDialogueId(1, 6993);
			player.getInterfaceState().setNextDialogueId(2, 6994);
			player.getInterfaceState().setNextDialogueId(3, 6995);
			break;
		case 6992:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT, "I need another assignment.");
			player.getInterfaceState().setNextDialogueId(0, 512);
			break;
		case 6993:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Do you have anything for trade?");
			player.getInterfaceState().setNextDialogueId(0, 507);
			break;
		case 6994:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Tell me about the Task System.|Sorry I was just leaving.");
			player.getInterfaceState().setNextDialogueId(0, 509);
			player.getInterfaceState().setNextDialogueId(1, 510);
			break;
		case 6995:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT, "Er...nothing...");
			player.getInterfaceState().setNextDialogueId(0, 6996);
			break;
		case 6996:
			player.getActionSender().removeChatboxInterface();
			break;
		case 7000:
			player.getActionSender().sendDialogue("Estate Agent", DialogueType.NPC, 5419, FacialAnimation.DEFAULT, "'Ello mate, what can I do ye for?");
			player.getInterfaceState().setNextDialogueId(0, 7001);
			break;
		case 7001:
			player.getActionSender().sendDialogue("Select an option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"What can you teach me?|What is that cape you're wearing?|Oh, nevermind");
			player.getInterfaceState().setNextDialogueId(0, 7002);
			player.getInterfaceState().setNextDialogueId(1, 7003);
			player.getInterfaceState().setNextDialogueId(2, 7004);
			break;
		case 7002:
			player.getActionSender().sendDialogue("Estate Agent", DialogueType.NPC, 5419, FacialAnimation.DEFAULT,
					"Take this hammer and build me 2 chairs, and we'll talk.");
			break;
		case 71:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No");
			player.getInterfaceState().setNextDialogueId(0, 72);
			player.getInterfaceState().setNextDialogueId(1, 73);
			break;
		case 72:
			player.getActionSender().sendDialogue("Global Teleports", DialogueType.NPC, 3248, FacialAnimation.DEFAULT, "Where would you like to go?");
			player.getInterfaceState().setNextDialogueId(0, 74);
			break;
		case 73:
			player.getActionSender().removeChatboxInterface();
			break;
		case 74:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Training Areas|Minigames|Boss Teleports|Skilling Teleports|Next Page.");//Training Areas|Minigames|Boss Teleports|City Teleports|PvP Teleports
			player.getInterfaceState().setNextDialogueId(0, 75);
			player.getInterfaceState().setNextDialogueId(1, 76);
			player.getInterfaceState().setNextDialogueId(2, 77);
			player.getInterfaceState().setNextDialogueId(3, 300);//City Teleports 300
			player.getInterfaceState().setNextDialogueId(4, 400);//player.getInterfaceState().setNextDialogueId(4, 400);
			break;
		case 75:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Rock Crabs|Taverly Dungeon|Brimhaven Dungeon|Fremennik Slayer Cave|Next Page.");
			player.getInterfaceState().setNextDialogueId(0, 78);
			player.getInterfaceState().setNextDialogueId(1, 79);
			player.getInterfaceState().setNextDialogueId(2, 80);
			player.getInterfaceState().setNextDialogueId(3, 84);
			player.getInterfaceState().setNextDialogueId(4, 88);
			break;
		case 76:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Barrows|Warriors Guild|Pest Control");
			player.getInterfaceState().setNextDialogueId(0, 81);
			player.getInterfaceState().setNextDialogueId(1, 86);
			player.getInterfaceState().setNextDialogueId(2, 122);
			break;
		case 77:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Godwars|Dagannoths|King Black Dragon|Zulrah|Lizardman Shaman");
			player.getInterfaceState().setNextDialogueId(0, 82);
			player.getInterfaceState().setNextDialogueId(1, 95);
			player.getInterfaceState().setNextDialogueId(2, 83);
			player.getInterfaceState().setNextDialogueId(3, 96);
			player.getInterfaceState().setNextDialogueId(4, 125);
			break;
		case 78:
			player.teleport(Constants.ROCK_CRABS, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 79:
			player.teleport(Constants.TAVERLY_DUNGEON, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 80:
			player.teleport(Constants.BRIMHAVEN_DUNGEON, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 81:
			player.teleport(Constants.BARROWS, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 82:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Armadyl|Bandos|Saradomin|Zamorak");
			player.getInterfaceState().setNextDialogueId(0, 90);
			player.getInterfaceState().setNextDialogueId(1, 91);
			player.getInterfaceState().setNextDialogueId(2, 87);
			player.getInterfaceState().setNextDialogueId(3, 92);
			break;
		case 83:
			player.teleport(Constants.KBD_LAIR, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 84:
			player.teleport(Constants.FREMENNIK_SLAYER_DUNGEON, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 85:
			player.teleport(Constants.KBD_LAIR, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 86:
			player.teleport(Constants.WARRIORS_GUILD, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 87:
			player.teleport(Constants.SARA, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 88:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Slayer Tower|Stronghold of Security|Waterfall Dungeon|Experiments|Next Page.");
			player.getInterfaceState().setNextDialogueId(0, 89);
			player.getInterfaceState().setNextDialogueId(1, 93);
			player.getInterfaceState().setNextDialogueId(2, 94);
			player.getInterfaceState().setNextDialogueId(3, 97);
			player.getInterfaceState().setNextDialogueId(4, 98);
			break;
		case 89:
			player.teleport(Constants.SLAYER_TOWER, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 90:
			player.teleport(Constants.ARMADYL, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 91:
			player.teleport(Constants.BANDOS, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 92:
			player.teleport(Constants.ZAMMY, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 93:
			player.teleport(Location.create(1860, 5244, 0), 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 94:
			player.teleport(Constants.WATERFALL, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 95:
			player.teleport(Constants.DAGANNOTHKINGS, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 96:
			player.teleport(Constants.ZULRAH, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 97:
			player.teleport(Location.create(3577, 9927, 0), 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 98:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Mourner Tunnels|Asgarnian Ice Dungeon|Sand Crabs|Dagannoth Lair");
			player.getInterfaceState().setNextDialogueId(0, 99);
			player.getInterfaceState().setNextDialogueId(1, 141);
			player.getInterfaceState().setNextDialogueId(2, 123);
			player.getInterfaceState().setNextDialogueId(3, 124);
			break;
		case 123:
			player.teleport(Constants.SAND_CRABS, 0, 0, true);
			break;
		case 124:
			player.teleport(Constants.DAGANNOTHS, 0, 0, true);
			break;
		case 99:
			player.setTeleportTarget(Location.create(2033, 4636));
			player.getActionSender().removeChatboxInterface();
			break;
		case 125:
			player.setTeleportTarget(Location.create(1464, 3688, 0));
			player.getActionSender().removeChatboxInterface();
			break;
		case 141:
			player.setTeleportTarget(Location.create(3009, 9550, 0));
			player.getActionSender().removeChatboxInterface();
			break;
		case 100:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.ATTACK_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>  Congratulations, you just advanced an Attack level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.ATTACK) + ".");
			break;
		case 101:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.DEFENCE_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080> Congratulations, you just advanced a Defence level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.DEFENCE) + ".");
			if (player.getSkills().getLevelForExperience(Skills.DEFENCE) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 107);
			}
			break;
		case 102:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.STRENGTH_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Strength level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.STRENGTH) + ".");
			break;
		case 103:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.HITPOINT_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Hitpoints level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.HITPOINTS) + ".");
			break;
		case 104:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.RANGING_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Ranged level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.RANGE) + ".");
			if (player.getSkills().getLevelForExperience(Skills.RANGE) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 108);
			}
			break;
		case 105:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PRAYER_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Prayer level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.PRAYER) + ".");
			break;
		case 106:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.MAGIC_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Magic level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.MAGIC) + ".");
			break;
		case 107:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.COOKING_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Cooking level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.COOKING) + ".");
			break;
		case 108:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.WOODCUTTING_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Woodcutting level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.WOODCUTTING) + ".");
			break;
		case 109:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.FLETCHING_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>  Congratulations, you just advanced a Fletching level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.FLETCHING) + ".");
			break;
		case 110:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.FISHING_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Fishing level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.FISHING) + ".");
			break;
		case 111:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.FIREMAKING_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Firemaking level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.FIREMAKING) + ".");
			break;
		case 112:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.CRAFTING_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Crafting level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.CRAFTING) + ".");
			break;
		case 113:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.SMITHING_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>  Congratulations, you just advanced a Smithing level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.SMITHING) + ".");
			break;
		case 114:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.MINING_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>  Congratulations, you just advanced a Mining level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.MINING) + ".");
			break;
		case 115:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.HERBLORE_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Herblore level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.HERBLORE) + ".");
			break;
		case 116:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.AGILITY_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced an Agility level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.AGILITY) + ".");
			break;
		case 117:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.THIEVING_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Thieving level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.THIEVING) + ".");
			break;
		case 118:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.SLAYER_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Slayer level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.SLAYER) + ".");
			break;
		case 119:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.FARMING_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Farming level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.FARMING) + ".");
			break;
		case 120:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.RUNECRAFTING_LEVEL_UP, -1, FacialAnimation.DEFAULT,
					"<col=000080>Congratulations, you just advanced a Runecrafting level!",
					"You have now reached level " + player.getSkills().getLevelForExperience(Skills.RUNECRAFTING) + ".");
			break;

		case 122:
			player.setTeleportTarget(Location.create(2659, 2676));
			player.getActionSender().removeChatboxInterface();
			break;

		case 306:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT, "Hello, I'm the Survival Guide, How can I help you?");
			player.getInterfaceState().setNextDialogueId(0, 307);
			break;
		case 307:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"How do I get around the game?|How can I make money?|Where can I start training?|Nothing.");
			player.getInterfaceState().setNextDialogueId(0, 308);
			player.getInterfaceState().setNextDialogueId(1, 309);
			player.getInterfaceState().setNextDialogueId(2, 310);
			player.getInterfaceState().setNextDialogueId(3, 54);
			break;
		case 308:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT, "How do I get around the game?");
			player.getInterfaceState().setNextDialogueId(0, 311);
			break;
		case 309:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT, "How can I make money?");
			player.getInterfaceState().setNextDialogueId(0, 312);
			break;
		case 310:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT, "Where can I start training?");
			player.getInterfaceState().setNextDialogueId(0, 313);
			break;
		case 311:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT, "Use spell teleports, jewelry teleports and the Wizard Distentor in Falador center to access other game regions.");
			player.getInterfaceState().setNextDialogueId(0, 307);
			break;
		case 312:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT, "Their are various methods of money methods, Skilling/PvM are the main methods to increase the amount of cash you have.");
			player.getInterfaceState().setNextDialogueId(0, 307);
			break;
		case 313:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT, "The basic training area's that you can use can be found by talking<br>to the Wizard in the Centre of Falador.");
			player.getInterfaceState().setNextDialogueId(0, 307);
			break;
		case 315:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT, "Can I interest you in my skilling wares?");
			player.getInterfaceState().setNextDialogueId(0, 316);
			break;
		case 316:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes.|No thanks.");
			player.getInterfaceState().setNextDialogueId(0, 317);
			player.getInterfaceState().setNextDialogueId(0, 318);
			break;
		case 317:

		case 500:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT, "'Ello and what are you after then?");
			player.getInterfaceState().setNextDialogueId(0, 501);
			break;
		case 501:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I need another assignment.|Do you have anything for trade?|About the task system...|Er...nothing...");
			player.getInterfaceState().setNextDialogueId(0, 502);
			player.getInterfaceState().setNextDialogueId(1, 503);
			player.getInterfaceState().setNextDialogueId(2, 504);
			player.getInterfaceState().setNextDialogueId(3, 505);
			break;
		case 502:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT, "I need another assignment.");
			player.getInterfaceState().setNextDialogueId(0, 512);
			break;
		case 503:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Do you have anything for trade?");
			player.getInterfaceState().setNextDialogueId(0, 507);
			break;
		case 504:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Tell me about the Task System.|Sorry I was just leaving.");
			player.getInterfaceState().setNextDialogueId(0, 509);
			player.getInterfaceState().setNextDialogueId(1, 510);
			break;
		case 505:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT, "Er...nothing...");
			player.getInterfaceState().setNextDialogueId(0, 506);
			break;
		case 506:
			player.getActionSender().removeChatboxInterface();
			break;
		case 507:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.HAPPY, "I have a wide selection of Slayer equipment; take a look!");
			player.getInterfaceState().setNextDialogueId(0, 508);
			break;
		case 508:
			Shop.open(player, 12, 1);
			player.getActionSender().removeChatboxInterface();
			break;
		case 509:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT, "Tell me about the task system.");
			player.getInterfaceState().setNextDialogueId(0, 511);
			break;
		case 510:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Sorry I was just leaving.");
			player.getInterfaceState().setNextDialogueId(0, 506);
			break;
		case 511:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT, "There isn't much information on it now, come back later.");
			player.getInterfaceState().setNextDialogueId(0, 506);
			break;
		case 512:
			if (player.getSlayer().getSlayerTask() != null) {
				player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT, "You're still hunting " + player.getSlayer().getSlayerTask().getName() + "; come back when you've<br>finished your task.");
				player.getInterfaceState().setNextDialogueId(0, 506);
			} else {
				final Master master = Master.forId(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getId());
				final SlayerTask newTask = slayerService.assignTask(player, master);
				if (newTask != null) {
					player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT, "Great, you're doing great. Your new task is to kill<br>" + newTask.getTaskAmount() + " " + newTask.getName() + "s");
					player.getInterfaceState().setNextDialogueId(0, 506);
				}
			}
			break;
		case 513:
			player.getActionSender().sendDialogue("Vannaka", DialogueType.NPC, 403, FacialAnimation.HAPPY, "Hello there, " + player.getName() + ", what can I help you with?");
			player.getInterfaceState().setNextDialogueId(0, 514);
			break;
		case 514:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"How am I doing so far?|Who are you?|Where are you?|Got any tips for me|Nothing really.");
			player.getInterfaceState().setNextDialogueId(0, 515);
			player.getInterfaceState().setNextDialogueId(1, 516);
			player.getInterfaceState().setNextDialogueId(2, 517);
			player.getInterfaceState().setNextDialogueId(3, 518);
			player.getInterfaceState().setNextDialogueId(4, 519);
			break;
		case 515:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "How am I doing so far?");
			player.getInterfaceState().setNextDialogueId(0, 520);
			break;
		case 516:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Who are you?");
			player.getInterfaceState().setNextDialogueId(0, 521);
			break;
		case 517:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Where are you?");
			player.getInterfaceState().setNextDialogueId(0, 522);
			break;
		case 518:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Got any tips for me?");
			player.getInterfaceState().setNextDialogueId(0, 523);
			break;
		case 519:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Nothing really.");
			player.getInterfaceState().setNextDialogueId(0, 73);
			break;
		case 520:
			if (player.getSlayer().getSlayerTask() != null) {
				player.getActionSender().sendDialogue("Vannaka", DialogueType.NPC, 403, FacialAnimation.HAPPY, "You're current assigned to kill " + player.getSlayer().getSlayerTask().getName().toLowerCase() + "; only " + player.getSlayer().getSlayerTask().getTaskAmount() + " more", "to go.");
				player.getInterfaceState().setNextDialogueId(0, 514);
			} else {
				player.getActionSender().sendDialogue("Vannaka", DialogueType.NPC, 403, FacialAnimation.HAPPY, "You currently have no task, come to me so I can assign you one.");
				player.getInterfaceState().setNextDialogueId(0, 514);
			}
			break;
		case 521:
			player.getActionSender().sendDialogue("Vannaka", DialogueType.NPC, 403, FacialAnimation.HAPPY, "My name's Vannaka; I'm a Slayer Master.");
			player.getInterfaceState().setNextDialogueId(0, 514);
			break;
		case 522:
			player.getActionSender().sendDialogue("Vannaka", DialogueType.NPC, 403, FacialAnimation.HAPPY, "You'll find me in the city of Edgeville.<br>I'll be here when you need a new task.");
			player.getInterfaceState().setNextDialogueId(0, 514);
			break;
		case 523:
			player.getActionSender().sendDialogue("Vannaka", DialogueType.NPC, 403, FacialAnimation.HAPPY, "At the moment, no.");
			player.getInterfaceState().setNextDialogueId(0, 514);
			break;
		case 3666:
			player.getActionSender().sendDialogue("Prince Brand", DialogueType.NPC, 3666, FacialAnimation.HAPPY, "Oh thank you for supporting my kingdom, what can I do for you?");
			player.getInterfaceState().setNextDialogueId(0, 3667);
			break;
		case 3667:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"What is this place?|I'd like to browse your shop.|Nothing, I'll be going now.");
			player.getInterfaceState().setNextDialogueId(0, 3668);
			player.getInterfaceState().setNextDialogueId(1, 3669);
			player.getInterfaceState().setNextDialogueId(2, 3670);
			break;
		case 3668:
			player.getActionSender().sendDialogue("Prince Brand", DialogueType.NPC, 3666, FacialAnimation.HAPPY, "This land is the Extreme Donator Zone, only for the most elite supporters of ProjectV, you'll find an abundance of activities you can do here.");
			player.getInterfaceState().setNextDialogueId(0, 3671);
			break;
		case 3671:
			player.getActionSender().sendDialogue("Prince Brand", DialogueType.NPC, 3666, FacialAnimation.HAPPY, "Most skills are trainable here but beware; some areas you will be vulnerable to player attacks!");
			player.getInterfaceState().setNextDialogueId(0, 3667);
			break;
		case 3669:
			player.getActionSender().sendDialogue("Prince Brand", DialogueType.NPC, 3666, FacialAnimation.HAPPY, "Oh of course, I've got a few items you may enjoy..");
			Shop.open(player, 14, 0);
			player.getActionSender().removeChatboxInterface();
			break;
		case 3670:
			player.getActionSender().sendDialogue("Prince Brand", DialogueType.NPC, 3666, FacialAnimation.HAPPY, "Farewell, come back soon.");
			player.getActionSender().removeChatboxInterface();
			break;
		case 687:
			player.getActionSender().sendDialogue("Bartender", DialogueType.NPC, 687, FacialAnimation.HAPPY, "Would you like to start the Recipe for Disaster minigame?");
			player.getInterfaceState().setNextDialogueId(0, 688);
			break;
		case 688:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No i'd like to see your shop.|No thanks.");
			player.getInterfaceState().setNextDialogueId(0, 689);
			player.getInterfaceState().setNextDialogueId(1, 690);
			player.getInterfaceState().setNextDialogueId(2, 691);
			break;
		case 689:
			player.getRFD().start();
			player.getActionSender().removeChatboxInterface();
			break;
		case 690:
			Shop.open(player, 13, 2);
			player.getActionSender().removeChatboxInterface();
			break;
		case 691:
			player.getActionSender().removeChatboxInterface();
			break;
		case 822:
			player.getActionSender().sendDialogue("Oziach", DialogueType.NPC, 822, FacialAnimation.HAPPY, "Hello, What can I do for you?");
			player.getInterfaceState().setNextDialogueId(0, 823);
			break;
		case 823:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Can you combine my draconic visage and my anti-fire shield?|Nothing.");
			player.getInterfaceState().setNextDialogueId(0, 824);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 824:
			boolean hasItems = player.getInventory().contains(11286) && player.getInventory().contains(1540);
			if (hasItems) {
				player.getActionSender().sendDialogue("Oziach", DialogueType.NPC, 822, FacialAnimation.HAPPY, "Sure! It'll cost you 750,000 coins. Are you sure you want to pay this?");
				player.getInterfaceState().setNextDialogueId(0, 825);
			} else {
				player.getActionSender().sendDialogue("Oziach", DialogueType.NPC, 822, FacialAnimation.HAPPY, "You don't seem to have the visage and anti-fire shield with you.");
				player.getInterfaceState().setNextDialogueId(0, -1);
			}
			break;
		case 825:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes please|No");
			player.getInterfaceState().setNextDialogueId(0, 826);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 826:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Yes please");
			player.getInterfaceState().setNextDialogueId(0, 827);
			break;
		case 827:
			hasItems = player.getInventory().contains(11286) && player.getInventory().contains(1540) && player.getInventory().getCount(995) >= 750000;
			if (hasItems) {
				player.getActionSender().removeChatboxInterface();
				player.getInventory().remove(new Item(11286, 1));
				player.getInventory().remove(new Item(1540, 1));
				player.getInventory().remove(new Item(995, 750000));
				player.getInventory().add(new Item(11283, 1));
				player.getActionSender().sendMessage("Oziach takes the items and combines them for you.");
			} else {
				player.getActionSender().sendDialogue("Oziach", DialogueType.NPC, 822, FacialAnimation.HAPPY, "You don't seem to have all the required items, Come back when you have them.");
				player.getInterfaceState().setNextDialogueId(0, -1);
			}
			break;
		case 5550:
			player.getActionSender().sendDialogue("Arianwyn", DialogueType.NPC, 3432, FacialAnimation.HAPPY, "Hello, What can I do for you?");
			player.getInterfaceState().setNextDialogueId(0, 823);
			break;
		case 5551:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I'd like to purchase a Crystal bow.|I'd like to purchase a Crystal shield.|I'd like to purchase a Crystal halberd");
			player.getInterfaceState().setNextDialogueId(0, 5552);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 5552:
			boolean xhasItems = player.getInventory().contains(4207);
			if (xhasItems) {
				player.getActionSender().sendDialogue("Arianwyn", DialogueType.NPC, 3432, FacialAnimation.HAPPY, "Sure! It'll cost you 750,000 coins. Are you sure you want to pay this?");
				player.getInterfaceState().setNextDialogueId(0, 825);
			} else {
				player.getActionSender().sendDialogue("Arianwyn", DialogueType.NPC, 3432, FacialAnimation.HAPPY, "You don't seem to have the visage and anti-fire shield with you.");
				player.getInterfaceState().setNextDialogueId(0, -1);
			}
			break;
		case 5553:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes please|No");
			player.getInterfaceState().setNextDialogueId(0, 826);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 5554:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Yes please");
			player.getInterfaceState().setNextDialogueId(0, 827);
			break;
		case 5555:
			xhasItems = player.getInventory().contains(11286) && player.getInventory().contains(1540) && player.getInventory().getCount(995) >= 750000;
			if (xhasItems) {
				player.getActionSender().removeChatboxInterface();
				player.getInventory().remove(new Item(11286, 1));
				player.getInventory().remove(new Item(1540, 1));
				player.getInventory().remove(new Item(995, 750000));
				player.getInventory().add(new Item(11283, 1));
				player.getActionSender().sendMessage("Arianwyn takes the items and combines them for you.");
			} else {
				player.getActionSender().sendDialogue("Arianwyn", DialogueType.NPC, 822, FacialAnimation.HAPPY, "You don't seem to have all the required items, Come back when you have them.");
				player.getInterfaceState().setNextDialogueId(0, -1);
			}
			break;
		case 2461:
			String message = "I will release some cyclops to drop the next<br>defender for you.";
			int next = 2462;
			if (player.getInventory().getCount(WarriorsGuild.TOKENS) < 100 && !Constants.hasAttackCape(player)) {
				message = "You don't have enough tokens to enter.";
				next = 2463;
			}
			player.getActionSender().sendDialogue("Kamfreena", DialogueType.NPC, 2461, FacialAnimation.HAPPY, message);
			player.getInterfaceState().setNextDialogueId(0, next);
			break;
		case 2462:
			player.getWarriorsGuild().handleDoorClick(WarriorsGuild.GAME_DOOR_1);
			player.getActionSender().removeChatboxInterface();
			break;
		case 2463:
			player.getActionSender().removeChatboxInterface();
			break;
		case 1603:
			if (player.getSettings().completedMageArena()) {
				player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Hello, Kolodion.");
				player.getInterfaceState().setNextDialogueId(0, 1621);
			} else {
				player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Hello there. What is this place?");
				player.getInterfaceState().setNextDialogueId(0, 1604);
			}
			break;
		case 1604:
			player.getActionSender().sendDialogue("Kolodion", DialogueType.NPC, 1603, FacialAnimation.HAPPY, "I am the great Kolodion, master of battle magic, and<br>this is my battle arena. Top wizards travel from all over<br>Survival to fight here.");
			player.getInterfaceState().setNextDialogueId(0, 1605);
			break;
		case 1605:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Can I fight here?|Fairwell!");
			player.getInterfaceState().setNextDialogueId(0, 1606);
			player.getInterfaceState().setNextDialogueId(1, 1607);
			break;
		case 1606:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Can I fight here?");
			player.getInterfaceState().setNextDialogueId(0, 1608);
			break;
		case 1607:
			player.getActionSender().removeChatboxInterface();
			break;
		case 1608:
			player.getActionSender().sendDialogue("Kolodion", DialogueType.NPC, 1603, FacialAnimation.HAPPY, "My arena is open to any high level wizard, but this is<br>no game. Many wizards fall in this arena, never to rise<br>again. The strongest mages have been destroyed.");
			player.getInterfaceState().setNextDialogueId(0, 1609);
			break;
		case 1609:
			player.getActionSender().sendDialogue("Kolodion", DialogueType.NPC, 1603, FacialAnimation.HAPPY, "If you're sure you want in?");
			player.getInterfaceState().setNextDialogueId(0, 1610);
			break;
		case 1610:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes indeedy.|No I don't.");
			player.getInterfaceState().setNextDialogueId(0, 1611);
			player.getInterfaceState().setNextDialogueId(1, 1607);
			break;
		case 1611:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Yes indeedy.");
			player.getInterfaceState().setNextDialogueId(0, 1612);
			break;
		case 1612:
			player.getActionSender().sendDialogue("Kolodion", DialogueType.NPC, 1603, FacialAnimation.HAPPY, "Good, good. You have a healthy sense of competition.");
			player.getInterfaceState().setNextDialogueId(0, 1613);
			break;
		case 1613:
			player.getActionSender().sendDialogue("Kolodion", DialogueType.NPC, 1603, FacialAnimation.HAPPY, "Remember, traveller - in my arena, hand-to-hand<br>combat is useless. Your strength will diminish as you<br>enter the arena, but the spells you can learn are<br>amonst the most powerful in all of Survival.");
			player.getInterfaceState().setNextDialogueId(0, 1614);
			break;
		case 1614:
			player.getActionSender().sendDialogue("Kolodion", DialogueType.NPC, 1603, FacialAnimation.HAPPY, "Before I can accept you in, we must duel.");
			player.getInterfaceState().setNextDialogueId(0, 1615);
			break;
		case 1615:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Okay, let's fight.|No thanks.");
			player.getInterfaceState().setNextDialogueId(0, 1616);
			player.getInterfaceState().setNextDialogueId(1, 1607);
			break;
		case 1616:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Okay, let's fight.");
			player.getInterfaceState().setNextDialogueId(0, 1617);
			break;
		case 1617:
			player.getActionSender().sendDialogue("Kolodion", DialogueType.NPC, 1603, FacialAnimation.HAPPY, "I must first check that you are up to scratch.");
			if (player.getSkills().getLevelForExperience(Skills.MAGIC) > 59) {
				player.getInterfaceState().setNextDialogueId(0, 1618);
			} else {
				player.getInterfaceState().setNextDialogueId(0, 1626);
			}
			break;
		case 1618:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "You don't need to worry about that.");
			player.getInterfaceState().setNextDialogueId(0, 1619);
			break;
		case 1619:
			player.getActionSender().sendDialogue("Kolodion", DialogueType.NPC, 1603, FacialAnimation.HAPPY, "Not just any magician can enter - only the most<br>powerfl and most feared. Before you can use the<br>power of this arena, you must prove yourself against me.");
			player.getInterfaceState().setNextDialogueId(0, 1620);
			break;
		case 1620:
			player.getActionSender().removeChatboxInterface();
			player.getMageArena().start();
			break;
		case 1621:
			player.getActionSender().sendDialogue("Kolodion", DialogueType.NPC, 1603, FacialAnimation.HAPPY, "Hello, young mage. You're a tough one.");
			player.getInterfaceState().setNextDialogueId(0, 1622);
			break;
		case 1622:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "What now?");
			player.getInterfaceState().setNextDialogueId(0, 1623);
			break;
		case 1623:
			player.getActionSender().sendDialogue("Kolodion", DialogueType.NPC, 1603, FacialAnimation.HAPPY, "Step into the magic pool. It will take you to a chamber.<br>There, you must decide which god you will represent in<br>the arena.");
			player.getInterfaceState().setNextDialogueId(0, 1624);
			break;
		case 1624:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY, "Thanks, Kolodion");
			player.getInterfaceState().setNextDialogueId(0, 1625);
			break;
		case 1625:
			player.getActionSender().sendDialogue("Kolodion", DialogueType.NPC, 1603, FacialAnimation.HAPPY, "That's what I'm here for.");
			player.getInterfaceState().setNextDialogueId(0, 1607);
			break;
		case 1626:
			player.getActionSender().sendDialogue("Kolodion", DialogueType.NPC, 1603, FacialAnimation.HAPPY, "You don't seem to be a powerful enough magician yet.");
			player.getInterfaceState().setNextDialogueId(0, 1607);
			break;
		case 1712:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Edgeville|Karamja|Draynor Village|Al Kharid|Nothing.");
			player.getInterfaceState().setNextDialogueId(0, 1713);
			player.getInterfaceState().setNextDialogueId(1, 1714);
			player.getInterfaceState().setNextDialogueId(2, 1715);
			player.getInterfaceState().setNextDialogueId(3, 1716);
			player.getInterfaceState().setNextDialogueId(4, 1717);
			break;
		case 1713:
			player.getJewellery().gemTeleport(player, Location.create(3089, 3496, 0));
			player.getActionSender().removeChatboxInterface();
			break;
		case 1714:
			player.getJewellery().gemTeleport(player, Location.create(2918, 3176, 0));
			player.getActionSender().removeChatboxInterface();
			break;
		case 1715:
			player.getJewellery().gemTeleport(player, Location.create(3105, 3249, 0));
			player.getActionSender().removeChatboxInterface();
			break;
		case 1716:
			player.getJewellery().gemTeleport(player, Location.create(3293, 3163, 0));
			player.getActionSender().removeChatboxInterface();
			break;
		case 1717:
			player.getActionSender().removeChatboxInterface();
			break;
		case 1718:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Burthorpe Games Room|Barbarian Outpost|Nowhere");
			player.getInterfaceState().setNextDialogueId(0, 1719);
			player.getInterfaceState().setNextDialogueId(1, 1720);
			player.getInterfaceState().setNextDialogueId(2, 1721);
			break;
		case 1719:
			player.getJewellery().gemTeleport(player, Location.create(2926, 3559, 0));
			player.getActionSender().removeAllInterfaces().removeChatboxInterface();
			break;
		case 1720:
			player.getJewellery().gemTeleport(player, Location.create(2525, 3576, 0));
			player.getActionSender().removeAllInterfaces().removeChatboxInterface();
			break;
		case 1721:
			player.getActionSender().removeAllInterfaces().removeChatboxInterface();
			break;
		case 1722:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Al-Kharid Duel Arena|Castle wars Arena|Clan wars Arena|Nowhere");
			player.getInterfaceState().setNextDialogueId(0, 1724);
			player.getInterfaceState().setNextDialogueId(1, 1723);
			player.getInterfaceState().setNextDialogueId(2, 1725);
			player.getInterfaceState().setNextDialogueId(3, 1726);
			break;
		case 1723:
			player.getJewellery().gemTeleport(player, Location.create(2440, 3089, 0));
			player.getActionSender().removeAllInterfaces().removeChatboxInterface();
			break;
		case 1724:
			player.getJewellery().gemTeleport(player, Location.create(3316, 3235, 0));
			player.getActionSender().removeAllInterfaces().removeChatboxInterface();
			break;
		case 1725:
			player.getJewellery().gemTeleport(player, Location.create(3369, 3169, 0));
			player.getActionSender().removeAllInterfaces().removeChatboxInterface();
			break;
		case 1726:
			player.getActionSender().removeAllInterfaces().removeChatboxInterface();
			break;

		case 1755:
			player.getActionSender().sendDialogue("Void Knight", DialogueType.NPC, 1755, FacialAnimation.HAPPY, "Would you like to upgrade your ring for 500 Pest Control Points?");
			player.getInterfaceState().setNextDialogueId(0, 1756);
			break;
		case 1756:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No");
			player.getInterfaceState().setNextDialogueId(0, 1757);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 1757:
			int pcPoints = player.getDatabaseEntity().getStatistics().getPestControlPoints();
			if (pcPoints < 500) {
				player.getActionSender().sendDialogue("Void Knight", DialogueType.NPC, 1755, FacialAnimation.ANGER_1, "You don't seem to have enough Pest Control Points to upgrade.");
				player.getInterfaceState().setNextDialogueId(0, -1);
			} else {
				if (player.getInterfaceAttribute("ring") != null &&
						player.getInventory().contains(player.getInterfaceAttribute("ring"))) {
					int item = player.getInterfaceAttribute("ring");
					Optional<Constants.ImbuedRings> ring = Constants.ImbuedRings.of(item);
					if (ring.isPresent()) {
						player.getActionSender().removeChatboxInterface();
						player.getInventory().remove(new Item(item));
						player.getInventory().add(new Item(ring.get().getImbued()));
						player.getDatabaseEntity().getStatistics().setPestControlPoints(pcPoints - 500);
						player.getActionSender().sendMessage("You imbue your " + CacheItemDefinition.get(item).name + " for 500 Pest Control Points.");
					}
				}
			}
			break;
		case 2000:
			boolean trimmed = player.trimmed();
			boolean skillMaster = player.getSkills().getLevelForExperience(getSkillId((int) player.getAttribute("talkingNpc"))) == 99;
			Item cape = null;
			Item hood = null;
			switch ((int) player.getAttribute("talkingNpc")) {
			case 2460://ajjat
				player.getActionSender().sendDialogue("Ajjat", DialogueType.NPC, 2460, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Attack skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in attack.");
				cape = new Item(trimmed ? 9748 : 9747, 1);
				hood = new Item(9749, 1);
				break;
			case 3216://melee tutor
				player.getActionSender().sendDialogue("Melee combat tutor", DialogueType.NPC, 3216, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Defence skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in defence.");
				cape = new Item(trimmed ? 9754 : 9753, 1);
				hood = new Item(9755, 1);
				break;
			case 2473://sloane
				player.getActionSender().sendDialogue("Sloane", DialogueType.NPC, 2473, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Strength skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in strength.");
				cape = new Item(trimmed ? 9751 : 9750, 1);
				hood = new Item(9752, 1);
				break;
			case 6059://armour salesman
				player.getActionSender().sendDialogue("Armour salesman", DialogueType.NPC, 6059, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Ranged skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in ranged.");
				cape = new Item(trimmed ? 9757 : 9756, 1);
				hood = new Item(9758, 1);
				break;
			case 2578://brother jered
				player.getActionSender().sendDialogue("Brother Jered", DialogueType.NPC, 2578, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Prayer skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in hitpoints.");
				cape = new Item(trimmed ? 9759 : 9760, 1);
				hood = new Item(9761, 1);
				break;
			case 2658://head chef
				player.getActionSender().sendDialogue("Head Chef", DialogueType.NPC, 2658, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Cooking skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in cooking.");
				cape = new Item(trimmed ? 9802 : 9801, 1);
				hood = new Item(9803, 1);
				break;
			case 1044://hickton
				player.getActionSender().sendDialogue("Hickton", DialogueType.NPC, 1044, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Fletching skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in fletching.");
				cape = new Item(trimmed ? 9784 : 9783, 1);
				hood = new Item(9785, 1);
				break;
			case 118://ignatius vulcan
				player.getActionSender().sendDialogue("Ignatius Vulcan", DialogueType.NPC, 118, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Firemaking skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in firemaking.");
				cape = new Item(trimmed ? 9805 : 9804, 1);
				hood = new Item(9806, 1);
				break;
			case 5045://Kaqemeex
				if (skillMaster) {
					player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
							"Can I see your shop?|Can I purchase a cape of Herblore");
					player.getInterfaceState().setNextDialogueId(0, 2003);
					player.getInterfaceState().setNextDialogueId(1, 2004);
				} else {
					player.getActionSender().removeChatboxInterface();
					Shop.open(player, 29, 0);
				}
				return;
			case 3193://Martin Thwait
				player.getActionSender().sendDialogue("Martin Thwait", DialogueType.NPC, 3193, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Thieving skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in thieving.");
				cape = new Item(trimmed ? 9778 : 9777, 1);
				hood = new Item(9779, 1);
				break;
			case 5810://Master Crafter
				player.getActionSender().sendDialogue("Master Crafter", DialogueType.NPC, 5810, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Crafting skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in crafting.");
				cape = new Item(trimmed ? 9781 : 9780, 1);
				hood = new Item(9782, 1);
				break;
			case 2913://Master fisher
				player.getActionSender().sendDialogue("Master fisher", DialogueType.NPC, 2913, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Fishing skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in fishing.");
				cape = new Item(trimmed ? 9799 : 9798, 1);
				hood = new Item(9800, 1);
				break;
			case 3249://Robe Store owner
				player.getActionSender().sendDialogue("Robe Store owner", DialogueType.NPC, 3249, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Magic skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in magic.");
				cape = new Item(trimmed ? 9763 : 9762, 1);
				hood = new Item(9764, 1);
				break;
			case 3343://Surgeon General Tafani
				player.getActionSender().sendDialogue("Surgeon General Tafani", DialogueType.NPC, 3343, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Hitpoints skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in hitpoints.");
				cape = new Item(trimmed ? 9769 : 9768, 1);
				hood = new Item(9770, 1);
				break;
			case 4733://Thurgo
				player.getActionSender().sendDialogue("Thurgo", DialogueType.NPC, 4733, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Smithing skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in smithing.");
				cape = new Item(trimmed ? 9796 : 9795, 1);
				hood = new Item(9797, 1);
				break;
			case 3226://Woodsman tutor
				player.getActionSender().sendDialogue("Woodsman tutor", DialogueType.NPC, 3226, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Woodcutting skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in woodcutting.");
				cape = new Item(trimmed ? 9808 : 9807, 1);
				hood = new Item(9809, 1);
				break;
			case 405://Duradel
				player.getActionSender().sendDialogue("Duradel", DialogueType.NPC, 405, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Slayer skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in slayer.");
				cape = new Item(trimmed ? 9787 : 9786, 1);
				hood = new Item(9788, 1);
				break;
			case 3363:
				player.getActionSender().sendDialogue("Dwarf", DialogueType.NPC, 3363, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Mining skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in mining.");
				cape = new Item(trimmed ? 9793 : 9792, 1);
				hood = new Item(9794, 1);
				break;
			case 5832:
				player.getActionSender().sendDialogue("Martin the Master Gardener", DialogueType.NPC, 5832, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Farming skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in farming.");
				cape = new Item(trimmed ? 9811 : 9810, 1);
				hood = new Item(9812, 1);
				break;
			case 637:
				player.getActionSender().sendDialogue("Aubury", DialogueType.NPC, 637, FacialAnimation.HAPPY, skillMaster ? "You seem to be a master of the Runecrafting skill do you want a skillcape for 99k?" : "Come speak to me when you're a master in runecrafting.");
				cape = new Item(trimmed ? 9766 : 9765, 1);
				hood = new Item(9767, 1);
				break;
			}
			player.getInterfaceState().setNextDialogueId(0, skillMaster ? 2001 : -1);
			player.setAttribute("cape", cape);
			player.setAttribute("hood", hood);
			break;
		case 2001:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No.");
			player.getInterfaceState().setNextDialogueId(0, 2002);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 2002:
			player.getActionSender().removeChatboxInterface();
			boolean hasGold = player.getInventory().getCount(995) > 99000;
			if (hasGold) {
				if (player.hasAttribute("cape") && player.hasAttribute("hood")) {
					if (player.getInventory().add(player.getAttribute("cape")) && player.getInventory().add(player.getAttribute("hood"))) {
						player.getInventory().remove(new Item(995, 99000));
						player.getActionSender().sendMessage("You purchase an attack skillcape for 99k");
					}
					player.removeAttribute("cape");
					player.removeAttribute("hood");
				}
			} else {
				player.getActionSender().sendMessage("Not enough coins to purchase this.");
			}
			break;
		case 2003:
			player.getActionSender().removeChatboxInterface();
			Shop.open(player, 29, 0);
			break;
		case 2004:
			trimmed = player.trimmed();
			player.getActionSender().sendDialogue("Kaqemeex", DialogueType.NPC, 5045, FacialAnimation.HAPPY, "You seem to be a master of the Herblore skill do you want a skillcape for 99k?");
			cape = new Item(trimmed ? 9775 : 9774, 1);
			hood = new Item(9776, 1);
			player.getInterfaceState().setNextDialogueId(0, 2001);
			player.setAttribute("cape", cape);
			player.setAttribute("hood", hood);
			break;

		case 2180:
			player.getActionSender().sendDialogue("Tzhaar-Mej-Jal", DialogueType.NPC, 2180, FacialAnimation.HAPPY, "Hello would you like to gamble your fire capes?");
			player.getInterfaceState().setNextDialogueId(0, 2181);
			break;
		case 2181:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No.");
			player.getInterfaceState().setNextDialogueId(0, 2182);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 2182:
			player.getActionSender().sendDialogue("Tzhaar-Mej-Jal", DialogueType.NPC, 2180, FacialAnimation.HAPPY, "How many would you like to gamble?");
			player.getInterfaceState().setNextDialogueId(0, 2183);
			break;
		case 2183:
			player.getActionSender().removeChatboxInterface();
			player.getActionSender().sendEnterAmountInterface();
			player.setInterfaceAttribute("gamble_firecape", true);
			break;

		case 9000:
			player.getActionSender().sendDialogue("Adam", DialogueType.NPC, 311, FacialAnimation.HAPPY, "Welcome to Survival, I am Adam I can transform you into a Iron man if you'd wish?");
			player.getInterfaceState().setNextDialogueId(0, 9001);
			break;
		case 550:
			player.getActionSender().sendDialogue("Overseer", DialogueType.NPC, 5886, FacialAnimation.HAPPY, "Hello, human. Bring me your Bludgeon axon, bludgeon spine, and your bludgeon claw.");
			player.getInterfaceState().setNextDialogueId(0, 551);
			break;
		case 551:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Okay!|No.");
			player.getInterfaceState().setNextDialogueId(0, 552);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 552:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(13275) && player.getInventory().contains(13276) && player.getInventory().contains(13274)) {
				player.getInventory().remove(new Item(13275));
				player.getInventory().remove(new Item(13276));
				player.getInventory().remove(new Item(13274));
				player.getInventory().add(new Item(13263));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 13263, null, "The Overseer merges your parts together and Hands you a Abyssal Bludgeon.");
			}
			break;
		case 660:
			player.getActionSender().sendDialogue(" ", DialogueType.MESSAGE_MODEL_LEFT, 13273, FacialAnimation.HAPPY, "You place the Unsired into the Font of Consumption...");
			player.getInterfaceState().setNextDialogueId(0, 661);
			break;
		case 661:
			player.getActionSender().sendDialogue(" ", DialogueType.MESSAGE_MODEL_LEFT, 13273, FacialAnimation.HAPPY, "The Font consumes the Unsired and returns you a reward.");
			break;
		case 9001:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No.");
			player.getInterfaceState().setNextDialogueId(0, 9002);
			player.getInterfaceState().setNextDialogueId(1, 9003);
			break;
		case 9002:
			player.getActionSender().sendDialogue("Adam", DialogueType.NPC, 311, FacialAnimation.HAPPY, "Are you sure? If you become a Iron man your account will be restricted.");
			player.getInterfaceState().setNextDialogueId(0, 9004);
			break;
		case 9003:
			player.getActionSender().sendDialogue("Adam", DialogueType.NPC, 311, FacialAnimation.HAPPY, "That's fine feel free to speak to the Guide to learn about Survival!");
			player.removeAttribute("busy");
			player.getInterfaceState().setNextDialogueId(0, -1);
			break;
		case 9004:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No.");
			player.getInterfaceState().setNextDialogueId(0, 9005);
			player.getInterfaceState().setNextDialogueId(1, 9006);
			break;
		case 9005:
			player.removeAttribute("busy");
			player.getActionSender().sendDialogue("", DialogueType.MESSAGE, -1, null,
					"You are now an Iron Man.");
			permissionService.give(player, PermissionService.PlayerPermissions.IRON_MAN);
			break;
		case 9006:
			player.removeAttribute("busy");
			player.getActionSender().removeChatboxInterface();
			break;

		case 1340:
			if (permissionService.isAny(player, PermissionService.PlayerPermissions.ADMINISTRATOR, PermissionService.PlayerPermissions.ADMINISTRATOR)) {
				player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
						"Edit Name Color|I'd like to see the donator shop|Collect donation");
				player.getInterfaceState().setNextDialogueId(0, 1341);
				player.getInterfaceState().setNextDialogueId(1, 1342);
				player.getInterfaceState().setNextDialogueId(2, 1347);
			} else {
				player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
						"I'd like to claim my donation.|I'd like to see the donator shop|Who are you?");
				player.getInterfaceState().setNextDialogueId(0, 1347);
				player.getInterfaceState().setNextDialogueId(1, 1342);
				player.getInterfaceState().setNextDialogueId(2, 1348);
			}
			break;
		case 1341:
			if (permissionService.isAny(player, PermissionService.PlayerPermissions.ADMINISTRATOR, PermissionService.PlayerPermissions.ADMINISTRATOR)) {
				player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
						"Red|Blue|Pink|White|Black");
				player.getInterfaceState().setNextDialogueId(0, 1342);
				player.getInterfaceState().setNextDialogueId(1, 1343);
				player.getInterfaceState().setNextDialogueId(2, 1344);
				player.getInterfaceState().setNextDialogueId(3, 1345);
				player.getInterfaceState().setNextDialogueId(4, 1346);
			}
			break;
		case 1342:
			player.getActionSender().removeChatboxInterface();
			//				Shop.open(player, 41, 0);
			player.setNameColor("<col=FF0000>");
			break;
		case 1343:
			player.getActionSender().removeChatboxInterface();
			player.setNameColor("<col=0000FF>");
			break;
		case 1344:
			player.getActionSender().removeChatboxInterface();
			player.setNameColor("<col=FF69B4>");
			break;
		case 1345:
			player.getActionSender().removeChatboxInterface();
			player.setNameColor("<col=FFFFFF>");
			break;
		case 1346:
			player.getActionSender().removeChatboxInterface();
			player.setNameColor("");
			break;
		/*case 1347:
			if (!DonationManager.rspsdata(player, player.getName())) {
				player.getActionSender().sendDialogue("Matthias", DialogueType.NPC, 1340, FacialAnimation.HAPPY, "You don't seem to have anything waiting for you.");
				player.getInterfaceState().setNextDialogueId(0, -1);
			}
			break;*/
		case 1348:
			player.getActionSender().sendDialogue("Matthias", DialogueType.NPC, 1340, FacialAnimation.HAPPY, "Hello, I am a servant to donators,<br>Become a donator today and enjoy all the awesome benefits that come with it!");
			player.getInterfaceState().setNextDialogueId(0, -1);
			break;

			/* Ranged combat tutor */
		case 1349:
			player.getActionSender().sendDialogue("Ranged combat tutor", DialogueType.NPC, 3217, FacialAnimation.DEFAULT,
					"Here's your ranged starter kit. You only receive one; so put it go good use.");
			break;
		case 1350:
			player.getActionSender().sendDialogue("Ranged combat tutor", DialogueType.NPC, 3217, FacialAnimation.DEFAULT,
					"You have already received your ranged starter kit.");
			break;

			/* Magic combat tutor */
		case 1351:
			player.getActionSender().sendDialogue("Magic combat tutor", DialogueType.NPC, 3218, FacialAnimation.DEFAULT,
					"Here's your magic starter kit. You only receive one; so put it to good use.");
			break;
		case 1352:
			player.getActionSender().sendDialogue("Magic combat tutor", DialogueType.NPC, 3218, FacialAnimation.DEFAULT,
					"You have already received your magic starter kit.");
			break;
		case 1353: // Slayer ring
			/*if (Location.getWildernessLevel(player, player.getLocation()) > 20) {
					player.getActionSender().sendMessage("You cannot use this above level 20 Wilderness.");
					player.getActionSender().removeChatboxInterface();
					return;
				}*/
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Slayer Cave|Cave Horrors|Abyssal Demons");
			player.getInterfaceState().setNextDialogueId(0, 1354);
			player.getInterfaceState().setNextDialogueId(1, 1355);
			player.getInterfaceState().setNextDialogueId(2, 1356);
			player.getInterfaceState().setNextDialogueId(3, 1357);
			break;
		case 1354:
			player.getJewellery().gemTeleport(player, Location.create(2438, 9822, 0));
			player.getActionSender().removeAllInterfaces().removeChatboxInterface();
			break;
		case 1355:
			player.getJewellery().gemTeleport(player, Location.create(3747, 9374, 0));
			player.getActionSender().removeAllInterfaces().removeChatboxInterface();
			break;
		case 1356:
			player.getJewellery().gemTeleport(player, Location.create(3424, 3549, 2));
			player.getActionSender().removeAllInterfaces().removeChatboxInterface();
			break;
		case 1357:
			break;

			/* START: Paul (ironman) */
		case 1358:
			player.getActionSender().sendDialogue("Paul", DialogueType.NPC, 317, FacialAnimation.DEFAULT,
					"Here's your iron man armour. Take care of it!");
			break;
		case 1359:
			player.getActionSender().sendDialogue("Paul", DialogueType.NPC, 317, FacialAnimation.DEFAULT,
					"You have already received your iron man armour.");
			break;
		case 1360:
			player.getActionSender().sendDialogue("Paul", DialogueType.NPC, 317, FacialAnimation.DEFAULT,
					"Hello, how can I help you?");
			player.getInterfaceState().setNextDialogueId(0, 1361);
			player.getActionSender().sendDialogue("Paul", DialogueType.NPC, 317, FacialAnimation.DEFAULT,
                        "You are not an iron man. Your iron men receive special armour.");
			break;
		case 1361:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I want to become an Ultimate Iron Man|I want to claim my Iron man Armour|Nothing.");
			player.getInterfaceState().setNextDialogueId(0, 1362);
			player.getInterfaceState().setNextDialogueId(1, 1363);
			player.getInterfaceState().setNextDialogueId(2, -1);
			break;
		case 1362:
			player.getActionSender().sendDialogue("Paul", DialogueType.NPC, 317, FacialAnimation.DEFAULT,
					"Are you sure? If you do this your account will be completely reset including Items and Stats and unable to bank.");
			player.getInterfaceState().setNextDialogueId(0, 1364);
			break;
		case 1363:
			final boolean normalIronMan = permissionService.is(player, PermissionService.PlayerPermissions.IRON_MAN);
			final boolean ultimateIronMan = permissionService.is(player, PermissionService.PlayerPermissions.ULTIMATE_IRON_MAN);
			ClaimService claimService = Server.getInjector().getInstance(ClaimService.class);
			PlayerService playerService = Server.getInjector().getInstance(PlayerService.class);;
			if (!normalIronMan && !ultimateIronMan) {
				player.getActionSender().sendDialogue("Paul", DialogueType.NPC, 317, FacialAnimation.DEFAULT,
						"You are not an iron man. Your iron men receive special armour.");
				player.getInterfaceState().setNextDialogueId(0, -1);
			} else {
				if (claimService.hasClaimed(player, ClaimType.IRONMAN_ARMOUR)) {
					player.getActionSender().sendDialogue("Paul", DialogueType.NPC, 317, FacialAnimation.DEFAULT,
							"You have already received your iron man armour.");
					player.getInterfaceState().setNextDialogueId(0, -1);
				} else {
					player.getActionSender().sendDialogue("Paul", DialogueType.NPC, 317, FacialAnimation.DEFAULT,
							"Here's your iron man armour. Take care of it!");
					if (normalIronMan) {
						playerService.giveItem(player, new Item(12810, 1), true);
						playerService.giveItem(player, new Item(12811, 1), true);
						playerService.giveItem(player, new Item(12812, 1), true);
					} else {
						playerService.giveItem(player, new Item(12813, 1), true);
						playerService.giveItem(player, new Item(12814, 1), true);
						playerService.giveItem(player, new Item(12815, 1), true);
					}
					claimService.claim(player, ClaimType.IRONMAN_ARMOUR);
					player.getInterfaceState().setNextDialogueId(0, -1);
				}
			}
			break;
		case 1364:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes I want to become an Ultimate Iron man|No!!!");
			player.getInterfaceState().setNextDialogueId(0, 1365);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 1365:
			PermissionService perms3 = Server.getInjector().getInstance(PermissionService.class);
			GroundItemService groundItems = Server.getInjector().getInstance(GroundItemService.class);
			if (permissionService.is(player, PermissionService.PlayerPermissions.ULTIMATE_IRON_MAN)) {
				player.getActionSender().sendDialogue("Paul", DialogueType.NPC, 317, FacialAnimation.DEFAULT,
						"You are already an ultimate iron man.");
				player.getInterfaceState().setNextDialogueId(0, -1);
			} else {
				if (player.getSkills().getTotalLevel() != 32) {
					player.getActionSender().sendDialogue("Paul", DialogueType.NPC, 317, FacialAnimation.DEFAULT,
							"You must have not leveled your account to become an iron man.");
					player.getInterfaceState().setNextDialogueId(0, -1);
				} else {
					player.getActionSender().removeChatboxInterface();
					player.getBank().clear();
					player.getInventory().clear();
					player.getSkills().resetStats();
					player.setIsIronMan(false);
					player.setUltimateIronMan(true);
					perms3.give(player, PermissionService.PlayerPermissions.ULTIMATE_IRON_MAN);
					groundItems.getPlayerDroppedItems(player.getName()).forEach(groundItems::removeGroundItem);
				}
			}
			break;
			/* END: Paul (ironman) */

		case 300:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Camelot Woodcutting|Varrock Mining|Falador Mine|Piscatoris Fishing colony|Next Page.");//Edgeville|Karamja|Gnome Stronghold|Piscatoris Fishing colony|Next Page.
			player.getInterfaceState().setNextDialogueId(0, 301);
			player.getInterfaceState().setNextDialogueId(1, 302);
			player.getInterfaceState().setNextDialogueId(2, 303);
			player.getInterfaceState().setNextDialogueId(3, 304);
			player.getInterfaceState().setNextDialogueId(4, 3006);
			break;
		case 301://Camelot Woodcutting
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(2731, 3485));
			break;
		case 302://Varrock Mining
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(3283, 3371));
			break;
		case 303://Falador Mine
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(3053, 9774));
			break;
		case 304://Piscatoris Fishing colony
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(2332, 3681));
			break;
		case 305://Agility
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Gnome Agility Course|Draynor Rooftop|Seer's Village Rooftop");
			player.getInterfaceState().setNextDialogueId(0, 390);
			player.getInterfaceState().setNextDialogueId(1, 391);
			player.getInterfaceState().setNextDialogueId(2, 392);
			break;
		case 390://Gnome Agility Course
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(2471, 3437));
			break;
		case 391://Draynor Rooftop
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(3104, 3264));
			break;	
		case 392://Seer's Village Rooftop
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(2731, 3485));
			break;	



		case 400:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Cities|Player vs Player|Member Zone");
			player.getInterfaceState().setNextDialogueId(0, 401);
			player.getInterfaceState().setNextDialogueId(1, 402);
			player.getInterfaceState().setNextDialogueId(2, 403);
			break;
		case 401:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Al-Kharid|Ape Atoll|Ardougne|Camelot|Next Page.");
			player.getInterfaceState().setNextDialogueId(0, 409);
			player.getInterfaceState().setNextDialogueId(1, 410);
			player.getInterfaceState().setNextDialogueId(2, 411);
			player.getInterfaceState().setNextDialogueId(3, 412);
			player.getInterfaceState().setNextDialogueId(4, 413);
			break;
		case 409:
			player.teleport(Constants.AL_KHARID, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 410:
			player.teleport(Constants.APE_ATOLL, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 411:
			player.teleport(Constants.ARDOUGNE, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 412:
			player.teleport(Constants.CAMELOT, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 413:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Draynor Village|Falador|Karamja|Lumbridge|Next Page.");
			player.getInterfaceState().setNextDialogueId(0, 414);
			player.getInterfaceState().setNextDialogueId(1, 415);
			player.getInterfaceState().setNextDialogueId(2, 416);
			player.getInterfaceState().setNextDialogueId(3, 417);
			player.getInterfaceState().setNextDialogueId(4, 418);
			break;
		case 414:
			player.teleport(Constants.DRAYNOR_VILLAGE, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 415:
			player.teleport(Constants.FALADOR, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 416:
			player.teleport(Constants.KARAMJA, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 417:
			player.teleport(Constants.LUMBRIDGE, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 418:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Shilo Village|Varrock|Watchtower|Zanaris|Next Page");
			player.getInterfaceState().setNextDialogueId(0, 419);
			player.getInterfaceState().setNextDialogueId(1, 420);
			player.getInterfaceState().setNextDialogueId(2, 421);
			player.getInterfaceState().setNextDialogueId(3, 422);
			player.getInterfaceState().setNextDialogueId(4, 423);
			break;
		case 419:
			player.teleport(Constants.SHILO_VILLAGE, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 420:
			player.teleport(Constants.VARROCK, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 421:
			player.teleport(Constants.WATCHTOWER, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 422:
			player.teleport(Constants.ZANARIS, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 423:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Lletya|Dorgesh Kaan|Lunar Isle|Pollnivneach");
			player.getInterfaceState().setNextDialogueId(0, 424);
			player.getInterfaceState().setNextDialogueId(1, 425);
			player.getInterfaceState().setNextDialogueId(2, 426);
			player.getInterfaceState().setNextDialogueId(3, 427);
			break;
		case 424:
			player.teleport(Constants.LLETYA, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 425:
			player.teleport(Constants.DORG, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 426:
			player.teleport(Constants.LUNAR_ISLE, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 427:
			player.teleport(Constants.POLLNIVNEACH, 0, 0, true);
			player.getActionSender().removeChatboxInterface();
			break;
		case 402:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"East Dragons|West Dragons|Chaos Ele|Resource Area");
			player.getInterfaceState().setNextDialogueId(0, 405);
			player.getInterfaceState().setNextDialogueId(1, 406);
			player.getInterfaceState().setNextDialogueId(2, 407);
			player.getInterfaceState().setNextDialogueId(3, 408);
			break;
		case 405: //easts
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(3351, 3670));
			break;
		case 406://wests
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(2985, 3596));
			break;
		case 407://chaos ele
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(3287, 3923));
			break;
		case 408://widly skill area
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(3184, 3952));
			break;
		case 403:
			player.getActionSender().removeChatboxInterface();
			player.teleport(Constants.MEMBER_ZONE, 0, 0, true);
			player.getActionSender().sendMessage("Thank you for supporting ProjectV");
			break;
		case 404:
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(3189, 3959));
			player.getActionSender().sendMessage("THE SNAKE BANK RETURNS!");
			break;

		case 450://member npc
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DISTRESSED,
					"Hi|I'm not finished|Come back later");
			player.getInterfaceState().setNextDialogueId(0, 5000);
			player.getInterfaceState().setNextDialogueId(0, 5001);
			player.getInterfaceState().setNextDialogueId(0, 5002);

		case 3006:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Agility|Tai Bwo Wannai|Entrana|Shilo Village|Next Page.");
			player.getInterfaceState().setNextDialogueId(0, 305);
			player.getInterfaceState().setNextDialogueId(1, 3007);
			player.getInterfaceState().setNextDialogueId(2, 3008);
			player.getInterfaceState().setNextDialogueId(3, 3009);
			player.getInterfaceState().setNextDialogueId(4, 3010);
			break;
		case 3010:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Crafting Guild");
			player.getInterfaceState().setNextDialogueId(0, 3018);
			break;
		case 3018:
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(2933, 3290));//3081
			break;
		case 3007:
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(2802, 3078));//3081
			break;
		case 3008:
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(2834, 3335));
			break;
		case 3009:
			player.getActionSender().removeChatboxInterface();
			player.setTeleportTarget(Location.create(2865, 2956));
			break;

		case 2040:
			player.getActionSender().sendDialogue("Zul-Areth", DialogueType.NPC, 2040, FacialAnimation.DEFAULT,
					"Hello, How can I help you?");
			player.getInterfaceState().setNextDialogueId(0, 2041);
			break;
		case 2041:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Can I collect my items?");
			player.getInterfaceState().setNextDialogueId(0, 2042);
			break;
		case 2042:
			boolean required = player.getDatabaseEntity().getZulrahState().getItemsLostZulrah().isEmpty();
			int amount = permissionService.isAny(player, PermissionServiceImpl.SPECIAL_PERMISSIONS) ? 37500 : 75000;
			//s int amount = player.getRights() != Player.Rights.PLAYER && player.getRights() != Player.Rights.IRON_MAN ? 37500 : 75000;
			player.getActionSender().sendDialogue("Zul-Areth", DialogueType.NPC, 2040, FacialAnimation.DEFAULT,
					required ? "You currently don't have any items awaiting you." : "Absolutely, that'll be " + amount + " coins.");
			player.getInterfaceState().setNextDialogueId(0, required ? 2043 : 2044);
			break;
		case 2043:
			player.getActionSender().removeChatboxInterface();
			break;
		case 2044:
			player.getActionSender().removeChatboxInterface();
			player.getZulAreth().claimItems();
			break;
		case 2045:
			player.getActionSender().sendDialogue("Zul-Areth", DialogueType.NPC, 2040, FacialAnimation.DEFAULT,
					"You don't have enough coins.");
			player.getInterfaceState().setNextDialogueId(0, 2046);
			break;
		case 2046:
			player.getActionSender().removeChatboxInterface();
			break;

		case 2914:
			player.getActionSender().sendDialogue("Otto Godblessed", DialogueType.NPC, 2914, FacialAnimation.DEFAULT,
					"Hello how can I help you?");
			player.getInterfaceState().setNextDialogueId(0, 2915);
			break;
		case 2915:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Can you make my Zamorakian Spear into a hasta?|Nothing.");
			player.getInterfaceState().setNextDialogueId(0, 2916);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 2916:
			player.getActionSender().sendDialogue("Otto Godblessed", DialogueType.NPC, 2914, FacialAnimation.DEFAULT,
					"For a fee of 300,000 coins, Is that okay?");
			player.getInterfaceState().setNextDialogueId(0, 2917);
			break;
		case 2917:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes.|No.");
			player.getInterfaceState().setNextDialogueId(0, 2918);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 2918:
			boolean hasSpear = player.getInventory().contains(11824);
			boolean hasCoins = player.getInventory().getCount(995) >= 300000;
			player.getActionSender().removeChatboxInterface();
			if (!hasSpear) {
				player.getActionSender().sendMessage("You seem to be missing the Zamorakian Spear.");
			} else if (!hasCoins) {
				player.getActionSender().sendMessage("You don't have enough coins to complete this.");
			} else {
				player.getInventory().remove(new Item(995, 300000));
				player.getInventory().remove(new Item(11824));
				player.getInventory().add(new Item(11889));
			}
			break;

		case 3227:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"Good day, how may I help you?");
			player.getInterfaceState().setNextDialogueId(0, 3228);
			break;
		case 3228:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"How do i use the bank?|I'd like to access my bank account please.|I'd like to check my PIN settings.|I'd like to collect items.");
			player.getInterfaceState().setNextDialogueId(0, 3229);
			player.getInterfaceState().setNextDialogueId(1, 3230);
			player.getInterfaceState().setNextDialogueId(2, 3258);
			player.getInterfaceState().setNextDialogueId(3, -1);
			break;
		case 3229:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Using the bank itself.|Using Bank deposit boxes.|What's this PIN thing that people keep talking about?|Goodbye.");
			player.getInterfaceState().setNextDialogueId(0, 3230);
			player.getInterfaceState().setNextDialogueId(1, 3238);
			player.getInterfaceState().setNextDialogueId(2, 3241);
			player.getInterfaceState().setNextDialogueId(3, -1);
			break;
		case 3230:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"Using the bank itself. I'm not sure how....?");
			player.getInterfaceState().setNextDialogueId(0, 3231);
			break;
		case 3231:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"Speak to any banker and ask to see your bank<br>account. If you have set a PIN you will be asked for<br>it, then all the belongings you have placed in the bank<br>will appear in the window. To withdraw one item, left-");
			player.getInterfaceState().setNextDialogueId(0, 3232);
			break;
		case 3232:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"click on it once.");
			player.getInterfaceState().setNextDialogueId(0, 3233);
			break;
		case 3233:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"To withdraw many, right-click on the item and select<br>from the menu. The same for depositing, left-click on<br>the item in your inventory to deposit it in the bank.<br>Right-click on it to deposit many of the same items.");
			player.getInterfaceState().setNextDialogueId(0, 3234);
			break;
		case 3234:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"To move things around in your bank: firstly select<br>Swap or Insert as your default moving mode, you can<br>find these buttons on the bank window itself. Then click<br>and drag an item to where you want it to appear.");
			player.getInterfaceState().setNextDialogueId(0, 3235);
			break;
		case 3235:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"You may withdraw 'notes' or 'certificates' when the<br>items you are trying to withdraw do not stack in your<br>ivnentory. This will only work for items which are<br>tradable.");
			player.getInterfaceState().setNextDialogueId(0, 3236);
			break;
		case 3236:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"For instance, if you wanted to sell 100 logs to another<br>player, they would not fin in one inventory and you<br>would need to do multiple trades. Instead, click the<br>Note button to do withdraw the logs as 'certs' or 'notes'.");
			player.getInterfaceState().setNextDialogueId(0, 3237);
			break;
		case 3237:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"then withdraw the items you need.");
			player.getInterfaceState().setNextDialogueId(0, 3229);
			break;
		case 3238:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"Using Bank deposit boxes.... what are they?");
			player.getInterfaceState().setNextDialogueId(0, 3239);
			break;
		case 3239:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"They look like grey pillars, there's one just over there,<br>Bank Deposit boxes save so much time. If you're<br>simply wanting to deposit a single item, 'Use'<br>it on the deposit box.");
			player.getInterfaceState().setNextDialogueId(0, 3240);
			break;
		case 3240:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"Otherwise, simply click once on the box and it will give<br>you a choice of what to deposit in an interface very<br>similiar to the bank itself. Very quick for when you're<br>simply fishing or mining etc.");
			player.getInterfaceState().setNextDialogueId(0, 3229);
			break;
		case 3241:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"What's this PIN thing that people keep talking about?");
			player.getInterfaceState().setNextDialogueId(0, 3242);
			break;
		case 3242:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"The PIN - Personal Identification Number - can be<br>set on your bank account to protect the items there in<br>case someone finds out your account password. It<br>consists of four numbers that you remember and tell");
			player.getInterfaceState().setNextDialogueId(0, 3243);
			break;
		case 3243:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"no one.");
			player.getInterfaceState().setNextDialogueId(0, 3244);
			break;
		case 3244:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"So if someone did manage to get your password they<br>couldn't steal your items if they were in the bank.");
			player.getInterfaceState().setNextDialogueId(0, 3245);
			break;
		case 3245:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"How do I set my PIN?|How do I remove my PIN?|What happens if I forget my PIN?|I know about the PIN, tell me about the bank.|Goodbye.");
			player.getInterfaceState().setNextDialogueId(0, 3246);
			player.getInterfaceState().setNextDialogueId(1, 3252);
			player.getInterfaceState().setNextDialogueId(2, 3255);
			player.getInterfaceState().setNextDialogueId(3, 3229);
			player.getInterfaceState().setNextDialogueId(4, -1);
			break;
		case 3246:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"How do I set my PIN?");
			player.getInterfaceState().setNextDialogueId(0, 3247);
			break;
		case 3247:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"You can set your PIN by talking to any banker, they<br>will allow you to access your bank pin settings. Here<br>you can choose to set your pin and recovery delay.<br>Remember not to set it to anything personal such as");
			player.getInterfaceState().setNextDialogueId(0, 3248);
			break;
		case 3248:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"your real life bank PIN or birthday.");
			player.getInterfaceState().setNextDialogueId(0, 3249);
			break;
		case 3249:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"The recovery delay is to protect your banked items<br>from account thieves. If someone stole your account<br>and asked to have the PIN deleted, they would have to<br>wait a few days before accessing your bank account to");
			player.getInterfaceState().setNextDialogueId(0, 3250);
			break;
		case 3250:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"steal your items. This will give you time to recover<br>your account.");
			player.getInterfaceState().setNextDialogueId(0, 3251);
			break;
		case 3251:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"There will also be a delay in actually setting the PIN<br>to be used, this is so that if your account is stolen and<br>a PIN set, you can cancel it before it comes into use!");
			player.getInterfaceState().setNextDialogueId(0, 3245);
			break;
		case 3252:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"How do I remove my PIN?");
			player.getInterfaceState().setNextDialogueId(0, 3253);
			break;
		case 3253:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"Talking to any banker will enable you to access your<br>PIN settings. There you can cancel or change your<br>PIN, but you will need to wait for your recovery<br>delay to expire to be able to access your bank. This");
			player.getInterfaceState().setNextDialogueId(0, 3254);
			break;
		case 3254:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"can be set in the settings page and will protect your<br>items should your account be stolen.");
			player.getInterfaceState().setNextDialogueId(0, 3245);
			break;
		case 3255:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"What happens if I forget my PIN?");
			player.getInterfaceState().setNextDialogueId(0, 3256);
			break;
		case 3256:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"If you find yourself faced with the PIN keypad and<br>you don't know the PIN, just look on the right-hand<br>side for a button marked 'I don't know it'. Click this<br>button. Your PIN will be deleted (after a delay of a");
			player.getInterfaceState().setNextDialogueId(0, 3257);
			break;
		case 3257:
			player.getActionSender().sendDialogue(CacheNPCDefinition.get(player.getAttribute("talkingNpc")).getName(), DialogueType.NPC, player.getAttribute("talkingNpc"), FacialAnimation.DEFAULT,
					"few days) and you'll be able to use your bank as<br>before. You may still use the bank deposit box without<br>your PIN.");
			player.getInterfaceState().setNextDialogueId(0, 3245);
			break;
		case 3258:
			BankPinService service = Server.getInjector().getInstance(BankPinService.class);

			player.getActionSender().removeChatboxInterface();
			service.openPinSettingsInterface(player, BankPinServiceImpl.SettingScreenType.DEFAULT);
			break;

		case 5919:
			player.getActionSender().sendDialogue("Grace", DialogueType.NPC, 5919, FacialAnimation.DEFAULT,
					"Hello, How can I help you?");
			player.getInterfaceState().setNextDialogueId(0, 5920);
			break;
		case 5920:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I'd like to color my graceful clothing.|Nothing.");
			player.getInterfaceState().setNextDialogueId(0, 5921);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 5921:
			player.getActionSender().sendDialogue("Grace", DialogueType.NPC, 5919, FacialAnimation.DEFAULT,
					"What color would you like to upgrade to?");
			player.getInterfaceState().setNextDialogueId(0, 5922);
			break;
		case 5922:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Purple|Blue|Yellow|Red|Green|");
			player.getInterfaceState().setNextDialogueId(0, 5923);
			player.getInterfaceState().setNextDialogueId(1, 5924);
			player.getInterfaceState().setNextDialogueId(2, 5925);
			player.getInterfaceState().setNextDialogueId(3, 5926);
			player.getInterfaceState().setNextDialogueId(4, 5927);
			break;
		case 5923:
			//Purple
			if (player.getInventory().containsItems(Constants.GRACEFUL) && player.getInventory().getCount(11849) >= 90) {
				player.getActionSender().removeChatboxInterface();
				player.getInventory().removeItems(Constants.GRACEFUL);
				player.getInventory().remove(new Item(11849, 90));
				player.getInventory().addItems(Constants.PURPLE_GRACEFUL);
			} else {
				player.getActionSender().sendDialogue("Grace", DialogueType.NPC, 5919, FacialAnimation.DEFAULT,
						"You need a full graceful set and 90 marks of grace to upgrade.");
				player.getInterfaceState().setNextDialogueId(0, -1);
			}
			break;
		case 5924:
			//Blue
			if (player.getInventory().containsItems(Constants.GRACEFUL) && player.getInventory().getCount(11849) >= 90) {
				player.getActionSender().removeChatboxInterface();
				player.getInventory().removeItems(Constants.GRACEFUL);
				player.getInventory().remove(new Item(11849, 90));
				player.getInventory().addItems(Constants.BLUE_GRACEFUL);
			} else {
				player.getActionSender().sendDialogue("Grace", DialogueType.NPC, 5919, FacialAnimation.DEFAULT,
						"You need a full graceful set and 90 marks of grace to upgrade.");
				player.getInterfaceState().setNextDialogueId(0, -1);
			}
			break;
		case 5925:
			//Yellow
			if (player.getInventory().containsItems(Constants.GRACEFUL) && player.getInventory().getCount(11849) >= 90) {
				player.getActionSender().removeChatboxInterface();
				player.getInventory().removeItems(Constants.GRACEFUL);
				player.getInventory().remove(new Item(11849, 90));
				player.getInventory().addItems(Constants.YELLOW_GRACEFUL);
			} else {
				player.getActionSender().sendDialogue("Grace", DialogueType.NPC, 5919, FacialAnimation.DEFAULT,
						"You need a full graceful set and 90 marks of grace to upgrade.");
				player.getInterfaceState().setNextDialogueId(0, -1);
			}
			break;
		case 5926:
			//Red
			if (player.getInventory().containsItems(Constants.GRACEFUL) && player.getInventory().getCount(11849) >= 90) {
				player.getActionSender().removeChatboxInterface();
				player.getInventory().removeItems(Constants.GRACEFUL);
				player.getInventory().remove(new Item(11849, 90));
				player.getInventory().addItems(Constants.RED_GRACEFUL);
			} else {
				player.getActionSender().sendDialogue("Grace", DialogueType.NPC, 5919, FacialAnimation.DEFAULT,
						"You need a full graceful set and 90 marks of grace to upgrade.");
				player.getInterfaceState().setNextDialogueId(0, -1);
			}
			break;
		case 5927:
			//Green
			if (player.getInventory().containsItems(Constants.GRACEFUL) && player.getInventory().getCount(11849) >= 90) {
				player.getActionSender().removeChatboxInterface();
				player.getInventory().removeItems(Constants.GRACEFUL);
				player.getInventory().remove(new Item(11849, 90));
				player.getInventory().addItems(Constants.GREEN_GRACEFUL);
			} else {
				player.getActionSender().sendDialogue("Grace", DialogueType.NPC, 5919, FacialAnimation.DEFAULT,
						"You need a full graceful set and 90 marks of grace to upgrade.");
				player.getInterfaceState().setNextDialogueId(0, -1);
			}
			break;

		case 12954:
			int interfaceId1 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId1, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId1, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId1, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 12954, 150);

			player.getInterfaceState().setNextDialogueId(0, 12955);
			break;
		case 12955:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 12956);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 12956:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(12954) && player.getInventory().contains(20143)) {
				player.getInventory().remove(new Item(12954));
				player.getInventory().remove(new Item(20143));
				player.getInventory().add(new Item(19722));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 19722, null, "You merge the two together to make an Dragon Defender (t).");
				player.getActionSender().sendMessage("You merge the two together to make a Dragon Defender (t).");
			}
			break;

		case 11920:
			int interfaceId2 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId2, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId2, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId2, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 11920, 150);

			player.getInterfaceState().setNextDialogueId(0, 11921);
			break;
		case 11921:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 11922);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11922:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(11920) && player.getInventory().contains(12800)) {
				player.getInventory().remove(new Item(11920));
				player.getInventory().remove(new Item(12800));
				player.getInventory().add(new Item(12797));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 12797, null, "You merge the two together to make an Dragon Pickaxe.");
				player.getActionSender().sendMessage("You merge the two together to make a Dragon Pickaxe.");
			}
			break;

		case 11335:
			int interfaceId3 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId3, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId3, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId3, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 11335, 150);

			player.getInterfaceState().setNextDialogueId(0, 11336);
			break;
		case 11336:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 11337);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11337:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(11335) && player.getInventory().contains(12538)) {
				player.getInventory().remove(new Item(11335));
				player.getInventory().remove(new Item(12538));
				player.getInventory().add(new Item(12417));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 12417, null, "You merge the two together to make an Dragon fullhelm (g).");
				player.getActionSender().sendMessage("You merge the two together to make a Dragon fullhelm (g).");
			}
			break;

		case 1187:
			int interfaceId4 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId4, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId4, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId4, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 1187, 150);

			player.getInterfaceState().setNextDialogueId(0, 1188);
			break;
		case 1188:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 1189);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 1189:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(1187) && player.getInventory().contains(12532)) {
				player.getInventory().remove(new Item(1187));
				player.getInventory().remove(new Item(12532));
				player.getInventory().add(new Item(12418));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 12418, null, "You merge the two together to make an Dragon sq shield (g).");
				player.getActionSender().sendMessage("You merge the two together to make a Dragon sq shiel (g).");
			}
			break;

		case 11787:
			int interfaceId5 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId5, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId5, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId5, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 11787, 150);

			player.getInterfaceState().setNextDialogueId(0, 11788);
			break;
		case 11788:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 11789);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11789:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(11787) && player.getInventory().contains(12798)) {
				player.getInventory().remove(new Item(11787));
				player.getInventory().remove(new Item(12798));
				player.getInventory().add(new Item(12795));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 12795, null, "You merge the two together to make an Steam Battlestaff.");
				player.getActionSender().sendMessage("You merge the two together to make a Steam Battlestaff.");
			}
			break;

		case 11924:
			int interfaceId6 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId6, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId6, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId6, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 11924, 150);

			player.getInterfaceState().setNextDialogueId(0, 11925);
			break;
		case 11925:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 11926);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11926:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(11924) && player.getInventory().contains(12802)) {
				player.getInventory().remove(new Item(11924));
				player.getInventory().remove(new Item(12802));
				player.getInventory().add(new Item(12806));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 12806, null, "You merge the two together to make an Malediction ward.");
				player.getActionSender().sendMessage("You merge the two together to make a Malediction ward.");
			}
			break;

		case 11927:
			int interfaceId7 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId7, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId7, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId7, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 11926, 150);

			player.getInterfaceState().setNextDialogueId(0, 11928);
			break;
		case 11928:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 11929);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11929:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(11926) && player.getInventory().contains(12802)) {
				player.getInventory().remove(new Item(11926));
				player.getInventory().remove(new Item(12802));
				player.getInventory().add(new Item(12807));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 12807, null, "You merge the two together to make an Odium ward.");
				player.getActionSender().sendMessage("You merge the two together to make a Odium ward.");
			}
			break;

		case 4587:
			int interfaceId8 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId8, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId8, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId8, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 4587, 150);

			player.getInterfaceState().setNextDialogueId(0, 4588);
			break;
		case 4588:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 4589);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 4589:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(4587) && player.getInventory().contains(20002)) {
				player.getInventory().remove(new Item(4587));
				player.getInventory().remove(new Item(20002));
				player.getInventory().add(new Item(20000));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 20000, null, "You merge the two together to make an Dragon Scimitar (or).");
				player.getActionSender().sendMessage("You merge the two together to make a Dragon Scimitar (or).");
			}
			break;

		case 19553:
			int interfaceId9 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId9, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId9, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId9, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 19553, 150);

			player.getInterfaceState().setNextDialogueId(0, 19554);
			break;
		case 19554:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 19555);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 19555:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(19553) && player.getInventory().contains(20062)) {
				player.getInventory().remove(new Item(19553));
				player.getInventory().remove(new Item(20062));
				player.getInventory().add(new Item(20366));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 20366, null, "You merge the two together to make an Amulet of Torture (or).");
				player.getActionSender().sendMessage("You merge the two together to make a Amulet of Torture (or).");
			}
			break;

		case 12002:
			int interfaceId10 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId10, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId10, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId10, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 12002, 150);

			player.getInterfaceState().setNextDialogueId(0, 12003);
			break;
		case 12003:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 12004);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 12004:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(12002) && player.getInventory().contains(20065)) {
				player.getInventory().remove(new Item(12002));
				player.getInventory().remove(new Item(20065));
				player.getInventory().add(new Item(19720));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 19720, null, "You merge the two together to make an Occult necklace (or).");
				player.getActionSender().sendMessage("You merge the two together to make a Occult necklace (or).");
			}
			break;

		case 11804:
			int interfaceId11 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId11, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId11, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId11, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 11804, 150);

			player.getInterfaceState().setNextDialogueId(0, 11805);
			break;
		case 11805:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 11806);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11806:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(11804) && player.getInventory().contains(20071)) {
				player.getInventory().remove(new Item(11804));
				player.getInventory().remove(new Item(20071));
				player.getInventory().add(new Item(20370));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 20370, null, "You merge the two together to make an Bandos godsword (or).");
				player.getActionSender().sendMessage("You merge the two together to make a Bandos godsword (or).");
			}
			break;

		case 11807:
			int interfaceId12 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId12, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId12, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId12, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 11806, 150);

			player.getInterfaceState().setNextDialogueId(0, 11808);
			break;
		case 11808:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 11809);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11809:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(11806) && player.getInventory().contains(20074)) {
				player.getInventory().remove(new Item(11806));
				player.getInventory().remove(new Item(20074));
				player.getInventory().add(new Item(20372));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 20372, null, "You merge the two together to make an Saradomin godsword (or).");
				player.getActionSender().sendMessage("You merge the two together to make a Saradomin godsword (or).");
			}
			break;

		case 11810:
			int interfaceId13 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId13, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId13, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId13, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 11806, 150);

			player.getInterfaceState().setNextDialogueId(0, 11811);
			break;
		case 11811:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 11812);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11812:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(11806) && player.getInventory().contains(20074)) {
				player.getInventory().remove(new Item(11806));
				player.getInventory().remove(new Item(20074));
				player.getInventory().add(new Item(20372));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 20372, null, "You merge the two together to make an Saradomin godsword (or).");
				player.getActionSender().sendMessage("You merge the two together to make a Saradomin godsword (or).");
			}
			break;

		case 11813:
			int interfaceId14 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId14, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId14, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId14, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 11808, 150);

			player.getInterfaceState().setNextDialogueId(0, 11814);
			break;
		case 11814:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 11815);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11815:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(11808) && player.getInventory().contains(20077)) {
				player.getInventory().remove(new Item(11808));
				player.getInventory().remove(new Item(20077));
				player.getInventory().add(new Item(20374));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 20374, null, "You merge the two together to make an Zamorak godsword (or).");
				player.getActionSender().sendMessage("You merge the two together to make a Zamorak godsword (or).");
			}
			break;

		case 11816:
			int interfaceId15 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId15, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId15, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId15, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 11802, 150);

			player.getInterfaceState().setNextDialogueId(0, 11817);
			break;
		case 11817:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 11818);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11818:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(11802) && player.getInventory().contains(20068)) {
				player.getInventory().remove(new Item(11802));
				player.getInventory().remove(new Item(20068));
				player.getInventory().add(new Item(20368));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 20368, null, "You merge the two together to make an Armadyl godsword (or).");
				player.getActionSender().sendMessage("You merge the two together to make a Armadyl godsword (or).");
			}
			break;

		case 11819:
			int interfaceId16 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId16, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId16, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId16, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 11235, 150);

			player.getInterfaceState().setNextDialogueId(0, 11820);
			break;
		case 11820:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 11821);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11821:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(11235) && player.getInventory().contains(12757)) {
				player.getInventory().remove(new Item(11235));
				player.getInventory().remove(new Item(12757));
				player.getInventory().add(new Item(12766));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 12766, null, "You merge the two together to make an Blue Dark bow.");
				player.getActionSender().sendMessage("You merge the two together to make a Blue Dark bow.");
			}
			break;

		case 11822:
			int interfaceId17 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId17, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId17, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId17, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 11235, 150);

			player.getInterfaceState().setNextDialogueId(0, 11823);
			break;
		case 11823:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 11824);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11824:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(11235) && player.getInventory().contains(12759)) {
				player.getInventory().remove(new Item(11235));
				player.getInventory().remove(new Item(12759));
				player.getInventory().add(new Item(12765));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 12765, null, "You merge the two together to make an Green Dark bow.");
				player.getActionSender().sendMessage("You merge the two together to make a Green Dark bow.");
			}
			break;

		case 11825:
			int interfaceId18 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId18, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId18, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId18, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 11235, 150);

			player.getInterfaceState().setNextDialogueId(0, 11826);
			break;
		case 11826:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 11827);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11827:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(11235) && player.getInventory().contains(12761)) {
				player.getInventory().remove(new Item(11235));
				player.getInventory().remove(new Item(12761));
				player.getInventory().add(new Item(12767));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 12767, null, "You merge the two together to make an Yellow Dark bow.");
				player.getActionSender().sendMessage("You merge the two together to make a Yellow Dark bow.");
			}
			break;

		case 11828:
			int interfaceId19 = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId19, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId19, 1, "Would you like to merge these items?");
			player.getActionSender().sendString(interfaceId19, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 11235, 150);

			player.getInterfaceState().setNextDialogueId(0, 11829);
			break;
		case 11829:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 11830);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11830:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(11235) && player.getInventory().contains(12763)) {
				player.getInventory().remove(new Item(11235));
				player.getInventory().remove(new Item(12763));
				player.getInventory().add(new Item(12768));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 12768, null, "You merge the two together to make an White Dark bow.");
				player.getActionSender().sendMessage("You merge the two together to make a White Dark bow.");
			}
			break;

		case 4151:
			int interfaceId = 193;

			player.getActionSender().sendInterface(162, 546, interfaceId, false);
			player.getActionSender().sendAccessMask(1, 193, 2, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 3, -1, -1);
			player.getActionSender().sendAccessMask(0, 193, 4, -1, -1);

			player.getActionSender().sendString(interfaceId, 1, "Would you like to merge these items? This is irreverisble.");
			player.getActionSender().sendString(interfaceId, 2, "Click here to continue.");
			player.getActionSender().sendItemOnInterface(193, 0, 4151, 150);

			player.getInterfaceState().setNextDialogueId(0, 4152);
			break;
		case 4152:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes i'd like to merge them.|No.");
			player.getInterfaceState().setNextDialogueId(0, 4153);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 4153:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(4151) && player.getInventory().contains(12004)) {
				player.getInventory().remove(new Item(4151));
				player.getInventory().remove(new Item(12004));
				player.getInventory().add(new Item(12006));
				player.getActionSender().sendDialogue("", ActionSender.DialogueType.MESSAGE_MODEL_LEFT, 12006, null, "You merge the two together to make an Abyssal Tentacle.");
				player.getActionSender().sendMessage("You merge the two together to make an Abyssal Tentacle.");
			}
			break;

		case 5074:
			player.getActionSender().sendDialogue("Kent", DialogueType.NPC, 5074, FacialAnimation.DEFAULT,
					"Hello, How can I help you?");
			player.getInterfaceState().setNextDialogueId(0, 5075);
			break;
		case 5075:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Who are you?|Can I claim my vote reward?|Nothing");
			player.getInterfaceState().setNextDialogueId(0, 5076);
			player.getInterfaceState().setNextDialogueId(1, 5077);
			player.getInterfaceState().setNextDialogueId(2, 5078);
			break;
		case 5076:
			player.getActionSender().sendDialogue("Kent", DialogueType.NPC, 5074, FacialAnimation.DEFAULT,
					"My name is Kent, Lord Clank has put me<br> in charge of handling vote rewards.");
			player.getInterfaceState().setNextDialogueId(0, 5075);
			break;
		case 5077:
			player.getActionSender().sendDialogue("Kent", DialogueType.NPC, 5074, FacialAnimation.DEFAULT,
					"Absolutely!");
			player.getInterfaceState().setNextDialogueId(0, 5079);
			break;
		case 5078:
			player.getActionSender().removeChatboxInterface();
			break;
		case 5079:// make that on login for player
			player.getActionSender().removeChatboxInterface();
			break;
		case 5080:
			player.getActionSender().sendDialogue("Kent", DialogueType.NPC, 5074, FacialAnimation.DEFAULT,
					"You don't seem to have any rewards waiting for you...");
			player.getInterfaceState().setNextDialogueId(0, 5078);
			break;//hey1 sec gotta help mom with groceries.

			/** Bob Barter Decanting **/
		case 5449:
			player.getActionSender().sendDialogue("Bob Barter", DialogueType.NPC, 5449, FacialAnimation.DEFAULT,
					"Hello how can I help you?");
			player.getInterfaceState().setNextDialogueId(0, 5450);
			break;
		case 5450:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Can you decant my potions?|Nothing");
			player.getInterfaceState().setNextDialogueId(0, 5451);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 5451:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"Can you decant my potions?");
			player.getInterfaceState().setNextDialogueId(0, 5452);
			break;
		case 5452:
			boolean donator = permissionService.isAny(player, PermissionServiceImpl.SPECIAL_PERMISSIONS);
			player.getActionSender().sendDialogue("Bob Barter", DialogueType.NPC, 5449, FacialAnimation.DEFAULT,
					donator ? "You seem to have special permissions and will<br>get my services for free." : "Sure at a price of 50,000 coins.");
			player.getInterfaceState().setNextDialogueId(0, 5453);
			break;
		case 5453:
			donator = permissionService.isAny(player, PermissionServiceImpl.SPECIAL_PERMISSIONS);
			if (!donator) {
				if (player.getInventory().getCount(995) < 50000) {
					player.getActionSender().sendMessage("You don't have enough coins to complete this action.");
					player.getActionSender().removeChatboxInterface();
				}
				player.getInventory().remove(new Item(995, 50000));
			}
			player.getActionSender().removeChatboxInterface();
			PotionDecanterService potionDecanterService = Server.getInjector().getInstance(PotionDecanterService.class);
			potionDecanterService.decantPotions(player);
			break;


		case 6599:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"Who are you and what is this place?");
			player.getInterfaceState().setNextDialogueId(0, 6600);
			break;
		case 6600:
			player.getActionSender().sendDialogue("Mandrith", DialogueType.NPC, 6599, FacialAnimation.DEFAULT,
					"My name is Mandrith.");
			player.getInterfaceState().setNextDialogueId(0, 6601);
			break;
		case 6601:
			player.getActionSender().sendDialogue("Mandrith", DialogueType.NPC, 6599, FacialAnimation.DEFAULT,
					"I collect valuable resources and pawn off access to them<br>to foolish adventurers, like yourself.");
			player.getInterfaceState().setNextDialogueId(0, 6602);
			break;
		case 6602:
			player.getActionSender().sendDialogue("Mandrith", DialogueType.NPC, 6599, FacialAnimation.DEFAULT,
					"You should take a look inside my arena. There's an<br> abundance of valuable resources inside.");
			player.getInterfaceState().setNextDialogueId(0, 6603);
			break;
		case 6603:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"And I can take whatever I want?");
			player.getInterfaceState().setNextDialogueId(0, 6604);
			break;
		case 6604:
			player.getActionSender().sendDialogue("Mandrith", DialogueType.NPC, 6599, FacialAnimation.DEFAULT,
					"It's all yours. All i ask is you pay the upfront fee of 30,000 coins.");
			player.getInterfaceState().setNextDialogueId(0, 6605);
			break;
		case 6605:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"Will others be able to kill me inside?");
			player.getInterfaceState().setNextDialogueId(0, 6606);
			break;
		case 6606:
			player.getActionSender().sendDialogue("Mandrith", DialogueType.NPC, 6599, FacialAnimation.DEFAULT,
					"Yes. These walls will only hold them back for so long.");
			player.getInterfaceState().setNextDialogueId(0, 6607);
			break;
		case 6607:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"You'll endGame them though, right?");
			player.getInterfaceState().setNextDialogueId(0, 6608);
			break;
		case 6608:
			player.getActionSender().sendDialogue("Mandrith", DialogueType.NPC, 6599, FacialAnimation.DEFAULT,
					"Haha! For the right price, I won't deny any one access<br>to my arena. Even if their intention is to kill you.");
			player.getInterfaceState().setNextDialogueId(0, 6609);
			break;
		case 6609:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"Right...");
			player.getInterfaceState().setNextDialogueId(0, 6610);
			break;
		case 6610:
			player.getActionSender().sendDialogue("Mandrith", DialogueType.NPC, 6599, FacialAnimation.DEFAULT,
					"My arena holds many treasures that I've acquired at<br>great expense, adventurer. Their bounty can come at a<br>price.");
			player.getInterfaceState().setNextDialogueId(0, 6611);
			break;
		case 6611:
			player.getActionSender().sendDialogue("Mandrith", DialogueType.NPC, 6599, FacialAnimation.DEFAULT,
					"One day, adventurer, I will boast ownership of a much<br>larger, much more dangerous arena than this. Take<br>advantage of this offer while it lasts.");
			player.getInterfaceState().setNextDialogueId(0, -1);
			break;

		case 6481:
			player.getActionSender().sendDialogue("Mac ", DialogueType.NPC, 6481, FacialAnimation.DEFAULT,
					"Hello, how can I help you?");
			player.getInterfaceState().setNextDialogueId(0, 6482);
			break;
		case 6482:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Can I get a Max Cape?|Nothing.");
			player.getInterfaceState().setNextDialogueId(0, 6483);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 6483:
			int totalLevel = player.getSkills().getTotalLevel();
			String text = totalLevel >= Constants.MAX_LEVEL ? "Sure you seem to be a master of all skills, It'll cost 2m is that alright?" : "Sorry, please come back when you're a master of all skills";
			int nextDialogue = totalLevel >= Constants.MAX_LEVEL ? 6484 : -1;
			player.getActionSender().sendDialogue("Mac ", DialogueType.NPC, 6481, FacialAnimation.DEFAULT,
					text);
			player.getInterfaceState().setNextDialogueId(0, nextDialogue);
			break;
		case 6484:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No.");
			player.getInterfaceState().setNextDialogueId(0, 6485);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 6485:
			player.getActionSender().removeChatboxInterface();
			hasGold = player.getInventory().getCount(995) >= 2000000;
			if (hasGold) {
				if (player.getInventory().add(new Item(13280)) && player.getInventory().add(new Item(13281))) {
					player.getInventory().remove(new Item(995, 2000000));
					player.getActionSender().sendMessage("You pay 2.5m for the Max Cape and hood.");
				}
			} else {
				player.getActionSender().sendMessage("You don't have enough gold to purchase the Max cape and hood.");
			}
			break;
			/**
			 * Emblem trader bullshit
			 */
		case 10000:
			player.getActionSender().removeChatboxInterface();
			int pointsPrior = player.getDatabaseEntity().getBountyHunter().getBountyShopPoints();
			for (Item inv : player.getInventory().toArray()) {
				if (inv == null) {
					continue;
				}
				BountyHunterService.Emblems.of(inv.getId()).ifPresent(e -> {
					player.getInventory().remove(inv);
					int points = player.getDatabaseEntity().getBountyHunter().getBountyShopPoints();
					player.getDatabaseEntity().getBountyHunter().setBountyShopPoints(e.getCost() + points);
				});
			}
			int addedPoints = player.getDatabaseEntity().getBountyHunter().getBountyShopPoints() - pointsPrior;
			player.getActionSender().sendMessage("You sell all your emblems for " + NumberFormat.getNumberInstance(Locale.ENGLISH).format(addedPoints) + " Bounties.");
			break;

		case 11864:
			player.getActionSender().sendDialogue("Vannaka", DialogueType.NPC, 403, FacialAnimation.DEFAULT,
					"Would you like to upgrade your Slayer helm for 250 Slayer Points?");
			player.getInterfaceState().setNextDialogueId(0, 11865);
			break;
		case 11865:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No.");
			player.getInterfaceState().setNextDialogueId(0, 11866);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 11866:
			int points = player.getDatabaseEntity().getStatistics().getSlayerRewardPoints();
			if (points < 250) {
				player.getActionSender().sendDialogue("Vannaka", DialogueType.NPC, 403, FacialAnimation.DEFAULT,
						"You don't have enough points to complete this.");
				player.getInterfaceState().setNextDialogueId(0, -1);
			} else {
				player.getActionSender().removeChatboxInterface();
				if (player.getInventory().hasItem(new Item(11864))) {
					player.getInventory().remove(new Item(11864));
					player.getInventory().add(new Item(11865));
					player.getDatabaseEntity().getStatistics().setSlayerRewardPoints(points - 250);
					player.getActionSender().sendMessage("You upgrade your Slayer helm for 250 Slayer reward points.");
				}
			}
			break;

		case 11941:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"One|Five|All");
			player.getInterfaceState().setNextDialogueId(0, 11942);
			player.getInterfaceState().setNextDialogueId(1, 11943);
			player.getInterfaceState().setNextDialogueId(2, 11944);
			break;
		case 11942:
			Item one = player.getInterfaceAttribute("lootingBagItem");
			int oneIndex = player.getInterfaceAttribute("lootingBagIndex");
			LootingBagService lootingBagService = Server.getInjector().getInstance(LootingBagService.class);
			if (one != null && oneIndex != -1) {
				lootingBagService.deposit(player, oneIndex, one.getId(), 1);
			}
			break;
		case 11943:
			Item five = player.getInterfaceAttribute("lootingBagItem");
			int fiveIndex = player.getInterfaceAttribute("lootingBagIndex");
			lootingBagService = Server.getInjector().getInstance(LootingBagService.class);
			if (five != null && fiveIndex != -1) {
				lootingBagService.deposit(player, fiveIndex, five.getId(), 5);
			}
			break;
		case 11944:
			Item all = player.getInterfaceAttribute("lootingBagItem");
			int allIndex = player.getInterfaceAttribute("lootingBagIndex");
			lootingBagService = Server.getInjector().getInstance(LootingBagService.class);
			if (all != null && allIndex != -1) {
				lootingBagService.deposit(player, allIndex, all.getId(), player.getInventory().getCount(all.getId()));
			}
			break;


		case 12020:
			Map<GemBagService.Gems, Integer> gemBag = player.getDatabaseEntity().getGemBag();
			if (gemBag.size() <= 0) {
				return;
			}
			List<GemBagService.Gems> gems = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			for (Map.Entry pair : gemBag.entrySet()) {
				GemBagService.Gems key = (GemBagService.Gems) pair.getKey();
				int value = (int) pair.getValue();
				sb.append(key.getName()).append(" (").append(value).append(")|");
				gems.add(key);
			}
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					sb.toString());
			for (int i = 0; i < gems.size(); i++) {
				player.getInterfaceState().setNextDialogueId(i, 12021 + i);
				player.setInterfaceAttribute("gemBagType" + i, gems.get(i));
			}
			break;
		case 12021:
			player.getActionSender().removeChatboxInterface();
			GemBagService.Gems gemOne = player.getInterfaceAttribute("gemBagType0");
			if (gemOne != null) {
				gemBagService.withdraw(player, gemOne);
			}
			break;
		case 12022:
			player.getActionSender().removeChatboxInterface();
			GemBagService.Gems gemTwo = player.getInterfaceAttribute("gemBagType1");
			if (gemTwo != null) {
				gemBagService.withdraw(player, gemTwo);
			}
			break;
		case 12023:
			player.getActionSender().removeChatboxInterface();
			GemBagService.Gems gemThree = player.getInterfaceAttribute("gemBagType2");
			if (gemThree != null) {
				gemBagService.withdraw(player, gemThree);
			}
			break;
		case 12024:
			player.getActionSender().removeChatboxInterface();
			GemBagService.Gems gemFour = player.getInterfaceAttribute("gemBagType3");
			if (gemFour != null) {
				gemBagService.withdraw(player, gemFour);
			}
			break;
		case 12025:
			player.getActionSender().removeChatboxInterface();
			GemBagService.Gems gemFive = player.getInterfaceAttribute("gemBagType4");
			if (gemFive != null) {
				gemBagService.withdraw(player, gemFive);
			}
			break;


		case 12929:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT, "If I do this i'll lose my mutagen.... Should I continue?");
			player.getInterfaceState().setNextDialogueId(0, 12930);
			break;
		case 12930:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No.");
			player.getInterfaceState().setNextDialogueId(0, 12931);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 12931:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(13196)) {
				if (player.getInventory().add(new Item(12929))) {
					player.getInventory().remove(new Item(13196));
				}
			}
			break;

		case 12932:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT, "If I do this i'll lose my mutagen.... Should I continue?");
			player.getInterfaceState().setNextDialogueId(0, 12933);
			break;
		case 12933:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No.");
			player.getInterfaceState().setNextDialogueId(0, 12934);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		case 12934:
			player.getActionSender().removeChatboxInterface();
			if (player.getInventory().contains(13198)) {
				if (player.getInventory().add(new Item(12929))) {
					player.getInventory().remove(new Item(13198));
				}
			}
			break;

		case 13190:

			player.getActionSender().sendInterface(162, 546, 193, false)
			.sendAccessMask(1, 193, 2, -1, -1)
			.sendAccessMask(0, 193, 3, -1, -1)
			.sendAccessMask(0, 193, 4, -1, -1)

			.sendString(193, 1, "Are you sure you want to redeem this for premium status?")
			.sendString(193, 2, "Click here to continue.")
			.sendItemOnInterface(193, 0, 13190, 150);
			player.getInterfaceState().setNextDialogueId(0, 13191);
			break;
		case 13191:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No.");
			player.getInterfaceState().setNextDialogueId(0, 13192);
			player.getInterfaceState().setNextDialogueId(1, -1);
			break;
		/*case 13192:
			player.getActionSender().removeChatboxInterface();
			try {
				if (player.getInventory().contains(13190)) {
					player.getInventory().remove(new Item(13190));
					DonationManager.execute(player);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;*/
		case 13193:
			if (!player.hasAttribute("otherPlayer")) {
				player.getActionSender().removeChatboxInterface();
				return;
			}
			Player other = player.getAttribute("otherPlayer");
			player.getActionSender().sendInterface(162, 546, 193, false)
			.sendAccessMask(1, 193, 2, -1, -1)
			.sendAccessMask(0, 193, 3, -1, -1)
			.sendAccessMask(0, 193, 4, -1, -1)

			.sendString(193, 1, "Are you sure you want to redeem this premium status for " + other.getName() + "?")
			.sendString(193, 2, "Click here to continue.")
			.sendItemOnInterface(193, 0, 13190, 150);
			player.getInterfaceState().setNextDialogueId(0, 13194);
			break;
		case 13194:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes|No.");
			player.getInterfaceState().setNextDialogueId(0, 13195);
			player.getInterfaceState().setNextDialogueId(1, 13196);
			break;
		/*case 13195:
			if (!player.hasAttribute("otherPlayer")) {
				player.getActionSender().removeChatboxInterface();
				return;
			}
			other = player.getAttribute("otherPlayer");
			player.getActionSender().removeChatboxInterface();
			try {
				if (player.getInventory().contains(13190)) {
					player.getInventory().remove(new Item(13190));
					player.getActionSender().sendMessage("Successfully gave " + other.getName() + " premium status.");
					DonationManager.execute(other);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			player.removeAttribute("otherPlayer");
			break;*/
		case 13196:
			player.getActionSender().removeChatboxInterface();
			player.removeAttribute("otherPlayer");
			break;

			/**
			 * Teleport options
			 */

		case 25500:
			player.getActionSender().sendDialogue("ProjectV Teleports", ActionSender.DialogueType.TELEPORT_INTERFACE, -1, null, "Varrock|Edgeville|Rock crabs|Barrows");
			//    			player.getInterfaceState().setNextDialogueId(0, 15001);//15001
			//                player.getInterfaceState().setNextDialogueId(1, 15002);//15002
			//                player.getInterfaceState().setNextDialogueId(2, -1);//15003
			//                player.getInterfaceState().setNextDialogueId(3, -1);//15004
			//                player.getInterfaceState().setNextDialogueId(4, -1);//15005
			break;

		case 15001:
			player.setTeleportTarget(Location.create(3213, 3424, 0));
			player.getActionSender().closeAll();
			break;

		case 15003:
			player.setTeleportTarget(Location.create(2757, 3477, 0));
			player.getActionSender().closeAll();
			break;

		case 15002:
			player.setTeleportTarget(Location.create(3088, 3489, 0));
			player.getActionSender().closeAll();
			break;

		case 15004:
			player.setTeleportTarget(Location.create(3088, 3502, 0));
			player.getActionSender().closeAll();
			break;

		case 15005:
			player.setTeleportTarget(Location.create(3565, 3316, 0));
			player.getActionSender().closeAll();
			break;


			/**
			 * World Switcher (Widget 69)
			 */

		case 6969:
			player.getActionSender().sendDialogue("", ActionSender.DialogueType.WORLD_SWITCHER, -1, null, "");//
			break;

		}
	}

	public static void advanceDialogue(Player player, int index) {
        int dialogueId = player.getInterfaceState().getNextDialogueId(index);
        if (dialogueId == -1) {
            player.getActionSender().removeChatboxInterface();
            return;
        }
        openDialogue(player, dialogueId);
    }

	public static int getSkillId(int npcId) {
		switch (npcId) {
		case 2460:
			return Skills.ATTACK;
		case 3216:
			return Skills.DEFENCE;
		case 2473:
			return Skills.STRENGTH;
		case 2578:
			return Skills.PRAYER;
		case 6059:
			return Skills.RANGE;
		case 2658:
			return Skills.COOKING;
		case 1044:
			return Skills.FLETCHING;
		case 118:
			return Skills.FIREMAKING;
		case 5045:
			return Skills.HERBLORE;
		case 3193:
			return Skills.THIEVING;
		case 5810:
			return Skills.CRAFTING;
		case 2913:
			return Skills.FISHING;
		case 3249:
			return Skills.MAGIC;
		case 3343:
			return Skills.HITPOINTS;
		case 4733:
			return Skills.SMITHING;
		case 3226:
			return Skills.WOODCUTTING;
		case 405:
			return Skills.SLAYER;
		case 3363:
			return Skills.MINING;
		case 5382:
			return Skills.FARMING;
		case 637:
			return Skills.RUNECRAFTING;
		}
		return 0;
	}

}