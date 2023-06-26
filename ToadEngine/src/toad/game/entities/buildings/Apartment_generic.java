package toad.game.entities.buildings;

import toad.game.level.Level;
import toad.game.level.interior.int_apartment;
import toad.gfx.Assets;

import java.awt.*;

public class Apartment_generic extends Building{
	
	public Apartment_generic(Level level, int x, int y) {
		super(level, x, y, Assets.apt_generic, new int_apartment());

		int xOffset = 8;
		int yOffset = 18;
		makeStandardDoor(32, 0, new Point(80, 95), new Point(x + (w / 2) - xOffset, y + h - yOffset));
	}
}
