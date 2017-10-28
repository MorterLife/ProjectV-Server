package org.rs2server.rs2.content.misc;

import org.rs2server.Server;
import org.rs2server.rs2.domain.service.api.PlayerService;
import org.rs2server.rs2.model.Item;
import org.rs2server.rs2.model.player.Player;

import java.util.*;

/**
 * Created by Life @https://www.rune-server.ee/members/trikru./
 */
public class Casket {


    private static final List<Item> REWARD_ITEMS = new ArrayList<>();
    private static final List<Item> RARE_ITEMS = new ArrayList<>();

    private final Player player;
    private final PlayerService service;
    private static final Item CASKET = new Item(405);
    private final Random random;

    public Casket(Player player) {
        this.player = player;
        this.service = Server.getInjector().getInstance(PlayerService.class);
        this.random = new Random();
    }

    public Optional<Item> getRewardItem() {
        List<Item> items = new ArrayList<>();
        if (!player.getInventory().hasItem(CASKET)) {
            return Optional.empty();
        }
        items.addAll(getRandom() > 2 ? REWARD_ITEMS : RARE_ITEMS);
        Collections.shuffle(items);
        return Optional.of(items.get(0));
    }


    private int getRandom() {
        return random.nextInt(100);
    }


    static {
        REWARD_ITEMS.add(new Item(995, 500));//500 coins
        REWARD_ITEMS.add(new Item(995, 1000));//1 coins
        REWARD_ITEMS.add(new Item(995, 5000));//5 coins
        REWARD_ITEMS.add(new Item(995, 35000));//35 coins
        REWARD_ITEMS.add(new Item(995, 50000));//50 coins
        REWARD_ITEMS.add(new Item(995, 2000));//5 coins
        REWARD_ITEMS.add(new Item(995, 1500));//1.5 coins

        RARE_ITEMS.add(new Item(20080));//heads mummy
        RARE_ITEMS.add(new Item(20083));//body mummy
        RARE_ITEMS.add(new Item(20089));//legs mummy
        RARE_ITEMS.add(new Item(20086));//hand mummy
        RARE_ITEMS.add(new Item(20092));//boots mummy
        RARE_ITEMS.add(new Item(20017));//ring of coins

    }
}
