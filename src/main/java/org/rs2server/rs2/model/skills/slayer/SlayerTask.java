package org.rs2server.rs2.model.skills.slayer;

import java.util.Arrays;

import org.rs2server.rs2.Constants;

public class SlayerTask {

	/**
	 * The slayer task groups.
	 * Each group has a unique id which is assigned by Jagex for the Slayer rewards interface,
	 * therefore these ids SHOULD NOT BE TOUCHED.
	 */
	public enum TaskGroup {
		GOBLINS(2),
		COW(6),
		BATS(8),
		WOLVES(9),
		ZOMBIES(10),
		SKELETONS(11),
		HILL_GIANTS(14),
		FIRE_GIANTS(16),
		MOSS_GIANTS(17),
		GREEN_DRAGONS(24),
		BLACK_DRAGONS(27),
		LESSER_DEMONS(28),
		GREATER_DEMONS(29),
		BLACK_DEMONS(30),
		HELLHOUNDS(31),
		TUROTHS(36),
		CAVE_CRAWLERS(37),
		CRAWLING_HANDS(39),
		ABERRANT_SPECTRE(41),
		ABYSSAL_DEMONS(42),
		BASILISKS(43),
		COCKATRICE(44),
		KURASKS(45),
		GARGOYLES(46),
		PYREFIENDS(47),
		BLOODVELDS(48),
		DUST_DEVILS(49),
		JELLIES(50),
		NECHRYAEL(52),
		BRONZE_DRAGONS(58),
		IRON_DRAGONS(59),
		STEEL_DRAGONS(60),
		CAVE_SLIMES(62),
		CAVE_BUG(63),
		DARK_BEASTS(66),
		DESERT_LIZARD(68),
		SKELETAL_WYVERN(72),
		MINOTAURS(76),
		ANKOUS(79),
		CAVE_HORRORS(80),
		SPIRITUAL_CREATURES(89),
		CAVE_KRAKEN(92),
		AVIANSES(94),
		SMOKE_DEVIL(95),
		TZHAARS(96),
		BOSSES(98),
		JAD(97),
		CREATURES(99),
		BLUE_DRAGON(25),
		BANSHEE(38),
		SUQAHS(83),
		DAGS(35);

		private int id;

		TaskGroup(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public static TaskGroup forName(String taskGroup) {
			if (taskGroup == null) {
				return null;
			}
			return Arrays.stream(TaskGroup.values()).filter(g -> g.name().equals(taskGroup)).findFirst().get();
		}
	}

	public enum Master {
		TURAEL(401, 5,//starter
				new Object[][]{
				{"Chicken", 1, 25, 50, 110.0, TaskGroup.WOLVES},
				{"Giant bat", 1, 25, 50, 110.0, TaskGroup.BATS},
				{"Rock crab", 1, 10, 45, 110.0, TaskGroup.CREATURES},
				{"Goblin", 1, 10, 45, 110.0, TaskGroup.GOBLINS},
				{"Bear", 1, 10, 40, 110.0, TaskGroup.CREATURES},
				{"Cave bug", 7, 10, 40, 110.0, TaskGroup.CAVE_BUG},
				{"Scorpion", 1, 10, 40, 110.0, TaskGroup.CREATURES},
				{"Skeleton", 1, 10, 40, 110.0, TaskGroup.SKELETONS},
				{"Zombie", 1, 10, 40, 110.0, TaskGroup.ZOMBIES},
				{"Cave crawler", 10, 35, 75, 111.0, TaskGroup.CAVE_CRAWLERS},
				{"Cave slime", 17, 10, 40, 110.0, TaskGroup.CAVE_SLIMES},
				{"Cow", 1, 10, 40, 110.0, TaskGroup.COW},
				{"Crawling hand", 5, 10, 60, 110.0, TaskGroup.CRAWLING_HANDS},
				{"Desert lizard", 22, 10, 40, 110.0, TaskGroup.DESERT_LIZARD},
				{"Minotaur", 1, 10, 40, 110.0, TaskGroup.MINOTAURS},
				{"Dwarf", 1, 10, 40, 110.0, TaskGroup.CREATURES},
				}),
		VANNAKA(403, 8,//easy
				new Object[][]{
						{"Ankou", 1, 25, 50, 460.0, TaskGroup.ANKOUS},
						{"Banshee", 15, 10, 40, 12.0, TaskGroup.BANSHEE},
						{"Blue dragon", 2, 10, 40, 13.0, TaskGroup.BLUE_DRAGON},
						{"Goblin", 1, 10, 45, 8.0, TaskGroup.GOBLINS},
						{"Rock crab", 1, 10, 45, 10.0, TaskGroup.CREATURES},
						{"Crawling hand", 5, 10, 45, 15.0, TaskGroup.CRAWLING_HANDS},
						{"Hill giant", 20, 15, 40, 17.0, TaskGroup.HILL_GIANTS},
						{"Cave crawler", 10, 35, 75, 11.0, TaskGroup.CAVE_CRAWLERS},
						{"Cave slime", 17, 10, 40, 9.0, TaskGroup.CAVE_SLIMES},
						{"Lesser demon", 30, 25, 45, 22.0, TaskGroup.LESSER_DEMONS},
						{"Cave crawler", 5, 35, 75, 2.0, TaskGroup.CAVE_CRAWLERS},
						{"Cockatrice", 25, 50, 70, 19.0, TaskGroup.COCKATRICE},
						{"Green dragon", 35, 35, 50, 25.0, TaskGroup.GREEN_DRAGONS},
						{"Moss giant", 20, 30, 60, 23.0, TaskGroup.MOSS_GIANTS},
						{"Fire giant", 20, 40, 85, 52.0, TaskGroup.FIRE_GIANTS},
						}),
		CHAELDAR(404, 12,//medium
				new Object[][]{
						{"Hill giant", 20, 15, 60, 20.0, TaskGroup.HILL_GIANTS},
						{"Lizardman", 1, 25, 60, 35.0, TaskGroup.CREATURES},
						{"Lesser demon", 30, 25, 60, 85.0, TaskGroup.LESSER_DEMONS},
						{"Cockatrice", 25, 50, 175, 37.0, TaskGroup.COCKATRICE},
						{"Green dragon", 35, 35, 200, 40.0, TaskGroup.GREEN_DRAGONS},
						{"Blue dragon", 35, 35, 200, 200.0, TaskGroup.BLUE_DRAGON},
						{"Fire giant", 62, 40, 120, 120.0, TaskGroup.FIRE_GIANTS},
						{"Moss giant", 35, 30, 100, 110.0, TaskGroup.MOSS_GIANTS},
						{"Bronze dragon", 50, 30, 70, 125.0, TaskGroup.BRONZE_DRAGONS},
						{"Iron dragon", 65, 30, 70, 173.2, TaskGroup.IRON_DRAGONS},
						{"Steel dragon", 79, 30, 70, 220.4, TaskGroup.STEEL_DRAGONS},
						{"Black dragon", 75, 30, 100, 119.4, TaskGroup.BLACK_DRAGONS},
						{"Abyssal demons", 85, 60, 130, 760.0, TaskGroup.ABYSSAL_DEMONS},
						{"TzHaar-Ket", 40, 30, 80, 140.0, TaskGroup.TZHAARS},
						{"TzHaar-Xil", 40, 30, 80, 125.0, TaskGroup.TZHAARS},
						{"Banshee", 15, 30, 80, 45.0, TaskGroup.BANSHEE},
						{"Bloodveld", 50, 60, 90, 120.0, TaskGroup.BLOODVELDS},
						{"Gargoyle", 75, 70, 100, 105.0, TaskGroup.GARGOYLES},
						{"Hellhound", 50, 50, 100, 560.0, TaskGroup.HELLHOUNDS},
						{"Turoth", 55, 30, 80, 79.0, TaskGroup.TUROTHS},
						{"Kurask", 70, 40, 100, 97.0, TaskGroup.KURASKS},
						{"Jelly", 52, 30, 80, 75.0, TaskGroup.JELLIES},
						{"Pyrefiend", 45, 30, 100, 45.0, TaskGroup.PYREFIENDS},
						{"Basilisk", 40, 50, 90, 75.0, TaskGroup.BASILISKS}}),
//Nieve = 490
		NIEVE(490, 30,//boss
				new Object[][]{
			{"General Graardor", 90, 20, 45, 2250.4, TaskGroup.BOSSES},
			{"Chaos Fanatic", 1, 20, 45, 840.4, TaskGroup.BOSSES},
			{"Dagannoth Prime", 90, 20, 45, 540.4, TaskGroup.BOSSES},
			{"Dagannoth Rex", 90, 20, 45, 540.4, TaskGroup.BOSSES},
			{"Dagannoth Supreme", 90, 20, 45, 540.4, TaskGroup.BOSSES},
			{"K'ril Tsutsaroth", 90, 20, 45, 540.4, TaskGroup.BOSSES},
			{"Kraken", 87, 20, 45, 600.4, TaskGroup.BOSSES},
			{"Kree'arra", 90, 20, 45, 2250.4, TaskGroup.BOSSES},
			{"Demonic gorilla", 1, 20, 45, 650.4, TaskGroup.BOSSES},
			{"Zulrah", 90, 20, 45, 1240, TaskGroup.BOSSES},
			{"Commander Zilyana", 90, 20, 45, 2250.4, TaskGroup.BOSSES},
			{"Thermonuclear smoke devil", 93, 20, 45, 420.4, TaskGroup.BOSSES},
			{"Chaos Elemental", 90, 20, 45, 840.4, TaskGroup.BOSSES},
			{"King Black Dragon", 90, 20, 45, 450.4, TaskGroup.BOSSES},
			{"TzTok-Jad", 90, 2, 3, 2250, TaskGroup.BOSSES},
			{"Cerberus", 91, 20, 45, 225.4, TaskGroup.BOSSES},
				}),
		
		STEVE(6798, 25,
				new Object[][] {
		     {"Abyssal demon", 85, 50, 150, 570, TaskGroup.ABYSSAL_DEMONS},
		     {"Aberrant spectre", 60, 120, 185, 350, TaskGroup.ABERRANT_SPECTRE},
		     {"Ankou", 1, 120, 185, 460, TaskGroup.CREATURES},
		     {"Aviansie", 1, 50, 89, 75, TaskGroup.AVIANSES},
		     {"Black demon", 1, 120, 185, 350, TaskGroup.BLACK_DEMONS},
		     {"Black dragon", 1, 10, 40, 75, TaskGroup.BLACK_DRAGONS},
		     {"Bloodveld", 50, 120, 185, 320, TaskGroup.BLOODVELDS},
		     {"Blue dragon", 1, 120, 185, 320, TaskGroup.BLUE_DRAGON},
		     {"Cave horror", 55, 100, 185, 240, TaskGroup.CAVE_HORRORS},
		     {"Cave kraken", 87, 100, 125, 370, TaskGroup.CAVE_KRAKEN},
		     {"Dagannoth", 1, 120, 185, 210, TaskGroup.DAGS},
		     {"Dark beast", 90, 10, 20, 420, TaskGroup.DARK_BEASTS},
		     {"Dust devils", 65, 120, 185, 210, TaskGroup.DUST_DEVILS},
		     {"Elves", 1, 60, 90, 75, TaskGroup.CREATURES},
		     {"Fire giants", 1, 120, 190, 110, TaskGroup.FIRE_GIANTS},
		     {"Gargoyle", 75, 120, 185, 160, TaskGroup.GARGOYLES},
		     {"Greator demon", 1, 120, 185, 420, TaskGroup.CREATURES},
		     {"Hellhound", 1, 120, 185, 320, TaskGroup.CREATURES},
		     {"Iron dragons", 1, 25, 60, 75, TaskGroup.IRON_DRAGONS},
		     {"Kalphite", 1, 120, 185, 75, TaskGroup.CREATURES},
		     {"Kurask", 70, 120, 185, 75, TaskGroup.KURASKS},
		     {"Mithril dragon", 1, 4, 9, 75, TaskGroup.CREATURES},
		     {"Nechryael", 80, 120, 185, 400, TaskGroup.NECHRYAEL},
		     {"Red dragon", 1, 30, 80, 75, TaskGroup.CREATURES},
		     {"Skeletal wyvern", 72, 3, 70, 420, TaskGroup.SKELETAL_WYVERN},
		     {"Smoke devil", 93, 110, 185, 75, TaskGroup.SMOKE_DEVIL},
		     {"Spiritual warrior", 63, 30, 100, 75, TaskGroup.SPIRITUAL_CREATURES},
		     {"Spiritual ranger", 63, 30, 100, 75, TaskGroup.SPIRITUAL_CREATURES},
		     {"Spiritual mage", 83, 30, 100, 75, TaskGroup.SPIRITUAL_CREATURES},
		     {"Steel dragons", 1, 30, 60, 75, TaskGroup.CREATURES},
		     {"Suqah", 1, 130, 185, 320, TaskGroup.SUQAHS},
		     {"Troll", 1, 120, 185, 75, TaskGroup.CREATURES},
		     {"Turoth", 55, 120, 185, 75, TaskGroup.TUROTHS},
		     {"TzHaar-ket", 1, 30, 100, 75, TaskGroup.CREATURES},
		     {"TzHaar-xil", 1, 30, 100, 75, TaskGroup.CREATURES},
		     {"TzHaar-mej", 1, 30, 100, 75, TaskGroup.CREATURES},
		     {"TzHaar-hur", 1, 30, 100, 75, TaskGroup.CREATURES},

		}),
		MAZCHNA(402, 8,//easy
				new Object[][] {
			{"Chicken", 1, 25, 50, 6.0, TaskGroup.CREATURES},
			{"Giant bat", 1, 25, 50, 8.0, TaskGroup.BATS},
			{"Rock crab", 1, 10, 45, 10.0, TaskGroup.CREATURES},
			{"Goblin", 1, 10, 45, 6.0, TaskGroup.GOBLINS},
			{"Bear", 1, 10, 40, 6.0, TaskGroup.CREATURES},
			{"Cave bug", 7, 10, 40, 10.0, TaskGroup.CAVE_BUG},
			{"Scorpion", 1, 10, 40, 8.0, TaskGroup.CREATURES},
			{"Skeleton", 1, 10, 40, 8.0, TaskGroup.SKELETONS},
			{"Zombie", 1, 10, 40, 8.0, TaskGroup.ZOMBIES},
			{"Cave crawler", 10, 35, 75, 11.0, TaskGroup.CAVE_CRAWLERS},
			{"Cave slime", 17, 10, 40, 10.0, TaskGroup.CAVE_SLIMES},
			{"Cow", 1, 10, 40, 5.0, TaskGroup.COW},
			{"Crawling hand", 5, 10, 60, 9.0, TaskGroup.CRAWLING_HANDS},
			{"Desert lizard", 22, 10, 40, 9.0, TaskGroup.DESERT_LIZARD},
			{"Minotaur", 1, 10, 40, 10.0, TaskGroup.MINOTAURS},
			{"Dwarf", 1, 10, 40, 7.0, TaskGroup.CREATURES},
				}),
//ID, slay lvl, min amount, max amount, E75, group
		
		DURADEL(405, 27,//elite
				new Object[][] { 
				{ "Bloodveld", 50, 60, 100, 320 },
                { "Gargoyle", 75, 70, 100, 160 },
				{ "Dark beast", 70, 90, 130, 420.4 },
				{ "Greater demon", 1, 120, 90, 420 },
				{ "Hellhound", 1, 50, 100, 320 },
				{ "Aquanites", 78, 50, 120, 125 }, 
				{ "Turoth", 55, 30, 180, 129 },
				{ "Kurask", 70, 40, 100, 107 }, 
				{ "Jelly", 52, 30, 110, 115 },
				{ "Pyrefiend", 45, 30, 100, 45 },
				{ "Basilisk", 40, 50, 120, 75 },
				{ "Abyssal demons", 85, 60, 130, 570 },
				{"Fire giant", 62, 40, 120, 110.0, TaskGroup.FIRE_GIANTS},
				{"Moss giant", 35, 30, 100, 120.0, TaskGroup.MOSS_GIANTS},
				{"Bronze dragon", 50, 30, 100, 125.0, TaskGroup.BRONZE_DRAGONS},
				{"Iron dragon", 65, 30, 80, 173.2, TaskGroup.IRON_DRAGONS},
				{"Steel dragon", 79, 30, 70, 220.4, TaskGroup.STEEL_DRAGONS},
				{"Black dragon", 75, 60, 100, 420.4, TaskGroup.BLACK_DEMONS},
				{"Abyssal demon", 85, 60, 130, 570.0, TaskGroup.ABYSSAL_DEMONS},
				{"Hellhound", 50, 50, 100, 116.0, TaskGroup.HELLHOUNDS},
				{"Greater demon", 50, 120, 100, 320.0, TaskGroup.GREATER_DEMONS},
				{"Nechryael", 80, 50, 100, 400.0, TaskGroup.NECHRYAEL},
				{"Gargoyle", 75, 70, 100, 105.0, TaskGroup.GARGOYLES},
				{"Ankou", 1, 70, 100, 360.0, TaskGroup.ANKOUS},
				{"Cave horror", 58, 50, 120, 240.0, TaskGroup.CAVE_HORRORS},
				{"Smoke devil", 93, 50, 120, 75.0, TaskGroup.SMOKE_DEVIL},
                {"Dark beast", 90, 60, 130, 420.4, TaskGroup.DARK_BEASTS},
				{"Skeletal wyvern", 72, 60, 420, 225.4, TaskGroup.SKELETAL_WYVERN},
				{"Kraken", 87, 60, 130, 225.4, TaskGroup.CAVE_KRAKEN},
				{"Suqahs", 85, 30, 90, 215.8, TaskGroup.SUQAHS},
				{"Aberrant spectre", 60, 50, 100, 90 },
				{"Spiritual mage", 83, 60, 100, 88 },
				}),
		
		SUMONA(7780, 20, new Object[][] { 
				{ "Bat", 1, 25, 50, 8.0 },
				{ "Goblin", 1, 10, 45, 10.0 },
				{ "Crawling hand", 1, 10, 60, 10.0 },
				{ "Cave crawler", 5, 35, 75, 22.0 },
				{ "Cockatrice", 25, 50, 175, 37.0 },
				{ "Green dragon", 35, 35, 200, 40.0 },
				{ "Cave horror", 58, 50, 135, 55.0 },
				{ "Fire giant", 62, 40, 120, 111.0 },
				{ "Zombie hand", 70, 40, 60, 115.0 },
				{ "Skeletal hand", 60, 50, 100, 90.0 },
				{ "Werewolf", 65, 70, 120, 105.0 },
				{ "Baby red dragon", 50, 60, 120, 50.0 },
				{ "Moss giant", 35, 30, 100, 40.0 },
				{ "Bronze dragon", 50, 30, 70, 125.0 },
				{ "Iron dragon", 65, 30, 70, 173.2 },
				{ "Steel dragon", 79, 30, 70, 220.4 },
				{ "Abyssal demons", 85, 60, 130, 150 },
				{ "Nechryael", 80, 60, 120, 105 },
				{ "Aberrant spectre", 60, 50, 100, 90 },
				{ "Infernal mage", 45, 30, 80, 60 },
				{ "Bloodveld", 50, 60, 90, 120 },
				{ "Gargoyle", 75, 70, 100, 105 },
				{ "Dark beast", 70, 90, 130, 225.4 },
				{ "Greater demon", 1, 50, 90, 87 },
				{ "Hellhound", 1, 50, 100, 116 },
				{ "Aquanites", 78, 50, 90, 125 }, 
				{ "Turoth", 55, 30, 80, 79 },
				{ "Kurask", 70, 40, 100, 97 }, 
				{ "Jelly", 52, 30, 80, 75 },
				{ "Pyrefiend", 45, 30, 100, 45 },
				{ "Basilisk", 40, 50, 90, 75 },
				{"Skeletal wyvern", 72, 40, 80, 210}
		});
		// {"Desert strykewyrm", 77, 50, 90, 120},
		// {"Jungle strykewyrm", 73, 40, 80, 110},
		// {"Ice strykewyrm", 93, 68, 120, 330},
		// {"Spiritual mage", 83, 60, 100, 88},
		 

		private int id;
		private int taskRewardPoints;
		private Object[][] data;
		private TaskGroup group;

		Master(int id, int taskRewardPoints, Object[][] data) {
			this.id = id;
			this.taskRewardPoints = taskRewardPoints;
			this.data = data;
		}

		public static Master forId(int id) {
			for (Master master : Master.values()) {
				if (master.id == id) {
					return master;
				}
			}
			return null;
		}

		public int getId() {
			return id;
		}

		public int getTaskRewardPoints() {
			return taskRewardPoints;
		}

		public Object[][] getData() {
			return data;
		}

	}

	private Master master;
	private int taskId;
	private int taskAmount;
	private int initialAmount;

	public SlayerTask(Master master, int taskId, int taskAmount) {
		this.master = master;
		this.taskId = taskId;
		this.initialAmount = taskAmount;
		this.taskAmount = taskAmount;
	}

	public String getName() {
		return (String) master.data[taskId][0];
	}

	public int getTaskId() {
		return taskId;
	}

	public int getTaskAmount() {
		return taskAmount;
	}

	public void decreaseAmount() {
		taskAmount--;
	}
	
	public double getXPAmount() {
		return Double.parseDouble(master.data[taskId][4].toString());
	}

	public int getInitialAmount() {
		return initialAmount;
	}

	public Master getMaster() {
		return master;
	}

	/*
	 1=monkeys
2=goblins
3 = rats
4 = spiders
5 = birds
6 = cows
7 = scorpions
8 = bats
9 = wolves
10 = zombies
11 = skeletons
12 = ghosts
13 = bears
14 = hill giants
15 = ice giants
16 = fire giants
17 = moss giants
18 = trolls
19 = ice warriors
20 = ogres
21 = hobgoblins
22 = dogs
23 = ghouls
24 = green dragons
25 = blue dragons
26 = red dragons
27 = black dragons
28 = lesser demons
29 = greater demons
30 = black demons
31 = hell hounds
32 = shadow warrior
33 = werewolves
34 = vampires
35 = daggannoths
36 = turoths
37 = cave crawlers
38 = banshees
39 = crawling hands
40 = infernal mages
41 = aberrant spectres
42 = abyssal demons
43 = basilisks
44 = cocatrice
45 = kurask
[02:25:01] Thomas: 45: kurasks
46: gargoyles
47: Pyrefiends
48: Bloodveld
49: Dust devils
50: jellies
51: rockslugs
52: Nechryael
53: Kalphite
54: Earth Warriors
56: Otherwordly beings
57: Dwarves
58: Bronze Dragons
59: Iron dragons
60: steel dragons
61: wall beasts
62: cave slimes
63: cave bugs
64: shades
65: crocodiles
66: dark beasts
67: mogres
68: desert lizards
69: fever spiders
70: harpie bug swarms
71: sea snakes
72: skeletal wyverns
73: killerwatts
74: mutated zygomites
75: icefiends
76: minotaurs
77: fleshcrawlers
78: catablepon
79: ankou
80: cave horrors
81: jungle horrors
82: goraks
83: suqahs
84: brine rats
85: minions of scabaras
86: terror dogs
87: molanisks
88: waterfiends
89: spiritual creatures
90: creatures
91: Creatures
92: cave kraken
93: mithril dragons
94: aviansies
95: smoke devils
96: tzhaar
97: tztok-jad
98: Bosses
99: creatures

	 */

}
