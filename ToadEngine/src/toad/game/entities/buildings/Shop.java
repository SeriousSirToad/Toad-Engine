package toad.game.entities.buildings;

import java.util.ArrayList;

import toad.game.entities.Mob;
import toad.game.level.Level;
import toad.game.level.interior.int_00;
import toad.gfx.Assets;

public class Shop extends Building {
	
	ArrayList<Mob> npcs = new ArrayList<>();
	
	public Shop(Level level, int x, int y) {
		super(level, x, y, Assets.shop, new int_00());
		makeStandardDoor(32, 0);
	}

}
