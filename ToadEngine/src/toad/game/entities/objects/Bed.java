package toad.game.entities.objects;

import java.awt.Rectangle;

import toad.game.GameState;
import toad.game.level.Level;
import toad.gfx.Assets;
import toad.ui.GameWindow;

public class Bed extends GameObject{

	public Bed(Level level, int x, int y) {
		super(level, x, y, Assets.bed);
		collider = new Rectangle(x, y + (h - 18), w, 18);
	}

}
