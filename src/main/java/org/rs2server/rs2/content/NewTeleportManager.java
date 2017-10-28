/*package org.rs2server.rs2.content;

import org.rs2server.rs2.model.Location;
import org.rs2server.rs2.model.bit.component.Access;
import org.rs2server.rs2.model.bit.component.AccessBits;
import org.rs2server.rs2.model.bit.component.NumberRange;
import org.rs2server.rs2.model.player.Player;

*//**
 * @author Lifes
 * @since 01/13/2017
 *//*
public class NewTeleportManager {
	*//**
	 * @author Lifes
	 * Sends the Interface/CS2Script/Access Mask to the entity.
	 *//*
	public static void handleNTeleport(Player player) {
		player.getActionSender().sendInterface(187, false);
		player.getActionSender().sendCS2Script(217, new Object[] {"||||||||<col=ff><u=000000>Cunt Teleports|Pussy Crabs|Yaks|Experiments|Hill Giants|Sand Crabs|<col=ff><u=000000>Dungeon Teleports|Edgeville Dungeon|Taverly Dungeon|Brimhaven Dungeon|Fremennik Slayer Dungeon|Stronghold Slayer Cave|Asgarnia Ice Dungeon|Waterfall Dungeon|Mos'le Harmless|Kourend Catacombs|Crashsite Caverns|Ancient Caverns|<col=ff><u=000000>Minigame Teleports|Barrows|Pest Control|Warriors Guild|Duel Arena|Fight Caves|Wintertodt|<col=ff><u=000000>Skilling Teleports|Woodcutting Guild|Catherby Pier|Piscatoris Fishing Colony|Fishing Guild|Varrock Smithing|Varrock Mining|Mining Guild|Falador Mining|Motherlode Mines|Puro Puro|Gnome Agility Course<col=880000>(Lvl 1 Agility)|Draynor Rooftop Course<col=880000>(Lvl 10 Agility)|Varrock Rooftop Course<col=880000>(Lvl 30 Agility)|Barbarian Agility Course<col=880000>(Lvl 35 Agility)|Seer's Rooftop Course<col=880000>(Lvl 60 Agility)|Ardougne Rooftop Course<col=880000>(Lvl 90 Agility)|Ardougne Market<col=880000>(Level 1 Thieving)|Rogues Den<col=880000>(Level 50 Thieving)|<col=ff><u=000000>Boss Teleports|Kalphite Queen|King Black Dragon<col=880000>(Lvl 42 wildy)|Corporeal Beast|Abyssal Sire|Godwars|Lizardman Shaman|Zulrah|Dagannoth Kings|Giant Mole|Raids|<col=880000><u=000000>Wilderness Teleports|Mage Bank<col=48f442>(Safe)|Lava Dragons<col=880000>(Lvl 43 Wildy)|PvP Castle<col=e58106>(Lvl 15 Wildy)|Wilderness Resource Dungeon<col=880000>(Lvl 55 Wildy)|West Dragons<col=e58106>(Lvl 10 Wildy)|East Dragons<col=e58106>(Lvl 19 Wildy)|Edgeville Wildy", "<img=1>ProjectV Teleports<img=1>", 0}, "Iss");//Iss
		player.sendAccess(Access.of(187, 3, NumberRange.of(0, 170), AccessBits.CLICK_CONTINUE));
	}
	
	*//**
	 * @author Lifes
	 * Handles the Options for the Teleports
	 * Do not use Options 0 -> 5; Using those Options will break the Dialogue system.
	 *//*
	public static boolean handleNTeleportAction(Player player, int option) {
		switch (option) {
		case 92://Monster Teleports Option
			player.teleport(Location.create(3333, 3333, 0), 0, 0, false);//rock crabs
			player.getActionSender().closeAll();
			break;
		}
		return false;
	}

}
*/