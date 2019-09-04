package toad.game.entities.buildings;

import toad.game.level.Level;
import toad.game.level.interior.int_apartment;
import toad.gfx.Assets;

public class Apartment_generic extends Building{
	
	public Apartment_generic(Level level, int x, int y) {
		super(level, x, y, Assets.apt_generic, new int_apartment());
		makeStandardDoor(32, 0);
	}
	
}
