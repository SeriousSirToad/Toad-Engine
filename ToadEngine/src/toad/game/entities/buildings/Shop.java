package toad.game.entities.buildings;

import java.awt.*;
import java.util.ArrayList;

import toad.game.entities.Mob;
import toad.game.level.Level;
import toad.game.level.interior.int_generic;
import toad.gfx.Assets;

public class Shop extends Building {
	
	ArrayList<Mob> npcs = new ArrayList<>();
	
	public Shop(Level level, int x, int y) {
		super(level, x, y, Assets.shop, new int_generic());

		int xOffset = 8;
		int yOffset = 10;
		Point exteriorTpLocation = new Point(x + (w / 2) - xOffset, y + h - yOffset);
		Point interiorTpLocation = new Point(80, 95);

		makeStandardDoor(32, 0, interiorTpLocation, exteriorTpLocation);
	}
}
