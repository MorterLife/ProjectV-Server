package org.rs2server.rs2.event.impl;

import org.rs2server.rs2.event.Event;
import org.rs2server.rs2.model.World;

public class RandomMessage2Tick extends Event {



	public RandomMessage2Tick() {
		super(300000);
	}

	public void execute() {
		World.getWorld().sendWorldMessage("<img=21><col=ff0000>Players: There are currently " + World.getWorld().getPlayers().size() + " players online.");
	}

}
